# 📐 EstadoCuenta

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ Rol](Rol.md) · [EstadoContenido ➡️](EstadoContenido.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/EstadoCuenta.md) · [🗄️ MER](../mer/EstadoCuenta.md)

> Catálogo de estados de la cuenta de un usuario. Una cuenta Aprobada aún no está Activa hasta validar correo y celular.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Catálogos |

## Vista rápida

```java
public class EstadoCuenta {

    private UUID id = new UUID(0L, 0L);
    private String nombre = "";

    public EstadoCuenta()
    public EstadoCuenta(UUID valorId, String valorNombre)

    public List<EstadoCuenta> consultarEstadosCuenta()

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. EstadoCuenta | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Es usada por | Asociación | [Usuario](Usuario.md) | 1 | 0..\* | `Usuario.estadoCuenta` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único del Estado Cuenta dentro del sistema. |
| `private` | `nombre` | String | no final | no static | `""` | Representa el nombre del estado cuenta tal como lo entiende la comunidad universitaria. |

## Constructores

### `EstadoCuenta()` — Defecto

Constructor por defecto que crea un objeto EstadoCuenta con todos sus atributos en su valor por defecto

### `EstadoCuenta(UUID valorId, String valorNombre)` — Parametrizado

Constructor parametrizado que crea un objeto EstadoCuenta asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único del Estado Cuenta dentro del sistema. |
| `valorNombre` | String | Por valor | no final | Representa el nombre del estado cuenta tal como lo entiende la comunidad universitaria. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getNombre()` | `setNombre(String valorNombre)` | String |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`consultarEstadosCuenta`](#consultarestadoscuenta) | [List&lt;EstadoCuenta&gt;](EstadoCuenta.md) | Consultar la lista de estados de cuenta que maneja la plataforma. Para filtrar usuarios según el estado de su cuenta. |

### `consultarEstadosCuenta`

```java
public List<EstadoCuenta> consultarEstadosCuenta()
```

Consultar la lista de estados de cuenta que maneja la plataforma. Para filtrar usuarios según el estado de su cuenta.

<details>
<summary><b>Excepciones (1)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ Rol](Rol.md) · [EstadoContenido ➡️](EstadoContenido.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/EstadoCuenta.md) · [🗄️ MER](../mer/EstadoCuenta.md)
