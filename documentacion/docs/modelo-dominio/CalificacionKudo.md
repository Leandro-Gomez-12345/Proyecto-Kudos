# 🧩 CalificacionKudo

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ Replica](Replica.md) · [RevisionKudo ➡️](RevisionKudo.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/CalificacionKudo.md) · [📐 Clase](../clases/CalificacionKudo.md)

> Valoración de 1 a 5 estrellas que una persona da a el Kudo. Cada persona tiene una sola calificación por Kudo y puede modificarla.

| Línea de vida | Atributos | Responsabilidades | Eventos | Políticas propias |
| :---: | :---: | :---: | :---: | :---: |
| Nivel 4 | 5 | 3 | 3 | 9 |

## Contenido

1. [Muestreo](#1-muestreo)
2. [Caracterización](#2-caracterización)
3. [Responsabilidades](#3-responsabilidades)
4. [Event Storming](#4-event-storming)
5. [Políticas](#5-políticas)

---

## 1. Muestreo

Ejemplos reales del objeto para validar sus atributos con el negocio.

| **id** | **kudo** | **usuario** | **estrellas** | **fechaCalificacion** | **Combinación única** |
| --- | --- | --- | --- | --- | --- |
| 1 | valentina.arango@uco.edu.co-10/03/2026 14:20 | andres.cardona@uco.edu.co | 5 | 10/03/2026 18:00 | valentina.arango@uco.edu.co-10/03/2026 14:20-andres.cardona@uco.edu.co |
| 2 | valentina.arango@uco.edu.co-10/03/2026 14:20 | luisa.ospina@uco.edu.co | 4 | 11/03/2026 07:50 | valentina.arango@uco.edu.co-10/03/2026 14:20-luisa.ospina@uco.edu.co |
| 3 | andres.cardona@uco.edu.co-15/04/2026 09:05 | mateo.giraldo@uco.edu.co | 5 | 15/04/2026 12:00 | andres.cardona@uco.edu.co-15/04/2026 09:05-mateo.giraldo@uco.edu.co |
| 4 | andres.cardona@uco.edu.co-15/04/2026 09:05 | diego.montoya@uco.edu.co | 1 | 16/04/2026 13:15 | andres.cardona@uco.edu.co-15/04/2026 09:05-diego.montoya@uco.edu.co |
| 5 | carlos.zapata@uco.edu.co-25/09/2026 10:10 | valentina.arango@uco.edu.co | 3 | 25/09/2026 11:00 | carlos.zapata@uco.edu.co-25/09/2026 10:10-valentina.arango@uco.edu.co |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único de la calificación dentro del sistema. |
| `kudo` | Representa el Kudo que se califica. |
| `usuario` | Representa la persona que califica. |
| `estrellas` | Representa la cantidad de estrellas que la persona asigna. |
| `fechaCalificacion` | Representa la fecha y hora en que se registró o se modificó por última vez la calificación. |
| `Combinación única` | Representa que una persona solo puede tener una calificación por Kudo (RN-178, RN-180). |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único de la calificación dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `kudo` | Representa el Kudo que se califica. | [Kudo](Kudo.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Kudo). El Kudo no puede estar Vetado. |
| `usuario` | Representa la persona que califica. | [Usuario](Usuario.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Usuario). Lo asigna el sistema con el usuario autenticado; su cuenta debe estar Activa. |
| `estrellas` | Representa la cantidad de estrellas que la persona asigna. | Entero | Si | No aplica | No aplica | No aplica | Solo números enteros de 1 a 5. En pantalla se selecciona con estrellas; nunca se escribe. |
| `fechaCalificacion` | Representa la fecha y hora en que se registró o se modificó por última vez la calificación. | Fecha hora | Si | No aplica | No aplica | No aplica | La asigna el sistema al calificar y se actualiza si la persona modifica su calificación. El valor anterior queda en Auditoría. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `kudo` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `usuario` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `estrellas` | No aplica | No aplica | 1 | 5 | No | No aplica | No aplica | No aplica | No aplica | No | 0 | No |
| `fechaCalificacion` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | 01/01/1000 00:00 | No |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única | Representa que una persona solo puede tener una calificación por Kudo (RN-178, RN-180). | `kudo, usuario` | Compara exactamente. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`calificarKudo`](#calificarkudo) | [`cambiarCalificacionKudo`](#cambiarcalificacionkudo) | [`consultarCalificacionesKudo`](#consultarcalificacioneskudo) |
| --- | :---: | :---: | :---: |
| `id` | NR | R·NM | 👁 |
| `kudo` | R | NR·NM | 👁 |
| `usuario` | NR | NR·NM | 👁 |
| `estrellas` | R | R·M | 👁 |
| `fechaCalificacion` | NR | NR·M | 👁 |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`calificarKudo`](#calificarkudo) | Valorar el Kudo con 1 a 5 estrellas. Cada persona tiene una sola calificación por contenido. |
| 2 | [`cambiarCalificacionKudo`](#cambiarcalificacionkudo) | Cambiar las estrellas de una calificación propia (EVT-033). |
| 3 | [`consultarCalificacionesKudo`](#consultarcalificacioneskudo) | Ver las valoraciones que ha recibido el Kudo (CU-009). |

### `calificarKudo`

Valorar el Kudo con 1 a 5 estrellas. Cada persona tiene una sola calificación por contenido.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{kudo, estrellas}` | [CalificacionKudo](CalificacionKudo.md) | El Kudo que se califica y número de estrellas. |

**Salida:** [CalificacionKudo](CalificacionKudo.md) — La calificación registrada.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-CAK-0002](#p-cak-0002) | Asegurar que el Kudo y las estrellas sean válidos; las estrellas son un número entero de 1 a 5 (RN-177, INV-030). | La calificación debe ser de 1 a 5 estrellas. | 🛑 Detener operación |
| 3 | [P-KUD-0001](Kudo.md#p-kud-0001) | Asegurar que exista un Kudo con el identificador indicado. | No existe el Kudo que se indicó. | 🛑 Detener operación |
| 4 | [P-KUD-0002](Kudo.md#p-kud-0002) | Asegurar que el Kudo esté Visible, Reportado o En revisión; un contenido Vetado no admite réplicas ni calificaciones (RN-205). | El Kudo fue vetado y ya no admite interacción. | 🛑 Detener operación |
| 5 | [P-CAK-0008](#p-cak-0008) | Asegurar que quien califica no sea el autor del Kudo: nadie califica su propio contenido (supuesto: no lo pide ningún documento). | No puede calificar su propio contenido. | 🛑 Detener operación |
| 6 | [P-CAK-0003](#p-cak-0003) | Asegurar que la persona no tenga ya una calificación para el Kudo (RN-178, RN-180, INV-031). | Usted ya calificó este contenido; puede cambiar su calificación. | 🛑 Detener operación |
| 7 | [P-CAK-0004](#p-cak-0004) | Asegurar que el identificador de la calificación sea único dentro de las calificaciones; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 8 | [P-CAK-0005](#p-cak-0005) | Asignar a la calificación la persona que califica y la fecha y hora (RN-182, RN-183). | No se pudo guardar la calificación; se deshace toda la operación. | 🛑 Detener operación |
| 9 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `cambiarCalificacionKudo`

Cambiar las estrellas de una calificación propia (EVT-033).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la calificación. |
| `datos{estrellas}` | [CalificacionKudo](CalificacionKudo.md) | Número nuevo de estrellas. |

**Salida:** [CalificacionKudo](CalificacionKudo.md) — La calificación actualizada.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-CAK-0001](#p-cak-0001) | Asegurar que exista una calificación con el identificador indicado. | No existe la calificación que se indicó. | 🛑 Detener operación |
| 3 | [P-CAK-0006](#p-cak-0006) | Asegurar que quien cambia la calificación sea la misma persona que la hizo. | Solo puede cambiar sus propias calificaciones. | 🛑 Detener operación |
| 4 | [P-KUD-0002](Kudo.md#p-kud-0002) | Asegurar que el Kudo esté Visible, Reportado o En revisión; un contenido Vetado no admite réplicas ni calificaciones (RN-205). | El Kudo fue vetado y ya no admite interacción. | 🛑 Detener operación |
| 5 | [P-CAK-0002](#p-cak-0002) | Asegurar que el Kudo y las estrellas sean válidos; las estrellas son un número entero de 1 a 5 (RN-177, INV-030). | La calificación debe ser de 1 a 5 estrellas. | 🛑 Detener operación |
| 6 | [P-CAK-0009](#p-cak-0009) | Asegurar que las estrellas nuevas sean distintas de las actuales. | Esa ya es su calificación actual. | 🛑 Detener operación |
| 7 | [P-CAK-0007](#p-cak-0007) | Cuando cambien las estrellas, actualizar la fecha de la calificación; el valor anterior queda en la auditoría. | No se pudo guardar el cambio; se deshace toda la operación. | 🛑 Detener operación |
| 8 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `consultarCalificacionesKudo`

Ver las valoraciones que ha recibido el Kudo (CU-009).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{kudo, usuario}` | [CalificacionKudo](CalificacionKudo.md) | Datos opcionales para filtrar la consulta. |

**Salida:** [Lista&lt;CalificacionKudo&gt;](CalificacionKudo.md) — Las calificaciones que cumplen el filtro.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-KUD-0001](Kudo.md#p-kud-0001) | Asegurar que exista un Kudo con el identificador indicado. | No existe el Kudo que se indicó. | 🛑 Detener operación |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Usuario autenticado | **Calificar el Kudo**<br>Valorar el Kudo con 1 a 5 estrellas. Cada persona tiene una sola calificación por contenido. | **Kudo**: Para saber qué contenido se califica, quién es su autor y que admite interacción. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-CAK-0002](#p-cak-0002)<br>[P-KUD-0001](Kudo.md#p-kud-0001)<br>[P-KUD-0002](Kudo.md#p-kud-0002)<br>[P-CAK-0008](#p-cak-0008)<br>[P-CAK-0003](#p-cak-0003)<br>[P-CAK-0004](#p-cak-0004)<br>[P-CAK-0005](#p-cak-0005)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Calificación registrada** | ¿Qué indicadores se calculan con las calificaciones (RN-185, EVT-034)? Se asumió el promedio de estrellas, calculado al consultar y sin guardarlo. |
| Usuario autenticado | **Cambiar su calificación del Kudo**<br>Cambiar las estrellas de una calificación propia (EVT-033). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-CAK-0001](#p-cak-0001)<br>[P-CAK-0006](#p-cak-0006)<br>[P-KUD-0002](Kudo.md#p-kud-0002)<br>[P-CAK-0002](#p-cak-0002)<br>[P-CAK-0009](#p-cak-0009)<br>[P-CAK-0007](#p-cak-0007)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Calificación actualizada** | Ninguno |
| Usuario autenticado | **Consultar las calificaciones del Kudo**<br>Ver las valoraciones que ha recibido el Kudo (CU-009). | **Promedio de estrellas del Kudo**: Indicador calculado a partir de las calificaciones (RN-185). | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-KUD-0001](Kudo.md#p-kud-0001) | No aplica | 🟧 **Valoraciones consultadas** | ¿Qué indicadores se calculan con las calificaciones (RN-185, EVT-034)? Se asumió el promedio de estrellas, calculado al consultar y sin guardarlo. |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="p-cak-0001"></a>**P-CAK-0001** | Asegurar que exista una calificación con el identificador indicado. | CalificacionKudo | `AsegurarCalificacionKudoExisteRule` |
| <a id="p-cak-0002"></a>**P-CAK-0002** | Asegurar que el Kudo y las estrellas sean válidos; las estrellas son un número entero de 1 a 5 (RN-177, INV-030). | CalificacionKudo | `AsegurarFormatoCalificacionKudoValidoRule` |
| <a id="p-cak-0003"></a>**P-CAK-0003** | Asegurar que la persona no tenga ya una calificación para el Kudo (RN-178, RN-180, INV-031). | CalificacionKudo | `AsegurarUnicaCalificacionKudoPorPersonaRule` |
| <a id="p-cak-0004"></a>**P-CAK-0004** | Asegurar que el identificador de la calificación sea único dentro de las calificaciones; si ya existe, generar otro. | CalificacionKudo | `AsegurarIdCalificacionKudoUnicoRule` |
| <a id="p-cak-0005"></a>**P-CAK-0005** | Asignar a la calificación la persona que califica y la fecha y hora (RN-182, RN-183). | CalificacionKudo | `AsignarAutorYFechaCalificacionKudoRule` |
| <a id="p-cak-0006"></a>**P-CAK-0006** | Asegurar que quien cambia la calificación sea la misma persona que la hizo. | CalificacionKudo | `AsegurarUsuarioEsAutorDeCalificacionKudoRule` |
| <a id="p-cak-0007"></a>**P-CAK-0007** | Cuando cambien las estrellas, actualizar la fecha de la calificación; el valor anterior queda en la auditoría. | CalificacionKudo | `ActualizarFechaCalificacionKudoRule` |
| <a id="p-cak-0008"></a>**P-CAK-0008** | Asegurar que quien califica no sea el autor del Kudo: nadie califica su propio contenido (supuesto: no lo pide ningún documento). | CalificacionKudo | `AsegurarCalificadorNoEsAutorDeKudoRule` |
| <a id="p-cak-0009"></a>**P-CAK-0009** | Asegurar que las estrellas nuevas sean distintas de las actuales. | CalificacionKudo | `AsegurarEstrellasDistintasEnCalificacionKudoRule` |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ Replica](Replica.md) · [RevisionKudo ➡️](RevisionKudo.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/CalificacionKudo.md) · [📐 Clase](../clases/CalificacionKudo.md)
