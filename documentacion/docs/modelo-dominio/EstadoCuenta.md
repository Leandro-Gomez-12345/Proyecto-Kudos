# 🧩 EstadoCuenta

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ Rol](Rol.md) · [EstadoContenido ➡️](EstadoContenido.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/EstadoCuenta.md) · [📐 Clase](../clases/EstadoCuenta.md)

> Catálogo de estados de la cuenta de un usuario. Una cuenta Aprobada aún no está Activa hasta validar correo y celular.

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
| 1 | Pendiente de aprobación | Pendiente de aprobación |
| 2 | Aprobada | Aprobada |
| 3 | Activa | Activa |
| 4 | Suspendida | Suspendida |
| 5 | Desactivada | Desactivada |
| 6 | Rechazada | Rechazada |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único del Estado Cuenta dentro del sistema. |
| `nombre` | Representa el nombre del estado cuenta tal como lo entiende la comunidad universitaria. |
| `Combinación única` | Representa que no pueden existir dos registros de estado cuenta con el mismo nombre. |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único del Estado Cuenta dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `nombre` | Representa el nombre del estado cuenta tal como lo entiende la comunidad universitaria. | Texto | Si | 5 | 30 | Primera letra en mayúscula. Ej: Pendiente de aprobación | Solo se permiten carateres de letras mayusculas y minusculas con tildes y espacios. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `nombre` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única | Representa que no pueden existir dos registros de estado cuenta con el mismo nombre. | `nombre` | No distingue mayúsculas, minúsculas, tildes ni espacios repetidos. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`consultarEstadosCuenta`](#consultarestadoscuenta) |
| --- | :---: |
| `id` | 👁 |
| `nombre` | 👁 |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`consultarEstadosCuenta`](#consultarestadoscuenta) | Consultar la lista de estados de cuenta que maneja la plataforma. Para filtrar usuarios según el estado de su cuenta. |

### `consultarEstadosCuenta`

Consultar la lista de estados de cuenta que maneja la plataforma. Para filtrar usuarios según el estado de su cuenta.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `No aplica` | No aplica | No recibe datos de entrada. |

**Salida:** [Lista&lt;EstadoCuenta&gt;](EstadoCuenta.md) — Todos los estados de cuenta.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Administrador<br>Rector<br>Coordinador Académico<br>Coordinador de Facultad<br>Coordinador de Programa | **Consultar los estados de cuenta**<br>Consultar la lista de estados de cuenta que maneja la plataforma. Para filtrar usuarios según el estado de su cuenta. | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001) | No aplica | 🟧 **Estados de cuenta consultados** | Ninguno |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="catálogo semilla: sus valores los define el negocio desde el inicio y solo se consultan. no necesita políticas propias (yagni)."></a>**Catálogo semilla: sus valores los define el negocio desde el inicio y solo se consultan. No necesita políticas propias (YAGNI).** | — | — | — |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ Rol](Rol.md) · [EstadoContenido ➡️](EstadoContenido.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/EstadoCuenta.md) · [📐 Clase](../clases/EstadoCuenta.md)
