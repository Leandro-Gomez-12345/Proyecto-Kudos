# 📐 Auditoria

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ ProgramaAcademico](ProgramaAcademico.md) · [CoordinacionFacultad ➡️](CoordinacionFacultad.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Auditoria.md) · [🗄️ MER](../mer/Auditoria.md)

> Registro histórico, de solo lectura, de cada acción relevante: quién la hizo, qué hizo, sobre qué registro y cuándo. Se puede consultar.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Usuarios y auditoría |

## Vista rápida

```java
public class Auditoria {

    private UUID id = new UUID(0L, 0L);
    private Usuario usuario = new Usuario();
    private String accion = "";
    private String objetoAfectado = "";
    private UUID registroAfectado = new UUID(0L, 0L);
    private String detalle = "";
    private LocalDateTime fechaHora = LocalDateTime.of(1000, 1, 1, 0, 0);

    public Auditoria()
    public Auditoria(UUID valorId, Usuario valorUsuario, String valorAccion, String valorObjetoAfectado, UUID valorRegistroAfectado, String valorDetalle, LocalDateTime valorFechaHora)

    public void registrarAuditoria(Usuario usuario, String accion, String objetoAfectado, UUID registroAfectado, String detalle)
    public List<Auditoria> consultarAuditorias(Usuario usuario, String accion, String objetoAfectado, UUID registroAfectado, LocalDateTime fechaHora)
    public List<Auditoria> consultarAuditoriasPropias(String accion, String objetoAfectado, LocalDateTime fechaHora)

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. Auditoria | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Necesita de | Asociación | [Usuario](Usuario.md) | 0..\* | 1 | `Auditoria.usuario` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único del registro de auditoría dentro del sistema. |
| `private` | `usuario` | [Usuario](Usuario.md) | no final | no static | `new Usuario()` | Representa la persona que ejecutó la acción auditada. |
| `private` | `accion` | String | no final | no static | `""` | Representa la acción del negocio que se ejecutó, en lenguaje de negocio. |
| `private` | `objetoAfectado` | String | no final | no static | `""` | Representa el tipo de objeto de dominio sobre el que se ejecutó la acción. |
| `private` | `registroAfectado` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador del registro concreto que fue afectado por la acción. |
| `private` | `detalle` | String | no final | no static | `""` | Representa la descripción de lo que cambió, para poder reconstruir la decisión. |
| `private` | `fechaHora` | LocalDateTime | no final | no static | `LocalDateTime.of(1000, 1, 1, 0, 0)` | Representa la fecha y hora exactas en que se ejecutó la acción. |

## Constructores

### `Auditoria()` — Defecto

Constructor por defecto que crea un objeto Auditoria con todos sus atributos en su valor por defecto

### `Auditoria(UUID valorId, Usuario valorUsuario, String valorAccion, String valorObjetoAfectado, UUID valorRegistroAfectado, String valorDetalle, LocalDateTime valorFechaHora)` — Parametrizado

Constructor parametrizado que crea un objeto Auditoria asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único del registro de auditoría dentro del sistema. |
| `valorUsuario` | [Usuario](Usuario.md) | Por referencia | no final | Representa la persona que ejecutó la acción auditada. |
| `valorAccion` | String | Por valor | no final | Representa la acción del negocio que se ejecutó, en lenguaje de negocio. |
| `valorObjetoAfectado` | String | Por valor | no final | Representa el tipo de objeto de dominio sobre el que se ejecutó la acción. |
| `valorRegistroAfectado` | UUID | Por referencia | no final | Representa el identificador del registro concreto que fue afectado por la acción. |
| `valorDetalle` | String | Por valor | no final | Representa la descripción de lo que cambió, para poder reconstruir la decisión. |
| `valorFechaHora` | LocalDateTime | Por referencia | no final | Representa la fecha y hora exactas en que se ejecutó la acción. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getUsuario()` | `setUsuario(Usuario valorUsuario)` | [Usuario](Usuario.md) |
| `getAccion()` | `setAccion(String valorAccion)` | String |
| `getObjetoAfectado()` | `setObjetoAfectado(String valorObjetoAfectado)` | String |
| `getRegistroAfectado()` | `setRegistroAfectado(UUID valorRegistroAfectado)` | UUID |
| `getDetalle()` | `setDetalle(String valorDetalle)` | String |
| `getFechaHora()` | `setFechaHora(LocalDateTime valorFechaHora)` | LocalDateTime |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`registrarAuditoria`](#registrarauditoria) | void | Dejar constancia de un evento del negocio que ya ocurrió. Lo usan todas las responsabilidades que cambian algo, dentro de la misma operación: si la operación falla, no queda auditoría. |
| 2 | [`consultarAuditorias`](#consultarauditorias) | [List&lt;Auditoria&gt;](Auditoria.md) | Consultar el registro histórico de la plataforma, por ejemplo el histórico de decisiones de moderación (CU-015, RF-070). |
| 3 | [`consultarAuditoriasPropias`](#consultarauditoriaspropias) | [List&lt;Auditoria&gt;](Auditoria.md) | Consultar el historial de actividad propio (RN-106). |

### `registrarAuditoria`

```java
public void registrarAuditoria(Usuario usuario, String accion, String objetoAfectado, UUID registroAfectado, String detalle)
```

Dejar constancia de un evento del negocio que ya ocurrió. Lo usan todas las responsabilidades que cambian algo, dentro de la misma operación: si la operación falla, no queda auditoría.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `usuario` | [Usuario](Usuario.md) | Por referencia | Representa la persona que ejecutó la acción auditada. |
| `accion` | String | Por valor | Representa la acción del negocio que se ejecutó, en lenguaje de negocio. |
| `objetoAfectado` | String | Por valor | Representa el tipo de objeto de dominio sobre el que se ejecutó la acción. |
| `registroAfectado` | UUID | Por referencia | Representa el identificador del registro concreto que fue afectado por la acción. |
| `detalle` | String | Por valor | Representa la descripción de lo que cambió, para poder reconstruir la decisión. |

<details>
<summary><b>Excepciones (5)</b></summary>

- [**P-AUD-0002**](../modelo-dominio/Auditoria.md#p-aud-0002) — No se registra la auditoría y se deshace la operación que la originó.
- [**P-AUD-0003**](../modelo-dominio/Auditoria.md#p-aud-0003) — No se registra la auditoría y se deshace la operación que la originó.
- [**P-AUD-0004**](../modelo-dominio/Auditoria.md#p-aud-0004) — No se registra la auditoría y se deshace la operación que la originó.
- [**P-AUD-0005**](../modelo-dominio/Auditoria.md#p-aud-0005) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-AUD-0006**](../modelo-dominio/Auditoria.md#p-aud-0006) — No se registra la auditoría y se deshace la operación que la originó.

</details>

### `consultarAuditorias`

```java
public List<Auditoria> consultarAuditorias(Usuario usuario, String accion, String objetoAfectado, UUID registroAfectado, LocalDateTime fechaHora)
```

Consultar el registro histórico de la plataforma, por ejemplo el histórico de decisiones de moderación (CU-015, RF-070).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `usuario` | [Usuario](Usuario.md) | Por referencia | Representa la persona que ejecutó la acción auditada. |
| `accion` | String | Por valor | Representa la acción del negocio que se ejecutó, en lenguaje de negocio. |
| `objetoAfectado` | String | Por valor | Representa el tipo de objeto de dominio sobre el que se ejecutó la acción. |
| `registroAfectado` | UUID | Por referencia | Representa el identificador del registro concreto que fue afectado por la acción. |
| `fechaHora` | LocalDateTime | Por referencia | Representa la fecha y hora exactas en que se ejecutó la acción. |

<details>
<summary><b>Excepciones (2)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-AUD-0008**](../modelo-dominio/Auditoria.md#p-aud-0008) — No tiene permiso para consultar la auditoría.

</details>

### `consultarAuditoriasPropias`

```java
public List<Auditoria> consultarAuditoriasPropias(String accion, String objetoAfectado, LocalDateTime fechaHora)
```

Consultar el historial de actividad propio (RN-106).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `accion` | String | Por valor | Representa la acción del negocio que se ejecutó, en lenguaje de negocio. |
| `objetoAfectado` | String | Por valor | Representa el tipo de objeto de dominio sobre el que se ejecutó la acción. |
| `fechaHora` | LocalDateTime | Por referencia | Representa la fecha y hora exactas en que se ejecutó la acción. |

<details>
<summary><b>Excepciones (2)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-AUD-0007**](../modelo-dominio/Auditoria.md#p-aud-0007) — Solo puede consultar su propia actividad.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ ProgramaAcademico](ProgramaAcademico.md) · [CoordinacionFacultad ➡️](CoordinacionFacultad.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Auditoria.md) · [🗄️ MER](../mer/Auditoria.md)
