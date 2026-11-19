// Piezas de interfaz compartidas por todas las páginas.
import * as api from './api.js';
import { cerrarSesion } from './sesion.js';
import { CUENTA, AUTORIDADES, MODERADORES, AUDITORES } from './catalogos.js';

export const esc = (t) => String(t ?? '').replace(/[&<>"']/g, (c) => ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;' }[c]));
export const $ = (sel, raiz = document) => raiz.querySelector(sel);
export const $$ = (sel, raiz = document) => [...raiz.querySelectorAll(sel)];

const fmtFecha = new Intl.DateTimeFormat('es-CO', { day: 'numeric', month: 'short', year: 'numeric' });
const fmtFechaHora = new Intl.DateTimeFormat('es-CO', { day: 'numeric', month: 'short', year: 'numeric', hour: 'numeric', minute: '2-digit' });
export const fecha = (v) => (v ? fmtFecha.format(new Date(v.length === 10 ? `${v}T12:00:00` : v)) : '');
export const fechaHora = (v) => (v ? fmtFechaHora.format(new Date(v)) : '');
export const hoyISO = () => new Date().toISOString().slice(0, 10);

const TONOS = {
  verde: ['Activa', 'Visible', 'Contenido liberado', 'Vigente', 'Validado'],
  oro: ['Pendiente de aprobación', 'Aprobada', 'Reportado', 'Pendiente', 'En revisión', 'En curso'],
  rojo: ['Suspendida', 'Desactivada', 'Rechazada', 'Vetado', 'Contenido vetado', 'Aceptado'],
};
export function insignia(texto) {
  const tono = Object.keys(TONOS).find((k) => TONOS[k].includes(texto)) ?? 'gris';
  return `<span class="insignia insignia-${tono}">${esc(texto)}</span>`;
}

export function opciones(lista, { valor = '', vacio = null, texto = (x) => x.nombre, id = (x) => x.id } = {}) {
  const base = vacio === null ? '' : `<option value="">${esc(vacio)}</option>`;
  return base + lista.map((x) => `<option value="${esc(id(x))}" ${id(x) === valor ? 'selected' : ''}>${esc(texto(x))}</option>`).join('');
}

export const datosForm = (form) => Object.fromEntries(new FormData(form));

export function iniciales(nombre) {
  return String(nombre).split(' ').filter(Boolean).slice(0, 2).map((p) => p[0]).join('').toUpperCase();
}

// ---------- Estrellas ----------
export function estrellasVista(promedio, total) {
  const pct = Math.max(0, Math.min(100, (promedio / 5) * 100));
  const etiqueta = total ? `${promedio.toFixed(1)} de 5, ${total} ${total === 1 ? 'calificación' : 'calificaciones'}` : 'Sin calificaciones';
  return `<span class="estrellas" role="img" aria-label="${etiqueta}"><span class="estrellas-fondo">★★★★★</span><span class="estrellas-llenas" style="width:${pct}%">★★★★★</span></span>
    <span class="estrellas-texto">${total ? `${promedio.toFixed(1)} (${total})` : 'Sin calificar'}</span>`;
}

// Selector de 1 a 5 estrellas: "en pantalla se selecciona con estrellas; nunca se escribe".
export function estrellasSelector(nombre, actual = 0, etiqueta = 'Su calificación') {
  const radios = [1, 2, 3, 4, 5].map((n) => `
    <input type="radio" id="${nombre}-${n}" name="${nombre}" value="${n}" ${n === actual ? 'checked' : ''}>
    <label for="${nombre}-${n}" title="${n} ${n === 1 ? 'estrella' : 'estrellas'}">★<span class="sr">${n} ${n === 1 ? 'estrella' : 'estrellas'}</span></label>`).reverse().join('');
  return `<fieldset class="selector-estrellas"><legend class="sr">${esc(etiqueta)}</legend>${radios}</fieldset>`;
}

// ---------- Avisos ----------
let zonaAvisos;
export function aviso(mensaje, tipo = 'ok') {
  if (!zonaAvisos) {
    zonaAvisos = document.createElement('div');
    zonaAvisos.className = 'avisos';
    zonaAvisos.setAttribute('role', 'status');
    zonaAvisos.setAttribute('aria-live', 'polite');
    document.body.append(zonaAvisos);
  }
  const el = document.createElement('div');
  el.className = `aviso aviso-${tipo}`;
  el.textContent = mensaje;
  zonaAvisos.append(el);
  setTimeout(() => el.remove(), tipo === 'error' ? 6500 : 3800);
}

// Muestra el error de una política junto al campo que lo causó o en el formulario.
export function mostrarError(e, form = null) {
  if (e?.name !== 'ErrorDominio' && e?.name !== 'ErrorServidor') {
    console.error(e);
    aviso('Algo falló al procesar la solicitud. Intente de nuevo.', 'error');
    return;
  }
  if (form) {
    limpiarErrores(form);
    const campo = e.campo && form.querySelector(`[name="${e.campo}"]`);
    if (campo) {
      campo.setAttribute('aria-invalid', 'true');
      const msg = document.createElement('p');
      msg.className = 'campo-error';
      msg.id = `err-${e.campo}`;
      msg.textContent = e.message;
      (campo.closest('.campo') ?? campo.parentElement).append(msg);
      campo.setAttribute('aria-describedby', msg.id);
      campo.focus();
      return;
    }
    const zona = form.querySelector('.error-form');
    if (zona) {
      zona.textContent = e.message;
      zona.hidden = false;
      return;
    }
  }
  aviso(e.message, 'error');
}

export function limpiarErrores(form) {
  $$('.campo-error', form).forEach((x) => x.remove());
  $$('[aria-invalid]', form).forEach((x) => x.removeAttribute('aria-invalid'));
  const zona = form.querySelector('.error-form');
  if (zona) zona.hidden = true;
}

// Envía un formulario a una función de la API, con estado de carga y errores en su sitio.
export function alEnviar(form, accion) {
  form.addEventListener('submit', async (ev) => {
    ev.preventDefault();
    limpiarErrores(form);
    const boton = form.querySelector('[type="submit"]');
    if (boton) boton.disabled = true;
    try {
      await accion(datosForm(form), form);
    } catch (e) {
      mostrarError(e, form);
    } finally {
      if (boton) boton.disabled = false;
    }
  });
}

// ---------- Diálogo ----------
// cuerpo: HTML con los campos. alAceptar recibe los datos; si lanza un error, el diálogo sigue abierto.
export function dialogo({ titulo, cuerpo = '', textoAceptar = 'Guardar', peligro = false, alAceptar }) {
  return new Promise((resolver) => {
    const d = document.createElement('dialog');
    d.className = 'dialogo';
    d.innerHTML = `
      <form method="dialog" novalidate>
        <h2>${esc(titulo)}</h2>
        <div class="dialogo-cuerpo">${cuerpo}</div>
        <p class="error-form" role="alert" hidden></p>
        <div class="acciones">
          <button type="button" class="boton boton-sutil" data-cancelar>Cancelar</button>
          <button type="submit" class="boton ${peligro ? 'boton-peligro' : 'boton-primario'}">${esc(textoAceptar)}</button>
        </div>
      </form>`;
    document.body.append(d);
    const form = $('form', d);
    const cerrar = (valor) => {
      d.close();
      d.remove();
      resolver(valor);
    };
    $('[data-cancelar]', d).addEventListener('click', () => cerrar(false));
    d.addEventListener('cancel', (ev) => {
      ev.preventDefault();
      cerrar(false);
    });
    alEnviar(form, async (datos) => {
      await alAceptar?.(datos, form);
      cerrar(true);
    });
    d.showModal();
    ($('textarea, input:not([type=hidden]), select', form) ?? $('[type=submit]', form)).focus();
  });
}

// ---------- Estructura de la página ----------
const MENU = [
  { href: 'kudos.html', texto: 'Kudos', id: 'kudos' },
  { href: 'categorias.html', texto: 'Categorías', id: 'categorias' },
  { href: 'comunidad.html', texto: 'Comunidad', id: 'comunidad', contador: 'pendientes' },
  { href: 'academia.html', texto: 'Estructura académica', id: 'academia' },
  { href: 'moderacion.html', texto: 'Moderación', id: 'moderacion', roles: MODERADORES, contador: 'reportes' },
  { href: 'auditoria.html', texto: 'Auditoría', id: 'auditoria', roles: AUDITORES },
  { href: 'perfil.html', texto: 'Mi cuenta', id: 'perfil' },
];

export const MARCA = `<a class="marca" href="kudos.html" aria-label="Kudos UCO, inicio">
  <svg viewBox="0 0 32 32" aria-hidden="true"><path d="M16 2.5l3.9 8.6 9.4 1-7 6.4 2 9.2L16 23l-8.3 4.7 2-9.2-7-6.4 9.4-1z"/></svg>
  <span>Kudos<small>UCO</small></span></a>`;

async function contadores(usuario) {
  const r = {};
  try {
    if (AUTORIDADES.includes(usuario.rol)) r.pendientes = (await api.consultarUsuariosPendientesAprobacion()).length;
    if (MODERADORES.includes(usuario.rol)) {
      const [rk, rr] = await Promise.all([api.consultarReportesKudo({ estado: 'Pendiente' }), api.consultarReportesReplica({ estado: 'Pendiente' })]);
      r.reportes = rk.length + rr.length;
    }
  } catch {
    /* los contadores son una ayuda; no bloquean la página */
  }
  return r;
}

export async function montarPagina({ pagina, titulo, descripcion = '' }) {
  const usuario = await api.usuarioActual();
  if (!usuario) {
    location.replace('index.html');
    return new Promise(() => {});
  }
  if (usuario.estadoCuenta !== CUENTA.ACTIVA) {
    location.replace('verificacion.html');
    return new Promise(() => {});
  }
  const menu = MENU.filter((m) => !m.roles || m.roles.includes(usuario.rol));
  const shell = document.createElement('div');
  shell.className = 'app';
  shell.innerHTML = `
    <a class="saltar" href="#contenido">Saltar al contenido</a>
    <aside class="lateral">
      <div class="lateral-cabeza">
        ${MARCA}
        <button class="boton-menu" type="button" aria-expanded="false" aria-controls="menu-principal">Menú</button>
      </div>
      <nav id="menu-principal" class="menu" aria-label="Principal">
        <ul>${menu.map((m) => `<li><a href="${m.href}" ${m.id === pagina ? 'aria-current="page"' : ''}>
          <span>${m.texto}</span>${m.contador ? `<span class="contador" data-contador="${m.contador}" hidden></span>` : ''}</a></li>`).join('')}</ul>
        <div class="yo">
          <span class="avatar" aria-hidden="true">${esc(iniciales(`${usuario.nombre} ${usuario.apellido}`))}</span>
          <span class="yo-datos"><strong>${esc(usuario.nombre)} ${esc(usuario.apellido)}</strong><small>${esc(usuario.rolNombre)}</small></span>
          <button type="button" class="boton boton-sutil boton-chico" data-salir>Salir</button>
        </div>
      </nav>
    </aside>
    <main class="principal" id="contenido" tabindex="-1">
      <header class="cabecera">
        <div><h1>${esc(titulo)}</h1>${descripcion ? `<p class="bajada">${esc(descripcion)}</p>` : ''}</div>
        <div class="cabecera-acciones" id="acciones"></div>
      </header>
      <div id="vista"></div>
    </main>`;
  document.body.prepend(shell);
  document.title = `${titulo} | Kudos UCO`;
  $('[data-salir]', shell).addEventListener('click', async () => {
    await cerrarSesion();
    location.href = 'index.html';
  });
  const botonMenu = $('.boton-menu', shell);
  botonMenu.addEventListener('click', () => {
    const abierto = botonMenu.getAttribute('aria-expanded') === 'true';
    botonMenu.setAttribute('aria-expanded', String(!abierto));
    shell.classList.toggle('menu-abierto', !abierto);
  });
  contadores(usuario).then((r) => {
    Object.entries(r).forEach(([k, n]) => {
      const el = $(`[data-contador="${k}"]`, shell);
      if (el && n) {
        el.textContent = n;
        el.hidden = false;
        el.setAttribute('aria-label', `${n} pendientes`);
      }
    });
  });
  return usuario;
}

export function vacio(mensaje, accion = '') {
  return `<div class="vacio"><p>${esc(mensaje)}</p>${accion}</div>`;
}

export function cargando() {
  return '<p class="cargando" role="status">Cargando…</p>';
}

// Pestañas accesibles: botones con aria-selected y paneles por id.
export function pestanas(contenedor, alCambiar) {
  const botones = $$('[role="tab"]', contenedor);
  const activar = (b) => {
    botones.forEach((x) => {
      const activo = x === b;
      x.setAttribute('aria-selected', String(activo));
      x.tabIndex = activo ? 0 : -1;
      const panel = document.getElementById(x.getAttribute('aria-controls'));
      if (panel) panel.hidden = !activo;
    });
    alCambiar?.(b.dataset.tab);
    try {
      history.replaceState(null, '', `#${b.dataset.tab}`);
    } catch {
      /* sin historial */
    }
  };
  botones.forEach((b, i) => {
    b.addEventListener('click', () => activar(b));
    b.addEventListener('keydown', (ev) => {
      const d = ev.key === 'ArrowRight' ? 1 : ev.key === 'ArrowLeft' ? -1 : 0;
      if (!d) return;
      const sig = botones[(i + d + botones.length) % botones.length];
      sig.focus();
      activar(sig);
    });
  });
  const inicial = botones.find((b) => `#${b.dataset.tab}` === location.hash) ?? botones[0];
  activar(inicial);
}
