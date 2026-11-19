# 🗄️ VinculacionAcademica

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ CoordinacionPrograma](CoordinacionPrograma.md) · [Kudo ➡️](Kudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/VinculacionAcademica.md) · [📐 Clase](../clases/VinculacionAcademica.md)

> Vínculo de un estudiante o docente con un Programa Académico. Una persona puede estar en varios programas y la desvinculación conserva el historial.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `usuario` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · UQ | [Usuario](Usuario.md).`id` | Usuario |
| `programaAcademico` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · UQ · IX | [ProgramaAcademico](ProgramaAcademico.md).`id` | Programa académico |
| `fechaVinculacion` | `DATE` | — | — | — | ✅ | — | UQ · CHK | — | Fecha vinculación |
| `fechaDesvinculacion` | `DATE` | — | — | — | ❌ | — | CHK | — | Fecha desvinculación |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_VinculacionAcademica` | Llave primaria | `id` |
| `UQ_vinculacionAcademica_usuario_programaAcademico_fechaVinculacion` | Unicidad | `usuario`, `programaAcademico`, `fechaVinculacion` |
| `CHK_vinculacionAcademica_fechaVinculacion` | Chequeo | `fechaVinculacion` |
| `CHK_vinculacionAcademica_fechaDesvinculacion` | Chequeo | `fechaDesvinculacion` |
| `IX_vinculacionAcademica_programaAcademico` | Índice (no único) | `programaAcademico` |
| — | Llave foránea | `usuario` → [Usuario](Usuario.md).`id` |
| — | Llave foránea | `programaAcademico` → [ProgramaAcademico](ProgramaAcademico.md).`id` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único de la vinculación académica dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_VinculacionAcademica. Debe ser único en el sistema. |
| `usuario` | Referencia a la tabla Usuario; por medio de ella se conoce el estudiante o docente que se vincula al programa. La persona debe tener rol Estudiante o Docente. Única en combinación con programaAcademico, fechaVinculacion (UQ_vinculacionAcademica_usuario_programaAcademico_fechaVinculacion). |
| `programaAcademico` | Referencia a la tabla ProgramaAcademico; por medio de ella se conoce el programa académico en el que estudia o enseña la persona. Única en combinación con usuario, fechaVinculacion (UQ_vinculacionAcademica_usuario_programaAcademico_fechaVinculacion). |
| `fechaVinculacion` | Representa la fecha desde la que la persona estudia o enseña en el programa. No puede ser posterior a la fecha actual y no puede ser una fecha anterior a 01/01/1982 (año en que se fundó la UCO). Única en combinación con usuario, programaAcademico (UQ_vinculacionAcademica_usuario_programaAcademico_fechaVinculacion). Restricción de chequeo: fechaVinculacion &gt;= '1982-01-01' (fundación de la UCO). |
| `fechaDesvinculacion` | Representa la fecha en que la persona dejó el programa; vacía mientras el vínculo está vigente. Vacía significa que el vínculo es vigente todavía. Debe ser igual o posterior a la fecha de vinculación. Restricción de chequeo: fechaDesvinculacion IS NULL OR fechaDesvinculacion &gt;= fechaVinculacion. |

## Campos calculados (vistas)

Estos datos **no se guardan** en la tabla: se calculan en una vista a partir de otras tablas.

| Campo | Tipo | Tablas de origen | Cálculo | Descripción |
| --- | --- | --- | --- | --- |
| `vigente` | `BOOLEAN` | VinculacionAcademica | fechaDesvinculacion IS NULL | Indica si el estudiante o docente sigue estudiando o enseñando en el programa. Se deduce de que el vínculo no tenga fecha de desvinculación (RF-072, RF-073). |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ CoordinacionPrograma](CoordinacionPrograma.md) · [Kudo ➡️](Kudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/VinculacionAcademica.md) · [📐 Clase](../clases/VinculacionAcademica.md)
