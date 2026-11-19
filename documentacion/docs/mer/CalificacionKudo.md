# 🗄️ CalificacionKudo

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ Replica](Replica.md) · [RevisionKudo ➡️](RevisionKudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/CalificacionKudo.md) · [📐 Clase](../clases/CalificacionKudo.md)

> Valoración de 1 a 5 estrellas que una persona da a el Kudo. Cada persona tiene una sola calificación por Kudo y puede modificarla.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `kudo` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · UQ | [Kudo](Kudo.md).`id` | Kudo |
| `usuario` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · UQ · IX | [Usuario](Usuario.md).`id` | Usuario |
| `estrellas` | `INT` | — | 1 | — | ✅ | — | CHK | — | Estrellas |
| `fechaCalificacion` | `DATETIME` | — | — | — | ✅ | — | — | — | Fecha calificación |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_CalificacionKudo` | Llave primaria | `id` |
| `UQ_calificacionKudo_kudo_usuario` | Unicidad | `kudo`, `usuario` |
| `CHK_calificacionKudo_estrellas` | Chequeo | `estrellas` |
| `IX_calificacionKudo_usuario` | Índice (no único) | `usuario` |
| — | Llave foránea | `kudo` → [Kudo](Kudo.md).`id` |
| — | Llave foránea | `usuario` → [Usuario](Usuario.md).`id` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único de la calificación dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_CalificacionKudo. Debe ser único en el sistema. |
| `kudo` | Referencia a la tabla Kudo; por medio de ella se conoce el Kudo que se califica. El Kudo no puede estar Vetado. Única en combinación con usuario (UQ_calificacionKudo_kudo_usuario). |
| `usuario` | Referencia a la tabla Usuario; por medio de ella se conoce la persona que califica. Lo asigna el sistema con el usuario autenticado; su cuenta debe estar Activa. Única en combinación con kudo (UQ_calificacionKudo_kudo_usuario). |
| `estrellas` | Representa la cantidad de estrellas que la persona asigna. Solo números enteros de 1 a 5. En pantalla se selecciona con estrellas; nunca se escribe. Restricción de chequeo: estrellas BETWEEN 1 AND 5 (RN-177). |
| `fechaCalificacion` | Representa la fecha y hora en que se registró o se modificó por última vez la calificación. La asigna el sistema al calificar y se actualiza si la persona modifica su calificación. El valor anterior queda en Auditoría. |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ Replica](Replica.md) · [RevisionKudo ➡️](RevisionKudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/CalificacionKudo.md) · [📐 Clase](../clases/CalificacionKudo.md)
