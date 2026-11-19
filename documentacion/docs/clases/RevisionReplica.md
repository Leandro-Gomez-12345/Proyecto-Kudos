# 📐 RevisionReplica

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ CalificacionReplica](CalificacionReplica.md) · [ReporteKudo ➡️](ReporteKudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/RevisionReplica.md) · [🗄️ MER](../mer/RevisionReplica.md)

> Revisión de moderación sobre una réplica, hecha por el Rector o el Coordinador Académico. Resuelve a la vez todos sus reportes y termina vetando o liberando el contenido; un veto directo es una revisión sin reportes.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Moderación |

## Vista rápida

```java
public class RevisionReplica {

    private UUID id = new UUID(0L, 0L);
    private Replica replica = new Replica();
    private Usuario moderador = new Usuario();
    private EstadoRevision estadoRevision = new EstadoRevision();
    private String justificacion = "";
    private LocalDateTime fechaInicio = LocalDateTime.of(1000, 1, 1, 0, 0);
    private LocalDateTime fechaCierre = LocalDateTime.of(1000, 1, 1, 0, 0);

    public RevisionReplica()
    public RevisionReplica(UUID valorId, Replica valorReplica, Usuario valorModerador, EstadoRevision valorEstadoRevision, String valorJustificacion, LocalDateTime valorFechaInicio, LocalDateTime valorFechaCierre)

    public RevisionReplica iniciarRevisionReplica(Replica replica)
    public void cerrarRevisionReplicaConVeto(UUID id, String justificacion)
    public void cerrarRevisionReplicaConLiberacion(UUID id, String justificacion)
    public RevisionReplica vetarReplicaDirectamente(Replica replica, String justificacion)
    public List<RevisionReplica> consultarRevisionesReplica(Replica replica, Usuario moderador, EstadoRevision estadoRevision)

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. RevisionReplica | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Necesita de | Composición | [Replica](Replica.md) | 0..\* | 1 | `RevisionReplica.replica` |
| Necesita de | Asociación | [Usuario](Usuario.md) | 0..\* | 1 | `RevisionReplica.moderador` |
| Necesita de | Asociación | [EstadoRevision](EstadoRevision.md) | 0..\* | 1 | `RevisionReplica.estadoRevision` |
| Es usada por | Asociación | [ReporteReplica](ReporteReplica.md) | 0..1 | 0..\* | `ReporteReplica.revisionReplica` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único de la revisión dentro del sistema. |
| `private` | `replica` | [Replica](Replica.md) | no final | no static | `new Replica()` | Representa una réplica que se revisa. |
| `private` | `moderador` | [Usuario](Usuario.md) | no final | no static | `new Usuario()` | Representa la autoridad (Rector o Coordinador Académico) que inicia la revisión. Quien la cierra puede ser cualquiera de las dos y queda en la Auditoría. |
| `private` | `estadoRevision` | [EstadoRevision](EstadoRevision.md) | no final | no static | `new EstadoRevision()` | Representa si la revisión sigue en curso o cómo se cerró. |
| `private` | `justificacion` | String | no final | no static | `""` | Representa la razón de la decisión, necesaria para reconstruir la moderación. |
| `private` | `fechaInicio` | LocalDateTime | no final | no static | `LocalDateTime.of(1000, 1, 1, 0, 0)` | Representa la fecha y hora en que se abrió la revisión. |
| `private` | `fechaCierre` | LocalDateTime | no final | no static | `LocalDateTime.of(1000, 1, 1, 0, 0)` | Representa la fecha y hora en que se tomó la decisión; vacía mientras la revisión está en curso. |

## Constructores

### `RevisionReplica()` — Defecto

Constructor por defecto que crea un objeto RevisionReplica con todos sus atributos en su valor por defecto

### `RevisionReplica(UUID valorId, Replica valorReplica, Usuario valorModerador, EstadoRevision valorEstadoRevision, String valorJustificacion, LocalDateTime valorFechaInicio, LocalDateTime valorFechaCierre)` — Parametrizado

Constructor parametrizado que crea un objeto RevisionReplica asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único de la revisión dentro del sistema. |
| `valorReplica` | [Replica](Replica.md) | Por referencia | no final | Representa una réplica que se revisa. |
| `valorModerador` | [Usuario](Usuario.md) | Por referencia | no final | Representa la autoridad (Rector o Coordinador Académico) que inicia la revisión. Quien la cierra puede ser cualquiera de las dos y queda en la Auditoría. |
| `valorEstadoRevision` | [EstadoRevision](EstadoRevision.md) | Por referencia | no final | Representa si la revisión sigue en curso o cómo se cerró. |
| `valorJustificacion` | String | Por valor | no final | Representa la razón de la decisión, necesaria para reconstruir la moderación. |
| `valorFechaInicio` | LocalDateTime | Por referencia | no final | Representa la fecha y hora en que se abrió la revisión. |
| `valorFechaCierre` | LocalDateTime | Por referencia | no final | Representa la fecha y hora en que se tomó la decisión; vacía mientras la revisión está en curso. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getReplica()` | `setReplica(Replica valorReplica)` | [Replica](Replica.md) |
| `getModerador()` | `setModerador(Usuario valorModerador)` | [Usuario](Usuario.md) |
| `getEstadoRevision()` | `setEstadoRevision(EstadoRevision valorEstadoRevision)` | [EstadoRevision](EstadoRevision.md) |
| `getJustificacion()` | `setJustificacion(String valorJustificacion)` | String |
| `getFechaInicio()` | `setFechaInicio(LocalDateTime valorFechaInicio)` | LocalDateTime |
| `getFechaCierre()` | `setFechaCierre(LocalDateTime valorFechaCierre)` | LocalDateTime |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`iniciarRevisionReplica`](#iniciarrevisionreplica) | [RevisionReplica](RevisionReplica.md) | Tomar una réplica reportada para analizarla. La réplica pasa a En revisión y sus reportes pendientes quedan enlazados a esta revisión, por lo que se ven En revisión. |
| 2 | [`cerrarRevisionReplicaConVeto`](#cerrarrevisionreplicaconveto) | void | Decidir que la réplica incumple las políticas institucionales: la revisión se cierra, la réplica queda Vetada y sus reportes se ven Aceptados porque su revisión terminó en veto. La puede cerrar el Rector o el Coordinador Académico aunque no la haya iniciado (quien cierra queda en la Auditoría) y el veto es definitivo. Así se veta sin atributos de veto en la réplica. |
| 3 | [`cerrarRevisionReplicaConLiberacion`](#cerrarrevisionreplicaconliberacion) | void | Decidir que la réplica no incumple las políticas: la revisión se cierra, la réplica vuelve a Visible y sus reportes se ven Rechazados porque su revisión terminó en liberación. |
| 4 | [`vetarReplicaDirectamente`](#vetarreplicadirectamente) | [RevisionReplica](RevisionReplica.md) | Vetar una réplica por decisión de la autoridad aunque nadie la haya reportado. Queda registrado como una revisión que nace cerrada; si tenía reportes pendientes, quedan enlazados a ella y se ven Aceptados. |
| 5 | [`consultarRevisionesReplica`](#consultarrevisionesreplica) | [List&lt;RevisionReplica&gt;](RevisionReplica.md) | Consultar las revisiones en curso y el histórico de decisiones de moderación sobre las réplicas (CU-015, RF-068). |

### `iniciarRevisionReplica`

```java
public RevisionReplica iniciarRevisionReplica(Replica replica)
```

Tomar una réplica reportada para analizarla. La réplica pasa a En revisión y sus reportes pendientes quedan enlazados a esta revisión, por lo que se ven En revisión.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `replica` | [Replica](Replica.md) | Por referencia | Representa una réplica que se revisa. |

<details>
<summary><b>Excepciones (11)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0003**](../modelo-dominio/Usuario.md#p-usu-0003) — Solo el Rector y el Coordinador Académico pueden moderar contenido.
- [**P-REP-0001**](../modelo-dominio/Replica.md#p-rep-0001) — No existe la réplica que se indicó.
- [**P-REP-0004**](../modelo-dominio/Replica.md#p-rep-0004) — La réplica no tiene reportes pendientes por revisar.
- [**P-RVR-0002**](../modelo-dominio/RevisionReplica.md#p-rvr-0002) — La réplica ya tiene una revisión en curso.
- [**P-RVR-0003**](../modelo-dominio/RevisionReplica.md#p-rvr-0003) — Ya existe esa revisión.
- [**P-RVR-0004**](../modelo-dominio/RevisionReplica.md#p-rvr-0004) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-RVR-0005**](../modelo-dominio/RevisionReplica.md#p-rvr-0005) — No se pudo iniciar la revisión; se deshace toda la operación.
- [**P-REP-0009**](../modelo-dominio/Replica.md#p-rep-0009) — No se pudo actualizar el estado; se deshace toda la operación.
- [**P-RPR-0005**](../modelo-dominio/ReporteReplica.md#p-rpr-0005) — No se pudieron enlazar los reportes; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `cerrarRevisionReplicaConVeto`

```java
public void cerrarRevisionReplicaConVeto(UUID id, String justificacion)
```

Decidir que la réplica incumple las políticas institucionales: la revisión se cierra, la réplica queda Vetada y sus reportes se ven Aceptados porque su revisión terminó en veto. La puede cerrar el Rector o el Coordinador Académico aunque no la haya iniciado (quien cierra queda en la Auditoría) y el veto es definitivo. Así se veta sin atributos de veto en la réplica.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la revisión. |
| `justificacion` | String | Por valor | Representa la razón de la decisión, necesaria para reconstruir la moderación. |

<details>
<summary><b>Excepciones (8)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0003**](../modelo-dominio/Usuario.md#p-usu-0003) — Solo el Rector y el Coordinador Académico pueden moderar contenido.
- [**P-RVR-0001**](../modelo-dominio/RevisionReplica.md#p-rvr-0001) — No existe la revisión que se indicó.
- [**P-RVR-0006**](../modelo-dominio/RevisionReplica.md#p-rvr-0006) — La revisión ya fue cerrada.
- [**P-RVR-0007**](../modelo-dominio/RevisionReplica.md#p-rvr-0007) — Debe escribir la justificación de la decisión (entre 10 y 1000 caracteres).
- [**P-RVR-0008**](../modelo-dominio/RevisionReplica.md#p-rvr-0008) — No se pudo cerrar la revisión; se deshace toda la operación.
- [**P-REP-0010**](../modelo-dominio/Replica.md#p-rep-0010) — No se pudo vetar el contenido; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `cerrarRevisionReplicaConLiberacion`

```java
public void cerrarRevisionReplicaConLiberacion(UUID id, String justificacion)
```

Decidir que la réplica no incumple las políticas: la revisión se cierra, la réplica vuelve a Visible y sus reportes se ven Rechazados porque su revisión terminó en liberación.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la revisión. |
| `justificacion` | String | Por valor | Representa la razón de la decisión, necesaria para reconstruir la moderación. |

<details>
<summary><b>Excepciones (8)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0003**](../modelo-dominio/Usuario.md#p-usu-0003) — Solo el Rector y el Coordinador Académico pueden moderar contenido.
- [**P-RVR-0001**](../modelo-dominio/RevisionReplica.md#p-rvr-0001) — No existe la revisión que se indicó.
- [**P-RVR-0006**](../modelo-dominio/RevisionReplica.md#p-rvr-0006) — La revisión ya fue cerrada.
- [**P-RVR-0007**](../modelo-dominio/RevisionReplica.md#p-rvr-0007) — Debe escribir la justificación de la decisión (entre 10 y 1000 caracteres).
- [**P-RVR-0009**](../modelo-dominio/RevisionReplica.md#p-rvr-0009) — No se pudo cerrar la revisión; se deshace toda la operación.
- [**P-REP-0011**](../modelo-dominio/Replica.md#p-rep-0011) — No se pudo liberar el contenido; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `vetarReplicaDirectamente`

```java
public RevisionReplica vetarReplicaDirectamente(Replica replica, String justificacion)
```

Vetar una réplica por decisión de la autoridad aunque nadie la haya reportado. Queda registrado como una revisión que nace cerrada; si tenía reportes pendientes, quedan enlazados a ella y se ven Aceptados.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `replica` | [Replica](Replica.md) | Por referencia | Representa una réplica que se revisa. |
| `justificacion` | String | Por valor | Representa la razón de la decisión, necesaria para reconstruir la moderación. |

<details>
<summary><b>Excepciones (11)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0003**](../modelo-dominio/Usuario.md#p-usu-0003) — Solo el Rector y el Coordinador Académico pueden moderar contenido.
- [**P-RVR-0011**](../modelo-dominio/RevisionReplica.md#p-rvr-0011) — Debe indicar el contenido y escribir la justificación del veto.
- [**P-REP-0001**](../modelo-dominio/Replica.md#p-rep-0001) — No existe la réplica que se indicó.
- [**P-REP-0006**](../modelo-dominio/Replica.md#p-rep-0006) — La réplica ya está vetada o tiene una revisión en curso.
- [**P-RVR-0003**](../modelo-dominio/RevisionReplica.md#p-rvr-0003) — Ya existe esa revisión.
- [**P-RVR-0004**](../modelo-dominio/RevisionReplica.md#p-rvr-0004) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-RVR-0010**](../modelo-dominio/RevisionReplica.md#p-rvr-0010) — No se pudo registrar el veto; se deshace toda la operación.
- [**P-REP-0010**](../modelo-dominio/Replica.md#p-rep-0010) — No se pudo vetar el contenido; se deshace toda la operación.
- [**P-RPR-0005**](../modelo-dominio/ReporteReplica.md#p-rpr-0005) — No se pudieron enlazar los reportes; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `consultarRevisionesReplica`

```java
public List<RevisionReplica> consultarRevisionesReplica(Replica replica, Usuario moderador, EstadoRevision estadoRevision)
```

Consultar las revisiones en curso y el histórico de decisiones de moderación sobre las réplicas (CU-015, RF-068).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `replica` | [Replica](Replica.md) | Por referencia | Representa una réplica que se revisa. |
| `moderador` | [Usuario](Usuario.md) | Por referencia | Representa la autoridad (Rector o Coordinador Académico) que inicia la revisión. Quien la cierra puede ser cualquiera de las dos y queda en la Auditoría. |
| `estadoRevision` | [EstadoRevision](EstadoRevision.md) | Por referencia | Representa si la revisión sigue en curso o cómo se cerró. |

<details>
<summary><b>Excepciones (2)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0003**](../modelo-dominio/Usuario.md#p-usu-0003) — Solo el Rector y el Coordinador Académico pueden moderar contenido.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ CalificacionReplica](CalificacionReplica.md) · [ReporteKudo ➡️](ReporteKudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/RevisionReplica.md) · [🗄️ MER](../mer/RevisionReplica.md)
