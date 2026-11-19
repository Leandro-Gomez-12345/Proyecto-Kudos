# 📐 CalificacionKudo

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ Replica](Replica.md) · [RevisionKudo ➡️](RevisionKudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/CalificacionKudo.md) · [🗄️ MER](../mer/CalificacionKudo.md)

> Valoración de 1 a 5 estrellas que una persona da a el Kudo. Cada persona tiene una sola calificación por Kudo y puede modificarla.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Reconocimientos y conversación |

## Vista rápida

```java
public class CalificacionKudo {

    private UUID id = new UUID(0L, 0L);
    private Kudo kudo = new Kudo();
    private Usuario usuario = new Usuario();
    private int/Integer estrellas = 0;
    private LocalDateTime fechaCalificacion = LocalDateTime.of(1000, 1, 1, 0, 0);

    public CalificacionKudo()
    public CalificacionKudo(UUID valorId, Kudo valorKudo, Usuario valorUsuario, int/Integer valorEstrellas, LocalDateTime valorFechaCalificacion)

    public CalificacionKudo calificarKudo(Kudo kudo, int/Integer estrellas)
    public CalificacionKudo cambiarCalificacionKudo(UUID id, int/Integer estrellas)
    public List<CalificacionKudo> consultarCalificacionesKudo(Kudo kudo, Usuario usuario)

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. CalificacionKudo | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Necesita de | Composición | [Kudo](Kudo.md) | 0..\* | 1 | `CalificacionKudo.kudo` |
| Necesita de | Asociación | [Usuario](Usuario.md) | 0..\* | 1 | `CalificacionKudo.usuario` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único de la calificación dentro del sistema. |
| `private` | `kudo` | [Kudo](Kudo.md) | no final | no static | `new Kudo()` | Representa el Kudo que se califica. |
| `private` | `usuario` | [Usuario](Usuario.md) | no final | no static | `new Usuario()` | Representa la persona que califica. |
| `private` | `estrellas` | int/Integer | no final | no static | `0` | Representa la cantidad de estrellas que la persona asigna. |
| `private` | `fechaCalificacion` | LocalDateTime | no final | no static | `LocalDateTime.of(1000, 1, 1, 0, 0)` | Representa la fecha y hora en que se registró o se modificó por última vez la calificación. |

## Constructores

### `CalificacionKudo()` — Defecto

Constructor por defecto que crea un objeto CalificacionKudo con todos sus atributos en su valor por defecto

### `CalificacionKudo(UUID valorId, Kudo valorKudo, Usuario valorUsuario, int/Integer valorEstrellas, LocalDateTime valorFechaCalificacion)` — Parametrizado

Constructor parametrizado que crea un objeto CalificacionKudo asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único de la calificación dentro del sistema. |
| `valorKudo` | [Kudo](Kudo.md) | Por referencia | no final | Representa el Kudo que se califica. |
| `valorUsuario` | [Usuario](Usuario.md) | Por referencia | no final | Representa la persona que califica. |
| `valorEstrellas` | int/Integer | Por valor | no final | Representa la cantidad de estrellas que la persona asigna. |
| `valorFechaCalificacion` | LocalDateTime | Por referencia | no final | Representa la fecha y hora en que se registró o se modificó por última vez la calificación. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getKudo()` | `setKudo(Kudo valorKudo)` | [Kudo](Kudo.md) |
| `getUsuario()` | `setUsuario(Usuario valorUsuario)` | [Usuario](Usuario.md) |
| `getEstrellas()` | `setEstrellas(int/Integer valorEstrellas)` | int/Integer |
| `getFechaCalificacion()` | `setFechaCalificacion(LocalDateTime valorFechaCalificacion)` | LocalDateTime |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`calificarKudo`](#calificarkudo) | [CalificacionKudo](CalificacionKudo.md) | Valorar el Kudo con 1 a 5 estrellas. Cada persona tiene una sola calificación por contenido. |
| 2 | [`cambiarCalificacionKudo`](#cambiarcalificacionkudo) | [CalificacionKudo](CalificacionKudo.md) | Cambiar las estrellas de una calificación propia (EVT-033). |
| 3 | [`consultarCalificacionesKudo`](#consultarcalificacioneskudo) | [List&lt;CalificacionKudo&gt;](CalificacionKudo.md) | Ver las valoraciones que ha recibido el Kudo (CU-009). |

### `calificarKudo`

```java
public CalificacionKudo calificarKudo(Kudo kudo, int/Integer estrellas)
```

Valorar el Kudo con 1 a 5 estrellas. Cada persona tiene una sola calificación por contenido.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `kudo` | [Kudo](Kudo.md) | Por referencia | Representa el Kudo que se califica. |
| `estrellas` | int/Integer | Por valor | Representa la cantidad de estrellas que la persona asigna. |

<details>
<summary><b>Excepciones (9)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-CAK-0002**](../modelo-dominio/CalificacionKudo.md#p-cak-0002) — La calificación debe ser de 1 a 5 estrellas.
- [**P-KUD-0001**](../modelo-dominio/Kudo.md#p-kud-0001) — No existe el Kudo que se indicó.
- [**P-KUD-0002**](../modelo-dominio/Kudo.md#p-kud-0002) — El Kudo fue vetado y ya no admite interacción.
- [**P-CAK-0008**](../modelo-dominio/CalificacionKudo.md#p-cak-0008) — No puede calificar su propio contenido.
- [**P-CAK-0003**](../modelo-dominio/CalificacionKudo.md#p-cak-0003) — Usted ya calificó este contenido; puede cambiar su calificación.
- [**P-CAK-0004**](../modelo-dominio/CalificacionKudo.md#p-cak-0004) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-CAK-0005**](../modelo-dominio/CalificacionKudo.md#p-cak-0005) — No se pudo guardar la calificación; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `cambiarCalificacionKudo`

```java
public CalificacionKudo cambiarCalificacionKudo(UUID id, int/Integer estrellas)
```

Cambiar las estrellas de una calificación propia (EVT-033).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la calificación. |
| `estrellas` | int/Integer | Por valor | Representa la cantidad de estrellas que la persona asigna. |

<details>
<summary><b>Excepciones (8)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-CAK-0001**](../modelo-dominio/CalificacionKudo.md#p-cak-0001) — No existe la calificación que se indicó.
- [**P-CAK-0006**](../modelo-dominio/CalificacionKudo.md#p-cak-0006) — Solo puede cambiar sus propias calificaciones.
- [**P-KUD-0002**](../modelo-dominio/Kudo.md#p-kud-0002) — El Kudo fue vetado y ya no admite interacción.
- [**P-CAK-0002**](../modelo-dominio/CalificacionKudo.md#p-cak-0002) — La calificación debe ser de 1 a 5 estrellas.
- [**P-CAK-0009**](../modelo-dominio/CalificacionKudo.md#p-cak-0009) — Esa ya es su calificación actual.
- [**P-CAK-0007**](../modelo-dominio/CalificacionKudo.md#p-cak-0007) — No se pudo guardar el cambio; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `consultarCalificacionesKudo`

```java
public List<CalificacionKudo> consultarCalificacionesKudo(Kudo kudo, Usuario usuario)
```

Ver las valoraciones que ha recibido el Kudo (CU-009).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `kudo` | [Kudo](Kudo.md) | Por referencia | Representa el Kudo que se califica. |
| `usuario` | [Usuario](Usuario.md) | Por referencia | Representa la persona que califica. |

<details>
<summary><b>Excepciones (2)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-KUD-0001**](../modelo-dominio/Kudo.md#p-kud-0001) — No existe el Kudo que se indicó.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ Replica](Replica.md) · [RevisionKudo ➡️](RevisionKudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/CalificacionKudo.md) · [🗄️ MER](../mer/CalificacionKudo.md)
