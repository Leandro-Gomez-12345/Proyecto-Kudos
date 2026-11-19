# 📐 ProgramaAcademico

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ Usuario](Usuario.md) · [Auditoria ➡️](Auditoria.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/ProgramaAcademico.md) · [🗄️ MER](../mer/ProgramaAcademico.md)

> Unidad académica que pertenece exactamente a una Facultad y en la que se vinculan estudiantes y docentes.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Estructura académica |

## Vista rápida

```java
public class ProgramaAcademico {

    private UUID id = new UUID(0L, 0L);
    private Facultad facultad = new Facultad();
    private String nombre = "";

    public ProgramaAcademico()
    public ProgramaAcademico(UUID valorId, Facultad valorFacultad, String valorNombre)

    public ProgramaAcademico registrarProgramaAcademico(Facultad facultad, String nombre)
    public List<ProgramaAcademico> consultarProgramasAcademicos(Facultad facultad, String nombre)
    public ProgramaAcademico cambiarNombreProgramaAcademico(UUID id, String nombre)

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. ProgramaAcademico | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Necesita de | Composición | [Facultad](Facultad.md) | 0..\* | 1 | `ProgramaAcademico.facultad` |
| Es usada por | Composición | [CoordinacionPrograma](CoordinacionPrograma.md) | 1 | 0..\* | `CoordinacionPrograma.programaAcademico` |
| Es usada por | Asociación | [VinculacionAcademica](VinculacionAcademica.md) | 1 | 0..\* | `VinculacionAcademica.programaAcademico` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único del programa académico dentro del sistema. |
| `private` | `facultad` | [Facultad](Facultad.md) | no final | no static | `new Facultad()` | Representa la facultad a la que pertenece el programa académico. |
| `private` | `nombre` | String | no final | no static | `""` | Representa el nombre oficial del programa académico. |

## Constructores

### `ProgramaAcademico()` — Defecto

Constructor por defecto que crea un objeto ProgramaAcademico con todos sus atributos en su valor por defecto

### `ProgramaAcademico(UUID valorId, Facultad valorFacultad, String valorNombre)` — Parametrizado

Constructor parametrizado que crea un objeto ProgramaAcademico asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único del programa académico dentro del sistema. |
| `valorFacultad` | [Facultad](Facultad.md) | Por referencia | no final | Representa la facultad a la que pertenece el programa académico. |
| `valorNombre` | String | Por valor | no final | Representa el nombre oficial del programa académico. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getFacultad()` | `setFacultad(Facultad valorFacultad)` | [Facultad](Facultad.md) |
| `getNombre()` | `setNombre(String valorNombre)` | String |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`registrarProgramaAcademico`](#registrarprogramaacademico) | [ProgramaAcademico](ProgramaAcademico.md) | Crear un programa académico dentro de exactamente una facultad (RN-049, INV-008). |
| 2 | [`consultarProgramasAcademicos`](#consultarprogramasacademicos) | [List&lt;ProgramaAcademico&gt;](ProgramaAcademico.md) | Consultar los programas académicos, todos o los de una facultad (RF-021, RN-118). |
| 3 | [`cambiarNombreProgramaAcademico`](#cambiarnombreprogramaacademico) | [ProgramaAcademico](ProgramaAcademico.md) | Cambiar el nombre de un programa académico (RF-022). |

### `registrarProgramaAcademico`

```java
public ProgramaAcademico registrarProgramaAcademico(Facultad facultad, String nombre)
```

Crear un programa académico dentro de exactamente una facultad (RN-049, INV-008).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `facultad` | [Facultad](Facultad.md) | Por referencia | Representa la facultad a la que pertenece el programa académico. |
| `nombre` | String | Por valor | Representa el nombre oficial del programa académico. |

<details>
<summary><b>Excepciones (7)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0004**](../modelo-dominio/Usuario.md#p-usu-0004) — Solo un Administrador puede realizar esta acción.
- [**P-PRO-0002**](../modelo-dominio/ProgramaAcademico.md#p-pro-0002) — Se informa cuál dato no cumple y cómo debe escribirse.
- [**P-FAC-0001**](../modelo-dominio/Facultad.md#p-fac-0001) — No existe la facultad que se indicó.
- [**P-PRO-0004**](../modelo-dominio/ProgramaAcademico.md#p-pro-0004) — Ya existe un programa con ese nombre.
- [**P-PRO-0005**](../modelo-dominio/ProgramaAcademico.md#p-pro-0005) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `consultarProgramasAcademicos`

```java
public List<ProgramaAcademico> consultarProgramasAcademicos(Facultad facultad, String nombre)
```

Consultar los programas académicos, todos o los de una facultad (RF-021, RN-118).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `facultad` | [Facultad](Facultad.md) | Por referencia | Representa la facultad a la que pertenece el programa académico. |
| `nombre` | String | Por valor | Representa el nombre oficial del programa académico. |

<details>
<summary><b>Excepciones (1)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.

</details>

### `cambiarNombreProgramaAcademico`

```java
public ProgramaAcademico cambiarNombreProgramaAcademico(UUID id, String nombre)
```

Cambiar el nombre de un programa académico (RF-022).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador del programa. |
| `nombre` | String | Por valor | Representa el nombre oficial del programa académico. |

<details>
<summary><b>Excepciones (6)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0004**](../modelo-dominio/Usuario.md#p-usu-0004) — Solo un Administrador puede realizar esta acción.
- [**P-PRO-0001**](../modelo-dominio/ProgramaAcademico.md#p-pro-0001) — No existe el programa académico que se indicó.
- [**P-PRO-0003**](../modelo-dominio/ProgramaAcademico.md#p-pro-0003) — Se informa cómo debe escribirse el nombre.
- [**P-PRO-0004**](../modelo-dominio/ProgramaAcademico.md#p-pro-0004) — Ya existe un programa con ese nombre.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ Usuario](Usuario.md) · [Auditoria ➡️](Auditoria.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/ProgramaAcademico.md) · [🗄️ MER](../mer/ProgramaAcademico.md)
