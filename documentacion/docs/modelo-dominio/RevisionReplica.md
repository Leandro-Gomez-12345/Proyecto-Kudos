# 🧩 RevisionReplica

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ CalificacionReplica](CalificacionReplica.md) · [ReporteKudo ➡️](ReporteKudo.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/RevisionReplica.md) · [📐 Clase](../clases/RevisionReplica.md)

> Revisión de moderación sobre una réplica, hecha por el Rector o el Coordinador Académico. Resuelve a la vez todos sus reportes y termina vetando o liberando el contenido; un veto directo es una revisión sin reportes.

| Línea de vida | Atributos | Responsabilidades | Eventos | Políticas propias |
| :---: | :---: | :---: | :---: | :---: |
| Nivel 5 | 7 | 5 | 5 | 11 |

## Contenido

1. [Muestreo](#1-muestreo)
2. [Caracterización](#2-caracterización)
3. [Responsabilidades](#3-responsabilidades)
4. [Event Storming](#4-event-storming)
5. [Políticas](#5-políticas)

---

## 1. Muestreo

Ejemplos reales del objeto para validar sus atributos con el negocio.

| **id** | **replica** | **moderador** | **estadoRevision** | **justificacion** | **fechaInicio** | **fechaCierre** | **Combinación única** |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | mateo.giraldo@uco.edu.co-25/09/2026 12:00 | ana.gomez@uco.edu.co | Contenido vetado | La réplica es irrespetuosa con la persona reconocida. | 26/09/2026 08:00 | 26/09/2026 08:20 | mateo.giraldo@uco.edu.co-25/09/2026 12:00-26/09/2026 08:00 |
| 2 | diego.montoya@uco.edu.co-15/04/2026 10:00 | jorge.ramirez@uco.edu.co | Contenido liberado | La réplica no es spam; es una felicitación legítima. | 16/04/2026 09:00 | 16/04/2026 09:10 | diego.montoya@uco.edu.co-15/04/2026 10:00-16/04/2026 09:00 |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único de la revisión dentro del sistema. |
| `replica` | Representa una réplica que se revisa. |
| `moderador` | Representa la autoridad (Rector o Coordinador Académico) que inicia la revisión. Quien la cierra puede ser cualquiera de las dos y queda en la Auditoría. |
| `estadoRevision` | Representa si la revisión sigue en curso o cómo se cerró. |
| `justificacion` | Representa la razón de la decisión, necesaria para reconstruir la moderación. |
| `fechaInicio` | Representa la fecha y hora en que se abrió la revisión. |
| `fechaCierre` | Representa la fecha y hora en que se tomó la decisión; vacía mientras la revisión está en curso. |
| `Combinación única` | Representa que una réplica no puede tener dos revisiones iniciadas en la misma fecha y hora. |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único de la revisión dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `replica` | Representa una réplica que se revisa. | [Replica](Replica.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Réplica). Solo puede tener una revisión En curso a la vez. |
| `moderador` | Representa la autoridad (Rector o Coordinador Académico) que inicia la revisión. Quien la cierra puede ser cualquiera de las dos y queda en la Auditoría. | [Usuario](Usuario.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Usuario). Lo asigna el sistema con el usuario autenticado; solo Rector o Coordinador Académico. |
| `estadoRevision` | Representa si la revisión sigue en curso o cómo se cerró. | [EstadoRevision](EstadoRevision.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Estado Revisión). Inicia En curso y cambia una sola vez a Contenido vetado o Contenido liberado. |
| `justificacion` | Representa la razón de la decisión, necesaria para reconstruir la moderación. | Alfanumérico | No | 10 | 1000 | Texto libre. | Obligatoria al cerrar la revisión. Letras, números, tildes, espacios y signos de puntuación. |
| `fechaInicio` | Representa la fecha y hora en que se abrió la revisión. | Fecha hora | Si | No aplica | No aplica | No aplica | La asigna el sistema al iniciar la revisión y no cambia. |
| `fechaCierre` | Representa la fecha y hora en que se tomó la decisión; vacía mientras la revisión está en curso. | Fecha hora | No | No aplica | No aplica | No aplica | La asigna el sistema al cerrar la revisión. Posterior a la fecha de inicio. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `replica` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `moderador` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `estadoRevision` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `justificacion` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |
| `fechaInicio` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | 01/01/1000 00:00 | Si |
| `fechaCierre` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | 01/01/1000 00:00 | No |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única | Representa que una réplica no puede tener dos revisiones iniciadas en la misma fecha y hora. | `replica, fechaInicio` | Compara exactamente. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`iniciarRevisionReplica`](#iniciarrevisionreplica) | [`cerrarRevisionReplicaConVeto`](#cerrarrevisionreplicaconveto) | [`cerrarRevisionReplicaConLiberacion`](#cerrarrevisionreplicaconliberacion) | [`vetarReplicaDirectamente`](#vetarreplicadirectamente) | [`consultarRevisionesReplica`](#consultarrevisionesreplica) |
| --- | :---: | :---: | :---: | :---: | :---: |
| `id` | NR | R·NM | R·NM | NR | 👁 |
| `replica` | R | NR·NM | NR·NM | R | 👁 |
| `moderador` | NR | NR·NM | NR·NM | NR | 👁 |
| `estadoRevision` | NR | NR·M | NR·M | NR | 👁 |
| `justificacion` | NR | R·M | R·M | R | 👁 |
| `fechaInicio` | NR | NR·NM | NR·NM | NR | 👁 |
| `fechaCierre` | NR | NR·M | NR·M | NR | 👁 |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`iniciarRevisionReplica`](#iniciarrevisionreplica) | Tomar una réplica reportada para analizarla. La réplica pasa a En revisión y sus reportes pendientes quedan enlazados a esta revisión, por lo que se ven En revisión. |
| 2 | [`cerrarRevisionReplicaConVeto`](#cerrarrevisionreplicaconveto) | Decidir que la réplica incumple las políticas institucionales: la revisión se cierra, la réplica queda Vetada y sus reportes se ven Aceptados porque su revisión terminó en veto. La puede cerrar el Rector o el Coordinador Académico aunque no la haya iniciado (quien cierra queda en la Auditoría) y el veto es definitivo. Así se veta sin atributos de veto en la réplica. |
| 3 | [`cerrarRevisionReplicaConLiberacion`](#cerrarrevisionreplicaconliberacion) | Decidir que la réplica no incumple las políticas: la revisión se cierra, la réplica vuelve a Visible y sus reportes se ven Rechazados porque su revisión terminó en liberación. |
| 4 | [`vetarReplicaDirectamente`](#vetarreplicadirectamente) | Vetar una réplica por decisión de la autoridad aunque nadie la haya reportado. Queda registrado como una revisión que nace cerrada; si tenía reportes pendientes, quedan enlazados a ella y se ven Aceptados. |
| 5 | [`consultarRevisionesReplica`](#consultarrevisionesreplica) | Consultar las revisiones en curso y el histórico de decisiones de moderación sobre las réplicas (CU-015, RF-068). |

### `iniciarRevisionReplica`

Tomar una réplica reportada para analizarla. La réplica pasa a En revisión y sus reportes pendientes quedan enlazados a esta revisión, por lo que se ven En revisión.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{replica}` | [RevisionReplica](RevisionReplica.md) | La réplica que se va a revisar. |

**Salida:** [RevisionReplica](RevisionReplica.md) — La revisión abierta, En curso.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0003](Usuario.md#p-usu-0003) | Asegurar que quien ejecuta la acción tenga el rol Rector o Coordinador Académico (RN-193 a RN-200, RN-243, INV-033). | Solo el Rector y el Coordinador Académico pueden moderar contenido. | 🛑 Detener operación |
| 3 | [P-REP-0001](Replica.md#p-rep-0001) | Asegurar que exista una réplica con el identificador indicado. | No existe la réplica que se indicó. | 🛑 Detener operación |
| 4 | [P-REP-0004](Replica.md#p-rep-0004) | Asegurar que la réplica esté Reportada antes de iniciar su revisión (RN-208). | La réplica no tiene reportes pendientes por revisar. | 🛑 Detener operación |
| 5 | [P-RVR-0002](#p-rvr-0002) | Asegurar que la réplica no tenga ya una revisión En curso. | La réplica ya tiene una revisión en curso. | 🛑 Detener operación |
| 6 | [P-RVR-0003](#p-rvr-0003) | Asegurar que la réplica no tenga otra revisión iniciada en la misma fecha y hora. | Ya existe esa revisión. | 🛑 Detener operación |
| 7 | [P-RVR-0004](#p-rvr-0004) | Asegurar que el identificador de la revisión sea único dentro de las revisiones; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 8 | [P-RVR-0005](#p-rvr-0005) | Asignar a la revisión el moderador (quien la inicia), la fecha de inicio y el estado En curso. | No se pudo iniciar la revisión; se deshace toda la operación. | 🛑 Detener operación |
| 9 | [P-REP-0009](Replica.md#p-rep-0009) | Cuando se inicie una revisión, pasar la réplica de Reportada a En revisión (RN-208). | No se pudo actualizar el estado; se deshace toda la operación. | 🛑 Detener operación |
| 10 | [P-RPR-0005](ReporteReplica.md#p-rpr-0005) | Cuando se inicie una revisión de la réplica (o se vete directamente), enlazar con esa revisión todos sus reportes que aún no tienen revisión (RN-211). | No se pudieron enlazar los reportes; se deshace toda la operación. | 🛑 Detener operación |
| 11 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `cerrarRevisionReplicaConVeto`

Decidir que la réplica incumple las políticas institucionales: la revisión se cierra, la réplica queda Vetada y sus reportes se ven Aceptados porque su revisión terminó en veto. La puede cerrar el Rector o el Coordinador Académico aunque no la haya iniciado (quien cierra queda en la Auditoría) y el veto es definitivo. Así se veta sin atributos de veto en la réplica.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la revisión. |
| `datos{justificacion}` | [RevisionReplica](RevisionReplica.md) | Razón institucional del veto. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0003](Usuario.md#p-usu-0003) | Asegurar que quien ejecuta la acción tenga el rol Rector o Coordinador Académico (RN-193 a RN-200, RN-243, INV-033). | Solo el Rector y el Coordinador Académico pueden moderar contenido. | 🛑 Detener operación |
| 3 | [P-RVR-0001](#p-rvr-0001) | Asegurar que exista una revisión con el identificador indicado. | No existe la revisión que se indicó. | 🛑 Detener operación |
| 4 | [P-RVR-0006](#p-rvr-0006) | Asegurar que la revisión esté En curso; una revisión se cierra una sola vez. | La revisión ya fue cerrada. | 🛑 Detener operación |
| 5 | [P-RVR-0007](#p-rvr-0007) | Asegurar que la justificación de la decisión sea válida en longitud, obligatoriedad y formato (RN-250). | Debe escribir la justificación de la decisión (entre 10 y 1000 caracteres). | 🛑 Detener operación |
| 6 | [P-RVR-0008](#p-rvr-0008) | Asignar a la revisión la justificación, la fecha de cierre y el estado Contenido vetado. | No se pudo cerrar la revisión; se deshace toda la operación. | 🛑 Detener operación |
| 7 | [P-REP-0010](Replica.md#p-rep-0010) | Cuando una revisión se cierre con veto, o se vete directamente, pasar la réplica a Vetada (RN-209). | No se pudo vetar el contenido; se deshace toda la operación. | 🛑 Detener operación |
| 8 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `cerrarRevisionReplicaConLiberacion`

Decidir que la réplica no incumple las políticas: la revisión se cierra, la réplica vuelve a Visible y sus reportes se ven Rechazados porque su revisión terminó en liberación.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la revisión. |
| `datos{justificacion}` | [RevisionReplica](RevisionReplica.md) | Razón por la que el contenido se mantiene. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0003](Usuario.md#p-usu-0003) | Asegurar que quien ejecuta la acción tenga el rol Rector o Coordinador Académico (RN-193 a RN-200, RN-243, INV-033). | Solo el Rector y el Coordinador Académico pueden moderar contenido. | 🛑 Detener operación |
| 3 | [P-RVR-0001](#p-rvr-0001) | Asegurar que exista una revisión con el identificador indicado. | No existe la revisión que se indicó. | 🛑 Detener operación |
| 4 | [P-RVR-0006](#p-rvr-0006) | Asegurar que la revisión esté En curso; una revisión se cierra una sola vez. | La revisión ya fue cerrada. | 🛑 Detener operación |
| 5 | [P-RVR-0007](#p-rvr-0007) | Asegurar que la justificación de la decisión sea válida en longitud, obligatoriedad y formato (RN-250). | Debe escribir la justificación de la decisión (entre 10 y 1000 caracteres). | 🛑 Detener operación |
| 6 | [P-RVR-0009](#p-rvr-0009) | Asignar a la revisión la justificación, la fecha de cierre y el estado Contenido liberado. | No se pudo cerrar la revisión; se deshace toda la operación. | 🛑 Detener operación |
| 7 | [P-REP-0011](Replica.md#p-rep-0011) | Cuando una revisión se cierre liberando el contenido, devolver la réplica a Visible (RN-242). | No se pudo liberar el contenido; se deshace toda la operación. | 🛑 Detener operación |
| 8 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `vetarReplicaDirectamente`

Vetar una réplica por decisión de la autoridad aunque nadie la haya reportado. Queda registrado como una revisión que nace cerrada; si tenía reportes pendientes, quedan enlazados a ella y se ven Aceptados.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{replica, justificacion}` | [RevisionReplica](RevisionReplica.md) | La réplica que se veta y razón institucional del veto. |

**Salida:** [RevisionReplica](RevisionReplica.md) — La revisión cerrada que deja constancia del veto.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0003](Usuario.md#p-usu-0003) | Asegurar que quien ejecuta la acción tenga el rol Rector o Coordinador Académico (RN-193 a RN-200, RN-243, INV-033). | Solo el Rector y el Coordinador Académico pueden moderar contenido. | 🛑 Detener operación |
| 3 | [P-RVR-0011](#p-rvr-0011) | Asegurar que la réplica y la justificación del veto directo sean válidos en tipo, longitud, obligatoriedad y formato. | Debe indicar el contenido y escribir la justificación del veto. | 🛑 Detener operación |
| 4 | [P-REP-0001](Replica.md#p-rep-0001) | Asegurar que exista una réplica con el identificador indicado. | No existe la réplica que se indicó. | 🛑 Detener operación |
| 5 | [P-REP-0006](Replica.md#p-rep-0006) | Asegurar que la réplica esté Visible o Reportada para vetarla directamente; si está En revisión se cierra su revisión y si ya está Vetada no se repite. | La réplica ya está vetada o tiene una revisión en curso. | 🛑 Detener operación |
| 6 | [P-RVR-0003](#p-rvr-0003) | Asegurar que la réplica no tenga otra revisión iniciada en la misma fecha y hora. | Ya existe esa revisión. | 🛑 Detener operación |
| 7 | [P-RVR-0004](#p-rvr-0004) | Asegurar que el identificador de la revisión sea único dentro de las revisiones; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 8 | [P-RVR-0010](#p-rvr-0010) | Cuando la autoridad vete directamente, registrar una revisión que nace cerrada: moderador, justificación, fecha de inicio igual a la de cierre y estado Contenido vetado (CU-013, CU-014). | No se pudo registrar el veto; se deshace toda la operación. | 🛑 Detener operación |
| 9 | [P-REP-0010](Replica.md#p-rep-0010) | Cuando una revisión se cierre con veto, o se vete directamente, pasar la réplica a Vetada (RN-209). | No se pudo vetar el contenido; se deshace toda la operación. | 🛑 Detener operación |
| 10 | [P-RPR-0005](ReporteReplica.md#p-rpr-0005) | Cuando se inicie una revisión de la réplica (o se vete directamente), enlazar con esa revisión todos sus reportes que aún no tienen revisión (RN-211). | No se pudieron enlazar los reportes; se deshace toda la operación. | 🛑 Detener operación |
| 11 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `consultarRevisionesReplica`

Consultar las revisiones en curso y el histórico de decisiones de moderación sobre las réplicas (CU-015, RF-068).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{replica, moderador, estadoRevision}` | [RevisionReplica](RevisionReplica.md) | Datos opcionales para filtrar la consulta. |

**Salida:** [Lista&lt;RevisionReplica&gt;](RevisionReplica.md) — Las revisiones que cumplen el filtro.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0003](Usuario.md#p-usu-0003) | Asegurar que quien ejecuta la acción tenga el rol Rector o Coordinador Académico (RN-193 a RN-200, RN-243, INV-033). | Solo el Rector y el Coordinador Académico pueden moderar contenido. | 🛑 Detener operación |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Rector<br>Coordinador Académico | **Iniciar la revisión de la réplica**<br>Tomar una réplica reportada para analizarla. La réplica pasa a En revisión y sus reportes pendientes quedan enlazados a esta revisión, por lo que se ven En revisión. | **Reportes pendientes de la réplica**: Para conocer los motivos por los que se reportó.<br>**Contenido de la réplica**: Para leer lo que se va a revisar. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0003](Usuario.md#p-usu-0003)<br>[P-REP-0001](Replica.md#p-rep-0001)<br>[P-REP-0004](Replica.md#p-rep-0004)<br>[P-RVR-0002](#p-rvr-0002)<br>[P-RVR-0003](#p-rvr-0003)<br>[P-RVR-0004](#p-rvr-0004)<br>[P-RVR-0005](#p-rvr-0005)<br>[P-REP-0009](Replica.md#p-rep-0009)<br>[P-RPR-0005](ReporteReplica.md#p-rpr-0005)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Revisión iniciada** | Ninguno |
| Rector<br>Coordinador Académico | **Vetar la réplica revisada**<br>Decidir que la réplica incumple las políticas institucionales: la revisión se cierra, la réplica queda Vetada y sus reportes se ven Aceptados porque su revisión terminó en veto. La puede cerrar el Rector o el Coordinador Académico aunque no la haya iniciado (quien cierra queda en la Auditoría) y el veto es definitivo. Así se veta sin atributos de veto en la réplica. | **Reportes de la revisión**: Para sustentar la decisión. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0003](Usuario.md#p-usu-0003)<br>[P-RVR-0001](#p-rvr-0001)<br>[P-RVR-0006](#p-rvr-0006)<br>[P-RVR-0007](#p-rvr-0007)<br>[P-RVR-0008](#p-rvr-0008)<br>[P-REP-0010](Replica.md#p-rep-0010)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Revisión cerrada: contenido vetado** | Ninguno |
| Rector<br>Coordinador Académico | **Liberar la réplica revisada**<br>Decidir que la réplica no incumple las políticas: la revisión se cierra, la réplica vuelve a Visible y sus reportes se ven Rechazados porque su revisión terminó en liberación. | **Reportes de la revisión**: Para sustentar la decisión. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0003](Usuario.md#p-usu-0003)<br>[P-RVR-0001](#p-rvr-0001)<br>[P-RVR-0006](#p-rvr-0006)<br>[P-RVR-0007](#p-rvr-0007)<br>[P-RVR-0009](#p-rvr-0009)<br>[P-REP-0011](Replica.md#p-rep-0011)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Revisión cerrada: contenido liberado** | Ninguno |
| Rector<br>Coordinador Académico | **Vetar la réplica directamente**<br>Vetar una réplica por decisión de la autoridad aunque nadie la haya reportado. Queda registrado como una revisión que nace cerrada; si tenía reportes pendientes, quedan enlazados a ella y se ven Aceptados. | **Contenido de la réplica**: Para leer lo que se va a vetar. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0003](Usuario.md#p-usu-0003)<br>[P-RVR-0011](#p-rvr-0011)<br>[P-REP-0001](Replica.md#p-rep-0001)<br>[P-REP-0006](Replica.md#p-rep-0006)<br>[P-RVR-0003](#p-rvr-0003)<br>[P-RVR-0004](#p-rvr-0004)<br>[P-RVR-0010](#p-rvr-0010)<br>[P-REP-0010](Replica.md#p-rep-0010)<br>[P-RPR-0005](ReporteReplica.md#p-rpr-0005)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Réplica vetada directamente** | Ninguno |
| Rector<br>Coordinador Académico | **Consultar el histórico de revisiones y vetos de las réplicas**<br>Consultar las revisiones en curso y el histórico de decisiones de moderación sobre las réplicas (CU-015, RF-068). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0003](Usuario.md#p-usu-0003) | No aplica | 🟧 **Histórico de moderación consultado** | Ninguno |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="p-rvr-0001"></a>**P-RVR-0001** | Asegurar que exista una revisión con el identificador indicado. | RevisionReplica | `AsegurarRevisionReplicaExisteRule` |
| <a id="p-rvr-0002"></a>**P-RVR-0002** | Asegurar que la réplica no tenga ya una revisión En curso. | RevisionReplica | `AsegurarReplicaSinRevisionEnCursoRule` |
| <a id="p-rvr-0003"></a>**P-RVR-0003** | Asegurar que la réplica no tenga otra revisión iniciada en la misma fecha y hora. | RevisionReplica | `AsegurarRevisionReplicaNoRepetidaRule` |
| <a id="p-rvr-0004"></a>**P-RVR-0004** | Asegurar que el identificador de la revisión sea único dentro de las revisiones; si ya existe, generar otro. | RevisionReplica | `AsegurarIdRevisionReplicaUnicoRule` |
| <a id="p-rvr-0005"></a>**P-RVR-0005** | Asignar a la revisión el moderador (quien la inicia), la fecha de inicio y el estado En curso. | RevisionReplica | `AsignarDatosInicialesRevisionReplicaRule` |
| <a id="p-rvr-0006"></a>**P-RVR-0006** | Asegurar que la revisión esté En curso; una revisión se cierra una sola vez. | RevisionReplica | `AsegurarRevisionReplicaEnCursoRule` |
| <a id="p-rvr-0007"></a>**P-RVR-0007** | Asegurar que la justificación de la decisión sea válida en longitud, obligatoriedad y formato (RN-250). | RevisionReplica | `AsegurarFormatoJustificacionRevisionReplicaValidoRule` |
| <a id="p-rvr-0008"></a>**P-RVR-0008** | Asignar a la revisión la justificación, la fecha de cierre y el estado Contenido vetado. | RevisionReplica | `AsignarCierreConVetoRevisionReplicaRule` |
| <a id="p-rvr-0009"></a>**P-RVR-0009** | Asignar a la revisión la justificación, la fecha de cierre y el estado Contenido liberado. | RevisionReplica | `AsignarCierreConLiberacionRevisionReplicaRule` |
| <a id="p-rvr-0010"></a>**P-RVR-0010** | Cuando la autoridad vete directamente, registrar una revisión que nace cerrada: moderador, justificación, fecha de inicio igual a la de cierre y estado Contenido vetado (CU-013, CU-014). | RevisionReplica | `RegistrarVetoDirectoComoRevisionReplicaRule` |
| <a id="p-rvr-0011"></a>**P-RVR-0011** | Asegurar que la réplica y la justificación del veto directo sean válidos en tipo, longitud, obligatoriedad y formato. | RevisionReplica | `AsegurarFormatoVetoDirectoReplicaValidoRule` |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ CalificacionReplica](CalificacionReplica.md) · [ReporteKudo ➡️](ReporteKudo.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/RevisionReplica.md) · [📐 Clase](../clases/RevisionReplica.md)
