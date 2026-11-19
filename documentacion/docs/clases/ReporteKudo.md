# 📐 ReporteKudo

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ RevisionReplica](RevisionReplica.md) · [ReporteReplica ➡️](ReporteReplica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/ReporteKudo.md) · [🗄️ MER](../mer/ReporteKudo.md)

> Solicitud de revisión que hace cualquier usuario autenticado sobre un Kudo, con un motivo. Se resuelve cuando la revisión del contenido se cierra. Su estado (Pendiente, En revisión, Aceptado o Rechazado) no se guarda: se deduce de la revisión que lo resuelve.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Moderación |

## Vista rápida

```java
public class ReporteKudo {

    private UUID id = new UUID(0L, 0L);
    private Kudo kudo = new Kudo();
    private Usuario usuario = new Usuario();
    private Motivo motivo = new Motivo();
    private String descripcion = "";
    private RevisionKudo revisionKudo = new RevisionKudo();
    private LocalDateTime fechaReporte = LocalDateTime.of(1000, 1, 1, 0, 0);

    public ReporteKudo()
    public ReporteKudo(UUID valorId, Kudo valorKudo, Usuario valorUsuario, Motivo valorMotivo, String valorDescripcion, RevisionKudo valorRevisionKudo, LocalDateTime valorFechaReporte)

    public ReporteKudo reportarKudo(Kudo kudo, Motivo motivo, String descripcion)
    public List<ReporteKudo> consultarReportesKudo(Kudo kudo, Motivo motivo, String estado)
    public void enlazarReportesKudoConRevision(Kudo kudo, RevisionKudo revisionKudo)

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. ReporteKudo | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Necesita de | Composición | [Kudo](Kudo.md) | 0..\* | 1 | `ReporteKudo.kudo` |
| Necesita de | Asociación | [Usuario](Usuario.md) | 0..\* | 1 | `ReporteKudo.usuario` |
| Necesita de | Asociación | [Motivo](Motivo.md) | 0..\* | 1 | `ReporteKudo.motivo` |
| Necesita de | Asociación | [RevisionKudo](RevisionKudo.md) | 0..\* | 0..1 | `ReporteKudo.revisionKudo` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único del reporte dentro del sistema. |
| `private` | `kudo` | [Kudo](Kudo.md) | no final | no static | `new Kudo()` | Representa un Kudo que se reporta. |
| `private` | `usuario` | [Usuario](Usuario.md) | no final | no static | `new Usuario()` | Representa la persona que reporta el contenido. |
| `private` | `motivo` | [Motivo](Motivo.md) | no final | no static | `new Motivo()` | Representa la razón por la que se reporta el contenido. |
| `private` | `descripcion` | String | no final | no static | `""` | Representa la explicación adicional del reporte; obligatoria cuando el motivo es Otro. |
| `private` | `revisionKudo` | [RevisionKudo](RevisionKudo.md) | no final | no static | `new RevisionKudo()` | Representa la revisión que resuelve el reporte; vacía mientras el reporte está pendiente. De ella se deduce el estado del reporte: sin revisión = Pendiente, revisión En curso = En revisión, cerrada con veto = Aceptado, cerrada con liberación = Rechazado. |
| `private` | `fechaReporte` | LocalDateTime | no final | no static | `LocalDateTime.of(1000, 1, 1, 0, 0)` | Representa la fecha y hora en que se hizo el reporte. |

## Constructores

### `ReporteKudo()` — Defecto

Constructor por defecto que crea un objeto ReporteKudo con todos sus atributos en su valor por defecto

### `ReporteKudo(UUID valorId, Kudo valorKudo, Usuario valorUsuario, Motivo valorMotivo, String valorDescripcion, RevisionKudo valorRevisionKudo, LocalDateTime valorFechaReporte)` — Parametrizado

Constructor parametrizado que crea un objeto ReporteKudo asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único del reporte dentro del sistema. |
| `valorKudo` | [Kudo](Kudo.md) | Por referencia | no final | Representa un Kudo que se reporta. |
| `valorUsuario` | [Usuario](Usuario.md) | Por referencia | no final | Representa la persona que reporta el contenido. |
| `valorMotivo` | [Motivo](Motivo.md) | Por referencia | no final | Representa la razón por la que se reporta el contenido. |
| `valorDescripcion` | String | Por valor | no final | Representa la explicación adicional del reporte; obligatoria cuando el motivo es Otro. |
| `valorRevisionKudo` | [RevisionKudo](RevisionKudo.md) | Por referencia | no final | Representa la revisión que resuelve el reporte; vacía mientras el reporte está pendiente. De ella se deduce el estado del reporte: sin revisión = Pendiente, revisión En curso = En revisión, cerrada con veto = Aceptado, cerrada con liberación = Rechazado. |
| `valorFechaReporte` | LocalDateTime | Por referencia | no final | Representa la fecha y hora en que se hizo el reporte. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getKudo()` | `setKudo(Kudo valorKudo)` | [Kudo](Kudo.md) |
| `getUsuario()` | `setUsuario(Usuario valorUsuario)` | [Usuario](Usuario.md) |
| `getMotivo()` | `setMotivo(Motivo valorMotivo)` | [Motivo](Motivo.md) |
| `getDescripcion()` | `setDescripcion(String valorDescripcion)` | String |
| `getRevisionKudo()` | `setRevisionKudo(RevisionKudo valorRevisionKudo)` | [RevisionKudo](RevisionKudo.md) |
| `getFechaReporte()` | `setFechaReporte(LocalDateTime valorFechaReporte)` | LocalDateTime |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`reportarKudo`](#reportarkudo) | [ReporteKudo](ReporteKudo.md) | Pedir que se revise un Kudo porque se considera inapropiado, indicando un motivo (CU-010, CU-011). |
| 2 | [`consultarReportesKudo`](#consultarreporteskudo) | [List&lt;ReporteKudo&gt;](ReporteKudo.md) | Consultar los reportes hechos sobre los Kudos, para decidir si se revisan (CU-012, RF-064). |
| 3 | [`enlazarReportesKudoConRevision`](#enlazarreporteskudoconrevision) | void | Enlazar con la revisión que se abre los reportes del Kudo que aún no tienen revisión. Desde ese momento se ven En revisión, y al cerrarse la revisión se ven Aceptados o Rechazados sin cambiar nada más. Lo hace el sistema al iniciar la revisión o al vetar directamente. |

### `reportarKudo`

```java
public ReporteKudo reportarKudo(Kudo kudo, Motivo motivo, String descripcion)
```

Pedir que se revise un Kudo porque se considera inapropiado, indicando un motivo (CU-010, CU-011).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `kudo` | [Kudo](Kudo.md) | Por referencia | Representa un Kudo que se reporta. |
| `motivo` | [Motivo](Motivo.md) | Por referencia | Representa la razón por la que se reporta el contenido. |
| `descripcion` | String | Por valor | Representa la explicación adicional del reporte; obligatoria cuando el motivo es Otro. |

<details>
<summary><b>Excepciones (10)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-RPK-0001**](../modelo-dominio/ReporteKudo.md#p-rpk-0001) — Se informa cuál dato no cumple; si el motivo es Otro debe describirlo.
- [**P-KUD-0001**](../modelo-dominio/Kudo.md#p-kud-0001) — No existe el Kudo que se indicó.
- [**P-KUD-0003**](../modelo-dominio/Kudo.md#p-kud-0003) — El Kudo no puede reportarse en su estado actual.
- [**P-MOT-0001**](../modelo-dominio/Motivo.md#p-mot-0001) — No existe el motivo que se indicó.
- [**P-RPK-0002**](../modelo-dominio/ReporteKudo.md#p-rpk-0002) — Usted ya reportó este contenido.
- [**P-RPK-0003**](../modelo-dominio/ReporteKudo.md#p-rpk-0003) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-RPK-0004**](../modelo-dominio/ReporteKudo.md#p-rpk-0004) — No se pudo guardar el reporte; se deshace toda la operación.
- [**P-KUD-0008**](../modelo-dominio/Kudo.md#p-kud-0008) — No se pudo actualizar el estado; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `consultarReportesKudo`

```java
public List<ReporteKudo> consultarReportesKudo(Kudo kudo, Motivo motivo, String estado)
```

Consultar los reportes hechos sobre los Kudos, para decidir si se revisan (CU-012, RF-064).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `kudo` | [Kudo](Kudo.md) | Por referencia | Representa un Kudo que se reporta. |
| `motivo` | [Motivo](Motivo.md) | Por referencia | Representa la razón por la que se reporta el contenido. |
| `estado` | String | Por valor | Opcional: Pendiente, En revisión, Aceptado o Rechazado; se compara con el estado deducido de la revisión. |

<details>
<summary><b>Excepciones (2)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-RPK-0008**](../modelo-dominio/ReporteKudo.md#p-rpk-0008) — No tiene permiso para consultar los reportes.

</details>

### `enlazarReportesKudoConRevision`

```java
public void enlazarReportesKudoConRevision(Kudo kudo, RevisionKudo revisionKudo)
```

Enlazar con la revisión que se abre los reportes del Kudo que aún no tienen revisión. Desde ese momento se ven En revisión, y al cerrarse la revisión se ven Aceptados o Rechazados sin cambiar nada más. Lo hace el sistema al iniciar la revisión o al vetar directamente.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `kudo` | [Kudo](Kudo.md) | Por referencia | Representa un Kudo que se reporta. |
| `revisionKudo` | [RevisionKudo](RevisionKudo.md) | Por referencia | Representa la revisión que resuelve el reporte; vacía mientras el reporte está pendiente. De ella se deduce el estado del reporte: sin revisión = Pendiente, revisión En curso = En revisión, cerrada con veto = Aceptado, cerrada con liberación = Rechazado. |


---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ RevisionReplica](RevisionReplica.md) · [ReporteReplica ➡️](ReporteReplica.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/ReporteKudo.md) · [🗄️ MER](../mer/ReporteKudo.md)
