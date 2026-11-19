// Representación de un Kudo, compartida por el muro y el detalle.
import { CONTENIDO } from './catalogos.js';
import { esc, fechaHora, estrellasVista, insignia } from './ui.js';

const ESTRELLA = '<svg viewBox="0 0 32 32" aria-hidden="true"><path d="M16 2.5l3.9 8.6 9.4 1-7 6.4 2 9.2L16 23l-8.3 4.7 2-9.2-7-6.4 9.4-1z"/></svg>';

export function tarjetaKudo(k, { grande = false, enlace = true } = {}) {
  const vetado = k.estadoContenido === CONTENIDO.VETADO;
  const estado = k.estadoContenido === CONTENIDO.VISIBLE ? '' : insignia(k.estadoNombre);
  return `
    <article class="kudo ${grande ? 'kudo-grande' : ''} ${vetado ? 'kudo-vetado' : ''}" aria-labelledby="k-${k.id}">
      <div class="fila" style="justify-content:space-between">
        <span class="kudo-cinta">${ESTRELLA}${esc(k.nombreCategoria)}</span>
        ${estado}
      </div>
      <${grande ? 'h1' : 'h2'} class="kudo-para" id="k-${k.id}">Para ${esc(k.destinatarioNombre)}</${grande ? 'h1' : 'h2'}>
      <p class="kudo-de">De ${esc(k.autorNombre)}, ${esc(fechaHora(k.fechaCreacion))}</p>
      <p class="kudo-mensaje">${esc(k.contenido)}</p>
      <div class="kudo-pie">
        <span>${estrellasVista(k.promedio, k.calificaciones)}</span>
        ${enlace ? `<a href="kudo.html?id=${encodeURIComponent(k.id)}">${k.replicas ? `Ver conversación (${k.replicas})` : 'Ver y responder'}</a>` : ''}
      </div>
    </article>`;
}
