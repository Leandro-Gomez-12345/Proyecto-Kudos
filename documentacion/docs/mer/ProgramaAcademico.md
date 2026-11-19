# 🗄️ ProgramaAcademico

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ Usuario](Usuario.md) · [Auditoria ➡️](Auditoria.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/ProgramaAcademico.md) · [📐 Clase](../clases/ProgramaAcademico.md)

> Unidad académica que pertenece exactamente a una Facultad y en la que se vinculan estudiantes y docentes.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `facultad` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · IX | [Facultad](Facultad.md).`id` | Facultad |
| `nombre` | `VARCHAR(100)` | 100 | — | — | ✅ | — | UQ | — | Nombre |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_ProgramaAcademico` | Llave primaria | `id` |
| `UQ_programaAcademico_nombre` | Unicidad | `nombre` |
| `IX_programaAcademico_facultad` | Índice (no único) | `facultad` |
| — | Llave foránea | `facultad` → [Facultad](Facultad.md).`id` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único del programa académico dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_ProgramaAcademico. Debe ser único en el sistema. |
| `facultad` | Referencia a la tabla Facultad; por medio de ella se conoce la facultad a la que pertenece el programa académico. |
| `nombre` | Representa el nombre oficial del programa académico. Solo se permiten caracteres de letras mayúsculas y minúsculas con tildes y espacios. Longitud entre 5 y 100 caracteres. Debe ser único en el sistema (UQ_programaAcademico_nombre). |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ Usuario](Usuario.md) · [Auditoria ➡️](Auditoria.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/ProgramaAcademico.md) · [📐 Clase](../clases/ProgramaAcademico.md)
