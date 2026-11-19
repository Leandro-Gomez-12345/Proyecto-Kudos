# 🧩 Auditoria

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ ProgramaAcademico](ProgramaAcademico.md) · [CoordinacionFacultad ➡️](CoordinacionFacultad.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/Auditoria.md) · [📐 Clase](../clases/Auditoria.md)

> Registro histórico, de solo lectura, de cada acción relevante: quién la hizo, qué hizo, sobre qué registro y cuándo. Se puede consultar.

| Línea de vida | Atributos | Responsabilidades | Eventos | Políticas propias |
| :---: | :---: | :---: | :---: | :---: |
| Nivel 3 | 7 | 3 | 3 | 8 |

## Contenido

1. [Muestreo](#1-muestreo)
2. [Caracterización](#2-caracterización)
3. [Responsabilidades](#3-responsabilidades)
4. [Event Storming](#4-event-storming)
5. [Políticas](#5-políticas)

---

## 1. Muestreo

Ejemplos reales del objeto para validar sus atributos con el negocio.

| **id** | **usuario** | **accion** | **objetoAfectado** | **registroAfectado** | **detalle** | **fechaHora** | **Combinación única** |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | luisa.ospina@uco.edu.co | Aprobar usuario | Usuario | 10 | Aprobó el registro de Valentina Arango Duque como Estudiante: estadoCuenta Pendiente de aprobación → Aprobada. | 03/02/2026 09:10 | luisa.ospina@uco.edu.co-Aprobar usuario-03/02/2026 09:10 |
| 2 | ana.gomez@uco.edu.co | Modificar categoría | Categoria | 1 | nombre: 'Liderazgo' → 'Liderazgo institucional' | 20/08/2026 15:40 | ana.gomez@uco.edu.co-Modificar categoría-20/08/2026 15:40 |
| 3 | jorge.ramirez@uco.edu.co | Desactivar categoría | Categoria | 5 | La categoría Innovación queda inactiva para nuevos Kudos. | 01/09/2026 10:00 | jorge.ramirez@uco.edu.co-Desactivar categoría-01/09/2026 10:00 |
| 4 | valentina.arango@uco.edu.co | Actualizar celular | Usuario | 10 | Celular actualizado; queda pendiente de validación (RN-111). | 15/09/2026 18:05 | valentina.arango@uco.edu.co-Actualizar celular-15/09/2026 18:05 |
| 5 | ana.gomez@uco.edu.co | Cerrar revisión de Kudo | RevisionKudo | 1 | Contenido vetado: la información publicada es falsa. | 03/06/2026 10:30 | ana.gomez@uco.edu.co-Cerrar revisión de Kudo-03/06/2026 10:30 |
| 6 | luisa.ospina@uco.edu.co | Rechazar usuario | Usuario | 13 | estadoCuenta Pendiente de aprobación → Rechazada. Justificación: El correo no corresponde a un estudiante matriculado en Ingeniería de Sistemas. | 04/11/2026 10:00 | luisa.ospina@uco.edu.co-Rechazar usuario-04/11/2026 10:00 |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único del registro de auditoría dentro del sistema. |
| `usuario` | Representa la persona que ejecutó la acción auditada. |
| `accion` | Representa la acción del negocio que se ejecutó, en lenguaje de negocio. |
| `objetoAfectado` | Representa el tipo de objeto de dominio sobre el que se ejecutó la acción. |
| `registroAfectado` | Representa el identificador del registro concreto que fue afectado por la acción. |
| `detalle` | Representa la descripción de lo que cambió, para poder reconstruir la decisión. |
| `fechaHora` | Representa la fecha y hora exactas en que se ejecutó la acción. |
| `Combinación única` | Representa que una persona no puede ejecutar la misma acción dos veces en la misma fecha y hora. |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único del registro de auditoría dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `usuario` | Representa la persona que ejecutó la acción auditada. | [Usuario](Usuario.md) | Si | No aplica | No aplica | Identificador único universal de Usuario | Aplican las reglas del objeto referenciado (Usuario). Lo asigna el sistema con el usuario autenticado que ejecuta la acción. |
| `accion` | Representa la acción del negocio que se ejecutó, en lenguaje de negocio. | Alfanumérico | Si | 5 | 100 | Ej: Aprobar usuario | Verbo en infinitivo + objeto. Lo asigna el sistema. |
| `objetoAfectado` | Representa el tipo de objeto de dominio sobre el que se ejecutó la acción. | Texto | Si | 3 | 30 | Nombre del objeto. Ej: Kudo | Debe ser el nombre de un objeto de dominio del modelo. |
| `registroAfectado` | Representa el identificador del registro concreto que fue afectado por la acción. | Identificador único universal | Si | No aplica | No aplica | No aplica | Identificador de un registro existente del objeto indicado en objetoAfectado. No es una referencia fija porque puede apuntar a cualquier objeto. |
| `detalle` | Representa la descripción de lo que cambió, para poder reconstruir la decisión. | Alfanumérico | Si | 5 | 200 | No aplica | Lo genera el sistema y siempre se registra: describe lo que cambió (valor anterior → valor nuevo) y, cuando aplica, la justificación (RN-250). Nunca incluye datos sensibles (correo o celular completos). |
| `fechaHora` | Representa la fecha y hora exactas en que se ejecutó la acción. | Fecha hora | Si | No aplica | No aplica | No aplica | La asigna el sistema y no cambia. Un registro de auditoría nunca se modifica ni se elimina. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `usuario` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `accion` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |
| `objetoAfectado` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |
| `registroAfectado` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `detalle` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |
| `fechaHora` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | 01/01/1000 00:00 | Si |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única | Representa que una persona no puede ejecutar la misma acción dos veces en la misma fecha y hora. | `usuario, accion, fechaHora` | Compara exactamente. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`registrarAuditoria`](#registrarauditoria) | [`consultarAuditorias`](#consultarauditorias) | [`consultarAuditoriasPropias`](#consultarauditoriaspropias) |
| --- | :---: | :---: | :---: |
| `id` | NR | 👁 | 👁 |
| `usuario` | R | 👁 | 👁 |
| `accion` | R | 👁 | 👁 |
| `objetoAfectado` | R | 👁 | 👁 |
| `registroAfectado` | R | 👁 | 👁 |
| `detalle` | R | 👁 | 👁 |
| `fechaHora` | NR | 👁 | 👁 |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`registrarAuditoria`](#registrarauditoria) | Dejar constancia de un evento del negocio que ya ocurrió. Lo usan todas las responsabilidades que cambian algo, dentro de la misma operación: si la operación falla, no queda auditoría. |
| 2 | [`consultarAuditorias`](#consultarauditorias) | Consultar el registro histórico de la plataforma, por ejemplo el histórico de decisiones de moderación (CU-015, RF-070). |
| 3 | [`consultarAuditoriasPropias`](#consultarauditoriaspropias) | Consultar el historial de actividad propio (RN-106). |

### `registrarAuditoria`

Dejar constancia de un evento del negocio que ya ocurrió. Lo usan todas las responsabilidades que cambian algo, dentro de la misma operación: si la operación falla, no queda auditoría.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{usuario, accion, objetoAfectado, registroAfectado, detalle}` | [Auditoria](Auditoria.md) | Quién ejecutó, qué evento ocurrió, sobre qué registro y el detalle del cambio. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-AUD-0002](#p-aud-0002) | Asegurar que el usuario, la acción, el objeto afectado, el registro afectado y el detalle sean válidos en tipo, longitud, obligatoriedad y formato. | No se registra la auditoría y se deshace la operación que la originó. | 🛑 Detener operación |
| 2 | [P-AUD-0003](#p-aud-0003) | Asegurar que la acción y el objeto afectado correspondan a un evento auditable definido en el Event Storming (catálogo EventoAuditable). | No se registra la auditoría y se deshace la operación que la originó. | 🛑 Detener operación |
| 3 | [P-AUD-0004](#p-aud-0004) | Asegurar que el detalle no contenga el correo ni el celular completos (se guardan enmascarados, RNF-003). | No se registra la auditoría y se deshace la operación que la originó. | 🛑 Detener operación |
| 4 | [P-AUD-0005](#p-aud-0005) | Asegurar que el identificador del registro de auditoría sea único dentro de las auditorías; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 5 | [P-AUD-0006](#p-aud-0006) | Asignar al registro de auditoría la fecha y hora actuales del sistema; después no cambia. | No se registra la auditoría y se deshace la operación que la originó. | 🛑 Detener operación |

### `consultarAuditorias`

Consultar el registro histórico de la plataforma, por ejemplo el histórico de decisiones de moderación (CU-015, RF-070).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{usuario, accion, objetoAfectado, registroAfectado, fechaHora}` | [Auditoria](Auditoria.md) | Datos opcionales para filtrar la consulta. |

**Salida:** [Lista&lt;Auditoria&gt;](Auditoria.md) — Los registros de auditoría que cumplen el filtro.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-AUD-0008](#p-aud-0008) | Asegurar que quien consulta la auditoría completa tenga el rol Administrador, Rector o Coordinador Académico. | No tiene permiso para consultar la auditoría. | 🛑 Detener operación |

### `consultarAuditoriasPropias`

Consultar el historial de actividad propio (RN-106).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{accion, objetoAfectado, fechaHora}` | [Auditoria](Auditoria.md) | Datos opcionales para filtrar la consulta. |

**Salida:** [Lista&lt;Auditoria&gt;](Auditoria.md) — Las acciones ejecutadas por quien consulta.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-AUD-0007](#p-aud-0007) | Asegurar que el historial de actividad muestre solo las acciones ejecutadas por quien consulta (RN-106). | Solo puede consultar su propia actividad. | 🛑 Detener operación |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Sistema | **Registrar la auditoría del evento**<br>Dejar constancia de un evento del negocio que ya ocurrió. Lo usan todas las responsabilidades que cambian algo, dentro de la misma operación: si la operación falla, no queda auditoría. | **No aplica**: No aplica | [P-AUD-0002](#p-aud-0002)<br>[P-AUD-0003](#p-aud-0003)<br>[P-AUD-0004](#p-aud-0004)<br>[P-AUD-0005](#p-aud-0005)<br>[P-AUD-0006](#p-aud-0006) | No aplica | 🟧 **Auditoría registrada** | Ninguno |
| Administrador<br>Rector<br>Coordinador Académico | **Consultar la auditoría**<br>Consultar el registro histórico de la plataforma, por ejemplo el histórico de decisiones de moderación (CU-015, RF-070). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-AUD-0008](#p-aud-0008) | No aplica | 🟧 **Auditorías consultadas** | Ninguno |
| Usuario autenticado | **Consultar su historial de actividad**<br>Consultar el historial de actividad propio (RN-106). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-AUD-0007](#p-aud-0007) | No aplica | 🟧 **Historial de actividad consultado** | Ninguno |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="p-aud-0001"></a>**P-AUD-0001** | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | Auditoria | `RegistrarEventoEnAuditoriaRule` |
| <a id="p-aud-0002"></a>**P-AUD-0002** | Asegurar que el usuario, la acción, el objeto afectado, el registro afectado y el detalle sean válidos en tipo, longitud, obligatoriedad y formato. | Auditoria | `AsegurarFormatoAuditoriaValidoRule` |
| <a id="p-aud-0003"></a>**P-AUD-0003** | Asegurar que la acción y el objeto afectado correspondan a un evento auditable definido en el Event Storming (catálogo EventoAuditable). | Auditoria | `AsegurarAccionEsEventoAuditableRule` |
| <a id="p-aud-0004"></a>**P-AUD-0004** | Asegurar que el detalle no contenga el correo ni el celular completos (se guardan enmascarados, RNF-003). | Auditoria | `AsegurarDetalleSinDatosSensiblesRule` |
| <a id="p-aud-0005"></a>**P-AUD-0005** | Asegurar que el identificador del registro de auditoría sea único dentro de las auditorías; si ya existe, generar otro. | Auditoria | `AsegurarIdAuditoriaUnicoRule` |
| <a id="p-aud-0006"></a>**P-AUD-0006** | Asignar al registro de auditoría la fecha y hora actuales del sistema; después no cambia. | Auditoria | `AsignarFechaHoraAuditoriaRule` |
| <a id="p-aud-0007"></a>**P-AUD-0007** | Asegurar que el historial de actividad muestre solo las acciones ejecutadas por quien consulta (RN-106). | Auditoria | `AsegurarConsultaSoloActividadPropiaRule` |
| <a id="p-aud-0008"></a>**P-AUD-0008** | Asegurar que quien consulta la auditoría completa tenga el rol Administrador, Rector o Coordinador Académico. | Auditoria | `AsegurarUsuarioPuedeConsultarAuditoriaRule` |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ ProgramaAcademico](ProgramaAcademico.md) · [CoordinacionFacultad ➡️](CoordinacionFacultad.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/Auditoria.md) · [📐 Clase](../clases/Auditoria.md)
