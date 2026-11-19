# 🧩 ProgramaAcademico

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ Usuario](Usuario.md) · [Auditoria ➡️](Auditoria.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/ProgramaAcademico.md) · [📐 Clase](../clases/ProgramaAcademico.md)

> Unidad académica que pertenece exactamente a una Facultad y en la que se vinculan estudiantes y docentes.

| Línea de vida | Atributos | Responsabilidades | Eventos | Políticas propias |
| :---: | :---: | :---: | :---: | :---: |
| Nivel 2 | 3 | 3 | 3 | 5 |

## Contenido

1. [Muestreo](#1-muestreo)
2. [Caracterización](#2-caracterización)
3. [Responsabilidades](#3-responsabilidades)
4. [Event Storming](#4-event-storming)
5. [Políticas](#5-políticas)

---

## 1. Muestreo

Ejemplos reales del objeto para validar sus atributos con el negocio.

| **id** | **facultad** | **nombre** | **Combinación única** |
| --- | --- | --- | --- |
| 1 | Facultad de Ingeniería | Ingeniería de Sistemas | Ingeniería de Sistemas |
| 2 | Facultad de Ingeniería | Ingeniería Electrónica | Ingeniería Electrónica |
| 3 | Facultad de Ciencias de la Salud | Enfermería | Enfermería |
| 4 | Facultad de Ciencias Sociales | Psicología | Psicología |
| 5 | Facultad de Ciencias Económicas y Administrativas | Administración de Empresas | Administración de Empresas |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único del programa académico dentro del sistema. |
| `facultad` | Representa la facultad a la que pertenece el programa académico. |
| `nombre` | Representa el nombre oficial del programa académico. |
| `Combinación única` | Representa que en la universidad no pueden existir dos programas académicos con el mismo nombre. |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único del programa académico dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `facultad` | Representa la facultad a la que pertenece el programa académico. | [Facultad](Facultad.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado. |
| `nombre` | Representa el nombre oficial del programa académico. | Texto | Si | 5 | 100 | Primera letra en mayúscula. Ej: Ingeniería de Sistemas | Solo se permiten carateres de letras mayusculas y minusculas con tildes y espacios. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `facultad` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `nombre` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única | Representa que en la universidad no pueden existir dos programas académicos con el mismo nombre. | `nombre` | No distingue mayúsculas, minúsculas, tildes ni espacios repetidos. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`registrarProgramaAcademico`](#registrarprogramaacademico) | [`consultarProgramasAcademicos`](#consultarprogramasacademicos) | [`cambiarNombreProgramaAcademico`](#cambiarnombreprogramaacademico) |
| --- | :---: | :---: | :---: |
| `id` | NR | 👁 | R·NM |
| `facultad` | R | 👁 | NR·NM |
| `nombre` | R | 👁 | R·M |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`registrarProgramaAcademico`](#registrarprogramaacademico) | Crear un programa académico dentro de exactamente una facultad (RN-049, INV-008). |
| 2 | [`consultarProgramasAcademicos`](#consultarprogramasacademicos) | Consultar los programas académicos, todos o los de una facultad (RF-021, RN-118). |
| 3 | [`cambiarNombreProgramaAcademico`](#cambiarnombreprogramaacademico) | Cambiar el nombre de un programa académico (RF-022). |

### `registrarProgramaAcademico`

Crear un programa académico dentro de exactamente una facultad (RN-049, INV-008).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{facultad, nombre}` | [ProgramaAcademico](ProgramaAcademico.md) | Facultad a la que pertenece y nombre del programa. |

**Salida:** [ProgramaAcademico](ProgramaAcademico.md) — El programa registrado.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0004](Usuario.md#p-usu-0004) | Asegurar que quien ejecuta la acción tenga el rol Administrador (RN-129 a RN-131). | Solo un Administrador puede realizar esta acción. | 🛑 Detener operación |
| 3 | [P-PRO-0002](#p-pro-0002) | Asegurar que la facultad y el nombre del programa sean válidos en tipo, longitud, obligatoriedad y formato (RN-046, RN-048). | Se informa cuál dato no cumple y cómo debe escribirse. | 🛑 Detener operación |
| 4 | [P-FAC-0001](Facultad.md#p-fac-0001) | Asegurar que exista una facultad con el identificador indicado. | No existe la facultad que se indicó. | 🛑 Detener operación |
| 5 | [P-PRO-0004](#p-pro-0004) | Asegurar que no exista otro programa académico con el mismo nombre en la universidad. | Ya existe un programa con ese nombre. | 🛑 Detener operación |
| 6 | [P-PRO-0005](#p-pro-0005) | Asegurar que el identificador del programa académico sea único dentro de los programas académicos; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 7 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `consultarProgramasAcademicos`

Consultar los programas académicos, todos o los de una facultad (RF-021, RN-118).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{facultad, nombre}` | [ProgramaAcademico](ProgramaAcademico.md) | Datos opcionales para filtrar la consulta. |

**Salida:** [Lista&lt;ProgramaAcademico&gt;](ProgramaAcademico.md) — Los programas que cumplen el filtro.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |

### `cambiarNombreProgramaAcademico`

Cambiar el nombre de un programa académico (RF-022).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador del programa. |
| `datos{nombre}` | [ProgramaAcademico](ProgramaAcademico.md) | Nombre nuevo. |

**Salida:** [ProgramaAcademico](ProgramaAcademico.md) — El programa con su nombre nuevo.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0004](Usuario.md#p-usu-0004) | Asegurar que quien ejecuta la acción tenga el rol Administrador (RN-129 a RN-131). | Solo un Administrador puede realizar esta acción. | 🛑 Detener operación |
| 3 | [P-PRO-0001](#p-pro-0001) | Asegurar que exista un programa académico con el identificador indicado. | No existe el programa académico que se indicó. | 🛑 Detener operación |
| 4 | [P-PRO-0003](#p-pro-0003) | Asegurar que el nombre nuevo del programa sea válido en tipo, longitud, obligatoriedad y formato. | Se informa cómo debe escribirse el nombre. | 🛑 Detener operación |
| 5 | [P-PRO-0004](#p-pro-0004) | Asegurar que no exista otro programa académico con el mismo nombre en la universidad. | Ya existe un programa con ese nombre. | 🛑 Detener operación |
| 6 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Administrador | **Registrar el programa académico**<br>Crear un programa académico dentro de exactamente una facultad (RN-049, INV-008). | **Facultades**: Para elegir la facultad a la que pertenece el programa. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0004](Usuario.md#p-usu-0004)<br>[P-PRO-0002](#p-pro-0002)<br>[P-FAC-0001](Facultad.md#p-fac-0001)<br>[P-PRO-0004](#p-pro-0004)<br>[P-PRO-0005](#p-pro-0005)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Programa creado** | Ninguno |
| Usuario autenticado | **Consultar los programas académicos**<br>Consultar los programas académicos, todos o los de una facultad (RF-021, RN-118). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001) | No aplica | 🟧 **Programas académicos consultados** | Ninguno |
| Administrador | **Cambiar el nombre del programa académico**<br>Cambiar el nombre de un programa académico (RF-022). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0004](Usuario.md#p-usu-0004)<br>[P-PRO-0001](#p-pro-0001)<br>[P-PRO-0003](#p-pro-0003)<br>[P-PRO-0004](#p-pro-0004)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Programa modificado** | Ninguno |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="p-pro-0001"></a>**P-PRO-0001** | Asegurar que exista un programa académico con el identificador indicado. | ProgramaAcademico | `AsegurarProgramaAcademicoExisteRule` |
| <a id="p-pro-0002"></a>**P-PRO-0002** | Asegurar que la facultad y el nombre del programa sean válidos en tipo, longitud, obligatoriedad y formato (RN-046, RN-048). | ProgramaAcademico | `AsegurarFormatoProgramaAcademicoValidoRule` |
| <a id="p-pro-0003"></a>**P-PRO-0003** | Asegurar que el nombre nuevo del programa sea válido en tipo, longitud, obligatoriedad y formato. | ProgramaAcademico | `AsegurarFormatoNombreProgramaAcademicoValidoRule` |
| <a id="p-pro-0004"></a>**P-PRO-0004** | Asegurar que no exista otro programa académico con el mismo nombre en la universidad. | ProgramaAcademico | `AsegurarNombreProgramaAcademicoNoExistaRule` |
| <a id="p-pro-0005"></a>**P-PRO-0005** | Asegurar que el identificador del programa académico sea único dentro de los programas académicos; si ya existe, generar otro. | ProgramaAcademico | `AsegurarIdProgramaAcademicoUnicoRule` |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ Usuario](Usuario.md) · [Auditoria ➡️](Auditoria.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/ProgramaAcademico.md) · [📐 Clase](../clases/ProgramaAcademico.md)
