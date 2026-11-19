# 🧩 Usuario

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ Facultad](Facultad.md) · [ProgramaAcademico ➡️](ProgramaAcademico.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/Usuario.md) · [📐 Clase](../clases/Usuario.md)

> Persona de la comunidad universitaria registrada en la plataforma con un rol institucional. Su cuenta solo está activa si fue aprobada y validó correo y celular. La aprobación o el rechazo de su registro lo decide la autoridad que corresponde a su rol; queda en el estado de la cuenta y en la Auditoría.

| Línea de vida | Atributos | Responsabilidades | Eventos | Políticas propias |
| :---: | :---: | :---: | :---: | :---: |
| Nivel 2 | 10 | 19 | 19 | 51 |

## Contenido

1. [Muestreo](#1-muestreo)
2. [Caracterización](#2-caracterización)
3. [Responsabilidades](#3-responsabilidades)
4. [Event Storming](#4-event-storming)
5. [Políticas](#5-políticas)

---

## 1. Muestreo

Ejemplos reales del objeto para validar sus atributos con el negocio.

| **id** | **nombre** | **apellido** | **correoInstitucional** | **celular** | **correoVerificado** | **celularVerificado** | **rol** | **estadoCuenta** | **fechaRegistro** | **Combinación única 1** | **Combinación única 2** |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | Sofía | Restrepo Álvarez | sofia.restrepo@uco.edu.co | 3104567821 | Si | Si | Administrador | Activa | 05/01/2026 08:00 | sofia.restrepo@uco.edu.co | 3104567821 |
| 2 | Jorge Hernán | Ramírez Ochoa | jorge.ramirez@uco.edu.co | 3157894512 | Si | Si | Rector | Activa | 06/01/2026 09:15 | jorge.ramirez@uco.edu.co | 3157894512 |
| 3 | Ana María | Gómez Arango | ana.gomez@uco.edu.co | 3006781234 | Si | Si | Coordinador Académico | Activa | 08/01/2026 10:00 | ana.gomez@uco.edu.co | 3006781234 |
| 4 | Carlos Andrés | Zapata Henao | carlos.zapata@uco.edu.co | 3128904567 | Si | Si | Coordinador de Facultad | Activa | 12/01/2026 07:45 | carlos.zapata@uco.edu.co | 3128904567 |
| 5 | Patricia | Henao Botero | patricia.henao@uco.edu.co | 3014569870 | Si | Si | Coordinador de Facultad | Activa | 12/01/2026 08:20 | patricia.henao@uco.edu.co | 3014569870 |
| 6 | Luisa Fernanda | Ospina Ríos | luisa.ospina@uco.edu.co | 3205671234 | Si | Si | Coordinador de Programa | Activa | 15/01/2026 09:00 | luisa.ospina@uco.edu.co | 3205671234 |
| 7 | Natalia | Quintero Gil | natalia.quintero@uco.edu.co | 3176543210 | Si | Si | Coordinador de Programa | Suspendida | 15/01/2026 09:30 | natalia.quintero@uco.edu.co | 3176543210 |
| 8 | Andrés Felipe | Cardona Marín | andres.cardona@uco.edu.co | 3112345678 | Si | Si | Docente | Activa | 20/01/2026 10:10 | andres.cardona@uco.edu.co | 3112345678 |
| 9 | Diego | Montoya Jaramillo | diego.montoya@uco.edu.co | 3189012345 | Si | Si | Docente | Activa | 20/01/2026 11:00 | diego.montoya@uco.edu.co | 3189012345 |
| 10 | Valentina | Arango Duque | valentina.arango@uco.edu.co | 3023456789 | Si | Si | Estudiante | Activa | 02/02/2026 14:00 | valentina.arango@uco.edu.co | 3023456789 |
| 11 | Mateo | Giraldo Castaño | mateo.giraldo@uco.edu.co | 3045678901 | Si | Si | Estudiante | Activa | 02/02/2026 14:30 | mateo.giraldo@uco.edu.co | 3045678901 |
| 12 | Sara | Valencia Toro | sara.valencia@uco.edu.co | 3216789012 | Si | No | Estudiante | Pendiente de aprobación | 10/11/2026 16:20 | sara.valencia@uco.edu.co | 3216789012 |
| 13 | Julián | Torres Mejía | julian.torres@uco.edu.co | 3051239876 | Si | Si | Estudiante | Rechazada | 03/11/2026 09:40 | julian.torres@uco.edu.co | 3051239876 |
| 14 | Camila | Rendón Osorio | camila.rendon@uco.edu.co | 3134567890 | Si | No | Estudiante | Aprobada | 05/11/2026 11:15 | camila.rendon@uco.edu.co | 3134567890 |

<details>
<summary><b>Significado de cada columna</b></summary>

| Columna | Representa |
| --- | --- |
| `id` | Representa el identificador único del usuario dentro del sistema. |
| `nombre` | Representa el nombre o los nombres de la persona. |
| `apellido` | Representa el apellido o los apellidos de la persona. |
| `correoInstitucional` | Representa el correo institucional con el que la persona se registra y se identifica. |
| `celular` | Representa el número de celular de la persona, usado para validar su identidad. |
| `correoVerificado` | Representa si la persona ya confirmó que el correo institucional le pertenece. |
| `celularVerificado` | Representa si la persona ya confirmó que el número celular le pertenece. |
| `rol` | Representa el rol institucional que ejerce la persona en la plataforma. |
| `estadoCuenta` | Representa en qué estado se encuentra la cuenta de la persona. |
| `fechaRegistro` | Representa la fecha y hora en que la persona se registró en la plataforma. |
| `Combinación única 1` | Representa que no pueden existir dos usuarios con el mismo correo institucional . |
| `Combinación única 2` | Representa que no pueden existir dos usuarios con el mismo número celular. |

</details>

---

## 2. Caracterización

### 2.1 Definición de los atributos

| Atributo | Descripción | Tipo de dato | Oblig. | Long. mín. | Long. máx. | Formato | Reglas especiales |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `id` 🔑 | Representa el identificador único del usuario dentro del sistema. | Identificador único universal | Si | No aplica | No aplica | No aplica | No aplica |
| `nombre` | Representa el nombre o los nombres de la persona. | Texto | Si | 2 | 60 | Primera letra de cada nombre en mayúscula. Ej: Luisa Fernanda | Solo se permiten carateres de letras mayusculas y minusculas con tildes y espacios. |
| `apellido` | Representa el apellido o los apellidos de la persona. | Texto | Si | 2 | 60 | Primera letra de cada apellido en mayúscula. Ej: Ospina Ríos | Solo se permiten carateres de letras mayusculas y minusculas con tildes y espacios. |
| `correoInstitucional` | Representa el correo institucional con el que la persona se registra y se identifica. | Alfanumérico | Si | 12 | 100 | usuario@uco.edu.co. Ej: luisa.ospina@uco.edu.co | Debe terminar en @uco.edu.co. Antes de la @ solo letras minúsculas, números, punto, guion y guion bajo. Es único en la plataforma. Si cambia, debe volver a validarse. |
| `celular` | Representa el número de celular de la persona, usado para validar su identidad. | Alfanumérico | Si | 10 | 10 | 3XXXXXXXXX (10 dígitos, sin espacios ni indicativo). Ej: 3104567821 | Solo dígitos; debe iniciar en 3 (celular colombiano). Debe ser único en la plataforma. Si cambia, debe volver a validarse. |
| `correoVerificado` | Representa si la persona ya confirmó que el correo institucional le pertenece. | Lógico | Si | No aplica | No aplica | No aplica | Empieza valiedo No y si se verifica su valor cambia a Si. |
| `celularVerificado` | Representa si la persona ya confirmó que el número celular le pertenece. | Lógico | Si | No aplica | No aplica | No aplica | Empieza valiedo No y si se verifica su valor cambia a Si. |
| `rol` | Representa el rol institucional que ejerce la persona en la plataforma. | [Rol](Rol.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado. |
| `estadoCuenta` | Representa en qué estado se encuentra la cuenta de la persona. | [EstadoCuenta](EstadoCuenta.md) | Si | No aplica | No aplica | No aplica | Aplican las reglas del objeto referenciado. |
| `fechaRegistro` | Representa la fecha y hora en que la persona se registró en la plataforma. | Fecha hora | Si | No aplica | No aplica | No aplica | La asigna el sistema al registrar la cuenta y no cambia. |

### 2.2 Tratamiento del dato

| Atributo | Precisión | Redondeo | Rango inicial | Rango final | Calculado | Ajuste | Carácter ajuste | Trim inicio | Trim fin | Sensible | Valor por defecto | Autogenerado |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `id` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | Si |
| `nombre` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |
| `apellido` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | No | Vacio | No |
| `correoInstitucional` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | Si | Vacio | No |
| `celular` | No aplica | No aplica | No aplica | No aplica | No | No | No aplica | Si | Si | Si | Vacio | No |
| `correoVerificado` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | No | No |
| `celularVerificado` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | No | No |
| `rol` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `estadoCuenta` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | UUID vacio | No |
| `fechaRegistro` | No aplica | No aplica | No aplica | No aplica | No | No aplica | No aplica | No aplica | No aplica | No | 01/01/1000 00:00 | Si |

### 2.3 Combinaciones únicas

| Combinación | Descripción | Atributos | Regla de comparación |
| --- | --- | --- | --- |
| Combinación única 1 | Representa que no pueden existir dos usuarios con el mismo correo institucional . | `correoInstitucional` | No distingue mayúsculas ni minúsculas; ignora espacios al inicio y al final. |
| Combinación única 2 | Representa que no pueden existir dos usuarios con el mismo número celular. | `celular` | Compara solo los 10 dígitos. |

### 2.4 Uso de los atributos en cada responsabilidad

> **R** requerido · **NR** no requerido · **M** modificable · **NM** no modificable · **👁** se muestra · **🚫** no se muestra · **—** no aplica

| Atributo | [`registrarUsuario`](#registrarusuario) | [`presentarNuevamenteRegistroUsuario`](#presentarnuevamenteregistrousuario) | [`registrarRector`](#registrarrector) | [`registrarAdministrador`](#registraradministrador) | [`confirmarCorreoUsuario`](#confirmarcorreousuario) | [`confirmarCelularUsuario`](#confirmarcelularusuario) | [`activarCuentaUsuario`](#activarcuentausuario) | [`consultarInformacionPersonalUsuario`](#consultarinformacionpersonalusuario) | [`consultarUsuarios`](#consultarusuarios) | [`cambiarNombreUsuario`](#cambiarnombreusuario) | [`cambiarCorreoUsuario`](#cambiarcorreousuario) | [`cambiarCelularUsuario`](#cambiarcelularusuario) | [`suspenderCuentaUsuario`](#suspendercuentausuario) | [`reactivarCuentaUsuario`](#reactivarcuentausuario) | [`desactivarCuentaUsuario`](#desactivarcuentausuario) | [`cambiarRolUsuario`](#cambiarrolusuario) | [`consultarUsuariosPendientesAprobacion`](#consultarusuariospendientesaprobacion) | [`aprobarUsuario`](#aprobarusuario) | [`rechazarUsuario`](#rechazarusuario) |
| --- | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| `id` | NR | R·NM | NR | NR | R·NM | R·NM | R·NM | 👁 | 👁 | R·NM | R·NM | R·NM | R·NM | R·NM | R·NM | R·NM | 👁 | R·NM | R·NM |
| `nombre` | R | NR·NM | R | R | NR·NM | NR·NM | NR·NM | 👁 | 👁 | R·M | NR·NM | NR·NM | NR·NM | NR·NM | NR·NM | NR·NM | 👁 | NR·NM | NR·NM |
| `apellido` | R | NR·NM | R | R | NR·NM | NR·NM | NR·NM | 👁 | 👁 | R·M | NR·NM | NR·NM | NR·NM | NR·NM | NR·NM | NR·NM | 👁 | NR·NM | NR·NM |
| `correoInstitucional` | R | NR·NM | R | R | NR·NM | NR·NM | NR·NM | 👁 | 🚫 | NR·NM | R·M | NR·NM | NR·NM | NR·NM | NR·NM | NR·NM | 👁 | NR·NM | NR·NM |
| `celular` | R | NR·NM | R | R | NR·NM | NR·NM | NR·NM | 👁 | 🚫 | NR·NM | NR·NM | R·M | NR·NM | NR·NM | NR·NM | NR·NM | 🚫 | NR·NM | NR·NM |
| `correoVerificado` | NR | NR·NM | NR | NR | NR·M | NR·NM | NR·NM | 👁 | 🚫 | NR·NM | NR·M | NR·NM | NR·NM | NR·NM | NR·NM | NR·NM | 🚫 | NR·NM | NR·NM |
| `celularVerificado` | NR | NR·NM | NR | NR | NR·NM | NR·M | NR·NM | 👁 | 🚫 | NR·NM | NR·NM | NR·M | NR·NM | NR·NM | NR·NM | NR·NM | 🚫 | NR·NM | NR·NM |
| `rol` | R | R·M | NR | NR | NR·NM | NR·NM | NR·NM | 👁 | 👁 | NR·NM | NR·NM | NR·NM | NR·NM | NR·NM | NR·NM | R·M | 👁 | NR·NM | NR·NM |
| `estadoCuenta` | NR | NR·M | NR | NR | NR·NM | NR·NM | NR·M | 👁 | 👁 | NR·NM | NR·M | NR·M | NR·M | NR·M | NR·M | NR·NM | 👁 | NR·M | NR·M |
| `fechaRegistro` | NR | NR·NM | NR | NR | NR·NM | NR·NM | NR·NM | 👁 | 🚫 | NR·NM | NR·NM | NR·NM | NR·NM | NR·NM | NR·NM | NR·NM | 👁 | NR·NM | NR·NM |

---

## 3. Responsabilidades

| # | Responsabilidad | Descripción |
| --- | --- | --- |
| 1 | [`registrarUsuario`](#registrarusuario) | Registrarse en la plataforma con correo institucional y celular, eligiendo el rol (Estudiante, Docente o un rol de coordinador). El estudiante y el docente indican sus programas y quedan vinculados a ellos. La cuenta queda Pendiente de aprobación hasta que la autoridad que corresponde a su rol la apruebe o la rechace (RF-001 a RF-005). |
| 2 | [`presentarNuevamenteRegistroUsuario`](#presentarnuevamenteregistrousuario) | Volver a presentar el registro después de un rechazo, con la misma cuenta (el correo sigue siendo único, RN-073). Solo se puede si la cuenta está Rechazada; puede cambiar el rol y los programas. La cuenta vuelve a Pendiente de aprobación. |
| 3 | [`registrarRector`](#registrarrector) | Registrar al Rector de la universidad. Solo lo hace un Administrador (RN-094) y la cuenta nace Aprobada; se activa cuando un Administrador confirme su correo y su celular. Solo puede haber un Rector vigente. |
| 4 | [`registrarAdministrador`](#registraradministrador) | Registrar a otro Administrador de la plataforma. Solo lo hace un Administrador (RN-095) y la cuenta nace Aprobada; se activa cuando otro Administrador confirme su correo y su celular. El primer Administrador se crea con los datos iniciales (seed). |
| 5 | [`confirmarCorreoUsuario`](#confirmarcorreousuario) | Confirmar que el correo institucional registrado pertenece a la persona (RF-006, RN-077, RN-079). Lo hace la autoridad que debe decidir su registro o que la gestiona; el del Rector y el de los Administradores lo confirma un Administrador. Si ya se cumplen las otras condiciones, la cuenta se activa. |
| 6 | [`confirmarCelularUsuario`](#confirmarcelularusuario) | Confirmar que el número celular registrado pertenece a la persona (RF-007, RN-078, RN-080). Lo hace la autoridad que debe decidir su registro o que la gestiona; el del Rector y el de los Administradores lo confirma un Administrador. Si ya se cumplen las otras condiciones, la cuenta se activa. |
| 7 | [`activarCuentaUsuario`](#activarcuentausuario) | Activar la cuenta cuando ya fue aprobada y tiene el correo y el celular confirmados. Lo hace el sistema apenas se cumple la última condición (RN-084 a RN-088). En la auditoría figura como usuario el titular de la cuenta. |
| 8 | [`consultarInformacionPersonalUsuario`](#consultarinformacionpersonalusuario) | Consultar la información personal de una persona y el estado de sus validaciones (RN-101, RN-103, RF-008). |
| 9 | [`consultarUsuarios`](#consultarusuarios) | Buscar personas de la comunidad, por ejemplo para elegir al destinatario de un Kudo o para saber quién es el Rector activo (RF-076, RF-077). No muestra correo ni celular. |
| 10 | [`cambiarNombreUsuario`](#cambiarnombreusuario) | Corregir el nombre y el apellido propios (RN-102, RN-109). |
| 11 | [`cambiarCorreoUsuario`](#cambiarcorreousuario) | Cambiar el correo institucional propio. El correo nuevo debe confirmarlo otra vez la autoridad que lo gestiona (RN-104, RN-110). |
| 12 | [`cambiarCelularUsuario`](#cambiarcelularusuario) | Cambiar el número celular propio. El celular nuevo debe confirmarlo otra vez la autoridad que lo gestiona (RN-105, RN-111). |
| 13 | [`suspenderCuentaUsuario`](#suspendercuentausuario) | Suspender temporalmente la cuenta de una persona a cargo; mientras tanto no puede ingresar. Si coordina una facultad o un programa, su coordinación termina y la unidad queda sin coordinador hasta que se asigne otro. Sus vinculaciones académicas no se tocan. |
| 14 | [`reactivarCuentaUsuario`](#reactivarcuentausuario) | Levantar la suspensión de una cuenta para que la persona vuelva a ingresar. Si era coordinador, su coordinación no vuelve sola: la autoridad debe asignarla de nuevo. No se reactiva a un Rector o Coordinador Académico si mientras tanto se registró otro. |
| 15 | [`desactivarCuentaUsuario`](#desactivarcuentausuario) | Desactivar definitivamente la cuenta de una persona a cargo. Su historia (Kudos, réplicas, auditoría) se conserva. Si coordina una facultad o un programa, su coordinación termina; si es estudiante o docente, sus vinculaciones vigentes se cierran con fecha. |
| 16 | [`cambiarRolUsuario`](#cambiarrolusuario) | Cambiar el rol institucional de una persona cuando cambia su cargo (por ejemplo, un coordinador que vuelve a ser docente). Nadie cambia su propio rol (RN-107). |
| 17 | [`consultarUsuariosPendientesAprobacion`](#consultarusuariospendientesaprobacion) | Consultar las personas registradas cuya cuenta está Pendiente de aprobación y que le corresponde decidir a quien consulta. |
| 18 | [`aprobarUsuario`](#aprobarusuario) | Aprobar el registro de un estudiante, docente, Coordinador de Facultad o Coordinador Académico (el aspirante a Coordinador de Programa se aprueba al asignarle un programa). Su cuenta pasa a Aprobada y se activa cuando además tenga correo y celular validados (RN-084, RN-096). Quién aprobó y cuándo quedan en la Auditoría. |
| 19 | [`rechazarUsuario`](#rechazarusuario) | Rechazar el registro de una persona explicando la razón. Su cuenta pasa a Rechazada y sus vinculaciones se cierran; quién rechazó, cuándo y por qué quedan en la Auditoría. La persona puede volver a presentar su registro. |

### `registrarUsuario`

Registrarse en la plataforma con correo institucional y celular, eligiendo el rol (Estudiante, Docente o un rol de coordinador). El estudiante y el docente indican sus programas y quedan vinculados a ellos. La cuenta queda Pendiente de aprobación hasta que la autoridad que corresponde a su rol la apruebe o la rechace (RF-001 a RF-005).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{nombre, apellido, correoInstitucional, celular, rol}` | [Usuario](Usuario.md) | Datos con los que la persona se registra. |
| `programas` | [Lista&lt;ProgramaAcademico&gt;](ProgramaAcademico.md) | Programas a los que pertenece; obligatorio para Estudiante y Docente. |

**Salida:** [Usuario](Usuario.md) — El usuario registrado, con la cuenta Pendiente de aprobación.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0015](#p-usu-0015) | Asegurar que el nombre, el apellido, el correo institucional, el celular y el rol sean válidos en tipo, longitud, obligatoriedad, formato y rango (RN-069 a RN-072). | Se informa cuál dato no cumple y cómo debe escribirse. | 🛑 Detener operación |
| 2 | [P-ROL-0001](Rol.md#p-rol-0001) | Asegurar que exista un rol con el identificador indicado. | No existe el rol que se indicó. | 🛑 Detener operación |
| 3 | [P-USU-0023](#p-usu-0023) | Asegurar que el rol elegido al registrarse sea Estudiante, Docente, Coordinador de Programa, Coordinador de Facultad o Coordinador Académico. El Rector y los Administradores no se registran solos: los registra un Administrador (RN-094, RN-095). | No puede registrarse con ese rol; el Rector y los Administradores los registra un Administrador. | 🛑 Detener operación |
| 4 | [P-USU-0026](#p-usu-0026) | Asegurar que, si el rol es Estudiante o Docente, se indique al menos un programa académico existente al que pertenece (RN-053, RN-061); con los roles de coordinador no se indican programas. | Debe indicar al menos un programa académico al que pertenece. | 🛑 Detener operación |
| 5 | [P-PRO-0001](ProgramaAcademico.md#p-pro-0001) | Asegurar que exista un programa académico con el identificador indicado. | No existe el programa académico que se indicó. | 🛑 Detener operación |
| 6 | [P-USU-0019](#p-usu-0019) | Asegurar que no exista otro usuario con el mismo correo institucional (RN-073). | Ya existe una cuenta con ese correo institucional. | 🛑 Detener operación |
| 7 | [P-USU-0020](#p-usu-0020) | Asegurar que no exista otro usuario con el mismo número celular (RN-074). | Ya existe una cuenta con ese número celular. | 🛑 Detener operación |
| 8 | [P-USU-0021](#p-usu-0021) | Asegurar que el identificador del usuario sea único dentro de los usuarios; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 9 | [P-USU-0022](#p-usu-0022) | Asignar a la cuenta nueva el estado Pendiente de aprobación, el correo y el celular como no verificados y la fecha de registro. | No se pudo registrar la cuenta; se deshace toda la operación. | 🛑 Detener operación |
| 10 | [P-VIN-0001](VinculacionAcademica.md#p-vin-0001) | Cuando un estudiante o docente se registre o vuelva a presentar su registro, crear una vinculación con cada programa que indicó, con la fecha de registro como fecha de vinculación (RN-053, RN-056, RN-061, RN-064). | No se pudieron crear las vinculaciones; se deshace toda la operación. | 🛑 Detener operación |
| 11 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `presentarNuevamenteRegistroUsuario`

Volver a presentar el registro después de un rechazo, con la misma cuenta (el correo sigue siendo único, RN-073). Solo se puede si la cuenta está Rechazada; puede cambiar el rol y los programas. La cuenta vuelve a Pendiente de aprobación.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la cuenta rechazada. |
| `datos{rol}` | [Usuario](Usuario.md) | Rol con el que vuelve a registrarse. |
| `programas` | [Lista&lt;ProgramaAcademico&gt;](ProgramaAcademico.md) | Programas a los que pertenece; obligatorio para Estudiante y Docente. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0008](#p-usu-0008) | Asegurar que exista un usuario con el identificador indicado. | No existe el usuario que se indicó. | 🛑 Detener operación |
| 2 | [P-USU-0027](#p-usu-0027) | Asegurar que la cuenta esté Rechazada para volver a presentar el registro; mientras esté Pendiente de aprobación no puede presentarlo otra vez. | Su registro todavía está pendiente de aprobación; espere la decisión. | 🛑 Detener operación |
| 3 | [P-ROL-0001](Rol.md#p-rol-0001) | Asegurar que exista un rol con el identificador indicado. | No existe el rol que se indicó. | 🛑 Detener operación |
| 4 | [P-USU-0023](#p-usu-0023) | Asegurar que el rol elegido al registrarse sea Estudiante, Docente, Coordinador de Programa, Coordinador de Facultad o Coordinador Académico. El Rector y los Administradores no se registran solos: los registra un Administrador (RN-094, RN-095). | No puede registrarse con ese rol; el Rector y los Administradores los registra un Administrador. | 🛑 Detener operación |
| 5 | [P-USU-0026](#p-usu-0026) | Asegurar que, si el rol es Estudiante o Docente, se indique al menos un programa académico existente al que pertenece (RN-053, RN-061); con los roles de coordinador no se indican programas. | Debe indicar al menos un programa académico al que pertenece. | 🛑 Detener operación |
| 6 | [P-PRO-0001](ProgramaAcademico.md#p-pro-0001) | Asegurar que exista un programa académico con el identificador indicado. | No existe el programa académico que se indicó. | 🛑 Detener operación |
| 7 | [P-USU-0028](#p-usu-0028) | Asignar a la cuenta el rol elegido y el estado Pendiente de aprobación. El rechazo anterior y su justificación quedan en la Auditoría. | No se pudo presentar de nuevo el registro; se deshace toda la operación. | 🛑 Detener operación |
| 8 | [P-VIN-0001](VinculacionAcademica.md#p-vin-0001) | Cuando un estudiante o docente se registre o vuelva a presentar su registro, crear una vinculación con cada programa que indicó, con la fecha de registro como fecha de vinculación (RN-053, RN-056, RN-061, RN-064). | No se pudieron crear las vinculaciones; se deshace toda la operación. | 🛑 Detener operación |
| 9 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `registrarRector`

Registrar al Rector de la universidad. Solo lo hace un Administrador (RN-094) y la cuenta nace Aprobada; se activa cuando un Administrador confirme su correo y su celular. Solo puede haber un Rector vigente.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{nombre, apellido, correoInstitucional, celular}` | [Usuario](Usuario.md) | Datos del Rector; el rol lo asigna el sistema. |

**Salida:** [Usuario](Usuario.md) — El Rector registrado, con la cuenta Aprobada.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0004](#p-usu-0004) | Asegurar que quien ejecuta la acción tenga el rol Administrador (RN-129 a RN-131). | Solo un Administrador puede realizar esta acción. | 🛑 Detener operación |
| 3 | [P-USU-0024](#p-usu-0024) | Asegurar que el nombre, el apellido, el correo institucional y el celular sean válidos en tipo, longitud, obligatoriedad y formato (RN-069 a RN-072). | Se informa cuál dato no cumple y cómo debe escribirse. | 🛑 Detener operación |
| 4 | [P-USU-0019](#p-usu-0019) | Asegurar que no exista otro usuario con el mismo correo institucional (RN-073). | Ya existe una cuenta con ese correo institucional. | 🛑 Detener operación |
| 5 | [P-USU-0020](#p-usu-0020) | Asegurar que no exista otro usuario con el mismo número celular (RN-074). | Ya existe una cuenta con ese número celular. | 🛑 Detener operación |
| 6 | [P-USU-0042](#p-usu-0042) | Asegurar que no exista otro Rector con cuenta Aprobada o Activa cuando la persona tenga el rol Rector (RN-005, INV-001). | Ya existe un Rector activo en la universidad. | 🛑 Detener operación |
| 7 | [P-USU-0021](#p-usu-0021) | Asegurar que el identificador del usuario sea único dentro de los usuarios; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 8 | [P-USU-0025](#p-usu-0025) | Asignar a la cuenta nueva el rol que corresponde a la responsabilidad (Rector o Administrador), el estado Aprobada (el registro hecho por un Administrador es la aprobación institucional, RN-085, RN-094, RN-095), el correo y el celular como no verificados y la fecha de registro. Se activa cuando se confirmen su correo y su celular. | No se pudo registrar la cuenta; se deshace toda la operación. | 🛑 Detener operación |
| 9 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `registrarAdministrador`

Registrar a otro Administrador de la plataforma. Solo lo hace un Administrador (RN-095) y la cuenta nace Aprobada; se activa cuando otro Administrador confirme su correo y su celular. El primer Administrador se crea con los datos iniciales (seed).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `datos{nombre, apellido, correoInstitucional, celular}` | [Usuario](Usuario.md) | Datos del Administrador nuevo; el rol lo asigna el sistema. |

**Salida:** [Usuario](Usuario.md) — El Administrador registrado, con la cuenta Aprobada.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0004](#p-usu-0004) | Asegurar que quien ejecuta la acción tenga el rol Administrador (RN-129 a RN-131). | Solo un Administrador puede realizar esta acción. | 🛑 Detener operación |
| 3 | [P-USU-0024](#p-usu-0024) | Asegurar que el nombre, el apellido, el correo institucional y el celular sean válidos en tipo, longitud, obligatoriedad y formato (RN-069 a RN-072). | Se informa cuál dato no cumple y cómo debe escribirse. | 🛑 Detener operación |
| 4 | [P-USU-0019](#p-usu-0019) | Asegurar que no exista otro usuario con el mismo correo institucional (RN-073). | Ya existe una cuenta con ese correo institucional. | 🛑 Detener operación |
| 5 | [P-USU-0020](#p-usu-0020) | Asegurar que no exista otro usuario con el mismo número celular (RN-074). | Ya existe una cuenta con ese número celular. | 🛑 Detener operación |
| 6 | [P-USU-0021](#p-usu-0021) | Asegurar que el identificador del usuario sea único dentro de los usuarios; si ya existe, generar otro. | No se informa al actor: el sistema genera un nuevo identificador. | ▶️ Continuar operación |
| 7 | [P-USU-0025](#p-usu-0025) | Asignar a la cuenta nueva el rol que corresponde a la responsabilidad (Rector o Administrador), el estado Aprobada (el registro hecho por un Administrador es la aprobación institucional, RN-085, RN-094, RN-095), el correo y el celular como no verificados y la fecha de registro. Se activa cuando se confirmen su correo y su celular. | No se pudo registrar la cuenta; se deshace toda la operación. | 🛑 Detener operación |
| 8 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `confirmarCorreoUsuario`

Confirmar que el correo institucional registrado pertenece a la persona (RF-006, RN-077, RN-079). Lo hace la autoridad que debe decidir su registro o que la gestiona; el del Rector y el de los Administradores lo confirma un Administrador. Si ya se cumplen las otras condiciones, la cuenta se activa.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la persona cuyo correo se confirma. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0008](#p-usu-0008) | Asegurar que exista un usuario con el identificador indicado. | No existe el usuario que se indicó. | 🛑 Detener operación |
| 3 | [P-USU-0049](#p-usu-0049) | Asegurar que quien ejecuta la acción no sea la misma persona afectada (RN-107, RN-108). | No puede realizar esta acción sobre su propia cuenta. | 🛑 Detener operación |
| 4 | [P-USU-0031](#p-usu-0031) | Asegurar que quien confirma el correo institucional o el celular sea la autoridad que corresponde a la persona (RN-077, RN-078): mientras su registro está Pendiente de aprobación, la que debe decidirlo (Estudiante y Docente ← Coordinador de Programa con coordinación vigente de alguno de sus programas; aspirante a Coordinador de Programa ← cualquier Coordinador de Facultad con coordinación vigente; Coordinador de Facultad ← Coordinador Académico; Coordinador Académico ← Rector); después, la que la gestiona (RN-112 a RN-129). El correo y el celular del Rector y de los Administradores los confirma un Administrador (RN-129). No se confirman datos de una cuenta Rechazada o Desactivada. | A usted no le corresponde confirmar los datos de contacto de esta persona. | 🛑 Detener operación |
| 5 | [P-USU-0032](#p-usu-0032) | Asegurar que el correo institucional aún no esté verificado. | El correo ya estaba verificado. | 🛑 Detener operación |
| 6 | [P-USU-0034](#p-usu-0034) | Cuando la cuenta esté Aprobada y tenga el correo y el celular verificados, activarla (RN-084 a RN-088). | No se pudo activar la cuenta; se deshace toda la operación. | 🛑 Detener operación |
| 7 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `confirmarCelularUsuario`

Confirmar que el número celular registrado pertenece a la persona (RF-007, RN-078, RN-080). Lo hace la autoridad que debe decidir su registro o que la gestiona; el del Rector y el de los Administradores lo confirma un Administrador. Si ya se cumplen las otras condiciones, la cuenta se activa.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la persona cuyo celular se confirma. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0008](#p-usu-0008) | Asegurar que exista un usuario con el identificador indicado. | No existe el usuario que se indicó. | 🛑 Detener operación |
| 3 | [P-USU-0049](#p-usu-0049) | Asegurar que quien ejecuta la acción no sea la misma persona afectada (RN-107, RN-108). | No puede realizar esta acción sobre su propia cuenta. | 🛑 Detener operación |
| 4 | [P-USU-0031](#p-usu-0031) | Asegurar que quien confirma el correo institucional o el celular sea la autoridad que corresponde a la persona (RN-077, RN-078): mientras su registro está Pendiente de aprobación, la que debe decidirlo (Estudiante y Docente ← Coordinador de Programa con coordinación vigente de alguno de sus programas; aspirante a Coordinador de Programa ← cualquier Coordinador de Facultad con coordinación vigente; Coordinador de Facultad ← Coordinador Académico; Coordinador Académico ← Rector); después, la que la gestiona (RN-112 a RN-129). El correo y el celular del Rector y de los Administradores los confirma un Administrador (RN-129). No se confirman datos de una cuenta Rechazada o Desactivada. | A usted no le corresponde confirmar los datos de contacto de esta persona. | 🛑 Detener operación |
| 5 | [P-USU-0033](#p-usu-0033) | Asegurar que el celular aún no esté verificado. | El celular ya estaba verificado. | 🛑 Detener operación |
| 6 | [P-USU-0034](#p-usu-0034) | Cuando la cuenta esté Aprobada y tenga el correo y el celular verificados, activarla (RN-084 a RN-088). | No se pudo activar la cuenta; se deshace toda la operación. | 🛑 Detener operación |
| 7 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `activarCuentaUsuario`

Activar la cuenta cuando ya fue aprobada y tiene el correo y el celular confirmados. Lo hace el sistema apenas se cumple la última condición (RN-084 a RN-088). En la auditoría figura como usuario el titular de la cuenta.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador del usuario. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0008](#p-usu-0008) | Asegurar que exista un usuario con el identificador indicado. | No existe el usuario que se indicó. | 🛑 Detener operación |
| 2 | [P-USU-0035](#p-usu-0035) | Asegurar que la cuenta esté Aprobada y tenga el correo y el celular verificados antes de activarla (RN-085 a RN-088). | La cuenta aún no cumple las tres condiciones para activarse. | 🛑 Detener operación |
| 3 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `consultarInformacionPersonalUsuario`

Consultar la información personal de una persona y el estado de sus validaciones (RN-101, RN-103, RF-008).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador del usuario. |

**Salida:** [Usuario](Usuario.md) — La información personal completa.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0008](#p-usu-0008) | Asegurar que exista un usuario con el identificador indicado. | No existe el usuario que se indicó. | 🛑 Detener operación |
| 3 | [P-USU-0014](#p-usu-0014) | Asegurar que quien consulta sea el titular de la cuenta o la autoridad que lo gestiona (RN-101, RN-112 a RN-129). | No tiene permiso para consultar la información personal de esta persona. | 🛑 Detener operación |

### `consultarUsuarios`

Buscar personas de la comunidad, por ejemplo para elegir al destinatario de un Kudo o para saber quién es el Rector activo (RF-076, RF-077). No muestra correo ni celular.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{nombre, apellido, rol, estadoCuenta}` | [Usuario](Usuario.md) | Datos opcionales para filtrar la consulta. |

**Salida:** [Lista&lt;Usuario&gt;](Usuario.md) — Las personas que cumplen el filtro, sin datos sensibles.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |

### `cambiarNombreUsuario`

Corregir el nombre y el apellido propios (RN-102, RN-109).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador del usuario. |
| `datos{nombre, apellido}` | [Usuario](Usuario.md) | Nombre y apellido nuevos. |

**Salida:** [Usuario](Usuario.md) — El usuario con sus datos nuevos.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0008](#p-usu-0008) | Asegurar que exista un usuario con el identificador indicado. | No existe el usuario que se indicó. | 🛑 Detener operación |
| 3 | [P-USU-0013](#p-usu-0013) | Asegurar que quien ejecuta la acción sea el titular de la cuenta (RN-101 a RN-105). | Solo puede realizar esta acción sobre su propia cuenta. | 🛑 Detener operación |
| 4 | [P-USU-0016](#p-usu-0016) | Asegurar que el nombre y el apellido sean válidos en tipo, longitud, obligatoriedad y formato. | Se informa cuál dato no cumple y cómo debe escribirse. | 🛑 Detener operación |
| 5 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `cambiarCorreoUsuario`

Cambiar el correo institucional propio. El correo nuevo debe confirmarlo otra vez la autoridad que lo gestiona (RN-104, RN-110).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador del usuario. |
| `datos{correoInstitucional}` | [Usuario](Usuario.md) | Correo nuevo. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0008](#p-usu-0008) | Asegurar que exista un usuario con el identificador indicado. | No existe el usuario que se indicó. | 🛑 Detener operación |
| 3 | [P-USU-0013](#p-usu-0013) | Asegurar que quien ejecuta la acción sea el titular de la cuenta (RN-101 a RN-105). | Solo puede realizar esta acción sobre su propia cuenta. | 🛑 Detener operación |
| 4 | [P-USU-0017](#p-usu-0017) | Asegurar que el correo institucional sea válido en tipo, longitud, obligatoriedad y formato (termina en @uco.edu.co). | El correo debe ser institucional y terminar en @uco.edu.co. | 🛑 Detener operación |
| 5 | [P-USU-0019](#p-usu-0019) | Asegurar que no exista otro usuario con el mismo correo institucional (RN-073). | Ya existe una cuenta con ese correo institucional. | 🛑 Detener operación |
| 6 | [P-USU-0044](#p-usu-0044) | Cuando cambie el correo institucional, marcarlo como no verificado y devolver la cuenta a Aprobada hasta que la autoridad lo confirme de nuevo (RN-081, RN-110). | No se pudo actualizar el correo; se deshace toda la operación. | 🛑 Detener operación |
| 7 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `cambiarCelularUsuario`

Cambiar el número celular propio. El celular nuevo debe confirmarlo otra vez la autoridad que lo gestiona (RN-105, RN-111).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador del usuario. |
| `datos{celular}` | [Usuario](Usuario.md) | Celular nuevo. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0008](#p-usu-0008) | Asegurar que exista un usuario con el identificador indicado. | No existe el usuario que se indicó. | 🛑 Detener operación |
| 3 | [P-USU-0013](#p-usu-0013) | Asegurar que quien ejecuta la acción sea el titular de la cuenta (RN-101 a RN-105). | Solo puede realizar esta acción sobre su propia cuenta. | 🛑 Detener operación |
| 4 | [P-USU-0018](#p-usu-0018) | Asegurar que el celular sea válido en tipo, longitud, obligatoriedad y formato (10 dígitos que inician en 3). | El celular debe tener 10 dígitos e iniciar en 3. | 🛑 Detener operación |
| 5 | [P-USU-0020](#p-usu-0020) | Asegurar que no exista otro usuario con el mismo número celular (RN-074). | Ya existe una cuenta con ese número celular. | 🛑 Detener operación |
| 6 | [P-USU-0045](#p-usu-0045) | Cuando cambie el celular, marcarlo como no verificado y devolver la cuenta a Aprobada hasta que la autoridad lo confirme de nuevo (RN-082, RN-111). | No se pudo actualizar el celular; se deshace toda la operación. | 🛑 Detener operación |
| 7 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `suspenderCuentaUsuario`

Suspender temporalmente la cuenta de una persona a cargo; mientras tanto no puede ingresar. Si coordina una facultad o un programa, su coordinación termina y la unidad queda sin coordinador hasta que se asigne otro. Sus vinculaciones académicas no se tocan.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador del usuario afectado. |
| `justificacion` | Alfanumérico | Razón de la suspensión; queda en el detalle de la auditoría. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0008](#p-usu-0008) | Asegurar que exista un usuario con el identificador indicado. | No existe el usuario que se indicó. | 🛑 Detener operación |
| 3 | [P-USU-0049](#p-usu-0049) | Asegurar que quien ejecuta la acción no sea la misma persona afectada (RN-107, RN-108). | No puede realizar esta acción sobre su propia cuenta. | 🛑 Detener operación |
| 4 | [P-USU-0012](#p-usu-0012) | Asegurar que quien ejecuta la acción sea la autoridad que gestiona el rol de la persona afectada y que esta pertenezca a su ámbito (RN-112 a RN-129). El ámbito de un Coordinador de Programa o de Facultad es la unidad que coordina con una coordinación vigente; sin ella no gestiona a nadie. | Usted no es la autoridad que gestiona a esta persona. | 🛑 Detener operación |
| 5 | [P-USU-0046](#p-usu-0046) | Asegurar que la cuenta afectada esté Activa antes de suspenderla. | Solo se puede suspender una cuenta activa. | 🛑 Detener operación |
| 6 | [P-COF-0001](CoordinacionFacultad.md#p-cof-0001) | Cuando se suspenda o se desactive la cuenta de quien coordina la facultad, asignar a su coordinación vigente la fecha de fin: deja de estar vigente y la persona no puede usar sus responsabilidades de coordinador hasta que le asignen una coordinación de nuevo. | No se pudo finalizar la coordinación; se deshace toda la operación. | 🛑 Detener operación |
| 7 | [P-COP-0001](CoordinacionPrograma.md#p-cop-0001) | Cuando se suspenda o se desactive la cuenta de quien coordina el programa, asignar a su coordinación vigente la fecha de fin: deja de estar vigente y la persona no puede usar sus responsabilidades de coordinador hasta que le asignen una coordinación de nuevo. | No se pudo finalizar la coordinación; se deshace toda la operación. | 🛑 Detener operación |
| 8 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `reactivarCuentaUsuario`

Levantar la suspensión de una cuenta para que la persona vuelva a ingresar. Si era coordinador, su coordinación no vuelve sola: la autoridad debe asignarla de nuevo. No se reactiva a un Rector o Coordinador Académico si mientras tanto se registró otro.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador del usuario afectado. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0008](#p-usu-0008) | Asegurar que exista un usuario con el identificador indicado. | No existe el usuario que se indicó. | 🛑 Detener operación |
| 3 | [P-USU-0049](#p-usu-0049) | Asegurar que quien ejecuta la acción no sea la misma persona afectada (RN-107, RN-108). | No puede realizar esta acción sobre su propia cuenta. | 🛑 Detener operación |
| 4 | [P-USU-0012](#p-usu-0012) | Asegurar que quien ejecuta la acción sea la autoridad que gestiona el rol de la persona afectada y que esta pertenezca a su ámbito (RN-112 a RN-129). El ámbito de un Coordinador de Programa o de Facultad es la unidad que coordina con una coordinación vigente; sin ella no gestiona a nadie. | Usted no es la autoridad que gestiona a esta persona. | 🛑 Detener operación |
| 5 | [P-USU-0047](#p-usu-0047) | Asegurar que la cuenta afectada esté Suspendida antes de reactivarla. | Solo se puede reactivar una cuenta suspendida. | 🛑 Detener operación |
| 6 | [P-USU-0042](#p-usu-0042) | Asegurar que no exista otro Rector con cuenta Aprobada o Activa cuando la persona tenga el rol Rector (RN-005, INV-001). | Ya existe un Rector activo en la universidad. | 🛑 Detener operación |
| 7 | [P-USU-0043](#p-usu-0043) | Asegurar que no exista otro Coordinador Académico con cuenta Aprobada o Activa cuando la persona tenga ese rol (RN-011, INV-002). | Ya existe un Coordinador Académico activo en la universidad. | 🛑 Detener operación |
| 8 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `desactivarCuentaUsuario`

Desactivar definitivamente la cuenta de una persona a cargo. Su historia (Kudos, réplicas, auditoría) se conserva. Si coordina una facultad o un programa, su coordinación termina; si es estudiante o docente, sus vinculaciones vigentes se cierran con fecha.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador del usuario afectado. |
| `justificacion` | Alfanumérico | Razón de la desactivación; queda en el detalle de la auditoría. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0008](#p-usu-0008) | Asegurar que exista un usuario con el identificador indicado. | No existe el usuario que se indicó. | 🛑 Detener operación |
| 3 | [P-USU-0049](#p-usu-0049) | Asegurar que quien ejecuta la acción no sea la misma persona afectada (RN-107, RN-108). | No puede realizar esta acción sobre su propia cuenta. | 🛑 Detener operación |
| 4 | [P-USU-0012](#p-usu-0012) | Asegurar que quien ejecuta la acción sea la autoridad que gestiona el rol de la persona afectada y que esta pertenezca a su ámbito (RN-112 a RN-129). El ámbito de un Coordinador de Programa o de Facultad es la unidad que coordina con una coordinación vigente; sin ella no gestiona a nadie. | Usted no es la autoridad que gestiona a esta persona. | 🛑 Detener operación |
| 5 | [P-USU-0048](#p-usu-0048) | Asegurar que la cuenta afectada esté Activa o Suspendida antes de desactivarla. | La cuenta ya está desactivada o nunca fue activada. | 🛑 Detener operación |
| 6 | [P-COF-0001](CoordinacionFacultad.md#p-cof-0001) | Cuando se suspenda o se desactive la cuenta de quien coordina la facultad, asignar a su coordinación vigente la fecha de fin: deja de estar vigente y la persona no puede usar sus responsabilidades de coordinador hasta que le asignen una coordinación de nuevo. | No se pudo finalizar la coordinación; se deshace toda la operación. | 🛑 Detener operación |
| 7 | [P-COP-0001](CoordinacionPrograma.md#p-cop-0001) | Cuando se suspenda o se desactive la cuenta de quien coordina el programa, asignar a su coordinación vigente la fecha de fin: deja de estar vigente y la persona no puede usar sus responsabilidades de coordinador hasta que le asignen una coordinación de nuevo. | No se pudo finalizar la coordinación; se deshace toda la operación. | 🛑 Detener operación |
| 8 | [P-VIN-0002](VinculacionAcademica.md#p-vin-0002) | Cuando se rechace el registro o se desactive la cuenta de un estudiante o docente, asignar la fecha de desvinculación a todas sus vinculaciones vigentes; el vínculo no se borra (RN-057, RN-065). Al suspender la cuenta no se tocan. | No se pudieron cerrar las vinculaciones; se deshace toda la operación. | 🛑 Detener operación |
| 9 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `cambiarRolUsuario`

Cambiar el rol institucional de una persona cuando cambia su cargo (por ejemplo, un coordinador que vuelve a ser docente). Nadie cambia su propio rol (RN-107).

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador del usuario afectado. |
| `datos{rol}` | [Usuario](Usuario.md) | Rol nuevo. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0008](#p-usu-0008) | Asegurar que exista un usuario con el identificador indicado. | No existe el usuario que se indicó. | 🛑 Detener operación |
| 3 | [P-USU-0049](#p-usu-0049) | Asegurar que quien ejecuta la acción no sea la misma persona afectada (RN-107, RN-108). | No puede realizar esta acción sobre su propia cuenta. | 🛑 Detener operación |
| 4 | [P-USU-0012](#p-usu-0012) | Asegurar que quien ejecuta la acción sea la autoridad que gestiona el rol de la persona afectada y que esta pertenezca a su ámbito (RN-112 a RN-129). El ámbito de un Coordinador de Programa o de Facultad es la unidad que coordina con una coordinación vigente; sin ella no gestiona a nadie. | Usted no es la autoridad que gestiona a esta persona. | 🛑 Detener operación |
| 5 | [P-ROL-0001](Rol.md#p-rol-0001) | Asegurar que exista un rol con el identificador indicado. | No existe el rol que se indicó. | 🛑 Detener operación |
| 6 | [P-USU-0051](#p-usu-0051) | Asegurar que el rol nuevo sea distinto al rol actual de la persona. | La persona ya tiene ese rol. | 🛑 Detener operación |
| 7 | [P-USU-0050](#p-usu-0050) | Asegurar que la persona no tenga una coordinación ni una vinculación académica vigente antes de cambiar su rol (RN-001 a RN-003). | Primero debe cerrarse la coordinación o la vinculación vigente de la persona. | 🛑 Detener operación |
| 8 | [P-USU-0042](#p-usu-0042) | Asegurar que no exista otro Rector con cuenta Aprobada o Activa cuando la persona tenga el rol Rector (RN-005, INV-001). | Ya existe un Rector activo en la universidad. | 🛑 Detener operación |
| 9 | [P-USU-0043](#p-usu-0043) | Asegurar que no exista otro Coordinador Académico con cuenta Aprobada o Activa cuando la persona tenga ese rol (RN-011, INV-002). | Ya existe un Coordinador Académico activo en la universidad. | 🛑 Detener operación |
| 10 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `consultarUsuariosPendientesAprobacion`

Consultar las personas registradas cuya cuenta está Pendiente de aprobación y que le corresponde decidir a quien consulta.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `filtro{rol, estadoCuenta}` | [Usuario](Usuario.md) | Rol de las personas; el estado de cuenta se fija en Pendiente de aprobación. |

**Salida:** [Lista&lt;Usuario&gt;](Usuario.md) — Las personas pendientes a cargo de quien consulta.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0039](#p-usu-0039) | Asegurar que cada autoridad vea solo los registros pendientes que le corresponde decidir: el Coordinador de Programa, los estudiantes y docentes vinculados a su programa; el Coordinador de Facultad, los aspirantes a Coordinador de Programa; el Coordinador Académico, los aspirantes a Coordinador de Facultad; el Rector, los aspirantes a Coordinador Académico. Un coordinador sin coordinación vigente no tiene registros a su cargo. | No tiene registros pendientes a su cargo. | 🛑 Detener operación |

### `aprobarUsuario`

Aprobar el registro de un estudiante, docente, Coordinador de Facultad o Coordinador Académico (el aspirante a Coordinador de Programa se aprueba al asignarle un programa). Su cuenta pasa a Aprobada y se activa cuando además tenga correo y celular validados (RN-084, RN-096). Quién aprobó y cuándo quedan en la Auditoría.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la persona registrada. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0008](#p-usu-0008) | Asegurar que exista un usuario con el identificador indicado. | No existe el usuario que se indicó. | 🛑 Detener operación |
| 3 | [P-USU-0036](#p-usu-0036) | Asegurar que la cuenta afectada esté Pendiente de aprobación; un registro se decide una sola vez. | El registro de esta persona ya fue decidido. | 🛑 Detener operación |
| 4 | [P-USU-0037](#p-usu-0037) | Asegurar que quien decide sea la autoridad que corresponde al rol de la persona registrada: Estudiante y Docente ← Coordinador de Programa con coordinación vigente de alguno de los programas a los que la persona se vinculó (basta uno: la cuenta queda aprobada para todos sus programas); Coordinador de Facultad ← Coordinador Académico; Coordinador Académico ← Rector (RN-090, RN-092, RN-093). El aspirante a Coordinador de Programa se aprueba al asignarle un programa (RN-091) y lo puede rechazar cualquier Coordinador de Facultad con coordinación vigente. El Rector y los Administradores no pasan por aprobación (RN-094, RN-095). | A usted no le corresponde decidir el registro de esta persona. | 🛑 Detener operación |
| 5 | [P-USU-0049](#p-usu-0049) | Asegurar que quien ejecuta la acción no sea la misma persona afectada (RN-107, RN-108). | No puede realizar esta acción sobre su propia cuenta. | 🛑 Detener operación |
| 6 | [P-USU-0042](#p-usu-0042) | Asegurar que no exista otro Rector con cuenta Aprobada o Activa cuando la persona tenga el rol Rector (RN-005, INV-001). | Ya existe un Rector activo en la universidad. | 🛑 Detener operación |
| 7 | [P-USU-0043](#p-usu-0043) | Asegurar que no exista otro Coordinador Académico con cuenta Aprobada o Activa cuando la persona tenga ese rol (RN-011, INV-002). | Ya existe un Coordinador Académico activo en la universidad. | 🛑 Detener operación |
| 8 | [P-USU-0040](#p-usu-0040) | Asignar a la cuenta el estado Aprobada. Quién aprobó y cuándo quedan en la Auditoría (RN-096). | No se pudo aprobar la cuenta; se deshace toda la operación. | 🛑 Detener operación |
| 9 | [P-USU-0034](#p-usu-0034) | Cuando la cuenta esté Aprobada y tenga el correo y el celular verificados, activarla (RN-084 a RN-088). | No se pudo activar la cuenta; se deshace toda la operación. | 🛑 Detener operación |
| 10 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

### `rechazarUsuario`

Rechazar el registro de una persona explicando la razón. Su cuenta pasa a Rechazada y sus vinculaciones se cierran; quién rechazó, cuándo y por qué quedan en la Auditoría. La persona puede volver a presentar su registro.

**Entradas**

| Parámetro | Tipo de dato | Descripción |
| --- | --- | --- |
| `id` | Identificador único universal | Identificador de la persona registrada. |
| `justificacion` | Alfanumérico | Razón del rechazo; queda en el detalle de la auditoría. |

**Salida:** No aplica — No retorna ningún valor. En caso de error genera una excepción.

**Políticas que se aplican (en orden)**

| # | Política | Descripción | Excepción / mensaje | ¿Qué hago? |
| --- | --- | --- | --- | --- |
| 1 | [P-USU-0001](#p-usu-0001) | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Su cuenta no está activa, por lo que no puede realizar esta acción. | 🛑 Detener operación |
| 2 | [P-USU-0008](#p-usu-0008) | Asegurar que exista un usuario con el identificador indicado. | No existe el usuario que se indicó. | 🛑 Detener operación |
| 3 | [P-USU-0036](#p-usu-0036) | Asegurar que la cuenta afectada esté Pendiente de aprobación; un registro se decide una sola vez. | El registro de esta persona ya fue decidido. | 🛑 Detener operación |
| 4 | [P-USU-0037](#p-usu-0037) | Asegurar que quien decide sea la autoridad que corresponde al rol de la persona registrada: Estudiante y Docente ← Coordinador de Programa con coordinación vigente de alguno de los programas a los que la persona se vinculó (basta uno: la cuenta queda aprobada para todos sus programas); Coordinador de Facultad ← Coordinador Académico; Coordinador Académico ← Rector (RN-090, RN-092, RN-093). El aspirante a Coordinador de Programa se aprueba al asignarle un programa (RN-091) y lo puede rechazar cualquier Coordinador de Facultad con coordinación vigente. El Rector y los Administradores no pasan por aprobación (RN-094, RN-095). | A usted no le corresponde decidir el registro de esta persona. | 🛑 Detener operación |
| 5 | [P-USU-0049](#p-usu-0049) | Asegurar que quien ejecuta la acción no sea la misma persona afectada (RN-107, RN-108). | No puede realizar esta acción sobre su propia cuenta. | 🛑 Detener operación |
| 6 | [P-USU-0038](#p-usu-0038) | Asegurar que la justificación del rechazo sea válida en longitud y formato (entre 10 y 200 caracteres), porque se guarda en el detalle de la auditoría. | Debe explicar por qué rechaza el registro. | 🛑 Detener operación |
| 7 | [P-USU-0041](#p-usu-0041) | Asignar a la cuenta el estado Rechazada. Quién rechazó, cuándo y por qué quedan en la Auditoría. | No se pudo rechazar la cuenta; se deshace toda la operación. | 🛑 Detener operación |
| 8 | [P-VIN-0002](VinculacionAcademica.md#p-vin-0002) | Cuando se rechace el registro o se desactive la cuenta de un estudiante o docente, asignar la fecha de desvinculación a todas sus vinculaciones vigentes; el vínculo no se borra (RN-057, RN-065). Al suspender la cuenta no se tocan. | No se pudieron cerrar las vinculaciones; se deshace toda la operación. | 🛑 Detener operación |
| 9 | [P-AUD-0001](Auditoria.md#p-aud-0001) | Registrar en la Auditoría el evento ocurrido: quién lo ejecutó, sobre qué registro, el detalle (valor anterior → valor nuevo y justificación) y la fecha y hora (RN-096 a RN-100, RN-249). | No se pudo dejar la trazabilidad; se deshace toda la operación. | 🛑 Detener operación |

---

## 4. Event Storming

| Actor | Acción | Información externa (Read Model) | Políticas | Sistema externo | Evento | Aspectos por solucionar |
| --- | --- | --- | --- | --- | --- | --- |
| Persona que se registra | **Registrarse en la plataforma**<br>Registrarse en la plataforma con correo institucional y celular, eligiendo el rol (Estudiante, Docente o un rol de coordinador). El estudiante y el docente indican sus programas y quedan vinculados a ellos. La cuenta queda Pendiente de aprobación hasta que la autoridad que corresponde a su rol la apruebe o la rechace (RF-001 a RF-005). | **Roles que permiten registro propio**: Para elegir el rol con el que se registra (no aparecen Rector ni Administrador).<br>**Programas académicos**: Para que el estudiante o docente elija a cuáles pertenece. | [P-USU-0015](#p-usu-0015)<br>[P-ROL-0001](Rol.md#p-rol-0001)<br>[P-USU-0023](#p-usu-0023)<br>[P-USU-0026](#p-usu-0026)<br>[P-PRO-0001](ProgramaAcademico.md#p-pro-0001)<br>[P-USU-0019](#p-usu-0019)<br>[P-USU-0020](#p-usu-0020)<br>[P-USU-0021](#p-usu-0021)<br>[P-USU-0022](#p-usu-0022)<br>[P-VIN-0001](VinculacionAcademica.md#p-vin-0001)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Usuario registrado** | Ninguno |
| Persona con registro rechazado | **Presentar de nuevo el registro**<br>Volver a presentar el registro después de un rechazo, con la misma cuenta (el correo sigue siendo único, RN-073). Solo se puede si la cuenta está Rechazada; puede cambiar el rol y los programas. La cuenta vuelve a Pendiente de aprobación. | **Justificación del rechazo anterior**: Para que la persona sepa qué corregir (está en la Auditoría).<br>**Programas académicos**: Para volver a elegirlos. | [P-USU-0008](#p-usu-0008)<br>[P-USU-0027](#p-usu-0027)<br>[P-ROL-0001](Rol.md#p-rol-0001)<br>[P-USU-0023](#p-usu-0023)<br>[P-USU-0026](#p-usu-0026)<br>[P-PRO-0001](ProgramaAcademico.md#p-pro-0001)<br>[P-USU-0028](#p-usu-0028)<br>[P-VIN-0001](VinculacionAcademica.md#p-vin-0001)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Registro presentado nuevamente** | Ninguno |
| Administrador | **Registrar al Rector**<br>Registrar al Rector de la universidad. Solo lo hace un Administrador (RN-094) y la cuenta nace Aprobada; se activa cuando un Administrador confirme su correo y su celular. Solo puede haber un Rector vigente. | **Rector vigente**: Para comprobar que no exista otro Rector con cuenta Aprobada o Activa. | [P-USU-0001](#p-usu-0001)<br>[P-USU-0004](#p-usu-0004)<br>[P-USU-0024](#p-usu-0024)<br>[P-USU-0019](#p-usu-0019)<br>[P-USU-0020](#p-usu-0020)<br>[P-USU-0042](#p-usu-0042)<br>[P-USU-0021](#p-usu-0021)<br>[P-USU-0025](#p-usu-0025)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Rector registrado** | Ninguno |
| Administrador | **Registrar a un Administrador**<br>Registrar a otro Administrador de la plataforma. Solo lo hace un Administrador (RN-095) y la cuenta nace Aprobada; se activa cuando otro Administrador confirme su correo y su celular. El primer Administrador se crea con los datos iniciales (seed). | **No aplica**: No aplica | [P-USU-0001](#p-usu-0001)<br>[P-USU-0004](#p-usu-0004)<br>[P-USU-0024](#p-usu-0024)<br>[P-USU-0019](#p-usu-0019)<br>[P-USU-0020](#p-usu-0020)<br>[P-USU-0021](#p-usu-0021)<br>[P-USU-0025](#p-usu-0025)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Administrador registrado** | Ninguno |
| Administrador<br>Rector<br>Coordinador Académico<br>Coordinador de Facultad<br>Coordinador de Programa | **Confirmar el correo institucional de la persona**<br>Confirmar que el correo institucional registrado pertenece a la persona (RF-006, RN-077, RN-079). Lo hace la autoridad que debe decidir su registro o que la gestiona; el del Rector y el de los Administradores lo confirma un Administrador. Si ya se cumplen las otras condiciones, la cuenta se activa. | **Rol, estado de la cuenta, vinculaciones y coordinaciones vigentes de la persona**: Para saber si a quien confirma le corresponde hacerlo. | [P-USU-0001](#p-usu-0001)<br>[P-USU-0008](#p-usu-0008)<br>[P-USU-0049](#p-usu-0049)<br>[P-USU-0031](#p-usu-0031)<br>[P-USU-0032](#p-usu-0032)<br>[P-USU-0034](#p-usu-0034)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Correo institucional confirmado** | Ninguno |
| Administrador<br>Rector<br>Coordinador Académico<br>Coordinador de Facultad<br>Coordinador de Programa | **Confirmar el número celular de la persona**<br>Confirmar que el número celular registrado pertenece a la persona (RF-007, RN-078, RN-080). Lo hace la autoridad que debe decidir su registro o que la gestiona; el del Rector y el de los Administradores lo confirma un Administrador. Si ya se cumplen las otras condiciones, la cuenta se activa. | **Rol, estado de la cuenta, vinculaciones y coordinaciones vigentes de la persona**: Para saber si a quien confirma le corresponde hacerlo. | [P-USU-0001](#p-usu-0001)<br>[P-USU-0008](#p-usu-0008)<br>[P-USU-0049](#p-usu-0049)<br>[P-USU-0031](#p-usu-0031)<br>[P-USU-0033](#p-usu-0033)<br>[P-USU-0034](#p-usu-0034)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Celular confirmado** | Ninguno |
| Sistema | **Activar la cuenta**<br>Activar la cuenta cuando ya fue aprobada y tiene el correo y el celular confirmados. Lo hace el sistema apenas se cumple la última condición (RN-084 a RN-088). En la auditoría figura como usuario el titular de la cuenta. | **No aplica**: No aplica | [P-USU-0008](#p-usu-0008)<br>[P-USU-0035](#p-usu-0035)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Cuenta activada** | Ninguno |
| Usuario autenticado | **Consultar la información personal**<br>Consultar la información personal de una persona y el estado de sus validaciones (RN-101, RN-103, RF-008). | **No aplica**: No aplica | [P-USU-0001](#p-usu-0001)<br>[P-USU-0008](#p-usu-0008)<br>[P-USU-0014](#p-usu-0014) | No aplica | 🟧 **Información personal consultada** | Ninguno |
| Usuario autenticado | **Consultar las personas de la comunidad**<br>Buscar personas de la comunidad, por ejemplo para elegir al destinatario de un Kudo o para saber quién es el Rector activo (RF-076, RF-077). No muestra correo ni celular. | **No aplica**: No aplica | [P-USU-0001](#p-usu-0001) | No aplica | 🟧 **Usuarios consultados** | Ninguno |
| Usuario autenticado | **Actualizar su nombre y apellido**<br>Corregir el nombre y el apellido propios (RN-102, RN-109). | **No aplica**: No aplica | [P-USU-0001](#p-usu-0001)<br>[P-USU-0008](#p-usu-0008)<br>[P-USU-0013](#p-usu-0013)<br>[P-USU-0016](#p-usu-0016)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Información personal actualizada** | Ninguno |
| Usuario autenticado | **Actualizar su correo institucional**<br>Cambiar el correo institucional propio. El correo nuevo debe confirmarlo otra vez la autoridad que lo gestiona (RN-104, RN-110). | **No aplica**: No aplica | [P-USU-0001](#p-usu-0001)<br>[P-USU-0008](#p-usu-0008)<br>[P-USU-0013](#p-usu-0013)<br>[P-USU-0017](#p-usu-0017)<br>[P-USU-0019](#p-usu-0019)<br>[P-USU-0044](#p-usu-0044)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Correo institucional actualizado** | Ninguno |
| Usuario autenticado | **Actualizar su número celular**<br>Cambiar el número celular propio. El celular nuevo debe confirmarlo otra vez la autoridad que lo gestiona (RN-105, RN-111). | **No aplica**: No aplica | [P-USU-0001](#p-usu-0001)<br>[P-USU-0008](#p-usu-0008)<br>[P-USU-0013](#p-usu-0013)<br>[P-USU-0018](#p-usu-0018)<br>[P-USU-0020](#p-usu-0020)<br>[P-USU-0045](#p-usu-0045)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Celular actualizado** | Ninguno |
| Administrador<br>Rector<br>Coordinador Académico<br>Coordinador de Facultad<br>Coordinador de Programa | **Suspender la cuenta**<br>Suspender temporalmente la cuenta de una persona a cargo; mientras tanto no puede ingresar. Si coordina una facultad o un programa, su coordinación termina y la unidad queda sin coordinador hasta que se asigne otro. Sus vinculaciones académicas no se tocan. | **No aplica**: No aplica | [P-USU-0001](#p-usu-0001)<br>[P-USU-0008](#p-usu-0008)<br>[P-USU-0049](#p-usu-0049)<br>[P-USU-0012](#p-usu-0012)<br>[P-USU-0046](#p-usu-0046)<br>[P-COF-0001](CoordinacionFacultad.md#p-cof-0001)<br>[P-COP-0001](CoordinacionPrograma.md#p-cop-0001)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Cuenta suspendida** | Ninguno |
| Administrador<br>Rector<br>Coordinador Académico<br>Coordinador de Facultad<br>Coordinador de Programa | **Reactivar la cuenta suspendida**<br>Levantar la suspensión de una cuenta para que la persona vuelva a ingresar. Si era coordinador, su coordinación no vuelve sola: la autoridad debe asignarla de nuevo. No se reactiva a un Rector o Coordinador Académico si mientras tanto se registró otro. | **No aplica**: No aplica | [P-USU-0001](#p-usu-0001)<br>[P-USU-0008](#p-usu-0008)<br>[P-USU-0049](#p-usu-0049)<br>[P-USU-0012](#p-usu-0012)<br>[P-USU-0047](#p-usu-0047)<br>[P-USU-0042](#p-usu-0042)<br>[P-USU-0043](#p-usu-0043)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Cuenta activada (reactivación)** | Ninguno |
| Administrador<br>Rector<br>Coordinador Académico<br>Coordinador de Facultad<br>Coordinador de Programa | **Desactivar la cuenta**<br>Desactivar definitivamente la cuenta de una persona a cargo. Su historia (Kudos, réplicas, auditoría) se conserva. Si coordina una facultad o un programa, su coordinación termina; si es estudiante o docente, sus vinculaciones vigentes se cierran con fecha. | **No aplica**: No aplica | [P-USU-0001](#p-usu-0001)<br>[P-USU-0008](#p-usu-0008)<br>[P-USU-0049](#p-usu-0049)<br>[P-USU-0012](#p-usu-0012)<br>[P-USU-0048](#p-usu-0048)<br>[P-COF-0001](CoordinacionFacultad.md#p-cof-0001)<br>[P-COP-0001](CoordinacionPrograma.md#p-cop-0001)<br>[P-VIN-0002](VinculacionAcademica.md#p-vin-0002)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Cuenta desactivada** | Ninguno |
| Administrador<br>Rector<br>Coordinador Académico<br>Coordinador de Facultad<br>Coordinador de Programa | **Cambiar el rol de la persona**<br>Cambiar el rol institucional de una persona cuando cambia su cargo (por ejemplo, un coordinador que vuelve a ser docente). Nadie cambia su propio rol (RN-107). | **Roles**: Para elegir el rol nuevo.<br>**Coordinaciones y vinculaciones vigentes de la persona**: Para comprobar que no conserva un cargo del rol anterior. | [P-USU-0001](#p-usu-0001)<br>[P-USU-0008](#p-usu-0008)<br>[P-USU-0049](#p-usu-0049)<br>[P-USU-0012](#p-usu-0012)<br>[P-ROL-0001](Rol.md#p-rol-0001)<br>[P-USU-0051](#p-usu-0051)<br>[P-USU-0050](#p-usu-0050)<br>[P-USU-0042](#p-usu-0042)<br>[P-USU-0043](#p-usu-0043)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Rol del usuario cambiado** | Ninguno |
| Coordinador de Programa<br>Coordinador de Facultad<br>Coordinador Académico<br>Rector | **Consultar los registros pendientes de aprobación**<br>Consultar las personas registradas cuya cuenta está Pendiente de aprobación y que le corresponde decidir a quien consulta. | **Rol de cada persona**: Para saber qué autoridad debe decidir.<br>**Vinculaciones de cada persona**: Para que el Coordinador de Programa vea solo a quienes se vincularon a su programa. | [P-USU-0001](#p-usu-0001)<br>[P-USU-0039](#p-usu-0039) | No aplica | 🟧 **Registros pendientes consultados** | Ninguno |
| Coordinador de Programa<br>Coordinador Académico<br>Rector | **Aprobar el registro de la persona**<br>Aprobar el registro de un estudiante, docente, Coordinador de Facultad o Coordinador Académico (el aspirante a Coordinador de Programa se aprueba al asignarle un programa). Su cuenta pasa a Aprobada y se activa cuando además tenga correo y celular validados (RN-084, RN-096). Quién aprobó y cuándo quedan en la Auditoría. | **Rol y vinculaciones de la persona**: Para saber qué autoridad debe aprobarla.<br>**Rector y Coordinador Académico vigentes**: Para no aprobar un segundo Rector o Coordinador Académico. | [P-USU-0001](#p-usu-0001)<br>[P-USU-0008](#p-usu-0008)<br>[P-USU-0036](#p-usu-0036)<br>[P-USU-0037](#p-usu-0037)<br>[P-USU-0049](#p-usu-0049)<br>[P-USU-0042](#p-usu-0042)<br>[P-USU-0043](#p-usu-0043)<br>[P-USU-0040](#p-usu-0040)<br>[P-USU-0034](#p-usu-0034)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Usuario aprobado** | Ninguno |
| Coordinador de Programa<br>Coordinador de Facultad<br>Coordinador Académico<br>Rector | **Rechazar el registro de la persona**<br>Rechazar el registro de una persona explicando la razón. Su cuenta pasa a Rechazada y sus vinculaciones se cierran; quién rechazó, cuándo y por qué quedan en la Auditoría. La persona puede volver a presentar su registro. | **Rol y vinculaciones de la persona**: Para saber qué autoridad debe decidir. | [P-USU-0001](#p-usu-0001)<br>[P-USU-0008](#p-usu-0008)<br>[P-USU-0036](#p-usu-0036)<br>[P-USU-0037](#p-usu-0037)<br>[P-USU-0049](#p-usu-0049)<br>[P-USU-0038](#p-usu-0038)<br>[P-USU-0041](#p-usu-0041)<br>[P-VIN-0002](VinculacionAcademica.md#p-vin-0002)<br>[P-AUD-0001](Auditoria.md#p-aud-0001) | No aplica | 🟧 **Usuario rechazado** | Ninguno |

---

## 5. Políticas

| Número | Descripción | Objeto responsable | Regla responsable |
| --- | --- | --- | --- |
| <a id="p-usu-0001"></a>**P-USU-0001** | Asegurar que quien ejecuta la acción esté registrado y tenga la cuenta Activa (RN-085 a RN-089). | Usuario | `AsegurarUsuarioActivoRule` |
| <a id="p-usu-0002"></a>**P-USU-0002** | Asegurar que quien ejecuta la acción tenga el rol Administrador, Rector o Coordinador Académico (RN-134 a RN-143). | Usuario | `AsegurarUsuarioGestionaCategoriasRule` |
| <a id="p-usu-0003"></a>**P-USU-0003** | Asegurar que quien ejecuta la acción tenga el rol Rector o Coordinador Académico (RN-193 a RN-200, RN-243, INV-033). | Usuario | `AsegurarUsuarioPuedeModerarRule` |
| <a id="p-usu-0004"></a>**P-USU-0004** | Asegurar que quien ejecuta la acción tenga el rol Administrador (RN-129 a RN-131). | Usuario | `AsegurarUsuarioEsAdministradorRule` |
| <a id="p-usu-0005"></a>**P-USU-0005** | Asegurar que quien ejecuta la acción tenga el rol Coordinador Académico (RN-121). | Usuario | `AsegurarUsuarioEsCoordinadorAcademicoRule` |
| <a id="p-usu-0006"></a>**P-USU-0006** | Asegurar que quien ejecuta la acción tenga el rol Coordinador de Facultad (RN-117). | Usuario | `AsegurarUsuarioEsCoordinadorFacultadRule` |
| <a id="p-usu-0007"></a>**P-USU-0007** | Asegurar que quien ejecuta la acción tenga el rol Coordinador de Programa (RN-112, RN-113). | Usuario | `AsegurarUsuarioEsCoordinadorProgramaRule` |
| <a id="p-usu-0008"></a>**P-USU-0008** | Asegurar que exista un usuario con el identificador indicado. | Usuario | `AsegurarUsuarioExisteRule` |
| <a id="p-usu-0009"></a>**P-USU-0009** | Asegurar que la persona indicada exista y tenga la cuenta Activa. | Usuario | `AsegurarPersonaIndicadaActivaRule` |
| <a id="p-usu-0010"></a>**P-USU-0010** | Asegurar que la persona indicada tenga el rol Coordinador de Facultad (RN-001 a RN-003). | Usuario | `AsegurarPersonaTieneRolCoordinadorFacultadRule` |
| <a id="p-usu-0011"></a>**P-USU-0011** | Asegurar que la persona indicada tenga el rol Estudiante o Docente (RN-053, RN-061). | Usuario | `AsegurarPersonaEsEstudianteODocenteRule` |
| <a id="p-usu-0012"></a>**P-USU-0012** | Asegurar que quien ejecuta la acción sea la autoridad que gestiona el rol de la persona afectada y que esta pertenezca a su ámbito (RN-112 a RN-129). El ámbito de un Coordinador de Programa o de Facultad es la unidad que coordina con una coordinación vigente; sin ella no gestiona a nadie. | Usuario | `AsegurarAutoridadGestionaPersonaRule` |
| <a id="p-usu-0013"></a>**P-USU-0013** | Asegurar que quien ejecuta la acción sea el titular de la cuenta (RN-101 a RN-105). | Usuario | `AsegurarUsuarioEsTitularRule` |
| <a id="p-usu-0014"></a>**P-USU-0014** | Asegurar que quien consulta sea el titular de la cuenta o la autoridad que lo gestiona (RN-101, RN-112 a RN-129). | Usuario | `AsegurarUsuarioEsTitularOAutoridadRule` |
| <a id="p-usu-0015"></a>**P-USU-0015** | Asegurar que el nombre, el apellido, el correo institucional, el celular y el rol sean válidos en tipo, longitud, obligatoriedad, formato y rango (RN-069 a RN-072). | Usuario | `AsegurarFormatoRegistroUsuarioValidoRule` |
| <a id="p-usu-0016"></a>**P-USU-0016** | Asegurar que el nombre y el apellido sean válidos en tipo, longitud, obligatoriedad y formato. | Usuario | `AsegurarFormatoNombreUsuarioValidoRule` |
| <a id="p-usu-0017"></a>**P-USU-0017** | Asegurar que el correo institucional sea válido en tipo, longitud, obligatoriedad y formato (termina en @uco.edu.co). | Usuario | `AsegurarFormatoCorreoUsuarioValidoRule` |
| <a id="p-usu-0018"></a>**P-USU-0018** | Asegurar que el celular sea válido en tipo, longitud, obligatoriedad y formato (10 dígitos que inician en 3). | Usuario | `AsegurarFormatoCelularUsuarioValidoRule` |
| <a id="p-usu-0019"></a>**P-USU-0019** | Asegurar que no exista otro usuario con el mismo correo institucional (RN-073). | Usuario | `AsegurarCorreoUsuarioNoExistaRule` |
| <a id="p-usu-0020"></a>**P-USU-0020** | Asegurar que no exista otro usuario con el mismo número celular (RN-074). | Usuario | `AsegurarCelularUsuarioNoExistaRule` |
| <a id="p-usu-0021"></a>**P-USU-0021** | Asegurar que el identificador del usuario sea único dentro de los usuarios; si ya existe, generar otro. | Usuario | `AsegurarIdUsuarioUnicoRule` |
| <a id="p-usu-0022"></a>**P-USU-0022** | Asignar a la cuenta nueva el estado Pendiente de aprobación, el correo y el celular como no verificados y la fecha de registro. | Usuario | `AsignarEstadoInicialUsuarioRule` |
| <a id="p-usu-0023"></a>**P-USU-0023** | Asegurar que el rol elegido al registrarse sea Estudiante, Docente, Coordinador de Programa, Coordinador de Facultad o Coordinador Académico. El Rector y los Administradores no se registran solos: los registra un Administrador (RN-094, RN-095). | Usuario | `AsegurarRolPermiteRegistroPropioRule` |
| <a id="p-usu-0024"></a>**P-USU-0024** | Asegurar que el nombre, el apellido, el correo institucional y el celular sean válidos en tipo, longitud, obligatoriedad y formato (RN-069 a RN-072). | Usuario | `AsegurarFormatoRegistroPorAdministradorValidoRule` |
| <a id="p-usu-0025"></a>**P-USU-0025** | Asignar a la cuenta nueva el rol que corresponde a la responsabilidad (Rector o Administrador), el estado Aprobada (el registro hecho por un Administrador es la aprobación institucional, RN-085, RN-094, RN-095), el correo y el celular como no verificados y la fecha de registro. Se activa cuando se confirmen su correo y su celular. | Usuario | `AsignarEstadoInicialRegistroPorAdministradorRule` |
| <a id="p-usu-0026"></a>**P-USU-0026** | Asegurar que, si el rol es Estudiante o Docente, se indique al menos un programa académico existente al que pertenece (RN-053, RN-061); con los roles de coordinador no se indican programas. | Usuario | `AsegurarProgramasSegunRolRule` |
| <a id="p-usu-0027"></a>**P-USU-0027** | Asegurar que la cuenta esté Rechazada para volver a presentar el registro; mientras esté Pendiente de aprobación no puede presentarlo otra vez. | Usuario | `AsegurarCuentaRechazadaRule` |
| <a id="p-usu-0028"></a>**P-USU-0028** | Asignar a la cuenta el rol elegido y el estado Pendiente de aprobación. El rechazo anterior y su justificación quedan en la Auditoría. | Usuario | `AsignarCuentaPendienteNuevamenteRule` |
| <a id="p-usu-0029"></a>**P-USU-0029** | Asegurar que la persona indicada exista, tenga el rol Coordinador de Programa y su cuenta esté Pendiente de aprobación (aspirante) o Activa (RN-001 a RN-003, RN-091). | Usuario | `AsegurarPersonaAsignableComoCoordinadorProgramaRule` |
| <a id="p-usu-0030"></a>**P-USU-0030** | Cuando se asigne un programa a un aspirante a Coordinador de Programa con la cuenta Pendiente de aprobación, pasar su cuenta a Aprobada: asignarle un programa de su facultad es la aprobación del Coordinador de Facultad (RN-091). Se activa cuando se confirmen su correo y su celular. | Usuario | `AprobarCuentaAlAsignarCoordinacionProgramaRule` |
| <a id="p-usu-0031"></a>**P-USU-0031** | Asegurar que quien confirma el correo institucional o el celular sea la autoridad que corresponde a la persona (RN-077, RN-078): mientras su registro está Pendiente de aprobación, la que debe decidirlo (Estudiante y Docente ← Coordinador de Programa con coordinación vigente de alguno de sus programas; aspirante a Coordinador de Programa ← cualquier Coordinador de Facultad con coordinación vigente; Coordinador de Facultad ← Coordinador Académico; Coordinador Académico ← Rector); después, la que la gestiona (RN-112 a RN-129). El correo y el celular del Rector y de los Administradores los confirma un Administrador (RN-129). No se confirman datos de una cuenta Rechazada o Desactivada. | Usuario | `AsegurarAutoridadConfirmaContactoRule` |
| <a id="p-usu-0032"></a>**P-USU-0032** | Asegurar que el correo institucional aún no esté verificado. | Usuario | `AsegurarCorreoSinVerificarRule` |
| <a id="p-usu-0033"></a>**P-USU-0033** | Asegurar que el celular aún no esté verificado. | Usuario | `AsegurarCelularSinVerificarRule` |
| <a id="p-usu-0034"></a>**P-USU-0034** | Cuando la cuenta esté Aprobada y tenga el correo y el celular verificados, activarla (RN-084 a RN-088). | Usuario | `ActivarCuentaCuandoCumplaCondicionesRule` |
| <a id="p-usu-0035"></a>**P-USU-0035** | Asegurar que la cuenta esté Aprobada y tenga el correo y el celular verificados antes de activarla (RN-085 a RN-088). | Usuario | `AsegurarCuentaCumpleCondicionesActivacionRule` |
| <a id="p-usu-0036"></a>**P-USU-0036** | Asegurar que la cuenta afectada esté Pendiente de aprobación; un registro se decide una sola vez. | Usuario | `AsegurarCuentaPendienteAprobacionRule` |
| <a id="p-usu-0037"></a>**P-USU-0037** | Asegurar que quien decide sea la autoridad que corresponde al rol de la persona registrada: Estudiante y Docente ← Coordinador de Programa con coordinación vigente de alguno de los programas a los que la persona se vinculó (basta uno: la cuenta queda aprobada para todos sus programas); Coordinador de Facultad ← Coordinador Académico; Coordinador Académico ← Rector (RN-090, RN-092, RN-093). El aspirante a Coordinador de Programa se aprueba al asignarle un programa (RN-091) y lo puede rechazar cualquier Coordinador de Facultad con coordinación vigente. El Rector y los Administradores no pasan por aprobación (RN-094, RN-095). | Usuario | `AsegurarAprobadorCorrespondeAlRolRule` |
| <a id="p-usu-0038"></a>**P-USU-0038** | Asegurar que la justificación del rechazo sea válida en longitud y formato (entre 10 y 200 caracteres), porque se guarda en el detalle de la auditoría. | Usuario | `AsegurarFormatoJustificacionRechazoValidoRule` |
| <a id="p-usu-0039"></a>**P-USU-0039** | Asegurar que cada autoridad vea solo los registros pendientes que le corresponde decidir: el Coordinador de Programa, los estudiantes y docentes vinculados a su programa; el Coordinador de Facultad, los aspirantes a Coordinador de Programa; el Coordinador Académico, los aspirantes a Coordinador de Facultad; el Rector, los aspirantes a Coordinador Académico. Un coordinador sin coordinación vigente no tiene registros a su cargo. | Usuario | `AsegurarConsultaPendientesDeSuAmbitoRule` |
| <a id="p-usu-0040"></a>**P-USU-0040** | Asignar a la cuenta el estado Aprobada. Quién aprobó y cuándo quedan en la Auditoría (RN-096). | Usuario | `AsignarCuentaAprobadaRule` |
| <a id="p-usu-0041"></a>**P-USU-0041** | Asignar a la cuenta el estado Rechazada. Quién rechazó, cuándo y por qué quedan en la Auditoría. | Usuario | `AsignarCuentaRechazadaRule` |
| <a id="p-usu-0042"></a>**P-USU-0042** | Asegurar que no exista otro Rector con cuenta Aprobada o Activa cuando la persona tenga el rol Rector (RN-005, INV-001). | Usuario | `AsegurarUnicoRectorVigenteRule` |
| <a id="p-usu-0043"></a>**P-USU-0043** | Asegurar que no exista otro Coordinador Académico con cuenta Aprobada o Activa cuando la persona tenga ese rol (RN-011, INV-002). | Usuario | `AsegurarUnicoCoordinadorAcademicoVigenteRule` |
| <a id="p-usu-0044"></a>**P-USU-0044** | Cuando cambie el correo institucional, marcarlo como no verificado y devolver la cuenta a Aprobada hasta que la autoridad lo confirme de nuevo (RN-081, RN-110). | Usuario | `ExigirNuevaVerificacionCorreoRule` |
| <a id="p-usu-0045"></a>**P-USU-0045** | Cuando cambie el celular, marcarlo como no verificado y devolver la cuenta a Aprobada hasta que la autoridad lo confirme de nuevo (RN-082, RN-111). | Usuario | `ExigirNuevaVerificacionCelularRule` |
| <a id="p-usu-0046"></a>**P-USU-0046** | Asegurar que la cuenta afectada esté Activa antes de suspenderla. | Usuario | `AsegurarCuentaActivaParaSuspenderODesactivarRule` |
| <a id="p-usu-0047"></a>**P-USU-0047** | Asegurar que la cuenta afectada esté Suspendida antes de reactivarla. | Usuario | `AsegurarCuentaSuspendidaRule` |
| <a id="p-usu-0048"></a>**P-USU-0048** | Asegurar que la cuenta afectada esté Activa o Suspendida antes de desactivarla. | Usuario | `AsegurarCuentaNoDesactivadaRule` |
| <a id="p-usu-0049"></a>**P-USU-0049** | Asegurar que quien ejecuta la acción no sea la misma persona afectada (RN-107, RN-108). | Usuario | `AsegurarNoActuarSobreCuentaPropiaRule` |
| <a id="p-usu-0050"></a>**P-USU-0050** | Asegurar que la persona no tenga una coordinación ni una vinculación académica vigente antes de cambiar su rol (RN-001 a RN-003). | Usuario | `AsegurarPersonaSinCargoVigenteRule` |
| <a id="p-usu-0051"></a>**P-USU-0051** | Asegurar que el rol nuevo sea distinto al rol actual de la persona. | Usuario | `AsegurarRolNuevoDistintoRule` |

---

[🏠 Inicio](../../README.md) · [📑 Modelo de dominio](README.md) · [⬅️ Facultad](Facultad.md) · [ProgramaAcademico ➡️](ProgramaAcademico.md)<br>
**Ver este objeto en:** [🗄️ MER](../mer/Usuario.md) · [📐 Clase](../clases/Usuario.md)
