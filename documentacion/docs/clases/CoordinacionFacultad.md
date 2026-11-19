# 📐 CoordinacionFacultad

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ Auditoria](Auditoria.md) · [CoordinacionPrograma ➡️](CoordinacionPrograma.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/CoordinacionFacultad.md) · [🗄️ MER](../mer/CoordinacionFacultad.md)

> Historial de quién coordina cada Facultad. Solo una coordinación vigente por facultad y su reemplazo queda registrado.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Estructura académica |

## Vista rápida

```java
public class CoordinacionFacultad {

    private UUID id = new UUID(0L, 0L);
    private Usuario usuario = new Usuario();
    private Facultad facultad = new Facultad();
    private LocalDate fechaInicio = LocalDate.of(1000, 1, 1);
    private LocalDate fechaFin = LocalDate.of(1000, 1, 1);

    public CoordinacionFacultad()
    public CoordinacionFacultad(UUID valorId, Usuario valorUsuario, Facultad valorFacultad, LocalDate valorFechaInicio, LocalDate valorFechaFin)

    public CoordinacionFacultad asignarCoordinadorFacultad(Usuario usuario, Facultad facultad, LocalDate fechaInicio)
    public void finalizarCoordinacionFacultad(Usuario usuario, LocalDate fechaFin)
    public List<CoordinacionFacultad> consultarCoordinacionesFacultad(Usuario usuario, Facultad facultad)

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. CoordinacionFacultad | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Necesita de | Asociación | [Usuario](Usuario.md) | 0..\* | 1 | `CoordinacionFacultad.usuario` |
| Necesita de | Composición | [Facultad](Facultad.md) | 0..\* | 1 | `CoordinacionFacultad.facultad` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único de la coordinación facultad dentro del sistema. |
| `private` | `usuario` | [Usuario](Usuario.md) | no final | no static | `new Usuario()` | Representa la persona que ejerce o ejerció la coordinación. |
| `private` | `facultad` | [Facultad](Facultad.md) | no final | no static | `new Facultad()` | Representa la facultad que coordina la persona. |
| `private` | `fechaInicio` | LocalDate | no final | no static | `LocalDate.of(1000, 1, 1)` | Representa la fecha en que la persona empezó a coordinar. |
| `private` | `fechaFin` | LocalDate | no final | no static | `LocalDate.of(1000, 1, 1)` | Representa la fecha en que la persona dejó de coordinar; vacía mientras la coordinación está vigente. |

## Constructores

### `CoordinacionFacultad()` — Defecto

Constructor por defecto que crea un objeto CoordinacionFacultad con todos sus atributos en su valor por defecto

### `CoordinacionFacultad(UUID valorId, Usuario valorUsuario, Facultad valorFacultad, LocalDate valorFechaInicio, LocalDate valorFechaFin)` — Parametrizado

Constructor parametrizado que crea un objeto CoordinacionFacultad asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único de la coordinación facultad dentro del sistema. |
| `valorUsuario` | [Usuario](Usuario.md) | Por referencia | no final | Representa la persona que ejerce o ejerció la coordinación. |
| `valorFacultad` | [Facultad](Facultad.md) | Por referencia | no final | Representa la facultad que coordina la persona. |
| `valorFechaInicio` | LocalDate | Por referencia | no final | Representa la fecha en que la persona empezó a coordinar. |
| `valorFechaFin` | LocalDate | Por referencia | no final | Representa la fecha en que la persona dejó de coordinar; vacía mientras la coordinación está vigente. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getUsuario()` | `setUsuario(Usuario valorUsuario)` | [Usuario](Usuario.md) |
| `getFacultad()` | `setFacultad(Facultad valorFacultad)` | [Facultad](Facultad.md) |
| `getFechaInicio()` | `setFechaInicio(LocalDate valorFechaInicio)` | LocalDate |
| `getFechaFin()` | `setFechaFin(LocalDate valorFechaFin)` | LocalDate |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`asignarCoordinadorFacultad`](#asignarcoordinadorfacultad) | [CoordinacionFacultad](CoordinacionFacultad.md) | Asignar la persona que coordina la facultad cuando no tiene coordinador vigente: la primera vez o cuando el anterior dejó de estar activo (al suspender o desactivar su cuenta, su coordinación terminó). Así se hace el reemplazo (RN-220, RN-223). |
| 2 | [`finalizarCoordinacionFacultad`](#finalizarcoordinacionfacultad) | void | Terminar la coordinación vigente de la facultad cuando se suspende o se desactiva la cuenta de quien coordina. Lo hace el sistema; la facultad queda sin coordinador y las responsabilidades que dependen de ese coordinador no se pueden usar hasta que se asigne uno nuevo. |
| 3 | [`consultarCoordinacionesFacultad`](#consultarcoordinacionesfacultad) | [List&lt;CoordinacionFacultad&gt;](CoordinacionFacultad.md) | Consultar quién coordina la facultad hoy y quiénes la coordinaron antes (historial). |

### `asignarCoordinadorFacultad`

```java
public CoordinacionFacultad asignarCoordinadorFacultad(Usuario usuario, Facultad facultad, LocalDate fechaInicio)
```

Asignar la persona que coordina la facultad cuando no tiene coordinador vigente: la primera vez o cuando el anterior dejó de estar activo (al suspender o desactivar su cuenta, su coordinación terminó). Así se hace el reemplazo (RN-220, RN-223).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `usuario` | [Usuario](Usuario.md) | Por referencia | Representa la persona que ejerce o ejerció la coordinación. |
| `facultad` | [Facultad](Facultad.md) | Por referencia | Representa la facultad que coordina la persona. |
| `fechaInicio` | LocalDate | Por referencia | Representa la fecha en que la persona empezó a coordinar. |

<details>
<summary><b>Excepciones (11)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0005**](../modelo-dominio/Usuario.md#p-usu-0005) — Solo el Coordinador Académico puede realizar esta acción.
- [**P-COF-0002**](../modelo-dominio/CoordinacionFacultad.md#p-cof-0002) — Se informa cuál dato no cumple y cómo debe escribirse.
- [**P-FAC-0001**](../modelo-dominio/Facultad.md#p-fac-0001) — No existe la facultad que se indicó.
- [**P-USU-0009**](../modelo-dominio/Usuario.md#p-usu-0009) — La persona indicada no existe o su cuenta no está activa.
- [**P-USU-0010**](../modelo-dominio/Usuario.md#p-usu-0010) — La persona indicada no tiene el rol Coordinador de Facultad.
- [**P-COF-0003**](../modelo-dominio/CoordinacionFacultad.md#p-cof-0003) — La facultad ya tiene un coordinador vigente; para cambiarlo, primero debe suspenderse o desactivarse la cuenta del actual.
- [**P-COF-0004**](../modelo-dominio/CoordinacionFacultad.md#p-cof-0004) — La persona ya coordina otra unidad académica.
- [**P-COF-0005**](../modelo-dominio/CoordinacionFacultad.md#p-cof-0005) — Ya existe esa coordinación.
- [**P-COF-0006**](../modelo-dominio/CoordinacionFacultad.md#p-cof-0006) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `finalizarCoordinacionFacultad`

```java
public void finalizarCoordinacionFacultad(Usuario usuario, LocalDate fechaFin)
```

Terminar la coordinación vigente de la facultad cuando se suspende o se desactiva la cuenta de quien coordina. Lo hace el sistema; la facultad queda sin coordinador y las responsabilidades que dependen de ese coordinador no se pueden usar hasta que se asigne uno nuevo.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `usuario` | [Usuario](Usuario.md) | Por referencia | Representa la persona que ejerce o ejerció la coordinación. |
| `fechaFin` | LocalDate | Por referencia | Representa la fecha en que la persona dejó de coordinar; vacía mientras la coordinación está vigente. |

<details>
<summary><b>Excepciones (3)</b></summary>

- [**P-COF-0007**](../modelo-dominio/CoordinacionFacultad.md#p-cof-0007) — Esa coordinación ya había terminado.
- [**P-COF-0001**](../modelo-dominio/CoordinacionFacultad.md#p-cof-0001) — No se pudo finalizar la coordinación; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `consultarCoordinacionesFacultad`

```java
public List<CoordinacionFacultad> consultarCoordinacionesFacultad(Usuario usuario, Facultad facultad)
```

Consultar quién coordina la facultad hoy y quiénes la coordinaron antes (historial).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `usuario` | [Usuario](Usuario.md) | Por referencia | Representa la persona que ejerce o ejerció la coordinación. |
| `facultad` | [Facultad](Facultad.md) | Por referencia | Representa la facultad que coordina la persona. |

<details>
<summary><b>Excepciones (1)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ Auditoria](Auditoria.md) · [CoordinacionPrograma ➡️](CoordinacionPrograma.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/CoordinacionFacultad.md) · [🗄️ MER](../mer/CoordinacionFacultad.md)
