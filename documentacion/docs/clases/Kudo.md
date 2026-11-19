# 📐 Kudo

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ VinculacionAcademica](VinculacionAcademica.md) · [Replica ➡️](Replica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Kudo.md) · [🗄️ MER](../mer/Kudo.md)

> Reconocimiento formal que una persona otorga a otra de la comunidad universitaria, clasificado en una categoría y visible para la comunidad autenticada.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Reconocimientos y conversación |

## Vista rápida

```java
public class Kudo {

    private UUID id = new UUID(0L, 0L);
    private Usuario autor = new Usuario();
    private Usuario destinatario = new Usuario();
    private Categoria categoria = new Categoria();
    private String nombreCategoria = "";
    private String contenido = "";
    private EstadoContenido estadoContenido = new EstadoContenido();
    private LocalDateTime fechaCreacion = LocalDateTime.of(1000, 1, 1, 0, 0);

    public Kudo()
    public Kudo(UUID valorId, Usuario valorAutor, Usuario valorDestinatario, Categoria valorCategoria, String valorNombreCategoria, String valorContenido, EstadoContenido valorEstadoContenido, LocalDateTime valorFechaCreacion)

    public Kudo crearKudo(Usuario destinatario, Categoria categoria, String contenido)
    public Kudo consultarKudo(UUID id)
    public List<Kudo> consultarKudos(Usuario autor, Usuario destinatario, Categoria categoria, EstadoContenido estadoContenido)
    public void marcarKudoReportado(UUID id)
    public void ponerKudoEnRevision(UUID id)
    public void vetarKudo(UUID id)
    public void liberarKudo(UUID id)

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. Kudo | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Necesita de | Asociación | [Usuario](Usuario.md) | 0..\* | 1 | `Kudo.autor` |
| Necesita de | Asociación | [Usuario](Usuario.md) | 0..\* | 1 | `Kudo.destinatario` |
| Necesita de | Asociación | [Categoria](Categoria.md) | 0..\* | 1 | `Kudo.categoria` |
| Necesita de | Asociación | [EstadoContenido](EstadoContenido.md) | 0..\* | 1 | `Kudo.estadoContenido` |
| Es usada por | Composición | [Replica](Replica.md) | 1 | 0..\* | `Replica.kudo` |
| Es usada por | Composición | [CalificacionKudo](CalificacionKudo.md) | 1 | 0..\* | `CalificacionKudo.kudo` |
| Es usada por | Composición | [RevisionKudo](RevisionKudo.md) | 1 | 0..\* | `RevisionKudo.kudo` |
| Es usada por | Composición | [ReporteKudo](ReporteKudo.md) | 1 | 0..\* | `ReporteKudo.kudo` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único del Kudo dentro del sistema. |
| `private` | `autor` | [Usuario](Usuario.md) | no final | no static | `new Usuario()` | Representa la persona que otorga el reconocimiento. |
| `private` | `destinatario` | [Usuario](Usuario.md) | no final | no static | `new Usuario()` | Representa la persona que recibe el reconocimiento. |
| `private` | `categoria` | [Categoria](Categoria.md) | no final | no static | `new Categoria()` | Representa la categoría institucional en la que se clasifica el reconocimiento. |
| `private` | `nombreCategoria` | String | no final | no static | `""` | Representa el nombre que tenía la categoría en el momento de crear el Kudo; no cambia aunque la categoría se renombre. |
| `private` | `contenido` | String | no final | no static | `""` | Representa el texto completo del reconocimiento. |
| `private` | `estadoContenido` | [EstadoContenido](EstadoContenido.md) | no final | no static | `new EstadoContenido()` | Representa en qué estado del ciclo de vida se encuentra el Kudo. |
| `private` | `fechaCreacion` | LocalDateTime | no final | no static | `LocalDateTime.of(1000, 1, 1, 0, 0)` | Representa la fecha y hora en que se creó el Kudo. |

## Constructores

### `Kudo()` — Defecto

Constructor por defecto que crea un objeto Kudo con todos sus atributos en su valor por defecto

### `Kudo(UUID valorId, Usuario valorAutor, Usuario valorDestinatario, Categoria valorCategoria, String valorNombreCategoria, String valorContenido, EstadoContenido valorEstadoContenido, LocalDateTime valorFechaCreacion)` — Parametrizado

Constructor parametrizado que crea un objeto Kudo asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único del Kudo dentro del sistema. |
| `valorAutor` | [Usuario](Usuario.md) | Por referencia | no final | Representa la persona que otorga el reconocimiento. |
| `valorDestinatario` | [Usuario](Usuario.md) | Por referencia | no final | Representa la persona que recibe el reconocimiento. |
| `valorCategoria` | [Categoria](Categoria.md) | Por referencia | no final | Representa la categoría institucional en la que se clasifica el reconocimiento. |
| `valorNombreCategoria` | String | Por valor | no final | Representa el nombre que tenía la categoría en el momento de crear el Kudo; no cambia aunque la categoría se renombre. |
| `valorContenido` | String | Por valor | no final | Representa el texto completo del reconocimiento. |
| `valorEstadoContenido` | [EstadoContenido](EstadoContenido.md) | Por referencia | no final | Representa en qué estado del ciclo de vida se encuentra el Kudo. |
| `valorFechaCreacion` | LocalDateTime | Por referencia | no final | Representa la fecha y hora en que se creó el Kudo. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getAutor()` | `setAutor(Usuario valorAutor)` | [Usuario](Usuario.md) |
| `getDestinatario()` | `setDestinatario(Usuario valorDestinatario)` | [Usuario](Usuario.md) |
| `getCategoria()` | `setCategoria(Categoria valorCategoria)` | [Categoria](Categoria.md) |
| `getNombreCategoria()` | `setNombreCategoria(String valorNombreCategoria)` | String |
| `getContenido()` | `setContenido(String valorContenido)` | String |
| `getEstadoContenido()` | `setEstadoContenido(EstadoContenido valorEstadoContenido)` | [EstadoContenido](EstadoContenido.md) |
| `getFechaCreacion()` | `setFechaCreacion(LocalDateTime valorFechaCreacion)` | LocalDateTime |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`crearKudo`](#crearkudo) | [Kudo](Kudo.md) | Otorgar un reconocimiento a otra persona de la comunidad, en una categoría activa. Queda visible para la comunidad autenticada (CU-001). |
| 2 | [`consultarKudo`](#consultarkudo) | [Kudo](Kudo.md) | Ver un Kudo completo: autor, destinatario, fecha, categoría con la que se creó y contenido (CU-002). |
| 3 | [`consultarKudos`](#consultarkudos) | [List&lt;Kudo&gt;](Kudo.md) | Consultar el historial de Kudos: los que otorgó o recibió una persona, o los de una categoría (CU-003, RF-048 a RF-050). |
| 4 | [`marcarKudoReportado`](#marcarkudoreportado) | void | Pasar el Kudo de Visible a Reportado. Lo hace el sistema cuando el Kudo recibe su primer reporte pendiente. |
| 5 | [`ponerKudoEnRevision`](#ponerkudoenrevision) | void | Pasar el Kudo de Reportado a En revisión. Lo hace el sistema cuando el Rector o el Coordinador Académico inicia la revisión. |
| 6 | [`vetarKudo`](#vetarkudo) | void | Pasar el Kudo a Vetado: deja de estar disponible para la interacción normal. Lo hace el sistema cuando una revisión se cierra con veto o cuando la autoridad veta directamente. No necesita atributos propios del veto: quién, cuándo y por qué quedan en la revisión. |
| 7 | [`liberarKudo`](#liberarkudo) | void | Devolver el Kudo a Visible. Lo hace el sistema cuando una revisión se cierra sin veto. |

### `crearKudo`

```java
public Kudo crearKudo(Usuario destinatario, Categoria categoria, String contenido)
```

Otorgar un reconocimiento a otra persona de la comunidad, en una categoría activa. Queda visible para la comunidad autenticada (CU-001).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `destinatario` | [Usuario](Usuario.md) | Por referencia | Representa la persona que recibe el reconocimiento. |
| `categoria` | [Categoria](Categoria.md) | Por referencia | Representa la categoría institucional en la que se clasifica el reconocimiento. |
| `contenido` | String | Por valor | Representa el texto completo del reconocimiento. |

<details>
<summary><b>Excepciones (12)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-KUD-0014**](../modelo-dominio/Kudo.md#p-kud-0014) — Se informa cuál dato no cumple y cómo debe escribirse.
- [**P-USU-0009**](../modelo-dominio/Usuario.md#p-usu-0009) — La persona indicada no existe o su cuenta no está activa.
- [**P-KUD-0015**](../modelo-dominio/Kudo.md#p-kud-0015) — No puede otorgarse un Kudo a usted mismo.
- [**P-CAT-0001**](../modelo-dominio/Categoria.md#p-cat-0001) — No existe la categoría que se indicó.
- [**P-CAT-0008**](../modelo-dominio/Categoria.md#p-cat-0008) — La categoría está inactiva y no puede usarse.
- [**P-KUD-0019**](../modelo-dominio/Kudo.md#p-kud-0019) — Ya se registró ese Kudo.
- [**P-KUD-0013**](../modelo-dominio/Kudo.md#p-kud-0013) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-KUD-0017**](../modelo-dominio/Kudo.md#p-kud-0017) — No se pudo crear el Kudo; se deshace toda la operación.
- [**P-KUD-0016**](../modelo-dominio/Kudo.md#p-kud-0016) — No se pudo crear el Kudo; se deshace toda la operación.
- [**P-KUD-0018**](../modelo-dominio/Kudo.md#p-kud-0018) — No se pudo publicar el Kudo; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `consultarKudo`

```java
public Kudo consultarKudo(UUID id)
```

Ver un Kudo completo: autor, destinatario, fecha, categoría con la que se creó y contenido (CU-002).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador del Kudo. |

<details>
<summary><b>Excepciones (2)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-KUD-0001**](../modelo-dominio/Kudo.md#p-kud-0001) — No existe el Kudo que se indicó.

</details>

### `consultarKudos`

```java
public List<Kudo> consultarKudos(Usuario autor, Usuario destinatario, Categoria categoria, EstadoContenido estadoContenido)
```

Consultar el historial de Kudos: los que otorgó o recibió una persona, o los de una categoría (CU-003, RF-048 a RF-050).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `autor` | [Usuario](Usuario.md) | Por referencia | Representa la persona que otorga el reconocimiento. |
| `destinatario` | [Usuario](Usuario.md) | Por referencia | Representa la persona que recibe el reconocimiento. |
| `categoria` | [Categoria](Categoria.md) | Por referencia | Representa la categoría institucional en la que se clasifica el reconocimiento. |
| `estadoContenido` | [EstadoContenido](EstadoContenido.md) | Por referencia | Representa en qué estado del ciclo de vida se encuentra el Kudo. |

<details>
<summary><b>Excepciones (1)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.

</details>

### `marcarKudoReportado`

```java
public void marcarKudoReportado(UUID id)
```

Pasar el Kudo de Visible a Reportado. Lo hace el sistema cuando el Kudo recibe su primer reporte pendiente.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador del Kudo. |

<details>
<summary><b>Excepciones (1)</b></summary>

- [**P-KUD-0001**](../modelo-dominio/Kudo.md#p-kud-0001) — No existe el Kudo que se indicó.

</details>

### `ponerKudoEnRevision`

```java
public void ponerKudoEnRevision(UUID id)
```

Pasar el Kudo de Reportado a En revisión. Lo hace el sistema cuando el Rector o el Coordinador Académico inicia la revisión.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador del Kudo. |

<details>
<summary><b>Excepciones (2)</b></summary>

- [**P-KUD-0001**](../modelo-dominio/Kudo.md#p-kud-0001) — No existe el Kudo que se indicó.
- [**P-KUD-0004**](../modelo-dominio/Kudo.md#p-kud-0004) — El Kudo no tiene reportes pendientes por revisar.

</details>

### `vetarKudo`

```java
public void vetarKudo(UUID id)
```

Pasar el Kudo a Vetado: deja de estar disponible para la interacción normal. Lo hace el sistema cuando una revisión se cierra con veto o cuando la autoridad veta directamente. No necesita atributos propios del veto: quién, cuándo y por qué quedan en la revisión.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador del Kudo. |

<details>
<summary><b>Excepciones (2)</b></summary>

- [**P-KUD-0001**](../modelo-dominio/Kudo.md#p-kud-0001) — No existe el Kudo que se indicó.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `liberarKudo`

```java
public void liberarKudo(UUID id)
```

Devolver el Kudo a Visible. Lo hace el sistema cuando una revisión se cierra sin veto.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador del Kudo. |

<details>
<summary><b>Excepciones (3)</b></summary>

- [**P-KUD-0001**](../modelo-dominio/Kudo.md#p-kud-0001) — No existe el Kudo que se indicó.
- [**P-KUD-0005**](../modelo-dominio/Kudo.md#p-kud-0005) — El Kudo no está en revisión.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ VinculacionAcademica](VinculacionAcademica.md) · [Replica ➡️](Replica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Kudo.md) · [🗄️ MER](../mer/Kudo.md)
