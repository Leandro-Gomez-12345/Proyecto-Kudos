package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.MotivoDAO;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.MotivoEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class MotivoMySqlDAO extends SqlDAO implements MotivoDAO {

	private static final String SQL_CONSULTAR =
			"SELECT id, nombre FROM motivo";

	public MotivoMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public MotivoEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new MotivoEntidad();
		}

		var filtro = new MotivoEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new MotivoEntidad() : resultados.get(0);
	}

	@Override
	public List<MotivoEntidad> consultarPorFiltro(MotivoEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new MotivoEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conTexto("nombre", filtroSeguro.getNombre());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_MOTIVOS);
	}

	@Override
	public List<MotivoEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_MOTIVOS);
	}

	private MotivoEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new MotivoEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.setNombre(resultados.getString("nombre"));
		return entidad;
	}
}
