# 🧩 Categoria

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ Motivo](Motivo.md) · [Facultad ➡️](Facultad.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/Categoria.md) · [📐 Clase](../clases/Categoria.md)

> Clasificación institucional de los Kudos. La gestionan Administrador, Rector y Coordinador Académico; una categoría inactiva no se usa en nuevos Kudos.

| Línea de vida | Atributos | Responsabilidades | Eventos | Políticas propias |
| :---: | :---: | :---: | :---: | :---: |
| Nivel 1 | 4 | 6 | 6 | 10 |

## Contenido

1. [Muestreo](#1-muestreo)
2. [Caracterización](#2-caracterización)
3. [Responsabilidades](#3-responsabilidades)
4. [Event Storming](#4-event-storming)
5. [Políticas](#5-políticas)

---

## 1. Muestreo

Ejemplos reales del objeto para validar sus atributos con el negocio.

| **id** | **nombre** | **descripcion** | **activa** | **Combinación única** |
| --- | --- | --- | --- | --- |
| 1 | Liderazgo institucional | Reconoce a quien guía procesos o equipos que aportan al proyecto educativo de la UCO. | Si | Liderazgo institucional |
| 2 | Excelencia académica | Reconoce logros académicos sobresalientes en cursos, semilleros o investigaciones. | Si | Excelencia académica |
| 3 | Trabajo en equipo | Reconoce la colaboración, el apoyo a los compañeros y la construcción colectiva. | Si | Trabajo en equipo |
| 4 | Servicio a la comunidad | Reconoce aportes al bienestar de la comunidad universitaria y del Oriente antioqueño. | Si | Servicio a la comunidad |
| 5 | Innovación | Reconoce ideas o proyectos nuevos que mejoran procesos de la universidad. | No | Innovación |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único de la categoría dentro del sistema. |
| `nombre` | Representa el nombre con el que la universidad identifica la categoría de reconocimiento. |
| `descripcion` | Representa la explicación de qué tipo de conductas o logros se reconocen con esta categoría. |
| `activa` | Representa si la categoría puede usarse para crear nuevos Kudos. |
| `Combinación única` | Representa que no pueden existir dos categorías con el mismo nombre. |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único de la categoría dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `nombre` | Representa el nombre con el que la universidad identifica la categoría de reconocimiento. | Texto | Si | 3 | 50 | Primera letra en mayúscula. Ej: Trabajo en equipo | Solo letras, tildes y espacios. |
| `descripcion` | Representa la explicación de qué tipo de conductas o logros se reconocen con esta categoría. | Alfanumérico | Si | 10 | 300 | Texto libre en una o varias oraciones. | Letras mayusculas y minusculas, números, tildes, espacios y signos de puntuación (. , ; : ¿ ? ¡ ! ( ) -). |
| `activa` | Representa si la categoría puede usarse para crear nuevos Kudos. | Lógico | Si | No aplica | No aplica | Si / No | Si: se puede usar en nuevos Kudos. No: solo se conserva para los Kudos históricos. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `nombre` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |
| `descripcion` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |
| `activa` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | Si | No |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única | Representa que no pueden existir dos categorías con el mismo nombre. | `nombre` | No distingue mayúsculas, minúsculas, tildes ni espacios repetidos. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`registrarCategoria`](#registrarcategoria) | [`consultarCategorias`](#consultarcategorias) | [`cambiarNombreCategoria`](#cambiarnombrecategoria) | [`cambiarDescripcionCategoria`](#cambiardescripcioncategoria) | [`activarCategoria`](#activarcategoria) | [`desactivarCategoria`](#desactivarcategoria) |
| --- | :---: | :---: | :---: | :---: | :---: | :---: |
| `id` | NR | 👁 | R·NM | R·NM | R·NM | R·NM |
| `nombre` | R | 👁 | R·M | NR·NM | NR·NM | NR·NM |
| `descripcion` | R | 👁 | NR·NM | R·M | NR·NM | NR·NM |
| `activa` | NR | 👁 | NR·NM | NR·NM | NR·M | NR·M |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`registrarCategoria`](#registrarcategoria) | Crear una nueva categoría institucional para clasificar los Kudos. Nace activa. |
| 2 | [`consultarCategorias`](#consultarcategorias) | Consultar las categorías, activas o inactivas. Al otorgar un Kudo solo se ofrecen las activas; las inactivas siguen visibles para los Kudos históricos (INV-018). |
| 3 | [`cambiarNombreCategoria`](#cambiarnombrecategoria) | Cambiar el nombre de una categoría. Los Kudos ya creados conservan el nombre que tenía cuando se crearon (RN-145). |
| 4 | [`cambiarDescripcionCategoria`](#cambiardescripcioncategoria) | Cambiar la descripción que explica cuándo usar la categoría. |
| 5 | [`activarCategoria`](#activarcategoria) | Volver a habilitar una categoría inactiva para que pueda usarse en nuevos Kudos. |
| 6 | [`desactivarCategoria`](#desactivarcategoria) | Dejar de ofrecer una categoría para nuevos Kudos: desde ese momento no aparece entre las opciones al otorgar un Kudo. Los Kudos históricos la conservan (RN-144, RN-145). Siempre debe quedar al menos una categoría activa. |

### `registrarCategoria`

Crear una nueva categoría institucional para clasificar los Kudos. Nace activa.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{nombre, descripcion}` | [Categoria](Categoria.md) | Nombre y descripción de la categoría nueva. |

**Salida:** [Categoria](Categoria.md) — La categoría registrada.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0002](Usuario.md#p-usu-0002) | Asegurar que quien ejecuta la acción tenga el rol Administrador, Rector o Coordinador Académico (RN-134 a RN-143). | Solo el Administrador, el Rector y el Coordinador Académico pueden gestionar categorías. | 🛑 Detener operación |
| 3 | [P-CAT-0002](#p-cat-0002) | Asegurar que el nombre y la descripción de la categoría sean válidos en tipo, longitud, obligatoriedad y formato. | Se informa cuál dato no cumple y cómo debe escribirse. | 🛑 Detener operación |
| 4 | [P-CAT-0005](#p-cat-0005) | Asegurar que no exista otra categoría con el mismo nombre. | Ya existe una categoría con ese nombre. | 🛑 Detener operación |
| 5 | [P-CAT-0006](#p-cat-0006) | Asegurar que el identificador de la categoría sea único dentro de las categorías; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 6 | [P-CAT-0007](#p-cat-0007) | Asignar a la categoría nueva el estado activa, para que pueda usarse de inmediato en nuevos Kudos (RN-133). | No se pudo registrar la categoría; se deshace toda la operación. | 🛑 Detener operación |
| 7 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `consultarCategorias`

Consultar las categorías, activas o inactivas. Al otorgar un Kudo solo se ofrecen las activas; las inactivas siguen visibles para los Kudos históricos (INV-018).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{nombre, activa}` | [Categoria](Categoria.md) | Datos opcionales para filtrar la consulta. |

**Salida:** [Lista&lt;Categoria&gt;](Categoria.md) — Las categorías que cumplen el filtro.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |

### `cambiarNombreCategoria`

Cambiar el nombre de una categoría. Los Kudos ya creados conservan el nombre que tenía cuando se crearon (RN-145).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la categoría. |
| `datos{nombre}` | [Categoria](Categoria.md) | Nombre nuevo. |

**Salida:** [Categoria](Categoria.md) — La categoría con su nombre nuevo.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0002](Usuario.md#p-usu-0002) | Asegurar que quien ejecuta la acción tenga el rol Administrador, Rector o Coordinador Académico (RN-134 a RN-143). | Solo el Administrador, el Rector y el Coordinador Académico pueden gestionar categorías. | 🛑 Detener operación |
| 3 | [P-CAT-0001](#p-cat-0001) | Asegurar que exista una categoría con el identificador indicado. | No existe la categoría que se indicó. | 🛑 Detener operación |
| 4 | [P-CAT-0003](#p-cat-0003) | Asegurar que el nombre nuevo de la categoría sea válido en tipo, longitud, obligatoriedad y formato. | Se informa cómo debe escribirse el nombre. | 🛑 Detener operación |
| 5 | [P-CAT-0005](#p-cat-0005) | Asegurar que no exista otra categoría con el mismo nombre. | Ya existe una categoría con ese nombre. | 🛑 Detener operación |
| 6 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `cambiarDescripcionCategoria`

Cambiar la descripción que explica cuándo usar la categoría.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la categoría. |
| `datos{descripcion}` | [Categoria](Categoria.md) | Descripción nueva. |

**Salida:** [Categoria](Categoria.md) — La categoría con su descripción nueva.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0002](Usuario.md#p-usu-0002) | Asegurar que quien ejecuta la acción tenga el rol Administrador, Rector o Coordinador Académico (RN-134 a RN-143). | Solo el Administrador, el Rector y el Coordinador Académico pueden gestionar categorías. | 🛑 Detener operación |
| 3 | [P-CAT-0001](#p-cat-0001) | Asegurar que exista una categoría con el identificador indicado. | No existe la categoría que se indicó. | 🛑 Detener operación |
| 4 | [P-CAT-0004](#p-cat-0004) | Asegurar que la descripción nueva de la categoría sea válida en tipo, longitud, obligatoriedad y formato. | Se informa cómo debe escribirse la descripción. | 🛑 Detener operación |
| 5 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `activarCategoria`

Volver a habilitar una categoría inactiva para que pueda usarse en nuevos Kudos.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la categoría. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0002](Usuario.md#p-usu-0002) | Asegurar que quien ejecuta la acción tenga el rol Administrador, Rector o Coordinador Académico (RN-134 a RN-143). | Solo el Administrador, el Rector y el Coordinador Académico pueden gestionar categorías. | 🛑 Detener operación |
| 3 | [P-CAT-0001](#p-cat-0001) | Asegurar que exista una categoría con el identificador indicado. | No existe la categoría que se indicó. | 🛑 Detener operación |
| 4 | [P-CAT-0010](#p-cat-0010) | Asegurar que la categoría esté inactiva antes de activarla. | La categoría ya está activa. | 🛑 Detener operación |
| 5 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `desactivarCategoria`

Dejar de ofrecer una categoría para nuevos Kudos: desde ese momento no aparece entre las opciones al otorgar un Kudo. Los Kudos históricos la conservan (RN-144, RN-145). Siempre debe quedar al menos una categoría activa.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la categoría. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0002](Usuario.md#p-usu-0002) | Asegurar que quien ejecuta la acción tenga el rol Administrador, Rector o Coordinador Académico (RN-134 a RN-143). | Solo el Administrador, el Rector y el Coordinador Académico pueden gestionar categorías. | 🛑 Detener operación |
| 3 | [P-CAT-0001](#p-cat-0001) | Asegurar que exista una categoría con el identificador indicado. | No existe la categoría que se indicó. | 🛑 Detener operación |
| 4 | [P-CAT-0008](#p-cat-0008) | Asegurar que la categoría esté activa (RN-144, INV-019). | La categoría está inactiva y no puede usarse. | 🛑 Detener operación |
| 5 | [P-CAT-0009](#p-cat-0009) | Asegurar que, al desactivar la categoría, quede al menos otra categoría activa: siempre debe haber una categoría para publicar un Kudo (RN-144, RN-149, INV-017). | No puede desactivar la única categoría activa; active o cree otra primero. | 🛑 Detener operación |
| 6 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Administrador<br>Rector<br>Coordinador Académico | **Registrar la categoría**<br>Crear una nueva categoría institucional para clasificar los Kudos. Nace activa. | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0002](Usuario.md#p-usu-0002)<br>[P-CAT-0002](#p-cat-0002)<br>[P-CAT-0005](#p-cat-0005)<br>[P-CAT-0006](#p-cat-0006)<br>[P-CAT-0007](#p-cat-0007)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Categoría creada** | Ninguno |
| Usuario autenticado | **Consultar las categorías**<br>Consultar las categorías, activas o inactivas. Al otorgar un Kudo solo se ofrecen las activas; las inactivas siguen visibles para los Kudos históricos (INV-018). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001) | No aplica | 🟧 **Categorías consultadas** | Ninguno |
| Administrador<br>Rector<br>Coordinador Académico | **Cambiar el nombre de la categoría**<br>Cambiar el nombre de una categoría. Los Kudos ya creados conservan el nombre que tenía cuando se crearon (RN-145). | **Kudos que usan la categoría**: Para informar que conservarán el nombre anterior. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0002](Usuario.md#p-usu-0002)<br>[P-CAT-0001](#p-cat-0001)<br>[P-CAT-0003](#p-cat-0003)<br>[P-CAT-0005](#p-cat-0005)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Categoría modificada (nombre)** | Ninguno |
| Administrador<br>Rector<br>Coordinador Académico | **Cambiar la descripción de la categoría**<br>Cambiar la descripción que explica cuándo usar la categoría. | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0002](Usuario.md#p-usu-0002)<br>[P-CAT-0001](#p-cat-0001)<br>[P-CAT-0004](#p-cat-0004)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Categoría modificada (descripción)** | Ninguno |
| Administrador<br>Rector<br>Coordinador Académico | **Activar la categoría**<br>Volver a habilitar una categoría inactiva para que pueda usarse en nuevos Kudos. | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0002](Usuario.md#p-usu-0002)<br>[P-CAT-0001](#p-cat-0001)<br>[P-CAT-0010](#p-cat-0010)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Categoría activada** | Ninguno |
| Administrador<br>Rector<br>Coordinador Académico | **Desactivar la categoría**<br>Dejar de ofrecer una categoría para nuevos Kudos: desde ese momento no aparece entre las opciones al otorgar un Kudo. Los Kudos históricos la conservan (RN-144, RN-145). Siempre debe quedar al menos una categoría activa. | **Categorías activas**: Para comprobar que quede al menos otra activa. | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0002](Usuario.md#p-usu-0002)<br>[P-CAT-0001](#p-cat-0001)<br>[P-CAT-0008](#p-cat-0008)<br>[P-CAT-0009](#p-cat-0009)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Categoría desactivada** | Ninguno |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="p-cat-0001"></a>**P-CAT-0001** | Asegurar que exista una categoría con el identificador indicado. | Categoria | `AsegurarCategoriaExisteRule` |
| <a id="p-cat-0002"></a>**P-CAT-0002** | Asegurar que el nombre y la descripción de la categoría sean válidos en tipo, longitud, obligatoriedad y formato. | Categoria | `AsegurarFormatoCategoriaValidoRule` |
| <a id="p-cat-0003"></a>**P-CAT-0003** | Asegurar que el nombre nuevo de la categoría sea válido en tipo, longitud, obligatoriedad y formato. | Categoria | `AsegurarFormatoNombreCategoriaValidoRule` |
| <a id="p-cat-0004"></a>**P-CAT-0004** | Asegurar que la descripción nueva de la categoría sea válida en tipo, longitud, obligatoriedad y formato. | Categoria | `AsegurarFormatoDescripcionCategoriaValidoRule` |
| <a id="p-cat-0005"></a>**P-CAT-0005** | Asegurar que no exista otra categoría con el mismo nombre. | Categoria | `AsegurarNombreCategoriaNoExistaRule` |
| <a id="p-cat-0006"></a>**P-CAT-0006** | Asegurar que el identificador de la categoría sea único dentro de las categorías; si ya existe, generar otro. | Categoria | `AsegurarIdCategoriaUnicoRule` |
| <a id="p-cat-0007"></a>**P-CAT-0007** | Asignar a la categoría nueva el estado activa, para que pueda usarse de inmediato en nuevos Kudos (RN-133). | Categoria | `AsignarCategoriaActivaAlCrearRule` |
| <a id="p-cat-0008"></a>**P-CAT-0008** | Asegurar que la categoría esté activa (RN-144, INV-019). | Categoria | `AsegurarCategoriaActivaRule` |
| <a id="p-cat-0009"></a>**P-CAT-0009** | Asegurar que, al desactivar la categoría, quede al menos otra categoría activa: siempre debe haber una categoría para publicar un Kudo (RN-144, RN-149, INV-017). | Categoria | `AsegurarQuedeOtraCategoriaActivaRule` |
| <a id="p-cat-0010"></a>**P-CAT-0010** | Asegurar que la categoría esté inactiva antes de activarla. | Categoria | `AsegurarCategoriaInactivaRule` |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ Motivo](Motivo.md) · [Facultad ➡️](Facultad.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/Categoria.md) · [📐 Clase](../clases/Categoria.md)
