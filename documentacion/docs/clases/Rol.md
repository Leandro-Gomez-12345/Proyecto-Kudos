# 📐 Rol

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [EstadoCuenta ➡️](EstadoCuenta.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Rol.md) · [🗄️ MER](../mer/Rol.md)

> Catálogo de roles institucionales. Un usuario tiene exactamente un rol activo y los roles son mutuamente excluyentes, es decir que un usuario no puede tener más de un rol.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Catálogos |

## Vista rápida

```java
public class Rol {

    private UUID id = new UUID(0L, 0L);
    private String nombre = "";

    public Rol()
    public Rol(UUID valorId, String valorNombre)

    public List<Rol> consultarRoles()

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. Rol | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Es usada por | Asociación | [Usuario](Usuario.md) | 1 | 0..\* | `Usuario.rol` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único del Rol dentro del sistema. |
| `private` | `nombre` | String | no final | no static | `""` | Representa el nombre del rol tal como lo entiende la comunidad universitaria. |

## Constructores

### `Rol()` — Defecto

Constructor por defecto que crea un objeto Rol con todos sus atributos en su valor por defecto

### `Rol(UUID valorId, String valorNombre)` — Parametrizado

Constructor parametrizado que crea un objeto Rol asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único del Rol dentro del sistema. |
| `valorNombre` | String | Por valor | no final | Representa el nombre del rol tal como lo entiende la comunidad universitaria. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getNombre()` | `setNombre(String valorNombre)` | String |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`consultarRoles`](#consultarroles) | [List&lt;Rol&gt;](Rol.md) | Consultar la lista de roles institucionales que maneja la plataforma. Para elegir el rol con el que una persona se registra y para filtrar usuarios. |

### `consultarRoles`

```java
public List<Rol> consultarRoles()
```

Consultar la lista de roles institucionales que maneja la plataforma. Para elegir el rol con el que una persona se registra y para filtrar usuarios.


---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [EstadoCuenta ➡️](EstadoCuenta.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Rol.md) · [🗄️ MER](../mer/Rol.md)
