package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.EstadoCuentaDAO;
import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.EstadoCuentaEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class EstadoCuentaMySqlDAO extends SqlDAO implements EstadoCuentaDAO {

	private static final String SQL_CONSULTAR =
			"SELECT id, nombre FROM estadoCuenta";

	public EstadoCuentaMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public EstadoCuentaEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new EstadoCuentaEntidad();
		}

		var filtro = new EstadoCuentaEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new EstadoCuentaEntidad() : resultados.get(0);
	}

	@Override
	public List<EstadoCuentaEntidad> consultarPorFiltro(EstadoCuentaEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new EstadoCuentaEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conTexto("nombre", filtroSeguro.getNombre());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_ESTADOS_CUENTA);
	}

	@Override
	public List<EstadoCuentaEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_ESTADOS_CUENTA);
	}

	private EstadoCuentaEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new EstadoCuentaEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.setNombre(resultados.getString("nombre"));
		return entidad;
	}
}
