# 🧩 CoordinacionPrograma

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ CoordinacionFacultad](CoordinacionFacultad.md) · [VinculacionAcademica ➡️](VinculacionAcademica.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/CoordinacionPrograma.md) · [📐 Clase](../clases/CoordinacionPrograma.md)

> Historial de quién coordina cada Programa Académico. Solo una coordinación vigente por programa.

| Línea de vida | Atributos | Responsabilidades | Eventos | Políticas propias |
| :---: | :---: | :---: | :---: | :---: |
| Nivel 3 | 5 | 3 | 3 | 9 |

## Contenido

1. [Muestreo](#1-muestreo)
2. [Caracterización](#2-caracterización)
3. [Responsabilidades](#3-responsabilidades)
4. [Event Storming](#4-event-storming)
5. [Políticas](#5-políticas)

---

## 1. Muestreo

Ejemplos reales del objeto para validar sus atributos con el negocio.

| **id** | **usuario** | **programaAcademico** | **fechaInicio** | **fechaFin** | **Combinación única** |
| --- | --- | --- | --- | --- | --- |
| 1 | andres.cardona@uco.edu.co | Ingeniería de Sistemas | 01/02/2020 | 14/01/2026 | andres.cardona@uco.edu.co-Ingeniería de Sistemas-01/02/2020 |
| 2 | luisa.ospina@uco.edu.co | Ingeniería de Sistemas | 15/01/2026 | — | luisa.ospina@uco.edu.co-Ingeniería de Sistemas-15/01/2026 |
| 3 | natalia.quintero@uco.edu.co | Ingeniería Electrónica | 15/01/2026 | — | natalia.quintero@uco.edu.co-Ingeniería Electrónica-15/01/2026 |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único de la coordinación programa dentro del sistema. |
| `usuario` | Representa la persona que ejerce o ejerció la coordinación. |
| `programaAcademico` | Representa el programa académico que coordina la persona. |
| `fechaInicio` | Representa la fecha en que la persona empezó a coordinar. |
| `fechaFin` | Representa la fecha en que la persona dejó de coordinar; vacía mientras la coordinación está vigente. |
| `Combinación única` | Representa que una persona no puede iniciar dos veces la coordinación del mismo programa académico en la misma fecha. |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único de la coordinación programa dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `usuario` | Representa la persona que ejerce o ejerció la coordinación. | [Usuario](Usuario.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Usuario). La persona debe tener el rol Coordinador de Programa mientras la coordinación esté vigente. |
| `programaAcademico` | Representa el programa académico que coordina la persona. | [ProgramaAcademico](ProgramaAcademico.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Programa Académico). Solo puede existir una coordinación vigente por programa académico. |
| `fechaInicio` | Representa la fecha en que la persona empezó a coordinar. | Fecha | Si | No aplica | No aplica | No aplica | No puede ser posterior a la fecha actual y no puede se una fecha anterior a 01/01/1982 (año en que se fundó la UCO). |
| `fechaFin` | Representa la fecha en que la persona dejó de coordinar; vacía mientras la coordinación está vigente. | Fecha | No | No aplica | No aplica | No aplica | Vacía significa que la coordinación es vigente actualmente. Debe ser igual o posterior a la fecha de inicio. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `usuario` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `programaAcademico` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `fechaInicio` | No aplica | No aplica | 01/01/1982 (año de fundación de la UCO) | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | 01/01/1000 | No |
| `fechaFin` | No aplica | No aplica | Fecha de inicio | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | 01/01/1000 | No |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única | Representa que una persona no puede iniciar dos veces la coordinación del mismo programa académico en la misma fecha. | `usuario, programaAcademico, fechaInicio` | Compara exactamente. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`asignarCoordinadorPrograma`](#asignarcoordinadorprograma) | [`finalizarCoordinacionPrograma`](#finalizarcoordinacionprograma) | [`consultarCoordinacionesPrograma`](#consultarcoordinacionesprograma) |
| --- | :---: | :---: | :---: |
| `id` | NR | NR·NM | 👁 |
| `usuario` | R | R·NM | 👁 |
| `programaAcademico` | R | NR·NM | 👁 |
| `fechaInicio` | R | NR·NM | 👁 |
| `fechaFin` | NR | R·M | 👁 |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`asignarCoordinadorPrograma`](#asignarcoordinadorprograma) | Asignar la persona que coordina el programa académico cuando no tiene coordinador vigente: la primera vez o cuando el anterior dejó de estar activo (al suspender o desactivar su cuenta, su coordinación terminó). Así se hace el reemplazo (RN-220, RN-223). Solo puede elegir programas de su facultad. Si la persona es un aspirante a Coordinador de Programa con la cuenta Pendiente de aprobación, asignarle el programa es su aprobación (RN-091). |
| 2 | [`finalizarCoordinacionPrograma`](#finalizarcoordinacionprograma) | Terminar la coordinación vigente del programa académico cuando se suspende o se desactiva la cuenta de quien coordina. Lo hace el sistema; el programa académico queda sin coordinador y las responsabilidades que dependen de ese coordinador no se pueden usar hasta que se asigne uno nuevo. |
| 3 | [`consultarCoordinacionesPrograma`](#consultarcoordinacionesprograma) | Consultar quién coordina el programa académico hoy y quiénes la coordinaron antes (historial). |

### `asignarCoordinadorPrograma`

Asignar la persona que coordina el programa académico cuando no tiene coordinador vigente: la primera vez o cuando el anterior dejó de estar activo (al suspender o desactivar su cuenta, su coordinación terminó). Así se hace el reemplazo (RN-220, RN-223). Solo puede elegir programas de su facultad. Si la persona es un aspirante a Coordinador de Programa con la cuenta Pendiente de aprobación, asignarle el programa es su aprobación (RN-091).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{usuario, programaAcademico, fechaInicio}` | [CoordinacionPrograma](CoordinacionPrograma.md) | Persona que coordinará, el programa académico y fecha desde la que coordina. |

**Salida:** [CoordinacionPrograma](CoordinacionPrograma.md) — La coordinación vigente creada.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0006](Usuario.md#p-usu-0006) | Asegurar que quien ejecuta la acción tenga el rol Coordinador de Facultad (RN-117). | Solo un Coordinador de Facultad puede realizar esta acción. | 🛑 Detener operación |
| 3 | [P-COP-0003](#p-cop-0003) | Asegurar que la persona, el programa académico y la fecha de inicio sean válidos en tipo, obligatoriedad, formato y rango. | Se informa cuál dato no cumple y cómo debe escribirse. | 🛑 Detener operación |
| 4 | [P-PRO-0001](ProgramaAcademico.md#p-pro-0001) | Asegurar que exista un programa académico con el identificador indicado. | No existe el programa académico que se indicó. | 🛑 Detener operación |
| 5 | [P-COP-0002](#p-cop-0002) | Asegurar que el programa pertenezca a la facultad que coordina actualmente quien ejecuta la acción (RN-117, RN-120). | Ese programa no pertenece a su facultad. | 🛑 Detener operación |
| 6 | [P-USU-0029](Usuario.md#p-usu-0029) | Asegurar que la persona indicada exista, tenga el rol Coordinador de Programa y su cuenta esté Pendiente de aprobación (aspirante) o Activa (RN-001 a RN-003, RN-091). | La persona no es un aspirante ni un Coordinador de Programa activo. | 🛑 Detener operación |
| 7 | [P-COP-0004](#p-cop-0004) | Asegurar que el programa académico no tenga ya una coordinación vigente (RN-023, RN-051, RN-052). | El programa académico ya tiene un coordinador vigente; para cambiarlo, primero debe suspenderse o desactivarse la cuenta del actual. | 🛑 Detener operación |
| 8 | [P-COP-0005](#p-cop-0005) | Asegurar que la persona no tenga otra coordinación vigente del mismo tipo (INV-009, INV-010). | La persona ya coordina otra unidad académica. | 🛑 Detener operación |
| 9 | [P-COP-0006](#p-cop-0006) | Asegurar que la persona no inicie dos veces la coordinación del programa académico en la misma fecha. | Ya existe esa coordinación. | 🛑 Detener operación |
| 10 | [P-COP-0007](#p-cop-0007) | Asegurar que el identificador de la coordinación sea único dentro de las coordinaciones; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 11 | [P-USU-0030](Usuario.md#p-usu-0030) | Cuando se asigne un programa a un aspirante a Coordinador de Programa con la cuenta Pendiente de aprobación, pasar su cuenta a Aprobada: asignarle un programa de su facultad es la aprobación del Coordinador de Facultad (RN-091). Se activa cuando se confirmen su correo y su celular. | No se pudo aprobar la cuenta; se deshace toda la operación. | 🛑 Detener operación |
| 12 | [P-USU-0034](Usuario.md#p-usu-0034) | Cuando la cuenta esté Aprobada y tenga el correo y el celular verificados, activarla (RN-084 a RN-088). | No se pudo activar la cuenta; se deshace toda la operación. | 🛑 Detener operación |
| 13 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `finalizarCoordinacionPrograma`

Terminar la coordinación vigente del programa académico cuando se suspende o se desactiva la cuenta de quien coordina. Lo hace el sistema; el programa académico queda sin coordinador y las responsabilidades que dependen de ese coordinador no se pueden usar hasta que se asigne uno nuevo.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{usuario}` | [CoordinacionPrograma](CoordinacionPrograma.md) | Persona cuya cuenta se suspendió o desactivó. |
| `datos{fechaFin}` | [CoordinacionPrograma](CoordinacionPrograma.md) | Fecha de la suspensión o desactivación. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-COP-0008](#p-cop-0008) | Asegurar que la coordinación esté vigente (sin fecha de fin) antes de cerrarla por la suspensión o desactivación de quien coordina. | Esa coordinación ya había terminado. | 🛑 Detener operación |
| 2 | [P-COP-0001](#p-cop-0001) | Cuando se suspenda o se desactive la cuenta de quien coordina el programa, asignar a su coordinación vigente la fecha de fin: deja de estar vigente y la persona no puede usar sus responsabilidades de coordinador hasta que le asignen una coordinación de nuevo. | No se pudo finalizar la coordinación; se deshace toda la operación. | 🛑 Detener operación |
| 3 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `consultarCoordinacionesPrograma`

Consultar quién coordina el programa académico hoy y quiénes la coordinaron antes (historial).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{usuario, programaAcademico}` | [CoordinacionPrograma](CoordinacionPrograma.md) | Datos opcionales para filtrar la consulta. |

**Salida:** [Lista&lt;CoordinacionPrograma&gt;](CoordinacionPrograma.md) — Las coordinaciones vigentes e históricas que cumplen el filtro.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Coordinador de Facultad | **Asignar al coordinador del programa académico**<br>Asignar la persona que coordina el programa académico cuando no tiene coordinador vigente: la primera vez o cuando el anterior dejó de estar activo (al suspender o desactivar su cuenta, su coordinación terminó). Así se hace el reemplazo (RN-220, RN-223). Solo puede elegir programas de su facultad. Si la persona es un aspirante a Coordinador de Programa con la cuenta Pendiente de aprobación, asignarle el programa es su aprobación (RN-091). | **Aspirantes a Coordinador de Programa pendientes y Coordinadores de Programa activos sin coordinación**: Para elegir a quién se asigna.<br>**ProgramaAcademico**: Para elegir el programa académico. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0006](Usuario.md#p-usu-0006)<br>[P-COP-0003](#p-cop-0003)<br>[P-PRO-0001](ProgramaAcademico.md#p-pro-0001)<br>[P-COP-0002](#p-cop-0002)<br>[P-USU-0029](Usuario.md#p-usu-0029)<br>[P-COP-0004](#p-cop-0004)<br>[P-COP-0005](#p-cop-0005)<br>[P-COP-0006](#p-cop-0006)<br>[P-COP-0007](#p-cop-0007)<br>[P-USU-0030](Usuario.md#p-usu-0030)<br>[P-USU-0034](Usuario.md#p-usu-0034)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Coordinador de Programa asignado** | Ninguno |
| Sistema | **Finalizar la coordinación del programa académico**<br>Terminar la coordinación vigente del programa académico cuando se suspende o se desactiva la cuenta de quien coordina. Lo hace el sistema; el programa académico queda sin coordinador y las responsabilidades que dependen de ese coordinador no se pueden usar hasta que se asigne uno nuevo. | **No aplica**: No aplica | [P-COP-0008](#p-cop-0008)<br>[P-COP-0001](#p-cop-0001)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Coordinación de programa finalizada** | Ninguno |
| Usuario autenticado | **Consultar los coordinadores del programa académico**<br>Consultar quién coordina el programa académico hoy y quiénes la coordinaron antes (historial). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001) | No aplica | 🟧 **Coordinaciones consultadas** | Ninguno |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="p-cop-0001"></a>**P-COP-0001** | Cuando se suspenda o se desactive la cuenta de quien coordina el programa, asignar a su coordinación vigente la fecha de fin: deja de estar vigente y la persona no puede usar sus responsabilidades de coordinador hasta que le asignen una coordinación de nuevo. | CoordinacionPrograma | `FinalizarCoordinacionProgramaAlSuspenderODesactivarRule` |
| <a id="p-cop-0002"></a>**P-COP-0002** | Asegurar que el programa pertenezca a la facultad que coordina actualmente quien ejecuta la acción (RN-117, RN-120). | CoordinacionPrograma | `AsegurarProgramaPerteneceAFacultadDelCoordinadorRule` |
| <a id="p-cop-0003"></a>**P-COP-0003** | Asegurar que la persona, el programa académico y la fecha de inicio sean válidos en tipo, obligatoriedad, formato y rango. | CoordinacionPrograma | `AsegurarFormatoCoordinacionProgramaValidoRule` |
| <a id="p-cop-0004"></a>**P-COP-0004** | Asegurar que el programa académico no tenga ya una coordinación vigente (RN-023, RN-051, RN-052). | CoordinacionPrograma | `AsegurarProgramaAcademicoSinCoordinacionVigenteRule` |
| <a id="p-cop-0005"></a>**P-COP-0005** | Asegurar que la persona no tenga otra coordinación vigente del mismo tipo (INV-009, INV-010). | CoordinacionPrograma | `AsegurarPersonaSinOtraCoordinacionProgramaRule` |
| <a id="p-cop-0006"></a>**P-COP-0006** | Asegurar que la persona no inicie dos veces la coordinación del programa académico en la misma fecha. | CoordinacionPrograma | `AsegurarCoordinacionProgramaNoRepetidaRule` |
| <a id="p-cop-0007"></a>**P-COP-0007** | Asegurar que el identificador de la coordinación sea único dentro de las coordinaciones; si ya existe, generar otro. | CoordinacionPrograma | `AsegurarIdCoordinacionProgramaUnicoRule` |
| <a id="p-cop-0008"></a>**P-COP-0008** | Asegurar que la coordinación esté vigente (sin fecha de fin) antes de cerrarla por la suspensión o desactivación de quien coordina. | CoordinacionPrograma | `AsegurarCoordinacionProgramaVigenteRule` |
| <a id="p-cop-0009"></a>**P-COP-0009** | Asegurar que quien ejecuta la acción coordine actualmente el programa indicado (RN-112 a RN-115). | CoordinacionPrograma | `AsegurarUsuarioCoordinaElProgramaRule` |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ CoordinacionFacultad](CoordinacionFacultad.md) · [VinculacionAcademica ➡️](VinculacionAcademica.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/CoordinacionPrograma.md) · [📐 Clase](../clases/CoordinacionPrograma.md)
