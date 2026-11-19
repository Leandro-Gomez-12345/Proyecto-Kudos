# 🧩 Kudo

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ VinculacionAcademica](VinculacionAcademica.md) · [Replica ➡️](Replica.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/Kudo.md) · [📐 Clase](../clases/Kudo.md)

> Reconocimiento formal que una persona otorga a otra de la comunidad universitaria, clasificado en una categoría y visible para la comunidad autenticada.

| Línea de vida | Atributos | Responsabilidades | Eventos | Políticas propias |
| :---: | :---: | :---: | :---: | :---: |
| Nivel 3 | 8 | 7 | 7 | 19 |

## Contenido

1. [Muestreo](#1-muestreo)
2. [Caracterización](#2-caracterización)
3. [Responsabilidades](#3-responsabilidades)
4. [Event Storming](#4-event-storming)
5. [Políticas](#5-políticas)

---

## 1. Muestreo

Ejemplos reales del objeto para validar sus atributos con el negocio.

| **id** | **autor** | **destinatario** | **categoria** | **nombreCategoria** | **contenido** | **estadoContenido** | **fechaCreacion** | **Combinación única** |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | valentina.arango@uco.edu.co | mateo.giraldo@uco.edu.co | Trabajo en equipo | Trabajo en equipo | Gracias Mateo por liderar el proyecto de Bases de Datos y explicarle a todo el grupo cómo normalizar el modelo. ¡Sin ti no habríamos entregado a tiempo! | Visible | 10/03/2026 14:20 | valentina.arango@uco.edu.co-10/03/2026 14:20 |
| 2 | andres.cardona@uco.edu.co | valentina.arango@uco.edu.co | Excelencia académica | Excelencia académica | Valentina obtuvo la mejor nota del curso de Estructuras de Datos y apoyó como monitora a sus compañeros. | Visible | 15/04/2026 09:05 | andres.cardona@uco.edu.co-15/04/2026 09:05 |
| 3 | luisa.ospina@uco.edu.co | andres.cardona@uco.edu.co | Liderazgo institucional | Liderazgo | Profe Andrés, gracias por coordinar la Semana de la Ingeniería en el campus de Rionegro. | Reportado | 22/05/2026 11:30 | luisa.ospina@uco.edu.co-22/05/2026 11:30 |
| 4 | mateo.giraldo@uco.edu.co | diego.montoya@uco.edu.co | Servicio a la comunidad | Servicio a la comunidad | Felicitaciones al profe Diego por ganar el premio nacional de robótica 2026. | Vetado | 02/06/2026 16:45 | mateo.giraldo@uco.edu.co-02/06/2026 16:45 |
| 5 | carlos.zapata@uco.edu.co | luisa.ospina@uco.edu.co | Liderazgo institucional | Liderazgo institucional | Luisa reorganizó los horarios del programa y redujo los cruces de clases para más de 300 estudiantes. | En revisión | 25/09/2026 10:10 | carlos.zapata@uco.edu.co-25/09/2026 10:10 |
| 6 | diego.montoya@uco.edu.co | mateo.giraldo@uco.edu.co | Trabajo en equipo | Trabajo en equipo | Mateo, gracias por 'ayudarnos' en el parcial de Cálculo | Vetado | 01/10/2026 08:00 | diego.montoya@uco.edu.co-01/10/2026 08:00 |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único del Kudo dentro del sistema. |
| `autor` | Representa la persona que otorga el reconocimiento. |
| `destinatario` | Representa la persona que recibe el reconocimiento. |
| `categoria` | Representa la categoría institucional en la que se clasifica el reconocimiento. |
| `nombreCategoria` | Representa el nombre que tenía la categoría en el momento de crear el Kudo; no cambia aunque la categoría se renombre. |
| `contenido` | Representa el texto completo del reconocimiento. |
| `estadoContenido` | Representa en qué estado del ciclo de vida se encuentra el Kudo. |
| `fechaCreacion` | Representa la fecha y hora en que se creó el Kudo. |
| `Combinación única` | Representa que una persona no puede crear dos Kudos en la misma fecha y hora. |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único del Kudo dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `autor` | Representa la persona que otorga el reconocimiento. | [Usuario](Usuario.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Usuario). Lo asigna el sistema con el usuario autenticado; su cuenta debe estar Activa. |
| `destinatario` | Representa la persona que recibe el reconocimiento. | [Usuario](Usuario.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Usuario). Debe ser un usuario con cuenta Activa. |
| `categoria` | Representa la categoría institucional en la que se clasifica el reconocimiento. | [Categoria](Categoria.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Categoría). |
| `nombreCategoria` | Representa el nombre que tenía la categoría en el momento de crear el Kudo; no cambia aunque la categoría se renombre. | Texto | Si | 3 | 50 | Igual al nombre de la categoría. Ej: Liderazgo | Copia el nombre del atributo categoria. |
| `contenido` | Representa el texto completo del reconocimiento. | Alfanumérico | Si | 1 | Sin límite | Texto libre. | Se permite todos los caracteres. No tiene límite de longitud y nunca se trunca. |
| `estadoContenido` | Representa en qué estado del ciclo de vida se encuentra el Kudo. | [EstadoContenido](EstadoContenido.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado (Estado Contenido). Inicia en Creado y pasa a Visible al publicarse (RN-201). Un Kudo Vetado no admite interacción normal (RN-205). |
| `fechaCreacion` | Representa la fecha y hora en que se creó el Kudo. | Fecha hora | Si | No aplica | No aplica | No aplica | La asigna el sistema al crear el Kudo. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `autor` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `destinatario` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `categoria` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `nombreCategoria` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | No | No | No | Vacio | No |
| `contenido` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |
| `estadoContenido` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `fechaCreacion` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | 01/01/1000 00:00 | Si |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única | Representa que una persona no puede crear dos Kudos en la misma fecha y hora. | `autor, fechaCreacion` | Compara el correo del autor y la fecha y hora exactas. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`crearKudo`](#crearkudo) | [`consultarKudo`](#consultarkudo) | [`consultarKudos`](#consultarkudos) | [`marcarKudoReportado`](#marcarkudoreportado) | [`ponerKudoEnRevision`](#ponerkudoenrevision) | [`vetarKudo`](#vetarkudo) | [`liberarKudo`](#liberarkudo) |
| --- | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| `id` | NR | 👁 | 👁 | R·NM | R·NM | R·NM | R·NM |
| `autor` | NR | 👁 | 👁 | NR·NM | NR·NM | NR·NM | NR·NM |
| `destinatario` | R | 👁 | 👁 | NR·NM | NR·NM | NR·NM | NR·NM |
| `categoria` | R | 👁 | 👁 | NR·NM | NR·NM | NR·NM | NR·NM |
| `nombreCategoria` | NR | 👁 | 👁 | NR·NM | NR·NM | NR·NM | NR·NM |
| `contenido` | R | 👁 | 👁 | NR·NM | NR·NM | NR·NM | NR·NM |
| `estadoContenido` | NR | 👁 | 👁 | NR·M | NR·M | NR·M | NR·M |
| `fechaCreacion` | NR | 👁 | 👁 | NR·NM | NR·NM | NR·NM | NR·NM |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`crearKudo`](#crearkudo) | Otorgar un reconocimiento a otra persona de la comunidad, en una categoría activa. Queda visible para la comunidad autenticada (CU-001). |
| 2 | [`consultarKudo`](#consultarkudo) | Ver un Kudo completo: autor, destinatario, fecha, categoría con la que se creó y contenido (CU-002). |
| 3 | [`consultarKudos`](#consultarkudos) | Consultar el historial de Kudos: los que otorgó o recibió una persona, o los de una categoría (CU-003, RF-048 a RF-050). |
| 4 | [`marcarKudoReportado`](#marcarkudoreportado) | Pasar el Kudo de Visible a Reportado. Lo hace el sistema cuando el Kudo recibe su primer reporte pendiente. |
| 5 | [`ponerKudoEnRevision`](#ponerkudoenrevision) | Pasar el Kudo de Reportado a En revisión. Lo hace el sistema cuando el Rector o el Coordinador Académico inicia la revisión. |
| 6 | [`vetarKudo`](#vetarkudo) | Pasar el Kudo a Vetado: deja de estar disponible para la interacción normal. Lo hace el sistema cuando una revisión se cierra con veto o cuando la autoridad veta directamente. No necesita atributos propios del veto: quién, cuándo y por qué quedan en la revisión. |
| 7 | [`liberarKudo`](#liberarkudo) | Devolver el Kudo a Visible. Lo hace el sistema cuando una revisión se cierra sin veto. |

### `crearKudo`

Otorgar un reconocimiento a otra persona de la comunidad, en una categoría activa. Queda visible para la comunidad autenticada (CU-001).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{destinatario, categoria, contenido}` | [Kudo](Kudo.md) | A quién se reconoce, en qué categoría y el texto del reconocimiento. |

**Salida:** [Kudo](Kudo.md) — El Kudo creado y visible.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-KUD-0014](#p-kud-0014) | Asegurar que el destinatario, la categoría y el contenido sean válidos en tipo, longitud, obligatoriedad y formato; el contenido no tiene longitud máxima y nunca se trunca (RN-152, RN-153). | Se informa cuál dato no cumple y cómo debe escribirse. | 🛑 Detener operación |
| 3 | [P-USU-0009](Usuario.md#p-usu-0009) | Asegurar que la persona indicada exista y tenga la cuenta Activa. | La persona indicada no existe o su cuenta no está activa. | 🛑 Detener operación |
| 4 | [P-KUD-0015](#p-kud-0015) | Asegurar que el destinatario sea una persona distinta del autor: un Kudo se otorga "a otra" persona (glosario). | No puede otorgarse un Kudo a usted mismo. | 🛑 Detener operación |
| 5 | [P-CAT-0001](Categoria.md#p-cat-0001) | Asegurar que exista una categoría con el identificador indicado. | No existe la categoría que se indicó. | 🛑 Detener operación |
| 6 | [P-CAT-0008](Categoria.md#p-cat-0008) | Asegurar que la categoría esté activa (RN-144, INV-019). | La categoría está inactiva y no puede usarse. | 🛑 Detener operación |
| 7 | [P-KUD-0019](#p-kud-0019) | Asegurar que el autor no tenga otro Kudo creado en la misma fecha y hora. | Ya se registró ese Kudo. | 🛑 Detener operación |
| 8 | [P-KUD-0013](#p-kud-0013) | Asegurar que el identificador del Kudo sea único dentro de los Kudos; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 9 | [P-KUD-0017](#p-kud-0017) | Asignar al Kudo el autor (quien lo crea), la fecha de creación y el estado Creado (RN-201). | No se pudo crear el Kudo; se deshace toda la operación. | 🛑 Detener operación |
| 10 | [P-KUD-0016](#p-kud-0016) | Congelar en el Kudo el nombre que tiene la categoría en el momento de crearlo (RN-145). | No se pudo crear el Kudo; se deshace toda la operación. | 🛑 Detener operación |
| 11 | [P-KUD-0018](#p-kud-0018) | Cuando el Kudo quede registrado, pasarlo de Creado a Visible para la comunidad autenticada (CU-001, RN-155). | No se pudo publicar el Kudo; se deshace toda la operación. | 🛑 Detener operación |
| 12 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `consultarKudo`

Ver un Kudo completo: autor, destinatario, fecha, categoría con la que se creó y contenido (CU-002).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador del Kudo. |

**Salida:** [Kudo](Kudo.md) — El Kudo con todos sus datos.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-KUD-0001](#p-kud-0001) | Asegurar que exista un Kudo con el identificador indicado. | No existe el Kudo que se indicó. | 🛑 Detener operación |
| 3 | [P-KUD-0012](#p-kud-0012) | Cuando el Kudo esté Vetado, mostrarlo a la comunidad con el aviso "contenido vetado" y sin su contenido; solo el Rector y el Coordinador Académico ven el contenido completo. La conversación se conserva (RN-205, INV-035). | No aplica: se muestra el aviso en lugar del contenido. | ▶️ Continuar operación |

### `consultarKudos`

Consultar el historial de Kudos: los que otorgó o recibió una persona, o los de una categoría (CU-003, RF-048 a RF-050).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{autor, destinatario, categoria, estadoContenido}` | [Kudo](Kudo.md) | Datos opcionales para filtrar la consulta. |

**Salida:** [Lista&lt;Kudo&gt;](Kudo.md) — Los Kudos que cumplen el filtro.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-KUD-0012](#p-kud-0012) | Cuando el Kudo esté Vetado, mostrarlo a la comunidad con el aviso "contenido vetado" y sin su contenido; solo el Rector y el Coordinador Académico ven el contenido completo. La conversación se conserva (RN-205, INV-035). | No aplica: se muestra el aviso en lugar del contenido. | ▶️ Continuar operación |

### `marcarKudoReportado`

Pasar el Kudo de Visible a Reportado. Lo hace el sistema cuando el Kudo recibe su primer reporte pendiente.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador del Kudo. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-KUD-0001](#p-kud-0001) | Asegurar que exista un Kudo con el identificador indicado. | No existe el Kudo que se indicó. | 🛑 Detener operación |
| 2 | [P-KUD-0007](#p-kud-0007) | Asegurar que el Kudo esté Visible antes de pasarlo a Reportado. | No aplica: si ya estaba Reportado, conserva su estado. | ▶️ Continuar operación |

### `ponerKudoEnRevision`

Pasar el Kudo de Reportado a En revisión. Lo hace el sistema cuando el Rector o el Coordinador Académico inicia la revisión.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador del Kudo. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-KUD-0001](#p-kud-0001) | Asegurar que exista un Kudo con el identificador indicado. | No existe el Kudo que se indicó. | 🛑 Detener operación |
| 2 | [P-KUD-0004](#p-kud-0004) | Asegurar que el Kudo esté Reportado antes de iniciar su revisión (RN-203). | El Kudo no tiene reportes pendientes por revisar. | 🛑 Detener operación |

### `vetarKudo`

Pasar el Kudo a Vetado: deja de estar disponible para la interacción normal. Lo hace el sistema cuando una revisión se cierra con veto o cuando la autoridad veta directamente. No necesita atributos propios del veto: quién, cuándo y por qué quedan en la revisión.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador del Kudo. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-KUD-0001](#p-kud-0001) | Asegurar que exista un Kudo con el identificador indicado. | No existe el Kudo que se indicó. | 🛑 Detener operación |
| 2 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `liberarKudo`

Devolver el Kudo a Visible. Lo hace el sistema cuando una revisión se cierra sin veto.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador del Kudo. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-KUD-0001](#p-kud-0001) | Asegurar que exista un Kudo con el identificador indicado. | No existe el Kudo que se indicó. | 🛑 Detener operación |
| 2 | [P-KUD-0005](#p-kud-0005) | Asegurar que el Kudo esté En revisión antes de vetarlo o liberarlo por el cierre de una revisión. | El Kudo no está en revisión. | 🛑 Detener operación |
| 3 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Usuario autenticado | **Crear el Kudo**<br>Otorgar un reconocimiento a otra persona de la comunidad, en una categoría activa. Queda visible para la comunidad autenticada (CU-001). | **Personas con cuenta activa**: Para elegir al destinatario.<br>**Categorías activas**: Para clasificar el reconocimiento. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-KUD-0014](#p-kud-0014)<br>[P-USU-0009](Usuario.md#p-usu-0009)<br>[P-KUD-0015](#p-kud-0015)<br>[P-CAT-0001](Categoria.md#p-cat-0001)<br>[P-CAT-0008](Categoria.md#p-cat-0008)<br>[P-KUD-0019](#p-kud-0019)<br>[P-KUD-0013](#p-kud-0013)<br>[P-KUD-0017](#p-kud-0017)<br>[P-KUD-0016](#p-kud-0016)<br>[P-KUD-0018](#p-kud-0018)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Kudo creado** | Ninguno |
| Usuario autenticado | **Consultar el Kudo**<br>Ver un Kudo completo: autor, destinatario, fecha, categoría con la que se creó y contenido (CU-002). | **Conversación del Kudo (réplicas)**: Para mostrarla junto al reconocimiento.<br>**Calificaciones del Kudo y su promedio**: Para mostrar la valoración de la comunidad. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-KUD-0001](#p-kud-0001)<br>[P-KUD-0012](#p-kud-0012) | No aplica | 🟧 **Kudo consultado** | Ninguno |
| Usuario autenticado | **Consultar el historial de Kudos**<br>Consultar el historial de Kudos: los que otorgó o recibió una persona, o los de una categoría (CU-003, RF-048 a RF-050). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-KUD-0012](#p-kud-0012) | No aplica | 🟧 **Historial de Kudos consultado** | Ninguno |
| Sistema | **Marcar el Kudo como reportado**<br>Pasar el Kudo de Visible a Reportado. Lo hace el sistema cuando el Kudo recibe su primer reporte pendiente. | **No aplica**: No aplica | [P-KUD-0001](#p-kud-0001)<br>[P-KUD-0007](#p-kud-0007) | No aplica | 🟧 **Kudo reportado** | Ninguno |
| Sistema | **Poner el Kudo en revisión**<br>Pasar el Kudo de Reportado a En revisión. Lo hace el sistema cuando el Rector o el Coordinador Académico inicia la revisión. | **No aplica**: No aplica | [P-KUD-0001](#p-kud-0001)<br>[P-KUD-0004](#p-kud-0004) | No aplica | 🟧 **Kudo en revisión** | Ninguno |
| Sistema | **Vetar el Kudo**<br>Pasar el Kudo a Vetado: deja de estar disponible para la interacción normal. Lo hace el sistema cuando una revisión se cierra con veto o cuando la autoridad veta directamente. No necesita atributos propios del veto: quién, cuándo y por qué quedan en la revisión. | **No aplica**: No aplica | [P-KUD-0001](#p-kud-0001)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Kudo vetado** | Ninguno |
| Sistema | **Liberar el Kudo tras la revisión**<br>Devolver el Kudo a Visible. Lo hace el sistema cuando una revisión se cierra sin veto. | **No aplica**: No aplica | [P-KUD-0001](#p-kud-0001)<br>[P-KUD-0005](#p-kud-0005)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Kudo liberado tras revisión** | Ninguno |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="p-kud-0001"></a>**P-KUD-0001** | Asegurar que exista un Kudo con el identificador indicado. | Kudo | `AsegurarKudoExisteRule` |
| <a id="p-kud-0002"></a>**P-KUD-0002** | Asegurar que el Kudo esté Visible, Reportado o En revisión; un contenido Vetado no admite réplicas ni calificaciones (RN-205). | Kudo | `AsegurarKudoAdmiteInteraccionRule` |
| <a id="p-kud-0003"></a>**P-KUD-0003** | Asegurar que el Kudo esté Visible o Reportado para poder reportarlo (RN-202). | Kudo | `AsegurarKudoPuedeReportarseRule` |
| <a id="p-kud-0004"></a>**P-KUD-0004** | Asegurar que el Kudo esté Reportado antes de iniciar su revisión (RN-203). | Kudo | `AsegurarKudoReportadoRule` |
| <a id="p-kud-0005"></a>**P-KUD-0005** | Asegurar que el Kudo esté En revisión antes de vetarlo o liberarlo por el cierre de una revisión. | Kudo | `AsegurarKudoEnRevisionRule` |
| <a id="p-kud-0006"></a>**P-KUD-0006** | Asegurar que el Kudo esté Visible o Reportado para vetarlo directamente; si está En revisión se cierra su revisión y si ya está Vetado no se repite. | Kudo | `AsegurarKudoPuedeVetarseDirectamenteRule` |
| <a id="p-kud-0007"></a>**P-KUD-0007** | Asegurar que el Kudo esté Visible antes de pasarlo a Reportado. | Kudo | `AsegurarKudoVisibleParaReportarRule` |
| <a id="p-kud-0008"></a>**P-KUD-0008** | Cuando un Kudo Visible reciba un reporte, pasarlo a Reportado (RN-202). | Kudo | `MarcarKudoReportadoAlRecibirReporteRule` |
| <a id="p-kud-0009"></a>**P-KUD-0009** | Cuando se inicie una revisión, pasar el Kudo de Reportado a En revisión (RN-203). | Kudo | `PonerKudoEnRevisionAlIniciarRevisionRule` |
| <a id="p-kud-0010"></a>**P-KUD-0010** | Cuando una revisión se cierre con veto, o se vete directamente, pasar el Kudo a Vetado (RN-204, RN-205). | Kudo | `VetarKudoAlCerrarRevisionConVetoRule` |
| <a id="p-kud-0011"></a>**P-KUD-0011** | Cuando una revisión se cierre liberando el contenido, devolver el Kudo a Visible (RN-242). | Kudo | `LiberarKudoAlCerrarRevisionSinVetoRule` |
| <a id="p-kud-0012"></a>**P-KUD-0012** | Cuando el Kudo esté Vetado, mostrarlo a la comunidad con el aviso "contenido vetado" y sin su contenido; solo el Rector y el Coordinador Académico ven el contenido completo. La conversación se conserva (RN-205, INV-035). | Kudo | `MostrarAvisoEnKudoVetadoRule` |
| <a id="p-kud-0013"></a>**P-KUD-0013** | Asegurar que el identificador del Kudo sea único dentro de los Kudos; si ya existe, generar otro. | Kudo | `AsegurarIdKudoUnicoRule` |
| <a id="p-kud-0014"></a>**P-KUD-0014** | Asegurar que el destinatario, la categoría y el contenido sean válidos en tipo, longitud, obligatoriedad y formato; el contenido no tiene longitud máxima y nunca se trunca (RN-152, RN-153). | Kudo | `AsegurarFormatoKudoValidoRule` |
| <a id="p-kud-0015"></a>**P-KUD-0015** | Asegurar que el destinatario sea una persona distinta del autor: un Kudo se otorga "a otra" persona (glosario). | Kudo | `AsegurarDestinatarioDistintoDelAutorRule` |
| <a id="p-kud-0016"></a>**P-KUD-0016** | Congelar en el Kudo el nombre que tiene la categoría en el momento de crearlo (RN-145). | Kudo | `CongelarNombreCategoriaEnKudoRule` |
| <a id="p-kud-0017"></a>**P-KUD-0017** | Asignar al Kudo el autor (quien lo crea), la fecha de creación y el estado Creado (RN-201). | Kudo | `AsignarDatosInicialesKudoRule` |
| <a id="p-kud-0018"></a>**P-KUD-0018** | Cuando el Kudo quede registrado, pasarlo de Creado a Visible para la comunidad autenticada (CU-001, RN-155). | Kudo | `PublicarKudoAlConfirmarRule` |
| <a id="p-kud-0019"></a>**P-KUD-0019** | Asegurar que el autor no tenga otro Kudo creado en la misma fecha y hora. | Kudo | `AsegurarKudoNoRepetidoRule` |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ VinculacionAcademica](VinculacionAcademica.md) · [Replica ➡️](Replica.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/Kudo.md) · [📐 Clase](../clases/Kudo.md)
