// Única puerta de la interfaz hacia el backend.
// Cada función habla con el backend real (/api/v1/...) y entrega los datos ya listos para las pantallas.
export * from './api/usuarios.js';
export * from './api/academia.js';
export * from './api/contenido.js';
export * from './api/moderacion.js';
