// La sesión real vive en el servidor (cookie de Google). El id local solo lo usa el backend simulado
// de las pantallas que aún no se conectan al backend real.
import { http } from './http.js';

const CLAVE = 'kudos-uco-sesion';

export function idSesion() {
  try {
    return localStorage.getItem(CLAVE);
  } catch {
    return null;
  }
}

export function abrirSesion(id) {
  try {
    localStorage.setItem(CLAVE, id);
  } catch {
    /* sin almacenamiento */
  }
}

export async function cerrarSesion() {
  try {
    localStorage.removeItem(CLAVE);
  } catch {
    /* sin almacenamiento */
  }
  try {
    await http.post('/api/v1/autenticacion/salir');
  } catch {
    /* la sesión del servidor pudo haber vencido ya */
  }
}
