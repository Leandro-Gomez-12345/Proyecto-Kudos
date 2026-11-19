# 📐 CalificacionReplica

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ RevisionKudo](RevisionKudo.md) · [RevisionReplica ➡️](RevisionReplica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/CalificacionReplica.md) · [🗄️ MER](../mer/CalificacionReplica.md)

> Valoración de 1 a 5 estrellas que una persona da a la réplica. Cada persona tiene una sola calificación por réplica y puede modificarla.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Reconocimientos y conversación |

## Vista rápida

```java
public class CalificacionReplica {

    private UUID id = new UUID(0L, 0L);
    private Replica replica = new Replica();
    private Usuario usuario = new Usuario();
    private int/Integer estrellas = 0;
    private LocalDateTime fechaCalificacion = LocalDateTime.of(1000, 1, 1, 0, 0);

    public CalificacionReplica()
    public CalificacionReplica(UUID valorId, Replica valorReplica, Usuario valorUsuario, int/Integer valorEstrellas, LocalDateTime valorFechaCalificacion)

    public CalificacionReplica calificarReplica(Replica replica, int/Integer estrellas)
    public CalificacionReplica cambiarCalificacionReplica(UUID id, int/Integer estrellas)
    public List<CalificacionReplica> consultarCalificacionesReplica(Replica replica, Usuario usuario)

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. CalificacionReplica | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Necesita de | Composición | [Replica](Replica.md) | 0..\* | 1 | `CalificacionReplica.replica` |
| Necesita de | Asociación | [Usuario](Usuario.md) | 0..\* | 1 | `CalificacionReplica.usuario` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único de la calificación dentro del sistema. |
| `private` | `replica` | [Replica](Replica.md) | no final | no static | `new Replica()` | Representa la réplica que se califica. |
| `private` | `usuario` | [Usuario](Usuario.md) | no final | no static | `new Usuario()` | Representa la persona que califica. |
| `private` | `estrellas` | int/Integer | no final | no static | `0` | Representa la cantidad de estrellas que la persona asigna. |
| `private` | `fechaCalificacion` | LocalDateTime | no final | no static | `LocalDateTime.of(1000, 1, 1, 0, 0)` | Representa la fecha y hora en que se registró o se modificó por última vez la calificación. |

## Constructores

### `CalificacionReplica()` — Defecto

Constructor por defecto que crea un objeto CalificacionReplica con todos sus atributos en su valor por defecto

### `CalificacionReplica(UUID valorId, Replica valorReplica, Usuario valorUsuario, int/Integer valorEstrellas, LocalDateTime valorFechaCalificacion)` — Parametrizado

Constructor parametrizado que crea un objeto CalificacionReplica asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único de la calificación dentro del sistema. |
| `valorReplica` | [Replica](Replica.md) | Por referencia | no final | Representa la réplica que se califica. |
| `valorUsuario` | [Usuario](Usuario.md) | Por referencia | no final | Representa la persona que califica. |
| `valorEstrellas` | int/Integer | Por valor | no final | Representa la cantidad de estrellas que la persona asigna. |
| `valorFechaCalificacion` | LocalDateTime | Por referencia | no final | Representa la fecha y hora en que se registró o se modificó por última vez la calificación. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getReplica()` | `setReplica(Replica valorReplica)` | [Replica](Replica.md) |
| `getUsuario()` | `setUsuario(Usuario valorUsuario)` | [Usuario](Usuario.md) |
| `getEstrellas()` | `setEstrellas(int/Integer valorEstrellas)` | int/Integer |
| `getFechaCalificacion()` | `setFechaCalificacion(LocalDateTime valorFechaCalificacion)` | LocalDateTime |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`calificarReplica`](#calificarreplica) | [CalificacionReplica](CalificacionReplica.md) | Valorar la réplica con 1 a 5 estrellas. Cada persona tiene una sola calificación por contenido. |
| 2 | [`cambiarCalificacionReplica`](#cambiarcalificacionreplica) | [CalificacionReplica](CalificacionReplica.md) | Cambiar las estrellas de una calificación propia (EVT-033). |
| 3 | [`consultarCalificacionesReplica`](#consultarcalificacionesreplica) | [List&lt;CalificacionReplica&gt;](CalificacionReplica.md) | Ver las valoraciones que ha recibido la réplica (CU-009). |

### `calificarReplica`

```java
public CalificacionReplica calificarReplica(Replica replica, int/Integer estrellas)
```

Valorar la réplica con 1 a 5 estrellas. Cada persona tiene una sola calificación por contenido.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `replica` | [Replica](Replica.md) | Por referencia | Representa la réplica que se califica. |
| `estrellas` | int/Integer | Por valor | Representa la cantidad de estrellas que la persona asigna. |

<details>
<summary><b>Excepciones (10)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-CAR-0002**](../modelo-dominio/CalificacionReplica.md#p-car-0002) — La calificación debe ser de 1 a 5 estrellas.
- [**P-REP-0001**](../modelo-dominio/Replica.md#p-rep-0001) — No existe la réplica que se indicó.
- [**P-REP-0002**](../modelo-dominio/Replica.md#p-rep-0002) — La réplica fue vetada y ya no admite interacción.
- [**P-KUD-0002**](../modelo-dominio/Kudo.md#p-kud-0002) — El Kudo fue vetado y ya no admite interacción.
- [**P-CAR-0008**](../modelo-dominio/CalificacionReplica.md#p-car-0008) — No puede calificar su propio contenido.
- [**P-CAR-0003**](../modelo-dominio/CalificacionReplica.md#p-car-0003) — Usted ya calificó este contenido; puede cambiar su calificación.
- [**P-CAR-0004**](../modelo-dominio/CalificacionReplica.md#p-car-0004) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-CAR-0005**](../modelo-dominio/CalificacionReplica.md#p-car-0005) — No se pudo guardar la calificación; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `cambiarCalificacionReplica`

```java
public CalificacionReplica cambiarCalificacionReplica(UUID id, int/Integer estrellas)
```

Cambiar las estrellas de una calificación propia (EVT-033).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la calificación. |
| `estrellas` | int/Integer | Por valor | Representa la cantidad de estrellas que la persona asigna. |

<details>
<summary><b>Excepciones (9)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-CAR-0001**](../modelo-dominio/CalificacionReplica.md#p-car-0001) — No existe la calificación que se indicó.
- [**P-CAR-0006**](../modelo-dominio/CalificacionReplica.md#p-car-0006) — Solo puede cambiar sus propias calificaciones.
- [**P-REP-0002**](../modelo-dominio/Replica.md#p-rep-0002) — La réplica fue vetada y ya no admite interacción.
- [**P-KUD-0002**](../modelo-dominio/Kudo.md#p-kud-0002) — El Kudo fue vetado y ya no admite interacción.
- [**P-CAR-0002**](../modelo-dominio/CalificacionReplica.md#p-car-0002) — La calificación debe ser de 1 a 5 estrellas.
- [**P-CAR-0009**](../modelo-dominio/CalificacionReplica.md#p-car-0009) — Esa ya es su calificación actual.
- [**P-CAR-0007**](../modelo-dominio/CalificacionReplica.md#p-car-0007) — No se pudo guardar el cambio; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `consultarCalificacionesReplica`

```java
public List<CalificacionReplica> consultarCalificacionesReplica(Replica replica, Usuario usuario)
```

Ver las valoraciones que ha recibido la réplica (CU-009).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `replica` | [Replica](Replica.md) | Por referencia | Representa la réplica que se califica. |
| `usuario` | [Usuario](Usuario.md) | Por referencia | Representa la persona que califica. |

<details>
<summary><b>Excepciones (2)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-REP-0001**](../modelo-dominio/Replica.md#p-rep-0001) — No existe la réplica que se indicó.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ RevisionKudo](RevisionKudo.md) · [RevisionReplica ➡️](RevisionReplica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/CalificacionReplica.md) · [🗄️ MER](../mer/CalificacionReplica.md)
