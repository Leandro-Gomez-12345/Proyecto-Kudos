// Contexto Moderación: ReporteKudo, ReporteReplica, RevisionKudo y RevisionReplica, contra el backend real.
// El estado de un reporte no se guarda: se deduce de la revisión que lo resuelve (P-RPK-0006).
import { http } from '../http.js';
import { REVISION, nombreDe } from '../catalogos.js';
import {
  consulta, primero, directorio, nombreDePersona, fechaHoraONula, idONulo,
} from './adaptadores.js';

const TIPOS = {
  kudo: { reportes: '/api/v1/reportes-kudo', revisiones: '/api/v1/revisiones-kudo', campoRevision: 'revisionKudo' },
  replica: { reportes: '/api/v1/reportes-replica', revisiones: '/api/v1/revisiones-replica', campoRevision: 'revisionReplica' },
};

export function estadoReporte(estadoRevision) {
  if (!estadoRevision) return 'Pendiente';
  if (estadoRevision === REVISION.EN_CURSO) return 'En revisión';
  if (estadoRevision === REVISION.VETADO) return 'Aceptado';
  return 'Rechazado';
}

function resumen(contenido, idKudo, kudo, dir) {
  return {
    id: contenido.id,
    kudo: idKudo,
    contenido: contenido.contenido,
    autorNombre: nombreDePersona(dir, contenido.autor?.id),
    estadoContenido: contenido.estadoContenido?.id,
    estadoNombre: nombreDe('estadosContenido', contenido.estadoContenido?.id),
    fechaCreacion: fechaHoraONula(contenido.fechaCreacion) ?? '',
    destinatarioNombre: kudo ? nombreDePersona(dir, kudo.destinatario?.id) : '',
  };
}

// El backend no entrega el contenido dentro del reporte ni de la revisión: se busca por id.
// Para las réplicas no existe una consulta por id, así que se recorren las conversaciones de los Kudos.
async function indiceContenido(tipo, dir) {
  const kudos = await http.get('/api/v1/kudos');
  const indice = new Map();
  if (tipo === 'kudo') {
    kudos.forEach((k) => indice.set(k.id, resumen(k, k.id, k, dir)));
    return indice;
  }
  const conversaciones = await Promise.all(kudos.map(async (k) => ({
    kudo: k,
    replicas: await http.get(consulta('/api/v1/replicas', { kudo: { id: k.id } })).catch(() => []),
  })));
  conversaciones.forEach(({ kudo, replicas }) => replicas.forEach((r) => indice.set(r.id, resumen(r, kudo.id, kudo, dir))));
  return indice;
}

function crear(tipo) {
  const t = TIPOS[tipo];

  const reportar = async (datos) => primero(await http.post(t.reportes, {
    [tipo]: { id: datos[tipo] },
    motivo: { id: datos.motivo },
    descripcion: datos.descripcion ?? '',
  }));

  const leerRevisiones = (filtro = {}) => http.get(consulta(t.revisiones, {
    [tipo]: { id: filtro[tipo] },
    moderador: { id: filtro.moderador },
    estadoRevision: { id: filtro.estadoRevision },
  }));

  const leerReportes = (filtro = {}) => http.get(consulta(t.reportes, {
    [tipo]: { id: filtro[tipo] },
    motivo: { id: filtro.motivo },
  }));

  const reporteVista = (r, dir, estadosPorRevision, indice) => {
    const idRevision = idONulo(r[t.campoRevision]);
    return {
      id: r.id,
      [tipo]: r[tipo]?.id,
      usuario: r.usuario?.id,
      usuarioNombre: nombreDePersona(dir, r.usuario?.id),
      motivo: r.motivo?.id,
      motivoNombre: nombreDe('motivos', r.motivo?.id),
      descripcion: r.descripcion || null,
      [t.campoRevision]: idRevision,
      fechaReporte: fechaHoraONula(r.fechaReporte) ?? '',
      estado: estadoReporte(idRevision ? estadosPorRevision.get(idRevision) ?? REVISION.EN_CURSO : null),
      contenidoResumen: indice.get(r[tipo]?.id) ?? null,
    };
  };

  const consultarReportes = async (filtro = {}) => {
    const dir = await directorio();
    const [reportes, revisiones, indice] = await Promise.all([
      leerReportes(filtro),
      leerRevisiones().catch(() => []),
      indiceContenido(tipo, dir).catch(() => new Map()),
    ]);
    const estadosPorRevision = new Map(revisiones.map((x) => [x.id, x.estadoRevision?.id]));
    return reportes
      .map((r) => reporteVista(r, dir, estadosPorRevision, indice))
      .filter((r) => !filtro.estado || r.estado === filtro.estado)
      .sort((a, b) => b.fechaReporte.localeCompare(a.fechaReporte));
  };

  const iniciarRevision = async (datos) => primero(await http.post(t.revisiones, { [tipo]: { id: datos[tipo] } }));

  const cerrar = (accion) => (id, justificacion) => http.patch(`${t.revisiones}/${id}/${accion}`, { justificacion });

  const vetarDirectamente = async (datos) => primero(await http.post(`${t.revisiones}/veto-directo`, {
    [tipo]: { id: datos[tipo] },
    justificacion: datos.justificacion,
  }));

  const consultarRevisiones = async (filtro = {}) => {
    const dir = await directorio();
    const [revisiones, reportes, indice] = await Promise.all([
      leerRevisiones(filtro),
      leerReportes().catch(() => []),
      indiceContenido(tipo, dir).catch(() => new Map()),
    ]);
    const estadosPorRevision = new Map(revisiones.map((x) => [x.id, x.estadoRevision?.id]));
    return revisiones
      .map((r) => {
        const suyos = reportes
          .filter((x) => idONulo(x[t.campoRevision]) === r.id)
          .map((x) => reporteVista(x, dir, estadosPorRevision, indice));
        const fechaCierre = fechaHoraONula(r.fechaCierre);
        return {
          id: r.id,
          [tipo]: r[tipo]?.id,
          moderador: r.moderador?.id,
          moderadorNombre: nombreDePersona(dir, r.moderador?.id),
          estadoRevision: r.estadoRevision?.id,
          estadoNombre: nombreDe('estadosRevision', r.estadoRevision?.id),
          justificacion: r.justificacion || null,
          fechaInicio: fechaHoraONula(r.fechaInicio) ?? '',
          fechaCierre,
          directa: Boolean(fechaCierre) && suyos.length === 0,
          reportes: suyos,
          contenidoResumen: indice.get(r[tipo]?.id) ?? null,
        };
      })
      .sort((a, b) => b.fechaInicio.localeCompare(a.fechaInicio));
  };

  return {
    reportar, consultarReportes, iniciarRevision, cerrarConVeto: cerrar('veto'),
    cerrarConLiberacion: cerrar('liberacion'), vetarDirectamente, consultarRevisiones,
  };
}

const mk = crear('kudo');
const mr = crear('replica');

export const reportarKudo = mk.reportar;
export const consultarReportesKudo = mk.consultarReportes;
export const iniciarRevisionKudo = mk.iniciarRevision;
export const cerrarRevisionKudoConVeto = mk.cerrarConVeto;
export const cerrarRevisionKudoConLiberacion = mk.cerrarConLiberacion;
export const vetarKudoDirectamente = mk.vetarDirectamente;
export const consultarRevisionesKudo = mk.consultarRevisiones;

export const reportarReplica = mr.reportar;
export const consultarReportesReplica = mr.consultarReportes;
export const iniciarRevisionReplica = mr.iniciarRevision;
export const cerrarRevisionReplicaConVeto = mr.cerrarConVeto;
export const cerrarRevisionReplicaConLiberacion = mr.cerrarConLiberacion;
export const vetarReplicaDirectamente = mr.vetarDirectamente;
export const consultarRevisionesReplica = mr.consultarRevisiones;
