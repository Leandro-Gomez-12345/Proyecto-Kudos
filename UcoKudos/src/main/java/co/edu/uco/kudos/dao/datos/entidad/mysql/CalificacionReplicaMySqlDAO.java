package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.CalificacionReplicaDAO;
import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.CalificacionReplicaEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class CalificacionReplicaMySqlDAO extends SqlDAO implements CalificacionReplicaDAO {

	private static final String SQL_CREAR =
			"INSERT INTO calificacionReplica (id, replica, usuario, estrellas, "
			+ "fechaCalificacion) VALUES (?, ?, ?, ?, ?)";

	private static final String SQL_CONSULTAR =
			"SELECT id, replica, usuario, estrellas, fechaCalificacion FROM calificacionReplica";

	private static final String SQL_ACTUALIZAR =
			"UPDATE calificacionReplica SET replica = ?, usuario = ?, estrellas = ?, "
			+ "fechaCalificacion = ? WHERE id = ?";

	public CalificacionReplicaMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(CalificacionReplicaEntidad entidad) {
		ejecutarActualizacion(SQL_CREAR, CatalogoMensajes.Datos.USUARIO_ERROR_CREANDO_CALIFICACION_REPLICA,
				entidad.getId().toString(), entidad.getReplica().getId().toString(),
				entidad.getUsuario().getId().toString(), entidad.getEstrellas(), entidad.getFechaCalificacion());
	}

	@Override
	public CalificacionReplicaEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new CalificacionReplicaEntidad();
		}

		var filtro = new CalificacionReplicaEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new CalificacionReplicaEntidad() : resultados.get(0);
	}

	@Override
	public List<CalificacionReplicaEntidad> consultarPorFiltro(CalificacionReplicaEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new CalificacionReplicaEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conId("replica", filtroSeguro.getReplica().getId())
				.conId("usuario", filtroSeguro.getUsuario().getId())
				.conEntero("estrellas", filtroSeguro.getEstrellas())
				.conFechaHora("fechaCalificacion", filtroSeguro.getFechaCalificacion());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_CALIFICACIONES_REPLICA);
	}

	@Override
	public List<CalificacionReplicaEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_CALIFICACIONES_REPLICA);
	}

	@Override
	public void actualizar(UUID id, CalificacionReplicaEntidad entidad) {
		ejecutarActualizacion(SQL_ACTUALIZAR, CatalogoMensajes.Datos.USUARIO_ERROR_ACTUALIZANDO_CALIFICACION_REPLICA,
				entidad.getReplica().getId().toString(), entidad.getUsuario().getId().toString(),
				entidad.getEstrellas(), entidad.getFechaCalificacion(), id.toString());
	}

	private CalificacionReplicaEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new CalificacionReplicaEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.getReplica().setId(leerUUID(resultados, "replica"));
		entidad.getUsuario().setId(leerUUID(resultados, "usuario"));
		entidad.setEstrellas(resultados.getInt("estrellas"));
		entidad.setFechaCalificacion(resultados.getObject("fechaCalificacion", LocalDateTime.class));
		return entidad;
	}
}
