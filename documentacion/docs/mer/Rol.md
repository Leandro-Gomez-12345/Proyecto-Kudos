# 🗄️ Rol

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [EstadoCuenta ➡️](EstadoCuenta.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Rol.md) · [📐 Clase](../clases/Rol.md)

> Catálogo de roles institucionales. Un usuario tiene exactamente un rol activo y los roles son mutuamente excluyentes, es decir que un usuario no puede tener más de un rol.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `nombre` | `VARCHAR(30)` | 30 | — | — | ✅ | — | UQ | — | Nombre |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_Rol` | Llave primaria | `id` |
| `UQ_rol_nombre` | Unicidad | `nombre` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único del Rol dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_Rol. Debe ser único en el sistema. |
| `nombre` | Representa el nombre del rol tal como lo entiende la comunidad universitaria. Solo se permiten caracteres de letras mayúsculas y minúsculas con tildes y espacios. Longitud entre 6 y 30 caracteres. Debe ser único en el sistema (UQ_rol_nombre). |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [EstadoCuenta ➡️](EstadoCuenta.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Rol.md) · [📐 Clase](../clases/Rol.md)
