# 🧩 Facultad

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ Categoria](Categoria.md) · [Usuario ➡️](Usuario.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/Facultad.md) · [📐 Clase](../clases/Facultad.md)

> Unidad académica que agrupa Programas Académicos. Puede existir sin programas solo mientras se crea.

| Línea de vida | Atributos | Responsabilidades | Eventos | Políticas propias |
| :---: | :---: | :---: | :---: | :---: |
| Nivel 1 | 2 | 3 | 3 | 4 |

## Contenido

1. [Muestreo](#1-muestreo)
2. [Caracterización](#2-caracterización)
3. [Responsabilidades](#3-responsabilidades)
4. [Event Storming](#4-event-storming)
5. [Políticas](#5-políticas)

---

## 1. Muestreo

Ejemplos reales del objeto para validar sus atributos con el negocio.

| **id** | **nombre** | **Combinación única** |
| --- | --- | --- |
| 1 | Facultad de Ingeniería | Facultad de Ingeniería |
| 2 | Facultad de Ciencias de la Salud | Facultad de Ciencias de la Salud |
| 3 | Facultad de Ciencias Sociales | Facultad de Ciencias Sociales |
| 4 | Facultad de Ciencias Económicas y Administrativas | Facultad de Ciencias Económicas y Administrativas |
| 5 | Facultad de Ciencias Agropecuarias | Facultad de Ciencias Agropecuarias |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único de la facultad dentro del sistema. |
| `nombre` | Representa el nombre oficial de la facultad dentro de la universidad. |
| `Combinación única` | Representa que no pueden existir dos facultades con el mismo nombre. |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único de la facultad dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `nombre` | Representa el nombre oficial de la facultad dentro de la universidad. | Texto | Si | 13 | 70 | Facultad de &lt;área&gt;. Ej: Facultad de Ingeniería | Solo se permiten carateres de letras mayusculas y minusculas con tildes y espacios. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `nombre` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única | Representa que no pueden existir dos facultades con el mismo nombre. | `nombre` | No distingue mayúsculas, minúsculas, tildes ni espacios repetidos. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`registrarFacultad`](#registrarfacultad) | [`consultarFacultades`](#consultarfacultades) | [`cambiarNombreFacultad`](#cambiarnombrefacultad) |
| --- | :---: | :---: | :---: |
| `id` | NR | 👁 | R·NM |
| `nombre` | R | 👁 | R·M |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`registrarFacultad`](#registrarfacultad) | Crear una facultad de la universidad. Puede existir sin programas mientras se crea (RN-045). |
| 2 | [`consultarFacultades`](#consultarfacultades) | Consultar las facultades de la universidad (RF-016, RN-122). |
| 3 | [`cambiarNombreFacultad`](#cambiarnombrefacultad) | Cambiar el nombre de una facultad (RF-017). |

### `registrarFacultad`

Crear una facultad de la universidad. Puede existir sin programas mientras se crea (RN-045).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{nombre}` | [Facultad](Facultad.md) | Nombre de la facultad. |

**Salida:** [Facultad](Facultad.md) — La facultad registrada.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0004](Usuario.md#p-usu-0004) | Asegurar que quien ejecuta la acción tenga el rol Administrador (RN-129 a RN-131). | Solo un Administrador puede realizar esta acción. | 🛑 Detener operación |
| 3 | [P-FAC-0002](#p-fac-0002) | Asegurar que el nombre de la facultad sea válido en tipo, longitud, obligatoriedad y formato (RN-040). | Se informa cómo debe escribirse el nombre. | 🛑 Detener operación |
| 4 | [P-FAC-0003](#p-fac-0003) | Asegurar que no exista otra facultad con el mismo nombre. | Ya existe una facultad con ese nombre. | 🛑 Detener operación |
| 5 | [P-FAC-0004](#p-fac-0004) | Asegurar que el identificador de la facultad sea único dentro de las facultades; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 6 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `consultarFacultades`

Consultar las facultades de la universidad (RF-016, RN-122).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{nombre}` | [Facultad](Facultad.md) | Dato opcional para filtrar la consulta. |

**Salida:** [Lista&lt;Facultad&gt;](Facultad.md) — Las facultades que cumplen el filtro.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |

### `cambiarNombreFacultad`

Cambiar el nombre de una facultad (RF-017).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la facultad. |
| `datos{nombre}` | [Facultad](Facultad.md) | Nombre nuevo. |

**Salida:** [Facultad](Facultad.md) — La facultad con su nombre nuevo.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0004](Usuario.md#p-usu-0004) | Asegurar que quien ejecuta la acción tenga el rol Administrador (RN-129 a RN-131). | Solo un Administrador puede realizar esta acción. | 🛑 Detener operación |
| 3 | [P-FAC-0001](#p-fac-0001) | Asegurar que exista una facultad con el identificador indicado. | No existe la facultad que se indicó. | 🛑 Detener operación |
| 4 | [P-FAC-0002](#p-fac-0002) | Asegurar que el nombre de la facultad sea válido en tipo, longitud, obligatoriedad y formato (RN-040). | Se informa cómo debe escribirse el nombre. | 🛑 Detener operación |
| 5 | [P-FAC-0003](#p-fac-0003) | Asegurar que no exista otra facultad con el mismo nombre. | Ya existe una facultad con ese nombre. | 🛑 Detener operación |
| 6 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Administrador | **Registrar la facultad**<br>Crear una facultad de la universidad. Puede existir sin programas mientras se crea (RN-045). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0004](Usuario.md#p-usu-0004)<br>[P-FAC-0002](#p-fac-0002)<br>[P-FAC-0003](#p-fac-0003)<br>[P-FAC-0004](#p-fac-0004)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Facultad creada** | Ninguno |
| Usuario autenticado | **Consultar las facultades**<br>Consultar las facultades de la universidad (RF-016, RN-122). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001) | No aplica | 🟧 **Facultades consultadas** | Ninguno |
| Administrador | **Cambiar el nombre de la facultad**<br>Cambiar el nombre de una facultad (RF-017). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001)<br>[P-USU-0004](Usuario.md#p-usu-0004)<br>[P-FAC-0001](#p-fac-0001)<br>[P-FAC-0002](#p-fac-0002)<br>[P-FAC-0003](#p-fac-0003)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Facultad modificada** | Ninguno |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="p-fac-0001"></a>**P-FAC-0001** | Asegurar que exista una facultad con el identificador indicado. | Facultad | `AsegurarFacultadExisteRule` |
| <a id="p-fac-0002"></a>**P-FAC-0002** | Asegurar que el nombre de la facultad sea válido en tipo, longitud, obligatoriedad y formato (RN-040). | Facultad | `AsegurarFormatoFacultadValidoRule` |
| <a id="p-fac-0003"></a>**P-FAC-0003** | Asegurar que no exista otra facultad con el mismo nombre. | Facultad | `AsegurarNombreFacultadNoExistaRule` |
| <a id="p-fac-0004"></a>**P-FAC-0004** | Asegurar que el identificador de la facultad sea único dentro de las facultades; si ya existe, generar otro. | Facultad | `AsegurarIdFacultadUnicoRule` |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ Categoria](Categoria.md) · [Usuario ➡️](Usuario.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/Facultad.md) · [📐 Clase](../clases/Facultad.md)
