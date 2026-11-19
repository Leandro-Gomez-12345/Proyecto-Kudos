# 📐 Facultad

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ Categoria](Categoria.md) · [Usuario ➡️](Usuario.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Facultad.md) · [🗄️ MER](../mer/Facultad.md)

> Unidad académica que agrupa Programas Académicos. Puede existir sin programas solo mientras se crea.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Estructura académica |

## Vista rápida

```java
public class Facultad {

    private UUID id = new UUID(0L, 0L);
    private String nombre = "";

    public Facultad()
    public Facultad(UUID valorId, String valorNombre)

    public Facultad registrarFacultad(String nombre)
    public List<Facultad> consultarFacultades(String nombre)
    public Facultad cambiarNombreFacultad(UUID id, String nombre)

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. Facultad | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Es usada por | Composición | [ProgramaAcademico](ProgramaAcademico.md) | 1 | 0..\* | `ProgramaAcademico.facultad` |
| Es usada por | Composición | [CoordinacionFacultad](CoordinacionFacultad.md) | 1 | 0..\* | `CoordinacionFacultad.facultad` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único de la facultad dentro del sistema. |
| `private` | `nombre` | String | no final | no static | `""` | Representa el nombre oficial de la facultad dentro de la universidad. |

## Constructores

### `Facultad()` — Defecto

Constructor por defecto que crea un objeto Facultad con todos sus atributos en su valor por defecto

### `Facultad(UUID valorId, String valorNombre)` — Parametrizado

Constructor parametrizado que crea un objeto Facultad asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único de la facultad dentro del sistema. |
| `valorNombre` | String | Por valor | no final | Representa el nombre oficial de la facultad dentro de la universidad. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getNombre()` | `setNombre(String valorNombre)` | String |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`registrarFacultad`](#registrarfacultad) | [Facultad](Facultad.md) | Crear una facultad de la universidad. Puede existir sin programas mientras se crea (RN-045). |
| 2 | [`consultarFacultades`](#consultarfacultades) | [List&lt;Facultad&gt;](Facultad.md) | Consultar las facultades de la universidad (RF-016, RN-122). |
| 3 | [`cambiarNombreFacultad`](#cambiarnombrefacultad) | [Facultad](Facultad.md) | Cambiar el nombre de una facultad (RF-017). |

### `registrarFacultad`

```java
public Facultad registrarFacultad(String nombre)
```

Crear una facultad de la universidad. Puede existir sin programas mientras se crea (RN-045).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `nombre` | String | Por valor | Representa el nombre oficial de la facultad dentro de la universidad. |

<details>
<summary><b>Excepciones (6)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0004**](../modelo-dominio/Usuario.md#p-usu-0004) — Solo un Administrador puede realizar esta acción.
- [**P-FAC-0002**](../modelo-dominio/Facultad.md#p-fac-0002) — Se informa cómo debe escribirse el nombre.
- [**P-FAC-0003**](../modelo-dominio/Facultad.md#p-fac-0003) — Ya existe una facultad con ese nombre.
- [**P-FAC-0004**](../modelo-dominio/Facultad.md#p-fac-0004) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `consultarFacultades`

```java
public List<Facultad> consultarFacultades(String nombre)
```

Consultar las facultades de la universidad (RF-016, RN-122).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `nombre` | String | Por valor | Representa el nombre oficial de la facultad dentro de la universidad. |

<details>
<summary><b>Excepciones (1)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.

</details>

### `cambiarNombreFacultad`

```java
public Facultad cambiarNombreFacultad(UUID id, String nombre)
```

Cambiar el nombre de una facultad (RF-017).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la facultad. |
| `nombre` | String | Por valor | Representa el nombre oficial de la facultad dentro de la universidad. |

<details>
<summary><b>Excepciones (6)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0004**](../modelo-dominio/Usuario.md#p-usu-0004) — Solo un Administrador puede realizar esta acción.
- [**P-FAC-0001**](../modelo-dominio/Facultad.md#p-fac-0001) — No existe la facultad que se indicó.
- [**P-FAC-0002**](../modelo-dominio/Facultad.md#p-fac-0002) — Se informa cómo debe escribirse el nombre.
- [**P-FAC-0003**](../modelo-dominio/Facultad.md#p-fac-0003) — Ya existe una facultad con ese nombre.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ Categoria](Categoria.md) · [Usuario ➡️](Usuario.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Facultad.md) · [🗄️ MER](../mer/Facultad.md)
