package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.VinculacionAcademicaDAO;
import co.edu.uco.kudos.entidad.VinculacionAcademicaEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class VinculacionAcademicaMySqlDAO extends SqlDAO implements VinculacionAcademicaDAO {

	private static final String SQL_CREAR =
			"INSERT INTO vinculacionAcademica (id, usuario, programaAcademico, fechaVinculacion, "
			+ "fechaDesvinculacion) VALUES (?, ?, ?, ?, ?)";

	private static final String SQL_CONSULTAR =
			"SELECT id, usuario, programaAcademico, fechaVinculacion, "
			+ "fechaDesvinculacion FROM vinculacionAcademica";

	private static final String SQL_ACTUALIZAR =
			"UPDATE vinculacionAcademica SET usuario = ?, programaAcademico = ?, fechaVinculacion = ?, "
			+ "fechaDesvinculacion = ? WHERE id = ?";

	public VinculacionAcademicaMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(VinculacionAcademicaEntidad entidad) {
		ejecutarActualizacion(SQL_CREAR, CatalogoMensajes.Datos.USUARIO_ERROR_CREANDO_VINCULACION_ACADEMICA,
				entidad.getId().toString(), entidad.getUsuario().getId().toString(),
				entidad.getProgramaAcademico().getId().toString(), entidad.getFechaVinculacion(),
				escribirFechaOpcional(entidad.getFechaDesvinculacion()));
	}

	@Override
	public VinculacionAcademicaEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new VinculacionAcademicaEntidad();
		}

		var filtro = new VinculacionAcademicaEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new VinculacionAcademicaEntidad() : resultados.get(0);
	}

	@Override
	public List<VinculacionAcademicaEntidad> consultarPorFiltro(VinculacionAcademicaEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new VinculacionAcademicaEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conId("usuario", filtroSeguro.getUsuario().getId())
				.conId("programaAcademico", filtroSeguro.getProgramaAcademico().getId())
				.conFecha("fechaVinculacion", filtroSeguro.getFechaVinculacion())
				.conFecha("fechaDesvinculacion", filtroSeguro.getFechaDesvinculacion());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_VINCULACIONES_ACADEMICAS);
	}

	@Override
	public List<VinculacionAcademicaEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_VINCULACIONES_ACADEMICAS);
	}

	@Override
	public void actualizar(UUID id, VinculacionAcademicaEntidad entidad) {
		ejecutarActualizacion(SQL_ACTUALIZAR, CatalogoMensajes.Datos.USUARIO_ERROR_ACTUALIZANDO_VINCULACION_ACADEMICA,
				entidad.getUsuario().getId().toString(), entidad.getProgramaAcademico().getId().toString(),
				entidad.getFechaVinculacion(), escribirFechaOpcional(entidad.getFechaDesvinculacion()), id.toString());
	}

	private VinculacionAcademicaEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new VinculacionAcademicaEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.getUsuario().setId(leerUUID(resultados, "usuario"));
		entidad.getProgramaAcademico().setId(leerUUID(resultados, "programaAcademico"));
		entidad.setFechaVinculacion(resultados.getObject("fechaVinculacion", LocalDate.class));
		entidad.setFechaDesvinculacion(resultados.getObject("fechaDesvinculacion", LocalDate.class));
		return entidad;
	}
}
