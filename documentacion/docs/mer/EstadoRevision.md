# 🗄️ EstadoRevision

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ EstadoContenido](EstadoContenido.md) · [Motivo ➡️](Motivo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/EstadoRevision.md) · [📐 Clase](../clases/EstadoRevision.md)

> Catálogo de estados de una revisión de moderación: en curso o cerrada con el contenido vetado o liberado.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `nombre` | `VARCHAR(20)` | 20 | — | — | ✅ | — | UQ | — | Nombre |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_EstadoRevision` | Llave primaria | `id` |
| `UQ_estadoRevision_nombre` | Unicidad | `nombre` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único del Estado Revisión dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_EstadoRevision. Debe ser único en el sistema. |
| `nombre` | Representa el nombre del estado revisión tal como lo entiende la comunidad universitaria. Solo se permiten caracteres de letras mayúsculas y minúsculas con tildes y espacios. Longitud entre 5 y 20 caracteres. Debe ser único en el sistema (UQ_estadoRevision_nombre). |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ EstadoContenido](EstadoContenido.md) · [Motivo ➡️](Motivo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/EstadoRevision.md) · [📐 Clase](../clases/EstadoRevision.md)
