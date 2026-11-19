# 🗄️ EstadoContenido

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ EstadoCuenta](EstadoCuenta.md) · [EstadoRevision ➡️](EstadoRevision.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/EstadoContenido.md) · [📐 Clase](../clases/EstadoContenido.md)

> Catálogo de estados del ciclo de vida de un Kudo o de una Réplica.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `nombre` | `VARCHAR(20)` | 20 | — | — | ✅ | — | UQ | — | Nombre |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_EstadoContenido` | Llave primaria | `id` |
| `UQ_estadoContenido_nombre` | Unicidad | `nombre` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único del Estado Contenido dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_EstadoContenido. |
| `nombre` | Representa el nombre del estado contenido tal como lo entiende la comunidad universitaria. Solo se permiten caracteres de letras mayúsculas y minúsculas con tildes y espacios. Longitud entre 5 y 20 caracteres. Debe ser único en el sistema (UQ_estadoContenido_nombre). |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ EstadoCuenta](EstadoCuenta.md) · [EstadoRevision ➡️](EstadoRevision.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/EstadoContenido.md) · [📐 Clase](../clases/EstadoContenido.md)
