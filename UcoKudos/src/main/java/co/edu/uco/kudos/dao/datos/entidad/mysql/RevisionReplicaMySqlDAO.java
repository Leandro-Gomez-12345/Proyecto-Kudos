package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.RevisionReplicaDAO;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.RevisionReplicaEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class RevisionReplicaMySqlDAO extends SqlDAO implements RevisionReplicaDAO {

	private static final String SQL_CREAR =
			"INSERT INTO revisionReplica (id, replica, moderador, estadoRevision, justificacion, "
			+ "fechaInicio, fechaCierre) VALUES (?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_CONSULTAR =
			"SELECT id, replica, moderador, estadoRevision, justificacion, fechaInicio, "
			+ "fechaCierre FROM revisionReplica";

	private static final String SQL_ACTUALIZAR =
			"UPDATE revisionReplica SET replica = ?, moderador = ?, estadoRevision = ?, "
			+ "justificacion = ?, fechaInicio = ?, fechaCierre = ? WHERE id = ?";

	public RevisionReplicaMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(RevisionReplicaEntidad entidad) {
		ejecutarActualizacion(SQL_CREAR, CatalogoMensajes.Datos.USUARIO_ERROR_CREANDO_REVISION_REPLICA,
				entidad.getId().toString(), entidad.getReplica().getId().toString(),
				entidad.getModerador().getId().toString(), entidad.getEstadoRevision().getId().toString(),
				entidad.getJustificacion(), entidad.getFechaInicio(),
				escribirFechaHoraOpcional(entidad.getFechaCierre()));
	}

	@Override
	public RevisionReplicaEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new RevisionReplicaEntidad();
		}

		var filtro = new RevisionReplicaEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new RevisionReplicaEntidad() : resultados.get(0);
	}

	@Override
	public List<RevisionReplicaEntidad> consultarPorFiltro(RevisionReplicaEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new RevisionReplicaEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conId("replica", filtroSeguro.getReplica().getId())
				.conId("moderador", filtroSeguro.getModerador().getId())
				.conId("estadoRevision", filtroSeguro.getEstadoRevision().getId())
				.conTexto("justificacion", filtroSeguro.getJustificacion())
				.conFechaHora("fechaInicio", filtroSeguro.getFechaInicio())
				.conFechaHora("fechaCierre", filtroSeguro.getFechaCierre());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_REVISIONES_REPLICA);
	}

	@Override
	public List<RevisionReplicaEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_REVISIONES_REPLICA);
	}

	@Override
	public void actualizar(UUID id, RevisionReplicaEntidad entidad) {
		ejecutarActualizacion(SQL_ACTUALIZAR, CatalogoMensajes.Datos.USUARIO_ERROR_ACTUALIZANDO_REVISION_REPLICA,
				entidad.getReplica().getId().toString(), entidad.getModerador().getId().toString(),
				entidad.getEstadoRevision().getId().toString(), entidad.getJustificacion(), entidad.getFechaInicio(),
				escribirFechaHoraOpcional(entidad.getFechaCierre()), id.toString());
	}

	private RevisionReplicaEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new RevisionReplicaEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.getReplica().setId(leerUUID(resultados, "replica"));
		entidad.getModerador().setId(leerUUID(resultados, "moderador"));
		entidad.getEstadoRevision().setId(leerUUID(resultados, "estadoRevision"));
		entidad.setJustificacion(resultados.getString("justificacion"));
		entidad.setFechaInicio(resultados.getObject("fechaInicio", LocalDateTime.class));
		entidad.setFechaCierre(resultados.getObject("fechaCierre", LocalDateTime.class));
		return entidad;
	}
}
