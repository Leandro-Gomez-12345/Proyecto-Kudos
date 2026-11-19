# 🧩 VinculacionAcademica

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ CoordinacionPrograma](CoordinacionPrograma.md) · [Kudo ➡️](Kudo.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/VinculacionAcademica.md) · [📐 Clase](../clases/VinculacionAcademica.md)

> Vínculo de un estudiante o docente con un Programa Académico. Una persona puede estar en varios programas y la desvinculación conserva el historial.

| Línea de vida | Atributos | Responsabilidades | Eventos | Políticas propias |
| :---: | :---: | :---: | :---: | :---: |
| Nivel 3 | 5 | 5 | 5 | 9 |

## Contenido

1. [Muestreo](#1-muestreo)
2. [Caracterización](#2-caracterización)
3. [Responsabilidades](#3-responsabilidades)
4. [Event Storming](#4-event-storming)
5. [Políticas](#5-políticas)

---

## 1. Muestreo

Ejemplos reales del objeto para validar sus atributos con el negocio.

| **id** | **usuario** | **programaAcademico** | **fechaVinculacion** | **fechaDesvinculacion** | **Combinación única** |
| --- | --- | --- | --- | --- | --- |
| 1 | valentina.arango@uco.edu.co | Ingeniería de Sistemas | 01/02/2023 | — | valentina.arango@uco.edu.co-Ingeniería de Sistemas-01/02/2023 |
| 2 | valentina.arango@uco.edu.co | Ingeniería Electrónica | 01/02/2025 | — | valentina.arango@uco.edu.co-Ingeniería Electrónica-01/02/2025 |
| 3 | mateo.giraldo@uco.edu.co | Psicología | 01/02/2022 | 30/06/2025 | mateo.giraldo@uco.edu.co-Psicología-01/02/2022 |
| 4 | mateo.giraldo@uco.edu.co | Ingeniería de Sistemas | 01/08/2025 | — | mateo.giraldo@uco.edu.co-Ingeniería de Sistemas-01/08/2025 |
| 5 | andres.cardona@uco.edu.co | Ingeniería de Sistemas | 15/01/2026 | — | andres.cardona@uco.edu.co-Ingeniería de Sistemas-15/01/2026 |
| 6 | andres.cardona@uco.edu.co | Ingeniería Electrónica | 15/01/2026 | — | andres.cardona@uco.edu.co-Ingeniería Electrónica-15/01/2026 |
| 7 | diego.montoya@uco.edu.co | Ingeniería Electrónica | 20/01/2026 | — | diego.montoya@uco.edu.co-Ingeniería Electrónica-20/01/2026 |
| 8 | sara.valencia@uco.edu.co | Ingeniería de Sistemas | 10/11/2026 | — | sara.valencia@uco.edu.co-Ingeniería de Sistemas-10/11/2026 |
| 9 | camila.rendon@uco.edu.co | Enfermería | 05/11/2026 | — | camila.rendon@uco.edu.co-Enfermería-05/11/2026 |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único de la vinculación académica dentro del sistema. |
| `usuario` | Representa el estudiante o docente que se vincula al programa. |
| `programaAcademico` | Representa el programa académico en el que estudia o enseña la persona. |
| `fechaVinculacion` | Representa la fecha desde la que la persona estudia o enseña en el programa. |
| `fechaDesvinculacion` | Representa la fecha en que la persona dejó el programa; vacía mientras el vínculo está vigente. |
| `Combinación única` | Representa que una persona no puede vincularse dos veces al mismo programa en la misma fecha. |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único de la vinculación académica dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `usuario` | Representa el estudiante o docente que se vincula al programa. | [Usuario](Usuario.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Usuario). La persona debe tener rol Estudiante o Docente. |
| `programaAcademico` | Representa el programa académico en el que estudia o enseña la persona. | [ProgramaAcademico](ProgramaAcademico.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Programa Académico). |
| `fechaVinculacion` | Representa la fecha desde la que la persona estudia o enseña en el programa. | Fecha | Si | No aplica | No aplica | No aplica | No puede ser posterior a la fecha actual y no puede se una fecha anterior a 01/01/1982 (año en que se fundó la UCO). |
| `fechaDesvinculacion` | Representa la fecha en que la persona dejó el programa; vacía mientras el vínculo está vigente. | Fecha | No | No aplica | No aplica | No aplica | Vacía significa que el vínculo es vigente todavia. Debe ser igual o posterior a la fecha de vinculación. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `usuario` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `programaAcademico` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `fechaVinculacion` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | 01/01/1000 | No |
| `fechaDesvinculacion` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | 01/01/1000 | No |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única | Representa que una persona no puede vincularse dos veces al mismo programa en la misma fecha. | `usuario, programaAcademico, fechaVinculacion` | Compara exactamente. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`vincularPersonaAProgramaAcademico`](#vincularpersonaaprogramaacademico) | [`desvincularPersonaDeProgramaAcademico`](#desvincularpersonadeprogramaacademico) | [`vincularPersonaAlRegistrarse`](#vincularpersonaalregistrarse) | [`desvincularPersonaDeTodosLosProgramas`](#desvincularpersonadetodoslosprogramas) | [`consultarVinculacionesAcademicas`](#consultarvinculacionesacademicas) |
| --- | :---: | :---: | :---: | :---: | :---: |
| `id` | NR | R·NM | NR | NR·NM | 👁 |
| `usuario` | R | NR·NM | R | R·NM | 👁 |
| `programaAcademico` | R | NR·NM | R | NR·NM | 👁 |
| `fechaVinculacion` | R | NR·NM | R | NR·NM | 👁 |
| `fechaDesvinculacion` | NR | R·M | NR | R·M | 👁 |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`vincularPersonaAProgramaAcademico`](#vincularpersonaaprogramaacademico) | Vincular a un estudiante o docente con un programa adicional a los que indicó al registrarse. Una persona puede estar en varios programas a la vez (RN-053, RN-061). |
| 2 | [`desvincularPersonaDeProgramaAcademico`](#desvincularpersonadeprogramaacademico) | Terminar el vínculo de un estudiante o docente con un programa. El vínculo no se borra: queda con fecha de desvinculación (RN-057, RN-065). |
| 3 | [`vincularPersonaAlRegistrarse`](#vincularpersonaalregistrarse) | Crear las vinculaciones de un estudiante o docente con los programas que indicó al registrarse o al volver a presentar su registro. Lo hace el sistema; esos programas indican qué Coordinador de Programa debe aprobarlo. |
| 4 | [`desvincularPersonaDeTodosLosProgramas`](#desvincularpersonadetodoslosprogramas) | Cerrar con fecha todas las vinculaciones vigentes de una persona cuando se rechaza su registro o se desactiva su cuenta. Lo hace el sistema; al suspender no se tocan. |
| 5 | [`consultarVinculacionesAcademicas`](#consultarvinculacionesacademicas) | Consultar en qué programas está una persona o qué estudiantes y docentes tiene un programa, incluido el historial (RF-027, RF-028, RF-032, RF-033). |

### `vincularPersonaAProgramaAcademico`

Vincular a un estudiante o docente con un programa adicional a los que indicó al registrarse. Una persona puede estar en varios programas a la vez (RN-053, RN-061).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{usuario, programaAcademico, fechaVinculacion}` | [VinculacionAcademica](VinculacionAcademica.md) | Persona, programa y fecha desde la que queda vinculada. |

**Salida:** [VinculacionAcademica](VinculacionAcademica.md) — La vinculación vigente creada.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0007](Usuario.md#p-usu-0007) | Asegurar que quien ejecuta la acción tenga el rol Coordinador de Programa (RN-112, RN-113). | Solo un Coordinador de Programa puede realizar esta acción. | 🛑 Detener operación |
| 3 | [P-VIN-0004](#p-vin-0004) | Asegurar que la persona, el programa y la fecha de vinculación sean válidos en tipo, obligatoriedad, formato y rango. | Se informa cuál dato no cumple y cómo debe escribirse. | 🛑 Detener operación |
| 4 | [P-PRO-0001](ProgramaAcademico.md#p-pro-0001) | Asegurar que exista un programa académico con el identificador indicado. | No existe el programa académico que se indicó. | 🛑 Detener operación |
| 5 | [P-COP-0009](CoordinacionPrograma.md#p-cop-0009) | Asegurar que quien ejecuta la acción coordine actualmente el programa indicado (RN-112 a RN-115). | Usted no coordina ese programa. | 🛑 Detener operación |
| 6 | [P-USU-0009](Usuario.md#p-usu-0009) | Asegurar que la persona indicada exista y tenga la cuenta Activa. | La persona indicada no existe o su cuenta no está activa. | 🛑 Detener operación |
| 7 | [P-USU-0011](Usuario.md#p-usu-0011) | Asegurar que la persona indicada tenga el rol Estudiante o Docente (RN-053, RN-061). | Solo se pueden vincular a un programa estudiantes y docentes. | 🛑 Detener operación |
| 8 | [P-VIN-0006](#p-vin-0006) | Asegurar que la persona no tenga ya un vínculo vigente con el mismo programa. | La persona ya está vinculada a ese programa. | 🛑 Detener operación |
| 9 | [P-VIN-0007](#p-vin-0007) | Asegurar que la persona no se vincule dos veces al mismo programa en la misma fecha. | Ya existe esa vinculación. | 🛑 Detener operación |
| 10 | [P-VIN-0008](#p-vin-0008) | Asegurar que el identificador de la vinculación sea único dentro de las vinculaciones académicas; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 11 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `desvincularPersonaDeProgramaAcademico`

Terminar el vínculo de un estudiante o docente con un programa. El vínculo no se borra: queda con fecha de desvinculación (RN-057, RN-065).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la vinculación. |
| `datos{fechaDesvinculacion}` | [VinculacionAcademica](VinculacionAcademica.md) | Fecha desde la que deja de estar vinculada. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0007](Usuario.md#p-usu-0007) | Asegurar que quien ejecuta la acción tenga el rol Coordinador de Programa (RN-112, RN-113). | Solo un Coordinador de Programa puede realizar esta acción. | 🛑 Detener operación |
| 3 | [P-VIN-0003](#p-vin-0003) | Asegurar que exista una vinculación académica con el identificador indicado. | No existe la vinculación académica que se indicó. | 🛑 Detener operación |
| 4 | [P-COP-0009](CoordinacionPrograma.md#p-cop-0009) | Asegurar que quien ejecuta la acción coordine actualmente el programa indicado (RN-112 a RN-115). | Usted no coordina ese programa. | 🛑 Detener operación |
| 5 | [P-VIN-0009](#p-vin-0009) | Asegurar que el vínculo esté vigente (sin fecha de desvinculación) antes de desvincular. | La persona ya estaba desvinculada de ese programa. | 🛑 Detener operación |
| 6 | [P-VIN-0005](#p-vin-0005) | Asegurar que la fecha de desvinculación sea válida y sea igual o posterior a la fecha de vinculación. | La fecha de desvinculación no puede ser anterior a la de vinculación. | 🛑 Detener operación |
| 7 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `vincularPersonaAlRegistrarse`

Crear las vinculaciones de un estudiante o docente con los programas que indicó al registrarse o al volver a presentar su registro. Lo hace el sistema; esos programas indican qué Coordinador de Programa debe aprobarlo.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{usuario, fechaVinculacion}` | [VinculacionAcademica](VinculacionAcademica.md) | Persona registrada y fecha de registro. |
| `programas` | [Lista&lt;ProgramaAcademico&gt;](ProgramaAcademico.md) | Programas que indicó. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-VIN-0001](#p-vin-0001) | Cuando un estudiante o docente se registre o vuelva a presentar su registro, crear una vinculación con cada programa que indicó, con la fecha de registro como fecha de vinculación (RN-053, RN-056, RN-061, RN-064). | No se pudieron crear las vinculaciones; se deshace toda la operación. | 🛑 Detener operación |
| 2 | [P-PRO-0001](ProgramaAcademico.md#p-pro-0001) | Asegurar que exista un programa académico con el identificador indicado. | No existe el programa académico que se indicó. | 🛑 Detener operación |
| 3 | [P-VIN-0006](#p-vin-0006) | Asegurar que la persona no tenga ya un vínculo vigente con el mismo programa. | La persona ya está vinculada a ese programa. | 🛑 Detener operación |
| 4 | [P-VIN-0008](#p-vin-0008) | Asegurar que el identificador de la vinculación sea único dentro de las vinculaciones académicas; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |

### `desvincularPersonaDeTodosLosProgramas`

Cerrar con fecha todas las vinculaciones vigentes de una persona cuando se rechaza su registro o se desactiva su cuenta. Lo hace el sistema; al suspender no se tocan.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{usuario}` | [VinculacionAcademica](VinculacionAcademica.md) | Persona rechazada o desactivada. |
| `datos{fechaDesvinculacion}` | [VinculacionAcademica](VinculacionAcademica.md) | Fecha del rechazo o de la desactivación. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-VIN-0009](#p-vin-0009) | Asegurar que el vínculo esté vigente (sin fecha de desvinculación) antes de desvincular. | La persona ya estaba desvinculada de ese programa. | 🛑 Detener operación |
| 2 | [P-VIN-0002](#p-vin-0002) | Cuando se rechace el registro o se desactive la cuenta de un estudiante o docente, asignar la fecha de desvinculación a todas sus vinculaciones vigentes; el vínculo no se borra (RN-057, RN-065). Al suspender la cuenta no se tocan. | No se pudieron cerrar las vinculaciones; se deshace toda la operación. | 🛑 Detener operación |

### `consultarVinculacionesAcademicas`

Consultar en qué programas está una persona o qué estudiantes y docentes tiene un programa, incluido el historial (RF-027, RF-028, RF-032, RF-033).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{usuario, programaAcademico}` | [VinculacionAcademica](VinculacionAcademica.md) | Datos opcionales para filtrar la consulta. |

**Salida:** [Lista&lt;VinculacionAcademica&gt;](VinculacionAcademica.md) — Las vinculaciones vigentes e históricas que cumplen el filtro.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Coordinador de Programa | **Vincular al estudiante o docente con el programa**<br>Vincular a un estudiante o docente con un programa adicional a los que indicó al registrarse. Una persona puede estar en varios programas a la vez (RN-053, RN-061). | **Estudiantes y docentes con cuenta activa**: Para elegir a quién se vincula.<br>**Programa que coordina quien ejecuta**: Para vincular solo en su propio programa. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0007](Usuario.md#p-usu-0007)<br>[P-VIN-0004](#p-vin-0004)<br>[P-PRO-0001](ProgramaAcademico.md#p-pro-0001)<br>[P-COP-0009](CoordinacionPrograma.md#p-cop-0009)<br>[P-USU-0009](Usuario.md#p-usu-0009)<br>[P-USU-0011](Usuario.md#p-usu-0011)<br>[P-VIN-0006](#p-vin-0006)<br>[P-VIN-0007](#p-vin-0007)<br>[P-VIN-0008](#p-vin-0008)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Estudiante o docente vinculado a Programa** | Ninguno |
| Coordinador de Programa | **Desvincular al estudiante o docente del programa**<br>Terminar el vínculo de un estudiante o docente con un programa. El vínculo no se borra: queda con fecha de desvinculación (RN-057, RN-065). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0007](Usuario.md#p-usu-0007)<br>[P-VIN-0003](#p-vin-0003)<br>[P-COP-0009](CoordinacionPrograma.md#p-cop-0009)<br>[P-VIN-0009](#p-vin-0009)<br>[P-VIN-0005](#p-vin-0005)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Estudiante o docente desvinculado de Programa** | Ninguno |
| Sistema | **Vincular a la persona con sus programas al registrarse**<br>Crear las vinculaciones de un estudiante o docente con los programas que indicó al registrarse o al volver a presentar su registro. Lo hace el sistema; esos programas indican qué Coordinador de Programa debe aprobarlo. | **No aplica**: No aplica | [P-VIN-0001](#p-vin-0001)<br>[P-PRO-0001](ProgramaAcademico.md#p-pro-0001)<br>[P-VIN-0006](#p-vin-0006)<br>[P-VIN-0008](#p-vin-0008) | No aplica | 🟧 **Estudiante o docente vinculado a Programa** | Ninguno |
| Sistema | **Desvincular a la persona de todos sus programas**<br>Cerrar con fecha todas las vinculaciones vigentes de una persona cuando se rechaza su registro o se desactiva su cuenta. Lo hace el sistema; al suspender no se tocan. | **No aplica**: No aplica | [P-VIN-0009](#p-vin-0009)<br>[P-VIN-0002](#p-vin-0002) | No aplica | 🟧 **Estudiante o docente desvinculado de Programa** | Ninguno |
| Usuario autenticado | **Consultar las vinculaciones académicas**<br>Consultar en qué programas está una persona o qué estudiantes y docentes tiene un programa, incluido el historial (RF-027, RF-028, RF-032, RF-033). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001) | No aplica | 🟧 **Vinculaciones académicas consultadas** | Ninguno |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="p-vin-0001"></a>**P-VIN-0001** | Cuando un estudiante o docente se registre o vuelva a presentar su registro, crear una vinculación con cada programa que indicó, con la fecha de registro como fecha de vinculación (RN-053, RN-056, RN-061, RN-064). | VinculacionAcademica | `VincularProgramasAlRegistrarseRule` |
| <a id="p-vin-0002"></a>**P-VIN-0002** | Cuando se rechace el registro o se desactive la cuenta de un estudiante o docente, asignar la fecha de desvinculación a todas sus vinculaciones vigentes; el vínculo no se borra (RN-057, RN-065). Al suspender la cuenta no se tocan. | VinculacionAcademica | `DesvincularAlRechazarODesactivarRule` |
| <a id="p-vin-0003"></a>**P-VIN-0003** | Asegurar que exista una vinculación académica con el identificador indicado. | VinculacionAcademica | `AsegurarVinculacionAcademicaExisteRule` |
| <a id="p-vin-0004"></a>**P-VIN-0004** | Asegurar que la persona, el programa y la fecha de vinculación sean válidos en tipo, obligatoriedad, formato y rango. | VinculacionAcademica | `AsegurarFormatoVinculacionAcademicaValidoRule` |
| <a id="p-vin-0005"></a>**P-VIN-0005** | Asegurar que la fecha de desvinculación sea válida y sea igual o posterior a la fecha de vinculación. | VinculacionAcademica | `AsegurarFormatoDesvinculacionValidoRule` |
| <a id="p-vin-0006"></a>**P-VIN-0006** | Asegurar que la persona no tenga ya un vínculo vigente con el mismo programa. | VinculacionAcademica | `AsegurarPersonaSinVinculoVigenteEnProgramaRule` |
| <a id="p-vin-0007"></a>**P-VIN-0007** | Asegurar que la persona no se vincule dos veces al mismo programa en la misma fecha. | VinculacionAcademica | `AsegurarVinculacionAcademicaNoRepetidaRule` |
| <a id="p-vin-0008"></a>**P-VIN-0008** | Asegurar que el identificador de la vinculación sea único dentro de las vinculaciones académicas; si ya existe, generar otro. | VinculacionAcademica | `AsegurarIdVinculacionAcademicaUnicoRule` |
| <a id="p-vin-0009"></a>**P-VIN-0009** | Asegurar que el vínculo esté vigente (sin fecha de desvinculación) antes de desvincular. | VinculacionAcademica | `AsegurarVinculacionAcademicaVigenteRule` |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ CoordinacionPrograma](CoordinacionPrograma.md) · [Kudo ➡️](Kudo.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/VinculacionAcademica.md) · [📐 Clase](../clases/VinculacionAcademica.md)
