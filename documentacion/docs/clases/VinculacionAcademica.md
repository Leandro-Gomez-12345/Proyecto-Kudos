# 📐 VinculacionAcademica

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ CoordinacionPrograma](CoordinacionPrograma.md) · [Kudo ➡️](Kudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/VinculacionAcademica.md) · [🗄️ MER](../mer/VinculacionAcademica.md)

> Vínculo de un estudiante o docente con un Programa Académico. Una persona puede estar en varios programas y la desvinculación conserva el historial.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Estructura académica |

## Vista rápida

```java
public class VinculacionAcademica {

    private UUID id = new UUID(0L, 0L);
    private Usuario usuario = new Usuario();
    private ProgramaAcademico programaAcademico = new ProgramaAcademico();
    private LocalDate fechaVinculacion = LocalDate.of(1000, 1, 1);
    private LocalDate fechaDesvinculacion = LocalDate.of(1000, 1, 1);

    public VinculacionAcademica()
    public VinculacionAcademica(UUID valorId, Usuario valorUsuario, ProgramaAcademico valorProgramaAcademico, LocalDate valorFechaVinculacion, LocalDate valorFechaDesvinculacion)

    public VinculacionAcademica vincularPersonaAProgramaAcademico(Usuario usuario, ProgramaAcademico programaAcademico, LocalDate fechaVinculacion)
    public void desvincularPersonaDeProgramaAcademico(UUID id, LocalDate fechaDesvinculacion)
    public void vincularPersonaAlRegistrarse(Usuario usuario, LocalDate fechaVinculacion, List<ProgramaAcademico> programas)
    public void desvincularPersonaDeTodosLosProgramas(Usuario usuario, LocalDate fechaDesvinculacion)
    public List<VinculacionAcademica> consultarVinculacionesAcademicas(Usuario usuario, ProgramaAcademico programaAcademico)

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. VinculacionAcademica | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Necesita de | Asociación | [Usuario](Usuario.md) | 0..\* | 1 | `VinculacionAcademica.usuario` |
| Necesita de | Asociación | [ProgramaAcademico](ProgramaAcademico.md) | 0..\* | 1 | `VinculacionAcademica.programaAcademico` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único de la vinculación académica dentro del sistema. |
| `private` | `usuario` | [Usuario](Usuario.md) | no final | no static | `new Usuario()` | Representa el estudiante o docente que se vincula al programa. |
| `private` | `programaAcademico` | [ProgramaAcademico](ProgramaAcademico.md) | no final | no static | `new ProgramaAcademico()` | Representa el programa académico en el que estudia o enseña la persona. |
| `private` | `fechaVinculacion` | LocalDate | no final | no static | `LocalDate.of(1000, 1, 1)` | Representa la fecha desde la que la persona estudia o enseña en el programa. |
| `private` | `fechaDesvinculacion` | LocalDate | no final | no static | `LocalDate.of(1000, 1, 1)` | Representa la fecha en que la persona dejó el programa; vacía mientras el vínculo está vigente. |

## Constructores

### `VinculacionAcademica()` — Defecto

Constructor por defecto que crea un objeto VinculacionAcademica con todos sus atributos en su valor por defecto

### `VinculacionAcademica(UUID valorId, Usuario valorUsuario, ProgramaAcademico valorProgramaAcademico, LocalDate valorFechaVinculacion, LocalDate valorFechaDesvinculacion)` — Parametrizado

Constructor parametrizado que crea un objeto VinculacionAcademica asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único de la vinculación académica dentro del sistema. |
| `valorUsuario` | [Usuario](Usuario.md) | Por referencia | no final | Representa el estudiante o docente que se vincula al programa. |
| `valorProgramaAcademico` | [ProgramaAcademico](ProgramaAcademico.md) | Por referencia | no final | Representa el programa académico en el que estudia o enseña la persona. |
| `valorFechaVinculacion` | LocalDate | Por referencia | no final | Representa la fecha desde la que la persona estudia o enseña en el programa. |
| `valorFechaDesvinculacion` | LocalDate | Por referencia | no final | Representa la fecha en que la persona dejó el programa; vacía mientras el vínculo está vigente. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getUsuario()` | `setUsuario(Usuario valorUsuario)` | [Usuario](Usuario.md) |
| `getProgramaAcademico()` | `setProgramaAcademico(ProgramaAcademico valorProgramaAcademico)` | [ProgramaAcademico](ProgramaAcademico.md) |
| `getFechaVinculacion()` | `setFechaVinculacion(LocalDate valorFechaVinculacion)` | LocalDate |
| `getFechaDesvinculacion()` | `setFechaDesvinculacion(LocalDate valorFechaDesvinculacion)` | LocalDate |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`vincularPersonaAProgramaAcademico`](#vincularpersonaaprogramaacademico) | [VinculacionAcademica](VinculacionAcademica.md) | Vincular a un estudiante o docente con un programa adicional a los que indicó al registrarse. Una persona puede estar en varios programas a la vez (RN-053, RN-061). |
| 2 | [`desvincularPersonaDeProgramaAcademico`](#desvincularpersonadeprogramaacademico) | void | Terminar el vínculo de un estudiante o docente con un programa. El vínculo no se borra: queda con fecha de desvinculación (RN-057, RN-065). |
| 3 | [`vincularPersonaAlRegistrarse`](#vincularpersonaalregistrarse) | void | Crear las vinculaciones de un estudiante o docente con los programas que indicó al registrarse o al volver a presentar su registro. Lo hace el sistema; esos programas indican qué Coordinador de Programa debe aprobarlo. |
| 4 | [`desvincularPersonaDeTodosLosProgramas`](#desvincularpersonadetodoslosprogramas) | void | Cerrar con fecha todas las vinculaciones vigentes de una persona cuando se rechaza su registro o se desactiva su cuenta. Lo hace el sistema; al suspender no se tocan. |
| 5 | [`consultarVinculacionesAcademicas`](#consultarvinculacionesacademicas) | [List&lt;VinculacionAcademica&gt;](VinculacionAcademica.md) | Consultar en qué programas está una persona o qué estudiantes y docentes tiene un programa, incluido el historial (RF-027, RF-028, RF-032, RF-033). |

### `vincularPersonaAProgramaAcademico`

```java
public VinculacionAcademica vincularPersonaAProgramaAcademico(Usuario usuario, ProgramaAcademico programaAcademico, LocalDate fechaVinculacion)
```

Vincular a un estudiante o docente con un programa adicional a los que indicó al registrarse. Una persona puede estar en varios programas a la vez (RN-053, RN-061).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `usuario` | [Usuario](Usuario.md) | Por referencia | Representa el estudiante o docente que se vincula al programa. |
| `programaAcademico` | [ProgramaAcademico](ProgramaAcademico.md) | Por referencia | Representa el programa académico en el que estudia o enseña la persona. |
| `fechaVinculacion` | LocalDate | Por referencia | Representa la fecha desde la que la persona estudia o enseña en el programa. |

<details>
<summary><b>Excepciones (11)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0007**](../modelo-dominio/Usuario.md#p-usu-0007) — Solo un Coordinador de Programa puede realizar esta acción.
- [**P-VIN-0004**](../modelo-dominio/VinculacionAcademica.md#p-vin-0004) — Se informa cuál dato no cumple y cómo debe escribirse.
- [**P-PRO-0001**](../modelo-dominio/ProgramaAcademico.md#p-pro-0001) — No existe el programa académico que se indicó.
- [**P-COP-0009**](../modelo-dominio/CoordinacionPrograma.md#p-cop-0009) — Usted no coordina ese programa.
- [**P-USU-0009**](../modelo-dominio/Usuario.md#p-usu-0009) — La persona indicada no existe o su cuenta no está activa.
- [**P-USU-0011**](../modelo-dominio/Usuario.md#p-usu-0011) — Solo se pueden vincular a un programa estudiantes y docentes.
- [**P-VIN-0006**](../modelo-dominio/VinculacionAcademica.md#p-vin-0006) — La persona ya está vinculada a ese programa.
- [**P-VIN-0007**](../modelo-dominio/VinculacionAcademica.md#p-vin-0007) — Ya existe esa vinculación.
- [**P-VIN-0008**](../modelo-dominio/VinculacionAcademica.md#p-vin-0008) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `desvincularPersonaDeProgramaAcademico`

```java
public void desvincularPersonaDeProgramaAcademico(UUID id, LocalDate fechaDesvinculacion)
```

Terminar el vínculo de un estudiante o docente con un programa. El vínculo no se borra: queda con fecha de desvinculación (RN-057, RN-065).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la vinculación. |
| `fechaDesvinculacion` | LocalDate | Por referencia | Representa la fecha en que la persona dejó el programa; vacía mientras el vínculo está vigente. |

<details>
<summary><b>Excepciones (7)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0007**](../modelo-dominio/Usuario.md#p-usu-0007) — Solo un Coordinador de Programa puede realizar esta acción.
- [**P-VIN-0003**](../modelo-dominio/VinculacionAcademica.md#p-vin-0003) — No existe la vinculación académica que se indicó.
- [**P-COP-0009**](../modelo-dominio/CoordinacionPrograma.md#p-cop-0009) — Usted no coordina ese programa.
- [**P-VIN-0009**](../modelo-dominio/VinculacionAcademica.md#p-vin-0009) — La persona ya estaba desvinculada de ese programa.
- [**P-VIN-0005**](../modelo-dominio/VinculacionAcademica.md#p-vin-0005) — La fecha de desvinculación no puede ser anterior a la de vinculación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `vincularPersonaAlRegistrarse`

```java
public void vincularPersonaAlRegistrarse(Usuario usuario, LocalDate fechaVinculacion, List<ProgramaAcademico> programas)
```

Crear las vinculaciones de un estudiante o docente con los programas que indicó al registrarse o al volver a presentar su registro. Lo hace el sistema; esos programas indican qué Coordinador de Programa debe aprobarlo.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `usuario` | [Usuario](Usuario.md) | Por referencia | Representa el estudiante o docente que se vincula al programa. |
| `fechaVinculacion` | LocalDate | Por referencia | Representa la fecha desde la que la persona estudia o enseña en el programa. |
| `programas` | [List&lt;ProgramaAcademico&gt;](ProgramaAcademico.md) | Por referencia | Programas que indicó. |

<details>
<summary><b>Excepciones (4)</b></summary>

- [**P-VIN-0001**](../modelo-dominio/VinculacionAcademica.md#p-vin-0001) — No se pudieron crear las vinculaciones; se deshace toda la operación.
- [**P-PRO-0001**](../modelo-dominio/ProgramaAcademico.md#p-pro-0001) — No existe el programa académico que se indicó.
- [**P-VIN-0006**](../modelo-dominio/VinculacionAcademica.md#p-vin-0006) — La persona ya está vinculada a ese programa.
- [**P-VIN-0008**](../modelo-dominio/VinculacionAcademica.md#p-vin-0008) — No se informa al actor: el sistema genera un nuevo identificador.

</details>

### `desvincularPersonaDeTodosLosProgramas`

```java
public void desvincularPersonaDeTodosLosProgramas(Usuario usuario, LocalDate fechaDesvinculacion)
```

Cerrar con fecha todas las vinculaciones vigentes de una persona cuando se rechaza su registro o se desactiva su cuenta. Lo hace el sistema; al suspender no se tocan.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `usuario` | [Usuario](Usuario.md) | Por referencia | Representa el estudiante o docente que se vincula al programa. |
| `fechaDesvinculacion` | LocalDate | Por referencia | Representa la fecha en que la persona dejó el programa; vacía mientras el vínculo está vigente. |

<details>
<summary><b>Excepciones (2)</b></summary>

- [**P-VIN-0009**](../modelo-dominio/VinculacionAcademica.md#p-vin-0009) — La persona ya estaba desvinculada de ese programa.
- [**P-VIN-0002**](../modelo-dominio/VinculacionAcademica.md#p-vin-0002) — No se pudieron cerrar las vinculaciones; se deshace toda la operación.

</details>

### `consultarVinculacionesAcademicas`

```java
public List<VinculacionAcademica> consultarVinculacionesAcademicas(Usuario usuario, ProgramaAcademico programaAcademico)
```

Consultar en qué programas está una persona o qué estudiantes y docentes tiene un programa, incluido el historial (RF-027, RF-028, RF-032, RF-033).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `usuario` | [Usuario](Usuario.md) | Por referencia | Representa el estudiante o docente que se vincula al programa. |
| `programaAcademico` | [ProgramaAcademico](ProgramaAcademico.md) | Por referencia | Representa el programa académico en el que estudia o enseña la persona. |

<details>
<summary><b>Excepciones (1)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ CoordinacionPrograma](CoordinacionPrograma.md) · [Kudo ➡️](Kudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/VinculacionAcademica.md) · [🗄️ MER](../mer/VinculacionAcademica.md)
