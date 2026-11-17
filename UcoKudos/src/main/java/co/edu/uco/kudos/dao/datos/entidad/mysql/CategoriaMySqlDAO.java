package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.CategoriaDAO;
import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.CategoriaEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilBooleano;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class CategoriaMySqlDAO extends SqlDAO implements CategoriaDAO {

	private static final String SQL_CREAR =
			"INSERT INTO categoria (id, nombre, descripcion, activa) VALUES (?, ?, ?, ?)";

	private static final String SQL_CONSULTAR =
			"SELECT id, nombre, descripcion, activa FROM categoria";

	private static final String SQL_ACTUALIZAR =
			"UPDATE categoria SET nombre = ?, descripcion = ?, activa = ? WHERE id = ?";

	public CategoriaMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(CategoriaEntidad entidad) {
		ejecutarActualizacion(SQL_CREAR, CatalogoMensajes.Datos.USUARIO_ERROR_CREANDO_CATEGORIA,
				entidad.getId().toString(), entidad.getNombre(), entidad.getDescripcion(), entidad.isActiva());
	}

	@Override
	public CategoriaEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new CategoriaEntidad();
		}

		var filtro = new CategoriaEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new CategoriaEntidad() : resultados.get(0);
	}

	@Override
	public List<CategoriaEntidad> consultarPorFiltro(CategoriaEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new CategoriaEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conTexto("nombre", filtroSeguro.getNombre())
				.conTexto("descripcion", filtroSeguro.getDescripcion())
				.conBooleano("activa", filtroSeguro.isActiva(), UtilBooleano.SI);
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_CATEGORIAS);
	}

	@Override
	public List<CategoriaEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_CATEGORIAS);
	}

	@Override
	public void actualizar(UUID id, CategoriaEntidad entidad) {
		ejecutarActualizacion(SQL_ACTUALIZAR, CatalogoMensajes.Datos.USUARIO_ERROR_ACTUALIZANDO_CATEGORIA,
				entidad.getNombre(), entidad.getDescripcion(), entidad.isActiva(), id.toString());
	}

	private CategoriaEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new CategoriaEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.setNombre(resultados.getString("nombre"));
		entidad.setDescripcion(resultados.getString("descripcion"));
		entidad.setActiva(resultados.getBoolean("activa"));
		return entidad;
	}
}
