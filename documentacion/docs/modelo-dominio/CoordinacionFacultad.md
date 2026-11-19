# 🧩 CoordinacionFacultad

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ Auditoria](Auditoria.md) · [CoordinacionPrograma ➡️](CoordinacionPrograma.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/CoordinacionFacultad.md) · [📐 Clase](../clases/CoordinacionFacultad.md)

> Historial de quién coordina cada Facultad. Solo una coordinación vigente por facultad y su reemplazo queda registrado.

| Línea de vida | Atributos | Responsabilidades | Eventos | Políticas propias |
| :---: | :---: | :---: | :---: | :---: |
| Nivel 3 | 5 | 3 | 3 | 7 |

## Contenido

1. [Muestreo](#1-muestreo)
2. [Caracterización](#2-caracterización)
3. [Responsabilidades](#3-responsabilidades)
4. [Event Storming](#4-event-storming)
5. [Políticas](#5-políticas)

---

## 1. Muestreo

Ejemplos reales del objeto para validar sus atributos con el negocio.

| **id** | **usuario** | **facultad** | **fechaInicio** | **fechaFin** | **Combinación única** |
| --- | --- | --- | --- | --- | --- |
| 1 | diego.montoya@uco.edu.co | Facultad de Ingeniería | 01/02/2021 | 11/01/2026 | diego.montoya@uco.edu.co-Facultad de Ingeniería-01/02/2021 |
| 2 | carlos.zapata@uco.edu.co | Facultad de Ingeniería | 12/01/2026 | — | carlos.zapata@uco.edu.co-Facultad de Ingeniería-12/01/2026 |
| 3 | patricia.henao@uco.edu.co | Facultad de Ciencias de la Salud | 12/01/2026 | — | patricia.henao@uco.edu.co-Facultad de Ciencias de la Salud-12/01/2026 |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único de la coordinación facultad dentro del sistema. |
| `usuario` | Representa la persona que ejerce o ejerció la coordinación. |
| `facultad` | Representa la facultad que coordina la persona. |
| `fechaInicio` | Representa la fecha en que la persona empezó a coordinar. |
| `fechaFin` | Representa la fecha en que la persona dejó de coordinar; vacía mientras la coordinación está vigente. |
| `Combinación única` | Representa que una persona no puede iniciar dos veces la coordinación del mismo facultad en la misma fecha. |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único de la coordinación facultad dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `usuario` | Representa la persona que ejerce o ejerció la coordinación. | [Usuario](Usuario.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Usuario). La persona debe tener el rol Coordinador de Facultad mientras la coordinación esté vigente. |
| `facultad` | Representa la facultad que coordina la persona. | [Facultad](Facultad.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Facultad). Solo puede existir una coordinación vigente por facultad. |
| `fechaInicio` | Representa la fecha en que la persona empezó a coordinar. | Fecha | Si | No aplica | No aplica | No aplica | No puede ser posterior a la fecha actual y no puede se una fecha anterior a 01/01/1982 (año en que se fundó la UCO). |
| `fechaFin` | Representa la fecha en que la persona dejó de coordinar; vacía mientras la coordinación está vigente. | Fecha | No | No aplica | No aplica | No aplica | Vacía significa que la coordinación es vigente actualmente. Debe ser igual o posterior a la fecha de inicio. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `usuario` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `facultad` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `fechaInicio` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | 01/01/1000 | No |
| `fechaFin` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | 01/01/1000 | No |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única | Representa que una persona no puede iniciar dos veces la coordinación del mismo facultad en la misma fecha. | `usuario, facultad, fechaInicio` | Compara exactamente. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`asignarCoordinadorFacultad`](#asignarcoordinadorfacultad) | [`finalizarCoordinacionFacultad`](#finalizarcoordinacionfacultad) | [`consultarCoordinacionesFacultad`](#consultarcoordinacionesfacultad) |
| --- | :---: | :---: | :---: |
| `id` | NR | NR·NM | 👁 |
| `usuario` | R | R·NM | 👁 |
| `facultad` | R | NR·NM | 👁 |
| `fechaInicio` | R | NR·NM | 👁 |
| `fechaFin` | NR | R·M | 👁 |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`asignarCoordinadorFacultad`](#asignarcoordinadorfacultad) | Asignar la persona que coordina la facultad cuando no tiene coordinador vigente: la primera vez o cuando el anterior dejó de estar activo (al suspender o desactivar su cuenta, su coordinación terminó). Así se hace el reemplazo (RN-220, RN-223). |
| 2 | [`finalizarCoordinacionFacultad`](#finalizarcoordinacionfacultad) | Terminar la coordinación vigente de la facultad cuando se suspende o se desactiva la cuenta de quien coordina. Lo hace el sistema; la facultad queda sin coordinador y las responsabilidades que dependen de ese coordinador no se pueden usar hasta que se asigne uno nuevo. |
| 3 | [`consultarCoordinacionesFacultad`](#consultarcoordinacionesfacultad) | Consultar quién coordina la facultad hoy y quiénes la coordinaron antes (historial). |

### `asignarCoordinadorFacultad`

Asignar la persona que coordina la facultad cuando no tiene coordinador vigente: la primera vez o cuando el anterior dejó de estar activo (al suspender o desactivar su cuenta, su coordinación terminó). Así se hace el reemplazo (RN-220, RN-223).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{usuario, facultad, fechaInicio}` | [CoordinacionFacultad](CoordinacionFacultad.md) | Persona que coordinará, la facultad y fecha desde la que coordina. |

**Salida:** [CoordinacionFacultad](CoordinacionFacultad.md) — La coordinación vigente creada.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0005](Usuario.md#p-usu-0005) | Asegurar que quien ejecuta la acción tenga el rol Coordinador Académico (RN-121). | Solo el Coordinador Académico puede realizar esta acción. | 🛑 Detener operación |
| 3 | [P-COF-0002](#p-cof-0002) | Asegurar que la persona, la facultad y la fecha de inicio sean válidos en tipo, obligatoriedad, formato y rango. | Se informa cuál dato no cumple y cómo debe escribirse. | 🛑 Detener operación |
| 4 | [P-FAC-0001](Facultad.md#p-fac-0001) | Asegurar que exista una facultad con el identificador indicado. | No existe la facultad que se indicó. | 🛑 Detener operación |
| 5 | [P-USU-0009](Usuario.md#p-usu-0009) | Asegurar que la persona indicada exista y tenga la cuenta Activa. | La persona indicada no existe o su cuenta no está activa. | 🛑 Detener operación |
| 6 | [P-USU-0010](Usuario.md#p-usu-0010) | Asegurar que la persona indicada tenga el rol Coordinador de Facultad (RN-001 a RN-003). | La persona indicada no tiene el rol Coordinador de Facultad. | 🛑 Detener operación |
| 7 | [P-COF-0003](#p-cof-0003) | Asegurar que la facultad no tenga ya una coordinación vigente (RN-017, RN-043, RN-044). | La facultad ya tiene un coordinador vigente; para cambiarlo, primero debe suspenderse o desactivarse la cuenta del actual. | 🛑 Detener operación |
| 8 | [P-COF-0004](#p-cof-0004) | Asegurar que la persona no tenga otra coordinación vigente del mismo tipo (INV-009, INV-010). | La persona ya coordina otra unidad académica. | 🛑 Detener operación |
| 9 | [P-COF-0005](#p-cof-0005) | Asegurar que la persona no inicie dos veces la coordinación de la facultad en la misma fecha. | Ya existe esa coordinación. | 🛑 Detener operación |
| 10 | [P-COF-0006](#p-cof-0006) | Asegurar que el identificador de la coordinación sea único dentro de las coordinaciones; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 11 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `finalizarCoordinacionFacultad`

Terminar la coordinación vigente de la facultad cuando se suspende o se desactiva la cuenta de quien coordina. Lo hace el sistema; la facultad queda sin coordinador y las responsabilidades que dependen de ese coordinador no se pueden usar hasta que se asigne uno nuevo.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{usuario}` | [CoordinacionFacultad](CoordinacionFacultad.md) | Persona cuya cuenta se suspendió o desactivó. |
| `datos{fechaFin}` | [CoordinacionFacultad](CoordinacionFacultad.md) | Fecha de la suspensión o desactivación. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-COF-0007](#p-cof-0007) | Asegurar que la coordinación esté vigente (sin fecha de fin) antes de cerrarla por la suspensión o desactivación de quien coordina. | Esa coordinación ya había terminado. | 🛑 Detener operación |
| 2 | [P-COF-0001](#p-cof-0001) | Cuando se suspenda o se desactive la cuenta de quien coordina la facultad, asignar a su coordinación vigente la fecha de fin: deja de estar vigente y la persona no puede usar sus responsabilidades de coordinador hasta que le asignen una coordinación de nuevo. | No se pudo finalizar la coordinación; se deshace toda la operación. | 🛑 Detener operación |
| 3 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `consultarCoordinacionesFacultad`

Consultar quién coordina la facultad hoy y quiénes la coordinaron antes (historial).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{usuario, facultad}` | [CoordinacionFacultad](CoordinacionFacultad.md) | Datos opcionales para filtrar la consulta. |

**Salida:** [Lista&lt;CoordinacionFacultad&gt;](CoordinacionFacultad.md) — Las coordinaciones vigentes e históricas que cumplen el filtro.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Coordinador Académico | **Asignar al coordinador de la facultad**<br>Asignar la persona que coordina la facultad cuando no tiene coordinador vigente: la primera vez o cuando el anterior dejó de estar activo (al suspender o desactivar su cuenta, su coordinación terminó). Así se hace el reemplazo (RN-220, RN-223). | **Personas con el rol de coordinador y cuenta activa**: Para elegir a quién se asigna.<br>**Facultad**: Para elegir la facultad. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0005](Usuario.md#p-usu-0005)<br>[P-COF-0002](#p-cof-0002)<br>[P-FAC-0001](Facultad.md#p-fac-0001)<br>[P-USU-0009](Usuario.md#p-usu-0009)<br>[P-USU-0010](Usuario.md#p-usu-0010)<br>[P-COF-0003](#p-cof-0003)<br>[P-COF-0004](#p-cof-0004)<br>[P-COF-0005](#p-cof-0005)<br>[P-COF-0006](#p-cof-0006)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Coordinador de Facultad asignado** | Ninguno |
| Sistema | **Finalizar la coordinación de la facultad**<br>Terminar la coordinación vigente de la facultad cuando se suspende o se desactiva la cuenta de quien coordina. Lo hace el sistema; la facultad queda sin coordinador y las responsabilidades que dependen de ese coordinador no se pueden usar hasta que se asigne uno nuevo. | **No aplica**: No aplica | [P-COF-0007](#p-cof-0007)<br>[P-COF-0001](#p-cof-0001)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Coordinación de facultad finalizada** | Ninguno |
| Usuario autenticado | **Consultar los coordinadores de la facultad**<br>Consultar quién coordina la facultad hoy y quiénes la coordinaron antes (historial). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001) | No aplica | 🟧 **Coordinaciones consultadas** | Ninguno |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="p-cof-0001"></a>**P-COF-0001** | Cuando se suspenda o se desactive la cuenta de quien coordina la facultad, asignar a su coordinación vigente la fecha de fin: deja de estar vigente y la persona no puede usar sus responsabilidades de coordinador hasta que le asignen una coordinación de nuevo. | CoordinacionFacultad | `FinalizarCoordinacionFacultadAlSuspenderODesactivarRule` |
| <a id="p-cof-0002"></a>**P-COF-0002** | Asegurar que la persona, la facultad y la fecha de inicio sean válidos en tipo, obligatoriedad, formato y rango. | CoordinacionFacultad | `AsegurarFormatoCoordinacionFacultadValidoRule` |
| <a id="p-cof-0003"></a>**P-COF-0003** | Asegurar que la facultad no tenga ya una coordinación vigente (RN-017, RN-043, RN-044). | CoordinacionFacultad | `AsegurarFacultadSinCoordinacionVigenteRule` |
| <a id="p-cof-0004"></a>**P-COF-0004** | Asegurar que la persona no tenga otra coordinación vigente del mismo tipo (INV-009, INV-010). | CoordinacionFacultad | `AsegurarPersonaSinOtraCoordinacionFacultadRule` |
| <a id="p-cof-0005"></a>**P-COF-0005** | Asegurar que la persona no inicie dos veces la coordinación de la facultad en la misma fecha. | CoordinacionFacultad | `AsegurarCoordinacionFacultadNoRepetidaRule` |
| <a id="p-cof-0006"></a>**P-COF-0006** | Asegurar que el identificador de la coordinación sea único dentro de las coordinaciones; si ya existe, generar otro. | CoordinacionFacultad | `AsegurarIdCoordinacionFacultadUnicoRule` |
| <a id="p-cof-0007"></a>**P-COF-0007** | Asegurar que la coordinación esté vigente (sin fecha de fin) antes de cerrarla por la suspensión o desactivación de quien coordina. | CoordinacionFacultad | `AsegurarCoordinacionFacultadVigenteRule` |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ Auditoria](Auditoria.md) · [CoordinacionPrograma ➡️](CoordinacionPrograma.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/CoordinacionFacultad.md) · [📐 Clase](../clases/CoordinacionFacultad.md)
