# 🗄️ ReporteReplica

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ ReporteKudo](ReporteKudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/ReporteReplica.md) · [📐 Clase](../clases/ReporteReplica.md)

> Solicitud de revisión que hace cualquier usuario autenticado sobre una réplica, con un motivo. Se resuelve cuando la revisión del contenido se cierra. Su estado (Pendiente, En revisión, Aceptado o Rechazado) no se guarda: se deduce de la revisión que lo resuelve.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `replica` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · IX | [Replica](Replica.md).`id` | Réplica |
| `usuario` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · UQ | [Usuario](Usuario.md).`id` | Usuario |
| `motivo` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · IX | [Motivo](Motivo.md).`id` | Motivo |
| `revisionReplica` | `UUID` | 36 | — | — | ❌ | — | 🔗 FK · IX | [RevisionReplica](RevisionReplica.md).`id` | Revisión réplica |
| `descripcion` | `VARCHAR(500)` | 500 | — | — | ❌ | — | — | — | Descripción |
| `fechaReporte` | `DATETIME` | — | — | — | ✅ | — | UQ | — | Fecha reporte |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_ReporteReplica` | Llave primaria | `id` |
| `UQ_reporteReplica_usuario_fechaReporte` | Unicidad | `usuario`, `fechaReporte` |
| `IX_reporteReplica_replica` | Índice (no único) | `replica` |
| `IX_reporteReplica_motivo` | Índice (no único) | `motivo` |
| `IX_reporteReplica_revisionReplica` | Índice (no único) | `revisionReplica` |
| — | Llave foránea | `replica` → [Replica](Replica.md).`id` |
| — | Llave foránea | `usuario` → [Usuario](Usuario.md).`id` |
| — | Llave foránea | `motivo` → [Motivo](Motivo.md).`id` |
| — | Llave foránea | `revisionReplica` → [RevisionReplica](RevisionReplica.md).`id` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único del reporte dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_ReporteReplica. Debe ser único en el sistema. |
| `replica` | Referencia a la tabla Replica; por medio de ella se conoce una réplica que se reporta. El contenido debe estar Visible o Reportado. |
| `usuario` | Referencia a la tabla Usuario; por medio de ella se conoce la persona que reporta el contenido. Lo asigna el sistema con el usuario autenticado. Única en combinación con fechaReporte (UQ_reporteReplica_usuario_fechaReporte). |
| `motivo` | Referencia a la tabla Motivo; por medio de ella se conoce la razón por la que se reporta el contenido. |
| `revisionReplica` | Referencia a la tabla RevisionReplica; por medio de ella se conoce la revisión que resuelve el reporte; vacía mientras el reporte está pendiente. De ella se deduce el estado del reporte: sin revisión = Pendiente, revisión En curso = En revisión, cerrada con veto = Aceptado, cerrada con liberación = Rechazado. Lo asigna el sistema al iniciar la revisión. Debe ser una revisión del mismo contenido reportado. Vacía significa que el reporte está Pendiente. El estado del reporte no se guarda: se deduce de esta revisión (En curso = En revisión; Contenido vetado = Aceptado; Contenido liberado = Rechazado), RN-210 a RN-212. |
| `descripcion` | Representa la explicación adicional del reporte; obligatoria cuando el motivo es Otro. Obligatoria si el motivo es Otro. Letras, números, tildes, espacios y signos de puntuación. Longitud entre 0 y 500 caracteres. Es opcional. |
| `fechaReporte` | Representa la fecha y hora en que se hizo el reporte. La asigna el sistema y no cambia. Única en combinación con usuario (UQ_reporteReplica_usuario_fechaReporte). |

## Campos calculados (vistas)

Estos datos **no se guardan** en la tabla: se calculan en una vista a partir de otras tablas.

| Campo | Tipo | Tablas de origen | Cálculo | Descripción |
| --- | --- | --- | --- | --- |
| `estadoReporte` | `VARCHAR(12)` | ReporteReplica, RevisionReplica, EstadoRevision | CASE WHEN revisionReplica IS NULL THEN 'Pendiente' WHEN EstadoRevision.nombre = 'En curso' THEN 'En revisión' WHEN EstadoRevision.nombre = 'Contenido vetado' THEN 'Aceptado' ELSE 'Rechazado' END | Estado del reporte de la réplica deducido de la revisión que lo resuelve, sin guardarlo (RN-210 a RN-212): sin revisión = Pendiente, revisión En curso = En revisión, cerrada con veto = Aceptado, cerrada con liberación = Rechazado. |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ ReporteKudo](ReporteKudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/ReporteReplica.md) · [📐 Clase](../clases/ReporteReplica.md)
