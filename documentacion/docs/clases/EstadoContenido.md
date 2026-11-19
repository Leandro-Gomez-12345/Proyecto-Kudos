# 📐 EstadoContenido

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ EstadoCuenta](EstadoCuenta.md) · [EstadoRevision ➡️](EstadoRevision.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/EstadoContenido.md) · [🗄️ MER](../mer/EstadoContenido.md)

> Catálogo de estados del ciclo de vida de un Kudo o de una Réplica.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Catálogos |

## Vista rápida

```java
public class EstadoContenido {

    private UUID id = new UUID(0L, 0L);
    private String nombre = "";

    public EstadoContenido()
    public EstadoContenido(UUID valorId, String valorNombre)

    public List<EstadoContenido> consultarEstadosContenido()

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. EstadoContenido | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Es usada por | Asociación | [Kudo](Kudo.md) | 1 | 0..\* | `Kudo.estadoContenido` |
| Es usada por | Asociación | [Replica](Replica.md) | 1 | 0..\* | `Replica.estadoContenido` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único del Estado Contenido dentro del sistema. |
| `private` | `nombre` | String | no final | no static | `""` | Representa el nombre del estado contenido tal como lo entiende la comunidad universitaria. |

## Constructores

### `EstadoContenido()` — Defecto

Constructor por defecto que crea un objeto EstadoContenido con todos sus atributos en su valor por defecto

### `EstadoContenido(UUID valorId, String valorNombre)` — Parametrizado

Constructor parametrizado que crea un objeto EstadoContenido asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único del Estado Contenido dentro del sistema. |
| `valorNombre` | String | Por valor | no final | Representa el nombre del estado contenido tal como lo entiende la comunidad universitaria. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getNombre()` | `setNombre(String valorNombre)` | String |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`consultarEstadosContenido`](#consultarestadoscontenido) | [List&lt;EstadoContenido&gt;](EstadoContenido.md) | Consultar la lista de estados del contenido que maneja la plataforma. Para filtrar Kudos y réplicas según su estado al moderar. |

### `consultarEstadosContenido`

```java
public List<EstadoContenido> consultarEstadosContenido()
```

Consultar la lista de estados del contenido que maneja la plataforma. Para filtrar Kudos y réplicas según su estado al moderar.

<details>
<summary><b>Excepciones (1)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ EstadoCuenta](EstadoCuenta.md) · [EstadoRevision ➡️](EstadoRevision.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/EstadoContenido.md) · [🗄️ MER](../mer/EstadoContenido.md)
