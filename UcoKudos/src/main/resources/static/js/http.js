// Cliente HTTP hacia el backend real. La sesión viaja en una cookie.
const MENSAJE_SIN_CONEXION = 'No fue posible conectar con el servidor. Verifique que esté encendido.';
const MENSAJE_SESION_TERMINADA = 'Su sesión terminó. Inicie sesión de nuevo.';
const RUTA_SESION = '/api/v1/autenticacion';

export class ErrorServidor extends Error {
  constructor(mensaje, { estado = 0, datos = null } = {}) {
    super(mensaje);
    this.name = 'ErrorServidor';
    this.estado = estado;
    this.datos = datos;
  }
}

function mensajeSegunEstado(estado) {
  if (estado === 401) return MENSAJE_SESION_TERMINADA;
  return estado >= 500 ? MENSAJE_SIN_CONEXION : `Error ${estado}`;
}

async function peticion(metodo, ruta, cuerpo) {
  const cabeceras = { Accept: 'application/json' };
  if (cuerpo !== undefined) cabeceras['Content-Type'] = 'application/json';

  let respuesta;
  try {
    respuesta = await fetch(ruta, {
      method: metodo,
      headers: cabeceras,
      credentials: 'same-origin',
      body: cuerpo === undefined ? undefined : JSON.stringify(cuerpo),
    });
  } catch {
    throw new ErrorServidor(MENSAJE_SIN_CONEXION);
  }

  const esJson = respuesta.headers.get('content-type')?.includes('application/json');
  const contenido = esJson ? await respuesta.json() : null;
  if (respuesta.ok && contenido?.estado !== 'ERROR') {
    return Array.isArray(contenido?.datos) ? contenido.datos : [];
  }
  if (respuesta.status === 401 && ruta !== RUTA_SESION) location.replace('/index.html');
  const mensaje = contenido?.mensajes?.find(Boolean) ?? mensajeSegunEstado(respuesta.status);
  throw new ErrorServidor(mensaje, { estado: respuesta.status, datos: contenido });
}

export const http = {
  get: (ruta) => peticion('GET', ruta),
  post: (ruta, cuerpo) => peticion('POST', ruta, cuerpo),
  patch: (ruta, cuerpo) => peticion('PATCH', ruta, cuerpo),
};
