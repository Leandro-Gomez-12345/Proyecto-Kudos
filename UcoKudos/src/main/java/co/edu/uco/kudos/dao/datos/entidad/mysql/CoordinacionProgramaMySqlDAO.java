package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.CoordinacionProgramaDAO;
import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.CoordinacionProgramaEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class CoordinacionProgramaMySqlDAO extends SqlDAO implements CoordinacionProgramaDAO {

	private static final String SQL_CREAR =
			"INSERT INTO coordinacionPrograma (id, usuario, programaAcademico, fechaInicio, "
			+ "fechaFin) VALUES (?, ?, ?, ?, ?)";

	private static final String SQL_CONSULTAR =
			"SELECT id, usuario, programaAcademico, fechaInicio, fechaFin FROM coordinacionPrograma";

	private static final String SQL_ACTUALIZAR =
			"UPDATE coordinacionPrograma SET usuario = ?, programaAcademico = ?, fechaInicio = ?, "
			+ "fechaFin = ? WHERE id = ?";

	public CoordinacionProgramaMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(CoordinacionProgramaEntidad entidad) {
		ejecutarActualizacion(SQL_CREAR, CatalogoMensajes.Datos.USUARIO_ERROR_CREANDO_COORDINACION_PROGRAMA,
				entidad.getId().toString(), entidad.getUsuario().getId().toString(),
				entidad.getProgramaAcademico().getId().toString(), entidad.getFechaInicio(),
				escribirFechaOpcional(entidad.getFechaFin()));
	}

	@Override
	public CoordinacionProgramaEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new CoordinacionProgramaEntidad();
		}

		var filtro = new CoordinacionProgramaEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new CoordinacionProgramaEntidad() : resultados.get(0);
	}

	@Override
	public List<CoordinacionProgramaEntidad> consultarPorFiltro(CoordinacionProgramaEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new CoordinacionProgramaEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conId("usuario", filtroSeguro.getUsuario().getId())
				.conId("programaAcademico", filtroSeguro.getProgramaAcademico().getId())
				.conFecha("fechaInicio", filtroSeguro.getFechaInicio())
				.conFecha("fechaFin", filtroSeguro.getFechaFin());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_COORDINACIONES_PROGRAMA);
	}

	@Override
	public List<CoordinacionProgramaEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_COORDINACIONES_PROGRAMA);
	}

	@Override
	public void actualizar(UUID id, CoordinacionProgramaEntidad entidad) {
		ejecutarActualizacion(SQL_ACTUALIZAR, CatalogoMensajes.Datos.USUARIO_ERROR_ACTUALIZANDO_COORDINACION_PROGRAMA,
				entidad.getUsuario().getId().toString(), entidad.getProgramaAcademico().getId().toString(),
				entidad.getFechaInicio(), escribirFechaOpcional(entidad.getFechaFin()), id.toString());
	}

	private CoordinacionProgramaEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new CoordinacionProgramaEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.getUsuario().setId(leerUUID(resultados, "usuario"));
		entidad.getProgramaAcademico().setId(leerUUID(resultados, "programaAcademico"));
		entidad.setFechaInicio(resultados.getObject("fechaInicio", LocalDate.class));
		entidad.setFechaFin(resultados.getObject("fechaFin", LocalDate.class));
		return entidad;
	}
}
