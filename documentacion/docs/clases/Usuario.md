# 📐 Usuario

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ Facultad](Facultad.md) · [ProgramaAcademico ➡️](ProgramaAcademico.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Usuario.md) · [🗄️ MER](../mer/Usuario.md)

> Persona de la comunidad universitaria registrada en la plataforma con un rol institucional. Su cuenta solo está activa si fue aprobada y validó correo y celular. La aprobación o el rechazo de su registro lo decide la autoridad que corresponde a su rol; queda en el estado de la cuenta y en la Auditoría.

| Estereotipo | Modificador de acceso | Tipo de clase | Área |
| :---: | :---: | :---: | :---: |
| `class` | `public` | `no final` | Usuarios y auditoría |

## Vista rápida

```java
public class Usuario {

    private UUID id = new UUID(0L, 0L);
    private String nombre = "";
    private String apellido = "";
    private String correoInstitucional = "";
    private String celular = "";
    private boolean/Boolean correoVerificado = false;
    private boolean/Boolean celularVerificado = false;
    private Rol rol = new Rol();
    private EstadoCuenta estadoCuenta = new EstadoCuenta();
    private LocalDateTime fechaRegistro = LocalDateTime.of(1000, 1, 1, 0, 0);

    public Usuario()
    public Usuario(UUID valorId, String valorNombre, String valorApellido, String valorCorreoInstitucional, String valorCelular, boolean/Boolean valorCorreoVerificado, boolean/Boolean valorCelularVerificado, Rol valorRol, EstadoCuenta valorEstadoCuenta, LocalDateTime valorFechaRegistro)

    public Usuario registrarUsuario(String nombre, String apellido, String correoInstitucional, String celular, Rol rol, List<ProgramaAcademico> programas)
    public void presentarNuevamenteRegistroUsuario(UUID id, Rol rol, List<ProgramaAcademico> programas)
    public Usuario registrarRector(String nombre, String apellido, String correoInstitucional, String celular)
    public Usuario registrarAdministrador(String nombre, String apellido, String correoInstitucional, String celular)
    public void confirmarCorreoUsuario(UUID id)
    public void confirmarCelularUsuario(UUID id)
    public void activarCuentaUsuario(UUID id)
    public Usuario consultarInformacionPersonalUsuario(UUID id)
    public List<Usuario> consultarUsuarios(String nombre, String apellido, Rol rol, EstadoCuenta estadoCuenta)
    public Usuario cambiarNombreUsuario(UUID id, String nombre, String apellido)
    public void cambiarCorreoUsuario(UUID id, String correoInstitucional)
    public void cambiarCelularUsuario(UUID id, String celular)
    public void suspenderCuentaUsuario(UUID id, String justificacion)
    public void reactivarCuentaUsuario(UUID id)
    public void desactivarCuentaUsuario(UUID id, String justificacion)
    public void cambiarRolUsuario(UUID id, Rol rol)
    public List<Usuario> consultarUsuariosPendientesAprobacion(Rol rol, EstadoCuenta estadoCuenta)
    public void aprobarUsuario(UUID id)
    public void rechazarUsuario(UUID id, String justificacion)

    // + getters y setters de todos los atributos
}
```

## Relaciones

| Dirección | Tipo | Clase relacionada | Mult. Usuario | Mult. relacionada | Atributo que la representa |
| --- | --- | --- | --- | --- | --- |
| Necesita de | Asociación | [Rol](Rol.md) | 0..\* | 1 | `Usuario.rol` |
| Necesita de | Asociación | [EstadoCuenta](EstadoCuenta.md) | 0..\* | 1 | `Usuario.estadoCuenta` |
| Es usada por | Asociación | [Auditoria](Auditoria.md) | 1 | 0..\* | `Auditoria.usuario` |
| Es usada por | Asociación | [CoordinacionFacultad](CoordinacionFacultad.md) | 1 | 0..\* | `CoordinacionFacultad.usuario` |
| Es usada por | Asociación | [CoordinacionPrograma](CoordinacionPrograma.md) | 1 | 0..\* | `CoordinacionPrograma.usuario` |
| Es usada por | Asociación | [VinculacionAcademica](VinculacionAcademica.md) | 1 | 0..\* | `VinculacionAcademica.usuario` |
| Es usada por | Asociación | [Kudo](Kudo.md) | 1 | 0..\* | `Kudo.autor` |
| Es usada por | Asociación | [Kudo](Kudo.md) | 1 | 0..\* | `Kudo.destinatario` |
| Es usada por | Asociación | [Replica](Replica.md) | 1 | 0..\* | `Replica.autor` |
| Es usada por | Asociación | [CalificacionKudo](CalificacionKudo.md) | 1 | 0..\* | `CalificacionKudo.usuario` |
| Es usada por | Asociación | [RevisionKudo](RevisionKudo.md) | 1 | 0..\* | `RevisionKudo.moderador` |
| Es usada por | Asociación | [CalificacionReplica](CalificacionReplica.md) | 1 | 0..\* | `CalificacionReplica.usuario` |
| Es usada por | Asociación | [RevisionReplica](RevisionReplica.md) | 1 | 0..\* | `RevisionReplica.moderador` |
| Es usada por | Asociación | [ReporteKudo](ReporteKudo.md) | 1 | 0..\* | `ReporteKudo.usuario` |
| Es usada por | Asociación | [ReporteReplica](ReporteReplica.md) | 1 | 0..\* | `ReporteReplica.usuario` |

## Atributos

| Acceso | Nombre | Tipo | Modificación | Alcance | Valor por defecto | Descripción |
| --- | --- | --- | --- | --- | --- | --- |
| `private` | `id` | UUID | no final | no static | `new UUID(0L, 0L)` | Representa el identificador único del usuario dentro del sistema. |
| `private` | `nombre` | String | no final | no static | `""` | Representa el nombre o los nombres de la persona. |
| `private` | `apellido` | String | no final | no static | `""` | Representa el apellido o los apellidos de la persona. |
| `private` | `correoInstitucional` | String | no final | no static | `""` | Representa el correo institucional con el que la persona se registra y se identifica. |
| `private` | `celular` | String | no final | no static | `""` | Representa el número de celular de la persona, usado para validar su identidad. |
| `private` | `correoVerificado` | boolean/Boolean | no final | no static | `false` | Representa si la persona ya confirmó que el correo institucional le pertenece. |
| `private` | `celularVerificado` | boolean/Boolean | no final | no static | `false` | Representa si la persona ya confirmó que el número celular le pertenece. |
| `private` | `rol` | [Rol](Rol.md) | no final | no static | `new Rol()` | Representa el rol institucional que ejerce la persona en la plataforma. |
| `private` | `estadoCuenta` | [EstadoCuenta](EstadoCuenta.md) | no final | no static | `new EstadoCuenta()` | Representa en qué estado se encuentra la cuenta de la persona. |
| `private` | `fechaRegistro` | LocalDateTime | no final | no static | `LocalDateTime.of(1000, 1, 1, 0, 0)` | Representa la fecha y hora en que la persona se registró en la plataforma. |

## Constructores

### `Usuario()` — Defecto

Constructor por defecto que crea un objeto Usuario con todos sus atributos en su valor por defecto

### `Usuario(UUID valorId, String valorNombre, String valorApellido, String valorCorreoInstitucional, String valorCelular, boolean/Boolean valorCorreoVerificado, boolean/Boolean valorCelularVerificado, Rol valorRol, EstadoCuenta valorEstadoCuenta, LocalDateTime valorFechaRegistro)` — Parametrizado

Constructor parametrizado que crea un objeto Usuario asignando el valor recibido a cada uno de sus atributos

| Parámetro | Tipo | Paso | Modificación | Descripción |
| --- | --- | --- | --- | --- |
| `valorId` | UUID | Por referencia | no final | Representa el identificador único del usuario dentro del sistema. |
| `valorNombre` | String | Por valor | no final | Representa el nombre o los nombres de la persona. |
| `valorApellido` | String | Por valor | no final | Representa el apellido o los apellidos de la persona. |
| `valorCorreoInstitucional` | String | Por valor | no final | Representa el correo institucional con el que la persona se registra y se identifica. |
| `valorCelular` | String | Por valor | no final | Representa el número de celular de la persona, usado para validar su identidad. |
| `valorCorreoVerificado` | boolean/Boolean | Por valor | no final | Representa si la persona ya confirmó que el correo institucional le pertenece. |
| `valorCelularVerificado` | boolean/Boolean | Por valor | no final | Representa si la persona ya confirmó que el número celular le pertenece. |
| `valorRol` | [Rol](Rol.md) | Por referencia | no final | Representa el rol institucional que ejerce la persona en la plataforma. |
| `valorEstadoCuenta` | [EstadoCuenta](EstadoCuenta.md) | Por referencia | no final | Representa en qué estado se encuentra la cuenta de la persona. |
| `valorFechaRegistro` | LocalDateTime | Por referencia | no final | Representa la fecha y hora en que la persona se registró en la plataforma. |

## Métodos accesores

| Getter | Setter | Tipo |
| --- | --- | --- |
| `getId()` | `setId(UUID valorId)` | UUID |
| `getNombre()` | `setNombre(String valorNombre)` | String |
| `getApellido()` | `setApellido(String valorApellido)` | String |
| `getCorreoInstitucional()` | `setCorreoInstitucional(String valorCorreoInstitucional)` | String |
| `getCelular()` | `setCelular(String valorCelular)` | String |
| `getCorreoVerificado()` | `setCorreoVerificado(boolean/Boolean valorCorreoVerificado)` | boolean/Boolean |
| `getCelularVerificado()` | `setCelularVerificado(boolean/Boolean valorCelularVerificado)` | boolean/Boolean |
| `getRol()` | `setRol(Rol valorRol)` | [Rol](Rol.md) |
| `getEstadoCuenta()` | `setEstadoCuenta(EstadoCuenta valorEstadoCuenta)` | [EstadoCuenta](EstadoCuenta.md) |
| `getFechaRegistro()` | `setFechaRegistro(LocalDateTime valorFechaRegistro)` | LocalDateTime |

> Todos los accesores son `public` y `no final`.

## Métodos

| # | Método | Retorna | Descripción |
| --- | --- | --- | --- |
| 1 | [`registrarUsuario`](#registrarusuario) | [Usuario](Usuario.md) | Registrarse en la plataforma con correo institucional y celular, eligiendo el rol (Estudiante, Docente o un rol de coordinador). El estudiante y el docente indican sus programas y quedan vinculados a ellos. La cuenta queda Pendiente de aprobación hasta que la autoridad que corresponde a su rol la apruebe o la rechace (RF-001 a RF-005). |
| 2 | [`presentarNuevamenteRegistroUsuario`](#presentarnuevamenteregistrousuario) | void | Volver a presentar el registro después de un rechazo, con la misma cuenta (el correo sigue siendo único, RN-073). Solo se puede si la cuenta está Rechazada; puede cambiar el rol y los programas. La cuenta vuelve a Pendiente de aprobación. |
| 3 | [`registrarRector`](#registrarrector) | [Usuario](Usuario.md) | Registrar al Rector de la universidad. Solo lo hace un Administrador (RN-094) y la cuenta nace Aprobada; se activa cuando un Administrador confirme su correo y su celular. Solo puede haber un Rector vigente. |
| 4 | [`registrarAdministrador`](#registraradministrador) | [Usuario](Usuario.md) | Registrar a otro Administrador de la plataforma. Solo lo hace un Administrador (RN-095) y la cuenta nace Aprobada; se activa cuando otro Administrador confirme su correo y su celular. El primer Administrador se crea con los datos iniciales (seed). |
| 5 | [`confirmarCorreoUsuario`](#confirmarcorreousuario) | void | Confirmar que el correo institucional registrado pertenece a la persona (RF-006, RN-077, RN-079). Lo hace la autoridad que debe decidir su registro o que la gestiona; el del Rector y el de los Administradores lo confirma un Administrador. Si ya se cumplen las otras condiciones, la cuenta se activa. |
| 6 | [`confirmarCelularUsuario`](#confirmarcelularusuario) | void | Confirmar que el número celular registrado pertenece a la persona (RF-007, RN-078, RN-080). Lo hace la autoridad que debe decidir su registro o que la gestiona; el del Rector y el de los Administradores lo confirma un Administrador. Si ya se cumplen las otras condiciones, la cuenta se activa. |
| 7 | [`activarCuentaUsuario`](#activarcuentausuario) | void | Activar la cuenta cuando ya fue aprobada y tiene el correo y el celular confirmados. Lo hace el sistema apenas se cumple la última condición (RN-084 a RN-088). En la auditoría figura como usuario el titular de la cuenta. |
| 8 | [`consultarInformacionPersonalUsuario`](#consultarinformacionpersonalusuario) | [Usuario](Usuario.md) | Consultar la información personal de una persona y el estado de sus validaciones (RN-101, RN-103, RF-008). |
| 9 | [`consultarUsuarios`](#consultarusuarios) | [List&lt;Usuario&gt;](Usuario.md) | Buscar personas de la comunidad, por ejemplo para elegir al destinatario de un Kudo o para saber quién es el Rector activo (RF-076, RF-077). No muestra correo ni celular. |
| 10 | [`cambiarNombreUsuario`](#cambiarnombreusuario) | [Usuario](Usuario.md) | Corregir el nombre y el apellido propios (RN-102, RN-109). |
| 11 | [`cambiarCorreoUsuario`](#cambiarcorreousuario) | void | Cambiar el correo institucional propio. El correo nuevo debe confirmarlo otra vez la autoridad que lo gestiona (RN-104, RN-110). |
| 12 | [`cambiarCelularUsuario`](#cambiarcelularusuario) | void | Cambiar el número celular propio. El celular nuevo debe confirmarlo otra vez la autoridad que lo gestiona (RN-105, RN-111). |
| 13 | [`suspenderCuentaUsuario`](#suspendercuentausuario) | void | Suspender temporalmente la cuenta de una persona a cargo; mientras tanto no puede ingresar. Si coordina una facultad o un programa, su coordinación termina y la unidad queda sin coordinador hasta que se asigne otro. Sus vinculaciones académicas no se tocan. |
| 14 | [`reactivarCuentaUsuario`](#reactivarcuentausuario) | void | Levantar la suspensión de una cuenta para que la persona vuelva a ingresar. Si era coordinador, su coordinación no vuelve sola: la autoridad debe asignarla de nuevo. No se reactiva a un Rector o Coordinador Académico si mientras tanto se registró otro. |
| 15 | [`desactivarCuentaUsuario`](#desactivarcuentausuario) | void | Desactivar definitivamente la cuenta de una persona a cargo. Su historia (Kudos, réplicas, auditoría) se conserva. Si coordina una facultad o un programa, su coordinación termina; si es estudiante o docente, sus vinculaciones vigentes se cierran con fecha. |
| 16 | [`cambiarRolUsuario`](#cambiarrolusuario) | void | Cambiar el rol institucional de una persona cuando cambia su cargo (por ejemplo, un coordinador que vuelve a ser docente). Nadie cambia su propio rol (RN-107). |
| 17 | [`consultarUsuariosPendientesAprobacion`](#consultarusuariospendientesaprobacion) | [List&lt;Usuario&gt;](Usuario.md) | Consultar las personas registradas cuya cuenta está Pendiente de aprobación y que le corresponde decidir a quien consulta. |
| 18 | [`aprobarUsuario`](#aprobarusuario) | void | Aprobar el registro de un estudiante, docente, Coordinador de Facultad o Coordinador Académico (el aspirante a Coordinador de Programa se aprueba al asignarle un programa). Su cuenta pasa a Aprobada y se activa cuando además tenga correo y celular validados (RN-084, RN-096). Quién aprobó y cuándo quedan en la Auditoría. |
| 19 | [`rechazarUsuario`](#rechazarusuario) | void | Rechazar el registro de una persona explicando la razón. Su cuenta pasa a Rechazada y sus vinculaciones se cierran; quién rechazó, cuándo y por qué quedan en la Auditoría. La persona puede volver a presentar su registro. |

### `registrarUsuario`

```java
public Usuario registrarUsuario(String nombre, String apellido, String correoInstitucional, String celular, Rol rol, List<ProgramaAcademico> programas)
```

Registrarse en la plataforma con correo institucional y celular, eligiendo el rol (Estudiante, Docente o un rol de coordinador). El estudiante y el docente indican sus programas y quedan vinculados a ellos. La cuenta queda Pendiente de aprobación hasta que la autoridad que corresponde a su rol la apruebe o la rechace (RF-001 a RF-005).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `nombre` | String | Por valor | Representa el nombre o los nombres de la persona. |
| `apellido` | String | Por valor | Representa el apellido o los apellidos de la persona. |
| `correoInstitucional` | String | Por valor | Representa el correo institucional con el que la persona se registra y se identifica. |
| `celular` | String | Por valor | Representa el número de celular de la persona, usado para validar su identidad. |
| `rol` | [Rol](Rol.md) | Por referencia | Representa el rol institucional que ejerce la persona en la plataforma. |
| `programas` | [List&lt;ProgramaAcademico&gt;](ProgramaAcademico.md) | Por referencia | Programas a los que pertenece; obligatorio para Estudiante y Docente. |

<details>
<summary><b>Excepciones (11)</b></summary>

- [**P-USU-0015**](../modelo-dominio/Usuario.md#p-usu-0015) — Se informa cuál dato no cumple y cómo debe escribirse.
- [**P-ROL-0001**](../modelo-dominio/Rol.md#p-rol-0001) — No existe el rol que se indicó.
- [**P-USU-0023**](../modelo-dominio/Usuario.md#p-usu-0023) — No puede registrarse con ese rol; el Rector y los Administradores los registra un Administrador.
- [**P-USU-0026**](../modelo-dominio/Usuario.md#p-usu-0026) — Debe indicar al menos un programa académico al que pertenece.
- [**P-PRO-0001**](../modelo-dominio/ProgramaAcademico.md#p-pro-0001) — No existe el programa académico que se indicó.
- [**P-USU-0019**](../modelo-dominio/Usuario.md#p-usu-0019) — Ya existe una cuenta con ese correo institucional.
- [**P-USU-0020**](../modelo-dominio/Usuario.md#p-usu-0020) — Ya existe una cuenta con ese número celular.
- [**P-USU-0021**](../modelo-dominio/Usuario.md#p-usu-0021) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-USU-0022**](../modelo-dominio/Usuario.md#p-usu-0022) — No se pudo registrar la cuenta; se deshace toda la operación.
- [**P-VIN-0001**](../modelo-dominio/VinculacionAcademica.md#p-vin-0001) — No se pudieron crear las vinculaciones; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `presentarNuevamenteRegistroUsuario`

```java
public void presentarNuevamenteRegistroUsuario(UUID id, Rol rol, List<ProgramaAcademico> programas)
```

Volver a presentar el registro después de un rechazo, con la misma cuenta (el correo sigue siendo único, RN-073). Solo se puede si la cuenta está Rechazada; puede cambiar el rol y los programas. La cuenta vuelve a Pendiente de aprobación.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la cuenta rechazada. |
| `rol` | [Rol](Rol.md) | Por referencia | Representa el rol institucional que ejerce la persona en la plataforma. |
| `programas` | [List&lt;ProgramaAcademico&gt;](ProgramaAcademico.md) | Por referencia | Programas a los que pertenece; obligatorio para Estudiante y Docente. |

<details>
<summary><b>Excepciones (9)</b></summary>

- [**P-USU-0008**](../modelo-dominio/Usuario.md#p-usu-0008) — No existe el usuario que se indicó.
- [**P-USU-0027**](../modelo-dominio/Usuario.md#p-usu-0027) — Su registro todavía está pendiente de aprobación; espere la decisión.
- [**P-ROL-0001**](../modelo-dominio/Rol.md#p-rol-0001) — No existe el rol que se indicó.
- [**P-USU-0023**](../modelo-dominio/Usuario.md#p-usu-0023) — No puede registrarse con ese rol; el Rector y los Administradores los registra un Administrador.
- [**P-USU-0026**](../modelo-dominio/Usuario.md#p-usu-0026) — Debe indicar al menos un programa académico al que pertenece.
- [**P-PRO-0001**](../modelo-dominio/ProgramaAcademico.md#p-pro-0001) — No existe el programa académico que se indicó.
- [**P-USU-0028**](../modelo-dominio/Usuario.md#p-usu-0028) — No se pudo presentar de nuevo el registro; se deshace toda la operación.
- [**P-VIN-0001**](../modelo-dominio/VinculacionAcademica.md#p-vin-0001) — No se pudieron crear las vinculaciones; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `registrarRector`

```java
public Usuario registrarRector(String nombre, String apellido, String correoInstitucional, String celular)
```

Registrar al Rector de la universidad. Solo lo hace un Administrador (RN-094) y la cuenta nace Aprobada; se activa cuando un Administrador confirme su correo y su celular. Solo puede haber un Rector vigente.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `nombre` | String | Por valor | Representa el nombre o los nombres de la persona. |
| `apellido` | String | Por valor | Representa el apellido o los apellidos de la persona. |
| `correoInstitucional` | String | Por valor | Representa el correo institucional con el que la persona se registra y se identifica. |
| `celular` | String | Por valor | Representa el número de celular de la persona, usado para validar su identidad. |

<details>
<summary><b>Excepciones (9)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0004**](../modelo-dominio/Usuario.md#p-usu-0004) — Solo un Administrador puede realizar esta acción.
- [**P-USU-0024**](../modelo-dominio/Usuario.md#p-usu-0024) — Se informa cuál dato no cumple y cómo debe escribirse.
- [**P-USU-0019**](../modelo-dominio/Usuario.md#p-usu-0019) — Ya existe una cuenta con ese correo institucional.
- [**P-USU-0020**](../modelo-dominio/Usuario.md#p-usu-0020) — Ya existe una cuenta con ese número celular.
- [**P-USU-0042**](../modelo-dominio/Usuario.md#p-usu-0042) — Ya existe un Rector activo en la universidad.
- [**P-USU-0021**](../modelo-dominio/Usuario.md#p-usu-0021) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-USU-0025**](../modelo-dominio/Usuario.md#p-usu-0025) — No se pudo registrar la cuenta; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `registrarAdministrador`

```java
public Usuario registrarAdministrador(String nombre, String apellido, String correoInstitucional, String celular)
```

Registrar a otro Administrador de la plataforma. Solo lo hace un Administrador (RN-095) y la cuenta nace Aprobada; se activa cuando otro Administrador confirme su correo y su celular. El primer Administrador se crea con los datos iniciales (seed).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `nombre` | String | Por valor | Representa el nombre o los nombres de la persona. |
| `apellido` | String | Por valor | Representa el apellido o los apellidos de la persona. |
| `correoInstitucional` | String | Por valor | Representa el correo institucional con el que la persona se registra y se identifica. |
| `celular` | String | Por valor | Representa el número de celular de la persona, usado para validar su identidad. |

<details>
<summary><b>Excepciones (8)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0004**](../modelo-dominio/Usuario.md#p-usu-0004) — Solo un Administrador puede realizar esta acción.
- [**P-USU-0024**](../modelo-dominio/Usuario.md#p-usu-0024) — Se informa cuál dato no cumple y cómo debe escribirse.
- [**P-USU-0019**](../modelo-dominio/Usuario.md#p-usu-0019) — Ya existe una cuenta con ese correo institucional.
- [**P-USU-0020**](../modelo-dominio/Usuario.md#p-usu-0020) — Ya existe una cuenta con ese número celular.
- [**P-USU-0021**](../modelo-dominio/Usuario.md#p-usu-0021) — No se informa al actor: el sistema genera un nuevo identificador.
- [**P-USU-0025**](../modelo-dominio/Usuario.md#p-usu-0025) — No se pudo registrar la cuenta; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `confirmarCorreoUsuario`

```java
public void confirmarCorreoUsuario(UUID id)
```

Confirmar que el correo institucional registrado pertenece a la persona (RF-006, RN-077, RN-079). Lo hace la autoridad que debe decidir su registro o que la gestiona; el del Rector y el de los Administradores lo confirma un Administrador. Si ya se cumplen las otras condiciones, la cuenta se activa.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la persona cuyo correo se confirma. |

<details>
<summary><b>Excepciones (7)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0008**](../modelo-dominio/Usuario.md#p-usu-0008) — No existe el usuario que se indicó.
- [**P-USU-0049**](../modelo-dominio/Usuario.md#p-usu-0049) — No puede realizar esta acción sobre su propia cuenta.
- [**P-USU-0031**](../modelo-dominio/Usuario.md#p-usu-0031) — A usted no le corresponde confirmar los datos de contacto de esta persona.
- [**P-USU-0032**](../modelo-dominio/Usuario.md#p-usu-0032) — El correo ya estaba verificado.
- [**P-USU-0034**](../modelo-dominio/Usuario.md#p-usu-0034) — No se pudo activar la cuenta; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `confirmarCelularUsuario`

```java
public void confirmarCelularUsuario(UUID id)
```

Confirmar que el número celular registrado pertenece a la persona (RF-007, RN-078, RN-080). Lo hace la autoridad que debe decidir su registro o que la gestiona; el del Rector y el de los Administradores lo confirma un Administrador. Si ya se cumplen las otras condiciones, la cuenta se activa.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la persona cuyo celular se confirma. |

<details>
<summary><b>Excepciones (7)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0008**](../modelo-dominio/Usuario.md#p-usu-0008) — No existe el usuario que se indicó.
- [**P-USU-0049**](../modelo-dominio/Usuario.md#p-usu-0049) — No puede realizar esta acción sobre su propia cuenta.
- [**P-USU-0031**](../modelo-dominio/Usuario.md#p-usu-0031) — A usted no le corresponde confirmar los datos de contacto de esta persona.
- [**P-USU-0033**](../modelo-dominio/Usuario.md#p-usu-0033) — El celular ya estaba verificado.
- [**P-USU-0034**](../modelo-dominio/Usuario.md#p-usu-0034) — No se pudo activar la cuenta; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `activarCuentaUsuario`

```java
public void activarCuentaUsuario(UUID id)
```

Activar la cuenta cuando ya fue aprobada y tiene el correo y el celular confirmados. Lo hace el sistema apenas se cumple la última condición (RN-084 a RN-088). En la auditoría figura como usuario el titular de la cuenta.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador del usuario. |

<details>
<summary><b>Excepciones (3)</b></summary>

- [**P-USU-0008**](../modelo-dominio/Usuario.md#p-usu-0008) — No existe el usuario que se indicó.
- [**P-USU-0035**](../modelo-dominio/Usuario.md#p-usu-0035) — La cuenta aún no cumple las tres condiciones para activarse.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `consultarInformacionPersonalUsuario`

```java
public Usuario consultarInformacionPersonalUsuario(UUID id)
```

Consultar la información personal de una persona y el estado de sus validaciones (RN-101, RN-103, RF-008).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador del usuario. |

<details>
<summary><b>Excepciones (3)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0008**](../modelo-dominio/Usuario.md#p-usu-0008) — No existe el usuario que se indicó.
- [**P-USU-0014**](../modelo-dominio/Usuario.md#p-usu-0014) — No tiene permiso para consultar la información personal de esta persona.

</details>

### `consultarUsuarios`

```java
public List<Usuario> consultarUsuarios(String nombre, String apellido, Rol rol, EstadoCuenta estadoCuenta)
```

Buscar personas de la comunidad, por ejemplo para elegir al destinatario de un Kudo o para saber quién es el Rector activo (RF-076, RF-077). No muestra correo ni celular.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `nombre` | String | Por valor | Representa el nombre o los nombres de la persona. |
| `apellido` | String | Por valor | Representa el apellido o los apellidos de la persona. |
| `rol` | [Rol](Rol.md) | Por referencia | Representa el rol institucional que ejerce la persona en la plataforma. |
| `estadoCuenta` | [EstadoCuenta](EstadoCuenta.md) | Por referencia | Representa en qué estado se encuentra la cuenta de la persona. |

<details>
<summary><b>Excepciones (1)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.

</details>

### `cambiarNombreUsuario`

```java
public Usuario cambiarNombreUsuario(UUID id, String nombre, String apellido)
```

Corregir el nombre y el apellido propios (RN-102, RN-109).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador del usuario. |
| `nombre` | String | Por valor | Representa el nombre o los nombres de la persona. |
| `apellido` | String | Por valor | Representa el apellido o los apellidos de la persona. |

<details>
<summary><b>Excepciones (5)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0008**](../modelo-dominio/Usuario.md#p-usu-0008) — No existe el usuario que se indicó.
- [**P-USU-0013**](../modelo-dominio/Usuario.md#p-usu-0013) — Solo puede realizar esta acción sobre su propia cuenta.
- [**P-USU-0016**](../modelo-dominio/Usuario.md#p-usu-0016) — Se informa cuál dato no cumple y cómo debe escribirse.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `cambiarCorreoUsuario`

```java
public void cambiarCorreoUsuario(UUID id, String correoInstitucional)
```

Cambiar el correo institucional propio. El correo nuevo debe confirmarlo otra vez la autoridad que lo gestiona (RN-104, RN-110).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador del usuario. |
| `correoInstitucional` | String | Por valor | Representa el correo institucional con el que la persona se registra y se identifica. |

<details>
<summary><b>Excepciones (7)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0008**](../modelo-dominio/Usuario.md#p-usu-0008) — No existe el usuario que se indicó.
- [**P-USU-0013**](../modelo-dominio/Usuario.md#p-usu-0013) — Solo puede realizar esta acción sobre su propia cuenta.
- [**P-USU-0017**](../modelo-dominio/Usuario.md#p-usu-0017) — El correo debe ser institucional y terminar en @uco.edu.co.
- [**P-USU-0019**](../modelo-dominio/Usuario.md#p-usu-0019) — Ya existe una cuenta con ese correo institucional.
- [**P-USU-0044**](../modelo-dominio/Usuario.md#p-usu-0044) — No se pudo actualizar el correo; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `cambiarCelularUsuario`

```java
public void cambiarCelularUsuario(UUID id, String celular)
```

Cambiar el número celular propio. El celular nuevo debe confirmarlo otra vez la autoridad que lo gestiona (RN-105, RN-111).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador del usuario. |
| `celular` | String | Por valor | Representa el número de celular de la persona, usado para validar su identidad. |

<details>
<summary><b>Excepciones (7)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0008**](../modelo-dominio/Usuario.md#p-usu-0008) — No existe el usuario que se indicó.
- [**P-USU-0013**](../modelo-dominio/Usuario.md#p-usu-0013) — Solo puede realizar esta acción sobre su propia cuenta.
- [**P-USU-0018**](../modelo-dominio/Usuario.md#p-usu-0018) — El celular debe tener 10 dígitos e iniciar en 3.
- [**P-USU-0020**](../modelo-dominio/Usuario.md#p-usu-0020) — Ya existe una cuenta con ese número celular.
- [**P-USU-0045**](../modelo-dominio/Usuario.md#p-usu-0045) — No se pudo actualizar el celular; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `suspenderCuentaUsuario`

```java
public void suspenderCuentaUsuario(UUID id, String justificacion)
```

Suspender temporalmente la cuenta de una persona a cargo; mientras tanto no puede ingresar. Si coordina una facultad o un programa, su coordinación termina y la unidad queda sin coordinador hasta que se asigne otro. Sus vinculaciones académicas no se tocan.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador del usuario afectado. |
| `justificacion` | String | Por valor | Razón de la suspensión; queda en el detalle de la auditoría. |

<details>
<summary><b>Excepciones (8)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0008**](../modelo-dominio/Usuario.md#p-usu-0008) — No existe el usuario que se indicó.
- [**P-USU-0049**](../modelo-dominio/Usuario.md#p-usu-0049) — No puede realizar esta acción sobre su propia cuenta.
- [**P-USU-0012**](../modelo-dominio/Usuario.md#p-usu-0012) — Usted no es la autoridad que gestiona a esta persona.
- [**P-USU-0046**](../modelo-dominio/Usuario.md#p-usu-0046) — Solo se puede suspender una cuenta activa.
- [**P-COF-0001**](../modelo-dominio/CoordinacionFacultad.md#p-cof-0001) — No se pudo finalizar la coordinación; se deshace toda la operación.
- [**P-COP-0001**](../modelo-dominio/CoordinacionPrograma.md#p-cop-0001) — No se pudo finalizar la coordinación; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `reactivarCuentaUsuario`

```java
public void reactivarCuentaUsuario(UUID id)
```

Levantar la suspensión de una cuenta para que la persona vuelva a ingresar. Si era coordinador, su coordinación no vuelve sola: la autoridad debe asignarla de nuevo. No se reactiva a un Rector o Coordinador Académico si mientras tanto se registró otro.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador del usuario afectado. |

<details>
<summary><b>Excepciones (8)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0008**](../modelo-dominio/Usuario.md#p-usu-0008) — No existe el usuario que se indicó.
- [**P-USU-0049**](../modelo-dominio/Usuario.md#p-usu-0049) — No puede realizar esta acción sobre su propia cuenta.
- [**P-USU-0012**](../modelo-dominio/Usuario.md#p-usu-0012) — Usted no es la autoridad que gestiona a esta persona.
- [**P-USU-0047**](../modelo-dominio/Usuario.md#p-usu-0047) — Solo se puede reactivar una cuenta suspendida.
- [**P-USU-0042**](../modelo-dominio/Usuario.md#p-usu-0042) — Ya existe un Rector activo en la universidad.
- [**P-USU-0043**](../modelo-dominio/Usuario.md#p-usu-0043) — Ya existe un Coordinador Académico activo en la universidad.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `desactivarCuentaUsuario`

```java
public void desactivarCuentaUsuario(UUID id, String justificacion)
```

Desactivar definitivamente la cuenta de una persona a cargo. Su historia (Kudos, réplicas, auditoría) se conserva. Si coordina una facultad o un programa, su coordinación termina; si es estudiante o docente, sus vinculaciones vigentes se cierran con fecha.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador del usuario afectado. |
| `justificacion` | String | Por valor | Razón de la desactivación; queda en el detalle de la auditoría. |

<details>
<summary><b>Excepciones (9)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0008**](../modelo-dominio/Usuario.md#p-usu-0008) — No existe el usuario que se indicó.
- [**P-USU-0049**](../modelo-dominio/Usuario.md#p-usu-0049) — No puede realizar esta acción sobre su propia cuenta.
- [**P-USU-0012**](../modelo-dominio/Usuario.md#p-usu-0012) — Usted no es la autoridad que gestiona a esta persona.
- [**P-USU-0048**](../modelo-dominio/Usuario.md#p-usu-0048) — La cuenta ya está desactivada o nunca fue activada.
- [**P-COF-0001**](../modelo-dominio/CoordinacionFacultad.md#p-cof-0001) — No se pudo finalizar la coordinación; se deshace toda la operación.
- [**P-COP-0001**](../modelo-dominio/CoordinacionPrograma.md#p-cop-0001) — No se pudo finalizar la coordinación; se deshace toda la operación.
- [**P-VIN-0002**](../modelo-dominio/VinculacionAcademica.md#p-vin-0002) — No se pudieron cerrar las vinculaciones; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `cambiarRolUsuario`

```java
public void cambiarRolUsuario(UUID id, Rol rol)
```

Cambiar el rol institucional de una persona cuando cambia su cargo (por ejemplo, un coordinador que vuelve a ser docente). Nadie cambia su propio rol (RN-107).

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador del usuario afectado. |
| `rol` | [Rol](Rol.md) | Por referencia | Representa el rol institucional que ejerce la persona en la plataforma. |

<details>
<summary><b>Excepciones (10)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0008**](../modelo-dominio/Usuario.md#p-usu-0008) — No existe el usuario que se indicó.
- [**P-USU-0049**](../modelo-dominio/Usuario.md#p-usu-0049) — No puede realizar esta acción sobre su propia cuenta.
- [**P-USU-0012**](../modelo-dominio/Usuario.md#p-usu-0012) — Usted no es la autoridad que gestiona a esta persona.
- [**P-ROL-0001**](../modelo-dominio/Rol.md#p-rol-0001) — No existe el rol que se indicó.
- [**P-USU-0051**](../modelo-dominio/Usuario.md#p-usu-0051) — La persona ya tiene ese rol.
- [**P-USU-0050**](../modelo-dominio/Usuario.md#p-usu-0050) — Primero debe cerrarse la coordinación o la vinculación vigente de la persona.
- [**P-USU-0042**](../modelo-dominio/Usuario.md#p-usu-0042) — Ya existe un Rector activo en la universidad.
- [**P-USU-0043**](../modelo-dominio/Usuario.md#p-usu-0043) — Ya existe un Coordinador Académico activo en la universidad.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `consultarUsuariosPendientesAprobacion`

```java
public List<Usuario> consultarUsuariosPendientesAprobacion(Rol rol, EstadoCuenta estadoCuenta)
```

Consultar las personas registradas cuya cuenta está Pendiente de aprobación y que le corresponde decidir a quien consulta.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `rol` | [Rol](Rol.md) | Por referencia | Representa el rol institucional que ejerce la persona en la plataforma. |
| `estadoCuenta` | [EstadoCuenta](EstadoCuenta.md) | Por referencia | Representa en qué estado se encuentra la cuenta de la persona. |

<details>
<summary><b>Excepciones (2)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0039**](../modelo-dominio/Usuario.md#p-usu-0039) — No tiene registros pendientes a su cargo.

</details>

### `aprobarUsuario`

```java
public void aprobarUsuario(UUID id)
```

Aprobar el registro de un estudiante, docente, Coordinador de Facultad o Coordinador Académico (el aspirante a Coordinador de Programa se aprueba al asignarle un programa). Su cuenta pasa a Aprobada y se activa cuando además tenga correo y celular validados (RN-084, RN-096). Quién aprobó y cuándo quedan en la Auditoría.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la persona registrada. |

<details>
<summary><b>Excepciones (10)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0008**](../modelo-dominio/Usuario.md#p-usu-0008) — No existe el usuario que se indicó.
- [**P-USU-0036**](../modelo-dominio/Usuario.md#p-usu-0036) — El registro de esta persona ya fue decidido.
- [**P-USU-0037**](../modelo-dominio/Usuario.md#p-usu-0037) — A usted no le corresponde decidir el registro de esta persona.
- [**P-USU-0049**](../modelo-dominio/Usuario.md#p-usu-0049) — No puede realizar esta acción sobre su propia cuenta.
- [**P-USU-0042**](../modelo-dominio/Usuario.md#p-usu-0042) — Ya existe un Rector activo en la universidad.
- [**P-USU-0043**](../modelo-dominio/Usuario.md#p-usu-0043) — Ya existe un Coordinador Académico activo en la universidad.
- [**P-USU-0040**](../modelo-dominio/Usuario.md#p-usu-0040) — No se pudo aprobar la cuenta; se deshace toda la operación.
- [**P-USU-0034**](../modelo-dominio/Usuario.md#p-usu-0034) — No se pudo activar la cuenta; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

### `rechazarUsuario`

```java
public void rechazarUsuario(UUID id, String justificacion)
```

Rechazar el registro de una persona explicando la razón. Su cuenta pasa a Rechazada y sus vinculaciones se cierran; quién rechazó, cuándo y por qué quedan en la Auditoría. La persona puede volver a presentar su registro.

| Parámetro | Tipo | Paso | Descripción |
| --- | --- | --- | --- |
| `id` | UUID | Por referencia | Identificador de la persona registrada. |
| `justificacion` | String | Por valor | Razón del rechazo; queda en el detalle de la auditoría. |

<details>
<summary><b>Excepciones (9)</b></summary>

- [**P-USU-0001**](../modelo-dominio/Usuario.md#p-usu-0001) — Su cuenta no está activa, por lo que no puede realizar esta acción.
- [**P-USU-0008**](../modelo-dominio/Usuario.md#p-usu-0008) — No existe el usuario que se indicó.
- [**P-USU-0036**](../modelo-dominio/Usuario.md#p-usu-0036) — El registro de esta persona ya fue decidido.
- [**P-USU-0037**](../modelo-dominio/Usuario.md#p-usu-0037) — A usted no le corresponde decidir el registro de esta persona.
- [**P-USU-0049**](../modelo-dominio/Usuario.md#p-usu-0049) — No puede realizar esta acción sobre su propia cuenta.
- [**P-USU-0038**](../modelo-dominio/Usuario.md#p-usu-0038) — Debe explicar por qué rechaza el registro.
- [**P-USU-0041**](../modelo-dominio/Usuario.md#p-usu-0041) — No se pudo rechazar la cuenta; se deshace toda la operación.
- [**P-VIN-0002**](../modelo-dominio/VinculacionAcademica.md#p-vin-0002) — No se pudieron cerrar las vinculaciones; se deshace toda la operación.
- [**P-AUD-0001**](../modelo-dominio/Auditoria.md#p-aud-0001) — No se pudo dejar la trazabilidad; se deshace toda la operación.

</details>

---

[🏠 Inicio](../../README.md) · [📑 Diagrama de clases](README.md) · [⬅️ Facultad](Facultad.md) · [ProgramaAcademico ➡️](ProgramaAcademico.md)<br>
**Ver este objeto en:** [🧩 Dominio](../modelo-dominio/Usuario.md) · [🗄️ MER](../mer/Usuario.md)
