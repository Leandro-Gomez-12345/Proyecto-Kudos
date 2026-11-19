// Contexto Estructura académica: Facultad, ProgramaAcademico, CoordinacionFacultad,
// CoordinacionPrograma y VinculacionAcademica, contra el backend real.
import { http, ErrorServidor } from '../http.js';
import { nombreDe } from '../catalogos.js';
import {
  consulta, incluye, primero, directorio, nombreDePersona, actorActual, fechaONula,
} from './adaptadores.js';

const RUTA_FACULTADES = '/api/v1/facultades';
const RUTA_PROGRAMAS = '/api/v1/programas-academicos';

const hoy = () => new Date().toISOString().slice(0, 10);

// ---------- Facultad ----------
export const registrarFacultad = async (datos) => primero(await http.post(RUTA_FACULTADES, { nombre: datos.nombre }));

export const consultarFacultades = async (filtro = {}) => {
  const [facultades, programas] = await Promise.all([http.get(RUTA_FACULTADES), http.get(RUTA_PROGRAMAS)]);
  return facultades
    .filter((f) => incluye(f.nombre, filtro.nombre))
    .map((f) => ({ id: f.id, nombre: f.nombre, programas: programas.filter((p) => p.facultad?.id === f.id).length }))
    .sort((a, b) => a.nombre.localeCompare(b.nombre));
};

export const cambiarNombreFacultad = async (id, datos) => primero(await http.patch(`${RUTA_FACULTADES}/${id}/nombre`, { nombre: datos.nombre }));

// ---------- Programa académico ----------
export const registrarProgramaAcademico = async (datos) => primero(
  await http.post(RUTA_PROGRAMAS, { facultad: { id: datos.facultad }, nombre: datos.nombre }),
);

export const consultarProgramasAcademicos = async (filtro = {}) => {
  const [programas, facultades] = await Promise.all([
    http.get(consulta(RUTA_PROGRAMAS, { facultad: { id: filtro.facultad } })),
    http.get(RUTA_FACULTADES).catch(() => []),
  ]);
  const nombresFacultad = new Map(facultades.map((f) => [f.id, f.nombre]));
  return programas
    .filter((p) => incluye(p.nombre, filtro.nombre))
    .map((p) => ({ id: p.id, facultad: p.facultad?.id, facultadNombre: nombresFacultad.get(p.facultad?.id) ?? '', nombre: p.nombre }))
    .sort((a, b) => a.nombre.localeCompare(b.nombre));
};

export const cambiarNombreProgramaAcademico = async (id, datos) => primero(
  await http.patch(`${RUTA_PROGRAMAS}/${id}/nombre`, { nombre: datos.nombre }),
);

// ---------- Coordinaciones (genérico para facultad y programa) ----------
const COORD = {
  facultad: { ruta: '/api/v1/coordinaciones-facultad', unidad: 'facultad', rutaUnidades: RUTA_FACULTADES },
  programa: { ruta: '/api/v1/coordinaciones-programa', unidad: 'programaAcademico', rutaUnidades: RUTA_PROGRAMAS },
};

const MENSAJE_SIN_REEMPLAZO = 'El backend todavía no ofrece la operación de reemplazar al coordinador (terminar la coordinación vigente).';

function asignar(tipo) {
  const c = COORD[tipo];
  return async (datos) => primero(await http.post(c.ruta, {
    usuario: { id: datos.usuario },
    [c.unidad]: { id: datos[c.unidad] },
    fechaInicio: datos.fechaInicio,
  }));
}

function reemplazar() {
  return () => Promise.reject(new ErrorServidor(MENSAJE_SIN_REEMPLAZO));
}

async function leerCoordinaciones(tipo, filtro = {}) {
  const c = COORD[tipo];
  const [coordinaciones, dir, unidades] = await Promise.all([
    http.get(consulta(c.ruta, { usuario: { id: filtro.usuario }, [c.unidad]: { id: filtro[c.unidad] } })),
    directorio(),
    http.get(c.rutaUnidades),
  ]);
  const nombresUnidad = new Map(unidades.map((u) => [u.id, u.nombre]));
  return coordinaciones
    .map((x) => ({
      id: x.id,
      usuario: x.usuario?.id,
      usuarioNombre: nombreDePersona(dir, x.usuario?.id),
      [c.unidad]: x[c.unidad]?.id,
      unidadNombre: nombresUnidad.get(x[c.unidad]?.id) ?? '',
      fechaInicio: fechaONula(x.fechaInicio),
      fechaFin: fechaONula(x.fechaFin),
    }))
    .sort((a, b) => (!a.fechaFin === !b.fechaFin ? String(b.fechaInicio).localeCompare(String(a.fechaInicio)) : a.fechaFin ? 1 : -1));
}

export const asignarCoordinadorFacultad = asignar('facultad');
export const reemplazarCoordinadorFacultad = reemplazar('facultad');
export const consultarCoordinacionesFacultad = (filtro = {}) => leerCoordinaciones('facultad', filtro);
export const asignarCoordinadorPrograma = asignar('programa');
export const reemplazarCoordinadorPrograma = reemplazar('programa');
export const consultarCoordinacionesPrograma = (filtro = {}) => leerCoordinaciones('programa', filtro);

// ---------- Vinculación académica ----------
const RUTA_VINCULACIONES = '/api/v1/vinculaciones-academicas';

export const vincularPersonaAProgramaAcademico = async (datos) => primero(await http.post(RUTA_VINCULACIONES, {
  usuario: { id: datos.usuario },
  programaAcademico: { id: datos.programaAcademico },
  fechaVinculacion: datos.fechaVinculacion,
}));

export const desvincularPersonaDeProgramaAcademico = (id, datos) => http.patch(`${RUTA_VINCULACIONES}/${id}/desvinculacion`, {
  fechaDesvinculacion: datos.fechaDesvinculacion,
});

export const consultarVinculacionesAcademicas = async (filtro = {}) => {
  const [vinculaciones, dir, programas] = await Promise.all([
    http.get(consulta(RUTA_VINCULACIONES, { usuario: { id: filtro.usuario }, programaAcademico: { id: filtro.programaAcademico } })),
    directorio(),
    http.get(RUTA_PROGRAMAS),
  ]);
  const nombresPrograma = new Map(programas.map((p) => [p.id, p.nombre]));
  return vinculaciones
    .map((v) => ({
      id: v.id,
      usuario: v.usuario?.id,
      usuarioNombre: nombreDePersona(dir, v.usuario?.id),
      rolNombre: dir.get(v.usuario?.id)?.rol?.nombre || nombreDe('roles', dir.get(v.usuario?.id)?.rol?.id),
      programaAcademico: v.programaAcademico?.id,
      programaNombre: nombresPrograma.get(v.programaAcademico?.id) ?? '',
      fechaVinculacion: fechaONula(v.fechaVinculacion),
      fechaDesvinculacion: fechaONula(v.fechaDesvinculacion),
    }))
    .sort((a, b) => (!a.fechaDesvinculacion === !b.fechaDesvinculacion ? a.usuarioNombre.localeCompare(b.usuarioNombre) : a.fechaDesvinculacion ? 1 : -1));
};

// Unidades que coordina quien tiene la sesión, con coordinación vigente.
export const misUnidades = async () => {
  const actor = await actorActual();
  const [facultades, programas] = await Promise.all([
    leerCoordinaciones('facultad', { usuario: actor.id }),
    leerCoordinaciones('programa', { usuario: actor.id }),
  ]);
  return {
    facultades: facultades.filter((c) => !c.fechaFin).map((c) => c.facultad),
    programas: programas.filter((c) => !c.fechaFin).map((c) => c.programaAcademico),
    hoy: hoy(),
  };
};
