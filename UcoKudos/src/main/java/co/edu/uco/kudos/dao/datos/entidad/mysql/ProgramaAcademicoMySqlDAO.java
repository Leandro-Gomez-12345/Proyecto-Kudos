package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.ProgramaAcademicoDAO;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.ProgramaAcademicoEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ProgramaAcademicoMySqlDAO extends SqlDAO implements ProgramaAcademicoDAO {

	private static final String SQL_CREAR =
			"INSERT INTO programaAcademico (id, facultad, nombre) VALUES (?, ?, ?)";

	private static final String SQL_CONSULTAR =
			"SELECT id, facultad, nombre FROM programaAcademico";

	private static final String SQL_ACTUALIZAR =
			"UPDATE programaAcademico SET facultad = ?, nombre = ? WHERE id = ?";

	public ProgramaAcademicoMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(ProgramaAcademicoEntidad entidad) {
		ejecutarActualizacion(SQL_CREAR, CatalogoMensajes.Datos.USUARIO_ERROR_CREANDO_PROGRAMA_ACADEMICO,
				entidad.getId().toString(), entidad.getFacultad().getId().toString(), entidad.getNombre());
	}

	@Override
	public ProgramaAcademicoEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new ProgramaAcademicoEntidad();
		}

		var filtro = new ProgramaAcademicoEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new ProgramaAcademicoEntidad() : resultados.get(0);
	}

	@Override
	public List<ProgramaAcademicoEntidad> consultarPorFiltro(ProgramaAcademicoEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new ProgramaAcademicoEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conId("facultad", filtroSeguro.getFacultad().getId())
				.conTexto("nombre", filtroSeguro.getNombre());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_PROGRAMAS_ACADEMICOS);
	}

	@Override
	public List<ProgramaAcademicoEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_PROGRAMAS_ACADEMICOS);
	}

	@Override
	public void actualizar(UUID id, ProgramaAcademicoEntidad entidad) {
		ejecutarActualizacion(SQL_ACTUALIZAR, CatalogoMensajes.Datos.USUARIO_ERROR_ACTUALIZANDO_PROGRAMA_ACADEMICO,
				entidad.getFacultad().getId().toString(), entidad.getNombre(), id.toString());
	}

	private ProgramaAcademicoEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new ProgramaAcademicoEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.getFacultad().setId(leerUUID(resultados, "facultad"));
		entidad.setNombre(resultados.getString("nombre"));
		return entidad;
	}
}
