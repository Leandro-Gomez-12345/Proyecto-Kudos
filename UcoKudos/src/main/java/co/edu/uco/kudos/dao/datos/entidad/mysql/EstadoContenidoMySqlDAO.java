package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.EstadoContenidoDAO;
import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.EstadoContenidoEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class EstadoContenidoMySqlDAO extends SqlDAO implements EstadoContenidoDAO {

	private static final String SQL_CONSULTAR =
			"SELECT id, nombre FROM estadoContenido";

	public EstadoContenidoMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public EstadoContenidoEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new EstadoContenidoEntidad();
		}

		var filtro = new EstadoContenidoEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new EstadoContenidoEntidad() : resultados.get(0);
	}

	@Override
	public List<EstadoContenidoEntidad> consultarPorFiltro(EstadoContenidoEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new EstadoContenidoEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conTexto("nombre", filtroSeguro.getNombre());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_ESTADOS_CONTENIDO);
	}

	@Override
	public List<EstadoContenidoEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_ESTADOS_CONTENIDO);
	}

	private EstadoContenidoEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new EstadoContenidoEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.setNombre(resultados.getString("nombre"));
		return entidad;
	}
}
