package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.RolDAO;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.RolEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class RolMySqlDAO extends SqlDAO implements RolDAO {

	private static final String SQL_CONSULTAR =
			"SELECT id, nombre FROM rol";

	public RolMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public RolEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new RolEntidad();
		}

		var filtro = new RolEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new RolEntidad() : resultados.get(0);
	}

	@Override
	public List<RolEntidad> consultarPorFiltro(RolEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new RolEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conTexto("nombre", filtroSeguro.getNombre());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_ROLES);
	}

	@Override
	public List<RolEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_ROLES);
	}

	private RolEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new RolEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.setNombre(resultados.getString("nombre"));
		return entidad;
	}
}
