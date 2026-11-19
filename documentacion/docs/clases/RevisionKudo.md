# 📐 RevisionKudo

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ CalificacionKudo](CalificacionKudo.md) · [CalificacionReplica ➡️](CalificacionReplica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/RevisionKudo.md) · [🗄️ MER](../mer/RevisionKudo.md)

> Revisión de moderación sobre un Kudo, hecha por el Rector o el Coordinador Académico. Resuelve a la vez todos sus reportes y termina vetando o liberando el contenido; un veto directo es una revisión sin reportes.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Moderación |

## Vista rápida

```java
public class RevisionKudo {

    private UUID id = new UUID(0L, 0L);
    private Kudo kudo = new Kudo();
    private Usuario moderador = new Usuario();
    private EstadoRevision estadoRevision = new EstadoRevision();
    private String justificacion = "";
    private LocalDateTime fechaInicio = LocalDateTime.of(1000, 1, 1, 0, 0);
    private LocalDateTime fechaCierre = LocalDateTime.of(1000, 1, 1, 0, 0);

    public RevisionKudo()
    public RevisionKudo(UUID valorId, Kudo valorKudo, Usuario valorModerador, EstadoRevision valorEstadoRevision, String valorJustificacion, LocalDateTime valorFechaInicio, LocalDateTime valorFechaCierre)

    public RevisionKudo iniciarRevisionKudo(Kudo kudo)
    public void cerrarRevisionKudoConVeto(UUID id, String justificacion)
    public void cerrarRevisionKudoConLiberacion(UUID id, String justificacion)
    public RevisionKudo vetarKudoDirectamente(Kudo kudo, String justificacion)
    public List<RevisionKudo> consultarRevisionesKudo(Kudo kudo, Usuario moderador, EstadoRevision estadoRevision)

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. RevisionKudo | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Necesita de | Composición | [Kudo](Kudo.md) | 0..\* | 1 | `RevisionKudo.kudo` |
| Necesita de | Asociación | [Usuario](Usuario.md) | 0..\* | 1 | `RevisionKudo.moderador` |
| Necesita de | Asociación | [EstadoRevision](EstadoRevision.md) | 0..\* | 1 | `RevisionKudo.estadoRevision` |
| Es usada por | Asociación | [ReporteKudo](ReporteKudo.md) | 0..1 | 0..\* | `ReporteKudo.revisionKudo` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único de la revisión dentro del sistema. |
| `private` | `kudo` | [Kudo](Kudo.md) | no final | no static | `new Kudo()` | Representa un Kudo que se revisa. |
| `private` | `moderador` | [Usuario](Usuario.md) | no final | no static | `new Usuario()` | Representa la autoridad (Rector o Coordinador Académico) que inicia la revisión. Quien la cierra puede ser cualquiera de las dos y queda en la Auditoría. |
| `private` | `estadoRevision` | [EstadoRevision](EstadoRevision.md) | no final | no static | `new EstadoRevision()` | Representa si la revisión sigue en curso o cómo se cerró. |
| `private` | `justificacion` | String | no final | no static | `""` | Representa la razón de la decisión, necesaria para reconstruir la moderación. |
| `private` | `fechaInicio` | LocalDateTime | no final | no static | `LocalDateTime.of(1000, 1, 1, 0, 0)` | Representa la fecha y hora en que se abrió la revisión. |
| `private` | `fechaCierre` | LocalDateTime | no final | no static | `LocalDateTime.of(1000, 1, 1, 0, 0)` | Representa la fecha y hora en que se tomó la decisión; vacía mientras la revisión está en curso. |

## Constructores

### `RevisionKudo()` — Defecto

Constructor por defecto que crea un objeto RevisionKudo con todos sus atributos en su valor por defecto

### `RevisionKudo(UUID valorId, Kudo valorKudo, Usuario valorModerador, EstadoRevision valorEstadoRevision, String valorJustificacion, LocalDateTime valorFechaInicio, LocalDateTime valorFechaCierre)` — Parametrizado

Constructor parametrizado que crea un objeto RevisionKudo asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único de la revisión dentro del sistema. |
| `valorKudo` | [Kudo](Kudo.md) | Por referencia | no final | Representa un Kudo que se revisa. |
| `valorModerador` | [Usuario](Usuario.md) | Por referencia | no final | Representa la autoridad (Rector o Coordinador Académico) que inicia la revisión. Quien la cierra puede ser cualquiera de las dos y queda en la Auditoría. |
| `valorEstadoRevision` | [EstadoRevision](EstadoRevision.md) | Por referencia | no final | Representa si la revisión sigue en curso o cómo se cerró. |
| `valorJustificacion` | String | Por valor | no final | Representa la razón de la decisión, necesaria para reconstruir la moderación. |
| `valorFechaInicio` | LocalDateTime | Por referencia | no final | Representa la fecha y hora en que se abrió la revisión. |
| `valorFechaCierre` | LocalDateTime | Por referencia | no final | Representa la fecha y hora en que se tomó la decisión; vacía mientras la revisión está en curso. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getKudo()` | `setKudo(Kudo valorKudo)` | [Kudo](Kudo.md) |
| `getModerador()` | `setModerador(Usuario valorModerador)` | [Usuario](Usuario.md) |
| `getEstadoRevision()` | `setEstadoRevision(EstadoRevision valorEstadoRevision)` | [EstadoRevision](EstadoRevision.md) |
| `getJustificacion()` | `setJustificacion(String valorJustificacion)` | String |
| `getFechaInicio()` | `setFechaInicio(LocalDateTime valorFechaInicio)` | LocalDateTime |
| `getFechaCierre()` | `setFechaCierre(LocalDateTime valorFechaCierre)` | LocalDateTime |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`iniciarRevisionKudo`](#iniciarrevisionkudo) | [RevisionKudo](RevisionKudo.md) | Tomar un Kudo reportado para analizarlo. El Kudo pasa a En revisión y sus reportes pendientes quedan enlazados a esta revisión, por lo que se ven En revisión. |
| 2 | [`cerrarRevisionKudoConVeto`](#cerrarrevisionkudoconveto) | void | Decidir que el Kudo incumple las políticas institucionales: la revisión se cierra, el Kudo queda Vetado y sus reportes se ven Aceptados porque su revisión terminó en veto. La puede cerrar el Rector o el Coordinador Académico aunque no la haya iniciado (quien cierra queda en la Auditoría) y el veto es definitivo. Así se veta sin atributos de veto en el Kudo. |
| 3 | [`cerrarRevisionKudoConLiberacion`](#cerrarrevisionkudoconliberacion) | void | Decidir que el Kudo no incumple las políticas: la revisión se cierra, el Kudo vuelve a Visible y sus reportes se ven Rechazados porque su revisión terminó en liberación. |
| 4 | [`vetarKudoDirectamente`](#vetarkudodirectamente) | [RevisionKudo](RevisionKudo.md) | Vetar un Kudo por decisión de la autoridad aunque nadie lo haya reportado. Queda registrado como una revisión que nace cerrada; si tenía reportes pendientes, quedan enlazados a ella y se ven Aceptados. |
| 5 | [`consultarRevisionesKudo`](#consultarrevisioneskudo) | [List&lt;RevisionKudo&gt;](RevisionKudo.md) | Consultar las revisiones en curso y el histórico de decisiones de moderación sobre los Kudos (CU-015, RF-068). |

### `iniciarRevisionKudo`

```java
public RevisionKudo iniciarRevisionKudo(Kudo kudo)
```

Tomar un Kudo reportado para analizarlo. El Kudo pasa a En revisión y sus reportes pendientes quedan enlazados a esta revisión, por lo que se ven En revisión.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `kudo` | [Kudo](Kudo.md) | Por referencia | Representa un Kudo que se revisa. |

<details>
<summary><b>Excepciones (11)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0003**](../modelo-dominio/Usuario.md#p-usu-0003) — Solo el Rector y el Coordinador Académico pueden moderar contenido.
- [**P-KUD-0001**](../modelo-dominio/Kudo.md#p-kud-0001) — No existe el Kudo que se indicó.
- [**P-KUD-0004**](../modelo-dominio/Kudo.md#p-kud-0004) — El Kudo no tiene reportes pendientes por revisar.
- [**P-RVK-0002**](../modelo-dominio/RevisionKudo.md#p-rvk-0002) — El Kudo ya tiene una revisión en curso.
- [**P-RVK-0003**](../modelo-dominio/RevisionKudo.md#p-rvk-0003) — Ya existe esa revisión.
- [**P-RVK-0004**](../modelo-dominio/RevisionKudo.md#p-rvk-0004) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-RVK-0005**](../modelo-dominio/RevisionKudo.md#p-rvk-0005) — No se pudo iniciar la revisión; se deshace toda la operación.
- [**P-KUD-0009**](../modelo-dominio/Kudo.md#p-kud-0009) — No se pudo actualizar el estado; se deshace toda la operación.
- [**P-RPK-0005**](../modelo-dominio/ReporteKudo.md#p-rpk-0005) — No se pudieron enlazar los reportes; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `cerrarRevisionKudoConVeto`

```java
public void cerrarRevisionKudoConVeto(UUID id, String justificacion)
```

Decidir que el Kudo incumple las políticas institucionales: la revisión se cierra, el Kudo queda Vetado y sus reportes se ven Aceptados porque su revisión terminó en veto. La puede cerrar el Rector o el Coordinador Académico aunque no la haya iniciado (quien cierra queda en la Auditoría) y el veto es definitivo. Así se veta sin atributos de veto en el Kudo.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la revisión. |
| `justificacion` | String | Por valor | Representa la razón de la decisión, necesaria para reconstruir la moderación. |

<details>
<summary><b>Excepciones (8)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0003**](../modelo-dominio/Usuario.md#p-usu-0003) — Solo el Rector y el Coordinador Académico pueden moderar contenido.
- [**P-RVK-0001**](../modelo-dominio/RevisionKudo.md#p-rvk-0001) — No existe la revisión que se indicó.
- [**P-RVK-0006**](../modelo-dominio/RevisionKudo.md#p-rvk-0006) — La revisión ya fue cerrada.
- [**P-RVK-0007**](../modelo-dominio/RevisionKudo.md#p-rvk-0007) — Debe escribir la justificación de la decisión (entre 10 y 1000 caracteres).
- [**P-RVK-0008**](../modelo-dominio/RevisionKudo.md#p-rvk-0008) — No se pudo cerrar la revisión; se deshace toda la operación.
- [**P-KUD-0010**](../modelo-dominio/Kudo.md#p-kud-0010) — No se pudo vetar el contenido; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `cerrarRevisionKudoConLiberacion`

```java
public void cerrarRevisionKudoConLiberacion(UUID id, String justificacion)
```

Decidir que el Kudo no incumple las políticas: la revisión se cierra, el Kudo vuelve a Visible y sus reportes se ven Rechazados porque su revisión terminó en liberación.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la revisión. |
| `justificacion` | String | Por valor | Representa la razón de la decisión, necesaria para reconstruir la moderación. |

<details>
<summary><b>Excepciones (8)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0003**](../modelo-dominio/Usuario.md#p-usu-0003) — Solo el Rector y el Coordinador Académico pueden moderar contenido.
- [**P-RVK-0001**](../modelo-dominio/RevisionKudo.md#p-rvk-0001) — No existe la revisión que se indicó.
- [**P-RVK-0006**](../modelo-dominio/RevisionKudo.md#p-rvk-0006) — La revisión ya fue cerrada.
- [**P-RVK-0007**](../modelo-dominio/RevisionKudo.md#p-rvk-0007) — Debe escribir la justificación de la decisión (entre 10 y 1000 caracteres).
- [**P-RVK-0009**](../modelo-dominio/RevisionKudo.md#p-rvk-0009) — No se pudo cerrar la revisión; se deshace toda la operación.
- [**P-KUD-0011**](../modelo-dominio/Kudo.md#p-kud-0011) — No se pudo liberar el contenido; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `vetarKudoDirectamente`

```java
public RevisionKudo vetarKudoDirectamente(Kudo kudo, String justificacion)
```

Vetar un Kudo por decisión de la autoridad aunque nadie lo haya reportado. Queda registrado como una revisión que nace cerrada; si tenía reportes pendientes, quedan enlazados a ella y se ven Aceptados.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `kudo` | [Kudo](Kudo.md) | Por referencia | Representa un Kudo que se revisa. |
| `justificacion` | String | Por valor | Representa la razón de la decisión, necesaria para reconstruir la moderación. |

<details>
<summary><b>Excepciones (11)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0003**](../modelo-dominio/Usuario.md#p-usu-0003) — Solo el Rector y el Coordinador Académico pueden moderar contenido.
- [**P-RVK-0011**](../modelo-dominio/RevisionKudo.md#p-rvk-0011) — Debe indicar el contenido y escribir la justificación del veto.
- [**P-KUD-0001**](../modelo-dominio/Kudo.md#p-kud-0001) — No existe el Kudo que se indicó.
- [**P-KUD-0006**](../modelo-dominio/Kudo.md#p-kud-0006) — El Kudo ya está vetado o tiene una revisión en curso.
- [**P-RVK-0003**](../modelo-dominio/RevisionKudo.md#p-rvk-0003) — Ya existe esa revisión.
- [**P-RVK-0004**](../modelo-dominio/RevisionKudo.md#p-rvk-0004) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-RVK-0010**](../modelo-dominio/RevisionKudo.md#p-rvk-0010) — No se pudo registrar el veto; se deshace toda la operación.
- [**P-KUD-0010**](../modelo-dominio/Kudo.md#p-kud-0010) — No se pudo vetar el contenido; se deshace toda la operación.
- [**P-RPK-0005**](../modelo-dominio/ReporteKudo.md#p-rpk-0005) — No se pudieron enlazar los reportes; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `consultarRevisionesKudo`

```java
public List<RevisionKudo> consultarRevisionesKudo(Kudo kudo, Usuario moderador, EstadoRevision estadoRevision)
```

Consultar las revisiones en curso y el histórico de decisiones de moderación sobre los Kudos (CU-015, RF-068).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `kudo` | [Kudo](Kudo.md) | Por referencia | Representa un Kudo que se revisa. |
| `moderador` | [Usuario](Usuario.md) | Por referencia | Representa la autoridad (Rector o Coordinador Académico) que inicia la revisión. Quien la cierra puede ser cualquiera de las dos y queda en la Auditoría. |
| `estadoRevision` | [EstadoRevision](EstadoRevision.md) | Por referencia | Representa si la revisión sigue en curso o cómo se cerró. |

<details>
<summary><b>Excepciones (2)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0003**](../modelo-dominio/Usuario.md#p-usu-0003) — Solo el Rector y el Coordinador Académico pueden moderar contenido.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ CalificacionKudo](CalificacionKudo.md) · [CalificacionReplica ➡️](CalificacionReplica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/RevisionKudo.md) · [🗄️ MER](../mer/RevisionKudo.md)
