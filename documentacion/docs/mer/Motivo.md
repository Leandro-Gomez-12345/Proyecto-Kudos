# 🗄️ Motivo

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ EstadoRevision](EstadoRevision.md) · [Categoria ➡️](Categoria.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Motivo.md) · [📐 Clase](../clases/Motivo.md)

> Catálogo de motivos por los que un usuario reporta un Kudo o una Réplica.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `nombre` | `VARCHAR(20)` | 20 | — | — | ✅ | — | UQ | — | Nombre |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_Motivo` | Llave primaria | `id` |
| `UQ_motivo_nombre` | Unicidad | `nombre` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único del Motivo dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_Motivo. Debe ser único en el sistema. |
| `nombre` | Representa el nombre del motivo tal como lo entiende la comunidad universitaria. Solo se permiten caracteres de letras mayúsculas y minúsculas con tildes y espacios. Longitud entre 3 y 20 caracteres. Debe ser único en el sistema (UQ_motivo_nombre). |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ EstadoRevision](EstadoRevision.md) · [Categoria ➡️](Categoria.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Motivo.md) · [📐 Clase](../clases/Motivo.md)
