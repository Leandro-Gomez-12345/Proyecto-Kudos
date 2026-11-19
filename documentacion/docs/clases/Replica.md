# 📐 Replica

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ Kudo](Kudo.md) · [CalificacionKudo ➡️](CalificacionKudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Replica.md) · [🗄️ MER](../mer/Replica.md)

> Intervención dentro de la conversación de un Kudo. Puede responder directamente al Kudo o a otra réplica, sin límite de profundidad y sin ciclos.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Reconocimientos y conversación |

## Vista rápida

```java
public class Replica {

    private UUID id = new UUID(0L, 0L);
    private Kudo kudo = new Kudo();
    private Replica replicaPadre = Réplica vacía sin padre (id = new UUID(0L, 0L)); no se crea en cascada para no entrar en un ciclo;
    private Usuario autor = new Usuario();
    private String contenido = "";
    private EstadoContenido estadoContenido = new EstadoContenido();
    private LocalDateTime fechaCreacion = LocalDateTime.of(1000, 1, 1, 0, 0);

    public Replica()
    public Replica(UUID valorId, Kudo valorKudo, Replica valorReplicaPadre, Usuario valorAutor, String valorContenido, EstadoContenido valorEstadoContenido, LocalDateTime valorFechaCreacion)

    public Replica replicarKudo(Kudo kudo, String contenido)
    public Replica responderReplica(Replica replicaPadre, String contenido)
    public List<Replica> consultarConversacionKudo(Kudo kudo)
    public void marcarReplicaReportada(UUID id)
    public void ponerReplicaEnRevision(UUID id)
    public void vetarReplica(UUID id)
    public void liberarReplica(UUID id)

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. Replica | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Necesita de | Composición | [Kudo](Kudo.md) | 0..\* | 1 | `Replica.kudo` |
| Necesita de | Asociación | [Replica](Replica.md) | 0..\* | 0..1 | `Replica.replicaPadre` |
| Necesita de | Asociación | [Usuario](Usuario.md) | 0..\* | 1 | `Replica.autor` |
| Necesita de | Asociación | [EstadoContenido](EstadoContenido.md) | 0..\* | 1 | `Replica.estadoContenido` |
| Es usada por | Asociación | [Replica](Replica.md) | 0..1 | 0..\* | `Replica.replicaPadre` |
| Es usada por | Composición | [CalificacionReplica](CalificacionReplica.md) | 1 | 0..\* | `CalificacionReplica.replica` |
| Es usada por | Composición | [RevisionReplica](RevisionReplica.md) | 1 | 0..\* | `RevisionReplica.replica` |
| Es usada por | Composición | [ReporteReplica](ReporteReplica.md) | 1 | 0..\* | `ReporteReplica.replica` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único de la réplica dentro del sistema. |
| `private` | `kudo` | [Kudo](Kudo.md) | no final | no static | `new Kudo()` | Representa el Kudo a cuya conversación pertenece la réplica. |
| `private` | `replicaPadre` | [Replica](Replica.md) | no final | no static | `Réplica vacía sin padre (id = new UUID(0L, 0L)); no se crea en cascada para no entrar en un ciclo` | Representa la réplica a la que se responde; vacía cuando se responde directamente al Kudo. |
| `private` | `autor` | [Usuario](Usuario.md) | no final | no static | `new Usuario()` | Representa la persona que escribe la réplica. |
| `private` | `contenido` | String | no final | no static | `""` | Representa el texto completo de la réplica. |
| `private` | `estadoContenido` | [EstadoContenido](EstadoContenido.md) | no final | no static | `new EstadoContenido()` | Representa en qué estado del ciclo de vida se encuentra la réplica. |
| `private` | `fechaCreacion` | LocalDateTime | no final | no static | `LocalDateTime.of(1000, 1, 1, 0, 0)` | Representa la fecha y hora en que se publicó la réplica. |

## Constructores

### `Replica()` — Defecto

Constructor por defecto que crea un objeto Replica con todos sus atributos en su valor por defecto

### `Replica(UUID valorId, Kudo valorKudo, Replica valorReplicaPadre, Usuario valorAutor, String valorContenido, EstadoContenido valorEstadoContenido, LocalDateTime valorFechaCreacion)` — Parametrizado

Constructor parametrizado que crea un objeto Replica asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único de la réplica dentro del sistema. |
| `valorKudo` | [Kudo](Kudo.md) | Por referencia | no final | Representa el Kudo a cuya conversación pertenece la réplica. |
| `valorReplicaPadre` | [Replica](Replica.md) | Por referencia | no final | Representa la réplica a la que se responde; vacía cuando se responde directamente al Kudo. |
| `valorAutor` | [Usuario](Usuario.md) | Por referencia | no final | Representa la persona que escribe la réplica. |
| `valorContenido` | String | Por valor | no final | Representa el texto completo de la réplica. |
| `valorEstadoContenido` | [EstadoContenido](EstadoContenido.md) | Por referencia | no final | Representa en qué estado del ciclo de vida se encuentra la réplica. |
| `valorFechaCreacion` | LocalDateTime | Por referencia | no final | Representa la fecha y hora en que se publicó la réplica. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getKudo()` | `setKudo(Kudo valorKudo)` | [Kudo](Kudo.md) |
| `getReplicaPadre()` | `setReplicaPadre(Replica valorReplicaPadre)` | [Replica](Replica.md) |
| `getAutor()` | `setAutor(Usuario valorAutor)` | [Usuario](Usuario.md) |
| `getContenido()` | `setContenido(String valorContenido)` | String |
| `getEstadoContenido()` | `setEstadoContenido(EstadoContenido valorEstadoContenido)` | [EstadoContenido](EstadoContenido.md) |
| `getFechaCreacion()` | `setFechaCreacion(LocalDateTime valorFechaCreacion)` | LocalDateTime |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`replicarKudo`](#replicarkudo) | [Replica](Replica.md) | Escribir una réplica directamente sobre un Kudo. No hay límite de réplicas por persona (CU-004, RN-165, RN-166). |
| 2 | [`responderReplica`](#responderreplica) | [Replica](Replica.md) | Responder a otra réplica de la conversación, sin límite de profundidad (CU-005, RN-162, RN-164). |
| 3 | [`consultarConversacionKudo`](#consultarconversacionkudo) | [List&lt;Replica&gt;](Replica.md) | Ver el árbol completo de la conversación de un Kudo (CU-006, RF-055). |
| 4 | [`marcarReplicaReportada`](#marcarreplicareportada) | void | Pasar la réplica de Visible a Reportada. Lo hace el sistema cuando la réplica recibe su primer reporte pendiente. |
| 5 | [`ponerReplicaEnRevision`](#ponerreplicaenrevision) | void | Pasar la réplica de Reportada a En revisión. Lo hace el sistema cuando el Rector o el Coordinador Académico inicia la revisión. |
| 6 | [`vetarReplica`](#vetarreplica) | void | Pasar la réplica a Vetada: deja de estar disponible para la interacción normal. Lo hace el sistema cuando una revisión se cierra con veto o cuando la autoridad veta directamente. No necesita atributos propios del veto: quién, cuándo y por qué quedan en la revisión. |
| 7 | [`liberarReplica`](#liberarreplica) | void | Devolver la réplica a Visible. Lo hace el sistema cuando una revisión se cierra sin veto. |

### `replicarKudo`

```java
public Replica replicarKudo(Kudo kudo, String contenido)
```

Escribir una réplica directamente sobre un Kudo. No hay límite de réplicas por persona (CU-004, RN-165, RN-166).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `kudo` | [Kudo](Kudo.md) | Por referencia | Representa el Kudo a cuya conversación pertenece la réplica. |
| `contenido` | String | Por valor | Representa el texto completo de la réplica. |

<details>
<summary><b>Excepciones (9)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-REP-0014**](../modelo-dominio/Replica.md#p-rep-0014) — Se informa cuál dato no cumple y cómo debe escribirse.
- [**P-KUD-0001**](../modelo-dominio/Kudo.md#p-kud-0001) — No existe el Kudo que se indicó.
- [**P-KUD-0002**](../modelo-dominio/Kudo.md#p-kud-0002) — El Kudo fue vetado y ya no admite interacción.
- [**P-REP-0019**](../modelo-dominio/Replica.md#p-rep-0019) — Ya se registró esa réplica.
- [**P-REP-0013**](../modelo-dominio/Replica.md#p-rep-0013) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-REP-0017**](../modelo-dominio/Replica.md#p-rep-0017) — No se pudo crear la réplica; se deshace toda la operación.
- [**P-REP-0018**](../modelo-dominio/Replica.md#p-rep-0018) — No se pudo publicar la réplica; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `responderReplica`

```java
public Replica responderReplica(Replica replicaPadre, String contenido)
```

Responder a otra réplica de la conversación, sin límite de profundidad (CU-005, RN-162, RN-164).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `replicaPadre` | [Replica](Replica.md) | Por referencia | Representa la réplica a la que se responde; vacía cuando se responde directamente al Kudo. |
| `contenido` | String | Por valor | Representa el texto completo de la réplica. |

<details>
<summary><b>Excepciones (12)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-REP-0014**](../modelo-dominio/Replica.md#p-rep-0014) — Se informa cuál dato no cumple y cómo debe escribirse.
- [**P-REP-0001**](../modelo-dominio/Replica.md#p-rep-0001) — No existe la réplica que se indicó.
- [**P-REP-0002**](../modelo-dominio/Replica.md#p-rep-0002) — La réplica fue vetada y ya no admite interacción.
- [**P-REP-0015**](../modelo-dominio/Replica.md#p-rep-0015) — La réplica a la que responde no pertenece a esta conversación.
- [**P-REP-0016**](../modelo-dominio/Replica.md#p-rep-0016) — No se puede responder a esa réplica.
- [**P-KUD-0002**](../modelo-dominio/Kudo.md#p-kud-0002) — El Kudo fue vetado y ya no admite interacción.
- [**P-REP-0019**](../modelo-dominio/Replica.md#p-rep-0019) — Ya se registró esa réplica.
- [**P-REP-0013**](../modelo-dominio/Replica.md#p-rep-0013) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-REP-0017**](../modelo-dominio/Replica.md#p-rep-0017) — No se pudo crear la réplica; se deshace toda la operación.
- [**P-REP-0018**](../modelo-dominio/Replica.md#p-rep-0018) — No se pudo publicar la réplica; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `consultarConversacionKudo`

```java
public List<Replica> consultarConversacionKudo(Kudo kudo)
```

Ver el árbol completo de la conversación de un Kudo (CU-006, RF-055).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `kudo` | [Kudo](Kudo.md) | Por referencia | Representa el Kudo a cuya conversación pertenece la réplica. |

<details>
<summary><b>Excepciones (2)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-KUD-0001**](../modelo-dominio/Kudo.md#p-kud-0001) — No existe el Kudo que se indicó.

</details>

### `marcarReplicaReportada`

```java
public void marcarReplicaReportada(UUID id)
```

Pasar la réplica de Visible a Reportada. Lo hace el sistema cuando la réplica recibe su primer reporte pendiente.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la réplica. |

<details>
<summary><b>Excepciones (1)</b></summary>

- [**P-REP-0001**](../modelo-dominio/Replica.md#p-rep-0001) — No existe la réplica que se indicó.

</details>

### `ponerReplicaEnRevision`

```java
public void ponerReplicaEnRevision(UUID id)
```

Pasar la réplica de Reportada a En revisión. Lo hace el sistema cuando el Rector o el Coordinador Académico inicia la revisión.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la réplica. |

<details>
<summary><b>Excepciones (2)</b></summary>

- [**P-REP-0001**](../modelo-dominio/Replica.md#p-rep-0001) — No existe la réplica que se indicó.
- [**P-REP-0004**](../modelo-dominio/Replica.md#p-rep-0004) — La réplica no tiene reportes pendientes por revisar.

</details>

### `vetarReplica`

```java
public void vetarReplica(UUID id)
```

Pasar la réplica a Vetada: deja de estar disponible para la interacción normal. Lo hace el sistema cuando una revisión se cierra con veto o cuando la autoridad veta directamente. No necesita atributos propios del veto: quién, cuándo y por qué quedan en la revisión.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la réplica. |

<details>
<summary><b>Excepciones (2)</b></summary>

- [**P-REP-0001**](../modelo-dominio/Replica.md#p-rep-0001) — No existe la réplica que se indicó.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `liberarReplica`

```java
public void liberarReplica(UUID id)
```

Devolver la réplica a Visible. Lo hace el sistema cuando una revisión se cierra sin veto.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la réplica. |

<details>
<summary><b>Excepciones (3)</b></summary>

- [**P-REP-0001**](../modelo-dominio/Replica.md#p-rep-0001) — No existe la réplica que se indicó.
- [**P-REP-0005**](../modelo-dominio/Replica.md#p-rep-0005) — La réplica no está en revisión.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ Kudo](Kudo.md) · [CalificacionKudo ➡️](CalificacionKudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Replica.md) · [🗄️ MER](../mer/Replica.md)
