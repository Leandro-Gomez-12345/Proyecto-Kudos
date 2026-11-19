// Piezas compartidas por los módulos que hablan con el backend real: filtros, sesión, catálogos y nombres.
// El backend devuelve objetos anidados con solo el id (por ejemplo, el autor de un Kudo); aquí se completan
// con el directorio de personas y con los catálogos para que las pantallas reciban datos listos para mostrar.
import { http } from '../http.js';
import { CATALOGOS, nombreDe, sustituirIdsDeCatalogos } from '../catalogos.js';

const UUID_VACIO = '00000000-0000-0000-0000-000000000000';
const ANIO_SIN_FECHA = '1000';
const NOMBRE_CUENTA_ACTIVA = 'Activa';

const comparable = (texto) => String(texto ?? '').normalize('NFD').replace(/[̀-ͯ]/g, '').toLowerCase().trim();

// Los filtros de texto de las pantallas son de tipo "contiene"; el backend compara por igualdad.
export const incluye = (texto, filtro) => !filtro || comparable(texto).includes(comparable(filtro));

export const primero = (lista) => lista[0];

// Arma la dirección con filtros; los objetos anidados se envían como "autor.id=...".
export function consulta(ruta, filtro = {}) {
  const parametros = new URLSearchParams();
  const agregar = (clave, valor) => {
    if (valor === undefined || valor === null || valor === '') return;
    if (typeof valor === 'object') Object.entries(valor).forEach(([k, v]) => agregar(`${clave}.${k}`, v));
    else parametros.append(clave, valor);
  };
  Object.entries(filtro).forEach(([clave, valor]) => agregar(clave, valor));
  const texto = parametros.toString();
  return texto ? `${ruta}?${texto}` : ruta;
}

export const idONulo = (objeto) => (!objeto?.id || objeto.id === UUID_VACIO ? null : objeto.id);

// El backend usa 1000-01-01 como "sin fecha".
export const fechaONula = (fecha) => (!fecha || String(fecha).startsWith(ANIO_SIN_FECHA) ? null : fecha);
export const fechaHoraONula = fechaONula;

export const nombreCompleto = (u) => [u?.nombre, u?.apellido].filter(Boolean).join(' ');

// ---------- Directorio de personas (para completar nombres) ----------
let directorioEnCache = null;

export function directorio() {
  directorioEnCache ??= http.get('/api/v1/usuarios')
    .then((lista) => new Map(lista.map((u) => [u.id, u])))
    .catch(() => {
      directorioEnCache = null;
      return new Map();
    });
  return directorioEnCache;
}

export const nombreDePersona = (dir, id) => nombreCompleto(dir.get(id)) || 'Persona de la comunidad';

// ---------- Sesión y catálogos ----------
let catalogosCargados = null;

function asegurarCatalogos() {
  catalogosCargados ??= Promise.all([
    http.get('/api/v1/roles'),
    http.get('/api/v1/estados-cuenta'),
    http.get('/api/v1/estados-contenido'),
    http.get('/api/v1/estados-revision'),
    http.get('/api/v1/motivos'),
  ]).then(([roles, estadosCuenta, estadosContenido, estadosRevision, motivos]) => {
    sustituirIdsDeCatalogos({ roles, estadosCuenta, estadosContenido, estadosRevision, motivos });
  }).catch((e) => {
    catalogosCargados = null;
    throw e;
  });
  return catalogosCargados;
}

const idPorNombre = (lista, nombre) => CATALOGOS[lista].find((x) => comparable(x.nombre) === comparable(nombre))?.id ?? '';

export function usuarioDesdeServidor(u) {
  const rol = u.rol?.nombre ? idPorNombre('roles', u.rol.nombre) : u.rol?.id ?? '';
  const estadoCuenta = u.estadoCuenta?.nombre ? idPorNombre('estadosCuenta', u.estadoCuenta.nombre) : u.estadoCuenta?.id ?? '';
  return {
    id: u.id,
    nombre: u.nombre,
    apellido: u.apellido,
    correoInstitucional: u.correoInstitucional,
    celular: u.celular,
    correoVerificado: u.correoVerificado,
    celularVerificado: u.celularVerificado,
    rol,
    estadoCuenta,
    rolNombre: u.rol?.nombre || nombreDe('roles', rol),
    estadoNombre: u.estadoCuenta?.nombre || nombreDe('estadosCuenta', estadoCuenta),
    fechaRegistro: u.fechaRegistro,
  };
}

let sesionEnCache = null;

export function reiniciarSesion() {
  sesionEnCache = null;
}

// Devuelve null si no hay sesión; si la hay, indica si ese correo ya tiene cuenta registrada en Kudos.
export function sesionActual() {
  sesionEnCache ??= (async () => {
    try {
      const [sesion] = await http.get('/api/v1/autenticacion');
      if (!sesion) return null;
      if (!sesion.registrado) return { registrado: false, usuario: sesion.usuario };
      if (sesion.usuario.estadoCuenta?.nombre === NOMBRE_CUENTA_ACTIVA) {
        await asegurarCatalogos();
      }
      return { registrado: true, usuario: usuarioDesdeServidor(sesion.usuario) };
    } catch (e) {
      if (e.estado === 401) return null;
      throw e;
    }
  })().catch((e) => {
    sesionEnCache = null;
    throw e;
  });
  return sesionEnCache;
}

// Usuario registrado de la sesión, en cualquier estado de cuenta (null si no hay sesión o no está registrado).
export async function usuarioActual() {
  const sesion = await sesionActual();
  return sesion?.registrado ? sesion.usuario : null;
}

export async function actorActual() {
  const usuario = await usuarioActual();
  if (!usuario) throw new Error('Debe iniciar sesión para realizar esta acción.');
  return usuario;
}

// ---------- Calificaciones ----------
export function resumenCalificaciones(calificaciones, idActor) {
  const total = calificaciones.length;
  const mia = calificaciones.find((c) => c.usuario?.id === idActor);
  return {
    promedio: total ? calificaciones.reduce((suma, c) => suma + c.estrellas, 0) / total : 0,
    calificaciones: total,
    miCalificacion: mia ? { id: mia.id, estrellas: mia.estrellas, fechaCalificacion: mia.fechaCalificacion } : null,
  };
}

export const SIN_CALIFICACIONES = { promedio: 0, calificaciones: 0, miCalificacion: null };
