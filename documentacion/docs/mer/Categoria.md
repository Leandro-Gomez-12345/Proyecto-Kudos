# 🗄️ Categoria

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ Motivo](Motivo.md) · [Facultad ➡️](Facultad.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Categoria.md) · [📐 Clase](../clases/Categoria.md)

> Clasificación institucional de los Kudos. La gestionan Administrador, Rector y Coordinador Académico; una categoría inactiva no se usa en nuevos Kudos.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `nombre` | `VARCHAR(50)` | 50 | — | — | ✅ | — | UQ | — | Nombre |
| `descripcion` | `VARCHAR(300)` | 300 | — | — | ✅ | — | — | — | Descripción |
| `activa` | `BOOLEAN` | — | — | — | ✅ | — | — | — | Activa |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_Categoria` | Llave primaria | `id` |
| `UQ_categoria_nombre` | Unicidad | `nombre` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único de la categoría dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_Categoria. Debe ser único en el sistema. |
| `nombre` | Representa el nombre con el que la universidad identifica la categoría de reconocimiento. Solo letras, tildes y espacios. Longitud entre 3 y 50 caracteres. Debe ser único en el sistema (UQ_categoria_nombre). |
| `descripcion` | Representa la explicación de qué tipo de conductas o logros se reconocen con esta categoría. Letras mayúsculas y minúsculas, números, tildes, espacios y signos de puntuación (. , ; : ¿ ? ¡ ! ( ) -). Longitud entre 10 y 300 caracteres. |
| `activa` | Representa si la categoría puede usarse para crear nuevos Kudos. Sí: se puede usar en nuevos Kudos. No: solo se conserva para los Kudos históricos. Al crear el registro toma el valor verdadero (Sí). |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ Motivo](Motivo.md) · [Facultad ➡️](Facultad.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Categoria.md) · [📐 Clase](../clases/Categoria.md)
