package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.EstadoRevisionDAO;
import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.EstadoRevisionEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class EstadoRevisionMySqlDAO extends SqlDAO implements EstadoRevisionDAO {

	private static final String SQL_CONSULTAR =
			"SELECT id, nombre FROM estadoRevision";

	public EstadoRevisionMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public EstadoRevisionEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new EstadoRevisionEntidad();
		}

		var filtro = new EstadoRevisionEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new EstadoRevisionEntidad() : resultados.get(0);
	}

	@Override
	public List<EstadoRevisionEntidad> consultarPorFiltro(EstadoRevisionEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new EstadoRevisionEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conTexto("nombre", filtroSeguro.getNombre());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_ESTADOS_REVISION);
	}

	@Override
	public List<EstadoRevisionEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_ESTADOS_REVISION);
	}

	private EstadoRevisionEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new EstadoRevisionEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.setNombre(resultados.getString("nombre"));
		return entidad;
	}
}
