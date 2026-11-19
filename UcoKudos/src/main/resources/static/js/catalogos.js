// Catálogos semilla del modelo (M-Rol, M-EstadoCuenta, M-EstadoContenido, M-EstadoRevision, M-Motivo).
// Sus valores los define el negocio desde el inicio y solo se consultan.
const u = (n) => `00000000-0000-4000-8000-${String(n).padStart(12, '0')}`;

export const ROL = {
  ADMINISTRADOR: u(101),
  RECTOR: u(102),
  COORD_ACADEMICO: u(103),
  COORD_FACULTAD: u(104),
  COORD_PROGRAMA: u(105),
  DOCENTE: u(106),
  ESTUDIANTE: u(107),
};

export const CUENTA = {
  PENDIENTE: u(201),
  APROBADA: u(202),
  ACTIVA: u(203),
  SUSPENDIDA: u(204),
  DESACTIVADA: u(205),
  RECHAZADA: u(206),
};

export const CONTENIDO = {
  CREADO: u(301),
  VISIBLE: u(302),
  REPORTADO: u(303),
  EN_REVISION: u(304),
  VETADO: u(305),
};

export const REVISION = {
  EN_CURSO: u(401),
  VETADO: u(402),
  LIBERADO: u(403),
};

export const MOTIVO = {
  OFENSIVO: u(501),
  ACOSO: u(502),
  DISCRIMINACION: u(503),
  SPAM: u(504),
  FALSA: u(505),
  OTRO: u(506),
};

export const CATALOGOS = {
  roles: [
    { id: ROL.ADMINISTRADOR, nombre: 'Administrador' },
    { id: ROL.RECTOR, nombre: 'Rector' },
    { id: ROL.COORD_ACADEMICO, nombre: 'Coordinador Académico' },
    { id: ROL.COORD_FACULTAD, nombre: 'Coordinador de Facultad' },
    { id: ROL.COORD_PROGRAMA, nombre: 'Coordinador de Programa' },
    { id: ROL.DOCENTE, nombre: 'Docente' },
    { id: ROL.ESTUDIANTE, nombre: 'Estudiante' },
  ],
  estadosCuenta: [
    { id: CUENTA.PENDIENTE, nombre: 'Pendiente de aprobación' },
    { id: CUENTA.APROBADA, nombre: 'Aprobada' },
    { id: CUENTA.ACTIVA, nombre: 'Activa' },
    { id: CUENTA.SUSPENDIDA, nombre: 'Suspendida' },
    { id: CUENTA.DESACTIVADA, nombre: 'Desactivada' },
    { id: CUENTA.RECHAZADA, nombre: 'Rechazada' },
  ],
  estadosContenido: [
    { id: CONTENIDO.CREADO, nombre: 'Creado' },
    { id: CONTENIDO.VISIBLE, nombre: 'Visible' },
    { id: CONTENIDO.REPORTADO, nombre: 'Reportado' },
    { id: CONTENIDO.EN_REVISION, nombre: 'En revisión' },
    { id: CONTENIDO.VETADO, nombre: 'Vetado' },
  ],
  estadosRevision: [
    { id: REVISION.EN_CURSO, nombre: 'En curso' },
    { id: REVISION.VETADO, nombre: 'Contenido vetado' },
    { id: REVISION.LIBERADO, nombre: 'Contenido liberado' },
  ],
  motivos: [
    { id: MOTIVO.OFENSIVO, nombre: 'Lenguaje ofensivo' },
    { id: MOTIVO.ACOSO, nombre: 'Acoso' },
    { id: MOTIVO.DISCRIMINACION, nombre: 'Discriminación' },
    { id: MOTIVO.SPAM, nombre: 'Spam' },
    { id: MOTIVO.FALSA, nombre: 'Información falsa' },
    { id: MOTIVO.OTRO, nombre: 'Otro' },
  ],
};

export function nombreDe(lista, id) {
  return CATALOGOS[lista].find((x) => x.id === id)?.nombre ?? '';
}

// P-USU-0031: quién decide el registro (y gestiona la cuenta) según el rol de la persona.
// Estudiante y Docente ← Coordinador de Programa ← Coordinador de Facultad ← Coordinador Académico ← Rector ← Administrador ← Administrador
// Estas estructuras se rearman cuando se sustituyen los ids por los del backend (sustituirIdsDeCatalogos).
export const AUTORIDAD_DE = {};
export const RANGO = {};
export const AUTORIDADES = [];
export const GESTORES_CATEGORIAS = []; // P-USU-0002
export const MODERADORES = []; // P-USU-0003
export const AUDITORES = []; // P-AUD-0008

function reconstruirDerivados() {
  [AUTORIDAD_DE, RANGO].forEach((o) => Object.keys(o).forEach((k) => delete o[k]));
  [AUTORIDADES, GESTORES_CATEGORIAS, MODERADORES, AUDITORES].forEach((a) => { a.length = 0; });
  Object.assign(AUTORIDAD_DE, {
    [ROL.ESTUDIANTE]: ROL.COORD_PROGRAMA,
    [ROL.DOCENTE]: ROL.COORD_PROGRAMA,
    [ROL.COORD_PROGRAMA]: ROL.COORD_FACULTAD,
    [ROL.COORD_FACULTAD]: ROL.COORD_ACADEMICO,
    [ROL.COORD_ACADEMICO]: ROL.RECTOR,
    [ROL.RECTOR]: ROL.ADMINISTRADOR,
    [ROL.ADMINISTRADOR]: ROL.ADMINISTRADOR,
  });
  // Jerarquía usada para que nadie asigne un rol superior al suyo (supuesto documentado en el README).
  Object.assign(RANGO, {
    [ROL.ADMINISTRADOR]: 7,
    [ROL.RECTOR]: 6,
    [ROL.COORD_ACADEMICO]: 5,
    [ROL.COORD_FACULTAD]: 4,
    [ROL.COORD_PROGRAMA]: 3,
    [ROL.DOCENTE]: 2,
    [ROL.ESTUDIANTE]: 1,
  });
  AUTORIDADES.push(ROL.ADMINISTRADOR, ROL.RECTOR, ROL.COORD_ACADEMICO, ROL.COORD_FACULTAD, ROL.COORD_PROGRAMA);
  GESTORES_CATEGORIAS.push(ROL.ADMINISTRADOR, ROL.RECTOR, ROL.COORD_ACADEMICO);
  MODERADORES.push(ROL.RECTOR, ROL.COORD_ACADEMICO);
  AUDITORES.push(ROL.ADMINISTRADOR, ROL.RECTOR, ROL.COORD_ACADEMICO);
}
reconstruirDerivados();

const GRUPOS_CATALOGO = {
  roles: ROL, estadosCuenta: CUENTA, estadosContenido: CONTENIDO, estadosRevision: REVISION, motivos: MOTIVO,
};

const comparable = (texto) => String(texto ?? '').normalize('NFD').replace(/[̀-ͯ]/g, '').toLowerCase().trim();

// Los catálogos semilla del backend tienen sus propios ids. Al conocerlos se reemplazan los del front,
// emparejando por nombre, para que el resto de la interfaz use los ids reales sin cambios.
export function sustituirIdsDeCatalogos(catalogosServidor) {
  Object.entries(catalogosServidor).forEach(([lista, itemsServidor]) => {
    const claves = GRUPOS_CATALOGO[lista];
    if (lista === 'motivos') CATALOGOS.motivos = itemsServidor.map((m) => ({ id: m.id, nombre: m.nombre }));
    const objetivo = CATALOGOS[lista];
    objetivo.forEach((item) => {
      const real = itemsServidor.find((x) => comparable(x.nombre) === comparable(item.nombre));
      if (!real) return;
      const clave = Object.keys(claves).find((k) => claves[k] === item.id);
      if (clave) claves[clave] = real.id;
      item.id = real.id;
    });
  });
  reconstruirDerivados();
}
