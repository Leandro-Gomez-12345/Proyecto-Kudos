# 📐 Motivo

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ EstadoRevision](EstadoRevision.md) · [Categoria ➡️](Categoria.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Motivo.md) · [🗄️ MER](../mer/Motivo.md)

> Catálogo de motivos por los que un usuario reporta un Kudo o una Réplica.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Catálogos |

## Vista rápida

```java
public class Motivo {

    private UUID id = new UUID(0L, 0L);
    private String nombre = "";

    public Motivo()
    public Motivo(UUID valorId, String valorNombre)

    public List<Motivo> consultarMotivos()

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. Motivo | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Es usada por | Asociación | [ReporteKudo](ReporteKudo.md) | 1 | 0..\* | `ReporteKudo.motivo` |
| Es usada por | Asociación | [ReporteReplica](ReporteReplica.md) | 1 | 0..\* | `ReporteReplica.motivo` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único del Motivo dentro del sistema. |
| `private` | `nombre` | String | no final | no static | `""` | Representa el nombre del motivo tal como lo entiende la comunidad universitaria. |

## Constructores

### `Motivo()` — Defecto

Constructor por defecto que crea un objeto Motivo con todos sus atributos en su valor por defecto

### `Motivo(UUID valorId, String valorNombre)` — Parametrizado

Constructor parametrizado que crea un objeto Motivo asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único del Motivo dentro del sistema. |
| `valorNombre` | String | Por valor | no final | Representa el nombre del motivo tal como lo entiende la comunidad universitaria. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getNombre()` | `setNombre(String valorNombre)` | String |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`consultarMotivos`](#consultarmotivos) | [List&lt;Motivo&gt;](Motivo.md) | Consultar la lista de motivos de reporte que maneja la plataforma. Para elegir el motivo al reportar un Kudo o una réplica (CU-010). |

### `consultarMotivos`

```java
public List<Motivo> consultarMotivos()
```

Consultar la lista de motivos de reporte que maneja la plataforma. Para elegir el motivo al reportar un Kudo o una réplica (CU-010).

<details>
<summary><b>Excepciones (1)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ EstadoRevision](EstadoRevision.md) · [Categoria ➡️](Categoria.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Motivo.md) · [🗄️ MER](../mer/Motivo.md)
