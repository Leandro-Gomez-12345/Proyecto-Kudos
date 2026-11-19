# 🗄️ Auditoria

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ ProgramaAcademico](ProgramaAcademico.md) · [CoordinacionFacultad ➡️](CoordinacionFacultad.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Auditoria.md) · [📐 Clase](../clases/Auditoria.md)

> Registro histórico, de solo lectura, de cada acción relevante: quién la hizo, qué hizo, sobre qué registro y cuándo. Se puede consultar.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `usuario` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · UQ | [Usuario](Usuario.md).`id` | Usuario |
| `accion` | `VARCHAR(100)` | 100 | — | — | ✅ | — | UQ | — | Acción |
| `objetoAfectado` | `VARCHAR(30)` | 30 | — | — | ✅ | — | — | — | Objeto afectado |
| `registroAfectado` | `UUID` | 36 | — | — | ✅ | — | — | — | Registro afectado |
| `detalle` | `VARCHAR(200)` | 200 | — | — | ✅ | — | — | — | Detalle |
| `fechaHora` | `DATETIME` | — | — | — | ✅ | — | UQ | — | Fecha hora |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_Auditoria` | Llave primaria | `id` |
| `UQ_auditoria_usuario_accion_fechaHora` | Unicidad | `usuario`, `accion`, `fechaHora` |
| — | Llave foránea | `usuario` → [Usuario](Usuario.md).`id` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único del registro de auditoría dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_Auditoria. Debe ser único en el sistema. |
| `usuario` | Referencia a la tabla Usuario; por medio de ella se conoce la persona que ejecutó la acción auditada. Lo asigna el sistema con el usuario autenticado que ejecuta la acción. Única en combinación con accion, fechaHora (UQ_auditoria_usuario_accion_fechaHora). |
| `accion` | Representa la acción del negocio que se ejecutó, en lenguaje de negocio. Verbo en infinitivo + objeto. Lo asigna el sistema. Longitud entre 5 y 100 caracteres. Única en combinación con usuario, fechaHora (UQ_auditoria_usuario_accion_fechaHora). |
| `objetoAfectado` | Representa el tipo de objeto de dominio sobre el que se ejecutó la acción. Debe ser el nombre de un objeto de dominio del modelo. Longitud entre 3 y 30 caracteres. |
| `registroAfectado` | Representa el identificador del registro concreto que fue afectado por la acción. Identificador de un registro existente del objeto indicado en objetoAfectado. No es una referencia fija porque puede apuntar a cualquier objeto. |
| `detalle` | Representa la descripción de lo que cambió, para poder reconstruir la decisión. Lo genera el sistema y siempre se registra: describe lo que cambió (valor anterior → valor nuevo) y, cuando aplica, la justificación (RN-250). Nunca incluye datos sensibles (correo o celular completos). Longitud entre 5 y 200 caracteres. |
| `fechaHora` | Representa la fecha y hora exactas en que se ejecutó la acción. La asigna el sistema y no cambia. Un registro de auditoría nunca se modifica ni se elimina. Única en combinación con usuario, accion (UQ_auditoria_usuario_accion_fechaHora). |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ ProgramaAcademico](ProgramaAcademico.md) · [CoordinacionFacultad ➡️](CoordinacionFacultad.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Auditoria.md) · [📐 Clase](../clases/Auditoria.md)
