# 🧩 Replica

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ Kudo](Kudo.md) · [CalificacionKudo ➡️](CalificacionKudo.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/Replica.md) · [📐 Clase](../clases/Replica.md)

> Intervención dentro de la conversación de un Kudo. Puede responder directamente al Kudo o a otra réplica, sin límite de profundidad y sin ciclos.

| Línea de vida | Atributos | Responsabilidades | Eventos | Políticas propias |
| :---: | :---: | :---: | :---: | :---: |
| Nivel 4 | 7 | 7 | 7 | 19 |

## Contenido

1. [Muestreo](#1-muestreo)
2. [Caracterización](#2-caracterización)
3. [Responsabilidades](#3-responsabilidades)
4. [Event Storming](#4-event-storming)
5. [Políticas](#5-políticas)

---

## 1. Muestreo

Ejemplos reales del objeto para validar sus atributos con el negocio.

| **id** | **kudo** | **replicaPadre** | **autor** | **contenido** | **estadoContenido** | **fechaCreacion** | **Combinación única** |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | valentina.arango@uco.edu.co-10/03/2026 14:20 | — | mateo.giraldo@uco.edu.co | ¡Gracias Vale! El mérito es de todo el equipo. | Visible | 10/03/2026 15:00 | mateo.giraldo@uco.edu.co-10/03/2026 15:00 |
| 2 | valentina.arango@uco.edu.co-10/03/2026 14:20 | mateo.giraldo@uco.edu.co-10/03/2026 15:00 | valentina.arango@uco.edu.co | Igual tú hiciste la parte más difícil | Visible | 10/03/2026 15:12 | valentina.arango@uco.edu.co-10/03/2026 15:12 |
| 3 | valentina.arango@uco.edu.co-10/03/2026 14:20 | valentina.arango@uco.edu.co-10/03/2026 15:12 | andres.cardona@uco.edu.co | Excelente trabajo de los dos, así se construye comunidad. | Visible | 11/03/2026 08:30 | andres.cardona@uco.edu.co-11/03/2026 08:30 |
| 4 | andres.cardona@uco.edu.co-15/04/2026 09:05 | — | diego.montoya@uco.edu.co | Muy merecido, Valentina. | Visible | 15/04/2026 10:00 | diego.montoya@uco.edu.co-15/04/2026 10:00 |
| 5 | carlos.zapata@uco.edu.co-25/09/2026 10:10 | — | mateo.giraldo@uco.edu.co | Eso lo hace cualquiera, no merece reconocimiento. | Vetado | 25/09/2026 12:00 | mateo.giraldo@uco.edu.co-25/09/2026 12:00 |
| 6 | carlos.zapata@uco.edu.co-25/09/2026 10:10 | mateo.giraldo@uco.edu.co-25/09/2026 12:00 | luisa.ospina@uco.edu.co | Gracias por tu opinión, Mateo. | Reportado | 25/09/2026 12:40 | luisa.ospina@uco.edu.co-25/09/2026 12:40 |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único de la réplica dentro del sistema. |
| `kudo` | Representa el Kudo a cuya conversación pertenece la réplica. |
| `replicaPadre` | Representa la réplica a la que se responde; vacía cuando se responde directamente al Kudo. |
| `autor` | Representa la persona que escribe la réplica. |
| `contenido` | Representa el texto completo de la réplica. |
| `estadoContenido` | Representa en qué estado del ciclo de vida se encuentra la réplica. |
| `fechaCreacion` | Representa la fecha y hora en que se publicó la réplica. |
| `Combinación única` | Representa que una persona no puede publicar dos réplicas en la misma fecha y hora. |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único de la réplica dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `kudo` | Representa el Kudo a cuya conversación pertenece la réplica. | [Kudo](Kudo.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Kudo). El Kudo no puede estar Vetado. |
| `replicaPadre` | Representa la réplica a la que se responde; vacía cuando se responde directamente al Kudo. | [Replica](Replica.md) | No | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Réplica). Vacía significa que responde al Kudo. Si tiene valor, la réplica padre debe pertenecer al mismo Kudo y se esta replicando es la replica padre. |
| `autor` | Representa la persona que escribe la réplica. | [Usuario](Usuario.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Usuario). Lo asigna el sistema con el usuario autenticado; su cuenta debe estar Activa. |
| `contenido` | Representa el texto completo de la réplica. | Alfanumérico | Si | 1 | Sin límite | Texto libre. | Cualquier carácter, incluidos emojis y saltos de línea. No tiene límite de longitud y nunca se trunca. |
| `estadoContenido` | Representa en qué estado del ciclo de vida se encuentra la réplica. | [EstadoContenido](EstadoContenido.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Estado Contenido). Inicia en Creado y pasa a Visible al publicarse. |
| `fechaCreacion` | Representa la fecha y hora en que se publicó la réplica. | Fecha hora | Si | No aplica | No aplica | No aplica | La asigna el sistema cuando se crea la replica. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `kudo` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `replicaPadre` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `autor` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `contenido` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |
| `estadoContenido` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `fechaCreacion` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | 01/01/1000 00:00 | Si |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única | Representa que una persona no puede publicar dos réplicas en la misma fecha y hora. | `autor, fechaCreacion` | Compara el correo del autor y la fecha y hora exactas. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`replicarKudo`](#replicarkudo) | [`responderReplica`](#responderreplica) | [`consultarConversacionKudo`](#consultarconversacionkudo) | [`marcarReplicaReportada`](#marcarreplicareportada) | [`ponerReplicaEnRevision`](#ponerreplicaenrevision) | [`vetarReplica`](#vetarreplica) | [`liberarReplica`](#liberarreplica) |
| --- | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| `id` | NR | NR | 👁 | R·NM | R·NM | R·NM | R·NM |
| `kudo` | R | NR | 👁 | NR·NM | NR·NM | NR·NM | NR·NM |
| `replicaPadre` | NR | R | 👁 | NR·NM | NR·NM | NR·NM | NR·NM |
| `autor` | NR | NR | 👁 | NR·NM | NR·NM | NR·NM | NR·NM |
| `contenido` | R | R | 👁 | NR·NM | NR·NM | NR·NM | NR·NM |
| `estadoContenido` | NR | NR | 👁 | NR·M | NR·M | NR·M | NR·M |
| `fechaCreacion` | NR | NR | 👁 | NR·NM | NR·NM | NR·NM | NR·NM |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`replicarKudo`](#replicarkudo) | Escribir una réplica directamente sobre un Kudo. No hay límite de réplicas por persona (CU-004, RN-165, RN-166). |
| 2 | [`responderReplica`](#responderreplica) | Responder a otra réplica de la conversación, sin límite de profundidad (CU-005, RN-162, RN-164). |
| 3 | [`consultarConversacionKudo`](#consultarconversacionkudo) | Ver el árbol completo de la conversación de un Kudo (CU-006, RF-055). |
| 4 | [`marcarReplicaReportada`](#marcarreplicareportada) | Pasar la réplica de Visible a Reportada. Lo hace el sistema cuando la réplica recibe su primer reporte pendiente. |
| 5 | [`ponerReplicaEnRevision`](#ponerreplicaenrevision) | Pasar la réplica de Reportada a En revisión. Lo hace el sistema cuando el Rector o el Coordinador Académico inicia la revisión. |
| 6 | [`vetarReplica`](#vetarreplica) | Pasar la réplica a Vetada: deja de estar disponible para la interacción normal. Lo hace el sistema cuando una revisión se cierra con veto o cuando la autoridad veta directamente. No necesita atributos propios del veto: quién, cuándo y por qué quedan en la revisión. |
| 7 | [`liberarReplica`](#liberarreplica) | Devolver la réplica a Visible. Lo hace el sistema cuando una revisión se cierra sin veto. |

### `replicarKudo`

Escribir una réplica directamente sobre un Kudo. No hay límite de réplicas por persona (CU-004, RN-165, RN-166).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{kudo, contenido}` | [Replica](Replica.md) | Kudo al que se replica y texto de la réplica. |

**Salida:** [Replica](Replica.md) — La réplica publicada.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-REP-0014](#p-rep-0014) | Asegurar que el Kudo y el contenido de la réplica sean válidos en tipo, longitud, obligatoriedad y formato; el contenido no tiene longitud máxima (RN-170). | Se informa cuál dato no cumple y cómo debe escribirse. | 🛑 Detener operación |
| 3 | [P-KUD-0001](Kudo.md#p-kud-0001) | Asegurar que exista un Kudo con el identificador indicado. | No existe el Kudo que se indicó. | 🛑 Detener operación |
| 4 | [P-KUD-0002](Kudo.md#p-kud-0002) | Asegurar que el Kudo esté Visible, Reportado o En revisión; un contenido Vetado no admite réplicas ni calificaciones (RN-205). | El Kudo fue vetado y ya no admite interacción. | 🛑 Detener operación |
| 5 | [P-REP-0019](#p-rep-0019) | Asegurar que el autor no tenga otra réplica publicada en la misma fecha y hora. | Ya se registró esa réplica. | 🛑 Detener operación |
| 6 | [P-REP-0013](#p-rep-0013) | Asegurar que el identificador de la réplica sea único dentro de las réplicas; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 7 | [P-REP-0017](#p-rep-0017) | Asignar a la réplica el autor (quien la escribe), la fecha de creación y el estado Creado (RN-206). | No se pudo crear la réplica; se deshace toda la operación. | 🛑 Detener operación |
| 8 | [P-REP-0018](#p-rep-0018) | Cuando la réplica quede registrada, pasarla de Creado a Visible dentro de la conversación (CU-004). | No se pudo publicar la réplica; se deshace toda la operación. | 🛑 Detener operación |
| 9 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `responderReplica`

Responder a otra réplica de la conversación, sin límite de profundidad (CU-005, RN-162, RN-164).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{replicaPadre, contenido}` | [Replica](Replica.md) | Réplica a la que se responde y texto de la respuesta. |

**Salida:** [Replica](Replica.md) — La réplica hija publicada.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-REP-0014](#p-rep-0014) | Asegurar que el Kudo y el contenido de la réplica sean válidos en tipo, longitud, obligatoriedad y formato; el contenido no tiene longitud máxima (RN-170). | Se informa cuál dato no cumple y cómo debe escribirse. | 🛑 Detener operación |
| 3 | [P-REP-0001](#p-rep-0001) | Asegurar que exista una réplica con el identificador indicado. | No existe la réplica que se indicó. | 🛑 Detener operación |
| 4 | [P-REP-0002](#p-rep-0002) | Asegurar que la réplica esté Visible, Reportada o En revisión; un contenido Vetado no admite réplicas ni calificaciones (RN-205). | La réplica fue vetada y ya no admite interacción. | 🛑 Detener operación |
| 5 | [P-REP-0015](#p-rep-0015) | Asegurar que la réplica a la que se responde pertenezca a la conversación del mismo Kudo (INV-026, INV-027). | La réplica a la que responde no pertenece a esta conversación. | 🛑 Detener operación |
| 6 | [P-REP-0016](#p-rep-0016) | Asegurar que la réplica padre exista antes que la nueva réplica y que nunca pueda cambiarse, para que no existan autorreferencias ni ciclos (INV-028, INV-029). | No se puede responder a esa réplica. | 🛑 Detener operación |
| 7 | [P-KUD-0002](Kudo.md#p-kud-0002) | Asegurar que el Kudo esté Visible, Reportado o En revisión; un contenido Vetado no admite réplicas ni calificaciones (RN-205). | El Kudo fue vetado y ya no admite interacción. | 🛑 Detener operación |
| 8 | [P-REP-0019](#p-rep-0019) | Asegurar que el autor no tenga otra réplica publicada en la misma fecha y hora. | Ya se registró esa réplica. | 🛑 Detener operación |
| 9 | [P-REP-0013](#p-rep-0013) | Asegurar que el identificador de la réplica sea único dentro de las réplicas; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 10 | [P-REP-0017](#p-rep-0017) | Asignar a la réplica el autor (quien la escribe), la fecha de creación y el estado Creado (RN-206). | No se pudo crear la réplica; se deshace toda la operación. | 🛑 Detener operación |
| 11 | [P-REP-0018](#p-rep-0018) | Cuando la réplica quede registrada, pasarla de Creado a Visible dentro de la conversación (CU-004). | No se pudo publicar la réplica; se deshace toda la operación. | 🛑 Detener operación |
| 12 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `consultarConversacionKudo`

Ver el árbol completo de la conversación de un Kudo (CU-006, RF-055).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{kudo}` | [Replica](Replica.md) | Kudo cuya conversación se consulta. |

**Salida:** [Lista&lt;Replica&gt;](Replica.md) — Todas las réplicas del Kudo, con su réplica padre para armar el árbol.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-KUD-0001](Kudo.md#p-kud-0001) | Asegurar que exista un Kudo con el identificador indicado. | No existe el Kudo que se indicó. | 🛑 Detener operación |
| 3 | [P-REP-0012](#p-rep-0012) | Cuando la réplica esté Vetada, mostrarla a la comunidad con el aviso "contenido vetado" y sin su contenido; solo el Rector y el Coordinador Académico ven el contenido completo. La conversación se conserva (RN-205, INV-035). | No aplica: se muestra el aviso en lugar del contenido. | ▶️ Continuar operación |

### `marcarReplicaReportada`

Pasar la réplica de Visible a Reportada. Lo hace el sistema cuando la réplica recibe su primer reporte pendiente.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la réplica. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-REP-0001](#p-rep-0001) | Asegurar que exista una réplica con el identificador indicado. | No existe la réplica que se indicó. | 🛑 Detener operación |
| 2 | [P-REP-0007](#p-rep-0007) | Asegurar que la réplica esté Visible antes de pasarla a Reportada. | No aplica: si ya estaba Reportada, conserva su estado. | ▶️ Continuar operación |

### `ponerReplicaEnRevision`

Pasar la réplica de Reportada a En revisión. Lo hace el sistema cuando el Rector o el Coordinador Académico inicia la revisión.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la réplica. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-REP-0001](#p-rep-0001) | Asegurar que exista una réplica con el identificador indicado. | No existe la réplica que se indicó. | 🛑 Detener operación |
| 2 | [P-REP-0004](#p-rep-0004) | Asegurar que la réplica esté Reportada antes de iniciar su revisión (RN-208). | La réplica no tiene reportes pendientes por revisar. | 🛑 Detener operación |

### `vetarReplica`

Pasar la réplica a Vetada: deja de estar disponible para la interacción normal. Lo hace el sistema cuando una revisión se cierra con veto o cuando la autoridad veta directamente. No necesita atributos propios del veto: quién, cuándo y por qué quedan en la revisión.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la réplica. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-REP-0001](#p-rep-0001) | Asegurar que exista una réplica con el identificador indicado. | No existe la réplica que se indicó. | 🛑 Detener operación |
| 2 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `liberarReplica`

Devolver la réplica a Visible. Lo hace el sistema cuando una revisión se cierra sin veto.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la réplica. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-REP-0001](#p-rep-0001) | Asegurar que exista una réplica con el identificador indicado. | No existe la réplica que se indicó. | 🛑 Detener operación |
| 2 | [P-REP-0005](#p-rep-0005) | Asegurar que la réplica esté En revisión antes de vetarla o liberarla por el cierre de una revisión. | La réplica no está en revisión. | 🛑 Detener operación |
| 3 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Usuario autenticado | **Replicar el Kudo**<br>Escribir una réplica directamente sobre un Kudo. No hay límite de réplicas por persona (CU-004, RN-165, RN-166). | **Kudo**: Para saber a qué reconocimiento se replica y que admite interacción. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-REP-0014](#p-rep-0014)<br>[P-KUD-0001](Kudo.md#p-kud-0001)<br>[P-KUD-0002](Kudo.md#p-kud-0002)<br>[P-REP-0019](#p-rep-0019)<br>[P-REP-0013](#p-rep-0013)<br>[P-REP-0017](#p-rep-0017)<br>[P-REP-0018](#p-rep-0018)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Réplica creada** | Ninguno |
| Usuario autenticado | **Responder la réplica**<br>Responder a otra réplica de la conversación, sin límite de profundidad (CU-005, RN-162, RN-164). | **Réplica a la que se responde**: Para tomar de ella el Kudo de la conversación. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-REP-0014](#p-rep-0014)<br>[P-REP-0001](#p-rep-0001)<br>[P-REP-0002](#p-rep-0002)<br>[P-REP-0015](#p-rep-0015)<br>[P-REP-0016](#p-rep-0016)<br>[P-KUD-0002](Kudo.md#p-kud-0002)<br>[P-REP-0019](#p-rep-0019)<br>[P-REP-0013](#p-rep-0013)<br>[P-REP-0017](#p-rep-0017)<br>[P-REP-0018](#p-rep-0018)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Réplica respondida** | Ninguno |
| Usuario autenticado | **Consultar la conversación del Kudo**<br>Ver el árbol completo de la conversación de un Kudo (CU-006, RF-055). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-KUD-0001](Kudo.md#p-kud-0001)<br>[P-REP-0012](#p-rep-0012) | No aplica | 🟧 **Conversación consultada** | Ninguno |
| Sistema | **Marcar la réplica como reportada**<br>Pasar la réplica de Visible a Reportada. Lo hace el sistema cuando la réplica recibe su primer reporte pendiente. | **No aplica**: No aplica | [P-REP-0001](#p-rep-0001)<br>[P-REP-0007](#p-rep-0007) | No aplica | 🟧 **Réplica reportada** | Ninguno |
| Sistema | **Poner la réplica en revisión**<br>Pasar la réplica de Reportada a En revisión. Lo hace el sistema cuando el Rector o el Coordinador Académico inicia la revisión. | **No aplica**: No aplica | [P-REP-0001](#p-rep-0001)<br>[P-REP-0004](#p-rep-0004) | No aplica | 🟧 **Réplica en revisión** | Ninguno |
| Sistema | **Vetar la réplica**<br>Pasar la réplica a Vetada: deja de estar disponible para la interacción normal. Lo hace el sistema cuando una revisión se cierra con veto o cuando la autoridad veta directamente. No necesita atributos propios del veto: quién, cuándo y por qué quedan en la revisión. | **No aplica**: No aplica | [P-REP-0001](#p-rep-0001)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Réplica vetada** | Ninguno |
| Sistema | **Liberar la réplica tras la revisión**<br>Devolver la réplica a Visible. Lo hace el sistema cuando una revisión se cierra sin veto. | **No aplica**: No aplica | [P-REP-0001](#p-rep-0001)<br>[P-REP-0005](#p-rep-0005)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Réplica liberada tras revisión** | Ninguno |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="p-rep-0001"></a>**P-REP-0001** | Asegurar que exista una réplica con el identificador indicado. | Replica | `AsegurarReplicaExisteRule` |
| <a id="p-rep-0002"></a>**P-REP-0002** | Asegurar que la réplica esté Visible, Reportada o En revisión; un contenido Vetado no admite réplicas ni calificaciones (RN-205). | Replica | `AsegurarReplicaAdmiteInteraccionRule` |
| <a id="p-rep-0003"></a>**P-REP-0003** | Asegurar que la réplica esté Visible o Reportada para poder reportarla (RN-207). | Replica | `AsegurarReplicaPuedeReportarseRule` |
| <a id="p-rep-0004"></a>**P-REP-0004** | Asegurar que la réplica esté Reportada antes de iniciar su revisión (RN-208). | Replica | `AsegurarReplicaReportadaRule` |
| <a id="p-rep-0005"></a>**P-REP-0005** | Asegurar que la réplica esté En revisión antes de vetarla o liberarla por el cierre de una revisión. | Replica | `AsegurarReplicaEnRevisionRule` |
| <a id="p-rep-0006"></a>**P-REP-0006** | Asegurar que la réplica esté Visible o Reportada para vetarla directamente; si está En revisión se cierra su revisión y si ya está Vetada no se repite. | Replica | `AsegurarReplicaPuedeVetarseDirectamenteRule` |
| <a id="p-rep-0007"></a>**P-REP-0007** | Asegurar que la réplica esté Visible antes de pasarla a Reportada. | Replica | `AsegurarReplicaVisibleParaReportarRule` |
| <a id="p-rep-0008"></a>**P-REP-0008** | Cuando una réplica Visible reciba un reporte, pasarla a Reportada (RN-207). | Replica | `MarcarReplicaReportadaAlRecibirReporteRule` |
| <a id="p-rep-0009"></a>**P-REP-0009** | Cuando se inicie una revisión, pasar la réplica de Reportada a En revisión (RN-208). | Replica | `PonerReplicaEnRevisionAlIniciarRevisionRule` |
| <a id="p-rep-0010"></a>**P-REP-0010** | Cuando una revisión se cierre con veto, o se vete directamente, pasar la réplica a Vetada (RN-209). | Replica | `VetarReplicaAlCerrarRevisionConVetoRule` |
| <a id="p-rep-0011"></a>**P-REP-0011** | Cuando una revisión se cierre liberando el contenido, devolver la réplica a Visible (RN-242). | Replica | `LiberarReplicaAlCerrarRevisionSinVetoRule` |
| <a id="p-rep-0012"></a>**P-REP-0012** | Cuando la réplica esté Vetada, mostrarla a la comunidad con el aviso "contenido vetado" y sin su contenido; solo el Rector y el Coordinador Académico ven el contenido completo. La conversación se conserva (RN-205, INV-035). | Replica | `MostrarAvisoEnReplicaVetadaRule` |
| <a id="p-rep-0013"></a>**P-REP-0013** | Asegurar que el identificador de la réplica sea único dentro de las réplicas; si ya existe, generar otro. | Replica | `AsegurarIdReplicaUnicoRule` |
| <a id="p-rep-0014"></a>**P-REP-0014** | Asegurar que el Kudo y el contenido de la réplica sean válidos en tipo, longitud, obligatoriedad y formato; el contenido no tiene longitud máxima (RN-170). | Replica | `AsegurarFormatoReplicaValidoRule` |
| <a id="p-rep-0015"></a>**P-REP-0015** | Asegurar que la réplica a la que se responde pertenezca a la conversación del mismo Kudo (INV-026, INV-027). | Replica | `AsegurarReplicaPadreDelMismoKudoRule` |
| <a id="p-rep-0016"></a>**P-REP-0016** | Asegurar que la réplica padre exista antes que la nueva réplica y que nunca pueda cambiarse, para que no existan autorreferencias ni ciclos (INV-028, INV-029). | Replica | `AsegurarConversacionSinCiclosRule` |
| <a id="p-rep-0017"></a>**P-REP-0017** | Asignar a la réplica el autor (quien la escribe), la fecha de creación y el estado Creado (RN-206). | Replica | `AsignarDatosInicialesReplicaRule` |
| <a id="p-rep-0018"></a>**P-REP-0018** | Cuando la réplica quede registrada, pasarla de Creado a Visible dentro de la conversación (CU-004). | Replica | `PublicarReplicaAlConfirmarRule` |
| <a id="p-rep-0019"></a>**P-REP-0019** | Asegurar que el autor no tenga otra réplica publicada en la misma fecha y hora. | Replica | `AsegurarReplicaNoRepetidaRule` |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ Kudo](Kudo.md) · [CalificacionKudo ➡️](CalificacionKudo.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/Replica.md) · [📐 Clase](../clases/Replica.md)
