package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.FacultadDAO;
import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.FacultadEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class FacultadMySqlDAO extends SqlDAO implements FacultadDAO {

	private static final String SQL_CREAR =
			"INSERT INTO facultad (id, nombre) VALUES (?, ?)";

	private static final String SQL_CONSULTAR =
			"SELECT id, nombre FROM facultad";

	private static final String SQL_ACTUALIZAR =
			"UPDATE facultad SET nombre = ? WHERE id = ?";

	public FacultadMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(FacultadEntidad entidad) {
		ejecutarActualizacion(SQL_CREAR, CatalogoMensajes.Datos.USUARIO_ERROR_CREANDO_FACULTAD,
				entidad.getId().toString(), entidad.getNombre());
	}

	@Override
	public FacultadEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new FacultadEntidad();
		}

		var filtro = new FacultadEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new FacultadEntidad() : resultados.get(0);
	}

	@Override
	public List<FacultadEntidad> consultarPorFiltro(FacultadEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new FacultadEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conTexto("nombre", filtroSeguro.getNombre());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_FACULTADES);
	}

	@Override
	public List<FacultadEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_FACULTADES);
	}

	@Override
	public void actualizar(UUID id, FacultadEntidad entidad) {
		ejecutarActualizacion(SQL_ACTUALIZAR, CatalogoMensajes.Datos.USUARIO_ERROR_ACTUALIZANDO_FACULTAD,
				entidad.getNombre(), id.toString());
	}

	private FacultadEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new FacultadEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.setNombre(resultados.getString("nombre"));
		return entidad;
	}
}
