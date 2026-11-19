# 🗄️ RevisionKudo

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ CalificacionKudo](CalificacionKudo.md) · [CalificacionReplica ➡️](CalificacionReplica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/RevisionKudo.md) · [📐 Clase](../clases/RevisionKudo.md)

> Revisión de moderación sobre un Kudo, hecha por el Rector o el Coordinador Académico. Resuelve a la vez todos sus reportes y termina vetando o liberando el contenido; un veto directo es una revisión sin reportes.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `kudo` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · UQ | [Kudo](Kudo.md).`id` | Kudo |
| `moderador` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · IX | [Usuario](Usuario.md).`id` | Moderador |
| `estadoRevision` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · IX | [EstadoRevision](EstadoRevision.md).`id` | Estado revisión |
| `justificacion` | `VARCHAR(1000)` | 1000 | — | — | ❌ | — | — | — | Justificación |
| `fechaInicio` | `DATETIME` | — | — | — | ✅ | — | UQ | — | Fecha inicio |
| `fechaCierre` | `DATETIME` | — | — | — | ❌ | — | CHK | — | Fecha cierre |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_RevisionKudo` | Llave primaria | `id` |
| `UQ_revisionKudo_kudo_fechaInicio` | Unicidad | `kudo`, `fechaInicio` |
| `CHK_revisionKudo_fechaCierre` | Chequeo | `fechaCierre` |
| `IX_revisionKudo_moderador` | Índice (no único) | `moderador` |
| `IX_revisionKudo_estadoRevision` | Índice (no único) | `estadoRevision` |
| — | Llave foránea | `kudo` → [Kudo](Kudo.md).`id` |
| — | Llave foránea | `moderador` → [Usuario](Usuario.md).`id` |
| — | Llave foránea | `estadoRevision` → [EstadoRevision](EstadoRevision.md).`id` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único de la revisión dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_RevisionKudo. Debe ser único en el sistema. |
| `kudo` | Referencia a la tabla Kudo; por medio de ella se conoce un Kudo que se revisa. Solo puede tener una revisión En curso a la vez. Única en combinación con fechaInicio (UQ_revisionKudo_kudo_fechaInicio). |
| `moderador` | Referencia a la tabla Usuario; por medio de ella se conoce la autoridad que realiza la revisión. Lo asigna el sistema con el usuario autenticado; solo Rector o Coordinador Académico. |
| `estadoRevision` | Referencia a la tabla EstadoRevision; por medio de ella se conoce si la revisión sigue en curso o cómo se cerró. Inicia En curso y cambia una sola vez a Contenido vetado o Contenido liberado. |
| `justificacion` | Representa la razón de la decisión, necesaria para reconstruir la moderación. Obligatoria al cerrar la revisión. Solo se permite letras mayúsculas y minúsculas con y sin tilde, números, espacios y signos de puntuación. Longitud entre 10 y 1000 caracteres. Es opcional. |
| `fechaInicio` | Representa la fecha y hora en que se abrió la revisión. La asigna el sistema al iniciar la revisión y no cambia. Única en combinación con kudo (UQ_revisionKudo_kudo_fechaInicio). |
| `fechaCierre` | Representa la fecha y hora en que se tomó la decisión; vacía mientras la revisión está en curso. La asigna el sistema al cerrar la revisión. Posterior a la fecha de inicio. Restricción de chequeo: fechaCierre IS NULL OR fechaCierre &gt;= fechaInicio (en un veto directo son iguales). |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ CalificacionKudo](CalificacionKudo.md) · [CalificacionReplica ➡️](CalificacionReplica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/RevisionKudo.md) · [📐 Clase](../clases/RevisionKudo.md)
