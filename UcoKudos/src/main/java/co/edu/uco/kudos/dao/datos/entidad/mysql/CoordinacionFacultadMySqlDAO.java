package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.CoordinacionFacultadDAO;
import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.CoordinacionFacultadEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class CoordinacionFacultadMySqlDAO extends SqlDAO implements CoordinacionFacultadDAO {

	private static final String SQL_CREAR =
			"INSERT INTO coordinacionFacultad (id, usuario, facultad, fechaInicio, fechaFin) VALUES (?, "
			+ "?, ?, ?, ?)";

	private static final String SQL_CONSULTAR =
			"SELECT id, usuario, facultad, fechaInicio, fechaFin FROM coordinacionFacultad";

	private static final String SQL_ACTUALIZAR =
			"UPDATE coordinacionFacultad SET usuario = ?, facultad = ?, fechaInicio = ?, "
			+ "fechaFin = ? WHERE id = ?";

	public CoordinacionFacultadMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(CoordinacionFacultadEntidad entidad) {
		ejecutarActualizacion(SQL_CREAR, CatalogoMensajes.Datos.USUARIO_ERROR_CREANDO_COORDINACION_FACULTAD,
				entidad.getId().toString(), entidad.getUsuario().getId().toString(),
				entidad.getFacultad().getId().toString(), entidad.getFechaInicio(),
				escribirFechaOpcional(entidad.getFechaFin()));
	}

	@Override
	public CoordinacionFacultadEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new CoordinacionFacultadEntidad();
		}

		var filtro = new CoordinacionFacultadEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new CoordinacionFacultadEntidad() : resultados.get(0);
	}

	@Override
	public List<CoordinacionFacultadEntidad> consultarPorFiltro(CoordinacionFacultadEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new CoordinacionFacultadEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conId("usuario", filtroSeguro.getUsuario().getId())
				.conId("facultad", filtroSeguro.getFacultad().getId())
				.conFecha("fechaInicio", filtroSeguro.getFechaInicio())
				.conFecha("fechaFin", filtroSeguro.getFechaFin());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_COORDINACIONES_FACULTAD);
	}

	@Override
	public List<CoordinacionFacultadEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_COORDINACIONES_FACULTAD);
	}

	@Override
	public void actualizar(UUID id, CoordinacionFacultadEntidad entidad) {
		ejecutarActualizacion(SQL_ACTUALIZAR, CatalogoMensajes.Datos.USUARIO_ERROR_ACTUALIZANDO_COORDINACION_FACULTAD,
				entidad.getUsuario().getId().toString(), entidad.getFacultad().getId().toString(),
				entidad.getFechaInicio(), escribirFechaOpcional(entidad.getFechaFin()), id.toString());
	}

	private CoordinacionFacultadEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new CoordinacionFacultadEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.getUsuario().setId(leerUUID(resultados, "usuario"));
		entidad.getFacultad().setId(leerUUID(resultados, "facultad"));
		entidad.setFechaInicio(resultados.getObject("fechaInicio", LocalDate.class));
		entidad.setFechaFin(resultados.getObject("fechaFin", LocalDate.class));
		return entidad;
	}
}
