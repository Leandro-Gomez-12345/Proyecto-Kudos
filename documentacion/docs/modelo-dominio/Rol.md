# 🧩 Rol

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [EstadoCuenta ➡️](EstadoCuenta.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/Rol.md) · [📐 Clase](../clases/Rol.md)

> Catálogo de roles institucionales. Un usuario tiene exactamente un rol activo y los roles son mutuamente excluyentes, es decir que un usuario no puede tener más de un rol.

| Línea de vida | Atributos | Responsabilidades | Eventos | Políticas propias |
| :---: | :---: | :---: | :---: | :---: |
| Nivel 1 | 2 | 1 | 1 | 1 |

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
| 1 | Administrador | Administrador |
| 2 | Rector | Rector |
| 3 | Coordinador Académico | Coordinador Académico |
| 4 | Coordinador de Facultad | Coordinador de Facultad |
| 5 | Coordinador de Programa | Coordinador de Programa |
| 6 | Docente | Docente |
| 7 | Estudiante | Estudiante |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único del Rol dentro del sistema. |
| `nombre` | Representa el nombre del rol tal como lo entiende la comunidad universitaria. |
| `Combinación única` | Representa que no pueden existir dos registros de rol con el mismo nombre. |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único del Rol dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `nombre` | Representa el nombre del rol tal como lo entiende la comunidad universitaria. | Texto | Si | 6 | 30 | Primera letra en mayúscula. Ej: Administrador | Solo se permiten carateres de letras mayusculas y minusculas con tildes y espacios. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `nombre` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única | Representa que no pueden existir dos registros de rol con el mismo nombre. | `nombre` | No distingue mayúsculas, minúsculas, tildes ni espacios repetidos. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`consultarRoles`](#consultarroles) |
| --- | :---: |
| `id` | 👁 |
| `nombre` | 👁 |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`consultarRoles`](#consultarroles) | Consultar la lista de roles institucionales que maneja la plataforma. Para elegir el rol con el que una persona se registra y para filtrar usuarios. |

### `consultarRoles`

Consultar la lista de roles institucionales que maneja la plataforma. Para elegir el rol con el que una persona se registra y para filtrar usuarios.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `No aplica` | No aplica | No recibe datos de entrada. |

**Salida:** [Lista&lt;Rol&gt;](Rol.md) — Todos los roles institucionales.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | No aplica | No aplica | No aplica | ▶️ No aplica |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Persona que se registra<br>Administrador | **Consultar los roles institucionales**<br>Consultar la lista de roles institucionales que maneja la plataforma. Para elegir el rol con el que una persona se registra y para filtrar usuarios. | **No aplica**: No aplica | No aplica | No aplica | 🟧 **Roles institucionales consultados** | Ninguno |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="p-rol-0001"></a>**P-ROL-0001** | Asegurar que exista un rol con el identificador indicado. | Rol | `AsegurarRolExisteRule` |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [EstadoCuenta ➡️](EstadoCuenta.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/Rol.md) · [📐 Clase](../clases/Rol.md)
