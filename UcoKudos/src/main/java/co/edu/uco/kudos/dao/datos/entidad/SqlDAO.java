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
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilSQL;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public abstract class SqlDAO {

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
		try (var sentencia = getConexion().prepareStatement(sql)) {
			asignarParametros(sentencia, Arrays.asList(parametros));
			sentencia.executeUpdate();
		} catch (SQLException excepcion) {
			throw UcoKudosDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	protected <E> List<E> ejecutarConsulta(String sql, List<Object> parametros, MapeadorFila<E> mapeador,
			String mensajeUsuario) {
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

	private static void asignarParametros(PreparedStatement sentencia, List<Object> parametros)
			throws SQLException {
		for (int posicion = 0; posicion < parametros.size(); posicion++) {
			sentencia.setObject(posicion + 1, parametros.get(posicion));
		}
	}
}
