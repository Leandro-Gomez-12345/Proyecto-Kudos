# 🗄️ Kudo

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ VinculacionAcademica](VinculacionAcademica.md) · [Replica ➡️](Replica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Kudo.md) · [📐 Clase](../clases/Kudo.md)

> Reconocimiento formal que una persona otorga a otra de la comunidad universitaria, clasificado en una categoría y visible para la comunidad autenticada.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `autor` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · UQ | [Usuario](Usuario.md).`id` | Autor |
| `destinatario` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · CHK · IX | [Usuario](Usuario.md).`id` | Destinatario |
| `categoria` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · IX | [Categoria](Categoria.md).`id` | Categoría |
| `estadoContenido` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · IX | [EstadoContenido](EstadoContenido.md).`id` | Estado contenido |
| `nombreCategoria` | `VARCHAR(50)` | 50 | — | — | ✅ | — | — | — | Nombre categoría |
| `contenido` | `TEXT` | Sin límite | — | — | ✅ | — | — | — | Contenido |
| `fechaCreacion` | `DATETIME` | — | — | — | ✅ | — | UQ | — | Fecha creación |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_Kudo` | Llave primaria | `id` |
| `UQ_kudo_autor_fechaCreacion` | Unicidad | `autor`, `fechaCreacion` |
| `CHK_kudo_destinatario` | Chequeo | `destinatario` |
| `IX_kudo_destinatario` | Índice (no único) | `destinatario` |
| `IX_kudo_categoria` | Índice (no único) | `categoria` |
| `IX_kudo_estadoContenido` | Índice (no único) | `estadoContenido` |
| — | Llave foránea | `autor` → [Usuario](Usuario.md).`id` |
| — | Llave foránea | `destinatario` → [Usuario](Usuario.md).`id` |
| — | Llave foránea | `categoria` → [Categoria](Categoria.md).`id` |
| — | Llave foránea | `estadoContenido` → [EstadoContenido](EstadoContenido.md).`id` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único del Kudo dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_Kudo. Debe ser único en el sistema. |
| `autor` | Referencia a la tabla Usuario; por medio de ella se conoce la persona que otorga el reconocimiento. Lo asigna el sistema con el usuario autenticado; su cuenta debe estar Activa. Única en combinación con fechaCreacion (UQ_kudo_autor_fechaCreacion). |
| `destinatario` | Referencia a la tabla Usuario; por medio de ella se conoce la persona que recibe el reconocimiento. Debe ser un usuario con cuenta Activa. Restricción de chequeo: destinatario &lt;&gt; autor (P-KUD-0015: el Kudo se otorga a otra persona). |
| `categoria` | Referencia a la tabla Categoria; por medio de ella se conoce la categoría institucional en la que se clasifica el reconocimiento. |
| `estadoContenido` | Referencia a la tabla EstadoContenido; por medio de ella se conoce en qué estado del ciclo de vida se encuentra el Kudo. Inicia en Creado y pasa a Visible al publicarse (RN-201). Un Kudo Vetado no admite interacción normal (RN-205). |
| `nombreCategoria` | Representa el nombre que tenía la categoría en el momento de crear el Kudo; no cambia aunque la categoría se renombre. Copia el nombre del atributo categoria. Longitud entre 3 y 50 caracteres. Es un valor congelado (P-KUD-0016): se guarda físicamente para conservar la historia. |
| `contenido` | Representa el texto completo del reconocimiento. Se permite todos los caracteres. No tiene límite de longitud y nunca se trunca. |
| `fechaCreacion` | Representa la fecha y hora en que se creó el Kudo. La asigna el sistema al crear el Kudo. Única en combinación con autor (UQ_kudo_autor_fechaCreacion). |

## Campos calculados (vistas)

Estos datos **no se guardan** en la tabla: se calculan en una vista a partir de otras tablas.

| Campo | Tipo | Tablas de origen | Cálculo | Descripción |
| --- | --- | --- | --- | --- |
| `promedioEstrellas` | `DECIMAL(3,2)` | Kudo, CalificacionKudo | AVG(CalificacionKudo.estrellas) de las calificaciones cuyo kudo = Kudo.id; 0 si no tiene calificaciones | Valoración promedio que la comunidad le da al Kudo. Participa en los indicadores del equipo (RN-185) y en la consulta de valoraciones (CU-009). |
| `totalCalificaciones` | `INT` | Kudo, CalificacionKudo | COUNT(CalificacionKudo.id) cuyo kudo = Kudo.id | Cantidad de personas que han calificado el Kudo (CU-009). |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ VinculacionAcademica](VinculacionAcademica.md) · [Replica ➡️](Replica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Kudo.md) · [📐 Clase](../clases/Kudo.md)
