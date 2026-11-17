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
}
