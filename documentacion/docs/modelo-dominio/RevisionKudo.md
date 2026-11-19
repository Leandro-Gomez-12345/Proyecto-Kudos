# 🧩 RevisionKudo

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ CalificacionKudo](CalificacionKudo.md) · [CalificacionReplica ➡️](CalificacionReplica.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/RevisionKudo.md) · [📐 Clase](../clases/RevisionKudo.md)

> Revisión de moderación sobre un Kudo, hecha por el Rector o el Coordinador Académico. Resuelve a la vez todos sus reportes y termina vetando o liberando el contenido; un veto directo es una revisión sin reportes.

| Línea de vida | Atributos | Responsabilidades | Eventos | Políticas propias |
| :---: | :---: | :---: | :---: | :---: |
| Nivel 4 | 7 | 5 | 5 | 11 |

## Contenido

1. [Muestreo](#1-muestreo)
2. [Caracterización](#2-caracterización)
3. [Responsabilidades](#3-responsabilidades)
4. [Event Storming](#4-event-storming)
5. [Políticas](#5-políticas)

---

## 1. Muestreo

Ejemplos reales del objeto para validar sus atributos con el negocio.

| **id** | **kudo** | **moderador** | **estadoRevision** | **justificacion** | **fechaInicio** | **fechaCierre** | **Combinación única** |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | mateo.giraldo@uco.edu.co-02/06/2026 16:45 | ana.gomez@uco.edu.co | Contenido vetado | Se verificó con la Facultad de Ingeniería que el premio no existe; la información es falsa. | 03/06/2026 08:00 | 03/06/2026 10:30 | mateo.giraldo@uco.edu.co-02/06/2026 16:45-03/06/2026 08:00 |
| 2 | andres.cardona@uco.edu.co-15/04/2026 09:05 | jorge.ramirez@uco.edu.co | Contenido liberado | El reconocimiento es verídico; el reporte no tiene fundamento. | 16/04/2026 09:00 | 16/04/2026 11:00 | andres.cardona@uco.edu.co-15/04/2026 09:05-16/04/2026 09:00 |
| 3 | carlos.zapata@uco.edu.co-25/09/2026 10:10 | ana.gomez@uco.edu.co | En curso | — | 26/09/2026 09:00 | — | carlos.zapata@uco.edu.co-25/09/2026 10:10-26/09/2026 09:00 |
| 4 | diego.montoya@uco.edu.co-01/10/2026 08:00 | jorge.ramirez@uco.edu.co | Contenido vetado | Veto directo: el mensaje insinúa fraude académico en un parcial. | 01/10/2026 09:00 | 01/10/2026 09:05 | diego.montoya@uco.edu.co-01/10/2026 08:00-01/10/2026 09:00 |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único de la revisión dentro del sistema. |
| `kudo` | Representa un Kudo que se revisa. |
| `moderador` | Representa la autoridad (Rector o Coordinador Académico) que inicia la revisión. Quien la cierra puede ser cualquiera de las dos y queda en la Auditoría. |
| `estadoRevision` | Representa si la revisión sigue en curso o cómo se cerró. |
| `justificacion` | Representa la razón de la decisión, necesaria para reconstruir la moderación. |
| `fechaInicio` | Representa la fecha y hora en que se abrió la revisión. |
| `fechaCierre` | Representa la fecha y hora en que se tomó la decisión; vacía mientras la revisión está en curso. |
| `Combinación única` | Representa que un Kudo no puede tener dos revisiones iniciadas en la misma fecha y hora. |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único de la revisión dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `kudo` | Representa un Kudo que se revisa. | [Kudo](Kudo.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Kudo). Solo puede tener una revisión En curso a la vez. |
| `moderador` | Representa la autoridad (Rector o Coordinador Académico) que inicia la revisión. Quien la cierra puede ser cualquiera de las dos y queda en la Auditoría. | [Usuario](Usuario.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Usuario). Lo asigna el sistema con el usuario autenticado; solo Rector o Coordinador Académico. |
| `estadoRevision` | Representa si la revisión sigue en curso o cómo se cerró. | [EstadoRevision](EstadoRevision.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Estado Revisión). Inicia En curso y cambia una sola vez a Contenido vetado o Contenido liberado. |
| `justificacion` | Representa la razón de la decisión, necesaria para reconstruir la moderación. | Alfanumérico | No | 10 | 1000 | Texto libre. | Obligatoria al cerrar la revisión. Solo se permirmite letras mayusculas y musculas con y sin tilde, números, espacios y signos de puntuación. |
| `fechaInicio` | Representa la fecha y hora en que se abrió la revisión. | Fecha hora | Si | No aplica | No aplica | No aplica | La asigna el sistema al iniciar la revisión y no cambia. |
| `fechaCierre` | Representa la fecha y hora en que se tomó la decisión; vacía mientras la revisión está en curso. | Fecha hora | No | No aplica | No aplica | No aplica | La asigna el sistema al cerrar la revisión. Posterior a la fecha de inicio. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `kudo` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `moderador` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `estadoRevision` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `justificacion` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |
| `fechaInicio` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | 01/01/1000 00:00 | Si |
| `fechaCierre` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | 01/01/1000 00:00 | No |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única | Representa que un Kudo no puede tener dos revisiones iniciadas en la misma fecha y hora. | `kudo, fechaInicio` | Compara exactamente. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`iniciarRevisionKudo`](#iniciarrevisionkudo) | [`cerrarRevisionKudoConVeto`](#cerrarrevisionkudoconveto) | [`cerrarRevisionKudoConLiberacion`](#cerrarrevisionkudoconliberacion) | [`vetarKudoDirectamente`](#vetarkudodirectamente) | [`consultarRevisionesKudo`](#consultarrevisioneskudo) |
| --- | :---: | :---: | :---: | :---: | :---: |
| `id` | NR | R·NM | R·NM | NR | 👁 |
| `kudo` | R | NR·NM | NR·NM | R | 👁 |
| `moderador` | NR | NR·NM | NR·NM | NR | 👁 |
| `estadoRevision` | NR | NR·M | NR·M | NR | 👁 |
| `justificacion` | NR | R·M | R·M | R | 👁 |
| `fechaInicio` | NR | NR·NM | NR·NM | NR | 👁 |
| `fechaCierre` | NR | NR·M | NR·M | NR | 👁 |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`iniciarRevisionKudo`](#iniciarrevisionkudo) | Tomar un Kudo reportado para analizarlo. El Kudo pasa a En revisión y sus reportes pendientes quedan enlazados a esta revisión, por lo que se ven En revisión. |
| 2 | [`cerrarRevisionKudoConVeto`](#cerrarrevisionkudoconveto) | Decidir que el Kudo incumple las políticas institucionales: la revisión se cierra, el Kudo queda Vetado y sus reportes se ven Aceptados porque su revisión terminó en veto. La puede cerrar el Rector o el Coordinador Académico aunque no la haya iniciado (quien cierra queda en la Auditoría) y el veto es definitivo. Así se veta sin atributos de veto en el Kudo. |
| 3 | [`cerrarRevisionKudoConLiberacion`](#cerrarrevisionkudoconliberacion) | Decidir que el Kudo no incumple las políticas: la revisión se cierra, el Kudo vuelve a Visible y sus reportes se ven Rechazados porque su revisión terminó en liberación. |
| 4 | [`vetarKudoDirectamente`](#vetarkudodirectamente) | Vetar un Kudo por decisión de la autoridad aunque nadie lo haya reportado. Queda registrado como una revisión que nace cerrada; si tenía reportes pendientes, quedan enlazados a ella y se ven Aceptados. |
| 5 | [`consultarRevisionesKudo`](#consultarrevisioneskudo) | Consultar las revisiones en curso y el histórico de decisiones de moderación sobre los Kudos (CU-015, RF-068). |

### `iniciarRevisionKudo`

Tomar un Kudo reportado para analizarlo. El Kudo pasa a En revisión y sus reportes pendientes quedan enlazados a esta revisión, por lo que se ven En revisión.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{kudo}` | [RevisionKudo](RevisionKudo.md) | El Kudo que se va a revisar. |

**Salida:** [RevisionKudo](RevisionKudo.md) — La revisión abierta, En curso.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0003](Usuario.md#p-usu-0003) | Asegurar que quien ejecuta la acción tenga el rol Rector o Coordinador Académico (RN-193 a RN-200, RN-243, INV-033). | Solo el Rector y el Coordinador Académico pueden moderar contenido. | 🛑 Detener operación |
| 3 | [P-KUD-0001](Kudo.md#p-kud-0001) | Asegurar que exista un Kudo con el identificador indicado. | No existe el Kudo que se indicó. | 🛑 Detener operación |
| 4 | [P-KUD-0004](Kudo.md#p-kud-0004) | Asegurar que el Kudo esté Reportado antes de iniciar su revisión (RN-203). | El Kudo no tiene reportes pendientes por revisar. | 🛑 Detener operación |
| 5 | [P-RVK-0002](#p-rvk-0002) | Asegurar que el Kudo no tenga ya una revisión En curso. | El Kudo ya tiene una revisión en curso. | 🛑 Detener operación |
| 6 | [P-RVK-0003](#p-rvk-0003) | Asegurar que el Kudo no tenga otra revisión iniciada en la misma fecha y hora. | Ya existe esa revisión. | 🛑 Detener operación |
| 7 | [P-RVK-0004](#p-rvk-0004) | Asegurar que el identificador de la revisión sea único dentro de las revisiones; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 8 | [P-RVK-0005](#p-rvk-0005) | Asignar a la revisión el moderador (quien la inicia), la fecha de inicio y el estado En curso. | No se pudo iniciar la revisión; se deshace toda la operación. | 🛑 Detener operación |
| 9 | [P-KUD-0009](Kudo.md#p-kud-0009) | Cuando se inicie una revisión, pasar el Kudo de Reportado a En revisión (RN-203). | No se pudo actualizar el estado; se deshace toda la operación. | 🛑 Detener operación |
| 10 | [P-RPK-0005](ReporteKudo.md#p-rpk-0005) | Cuando se inicie una revisión del Kudo (o se vete directamente), enlazar con esa revisión todos sus reportes que aún no tienen revisión (RN-211). | No se pudieron enlazar los reportes; se deshace toda la operación. | 🛑 Detener operación |
| 11 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `cerrarRevisionKudoConVeto`

Decidir que el Kudo incumple las políticas institucionales: la revisión se cierra, el Kudo queda Vetado y sus reportes se ven Aceptados porque su revisión terminó en veto. La puede cerrar el Rector o el Coordinador Académico aunque no la haya iniciado (quien cierra queda en la Auditoría) y el veto es definitivo. Así se veta sin atributos de veto en el Kudo.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la revisión. |
| `datos{justificacion}` | [RevisionKudo](RevisionKudo.md) | Razón institucional del veto. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0003](Usuario.md#p-usu-0003) | Asegurar que quien ejecuta la acción tenga el rol Rector o Coordinador Académico (RN-193 a RN-200, RN-243, INV-033). | Solo el Rector y el Coordinador Académico pueden moderar contenido. | 🛑 Detener operación |
| 3 | [P-RVK-0001](#p-rvk-0001) | Asegurar que exista una revisión con el identificador indicado. | No existe la revisión que se indicó. | 🛑 Detener operación |
| 4 | [P-RVK-0006](#p-rvk-0006) | Asegurar que la revisión esté En curso; una revisión se cierra una sola vez. | La revisión ya fue cerrada. | 🛑 Detener operación |
| 5 | [P-RVK-0007](#p-rvk-0007) | Asegurar que la justificación de la decisión sea válida en longitud, obligatoriedad y formato (RN-250). | Debe escribir la justificación de la decisión (entre 10 y 1000 caracteres). | 🛑 Detener operación |
| 6 | [P-RVK-0008](#p-rvk-0008) | Asignar a la revisión la justificación, la fecha de cierre y el estado Contenido vetado. | No se pudo cerrar la revisión; se deshace toda la operación. | 🛑 Detener operación |
| 7 | [P-KUD-0010](Kudo.md#p-kud-0010) | Cuando una revisión se cierre con veto, o se vete directamente, pasar el Kudo a Vetado (RN-204, RN-205). | No se pudo vetar el contenido; se deshace toda la operación. | 🛑 Detener operación |
| 8 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `cerrarRevisionKudoConLiberacion`

Decidir que el Kudo no incumple las políticas: la revisión se cierra, el Kudo vuelve a Visible y sus reportes se ven Rechazados porque su revisión terminó en liberación.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la revisión. |
| `datos{justificacion}` | [RevisionKudo](RevisionKudo.md) | Razón por la que el contenido se mantiene. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0003](Usuario.md#p-usu-0003) | Asegurar que quien ejecuta la acción tenga el rol Rector o Coordinador Académico (RN-193 a RN-200, RN-243, INV-033). | Solo el Rector y el Coordinador Académico pueden moderar contenido. | 🛑 Detener operación |
| 3 | [P-RVK-0001](#p-rvk-0001) | Asegurar que exista una revisión con el identificador indicado. | No existe la revisión que se indicó. | 🛑 Detener operación |
| 4 | [P-RVK-0006](#p-rvk-0006) | Asegurar que la revisión esté En curso; una revisión se cierra una sola vez. | La revisión ya fue cerrada. | 🛑 Detener operación |
| 5 | [P-RVK-0007](#p-rvk-0007) | Asegurar que la justificación de la decisión sea válida en longitud, obligatoriedad y formato (RN-250). | Debe escribir la justificación de la decisión (entre 10 y 1000 caracteres). | 🛑 Detener operación |
| 6 | [P-RVK-0009](#p-rvk-0009) | Asignar a la revisión la justificación, la fecha de cierre y el estado Contenido liberado. | No se pudo cerrar la revisión; se deshace toda la operación. | 🛑 Detener operación |
| 7 | [P-KUD-0011](Kudo.md#p-kud-0011) | Cuando una revisión se cierre liberando el contenido, devolver el Kudo a Visible (RN-242). | No se pudo liberar el contenido; se deshace toda la operación. | 🛑 Detener operación |
| 8 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `vetarKudoDirectamente`

Vetar un Kudo por decisión de la autoridad aunque nadie lo haya reportado. Queda registrado como una revisión que nace cerrada; si tenía reportes pendientes, quedan enlazados a ella y se ven Aceptados.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{kudo, justificacion}` | [RevisionKudo](RevisionKudo.md) | El Kudo que se veta y razón institucional del veto. |

**Salida:** [RevisionKudo](RevisionKudo.md) — La revisión cerrada que deja constancia del veto.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0003](Usuario.md#p-usu-0003) | Asegurar que quien ejecuta la acción tenga el rol Rector o Coordinador Académico (RN-193 a RN-200, RN-243, INV-033). | Solo el Rector y el Coordinador Académico pueden moderar contenido. | 🛑 Detener operación |
| 3 | [P-RVK-0011](#p-rvk-0011) | Asegurar que el Kudo y la justificación del veto directo sean válidos en tipo, longitud, obligatoriedad y formato. | Debe indicar el contenido y escribir la justificación del veto. | 🛑 Detener operación |
| 4 | [P-KUD-0001](Kudo.md#p-kud-0001) | Asegurar que exista un Kudo con el identificador indicado. | No existe el Kudo que se indicó. | 🛑 Detener operación |
| 5 | [P-KUD-0006](Kudo.md#p-kud-0006) | Asegurar que el Kudo esté Visible o Reportado para vetarlo directamente; si está En revisión se cierra su revisión y si ya está Vetado no se repite. | El Kudo ya está vetado o tiene una revisión en curso. | 🛑 Detener operación |
| 6 | [P-RVK-0003](#p-rvk-0003) | Asegurar que el Kudo no tenga otra revisión iniciada en la misma fecha y hora. | Ya existe esa revisión. | 🛑 Detener operación |
| 7 | [P-RVK-0004](#p-rvk-0004) | Asegurar que el identificador de la revisión sea único dentro de las revisiones; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 8 | [P-RVK-0010](#p-rvk-0010) | Cuando la autoridad vete directamente, registrar una revisión que nace cerrada: moderador, justificación, fecha de inicio igual a la de cierre y estado Contenido vetado (CU-013, CU-014). | No se pudo registrar el veto; se deshace toda la operación. | 🛑 Detener operación |
| 9 | [P-KUD-0010](Kudo.md#p-kud-0010) | Cuando una revisión se cierre con veto, o se vete directamente, pasar el Kudo a Vetado (RN-204, RN-205). | No se pudo vetar el contenido; se deshace toda la operación. | 🛑 Detener operación |
| 10 | [P-RPK-0005](ReporteKudo.md#p-rpk-0005) | Cuando se inicie una revisión del Kudo (o se vete directamente), enlazar con esa revisión todos sus reportes que aún no tienen revisión (RN-211). | No se pudieron enlazar los reportes; se deshace toda la operación. | 🛑 Detener operación |
| 11 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `consultarRevisionesKudo`

Consultar las revisiones en curso y el histórico de decisiones de moderación sobre los Kudos (CU-015, RF-068).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{kudo, moderador, estadoRevision}` | [RevisionKudo](RevisionKudo.md) | Datos opcionales para filtrar la consulta. |

**Salida:** [Lista&lt;RevisionKudo&gt;](RevisionKudo.md) — Las revisiones que cumplen el filtro.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0003](Usuario.md#p-usu-0003) | Asegurar que quien ejecuta la acción tenga el rol Rector o Coordinador Académico (RN-193 a RN-200, RN-243, INV-033). | Solo el Rector y el Coordinador Académico pueden moderar contenido. | 🛑 Detener operación |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Rector<br>Coordinador Académico | **Iniciar la revisión del Kudo**<br>Tomar un Kudo reportado para analizarlo. El Kudo pasa a En revisión y sus reportes pendientes quedan enlazados a esta revisión, por lo que se ven En revisión. | **Reportes pendientes del Kudo**: Para conocer los motivos por los que se reportó.<br>**Contenido del Kudo**: Para leer lo que se va a revisar. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0003](Usuario.md#p-usu-0003)<br>[P-KUD-0001](Kudo.md#p-kud-0001)<br>[P-KUD-0004](Kudo.md#p-kud-0004)<br>[P-RVK-0002](#p-rvk-0002)<br>[P-RVK-0003](#p-rvk-0003)<br>[P-RVK-0004](#p-rvk-0004)<br>[P-RVK-0005](#p-rvk-0005)<br>[P-KUD-0009](Kudo.md#p-kud-0009)<br>[P-RPK-0005](ReporteKudo.md#p-rpk-0005)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Revisión iniciada** | Ninguno |
| Rector<br>Coordinador Académico | **Vetar el Kudo revisado**<br>Decidir que el Kudo incumple las políticas institucionales: la revisión se cierra, el Kudo queda Vetado y sus reportes se ven Aceptados porque su revisión terminó en veto. La puede cerrar el Rector o el Coordinador Académico aunque no la haya iniciado (quien cierra queda en la Auditoría) y el veto es definitivo. Así se veta sin atributos de veto en el Kudo. | **Reportes de la revisión**: Para sustentar la decisión. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0003](Usuario.md#p-usu-0003)<br>[P-RVK-0001](#p-rvk-0001)<br>[P-RVK-0006](#p-rvk-0006)<br>[P-RVK-0007](#p-rvk-0007)<br>[P-RVK-0008](#p-rvk-0008)<br>[P-KUD-0010](Kudo.md#p-kud-0010)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Revisión cerrada: contenido vetado** | Ninguno |
| Rector<br>Coordinador Académico | **Liberar el Kudo revisado**<br>Decidir que el Kudo no incumple las políticas: la revisión se cierra, el Kudo vuelve a Visible y sus reportes se ven Rechazados porque su revisión terminó en liberación. | **Reportes de la revisión**: Para sustentar la decisión. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0003](Usuario.md#p-usu-0003)<br>[P-RVK-0001](#p-rvk-0001)<br>[P-RVK-0006](#p-rvk-0006)<br>[P-RVK-0007](#p-rvk-0007)<br>[P-RVK-0009](#p-rvk-0009)<br>[P-KUD-0011](Kudo.md#p-kud-0011)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Revisión cerrada: contenido liberado** | Ninguno |
| Rector<br>Coordinador Académico | **Vetar el Kudo directamente**<br>Vetar un Kudo por decisión de la autoridad aunque nadie lo haya reportado. Queda registrado como una revisión que nace cerrada; si tenía reportes pendientes, quedan enlazados a ella y se ven Aceptados. | **Contenido del Kudo**: Para leer lo que se va a vetar. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0003](Usuario.md#p-usu-0003)<br>[P-RVK-0011](#p-rvk-0011)<br>[P-KUD-0001](Kudo.md#p-kud-0001)<br>[P-KUD-0006](Kudo.md#p-kud-0006)<br>[P-RVK-0003](#p-rvk-0003)<br>[P-RVK-0004](#p-rvk-0004)<br>[P-RVK-0010](#p-rvk-0010)<br>[P-KUD-0010](Kudo.md#p-kud-0010)<br>[P-RPK-0005](ReporteKudo.md#p-rpk-0005)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Kudo vetado directamente** | Ninguno |
| Rector<br>Coordinador Académico | **Consultar el histórico de revisiones y vetos de los Kudos**<br>Consultar las revisiones en curso y el histórico de decisiones de moderación sobre los Kudos (CU-015, RF-068). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0003](Usuario.md#p-usu-0003) | No aplica | 🟧 **Histórico de moderación consultado** | Ninguno |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="p-rvk-0001"></a>**P-RVK-0001** | Asegurar que exista una revisión con el identificador indicado. | RevisionKudo | `AsegurarRevisionKudoExisteRule` |
| <a id="p-rvk-0002"></a>**P-RVK-0002** | Asegurar que el Kudo no tenga ya una revisión En curso. | RevisionKudo | `AsegurarKudoSinRevisionEnCursoRule` |
| <a id="p-rvk-0003"></a>**P-RVK-0003** | Asegurar que el Kudo no tenga otra revisión iniciada en la misma fecha y hora. | RevisionKudo | `AsegurarRevisionKudoNoRepetidaRule` |
| <a id="p-rvk-0004"></a>**P-RVK-0004** | Asegurar que el identificador de la revisión sea único dentro de las revisiones; si ya existe, generar otro. | RevisionKudo | `AsegurarIdRevisionKudoUnicoRule` |
| <a id="p-rvk-0005"></a>**P-RVK-0005** | Asignar a la revisión el moderador (quien la inicia), la fecha de inicio y el estado En curso. | RevisionKudo | `AsignarDatosInicialesRevisionKudoRule` |
| <a id="p-rvk-0006"></a>**P-RVK-0006** | Asegurar que la revisión esté En curso; una revisión se cierra una sola vez. | RevisionKudo | `AsegurarRevisionKudoEnCursoRule` |
| <a id="p-rvk-0007"></a>**P-RVK-0007** | Asegurar que la justificación de la decisión sea válida en longitud, obligatoriedad y formato (RN-250). | RevisionKudo | `AsegurarFormatoJustificacionRevisionKudoValidoRule` |
| <a id="p-rvk-0008"></a>**P-RVK-0008** | Asignar a la revisión la justificación, la fecha de cierre y el estado Contenido vetado. | RevisionKudo | `AsignarCierreConVetoRevisionKudoRule` |
| <a id="p-rvk-0009"></a>**P-RVK-0009** | Asignar a la revisión la justificación, la fecha de cierre y el estado Contenido liberado. | RevisionKudo | `AsignarCierreConLiberacionRevisionKudoRule` |
| <a id="p-rvk-0010"></a>**P-RVK-0010** | Cuando la autoridad vete directamente, registrar una revisión que nace cerrada: moderador, justificación, fecha de inicio igual a la de cierre y estado Contenido vetado (CU-013, CU-014). | RevisionKudo | `RegistrarVetoDirectoComoRevisionKudoRule` |
| <a id="p-rvk-0011"></a>**P-RVK-0011** | Asegurar que el Kudo y la justificación del veto directo sean válidos en tipo, longitud, obligatoriedad y formato. | RevisionKudo | `AsegurarFormatoVetoDirectoKudoValidoRule` |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ CalificacionKudo](CalificacionKudo.md) · [CalificacionReplica ➡️](CalificacionReplica.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/RevisionKudo.md) · [📐 Clase](../clases/RevisionKudo.md)
