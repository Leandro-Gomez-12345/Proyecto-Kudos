# 🗄️ EstadoCuenta

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ Rol](Rol.md) · [EstadoContenido ➡️](EstadoContenido.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/EstadoCuenta.md) · [📐 Clase](../clases/EstadoCuenta.md)

> Catálogo de estados de la cuenta de un usuario. Una cuenta Aprobada aún no está Activa hasta validar correo y celular.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `nombre` | `VARCHAR(30)` | 30 | — | — | ✅ | — | UQ | — | Nombre |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_EstadoCuenta` | Llave primaria | `id` |
| `UQ_estadoCuenta_nombre` | Unicidad | `nombre` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único del Estado Cuenta dentro del sistema, es la llave primaria PK_EstadoCuenta. |
| `nombre` | Representa el nombre del estado cuenta tal como lo entiende la comunidad universitaria. Solo se permiten caracteres de letras mayúsculas y minúsculas con tildes y espacios. Longitud entre 5 y 30 caracteres. Debe ser único en el sistema (UQ_estadoCuenta_nombre). |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ Rol](Rol.md) · [EstadoContenido ➡️](EstadoContenido.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/EstadoCuenta.md) · [📐 Clase](../clases/EstadoCuenta.md)
