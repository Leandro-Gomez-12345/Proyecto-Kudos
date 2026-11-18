package co.edu.uco.kudos.transversal.catalogo;

import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class CatalogoMensajes {

	private static final String CONTACTAR_ADMINISTRADOR = " Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad.";

	private CatalogoMensajes() {

	}

	public static final class UtilSQL {

		private UtilSQL() {

		}

		public static final String USUARIO_ERROR_PROBLEMA_VALIDANDO_SI_CONEXION_SQL_ESTA_ABIERTA = UtilTexto.concatenar("Se presentó un problema tratando de validar si la conexión con la fuente de información estaba abierta.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_SI_CONEXION_SQL_ESTA_ABIERTA = UtilTexto.concatenar("Se presentó un problema NO CONTROLADO tratando de validar si la conexión con la fuente de información estaba abierta.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_PROBLEMA_VALIDANDO_SI_CONEXION_SQL_ES_VALIDA = UtilTexto.concatenar("Se presentó un problema tratando de validar si la conexión con la fuente de información está respondiendo.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_PROBLEMA_VALIDANDO_SI_TRANSACCION_SQL_ESTA_INICIADA = UtilTexto.concatenar("Se presentó un problema tratando de validar si la operación sobre la fuente de información ya estaba iniciada.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_VALIDANDO_SI_TRANSACCION_SQL_ESTA_INICIADA = UtilTexto.concatenar("Se presentó un problema NO CONTROLADO tratando de validar si la operación sobre la fuente de información ya estaba iniciada.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CONEXION_SQL_NO_ESTA_ABIERTA = UtilTexto.concatenar("No es posible llevar a cabo la operación deseada porque la conexión con la fuente de información no está abierta.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONEXION_SQL_NO_ES_VALIDA = UtilTexto.concatenar("La conexión con la fuente de información no está respondiendo.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_NO_ES_POSIBLE_INICIAR_TRANSACCION_SQL = UtilTexto.concatenar("No es posible iniciar la operación deseada porque ya hay una operación en curso sobre la fuente de información.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_INICIANDO_TRANSACCION_SQL = UtilTexto.concatenar("Se presentó un problema tratando de iniciar la operación sobre la fuente de información.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_NO_ES_POSIBLE_CONFIRMAR_TRANSACCION_SQL = UtilTexto.concatenar("No es posible confirmar la operación porque no fue iniciada previamente.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONFIRMANDO_TRANSACCION_SQL = UtilTexto.concatenar("Se presentó un problema tratando de confirmar los cambios en la fuente de información.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_NO_ES_POSIBLE_CANCELAR_TRANSACCION_SQL = UtilTexto.concatenar("No es posible cancelar la operación porque no fue iniciada previamente.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CANCELANDO_TRANSACCION_SQL = UtilTexto.concatenar("Se presentó un problema tratando de deshacer los cambios en la fuente de información.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CERRANDO_CONEXION_SQL = UtilTexto.concatenar("Se presentó un problema tratando de cerrar la conexión con la fuente de información.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class Datos {

		private Datos() {

		}

		public static final String USUARIO_ERROR_ABRIENDO_CONEXION = UtilTexto.concatenar("Se presentó un problema tratando de abrir la conexión con la fuente de información.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_FACTORIA_NO_HABILITADA = UtilTexto.concatenar("La fuente de información solicitada para llevar a cabo la operación no está habilitada.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_VARIABLE_ENTORNO_NO_DEFINIDA = UtilTexto.concatenar("No es posible abrir la conexión con la fuente de información porque la variable de entorno %s no está definida.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CONSULTANDO_ROLES = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de los roles.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CONSULTANDO_ESTADOS_CUENTA = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de los estados de cuenta.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CONSULTANDO_ESTADOS_CONTENIDO = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de los estados del contenido.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CONSULTANDO_ESTADOS_REVISION = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de los estados de revisión.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CONSULTANDO_MOTIVOS = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de los motivos.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CREANDO_CATEGORIA = UtilTexto.concatenar("Se presentó un problema tratando de crear la información de la categoría.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_CATEGORIAS = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de las categorías.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_ACTUALIZANDO_CATEGORIA = UtilTexto.concatenar("Se presentó un problema tratando de actualizar la información de la categoría.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CREANDO_FACULTAD = UtilTexto.concatenar("Se presentó un problema tratando de crear la información de la facultad.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_FACULTADES = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de las facultades.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_ACTUALIZANDO_FACULTAD = UtilTexto.concatenar("Se presentó un problema tratando de actualizar la información de la facultad.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CREANDO_USUARIO = UtilTexto.concatenar("Se presentó un problema tratando de crear la información del usuario.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_USUARIOS = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de los usuarios.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_ACTUALIZANDO_USUARIO = UtilTexto.concatenar("Se presentó un problema tratando de actualizar la información del usuario.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CREANDO_PROGRAMA_ACADEMICO = UtilTexto.concatenar("Se presentó un problema tratando de crear la información del programa académico.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_PROGRAMAS_ACADEMICOS = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de los programas académicos.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_ACTUALIZANDO_PROGRAMA_ACADEMICO = UtilTexto.concatenar("Se presentó un problema tratando de actualizar la información del programa académico.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CREANDO_AUDITORIA = UtilTexto.concatenar("Se presentó un problema tratando de crear la información del registro de auditoría.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_AUDITORIAS = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de los registros de auditoría.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CREANDO_COORDINACION_FACULTAD = UtilTexto.concatenar("Se presentó un problema tratando de crear la información de la coordinación de facultad.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_COORDINACIONES_FACULTAD = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de las coordinaciones de facultad.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_ACTUALIZANDO_COORDINACION_FACULTAD = UtilTexto.concatenar("Se presentó un problema tratando de actualizar la información de la coordinación de facultad.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CREANDO_COORDINACION_PROGRAMA = UtilTexto.concatenar("Se presentó un problema tratando de crear la información de la coordinación de programa.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_COORDINACIONES_PROGRAMA = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de las coordinaciones de programa.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_ACTUALIZANDO_COORDINACION_PROGRAMA = UtilTexto.concatenar("Se presentó un problema tratando de actualizar la información de la coordinación de programa.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CREANDO_VINCULACION_ACADEMICA = UtilTexto.concatenar("Se presentó un problema tratando de crear la información de la vinculación académica.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_VINCULACIONES_ACADEMICAS = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de las vinculaciones académicas.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_ACTUALIZANDO_VINCULACION_ACADEMICA = UtilTexto.concatenar("Se presentó un problema tratando de actualizar la información de la vinculación académica.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CREANDO_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de crear la información del Kudo.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_KUDOS = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de los Kudos.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_ACTUALIZANDO_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de actualizar la información del Kudo.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CREANDO_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de crear la información de la réplica.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_REPLICAS = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de las réplicas.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_ACTUALIZANDO_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de actualizar la información de la réplica.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CREANDO_CALIFICACION_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de crear la información de la calificación del Kudo.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_CALIFICACIONES_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de las calificaciones de los Kudos.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_ACTUALIZANDO_CALIFICACION_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de actualizar la información de la calificación del Kudo.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CREANDO_REVISION_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de crear la información de la revisión del Kudo.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_REVISIONES_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de las revisiones de los Kudos.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_ACTUALIZANDO_REVISION_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de actualizar la información de la revisión del Kudo.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CREANDO_CALIFICACION_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de crear la información de la calificación de la réplica.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_CALIFICACIONES_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de las calificaciones de las réplicas.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_ACTUALIZANDO_CALIFICACION_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de actualizar la información de la calificación de la réplica.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CREANDO_REVISION_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de crear la información de la revisión de la réplica.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_REVISIONES_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de las revisiones de las réplicas.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_ACTUALIZANDO_REVISION_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de actualizar la información de la revisión de la réplica.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CREANDO_REPORTE_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de crear la información del reporte del Kudo.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_REPORTES_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de los reportes de los Kudos.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_ACTUALIZANDO_REPORTE_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de actualizar la información del reporte del Kudo.", CONTACTAR_ADMINISTRADOR);

		public static final String USUARIO_ERROR_CREANDO_REPORTE_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de crear la información del reporte de la réplica.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_REPORTES_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información de los reportes de las réplicas.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_ACTUALIZANDO_REPORTE_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de actualizar la información del reporte de la réplica.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class RolFachada {

		private RolFachada() {

		}

		public static final String USUARIO_ERROR_CONSULTANDO_ROLES = UtilTexto.concatenar("Se presentó un problema tratando de consultar los roles institucionales.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class EstadoCuentaFachada {

		private EstadoCuentaFachada() {

		}

		public static final String USUARIO_ERROR_CONSULTANDO_ESTADOS_CUENTA = UtilTexto.concatenar("Se presentó un problema tratando de consultar los estados de cuenta.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class EstadoContenidoFachada {

		private EstadoContenidoFachada() {

		}

		public static final String USUARIO_ERROR_CONSULTANDO_ESTADOS_CONTENIDO = UtilTexto.concatenar("Se presentó un problema tratando de consultar los estados del contenido.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class EstadoRevisionFachada {

		private EstadoRevisionFachada() {

		}

		public static final String USUARIO_ERROR_CONSULTANDO_ESTADOS_REVISION = UtilTexto.concatenar("Se presentó un problema tratando de consultar los estados de revisión.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class MotivoFachada {

		private MotivoFachada() {

		}

		public static final String USUARIO_ERROR_CONSULTANDO_MOTIVOS = UtilTexto.concatenar("Se presentó un problema tratando de consultar los motivos de reporte.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class CategoriaFachada {

		private CategoriaFachada() {

		}

		public static final String USUARIO_ERROR_REGISTRANDO_CATEGORIA = UtilTexto.concatenar("Se presentó un problema tratando de registrar la categoría.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_CATEGORIAS = UtilTexto.concatenar("Se presentó un problema tratando de consultar las categorías.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CAMBIANDO_NOMBRE_CATEGORIA = UtilTexto.concatenar("Se presentó un problema tratando de cambiar el nombre de la categoría.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CAMBIANDO_DESCRIPCION_CATEGORIA = UtilTexto.concatenar("Se presentó un problema tratando de cambiar la descripción de la categoría.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_ACTIVANDO_CATEGORIA = UtilTexto.concatenar("Se presentó un problema tratando de activar la categoría.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_DESACTIVANDO_CATEGORIA = UtilTexto.concatenar("Se presentó un problema tratando de desactivar la categoría.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class FacultadFachada {

		private FacultadFachada() {

		}

		public static final String USUARIO_ERROR_REGISTRANDO_FACULTAD = UtilTexto.concatenar("Se presentó un problema tratando de registrar la facultad.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_FACULTADES = UtilTexto.concatenar("Se presentó un problema tratando de consultar las facultades.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CAMBIANDO_NOMBRE_FACULTAD = UtilTexto.concatenar("Se presentó un problema tratando de cambiar el nombre de la facultad.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class UsuarioFachada {

		private UsuarioFachada() {

		}

		public static final String USUARIO_ERROR_REGISTRANDO_USUARIO = UtilTexto.concatenar("Se presentó un problema tratando de registrar al usuario.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_PRESENTANDO_NUEVAMENTE_REGISTRO_USUARIO = UtilTexto.concatenar("Se presentó un problema tratando de presentar nuevamente el registro del usuario.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_REGISTRANDO_RECTOR = UtilTexto.concatenar("Se presentó un problema tratando de registrar al Rector.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_REGISTRANDO_ADMINISTRADOR = UtilTexto.concatenar("Se presentó un problema tratando de registrar al Administrador.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_VERIFICANDO_CORREO_USUARIO = UtilTexto.concatenar("Se presentó un problema tratando de verificar el correo institucional del usuario.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_VERIFICANDO_CELULAR_USUARIO = UtilTexto.concatenar("Se presentó un problema tratando de verificar el celular del usuario.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_INFORMACION_PERSONAL_USUARIO = UtilTexto.concatenar("Se presentó un problema tratando de consultar la información personal del usuario.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_USUARIOS = UtilTexto.concatenar("Se presentó un problema tratando de consultar los usuarios.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CAMBIANDO_NOMBRE_USUARIO = UtilTexto.concatenar("Se presentó un problema tratando de cambiar el nombre del usuario.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CAMBIANDO_CORREO_USUARIO = UtilTexto.concatenar("Se presentó un problema tratando de cambiar el correo institucional del usuario.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CAMBIANDO_CELULAR_USUARIO = UtilTexto.concatenar("Se presentó un problema tratando de cambiar el celular del usuario.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_SUSPENDIENDO_CUENTA_USUARIO = UtilTexto.concatenar("Se presentó un problema tratando de suspender la cuenta del usuario.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_REACTIVANDO_CUENTA_USUARIO = UtilTexto.concatenar("Se presentó un problema tratando de reactivar la cuenta del usuario.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_DESACTIVANDO_CUENTA_USUARIO = UtilTexto.concatenar("Se presentó un problema tratando de desactivar la cuenta del usuario.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CAMBIANDO_ROL_USUARIO = UtilTexto.concatenar("Se presentó un problema tratando de cambiar el rol del usuario.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_USUARIOS_PENDIENTES_APROBACION = UtilTexto.concatenar("Se presentó un problema tratando de consultar los usuarios pendientes de aprobación.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_APROBANDO_USUARIO = UtilTexto.concatenar("Se presentó un problema tratando de aprobar el registro del usuario.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_RECHAZANDO_USUARIO = UtilTexto.concatenar("Se presentó un problema tratando de rechazar el registro del usuario.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class ProgramaAcademicoFachada {

		private ProgramaAcademicoFachada() {

		}

		public static final String USUARIO_ERROR_REGISTRANDO_PROGRAMA_ACADEMICO = UtilTexto.concatenar("Se presentó un problema tratando de registrar el programa académico.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_PROGRAMAS_ACADEMICOS = UtilTexto.concatenar("Se presentó un problema tratando de consultar los programas académicos.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CAMBIANDO_NOMBRE_PROGRAMA_ACADEMICO = UtilTexto.concatenar("Se presentó un problema tratando de cambiar el nombre del programa académico.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class AuditoriaFachada {

		private AuditoriaFachada() {

		}

		public static final String USUARIO_ERROR_CONSULTANDO_AUDITORIAS = UtilTexto.concatenar("Se presentó un problema tratando de consultar los registros de auditoría.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_AUDITORIAS_PROPIAS = UtilTexto.concatenar("Se presentó un problema tratando de consultar el historial de actividad propio.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class CoordinacionFacultadFachada {

		private CoordinacionFacultadFachada() {

		}

		public static final String USUARIO_ERROR_ASIGNANDO_COORDINADOR_FACULTAD = UtilTexto.concatenar("Se presentó un problema tratando de asignar al coordinador de la facultad.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_COORDINACIONES_FACULTAD = UtilTexto.concatenar("Se presentó un problema tratando de consultar las coordinaciones de la facultad.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class CoordinacionProgramaFachada {

		private CoordinacionProgramaFachada() {

		}

		public static final String USUARIO_ERROR_ASIGNANDO_COORDINADOR_PROGRAMA = UtilTexto.concatenar("Se presentó un problema tratando de asignar al coordinador del programa académico.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_COORDINACIONES_PROGRAMA = UtilTexto.concatenar("Se presentó un problema tratando de consultar las coordinaciones del programa académico.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class VinculacionAcademicaFachada {

		private VinculacionAcademicaFachada() {

		}

		public static final String USUARIO_ERROR_VINCULANDO_PERSONA_A_PROGRAMA = UtilTexto.concatenar("Se presentó un problema tratando de vincular a la persona con el programa académico.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_DESVINCULANDO_PERSONA_DE_PROGRAMA = UtilTexto.concatenar("Se presentó un problema tratando de desvincular a la persona del programa académico.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_VINCULACIONES_ACADEMICAS = UtilTexto.concatenar("Se presentó un problema tratando de consultar las vinculaciones académicas.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class KudoFachada {

		private KudoFachada() {

		}

		public static final String USUARIO_ERROR_CREANDO_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de crear el Kudo.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de consultar el Kudo.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_KUDOS = UtilTexto.concatenar("Se presentó un problema tratando de consultar los Kudos.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class ReplicaFachada {

		private ReplicaFachada() {

		}

		public static final String USUARIO_ERROR_REPLICANDO_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de replicar el Kudo.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_RESPONDIENDO_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de responder la réplica.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_CONVERSACION_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de consultar la conversación del Kudo.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class CalificacionKudoFachada {

		private CalificacionKudoFachada() {

		}

		public static final String USUARIO_ERROR_CALIFICANDO_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de calificar el Kudo.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CAMBIANDO_CALIFICACION_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de cambiar la calificación del Kudo.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_CALIFICACIONES_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de consultar las calificaciones del Kudo.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class RevisionKudoFachada {

		private RevisionKudoFachada() {

		}

		public static final String USUARIO_ERROR_INICIANDO_REVISION_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de iniciar la revisión del Kudo.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CERRANDO_REVISION_KUDO_CON_VETO = UtilTexto.concatenar("Se presentó un problema tratando de cerrar con veto la revisión del Kudo.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CERRANDO_REVISION_KUDO_CON_LIBERACION = UtilTexto.concatenar("Se presentó un problema tratando de cerrar con liberación la revisión del Kudo.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_VETANDO_KUDO_DIRECTAMENTE = UtilTexto.concatenar("Se presentó un problema tratando de vetar directamente el Kudo.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_REVISIONES_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de consultar las revisiones de los Kudos.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class CalificacionReplicaFachada {

		private CalificacionReplicaFachada() {

		}

		public static final String USUARIO_ERROR_CALIFICANDO_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de calificar la réplica.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CAMBIANDO_CALIFICACION_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de cambiar la calificación de la réplica.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_CALIFICACIONES_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de consultar las calificaciones de la réplica.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class RevisionReplicaFachada {

		private RevisionReplicaFachada() {

		}

		public static final String USUARIO_ERROR_INICIANDO_REVISION_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de iniciar la revisión de la réplica.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CERRANDO_REVISION_REPLICA_CON_VETO = UtilTexto.concatenar("Se presentó un problema tratando de cerrar con veto la revisión de la réplica.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CERRANDO_REVISION_REPLICA_CON_LIBERACION = UtilTexto.concatenar("Se presentó un problema tratando de cerrar con liberación la revisión de la réplica.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_VETANDO_REPLICA_DIRECTAMENTE = UtilTexto.concatenar("Se presentó un problema tratando de vetar directamente la réplica.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_REVISIONES_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de consultar las revisiones de las réplicas.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class ReporteKudoFachada {

		private ReporteKudoFachada() {

		}

		public static final String USUARIO_ERROR_REPORTANDO_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de reportar el Kudo.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_REPORTES_KUDO = UtilTexto.concatenar("Se presentó un problema tratando de consultar los reportes de los Kudos.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class ReporteReplicaFachada {

		private ReporteReplicaFachada() {

		}

		public static final String USUARIO_ERROR_REPORTANDO_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de reportar la réplica.", CONTACTAR_ADMINISTRADOR);
		public static final String USUARIO_ERROR_CONSULTANDO_REPORTES_REPLICA = UtilTexto.concatenar("Se presentó un problema tratando de consultar los reportes de las réplicas.", CONTACTAR_ADMINISTRADOR);
	}

	public static final class AbstractRestController {

		private AbstractRestController() {

		}

		public static final String RECURSO_NO_EXISTE = "El recurso %s solicitado con el método %s no existe.";
		public static final String METODO_NO_PERMITIDO = "El método %s no está permitido para el recurso solicitado.";
		public static final String CONTENIDO_NO_SOPORTADO = "El formato del contenido enviado no es compatible. Envíe la información en formato JSON.";
		public static final String CUERPO_NO_VALIDO = "La información enviada no tiene un formato válido.";
		public static final String PARAMETRO_NO_VALIDO = "El valor indicado para %s no es válido.";
		public static final String ERROR_NO_CONTROLADO = UtilTexto.concatenar("Se presentó un error inesperado tratando de llevar a cabo la operación deseada.", " Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y entréguele el código de la respuesta (correlationId).");
	}

	public static final class Reglas {

		private Reglas() {

		}

		public static final String APROBADOR_NO_CORRESPONDE_AL_ROL =
				"A usted no le corresponde decidir el registro de esta persona.";
		public static final String AUDITORIA_NO_REGISTRADA =
				"No se registra la auditoría y se deshace la operación que la originó.";
		public static final String AUTORIDAD_NO_GESTIONA_PERSONA =
				"Usted no es la autoridad que gestiona a esta persona.";
		public static final String AVISO_CONTENIDO_VETADO = "Contenido vetado";
		public static final String CALIFICACION_ESTRELLAS_NO_VALIDAS = "La calificación debe ser de 1 a 5 estrellas.";
		public static final String CALIFICACION_IGUAL_A_LA_ACTUAL = "Esa ya es su calificación actual.";
		public static final String CALIFICACION_KUDO_OBLIGATORIO = "Debe indicar el Kudo que califica.";
		public static final String CALIFICACION_NO_ES_PROPIA = "Solo puede cambiar sus propias calificaciones.";
		public static final String CALIFICACION_NO_EXISTE = "No existe la calificación que se indicó.";
		public static final String CALIFICACION_REPLICA_OBLIGATORIO = "Debe indicar la réplica que califica.";
		public static final String CALIFICACION_YA_REGISTRADA =
				"Usted ya calificó este contenido; puede cambiar su calificación.";
		public static final String CALIFICAR_CONTENIDO_PROPIO = "No puede calificar su propio contenido.";
		public static final String CATEGORIA_DESCRIPCION_NO_VALIDA =
				"La descripción de la categoría es obligatoria, debe tener entre 10 y 300 "
				+ "caracteres y solo puede contener letras, números, tildes, espacios y los "
				+ "signos . , ; : ¿ ? ¡ ! ( ) -.";
		public static final String CATEGORIA_INACTIVA = "La categoría está inactiva y no puede usarse.";
		public static final String CATEGORIA_NOMBRE_NO_VALIDO =
				"El nombre de la categoría es obligatorio, debe tener entre 3 y 50 caracteres y "
				+ "solo puede contener letras, tildes y espacios.";
		public static final String CATEGORIA_NOMBRE_REPETIDO = "Ya existe una categoría con ese nombre.";
		public static final String CATEGORIA_NO_EXISTE = "No existe la categoría que se indicó.";
		public static final String CATEGORIA_UNICA_ACTIVA =
				"No puede desactivar la única categoría activa; active o cree otra primero.";
		public static final String CATEGORIA_YA_ACTIVA = "La categoría ya está activa.";
		public static final String CELULAR_YA_VERIFICADO = "El celular ya estaba verificado.";
		public static final String COORDINACION_FECHA_INICIO_NO_VALIDA =
				"La fecha de inicio no puede ser anterior al 01/01/1982 ni posterior a la fecha "
				+ "actual.";
		public static final String COORDINACION_PERSONA_OBLIGATORIA = "Debe indicar la persona que coordina.";
		public static final String COORDINACION_REPETIDA = "Ya existe esa coordinación.";
		public static final String COORDINACION_YA_TERMINADA = "Esa coordinación ya había terminado.";
		public static final String COORDINADOR_ACADEMICO_YA_EXISTE =
				"Ya existe un Coordinador Académico activo en la universidad.";
		public static final String CORREO_YA_VERIFICADO = "El correo ya estaba verificado.";
		public static final String CUENTA_NO_ACTIVA_PARA_SUSPENDER = "Solo se puede suspender una cuenta activa.";
		public static final String CUENTA_NO_CUMPLE_CONDICIONES_ACTIVACION =
				"La cuenta aún no cumple las tres condiciones para activarse.";
		public static final String CUENTA_NO_RECHAZADA =
				"Su registro todavía está pendiente de aprobación; espere la decisión.";
		public static final String CUENTA_NO_SUSPENDIDA = "Solo se puede reactivar una cuenta suspendida.";
		public static final String CUENTA_YA_DESACTIVADA = "La cuenta ya está desactivada o nunca fue activada.";
		public static final String DESVINCULACION_FECHA_NO_VALIDA =
				"La fecha de desvinculación no puede ser anterior a la de vinculación.";
		public static final String FACULTAD_CON_COORDINADOR_VIGENTE =
				"La facultad ya tiene un coordinador vigente; para cambiarlo, primero debe "
				+ "suspenderse o desactivarse la cuenta del actual.";
		public static final String FACULTAD_NOMBRE_NO_VALIDO =
				"El nombre de la facultad debe tener la forma 'Facultad de <área>', entre 13 y "
				+ "70 caracteres, y solo puede contener letras, tildes y espacios.";
		public static final String FACULTAD_NOMBRE_REPETIDO = "Ya existe una facultad con ese nombre.";
		public static final String FACULTAD_NO_EXISTE = "No existe la facultad que se indicó.";
		public static final String FACULTAD_OBLIGATORIA = "Debe indicar la facultad que se va a coordinar.";
		public static final String JUSTIFICACION_RECHAZO_NO_VALIDA =
				"Debe explicar por qué rechaza el registro (entre 10 y 200 caracteres).";
		public static final String KUDO_CATEGORIA_OBLIGATORIA = "Debe indicar la categoría del Kudo.";
		public static final String KUDO_CONTENIDO_OBLIGATORIO = "Debe escribir el contenido del Kudo.";
		public static final String KUDO_CON_REVISION_EN_CURSO = "El Kudo ya tiene una revisión en curso.";
		public static final String KUDO_DESTINATARIO_OBLIGATORIO = "Debe indicar a quién otorga el Kudo.";
		public static final String KUDO_NO_ADMITE_INTERACCION = "El Kudo fue vetado y ya no admite interacción.";
		public static final String KUDO_NO_ESTA_EN_REVISION = "El Kudo no está en revisión.";
		public static final String KUDO_NO_EXISTE = "No existe el Kudo que se indicó.";
		public static final String KUDO_NO_PUEDE_REPORTARSE = "El Kudo no puede reportarse en su estado actual.";
		public static final String KUDO_NO_PUEDE_VETARSE_DIRECTAMENTE =
				"El Kudo ya está vetado o tiene una revisión en curso.";
		public static final String KUDO_PARA_SI_MISMO = "No puede otorgarse un Kudo a usted mismo.";
		public static final String KUDO_REPETIDO = "Ya se registró ese Kudo.";
		public static final String KUDO_SIN_REPORTES_PENDIENTES = "El Kudo no tiene reportes pendientes por revisar.";
		public static final String KUDO_VETO_OBLIGATORIO =
				"Debe indicar el contenido y escribir la justificación del veto.";
		public static final String MOTIVO_NO_EXISTE = "No existe el motivo que se indicó.";
		public static final String NO_PUEDE_ACTUAR_SOBRE_CUENTA_PROPIA =
				"No puede realizar esta acción sobre su propia cuenta.";
		public static final String PERSONA_CON_CARGO_VIGENTE =
				"Primero debe cerrarse la coordinación o la vinculación vigente de la persona.";
		public static final String PERSONA_NO_ACTIVA = "La persona indicada no existe o su cuenta no está activa.";
		public static final String PERSONA_NO_ASIGNABLE_COMO_COORDINADOR_PROGRAMA =
				"La persona no es un aspirante ni un Coordinador de Programa activo.";
		public static final String PERSONA_NO_ES_COORDINADOR_FACULTAD =
				"La persona indicada no tiene el rol Coordinador de Facultad.";
		public static final String PERSONA_NO_ES_ESTUDIANTE_NI_DOCENTE =
				"Solo se pueden vincular a un programa estudiantes y docentes.";
		public static final String PERSONA_YA_COORDINA_OTRA_UNIDAD = "La persona ya coordina otra unidad académica.";
		public static final String PERSONA_YA_VINCULADA = "La persona ya está vinculada a ese programa.";
		public static final String PROGRAMA_CON_COORDINADOR_VIGENTE =
				"El programa académico ya tiene un coordinador vigente; para cambiarlo, primero "
				+ "debe suspenderse o desactivarse la cuenta del actual.";
		public static final String PROGRAMA_FACULTAD_OBLIGATORIA =
				"Debe indicar la facultad a la que pertenece el programa.";
		public static final String PROGRAMA_NOMBRE_NO_VALIDO =
				"El nombre del programa académico es obligatorio, debe tener entre 5 y 100 "
				+ "caracteres y solo puede contener letras, tildes y espacios.";
		public static final String PROGRAMA_NOMBRE_REPETIDO = "Ya existe un programa con ese nombre.";
		public static final String PROGRAMA_NO_EXISTE = "No existe el programa académico que se indicó.";
		public static final String PROGRAMA_NO_PERTENECE_A_FACULTAD_DEL_COORDINADOR =
				"Ese programa no pertenece a su facultad.";
		public static final String PROGRAMA_OBLIGATORIA = "Debe indicar el programa académico que se va a coordinar.";
		public static final String PROGRAMA_OBLIGATORIO_SEGUN_ROL =
				"Debe indicar al menos un programa académico al que pertenece.";
		public static final String RECTOR_YA_EXISTE = "Ya existe un Rector activo en la universidad.";
		public static final String REGISTRO_YA_DECIDIDO = "El registro de esta persona ya fue decidido.";
		public static final String REPLICA_CONTENIDO_OBLIGATORIO = "Debe escribir el contenido de la réplica.";
		public static final String REPLICA_CON_REVISION_EN_CURSO = "La réplica ya tiene una revisión en curso.";
		public static final String REPLICA_KUDO_OBLIGATORIO = "Debe indicar el Kudo que se replica.";
		public static final String REPLICA_NO_ADMITE_INTERACCION = "La réplica fue vetada y ya no admite interacción.";
		public static final String REPLICA_NO_ESTA_EN_REVISION = "La réplica no está en revisión.";
		public static final String REPLICA_NO_EXISTE = "No existe la réplica que se indicó.";
		public static final String REPLICA_NO_PUEDE_REPORTARSE = "La réplica no puede reportarse en su estado actual.";
		public static final String REPLICA_NO_PUEDE_VETARSE_DIRECTAMENTE =
				"La réplica ya está vetada o tiene una revisión en curso.";
		public static final String REPLICA_PADRE_DE_OTRO_KUDO =
				"La réplica a la que responde no pertenece a esta conversación.";
		public static final String REPLICA_REPETIDA = "Ya se registró esa réplica.";
		public static final String REPLICA_SIN_REPORTES_PENDIENTES =
				"La réplica no tiene reportes pendientes por revisar.";
		public static final String REPLICA_SIN_RESPUESTA_POSIBLE = "No se puede responder a esa réplica.";
		public static final String REPLICA_VETO_OBLIGATORIO =
				"Debe indicar el contenido y escribir la justificación del veto.";
		public static final String REPORTE_DESCRIPCION_NO_VALIDA =
				"Si el motivo es Otro debe describirlo; la descripción puede tener hasta 500 "
				+ "caracteres y solo puede contener letras, números, tildes, espacios y signos de "
				+ "puntuación.";
		public static final String REPORTE_KUDO_OBLIGATORIO = "Debe indicar el Kudo que reporta.";
		public static final String REPORTE_MOTIVO_OBLIGATORIO = "Debe indicar el motivo del reporte.";
		public static final String REPORTE_REPLICA_OBLIGATORIO = "Debe indicar la réplica que reporta.";
		public static final String REPORTE_YA_REGISTRADO = "Usted ya reportó este contenido.";
		public static final String REVISION_JUSTIFICACION_NO_VALIDA =
				"Debe escribir la justificación de la decisión (entre 10 y 1000 caracteres, con "
				+ "letras, números y signos de puntuación).";
		public static final String REVISION_NO_EXISTE = "No existe la revisión que se indicó.";
		public static final String REVISION_REPETIDA = "Ya existe esa revisión.";
		public static final String REVISION_YA_CERRADA = "La revisión ya fue cerrada.";
		public static final String ROL_NO_EXISTE = "No existe el rol que se indicó.";
		public static final String ROL_NO_PERMITE_REGISTRO_PROPIO =
				"No puede registrarse con ese rol; el Rector y los Administradores los registra "
				+ "un Administrador.";
		public static final String ROL_NUEVO_IGUAL_AL_ACTUAL = "La persona ya tiene ese rol.";
		public static final String SIN_REGISTROS_PENDIENTES_A_CARGO = "No tiene registros pendientes a su cargo.";
		public static final String USUARIO_APELLIDO_NO_VALIDO =
				"El apellido es obligatorio, debe tener entre 2 y 60 caracteres y solo puede "
				+ "contener letras, tildes y espacios.";
		public static final String USUARIO_CELULAR_NO_VALIDO = "El celular debe tener 10 dígitos e iniciar en 3.";
		public static final String USUARIO_CELULAR_REPETIDO = "Ya existe una cuenta con ese número celular.";
		public static final String USUARIO_CORREO_NO_VALIDO =
				"El correo debe ser institucional y terminar en @uco.edu.co.";
		public static final String USUARIO_CORREO_REPETIDO = "Ya existe una cuenta con ese correo institucional.";
		public static final String USUARIO_NOMBRE_NO_VALIDO =
				"El nombre es obligatorio, debe tener entre 2 y 60 caracteres y solo puede "
				+ "contener letras, tildes y espacios.";
		public static final String USUARIO_NO_ACTIVO =
				"Su cuenta no está activa, por lo que no puede realizar esta acción.";
		public static final String USUARIO_NO_COORDINA_EL_PROGRAMA = "Usted no coordina ese programa.";
		public static final String USUARIO_NO_ES_ADMINISTRADOR = "Solo un Administrador puede realizar esta acción.";
		public static final String USUARIO_NO_ES_COORDINADOR_ACADEMICO =
				"Solo el Coordinador Académico puede realizar esta acción.";
		public static final String USUARIO_NO_ES_COORDINADOR_FACULTAD =
				"Solo un Coordinador de Facultad puede realizar esta acción.";
		public static final String USUARIO_NO_ES_COORDINADOR_PROGRAMA =
				"Solo un Coordinador de Programa puede realizar esta acción.";
		public static final String USUARIO_NO_ES_TITULAR = "Solo puede realizar esta acción sobre su propia cuenta.";
		public static final String USUARIO_NO_EXISTE = "No existe el usuario que se indicó.";
		public static final String USUARIO_NO_GESTIONA_CATEGORIAS =
				"Solo el Administrador, el Rector y el Coordinador Académico pueden gestionar "
				+ "categorías.";
		public static final String USUARIO_NO_PUEDE_CONSULTAR =
				"No tiene permiso para consultar la información personal de esta persona.";
		public static final String USUARIO_NO_PUEDE_CONSULTAR_AUDITORIA =
				"No tiene permiso para consultar la auditoría.";
		public static final String USUARIO_NO_PUEDE_CONSULTAR_REPORTES =
				"No tiene permiso para consultar los reportes.";
		public static final String USUARIO_NO_PUEDE_MODERAR =
				"Solo el Rector y el Coordinador Académico pueden moderar contenido.";
		public static final String USUARIO_ROL_OBLIGATORIO = "Debe seleccionar el rol con el que se registra.";
		public static final String VINCULACION_FECHA_NO_VALIDA =
				"La fecha de vinculación no puede ser anterior al 01/01/1982 ni posterior a la "
				+ "fecha actual.";
		public static final String VINCULACION_NO_EXISTE = "No existe la vinculación académica que se indicó.";
		public static final String VINCULACION_PERSONA_OBLIGATORIA = "Debe indicar la persona que se vincula.";
		public static final String VINCULACION_PROGRAMA_OBLIGATORIO = "Debe indicar el programa académico.";
		public static final String VINCULACION_REPETIDA = "Ya existe esa vinculación.";
		public static final String VINCULACION_YA_TERMINADA = "La persona ya estaba desvinculada de ese programa.";
	}
}
