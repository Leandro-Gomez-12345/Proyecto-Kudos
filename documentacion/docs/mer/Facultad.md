# 🗄️ Facultad

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ Categoria](Categoria.md) · [Usuario ➡️](Usuario.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Facultad.md) · [📐 Clase](../clases/Facultad.md)

> Unidad académica que agrupa Programas Académicos. Puede existir sin programas solo mientras se crea.

## Columnas

| Columna | Tipo | Longitud | Díg. enteros | Díg. decimales | Obligatorio | Autoincr. | Llaves | Referencia | Alias |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | `UUID` | 36 | — | — | ✅ | — | 🔑 PK | — | Identificador |
| `nombre` | `VARCHAR(70)` | 70 | — | — | ✅ | — | UQ | — | Nombre |

## Restricciones e índices

| Nombre | Tipo | Columnas |
| --- | --- | --- |
| `PK_Facultad` | Llave primaria | `id` |
| `UQ_facultad_nombre` | Unicidad | `nombre` |

## Descripción de las columnas

| Columna | Descripción |
| --- | --- |
| `id` | Representa el identificador único de la facultad dentro del sistema. Lo genera la aplicación (no es autoincremental) y es la llave primaria PK_Facultad. Debe ser único en el sistema. |
| `nombre` | Representa el nombre oficial de la facultad dentro de la universidad. Solo se permiten caracteres de letras mayúsculas y minúsculas con tildes y espacios. Longitud entre 13 y 70 caracteres. Debe ser único en el sistema (UQ_facultad_nombre). |

---

[🏠 Inicio](../../README.md) · [📑 MER](README.md) · [⬅️ Categoria](Categoria.md) · [Usuario ➡️](Usuario.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Facultad.md) · [📐 Clase](../clases/Facultad.md)
