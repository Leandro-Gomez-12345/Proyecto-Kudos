# 🗄️ CalificacionReplica

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ RevisionKudo](RevisionKudo.md) · [RevisionReplica ➡️](RevisionReplica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/CalificacionReplica.md) · [📐 Clase](../clases/CalificacionReplica.md)

> Valoración de 1 a 5 estrellas que una persona da a la réplica. Cada persona tiene una sola calificación por réplica y puede modificarla.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `replica` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · UQ | [Replica](Replica.md).`id` | Réplica |
| `usuario` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · UQ · IX | [Usuario](Usuario.md).`id` | Usuario |
| `estrellas` | `INT` | — | 1 | — | ✅ | — | CHK | — | Estrellas |
| `fechaCalificacion` | `DATETIME` | — | — | — | ✅ | — | — | — | Fecha calificación |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_CalificacionReplica` | Llave primaria | `id` |
| `UQ_calificacionReplica_replica_usuario` | Unicidad | `replica`, `usuario` |
| `CHK_calificacionReplica_estrellas` | Chequeo | `estrellas` |
| `IX_calificacionReplica_usuario` | Índice (no único) | `usuario` |
| — | Llave foránea | `replica` → [Replica](Replica.md).`id` |
| — | Llave foránea | `usuario` → [Usuario](Usuario.md).`id` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único de la calificación dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_CalificacionReplica. Debe ser único en el sistema. |
| `replica` | Referencia a la tabla Replica; por medio de ella se conoce la réplica que se califica. La réplica no puede estar Vetado. Única en combinación con usuario (UQ_calificacionReplica_replica_usuario). |
| `usuario` | Referencia a la tabla Usuario; por medio de ella se conoce la persona que califica. Lo asigna el sistema con el usuario autenticado; su cuenta debe estar Activa. Única en combinación con replica (UQ_calificacionReplica_replica_usuario). |
| `estrellas` | Representa la cantidad de estrellas que la persona asigna. Solo números enteros de 1 a 5 . En pantalla se selecciona con estrellas; nunca se escribe. Restricción de chequeo: estrellas BETWEEN 1 AND 5 (RN-177). |
| `fechaCalificacion` | Representa la fecha y hora en que se registró o se modificó por última vez la calificación. La asigna el sistema al calificar y se actualiza si la persona modifica su calificación. El valor anterior queda en Auditoría. |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ RevisionKudo](RevisionKudo.md) · [RevisionReplica ➡️](RevisionReplica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/CalificacionReplica.md) · [📐 Clase](../clases/CalificacionReplica.md)
