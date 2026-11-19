# 🧩 ReporteKudo

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ RevisionReplica](RevisionReplica.md) · [ReporteReplica ➡️](ReporteReplica.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/ReporteKudo.md) · [📐 Clase](../clases/ReporteKudo.md)

> Solicitud de revisión que hace cualquier usuario autenticado sobre un Kudo, con un motivo. Se resuelve cuando la revisión del contenido se cierra. Su estado (Pendiente, En revisión, Aceptado o Rechazado) no se guarda: se deduce de la revisión que lo resuelve.

| Línea de vida | Atributos | Responsabilidades | Eventos | Políticas propias |
| :---: | :---: | :---: | :---: | :---: |
| Nivel 5 | 7 | 3 | 3 | 8 |

## Contenido

1. [Muestreo](#1-muestreo)
2. [Caracterización](#2-caracterización)
3. [Responsabilidades](#3-responsabilidades)
4. [Event Storming](#4-event-storming)
5. [Políticas](#5-políticas)

---

## 1. Muestreo

Ejemplos reales del objeto para validar sus atributos con el negocio.

| **id** | **kudo** | **usuario** | **motivo** | **descripcion** | **revisionKudo** | **fechaReporte** | **Combinación única** |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | mateo.giraldo@uco.edu.co-02/06/2026 16:45 | valentina.arango@uco.edu.co | Información falsa | — | mateo.giraldo@uco.edu.co-02/06/2026 16:45-03/06/2026 08:00 | 02/06/2026 18:00 | valentina.arango@uco.edu.co-mateo.giraldo@uco.edu.co-02/06/2026 16:45 |
| 2 | mateo.giraldo@uco.edu.co-02/06/2026 16:45 | andres.cardona@uco.edu.co | Información falsa | — | mateo.giraldo@uco.edu.co-02/06/2026 16:45-03/06/2026 08:00 | 02/06/2026 19:30 | andres.cardona@uco.edu.co-mateo.giraldo@uco.edu.co-02/06/2026 16:45 |
| 3 | andres.cardona@uco.edu.co-15/04/2026 09:05 | mateo.giraldo@uco.edu.co | Otro | Creo que la nota no fue la más alta del curso. | andres.cardona@uco.edu.co-15/04/2026 09:05-16/04/2026 09:00 | 15/04/2026 20:00 | mateo.giraldo@uco.edu.co-andres.cardona@uco.edu.co-15/04/2026 09:05 |
| 4 | carlos.zapata@uco.edu.co-25/09/2026 10:10 | diego.montoya@uco.edu.co | Otro | Es un Kudo repetido de la semana pasada. | carlos.zapata@uco.edu.co-25/09/2026 10:10-26/09/2026 09:00 | 25/09/2026 18:00 | diego.montoya@uco.edu.co-carlos.zapata@uco.edu.co-25/09/2026 10:10 |
| 5 | luisa.ospina@uco.edu.co-22/05/2026 11:30 | mateo.giraldo@uco.edu.co | Otro | El profesor no coordinó ese evento. | — | 23/05/2026 08:00 | mateo.giraldo@uco.edu.co-luisa.ospina@uco.edu.co-22/05/2026 11:30 |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único del reporte dentro del sistema. |
| `kudo` | Representa un Kudo que se reporta. |
| `usuario` | Representa la persona que reporta el contenido. |
| `motivo` | Representa la razón por la que se reporta el contenido. |
| `descripcion` | Representa la explicación adicional del reporte; obligatoria cuando el motivo es Otro. |
| `revisionKudo` | Representa la revisión que resuelve el reporte; vacía mientras el reporte está pendiente. De ella se deduce el estado del reporte: sin revisión = Pendiente, revisión En curso = En revisión, cerrada con veto = Aceptado, cerrada con liberación = Rechazado. |
| `fechaReporte` | Representa la fecha y hora en que se hizo el reporte. |
| `Combinación única` | Representa que una persona solo puede reportar una vez el mismo contenido. |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único del reporte dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `kudo` | Representa un Kudo que se reporta. | [Kudo](Kudo.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Kudo). El contenido debe estar Visible o Reportado. |
| `usuario` | Representa la persona que reporta el contenido. | [Usuario](Usuario.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Usuario). Lo asigna el sistema con el usuario autenticado. |
| `motivo` | Representa la razón por la que se reporta el contenido. | [Motivo](Motivo.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Motivo). |
| `descripcion` | Representa la explicación adicional del reporte; obligatoria cuando el motivo es Otro. | Alfanumérico | No | 0 | 500 | Texto libre. | Obligatoria si el motivo es Otro. Letras, números, tildes, espacios y signos de puntuación. |
| `revisionKudo` | Representa la revisión que resuelve el reporte; vacía mientras el reporte está pendiente. De ella se deduce el estado del reporte: sin revisión = Pendiente, revisión En curso = En revisión, cerrada con veto = Aceptado, cerrada con liberación = Rechazado. | [RevisionKudo](RevisionKudo.md) | No | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Revisión Kudo). Lo asigna el sistema al iniciar la revisión. Debe ser una revisión del mismo contenido reportado. Vacía significa que el reporte está Pendiente. El estado del reporte no se guarda: se deduce de esta revisión (En curso = En revisión; Contenido vetado = Aceptado; Contenido liberado = Rechazado), RN-210 a RN-212. |
| `fechaReporte` | Representa la fecha y hora en que se hizo el reporte. | Fecha hora | Si | No aplica | No aplica | No aplica | La asigna el sistema y no cambia. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `kudo` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `usuario` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `motivo` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `descripcion` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |
| `revisionKudo` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `fechaReporte` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | 01/01/1000 00:00 | Si |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única | Representa que una persona solo puede reportar una vez el mismo contenido. | `usuario, kudo` | Compara el correo de quien reporta y la fecha y hora exactas. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`reportarKudo`](#reportarkudo) | [`consultarReportesKudo`](#consultarreporteskudo) | [`enlazarReportesKudoConRevision`](#enlazarreporteskudoconrevision) |
| --- | :---: | :---: | :---: |
| `id` | NR | 👁 | NR·NM |
| `kudo` | R | 👁 | R·NM |
| `usuario` | NR | 👁 | NR·NM |
| `motivo` | R | 👁 | NR·NM |
| `descripcion` | R | 👁 | NR·NM |
| `revisionKudo` | NR | 👁 | R·M |
| `fechaReporte` | NR | 👁 | NR·NM |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`reportarKudo`](#reportarkudo) | Pedir que se revise un Kudo porque se considera inapropiado, indicando un motivo (CU-010, CU-011). |
| 2 | [`consultarReportesKudo`](#consultarreporteskudo) | Consultar los reportes hechos sobre los Kudos, para decidir si se revisan (CU-012, RF-064). |
| 3 | [`enlazarReportesKudoConRevision`](#enlazarreporteskudoconrevision) | Enlazar con la revisión que se abre los reportes del Kudo que aún no tienen revisión. Desde ese momento se ven En revisión, y al cerrarse la revisión se ven Aceptados o Rechazados sin cambiar nada más. Lo hace el sistema al iniciar la revisión o al vetar directamente. |

### `reportarKudo`

Pedir que se revise un Kudo porque se considera inapropiado, indicando un motivo (CU-010, CU-011).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{kudo, motivo, descripcion}` | [ReporteKudo](ReporteKudo.md) | El Kudo que se reporta, motivo y explicación adicional. |

**Salida:** [ReporteKudo](ReporteKudo.md) — El reporte registrado; queda Pendiente porque aún no tiene revisión.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-RPK-0001](#p-rpk-0001) | Asegurar que el Kudo, el motivo y la descripción sean válidos; la descripción es obligatoria cuando el motivo es Otro (RN-190). | Se informa cuál dato no cumple; si el motivo es Otro debe describirlo. | 🛑 Detener operación |
| 3 | [P-KUD-0001](Kudo.md#p-kud-0001) | Asegurar que exista un Kudo con el identificador indicado. | No existe el Kudo que se indicó. | 🛑 Detener operación |
| 4 | [P-KUD-0003](Kudo.md#p-kud-0003) | Asegurar que el Kudo esté Visible o Reportado para poder reportarlo (RN-202). | El Kudo no puede reportarse en su estado actual. | 🛑 Detener operación |
| 5 | [P-MOT-0001](Motivo.md#p-mot-0001) | Asegurar que exista un motivo con el identificador indicado. | No existe el motivo que se indicó. | 🛑 Detener operación |
| 6 | [P-RPK-0002](#p-rpk-0002) | Asegurar que la persona no haya reportado antes el Kudo: un solo reporte por persona y contenido. | Usted ya reportó este contenido. | 🛑 Detener operación |
| 7 | [P-RPK-0003](#p-rpk-0003) | Asegurar que el identificador del reporte sea único dentro de los reportes; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 8 | [P-RPK-0004](#p-rpk-0004) | Asignar al reporte la persona que reporta y la fecha; la revisión queda vacía, que es lo que significa que el reporte está Pendiente (RN-188, RN-189, RN-210). | No se pudo guardar el reporte; se deshace toda la operación. | 🛑 Detener operación |
| 9 | [P-KUD-0008](Kudo.md#p-kud-0008) | Cuando un Kudo Visible reciba un reporte, pasarlo a Reportado (RN-202). | No se pudo actualizar el estado; se deshace toda la operación. | 🛑 Detener operación |
| 10 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `consultarReportesKudo`

Consultar los reportes hechos sobre los Kudos, para decidir si se revisan (CU-012, RF-064).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{kudo, motivo}` | [ReporteKudo](ReporteKudo.md) | Datos opcionales para filtrar la consulta. |
| `estado` | Texto | Opcional: Pendiente, En revisión, Aceptado o Rechazado; se compara con el estado deducido de la revisión. |

**Salida:** [Lista&lt;ReporteKudo&gt;](ReporteKudo.md) — Los reportes que cumplen el filtro, cada uno con su estado deducido de la revisión.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-RPK-0008](#p-rpk-0008) | Asegurar que solo el Rector y el Coordinador Académico consulten los reportes (CU-012: autoridades autorizadas; supuesto por confirmar). | No tiene permiso para consultar los reportes. | 🛑 Detener operación |
| 3 | [P-RPK-0006](#p-rpk-0006) | Calcular el estado del reporte a partir de su revisión, sin guardarlo: sin revisión = Pendiente; revisión En curso = En revisión; cerrada con Contenido vetado = Aceptado; cerrada con Contenido liberado = Rechazado (RN-210 a RN-212). | No aplica: el estado siempre se puede calcular. | ▶️ Continuar operación |

### `enlazarReportesKudoConRevision`

Enlazar con la revisión que se abre los reportes del Kudo que aún no tienen revisión. Desde ese momento se ven En revisión, y al cerrarse la revisión se ven Aceptados o Rechazados sin cambiar nada más. Lo hace el sistema al iniciar la revisión o al vetar directamente.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{kudo}` | [ReporteKudo](ReporteKudo.md) | Contenido cuyos reportes se enlazan. |
| `datos{revisionKudo}` | [ReporteKudo](ReporteKudo.md) | Revisión que los va a resolver. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-RPK-0007](#p-rpk-0007) | Asegurar que solo se enlacen los reportes que están Pendientes, es decir, que aún no tienen revisión. | No aplica: los reportes ya enlazados a otra revisión no se tocan. | ▶️ Continuar operación |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Usuario autenticado | **Reportar el Kudo**<br>Pedir que se revise un Kudo porque se considera inapropiado, indicando un motivo (CU-010, CU-011). | **Motivos**: Para elegir la razón del reporte.<br>**Kudo**: Para saber qué contenido se reporta y su estado. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-RPK-0001](#p-rpk-0001)<br>[P-KUD-0001](Kudo.md#p-kud-0001)<br>[P-KUD-0003](Kudo.md#p-kud-0003)<br>[P-MOT-0001](Motivo.md#p-mot-0001)<br>[P-RPK-0002](#p-rpk-0002)<br>[P-RPK-0003](#p-rpk-0003)<br>[P-RPK-0004](#p-rpk-0004)<br>[P-KUD-0008](Kudo.md#p-kud-0008)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Kudo reportado** | Ninguno |
| Rector<br>Coordinador Académico | **Consultar los reportes de los Kudos**<br>Consultar los reportes hechos sobre los Kudos, para decidir si se revisan (CU-012, RF-064). | **Contenido reportado**: Para leer lo que se reportó.<br>**Revisión de cada reporte**: Para deducir el estado del reporte. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-RPK-0008](#p-rpk-0008)<br>[P-RPK-0006](#p-rpk-0006) | No aplica | 🟧 **Reportes consultados** | Ninguno |
| Sistema | **Enlazar los reportes del Kudo con la revisión**<br>Enlazar con la revisión que se abre los reportes del Kudo que aún no tienen revisión. Desde ese momento se ven En revisión, y al cerrarse la revisión se ven Aceptados o Rechazados sin cambiar nada más. Lo hace el sistema al iniciar la revisión o al vetar directamente. | **No aplica**: No aplica | [P-RPK-0007](#p-rpk-0007) | No aplica | 🟧 **Reportes enlazados a la revisión** | Ninguno |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="p-rpk-0001"></a>**P-RPK-0001** | Asegurar que el Kudo, el motivo y la descripción sean válidos; la descripción es obligatoria cuando el motivo es Otro (RN-190). | ReporteKudo | `AsegurarFormatoReporteKudoValidoRule` |
| <a id="p-rpk-0002"></a>**P-RPK-0002** | Asegurar que la persona no haya reportado antes el Kudo: un solo reporte por persona y contenido. | ReporteKudo | `AsegurarReporteKudoNoRepetidoRule` |
| <a id="p-rpk-0003"></a>**P-RPK-0003** | Asegurar que el identificador del reporte sea único dentro de los reportes; si ya existe, generar otro. | ReporteKudo | `AsegurarIdReporteKudoUnicoRule` |
| <a id="p-rpk-0004"></a>**P-RPK-0004** | Asignar al reporte la persona que reporta y la fecha; la revisión queda vacía, que es lo que significa que el reporte está Pendiente (RN-188, RN-189, RN-210). | ReporteKudo | `AsignarDatosInicialesReporteKudoRule` |
| <a id="p-rpk-0005"></a>**P-RPK-0005** | Cuando se inicie una revisión del Kudo (o se vete directamente), enlazar con esa revisión todos sus reportes que aún no tienen revisión (RN-211). | ReporteKudo | `EnlazarReportesKudoPendientesConRevisionRule` |
| <a id="p-rpk-0006"></a>**P-RPK-0006** | Calcular el estado del reporte a partir de su revisión, sin guardarlo: sin revisión = Pendiente; revisión En curso = En revisión; cerrada con Contenido vetado = Aceptado; cerrada con Contenido liberado = Rechazado (RN-210 a RN-212). | ReporteKudo | `DeducirEstadoReporteKudoRule` |
| <a id="p-rpk-0007"></a>**P-RPK-0007** | Asegurar que solo se enlacen los reportes que están Pendientes, es decir, que aún no tienen revisión. | ReporteKudo | `AsegurarReportesKudoPendientesRule` |
| <a id="p-rpk-0008"></a>**P-RPK-0008** | Asegurar que solo el Rector y el Coordinador Académico consulten los reportes (CU-012: autoridades autorizadas; supuesto por confirmar). | ReporteKudo | `AsegurarConsultaReportesKudoPorModeradoresRule` |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ RevisionReplica](RevisionReplica.md) · [ReporteReplica ➡️](ReporteReplica.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/ReporteKudo.md) · [📐 Clase](../clases/ReporteKudo.md)
