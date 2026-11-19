// Contexto Reconocimientos: Categoria, Kudo, Replica, CalificacionKudo y CalificacionReplica, contra el backend real.
import { http } from '../http.js';
import { CONTENIDO, MODERADORES, nombreDe } from '../catalogos.js';
import {
  consulta, incluye, primero, directorio, nombreDePersona, actorActual, fechaHoraONula, idONulo,
  resumenCalificaciones, SIN_CALIFICACIONES,
} from './adaptadores.js';

// ---------- Categoría ----------
const RUTA_CATEGORIAS = '/api/v1/categorias';

const categoriaVista = (c, kudos = []) => ({
  id: c.id, nombre: c.nombre, descripcion: c.descripcion, activa: c.activa,
  kudos: kudos.filter((k) => k.categoria?.id === c.id).length,
});

export const registrarCategoria = async (datos) => categoriaVista(
  primero(await http.post(RUTA_CATEGORIAS, { nombre: datos.nombre, descripcion: datos.descripcion })),
);

export const consultarCategorias = async (filtro = {}) => {
  const [categorias, kudos] = await Promise.all([
    http.get(RUTA_CATEGORIAS),
    http.get('/api/v1/kudos').catch(() => []),
  ]);
  const soloActivas = filtro.activa === true || filtro.activa === 'true';
  const soloInactivas = filtro.activa === false || filtro.activa === 'false';
  return categorias
    .filter((c) => incluye(c.nombre, filtro.nombre))
    .filter((c) => (soloActivas ? c.activa : soloInactivas ? !c.activa : true))
    .map((c) => categoriaVista(c, kudos))
    .sort((a, b) => Number(b.activa) - Number(a.activa) || a.nombre.localeCompare(b.nombre));
};

export const cambiarNombreCategoria = async (id, datos) => categoriaVista(
  primero(await http.patch(`${RUTA_CATEGORIAS}/${id}/nombre`, { nombre: datos.nombre })),
);

export const cambiarDescripcionCategoria = async (id, datos) => categoriaVista(
  primero(await http.patch(`${RUTA_CATEGORIAS}/${id}/descripcion`, { descripcion: datos.descripcion })),
);

export const activarCategoria = (id) => http.patch(`${RUTA_CATEGORIAS}/${id}/activacion`);
export const desactivarCategoria = (id) => http.patch(`${RUTA_CATEGORIAS}/${id}/desactivacion`);

// ---------- Kudo ----------
const RUTA_KUDOS = '/api/v1/kudos';

function kudoVista(k, dir, extra = SIN_CALIFICACIONES, replicas = 0) {
  return {
    id: k.id,
    autor: k.autor?.id,
    destinatario: k.destinatario?.id,
    categoria: k.categoria?.id,
    nombreCategoria: k.nombreCategoria,
    contenido: k.contenido,
    estadoContenido: k.estadoContenido?.id,
    estadoNombre: nombreDe('estadosContenido', k.estadoContenido?.id),
    fechaCreacion: fechaHoraONula(k.fechaCreacion) ?? '',
    autorNombre: nombreDePersona(dir, k.autor?.id),
    destinatarioNombre: nombreDePersona(dir, k.destinatario?.id),
    replicas,
    ...extra,
  };
}

// El backend no entrega promedios ni conteos: se calculan al consultar (indicador de calificación del modelo).
async function kudoEnriquecido(k, dir, actor) {
  try {
    const [calificaciones, replicas] = await Promise.all([
      http.get(consulta('/api/v1/calificaciones-kudo', { kudo: { id: k.id } })),
      http.get(consulta('/api/v1/replicas', { kudo: { id: k.id } })),
    ]);
    return kudoVista(k, dir, resumenCalificaciones(calificaciones, actor.id), replicas.length);
  } catch {
    return kudoVista(k, dir);
  }
}

export const crearKudo = async (datos) => {
  const [kudos, dir] = await Promise.all([
    http.post(RUTA_KUDOS, { destinatario: { id: datos.destinatario }, categoria: { id: datos.categoria }, contenido: datos.contenido }),
    directorio(),
  ]);
  const kudo = primero(kudos);
  return kudo ? kudoVista(kudo, dir) : null;
};

export const consultarKudo = async (id) => {
  const [kudo, dir, actor] = await Promise.all([http.get(`${RUTA_KUDOS}/${id}`), directorio(), actorActual()]);
  return kudoEnriquecido(primero(kudo), dir, actor);
};

export const consultarKudos = async (filtro = {}) => {
  const base = { categoria: { id: filtro.categoria }, estadoContenido: { id: filtro.estadoContenido } };
  const consultas = filtro.persona
    ? [{ ...base, autor: { id: filtro.persona } }, { ...base, destinatario: { id: filtro.persona } }]
    : [{ ...base, autor: { id: filtro.autor }, destinatario: { id: filtro.destinatario } }];
  const [resultados, dir, actor] = await Promise.all([
    Promise.all(consultas.map((c) => http.get(consulta(RUTA_KUDOS, c)))),
    directorio(),
    actorActual(),
  ]);
  const unicos = new Map(resultados.flat().map((k) => [k.id, k]));
  const kudos = [...unicos.values()].filter((k) => incluye(k.contenido, filtro.texto));
  const vistas = await Promise.all(kudos.map((k) => kudoEnriquecido(k, dir, actor)));
  return vistas.sort((a, b) => b.fechaCreacion.localeCompare(a.fechaCreacion));
};

// ---------- Réplica ----------
const RUTA_REPLICAS = '/api/v1/replicas';

export const replicarKudo = async (datos) => primero(await http.post(RUTA_REPLICAS, {
  kudo: { id: datos.kudo },
  contenido: datos.contenido,
}));

export const responderReplica = async (datos) => primero(await http.post(`${RUTA_REPLICAS}/respuestas`, {
  kudo: datos.kudo ? { id: datos.kudo } : undefined,
  replicaPadre: { id: datos.replicaPadre },
  contenido: datos.contenido,
}));

// Árbol de la conversación. Las réplicas vetadas se ocultan para quien no modera,
// pero se conserva su lugar para no romper el hilo de las respuestas.
export const consultarConversacionKudo = async (filtro) => {
  const [replicas, dir, actor] = await Promise.all([
    http.get(consulta(RUTA_REPLICAS, { kudo: { id: filtro.kudo } })),
    directorio(),
    actorActual(),
  ]);
  const puedeVerVetado = MODERADORES.includes(actor.rol);
  const vistas = await Promise.all(replicas.map(async (r) => {
    let resumen = SIN_CALIFICACIONES;
    try {
      resumen = resumenCalificaciones(await http.get(consulta('/api/v1/calificaciones-replica', { replica: { id: r.id } })), actor.id);
    } catch {
      /* sin calificaciones disponibles */
    }
    const oculta = r.estadoContenido?.id === CONTENIDO.VETADO && !puedeVerVetado;
    return {
      id: r.id,
      kudo: r.kudo?.id ?? filtro.kudo,
      replicaPadre: idONulo(r.replicaPadre),
      fechaCreacion: fechaHoraONula(r.fechaCreacion) ?? '',
      estadoContenido: r.estadoContenido?.id,
      estadoNombre: nombreDe('estadosContenido', r.estadoContenido?.id),
      oculta,
      autor: oculta ? null : r.autor?.id,
      autorNombre: oculta ? '' : nombreDePersona(dir, r.autor?.id),
      contenido: oculta ? '' : r.contenido,
      ...resumen,
    };
  }));
  return vistas.sort((a, b) => a.fechaCreacion.localeCompare(b.fechaCreacion));
};

// ---------- Calificaciones (genérico para Kudo y Réplica) ----------
function calificaciones(tipo) {
  const ruta = `/api/v1/calificaciones-${tipo}`;

  const calificar = async (datos) => primero(await http.post(ruta, { [tipo]: { id: datos[tipo] }, estrellas: Number(datos.estrellas) }));

  const cambiar = async (id, datos) => primero(await http.patch(`${ruta}/${id}/estrellas`, { estrellas: Number(datos.estrellas) }));

  const consultar = async (filtro = {}) => {
    const [lista, dir] = await Promise.all([
      http.get(consulta(ruta, { [tipo]: { id: filtro[tipo] }, usuario: { id: filtro.usuario } })),
      directorio(),
    ]);
    return lista
      .map((c) => ({
        id: c.id,
        [tipo]: c[tipo]?.id,
        usuario: c.usuario?.id,
        usuarioNombre: nombreDePersona(dir, c.usuario?.id),
        estrellas: c.estrellas,
        fechaCalificacion: fechaHoraONula(c.fechaCalificacion) ?? '',
      }))
      .sort((a, b) => b.fechaCalificacion.localeCompare(a.fechaCalificacion));
  };

  return { calificar, cambiar, consultar };
}

const ck = calificaciones('kudo');
const cr = calificaciones('replica');
export const calificarKudo = ck.calificar;
export const cambiarCalificacionKudo = ck.cambiar;
export const consultarCalificacionesKudo = ck.consultar;
export const calificarReplica = cr.calificar;
export const cambiarCalificacionReplica = cr.cambiar;
export const consultarCalificacionesReplica = cr.consultar;
