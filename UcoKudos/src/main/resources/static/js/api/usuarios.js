// Contexto Identidad y cuentas: Rol, EstadoCuenta, Usuario y Auditoria, contra el backend real.
import { http } from '../http.js';
import { AUTORIDADES } from '../catalogos.js';
import {
  consulta, incluye, primero, nombreCompleto, directorio, nombreDePersona,
  sesionActual, usuarioActual, actorActual, usuarioDesdeServidor, fechaHoraONula, reiniciarSesion,
} from './adaptadores.js';

export { sesionActual, usuarioActual };

const RUTA = '/api/v1/usuarios';

// ---------- Catálogos ----------
export const consultarRoles = () => http.get('/api/v1/roles');
export const consultarEstadosCuenta = () => http.get('/api/v1/estados-cuenta');
export const consultarMotivos = () => http.get('/api/v1/motivos');
export const consultarEstadosContenido = () => http.get('/api/v1/estados-contenido');
export const consultarEstadosRevision = () => http.get('/api/v1/estados-revision');

// Roles que se pueden elegir al registrarse (el Rector y el Administrador los registra un Administrador).
export const consultarRolesRegistro = async () => {
  const roles = await http.get('/api/v1/roles');
  return roles.filter((r) => !['Administrador', 'Rector'].includes(r.nombre));
};

// ---------- Ingreso ----------
export const ingresar = async (correo) => {
  await http.post('/api/v1/autenticacion/ingreso', { correoInstitucional: correo });
  reiniciarSesion();
  return sesionActual();
};

// ---------- Registro ----------
export const registrarUsuario = async (datos) => {
  const usuario = primero(await http.post(RUTA, {
    usuario: { nombre: datos.nombre, apellido: datos.apellido, celular: datos.celular, rol: { id: datos.rol } },
    programas: (datos.programas ?? []).map((id) => ({ id })),
  }));
  return usuario;
};

// ---------- Consultas ----------
const publico = (u) => {
  const v = usuarioDesdeServidor(u);
  return {
    id: v.id, nombre: v.nombre, apellido: v.apellido, rol: v.rol, rolNombre: v.rolNombre,
    estadoCuenta: v.estadoCuenta, estadoNombre: v.estadoNombre,
  };
};

// No muestra correo ni celular. Los filtros de texto se aplican aquí porque el backend compara por igualdad.
export const consultarUsuarios = async (filtro = {}) => {
  const [usuarios, actor] = await Promise.all([
    http.get(consulta(RUTA, { rol: { id: filtro.rol }, estadoCuenta: { id: filtro.estadoCuenta } })),
    actorActual(),
  ]);
  const esAutoridad = AUTORIDADES.includes(actor.rol);
  return usuarios
    .filter((u) => incluye(u.nombre, filtro.nombre) && incluye(u.apellido, filtro.apellido))
    .map((u) => ({ ...publico(u), gestionable: esAutoridad && u.id !== actor.id }))
    .sort((a, b) => a.nombre.localeCompare(b.nombre));
};

export const consultarInformacionPersonalUsuario = async (id) => {
  const usuario = primero(await http.get(`${RUTA}/${id}`));
  return usuarioDesdeServidor(usuario);
};

export const consultarUsuariosPendientesAprobacion = async (filtro = {}) => {
  const usuarios = await http.get(consulta(`${RUTA}/pendientes-aprobacion`, { rol: { id: filtro.rol } }));
  return usuarios
    .map(usuarioDesdeServidor)
    .sort((a, b) => String(a.fechaRegistro).localeCompare(String(b.fechaRegistro)));
};

// ---------- Datos propios ----------
export const cambiarNombreUsuario = async (id, datos) => primero(
  await http.patch(`${RUTA}/${id}/nombre`, { nombre: datos.nombre, apellido: datos.apellido }),
);

export const cambiarCorreoUsuario = (id, datos) => http.patch(`${RUTA}/${id}/correo`, { correoInstitucional: datos.correoInstitucional });

export const cambiarCelularUsuario = (id, datos) => http.patch(`${RUTA}/${id}/celular`, { celular: datos.celular });

// ---------- Gestión de cuentas por la autoridad ----------
export const suspenderCuentaUsuario = (id, justificacion) => http.patch(`${RUTA}/${id}/suspension`, { justificacion });
export const reactivarCuentaUsuario = (id) => http.patch(`${RUTA}/${id}/reactivacion`);
export const desactivarCuentaUsuario = (id, justificacion) => http.patch(`${RUTA}/${id}/desactivacion`, { justificacion });
export const cambiarRolUsuario = (id, datos) => http.patch(`${RUTA}/${id}/rol`, { rol: { id: datos.rol } });

// ---------- Aprobación de registros ----------
export const aprobarUsuario = (id) => http.patch(`${RUTA}/${id}/aprobacion`);
export const rechazarUsuario = (id, justificacion) => http.patch(`${RUTA}/${id}/rechazo`, { justificacion });

// ---------- Auditoría ----------
async function consultarAuditoriasEn(ruta, filtro) {
  const [auditorias, dir] = await Promise.all([
    http.get(consulta(ruta, {
      usuario: { id: filtro.usuario },
      objetoAfectado: filtro.objetoAfectado,
      registroAfectado: filtro.registroAfectado,
    })),
    directorio(),
  ]);
  return auditorias
    .map((a) => ({
      id: a.id,
      usuario: a.usuario?.id,
      usuarioNombre: nombreDePersona(dir, a.usuario?.id),
      accion: a.accion,
      objetoAfectado: a.objetoAfectado,
      registroAfectado: a.registroAfectado,
      detalle: a.detalle,
      fechaHora: fechaHoraONula(a.fechaHora) ?? '',
    }))
    .filter((a) => incluye(a.accion, filtro.accion))
    .filter((a) => !filtro.desde || a.fechaHora.slice(0, 10) >= filtro.desde)
    .filter((a) => !filtro.hasta || a.fechaHora.slice(0, 10) <= filtro.hasta)
    .sort((a, b) => b.fechaHora.localeCompare(a.fechaHora));
}

export const consultarAuditorias = (filtro = {}) => consultarAuditoriasEn('/api/v1/auditorias', filtro);

export const consultarAuditoriasPropias = (filtro = {}) => consultarAuditoriasEn('/api/v1/auditorias/propias', { ...filtro, usuario: null });

export { nombreCompleto };
