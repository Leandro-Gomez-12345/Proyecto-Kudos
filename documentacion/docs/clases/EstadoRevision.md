# 📐 EstadoRevision

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ EstadoContenido](EstadoContenido.md) · [Motivo ➡️](Motivo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/EstadoRevision.md) · [🗄️ MER](../mer/EstadoRevision.md)

> Catálogo de estados de una revisión de moderación: en curso o cerrada con el contenido vetado o liberado.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Catálogos |

## Vista rápida

```java
public class EstadoRevision {

    private UUID id = new UUID(0L, 0L);
    private String nombre = "";

    public EstadoRevision()
    public EstadoRevision(UUID valorId, String valorNombre)

    public List<EstadoRevision> consultarEstadosRevision()

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. EstadoRevision | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Es usada por | Asociación | [RevisionKudo](RevisionKudo.md) | 1 | 0..\* | `RevisionKudo.estadoRevision` |
| Es usada por | Asociación | [RevisionReplica](RevisionReplica.md) | 1 | 0..\* | `RevisionReplica.estadoRevision` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único del Estado Revisión dentro del sistema. |
| `private` | `nombre` | String | no final | no static | `""` | Representa el nombre del estado revisión tal como lo entiende la comunidad universitaria. |

## Constructores

### `EstadoRevision()` — Defecto

Constructor por defecto que crea un objeto EstadoRevision con todos sus atributos en su valor por defecto

### `EstadoRevision(UUID valorId, String valorNombre)` — Parametrizado

Constructor parametrizado que crea un objeto EstadoRevision asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único del Estado Revisión dentro del sistema. |
| `valorNombre` | String | Por valor | no final | Representa el nombre del estado revisión tal como lo entiende la comunidad universitaria. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getNombre()` | `setNombre(String valorNombre)` | String |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`consultarEstadosRevision`](#consultarestadosrevision) | [List&lt;EstadoRevision&gt;](EstadoRevision.md) | Consultar la lista de estados de revisión que maneja la plataforma. Para filtrar las revisiones en curso y el histórico de vetos (CU-015). |

### `consultarEstadosRevision`

```java
public List<EstadoRevision> consultarEstadosRevision()
```

Consultar la lista de estados de revisión que maneja la plataforma. Para filtrar las revisiones en curso y el histórico de vetos (CU-015).

<details>
<summary><b>Excepciones (1)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ EstadoContenido](EstadoContenido.md) · [Motivo ➡️](Motivo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/EstadoRevision.md) · [🗄️ MER](../mer/EstadoRevision.md)
