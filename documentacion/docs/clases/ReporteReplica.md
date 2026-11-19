# 📐 ReporteReplica

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ ReporteKudo](ReporteKudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/ReporteReplica.md) · [🗄️ MER](../mer/ReporteReplica.md)

> Solicitud de revisión que hace cualquier usuario autenticado sobre una réplica, con un motivo. Se resuelve cuando la revisión del contenido se cierra. Su estado (Pendiente, En revisión, Aceptado o Rechazado) no se guarda: se deduce de la revisión que lo resuelve.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Moderación |

## Vista rápida

```java
public class ReporteReplica {

    private UUID id = new UUID(0L, 0L);
    private Replica replica = new Replica();
    private Usuario usuario = new Usuario();
    private Motivo motivo = new Motivo();
    private String descripcion = "";
    private RevisionReplica revisionReplica = new RevisionReplica();
    private LocalDateTime fechaReporte = LocalDateTime.of(1000, 1, 1, 0, 0);

    public ReporteReplica()
    public ReporteReplica(UUID valorId, Replica valorReplica, Usuario valorUsuario, Motivo valorMotivo, String valorDescripcion, RevisionReplica valorRevisionReplica, LocalDateTime valorFechaReporte)

    public ReporteReplica reportarReplica(Replica replica, Motivo motivo, String descripcion)
    public List<ReporteReplica> consultarReportesReplica(Replica replica, Motivo motivo, String estado)
    public void enlazarReportesReplicaConRevision(Replica replica, RevisionReplica revisionReplica)

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. ReporteReplica | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Necesita de | Composición | [Replica](Replica.md) | 0..\* | 1 | `ReporteReplica.replica` |
| Necesita de | Asociación | [Usuario](Usuario.md) | 0..\* | 1 | `ReporteReplica.usuario` |
| Necesita de | Asociación | [Motivo](Motivo.md) | 0..\* | 1 | `ReporteReplica.motivo` |
| Necesita de | Asociación | [RevisionReplica](RevisionReplica.md) | 0..\* | 0..1 | `ReporteReplica.revisionReplica` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único del reporte dentro del sistema. |
| `private` | `replica` | [Replica](Replica.md) | no final | no static | `new Replica()` | Representa una réplica que se reporta. |
| `private` | `usuario` | [Usuario](Usuario.md) | no final | no static | `new Usuario()` | Representa la persona que reporta el contenido. |
| `private` | `motivo` | [Motivo](Motivo.md) | no final | no static | `new Motivo()` | Representa la razón por la que se reporta el contenido. |
| `private` | `descripcion` | String | no final | no static | `""` | Representa la explicación adicional del reporte; obligatoria cuando el motivo es Otro. |
| `private` | `revisionReplica` | [RevisionReplica](RevisionReplica.md) | no final | no static | `new RevisionReplica()` | Representa la revisión que resuelve el reporte; vacía mientras el reporte está pendiente. De ella se deduce el estado del reporte: sin revisión = Pendiente, revisión En curso = En revisión, cerrada con veto = Aceptado, cerrada con liberación = Rechazado. |
| `private` | `fechaReporte` | LocalDateTime | no final | no static | `LocalDateTime.of(1000, 1, 1, 0, 0)` | Representa la fecha y hora en que se hizo el reporte. |

## Constructores

### `ReporteReplica()` — Defecto

Constructor por defecto que crea un objeto ReporteReplica con todos sus atributos en su valor por defecto

### `ReporteReplica(UUID valorId, Replica valorReplica, Usuario valorUsuario, Motivo valorMotivo, String valorDescripcion, RevisionReplica valorRevisionReplica, LocalDateTime valorFechaReporte)` — Parametrizado

Constructor parametrizado que crea un objeto ReporteReplica asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único del reporte dentro del sistema. |
| `valorReplica` | [Replica](Replica.md) | Por referencia | no final | Representa una réplica que se reporta. |
| `valorUsuario` | [Usuario](Usuario.md) | Por referencia | no final | Representa la persona que reporta el contenido. |
| `valorMotivo` | [Motivo](Motivo.md) | Por referencia | no final | Representa la razón por la que se reporta el contenido. |
| `valorDescripcion` | String | Por valor | no final | Representa la explicación adicional del reporte; obligatoria cuando el motivo es Otro. |
| `valorRevisionReplica` | [RevisionReplica](RevisionReplica.md) | Por referencia | no final | Representa la revisión que resuelve el reporte; vacía mientras el reporte está pendiente. De ella se deduce el estado del reporte: sin revisión = Pendiente, revisión En curso = En revisión, cerrada con veto = Aceptado, cerrada con liberación = Rechazado. |
| `valorFechaReporte` | LocalDateTime | Por referencia | no final | Representa la fecha y hora en que se hizo el reporte. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getReplica()` | `setReplica(Replica valorReplica)` | [Replica](Replica.md) |
| `getUsuario()` | `setUsuario(Usuario valorUsuario)` | [Usuario](Usuario.md) |
| `getMotivo()` | `setMotivo(Motivo valorMotivo)` | [Motivo](Motivo.md) |
| `getDescripcion()` | `setDescripcion(String valorDescripcion)` | String |
| `getRevisionReplica()` | `setRevisionReplica(RevisionReplica valorRevisionReplica)` | [RevisionReplica](RevisionReplica.md) |
| `getFechaReporte()` | `setFechaReporte(LocalDateTime valorFechaReporte)` | LocalDateTime |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`reportarReplica`](#reportarreplica) | [ReporteReplica](ReporteReplica.md) | Pedir que se revise una réplica porque se considera inapropiada, indicando un motivo (CU-010, CU-011). |
| 2 | [`consultarReportesReplica`](#consultarreportesreplica) | [List&lt;ReporteReplica&gt;](ReporteReplica.md) | Consultar los reportes hechos sobre las réplicas, para decidir si se revisan (CU-012, RF-064). |
| 3 | [`enlazarReportesReplicaConRevision`](#enlazarreportesreplicaconrevision) | void | Enlazar con la revisión que se abre los reportes de la réplica que aún no tienen revisión. Desde ese momento se ven En revisión, y al cerrarse la revisión se ven Aceptados o Rechazados sin cambiar nada más. Lo hace el sistema al iniciar la revisión o al vetar directamente. |

### `reportarReplica`

```java
public ReporteReplica reportarReplica(Replica replica, Motivo motivo, String descripcion)
```

Pedir que se revise una réplica porque se considera inapropiada, indicando un motivo (CU-010, CU-011).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `replica` | [Replica](Replica.md) | Por referencia | Representa una réplica que se reporta. |
| `motivo` | [Motivo](Motivo.md) | Por referencia | Representa la razón por la que se reporta el contenido. |
| `descripcion` | String | Por valor | Representa la explicación adicional del reporte; obligatoria cuando el motivo es Otro. |

<details>
<summary><b>Excepciones (11)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-RPR-0001**](../modelo-dominio/ReporteReplica.md#p-rpr-0001) — Se informa cuál dato no cumple; si el motivo es Otro debe describirlo.
- [**P-REP-0001**](../modelo-dominio/Replica.md#p-rep-0001) — No existe la réplica que se indicó.
- [**P-REP-0003**](../modelo-dominio/Replica.md#p-rep-0003) — La réplica no puede reportarse en su estado actual.
- [**P-KUD-0002**](../modelo-dominio/Kudo.md#p-kud-0002) — El Kudo fue vetado y ya no admite interacción.
- [**P-MOT-0001**](../modelo-dominio/Motivo.md#p-mot-0001) — No existe el motivo que se indicó.
- [**P-RPR-0002**](../modelo-dominio/ReporteReplica.md#p-rpr-0002) — Usted ya reportó este contenido.
- [**P-RPR-0003**](../modelo-dominio/ReporteReplica.md#p-rpr-0003) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-RPR-0004**](../modelo-dominio/ReporteReplica.md#p-rpr-0004) — No se pudo guardar el reporte; se deshace toda la operación.
- [**P-REP-0008**](../modelo-dominio/Replica.md#p-rep-0008) — No se pudo actualizar el estado; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `consultarReportesReplica`

```java
public List<ReporteReplica> consultarReportesReplica(Replica replica, Motivo motivo, String estado)
```

Consultar los reportes hechos sobre las réplicas, para decidir si se revisan (CU-012, RF-064).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `replica` | [Replica](Replica.md) | Por referencia | Representa una réplica que se reporta. |
| `motivo` | [Motivo](Motivo.md) | Por referencia | Representa la razón por la que se reporta el contenido. |
| `estado` | String | Por valor | Opcional: Pendiente, En revisión, Aceptado o Rechazado; se compara con el estado deducido de la revisión. |

<details>
<summary><b>Excepciones (2)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-RPR-0008**](../modelo-dominio/ReporteReplica.md#p-rpr-0008) — No tiene permiso para consultar los reportes.

</details>

### `enlazarReportesReplicaConRevision`

```java
public void enlazarReportesReplicaConRevision(Replica replica, RevisionReplica revisionReplica)
```

Enlazar con la revisión que se abre los reportes de la réplica que aún no tienen revisión. Desde ese momento se ven En revisión, y al cerrarse la revisión se ven Aceptados o Rechazados sin cambiar nada más. Lo hace el sistema al iniciar la revisión o al vetar directamente.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `replica` | [Replica](Replica.md) | Por referencia | Representa una réplica que se reporta. |
| `revisionReplica` | [RevisionReplica](RevisionReplica.md) | Por referencia | Representa la revisión que resuelve el reporte; vacía mientras el reporte está pendiente. De ella se deduce el estado del reporte: sin revisión = Pendiente, revisión En curso = En revisión, cerrada con veto = Aceptado, cerrada con liberación = Rechazado. |


---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ ReporteKudo](ReporteKudo.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/ReporteReplica.md) · [🗄️ MER](../mer/ReporteReplica.md)
