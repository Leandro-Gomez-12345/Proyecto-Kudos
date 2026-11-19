# 🗄️ Usuario

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ Facultad](Facultad.md) · [ProgramaAcademico ➡️](ProgramaAcademico.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Usuario.md) · [📐 Clase](../clases/Usuario.md)

> Persona de la comunidad universitaria registrada en la plataforma con un rol institucional. Su cuenta solo está activa si fue aprobada y validó correo y celular. La aprobación o el rechazo de su registro lo decide la autoridad que corresponde a su rol; queda en el estado de la cuenta y en la Auditoría.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `rol` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · IX | [Rol](Rol.md).`id` | Rol |
| `estadoCuenta` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · IX | [EstadoCuenta](EstadoCuenta.md).`id` | Estado cuenta |
| `nombre` | `VARCHAR(60)` | 60 | — | — | ✅ | — | — | — | Nombre |
| `apellido` | `VARCHAR(60)` | 60 | — | — | ✅ | — | — | — | Apellido |
| `correoInstitucional` | `VARCHAR(100)` | 100 | — | — | ✅ | — | UQ | — | Correo institucional |
| `celular` | `VARCHAR(10)` | 10 | — | — | ✅ | — | UQ | — | Celular |
| `correoVerificado` | `BOOLEAN` | — | — | — | ✅ | — | — | — | Correo verificado |
| `celularVerificado` | `BOOLEAN` | — | — | — | ✅ | — | — | — | Celular verificado |
| `fechaRegistro` | `DATETIME` | — | — | — | ✅ | — | — | — | Fecha registro |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_Usuario` | Llave primaria | `id` |
| `UQ_usuario_correoInstitucional` | Unicidad | `correoInstitucional` |
| `UQ_usuario_celular` | Unicidad | `celular` |
| `IX_usuario_rol` | Índice (no único) | `rol` |
| `IX_usuario_estadoCuenta` | Índice (no único) | `estadoCuenta` |
| — | Llave foránea | `rol` → [Rol](Rol.md).`id` |
| — | Llave foránea | `estadoCuenta` → [EstadoCuenta](EstadoCuenta.md).`id` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único del usuario dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_Usuario. Debe ser único en el sistema. |
| `rol` | Referencia a la tabla Rol; por medio de ella se conoce el rol institucional que ejerce la persona en la plataforma. |
| `estadoCuenta` | Referencia a la tabla EstadoCuenta; por medio de ella se conoce en qué estado se encuentra la cuenta de la persona. |
| `nombre` | Representa el nombre o los nombres de la persona. Solo se permiten caracteres de letras mayúsculas y minúsculas con tildes y espacios. Longitud entre 2 y 60 caracteres. |
| `apellido` | Representa el apellido o los apellidos de la persona. Solo se permiten caracteres de letras mayúsculas y minúsculas con tildes y espacios. Longitud entre 2 y 60 caracteres. |
| `correoInstitucional` | Representa el correo institucional con el que la persona se registra y se identifica. Debe terminar en @uco.edu.co. Antes de la @ solo letras minúsculas, números, punto, guion y guion bajo. Si cambia, debe volver a validarse. Longitud entre 12 y 100 caracteres. Debe ser único en el sistema (UQ_usuario_correoInstitucional). |
| `celular` | Representa el número de celular de la persona, usado para validar su identidad. Solo dígitos; debe iniciar en 3 (celular colombiano). Si cambia, debe volver a validarse. Longitud exacta de 10 caracteres. Debe ser único en el sistema (UQ_usuario_celular). |
| `correoVerificado` | Representa si la persona ya confirmó que el correo institucional le pertenece. Empieza valiendo No y si se verifica su valor cambia a Sí. |
| `celularVerificado` | Representa si la persona ya confirmó que el número celular le pertenece. Empieza valiendo No y si se verifica su valor cambia a Sí. |
| `fechaRegistro` | Representa la fecha y hora en que la persona se registró en la plataforma. La asigna el sistema al registrar la cuenta y no cambia. |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ Facultad](Facultad.md) · [ProgramaAcademico ➡️](ProgramaAcademico.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Usuario.md) · [📐 Clase](../clases/Usuario.md)
