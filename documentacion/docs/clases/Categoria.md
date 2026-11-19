# 📐 Categoria

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ Motivo](Motivo.md) · [Facultad ➡️](Facultad.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Categoria.md) · [🗄️ MER](../mer/Categoria.md)

> Clasificación institucional de los Kudos. La gestionan Administrador, Rector y Coordinador Académico; una categoría inactiva no se usa en nuevos Kudos.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Catálogos |

## Vista rápida

```java
public class Categoria {

    private UUID id = new UUID(0L, 0L);
    private String nombre = "";
    private String descripcion = "";
    private boolean/Boolean activa = true;

    public Categoria()
    public Categoria(UUID valorId, String valorNombre, String valorDescripcion, boolean/Boolean valorActiva)

    public Categoria registrarCategoria(String nombre, String descripcion)
    public List<Categoria> consultarCategorias(String nombre, boolean/Boolean activa)
    public Categoria cambiarNombreCategoria(UUID id, String nombre)
    public Categoria cambiarDescripcionCategoria(UUID id, String descripcion)
    public void activarCategoria(UUID id)
    public void desactivarCategoria(UUID id)

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. Categoria | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Es usada por | Asociación | [Kudo](Kudo.md) | 1 | 0..\* | `Kudo.categoria` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único de la categoría dentro del sistema. |
| `private` | `nombre` | String | no final | no static | `""` | Representa el nombre con el que la universidad identifica la categoría de reconocimiento. |
| `private` | `descripcion` | String | no final | no static | `""` | Representa la explicación de qué tipo de conductas o logros se reconocen con esta categoría. |
| `private` | `activa` | boolean/Boolean | no final | no static | `true` | Representa si la categoría puede usarse para crear nuevos Kudos. |

## Constructores

### `Categoria()` — Defecto

Constructor por defecto que crea un objeto Categoria con todos sus atributos en su valor por defecto

### `Categoria(UUID valorId, String valorNombre, String valorDescripcion, boolean/Boolean valorActiva)` — Parametrizado

Constructor parametrizado que crea un objeto Categoria asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único de la categoría dentro del sistema. |
| `valorNombre` | String | Por valor | no final | Representa el nombre con el que la universidad identifica la categoría de reconocimiento. |
| `valorDescripcion` | String | Por valor | no final | Representa la explicación de qué tipo de conductas o logros se reconocen con esta categoría. |
| `valorActiva` | boolean/Boolean | Por valor | no final | Representa si la categoría puede usarse para crear nuevos Kudos. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getNombre()` | `setNombre(String valorNombre)` | String |
| `getDescripcion()` | `setDescripcion(String valorDescripcion)` | String |
| `getActiva()` | `setActiva(boolean/Boolean valorActiva)` | boolean/Boolean |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`registrarCategoria`](#registrarcategoria) | [Categoria](Categoria.md) | Crear una nueva categoría institucional para clasificar los Kudos. Nace activa. |
| 2 | [`consultarCategorias`](#consultarcategorias) | [List&lt;Categoria&gt;](Categoria.md) | Consultar las categorías, activas o inactivas. Al otorgar un Kudo solo se ofrecen las activas; las inactivas siguen visibles para los Kudos históricos (INV-018). |
| 3 | [`cambiarNombreCategoria`](#cambiarnombrecategoria) | [Categoria](Categoria.md) | Cambiar el nombre de una categoría. Los Kudos ya creados conservan el nombre que tenía cuando se crearon (RN-145). |
| 4 | [`cambiarDescripcionCategoria`](#cambiardescripcioncategoria) | [Categoria](Categoria.md) | Cambiar la descripción que explica cuándo usar la categoría. |
| 5 | [`activarCategoria`](#activarcategoria) | void | Volver a habilitar una categoría inactiva para que pueda usarse en nuevos Kudos. |
| 6 | [`desactivarCategoria`](#desactivarcategoria) | void | Dejar de ofrecer una categoría para nuevos Kudos: desde ese momento no aparece entre las opciones al otorgar un Kudo. Los Kudos históricos la conservan (RN-144, RN-145). Siempre debe quedar al menos una categoría activa. |

### `registrarCategoria`

```java
public Categoria registrarCategoria(String nombre, String descripcion)
```

Crear una nueva categoría institucional para clasificar los Kudos. Nace activa.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `nombre` | String | Por valor | Representa el nombre con el que la universidad identifica la categoría de reconocimiento. |
| `descripcion` | String | Por valor | Representa la explicación de qué tipo de conductas o logros se reconocen con esta categoría. |

<details>
<summary><b>Excepciones (7)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0002**](../modelo-dominio/Usuario.md#p-usu-0002) — Solo el Administrador, el Rector y el Coordinador Académico pueden gestionar categorías.
- [**P-CAT-0002**](../modelo-dominio/Categoria.md#p-cat-0002) — Se informa cuál dato no cumple y cómo debe escribirse.
- [**P-CAT-0005**](../modelo-dominio/Categoria.md#p-cat-0005) — Ya existe una categoría con ese nombre.
- [**P-CAT-0006**](../modelo-dominio/Categoria.md#p-cat-0006) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-CAT-0007**](../modelo-dominio/Categoria.md#p-cat-0007) — No se pudo registrar la categoría; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `consultarCategorias`

```java
public List<Categoria> consultarCategorias(String nombre, boolean/Boolean activa)
```

Consultar las categorías, activas o inactivas. Al otorgar un Kudo solo se ofrecen las activas; las inactivas siguen visibles para los Kudos históricos (INV-018).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `nombre` | String | Por valor | Representa el nombre con el que la universidad identifica la categoría de reconocimiento. |
| `activa` | boolean/Boolean | Por valor | Representa si la categoría puede usarse para crear nuevos Kudos. |

<details>
<summary><b>Excepciones (1)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.

</details>

### `cambiarNombreCategoria`

```java
public Categoria cambiarNombreCategoria(UUID id, String nombre)
```

Cambiar el nombre de una categoría. Los Kudos ya creados conservan el nombre que tenía cuando se crearon (RN-145).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la categoría. |
| `nombre` | String | Por valor | Representa el nombre con el que la universidad identifica la categoría de reconocimiento. |

<details>
<summary><b>Excepciones (6)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0002**](../modelo-dominio/Usuario.md#p-usu-0002) — Solo el Administrador, el Rector y el Coordinador Académico pueden gestionar categorías.
- [**P-CAT-0001**](../modelo-dominio/Categoria.md#p-cat-0001) — No existe la categoría que se indicó.
- [**P-CAT-0003**](../modelo-dominio/Categoria.md#p-cat-0003) — Se informa cómo debe escribirse el nombre.
- [**P-CAT-0005**](../modelo-dominio/Categoria.md#p-cat-0005) — Ya existe una categoría con ese nombre.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `cambiarDescripcionCategoria`

```java
public Categoria cambiarDescripcionCategoria(UUID id, String descripcion)
```

Cambiar la descripción que explica cuándo usar la categoría.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la categoría. |
| `descripcion` | String | Por valor | Representa la explicación de qué tipo de conductas o logros se reconocen con esta categoría. |

<details>
<summary><b>Excepciones (5)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0002**](../modelo-dominio/Usuario.md#p-usu-0002) — Solo el Administrador, el Rector y el Coordinador Académico pueden gestionar categorías.
- [**P-CAT-0001**](../modelo-dominio/Categoria.md#p-cat-0001) — No existe la categoría que se indicó.
- [**P-CAT-0004**](../modelo-dominio/Categoria.md#p-cat-0004) — Se informa cómo debe escribirse la descripción.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `activarCategoria`

```java
public void activarCategoria(UUID id)
```

Volver a habilitar una categoría inactiva para que pueda usarse en nuevos Kudos.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la categoría. |

<details>
<summary><b>Excepciones (5)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0002**](../modelo-dominio/Usuario.md#p-usu-0002) — Solo el Administrador, el Rector y el Coordinador Académico pueden gestionar categorías.
- [**P-CAT-0001**](../modelo-dominio/Categoria.md#p-cat-0001) — No existe la categoría que se indicó.
- [**P-CAT-0010**](../modelo-dominio/Categoria.md#p-cat-0010) — La categoría ya está activa.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `desactivarCategoria`

```java
public void desactivarCategoria(UUID id)
```

Dejar de ofrecer una categoría para nuevos Kudos: desde ese momento no aparece entre las opciones al otorgar un Kudo. Los Kudos históricos la conservan (RN-144, RN-145). Siempre debe quedar al menos una categoría activa.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la categoría. |

<details>
<summary><b>Excepciones (6)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0002**](../modelo-dominio/Usuario.md#p-usu-0002) — Solo el Administrador, el Rector y el Coordinador Académico pueden gestionar categorías.
- [**P-CAT-0001**](../modelo-dominio/Categoria.md#p-cat-0001) — No existe la categoría que se indicó.
- [**P-CAT-0008**](../modelo-dominio/Categoria.md#p-cat-0008) — La categoría está inactiva y no puede usarse.
- [**P-CAT-0009**](../modelo-dominio/Categoria.md#p-cat-0009) — No puede desactivar la única categoría activa; active o cree otra primero.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ Motivo](Motivo.md) · [Facultad ➡️](Facultad.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Categoria.md) · [🗄️ MER](../mer/Categoria.md)
