package co.edu.uco.kudos.transversal.utilitarios;

import java.sql.Connection;
import java.sql.SQLException;

import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosTransversalExcepcion;

public final class UtilSQL {

	private static final int SEGUNDOS_VALIDACION_CONEXION = 5;

	private UtilSQL() {

	}

	public static boolean conexionEstaVacia(Connection conexion) {
		return UtilObjeto.esNulo(conexion);
	}

	public static boolean conexionEstaAbierta(Connection conexion) {
		try {
			return !conexionEstaVacia(conexion) && !conexion.isClosed();
		} catch (SQLException excepcion) {
			var mensajeUsuario = CatalogoMensajes.UtilSQL.USUARIO_ERROR_PROBLEMA_VALIDANDO_SI_CONEXION_SQL_ESTA_ABIERTA;
			throw UcoKudosTransversalExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.UtilSQL.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_SI_CONEXION_SQL_ESTA_ABIERTA;
			throw UcoKudosTransversalExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	public static boolean conexionEsValida(Connection conexion) {
		try {
			return conexionEstaAbierta(conexion) && conexion.isValid(SEGUNDOS_VALIDACION_CONEXION);
		} catch (SQLException excepcion) {
			var mensajeUsuario = CatalogoMensajes.UtilSQL.USUARIO_ERROR_PROBLEMA_VALIDANDO_SI_CONEXION_SQL_ES_VALIDA;
			throw UcoKudosTransversalExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	public static void asegurarConexionAbierta(Connection conexion) {
		if (!conexionEstaAbierta(conexion)) {
			var mensajeUsuario = CatalogoMensajes.UtilSQL.USUARIO_ERROR_CONEXION_SQL_NO_ESTA_ABIERTA;
			throw UcoKudosTransversalExcepcion.crear(mensajeUsuario);
		}
	}

	public static void asegurarConexionValida(Connection conexion) {
		asegurarConexionAbierta(conexion);
		if (!conexionEsValida(conexion)) {
			var mensajeUsuario = CatalogoMensajes.UtilSQL.USUARIO_ERROR_CONEXION_SQL_NO_ES_VALIDA;
			throw UcoKudosTransversalExcepcion.crear(mensajeUsuario);
		}
	}

	public static boolean transaccionEstaIniciada(Connection conexion) {
		try {
			return conexionEstaAbierta(conexion) && !conexion.getAutoCommit();
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (SQLException excepcion) {
			var mensajeUsuario = CatalogoMensajes.UtilSQL.USUARIO_ERROR_PROBLEMA_VALIDANDO_SI_TRANSACCION_SQL_ESTA_INICIADA;
			throw UcoKudosTransversalExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.UtilSQL.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_VALIDANDO_SI_TRANSACCION_SQL_ESTA_INICIADA;
			throw UcoKudosTransversalExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	public static void iniciarTransaccion(Connection conexion) {
		asegurarConexionAbierta(conexion);
		if (transaccionEstaIniciada(conexion)) {
			var mensajeUsuario = CatalogoMensajes.UtilSQL.USUARIO_ERROR_NO_ES_POSIBLE_INICIAR_TRANSACCION_SQL;
			throw UcoKudosTransversalExcepcion.crear(mensajeUsuario);
		}

		try {
			conexion.setAutoCommit(false);
		} catch (SQLException excepcion) {
			var mensajeUsuario = CatalogoMensajes.UtilSQL.USUARIO_ERROR_INICIANDO_TRANSACCION_SQL;
			throw UcoKudosTransversalExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	public static void confirmarTransaccion(Connection conexion) {
		asegurarTransaccionIniciada(conexion,
				CatalogoMensajes.UtilSQL.USUARIO_ERROR_NO_ES_POSIBLE_CONFIRMAR_TRANSACCION_SQL);

		try {
			conexion.commit();
			conexion.setAutoCommit(true);
		} catch (SQLException excepcion) {
			var mensajeUsuario = CatalogoMensajes.UtilSQL.USUARIO_ERROR_CONFIRMANDO_TRANSACCION_SQL;
			throw UcoKudosTransversalExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	public static void cancelarTransaccion(Connection conexion) {
		asegurarTransaccionIniciada(conexion,
				CatalogoMensajes.UtilSQL.USUARIO_ERROR_NO_ES_POSIBLE_CANCELAR_TRANSACCION_SQL);

		try {
			conexion.rollback();
			conexion.setAutoCommit(true);
		} catch (SQLException excepcion) {
			var mensajeUsuario = CatalogoMensajes.UtilSQL.USUARIO_ERROR_CANCELANDO_TRANSACCION_SQL;
			throw UcoKudosTransversalExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	public static void cerrarConexion(Connection conexion) {
		if (!conexionEstaAbierta(conexion)) {
			return;
		}

		try {
			if (transaccionEstaIniciada(conexion)) {
				conexion.rollback();
			}
			conexion.close();
		} catch (SQLException excepcion) {
			var mensajeUsuario = CatalogoMensajes.UtilSQL.USUARIO_ERROR_CERRANDO_CONEXION_SQL;
			throw UcoKudosTransversalExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	private static void asegurarTransaccionIniciada(Connection conexion, String mensajeUsuario) {
		if (!transaccionEstaIniciada(conexion)) {
			throw UcoKudosTransversalExcepcion.crear(mensajeUsuario);
		}
	}
}
