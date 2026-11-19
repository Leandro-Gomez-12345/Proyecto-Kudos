# 🗄️ CoordinacionFacultad

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ Auditoria](Auditoria.md) · [CoordinacionPrograma ➡️](CoordinacionPrograma.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/CoordinacionFacultad.md) · [📐 Clase](../clases/CoordinacionFacultad.md)

> Historial de quién coordina cada Facultad. Solo una coordinación vigente por facultad y su reemplazo queda registrado.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `usuario` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · UQ | [Usuario](Usuario.md).`id` | Usuario |
| `facultad` | `UUID` | 36 | — | — | ✅ | — | 🔗 FK · UQ · IX | [Facultad](Facultad.md).`id` | Facultad |
| `fechaInicio` | `DATE` | — | — | — | ✅ | — | UQ · CHK | — | Fecha inicio |
| `fechaFin` | `DATE` | — | — | — | ❌ | — | CHK | — | Fecha fin |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_CoordinacionFacultad` | Llave primaria | `id` |
| `UQ_coordinacionFacultad_usuario_facultad_fechaInicio` | Unicidad | `usuario`, `facultad`, `fechaInicio` |
| `CHK_coordinacionFacultad_fechaInicio` | Chequeo | `fechaInicio` |
| `CHK_coordinacionFacultad_fechaFin` | Chequeo | `fechaFin` |
| `IX_coordinacionFacultad_facultad` | Índice (no único) | `facultad` |
| — | Llave foránea | `usuario` → [Usuario](Usuario.md).`id` |
| — | Llave foránea | `facultad` → [Facultad](Facultad.md).`id` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único de la coordinación facultad dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_CoordinacionFacultad. Debe ser único en el sistema. |
| `usuario` | Referencia a la tabla Usuario; por medio de ella se conoce la persona que ejerce o ejerció la coordinación. La persona debe tener el rol Coordinador de Facultad mientras la coordinación esté vigente. Única en combinación con facultad, fechaInicio (UQ_coordinacionFacultad_usuario_facultad_fechaInicio). |
| `facultad` | Referencia a la tabla Facultad; por medio de ella se conoce la facultad que coordina la persona. Solo puede existir una coordinación vigente por facultad. Única en combinación con usuario, fechaInicio (UQ_coordinacionFacultad_usuario_facultad_fechaInicio). |
| `fechaInicio` | Representa la fecha en que la persona empezó a coordinar. No puede ser posterior a la fecha actual y no puede ser una fecha anterior a 01/01/1982 (año en que se fundó la UCO). Única en combinación con usuario, facultad (UQ_coordinacionFacultad_usuario_facultad_fechaInicio). Restricción de chequeo: fechaInicio &gt;= '1982-01-01' (fundación de la UCO). |
| `fechaFin` | Representa la fecha en que la persona dejó de coordinar; vacía mientras la coordinación está vigente. Vacía significa que la coordinación es vigente actualmente. Debe ser igual o posterior a la fecha de inicio. Restricción de chequeo: fechaFin IS NULL OR fechaFin &gt;= fechaInicio. |

## Campos calculados (vistas)

Estos datos **no se guardan** en la tabla: se calculan en una vista a partir de otras tablas.

| Campo | Tipo | Tablas de origen | Cálculo | Descripción |
| --- | --- | --- | --- | --- |
| `vigente` | `BOOLEAN` | CoordinacionFacultad | fechaFin IS NULL | Indica si la persona coordina actualmente la facultad. Se deduce de que la coordinación no tenga fecha de fin; permite conocer qué facultad acompaña cada Coordinador de Facultad (RF-075). |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ Auditoria](Auditoria.md) · [CoordinacionPrograma ➡️](CoordinacionPrograma.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/CoordinacionFacultad.md) · [📐 Clase](../clases/CoordinacionFacultad.md)
