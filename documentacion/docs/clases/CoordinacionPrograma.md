# 📐 CoordinacionPrograma

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ CoordinacionFacultad](CoordinacionFacultad.md) · [VinculacionAcademica ➡️](VinculacionAcademica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/CoordinacionPrograma.md) · [🗄️ MER](../mer/CoordinacionPrograma.md)

> Historial de quién coordina cada Programa Académico. Solo una coordinación vigente por programa.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Estructura académica |

## Vista rápida

```java
public class CoordinacionPrograma {

    private UUID id = new UUID(0L, 0L);
    private Usuario usuario = new Usuario();
    private ProgramaAcademico programaAcademico = new ProgramaAcademico();
    private LocalDate fechaInicio = LocalDate.of(1000, 1, 1);
    private LocalDate fechaFin = LocalDate.of(1000, 1, 1);

    public CoordinacionPrograma()
    public CoordinacionPrograma(UUID valorId, Usuario valorUsuario, ProgramaAcademico valorProgramaAcademico, LocalDate valorFechaInicio, LocalDate valorFechaFin)

    public CoordinacionPrograma asignarCoordinadorPrograma(Usuario usuario, ProgramaAcademico programaAcademico, LocalDate fechaInicio)
    public void finalizarCoordinacionPrograma(Usuario usuario, LocalDate fechaFin)
    public List<CoordinacionPrograma> consultarCoordinacionesPrograma(Usuario usuario, ProgramaAcademico programaAcademico)

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. CoordinacionPrograma | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Necesita de | Asociación | [Usuario](Usuario.md) | 0..\* | 1 | `CoordinacionPrograma.usuario` |
| Necesita de | Composición | [ProgramaAcademico](ProgramaAcademico.md) | 0..\* | 1 | `CoordinacionPrograma.programaAcademico` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único de la coordinación programa dentro del sistema. |
| `private` | `usuario` | [Usuario](Usuario.md) | no final | no static | `new Usuario()` | Representa la persona que ejerce o ejerció la coordinación. |
| `private` | `programaAcademico` | [ProgramaAcademico](ProgramaAcademico.md) | no final | no static | `new ProgramaAcademico()` | Representa el programa académico que coordina la persona. |
| `private` | `fechaInicio` | LocalDate | no final | no static | `LocalDate.of(1000, 1, 1)` | Representa la fecha en que la persona empezó a coordinar. |
| `private` | `fechaFin` | LocalDate | no final | no static | `LocalDate.of(1000, 1, 1)` | Representa la fecha en que la persona dejó de coordinar; vacía mientras la coordinación está vigente. |

## Constructores

### `CoordinacionPrograma()` — Defecto

Constructor por defecto que crea un objeto CoordinacionPrograma con todos sus atributos en su valor por defecto

### `CoordinacionPrograma(UUID valorId, Usuario valorUsuario, ProgramaAcademico valorProgramaAcademico, LocalDate valorFechaInicio, LocalDate valorFechaFin)` — Parametrizado

Constructor parametrizado que crea un objeto CoordinacionPrograma asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único de la coordinación programa dentro del sistema. |
| `valorUsuario` | [Usuario](Usuario.md) | Por referencia | no final | Representa la persona que ejerce o ejerció la coordinación. |
| `valorProgramaAcademico` | [ProgramaAcademico](ProgramaAcademico.md) | Por referencia | no final | Representa el programa académico que coordina la persona. |
| `valorFechaInicio` | LocalDate | Por referencia | no final | Representa la fecha en que la persona empezó a coordinar. |
| `valorFechaFin` | LocalDate | Por referencia | no final | Representa la fecha en que la persona dejó de coordinar; vacía mientras la coordinación está vigente. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getUsuario()` | `setUsuario(Usuario valorUsuario)` | [Usuario](Usuario.md) |
| `getProgramaAcademico()` | `setProgramaAcademico(ProgramaAcademico valorProgramaAcademico)` | [ProgramaAcademico](ProgramaAcademico.md) |
| `getFechaInicio()` | `setFechaInicio(LocalDate valorFechaInicio)` | LocalDate |
| `getFechaFin()` | `setFechaFin(LocalDate valorFechaFin)` | LocalDate |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`asignarCoordinadorPrograma`](#asignarcoordinadorprograma) | [CoordinacionPrograma](CoordinacionPrograma.md) | Asignar la persona que coordina el programa académico cuando no tiene coordinador vigente: la primera vez o cuando el anterior dejó de estar activo (al suspender o desactivar su cuenta, su coordinación terminó). Así se hace el reemplazo (RN-220, RN-223). Solo puede elegir programas de su facultad. Si la persona es un aspirante a Coordinador de Programa con la cuenta Pendiente de aprobación, asignarle el programa es su aprobación (RN-091). |
| 2 | [`finalizarCoordinacionPrograma`](#finalizarcoordinacionprograma) | void | Terminar la coordinación vigente del programa académico cuando se suspende o se desactiva la cuenta de quien coordina. Lo hace el sistema; el programa académico queda sin coordinador y las responsabilidades que dependen de ese coordinador no se pueden usar hasta que se asigne uno nuevo. |
| 3 | [`consultarCoordinacionesPrograma`](#consultarcoordinacionesprograma) | [List&lt;CoordinacionPrograma&gt;](CoordinacionPrograma.md) | Consultar quién coordina el programa académico hoy y quiénes la coordinaron antes (historial). |

### `asignarCoordinadorPrograma`

```java
public CoordinacionPrograma asignarCoordinadorPrograma(Usuario usuario, ProgramaAcademico programaAcademico, LocalDate fechaInicio)
```

Asignar la persona que coordina el programa académico cuando no tiene coordinador vigente: la primera vez o cuando el anterior dejó de estar activo (al suspender o desactivar su cuenta, su coordinación terminó). Así se hace el reemplazo (RN-220, RN-223). Solo puede elegir programas de su facultad. Si la persona es un aspirante a Coordinador de Programa con la cuenta Pendiente de aprobación, asignarle el programa es su aprobación (RN-091).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `usuario` | [Usuario](Usuario.md) | Por referencia | Representa la persona que ejerce o ejerció la coordinación. |
| `programaAcademico` | [ProgramaAcademico](ProgramaAcademico.md) | Por referencia | Representa el programa académico que coordina la persona. |
| `fechaInicio` | LocalDate | Por referencia | Representa la fecha en que la persona empezó a coordinar. |

<details>
<summary><b>Excepciones (13)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0006**](../modelo-dominio/Usuario.md#p-usu-0006) — Solo un Coordinador de Facultad puede realizar esta acción.
- [**P-COP-0003**](../modelo-dominio/CoordinacionPrograma.md#p-cop-0003) — Se informa cuál dato no cumple y cómo debe escribirse.
- [**P-PRO-0001**](../modelo-dominio/ProgramaAcademico.md#p-pro-0001) — No existe el programa académico que se indicó.
- [**P-COP-0002**](../modelo-dominio/CoordinacionPrograma.md#p-cop-0002) — Ese programa no pertenece a su facultad.
- [**P-USU-0029**](../modelo-dominio/Usuario.md#p-usu-0029) — La persona no es un aspirante ni un Coordinador de Programa activo.
- [**P-COP-0004**](../modelo-dominio/CoordinacionPrograma.md#p-cop-0004) — El programa académico ya tiene un coordinador vigente; para cambiarlo, primero debe suspenderse o desactivarse la cuenta del actual.
- [**P-COP-0005**](../modelo-dominio/CoordinacionPrograma.md#p-cop-0005) — La persona ya coordina otra unidad académica.
- [**P-COP-0006**](../modelo-dominio/CoordinacionPrograma.md#p-cop-0006) — Ya existe esa coordinación.
- [**P-COP-0007**](../modelo-dominio/CoordinacionPrograma.md#p-cop-0007) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-USU-0030**](../modelo-dominio/Usuario.md#p-usu-0030) — No se pudo aprobar la cuenta; se deshace toda la operación.
- [**P-USU-0034**](../modelo-dominio/Usuario.md#p-usu-0034) — No se pudo activar la cuenta; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `finalizarCoordinacionPrograma`

```java
public void finalizarCoordinacionPrograma(Usuario usuario, LocalDate fechaFin)
```

Terminar la coordinación vigente del programa académico cuando se suspende o se desactiva la cuenta de quien coordina. Lo hace el sistema; el programa académico queda sin coordinador y las responsabilidades que dependen de ese coordinador no se pueden usar hasta que se asigne uno nuevo.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `usuario` | [Usuario](Usuario.md) | Por referencia | Representa la persona que ejerce o ejerció la coordinación. |
| `fechaFin` | LocalDate | Por referencia | Representa la fecha en que la persona dejó de coordinar; vacía mientras la coordinación está vigente. |

<details>
<summary><b>Excepciones (3)</b></summary>

- [**P-COP-0008**](../modelo-dominio/CoordinacionPrograma.md#p-cop-0008) — Esa coordinación ya había terminado.
- [**P-COP-0001**](../modelo-dominio/CoordinacionPrograma.md#p-cop-0001) — No se pudo finalizar la coordinación; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `consultarCoordinacionesPrograma`

```java
public List<CoordinacionPrograma> consultarCoordinacionesPrograma(Usuario usuario, ProgramaAcademico programaAcademico)
```

Consultar quién coordina el programa académico hoy y quiénes la coordinaron antes (historial).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `usuario` | [Usuario](Usuario.md) | Por referencia | Representa la persona que ejerce o ejerció la coordinación. |
| `programaAcademico` | [ProgramaAcademico](ProgramaAcademico.md) | Por referencia | Representa el programa académico que coordina la persona. |

<details>
<summary><b>Excepciones (1)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ CoordinacionFacultad](CoordinacionFacultad.md) · [VinculacionAcademica ➡️](VinculacionAcademica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/CoordinacionPrograma.md) · [🗄️ MER](../mer/CoordinacionPrograma.md)
