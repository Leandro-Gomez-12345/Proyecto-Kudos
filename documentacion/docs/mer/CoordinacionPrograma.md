# 🗄️ CoordinacionPrograma

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ CoordinacionFacultad](CoordinacionFacultad.md) · [VinculacionAcademica ➡️](VinculacionAcademica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/CoordinacionPrograma.md) · [📐 Clase](../clases/CoordinacionPrograma.md)

> Historial de quién coordina cada Programa Académico. Solo una coordinación vigente por programa.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `usuario` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · UQ | [Usuario](Usuario.md).`id` | Usuario |
| `programaAcademico` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · UQ · IX | [ProgramaAcademico](ProgramaAcademico.md).`id` | Programa académico |
| `fechaInicio` | `DATE` | — | — | — | ✅ | — | UQ · CHK | — | Fecha inicio |
| `fechaFin` | `DATE` | — | — | — | ❌ | — | CHK | — | Fecha fin |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_CoordinacionPrograma` | Llave primaria | `id` |
| `UQ_coordinacionPrograma_usuario_programaAcademico_fechaInicio` | Unicidad | `usuario`, `programaAcademico`, `fechaInicio` |
| `CHK_coordinacionPrograma_fechaInicio` | Chequeo | `fechaInicio` |
| `CHK_coordinacionPrograma_fechaFin` | Chequeo | `fechaFin` |
| `IX_coordinacionPrograma_programaAcademico` | Índice (no único) | `programaAcademico` |
| — | Llave foránea | `usuario` → [Usuario](Usuario.md).`id` |
| — | Llave foránea | `programaAcademico` → [ProgramaAcademico](ProgramaAcademico.md).`id` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único de la coordinación programa dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_CoordinacionPrograma. Debe ser único en el sistema. |
| `usuario` | Referencia a la tabla Usuario; por medio de ella se conoce la persona que ejerce o ejerció la coordinación. La persona debe tener el rol Coordinador de Programa mientras la coordinación esté vigente. Única en combinación con programaAcademico, fechaInicio (UQ_coordinacionPrograma_usuario_programaAcademico_fechaInicio). |
| `programaAcademico` | Referencia a la tabla ProgramaAcademico; por medio de ella se conoce el programa académico que coordina la persona. Solo puede existir una coordinación vigente por programa académico. Única en combinación con usuario, fechaInicio (UQ_coordinacionPrograma_usuario_programaAcademico_fechaInicio). |
| `fechaInicio` | Representa la fecha en que la persona empezó a coordinar. No puede ser posterior a la fecha actual y no puede ser una fecha anterior a 01/01/1982 (año en que se fundó la UCO). Única en combinación con usuario, programaAcademico (UQ_coordinacionPrograma_usuario_programaAcademico_fechaInicio). Restricción de chequeo: fechaInicio &gt;= '1982-01-01' (fundación de la UCO). |
| `fechaFin` | Representa la fecha en que la persona dejó de coordinar; vacía mientras la coordinación está vigente. Vacía significa que la coordinación es vigente actualmente. Debe ser igual o posterior a la fecha de inicio. Restricción de chequeo: fechaFin IS NULL OR fechaFin &gt;= fechaInicio. |

## Campos calculados (vistas)

Estos datos **no se guardan** en la tabla: se calculan en una vista a partir de otras tablas.

| Campo | Tipo | Tablas de origen | Cálculo | Descripción |
| --- | --- | --- | --- | --- |
| `vigente` | `BOOLEAN` | CoordinacionPrograma | fechaFin IS NULL | Indica si la persona coordina actualmente el programa académico. Se deduce de que la coordinación no tenga fecha de fin (RF-074). |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ CoordinacionFacultad](CoordinacionFacultad.md) · [VinculacionAcademica ➡️](VinculacionAcademica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/CoordinacionPrograma.md) · [📐 Clase](../clases/CoordinacionPrograma.md)
