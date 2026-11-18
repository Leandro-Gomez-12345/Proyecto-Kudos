package co.edu.uco.kudos.dao.datos.entidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.transversal.excepciones.UcoKudosDatosExcepcion;
import co.edu.uco.kudos.transversal.logger.UcoKudosLogger;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilSQL;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public abstract class SqlDAO {

	private static final UcoKudosLogger LOGGER = UcoKudosLogger.obtener(SqlDAO.class);
	private static final long NANOSEGUNDOS_POR_MILISEGUNDO = 1_000_000L;
	private static final long MILISEGUNDOS_SENTENCIA_LENTA = 500L;

	private Connection conexion;

	protected SqlDAO(Connection conexion) {
		setConexion(conexion);
	}

	protected Connection getConexion() {
		return conexion;
	}

	private void setConexion(Connection conexion) {
		UtilSQL.asegurarConexionAbierta(conexion);
		this.conexion = conexion;
	}

	protected void ejecutarActualizacion(String sql, String mensajeUsuario, Object... parametros) {
		LOGGER.debug("{} [{} parámetros]", sql, parametros.length);
		var inicio = System.nanoTime();
		try (var sentencia = getConexion().prepareStatement(sql)) {
			asignarParametros(sentencia, Arrays.asList(parametros));
			sentencia.executeUpdate();
		} catch (SQLException excepcion) {
			throw UcoKudosDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
		registrarSentenciaLenta(sql, inicio);
	}

	protected <E> List<E> ejecutarConsulta(String sql, List<Object> parametros, MapeadorFila<E> mapeador,
			String mensajeUsuario) {
		LOGGER.debug("{} [{} parámetros]", sql, parametros.size());
		var inicio = System.nanoTime();
		var resultadosConsulta = new ArrayList<E>();
		try (var sentencia = getConexion().prepareStatement(sql)) {
			asignarParametros(sentencia, parametros);
			try (var resultados = sentencia.executeQuery()) {
				while (resultados.next()) {
					resultadosConsulta.add(mapeador.mapear(resultados));
				}
			}
		} catch (SQLException excepcion) {
			throw UcoKudosDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
		registrarSentenciaLenta(sql, inicio);
		return resultadosConsulta;
	}

	protected static UUID leerUUID(ResultSet resultados, String columna) throws SQLException {
		return UtilUUID.convertirAUUID(resultados.getString(columna));
	}

	protected static String escribirUUIDOpcional(UUID valor) {
		return UtilUUID.esValorDefecto(valor) ? null : valor.toString();
	}

	protected static LocalDate escribirFechaOpcional(LocalDate valor) {
		return UtilFecha.esValorDefecto(valor) ? null : valor;
	}

	protected static LocalDateTime escribirFechaHoraOpcional(LocalDateTime valor) {
		return UtilFechaHora.esValorDefecto(valor) ? null : valor;
	}

	private static void registrarSentenciaLenta(String sql, long inicio) {
		var milisegundos = (System.nanoTime() - inicio) / NANOSEGUNDOS_POR_MILISEGUNDO;
		if (milisegundos >= MILISEGUNDOS_SENTENCIA_LENTA) {
			LOGGER.advertencia("Sentencia lenta ({} ms): {}", milisegundos, sql);
		}
	}

	private static void asignarParametros(PreparedStatement sentencia, List<Object> parametros)
			throws SQLException {
		for (int posicion = 0; posicion < parametros.size(); posicion++) {
			sentencia.setObject(posicion + 1, parametros.get(posicion));
		}
	}
}
