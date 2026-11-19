# 🧩 Motivo

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ EstadoRevision](EstadoRevision.md) · [Categoria ➡️](Categoria.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/Motivo.md) · [📐 Clase](../clases/Motivo.md)

> Catálogo de motivos por los que un usuario reporta un Kudo o una Réplica.

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
| 1 | Lenguaje ofensivo | Lenguaje ofensivo |
| 2 | Acoso | Acoso |
| 3 | Discriminación | Discriminación |
| 4 | Spam | Spam |
| 5 | Información falsa | Información falsa |
| 6 | Otro | Otro |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único del Motivo dentro del sistema. |
| `nombre` | Representa el nombre del motivo tal como lo entiende la comunidad universitaria. |
| `Combinación única` | Representa que no pueden existir dos registros de motivo con el mismo nombre. |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único del Motivo dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `nombre` | Representa el nombre del motivo tal como lo entiende la comunidad universitaria. | Texto | Si | 3 | 20 | Primera letra en mayúscula. Ej: Lenguaje ofensivo | Solo se permiten carateres de letras mayusculas y minusculas con tildes y espacios. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `nombre` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única | Representa que no pueden existir dos registros de motivo con el mismo nombre. | `nombre` | No distingue mayúsculas, minúsculas, tildes ni espacios repetidos. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`consultarMotivos`](#consultarmotivos) |
| --- | :---: |
| `id` | 👁 |
| `nombre` | 👁 |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`consultarMotivos`](#consultarmotivos) | Consultar la lista de motivos de reporte que maneja la plataforma. Para elegir el motivo al reportar un Kudo o una réplica (CU-010). |

### `consultarMotivos`

Consultar la lista de motivos de reporte que maneja la plataforma. Para elegir el motivo al reportar un Kudo o una réplica (CU-010).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `No aplica` | No aplica | No recibe datos de entrada. |

**Salida:** [Lista&lt;Motivo&gt;](Motivo.md) — Todos los motivos de reporte.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](Usuario.md#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Usuario autenticado | **Consultar los motivos de reporte**<br>Consultar la lista de motivos de reporte que maneja la plataforma. Para elegir el motivo al reportar un Kudo o una réplica (CU-010). | **No aplica**: No aplica | [P-USU-0001](Usuario.md#p-usu-0001) | No aplica | 🟧 **Motivos de reporte consultados** | Ninguno |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="p-mot-0001"></a>**P-MOT-0001** | Asegurar que exista un motivo con el identificador indicado. | Motivo | `AsegurarMotivoExisteRule` |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ EstadoRevision](EstadoRevision.md) · [Categoria ➡️](Categoria.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/Motivo.md) · [📐 Clase](../clases/Motivo.md)
