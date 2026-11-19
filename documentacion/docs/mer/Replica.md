# 🗄️ Replica

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ Kudo](Kudo.md) · [CalificacionKudo ➡️](CalificacionKudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Replica.md) · [📐 Clase](../clases/Replica.md)

> Intervención dentro de la conversación de un Kudo. Puede responder directamente al Kudo o a otra réplica, sin límite de profundidad y sin ciclos.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `kudo` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · IX | [Kudo](Kudo.md).`id` | Kudo |
| `replicaPadre` | `UUID` | 36 | — | — | ❌ | — | 🔗 FK · CHK · IX | [Replica](Replica.md).`id` | Réplica padre |
| `autor` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · UQ | [Usuario](Usuario.md).`id` | Autor |
| `estadoContenido` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · IX | [EstadoContenido](EstadoContenido.md).`id` | Estado contenido |
| `contenido` | `TEXT` | Sin límite | — | — | ✅ | — | — | — | Contenido |
| `fechaCreacion` | `DATETIME` | — | — | — | ✅ | — | UQ | — | Fecha creación |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_Replica` | Llave primaria | `id` |
| `UQ_replica_autor_fechaCreacion` | Unicidad | `autor`, `fechaCreacion` |
| `CHK_replica_replicaPadre` | Chequeo | `replicaPadre` |
| `IX_replica_kudo` | Índice (no único) | `kudo` |
| `IX_replica_replicaPadre` | Índice (no único) | `replicaPadre` |
| `IX_replica_estadoContenido` | Índice (no único) | `estadoContenido` |
| — | Llave foránea | `kudo` → [Kudo](Kudo.md).`id` |
| — | Llave foránea | `replicaPadre` → [Replica](Replica.md).`id` |
| — | Llave foránea | `autor` → [Usuario](Usuario.md).`id` |
| — | Llave foránea | `estadoContenido` → [EstadoContenido](EstadoContenido.md).`id` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único de la réplica dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_Replica. Debe ser único en el sistema. |
| `kudo` | Referencia a la tabla Kudo; por medio de ella se conoce el Kudo a cuya conversación pertenece la réplica. El Kudo no puede estar Vetado. |
| `replicaPadre` | Referencia a la tabla Replica; por medio de ella se conoce la réplica a la que se responde; vacía cuando se responde directamente al Kudo. Vacía significa que responde al Kudo. Si tiene valor, la réplica padre debe pertenecer al mismo Kudo y se esta replicando es la replica padre. Restricción de chequeo: replicaPadre IS NULL OR replicaPadre &lt;&gt; id (P-REP-0016: sin autorreferencias). |
| `autor` | Referencia a la tabla Usuario; por medio de ella se conoce la persona que escribe la réplica. Lo asigna el sistema con el usuario autenticado; su cuenta debe estar Activa. Única en combinación con fechaCreacion (UQ_replica_autor_fechaCreacion). |
| `estadoContenido` | Referencia a la tabla EstadoContenido; por medio de ella se conoce en qué estado del ciclo de vida se encuentra la réplica. Inicia en Creado y pasa a Visible al publicarse. |
| `contenido` | Representa el texto completo de la réplica. Cualquier carácter, incluidos emojis y saltos de línea. No tiene límite de longitud y nunca se trunca. |
| `fechaCreacion` | Representa la fecha y hora en que se publicó la réplica. La asigna el sistema cuando se crea la replica. Única en combinación con autor (UQ_replica_autor_fechaCreacion). |

## Campos calculados (vistas)

Estos datos **no se guardan** en la tabla: se calculan en una vista a partir de otras tablas.

| Campo | Tipo | Tablas de origen | Cálculo | Descripción |
| --- | --- | --- | --- | --- |
| `promedioEstrellas` | `DECIMAL(3,2)` | Replica, CalificacionReplica | AVG(CalificacionReplica.estrellas) de las calificaciones cuya replica = Replica.id; 0 si no tiene calificaciones | Valoración promedio que la comunidad le da a la réplica (RN-185, CU-009). |
| `totalCalificaciones` | `INT` | Replica, CalificacionReplica | COUNT(CalificacionReplica.id) cuya replica = Replica.id | Cantidad de personas que han calificado la réplica (CU-009). |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ Kudo](Kudo.md) · [CalificacionKudo ➡️](CalificacionKudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Replica.md) · [📐 Clase](../clases/Replica.md)
