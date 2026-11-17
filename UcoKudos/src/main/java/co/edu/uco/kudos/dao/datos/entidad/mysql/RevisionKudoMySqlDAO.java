package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.RevisionKudoDAO;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.RevisionKudoEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class RevisionKudoMySqlDAO extends SqlDAO implements RevisionKudoDAO {

	private static final String SQL_CREAR =
			"INSERT INTO revisionKudo (id, kudo, moderador, estadoRevision, justificacion, fechaInicio, "
			+ "fechaCierre) VALUES (?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_CONSULTAR =
			"SELECT id, kudo, moderador, estadoRevision, justificacion, fechaInicio, "
			+ "fechaCierre FROM revisionKudo";

	private static final String SQL_ACTUALIZAR =
			"UPDATE revisionKudo SET kudo = ?, moderador = ?, estadoRevision = ?, justificacion = ?, "
			+ "fechaInicio = ?, fechaCierre = ? WHERE id = ?";

	public RevisionKudoMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(RevisionKudoEntidad entidad) {
		ejecutarActualizacion(SQL_CREAR, CatalogoMensajes.Datos.USUARIO_ERROR_CREANDO_REVISION_KUDO,
				entidad.getId().toString(), entidad.getKudo().getId().toString(),
				entidad.getModerador().getId().toString(), entidad.getEstadoRevision().getId().toString(),
				entidad.getJustificacion(), entidad.getFechaInicio(),
				escribirFechaHoraOpcional(entidad.getFechaCierre()));
	}

	@Override
	public RevisionKudoEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new RevisionKudoEntidad();
		}

		var filtro = new RevisionKudoEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new RevisionKudoEntidad() : resultados.get(0);
	}

	@Override
	public List<RevisionKudoEntidad> consultarPorFiltro(RevisionKudoEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new RevisionKudoEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conId("kudo", filtroSeguro.getKudo().getId())
				.conId("moderador", filtroSeguro.getModerador().getId())
				.conId("estadoRevision", filtroSeguro.getEstadoRevision().getId())
				.conTexto("justificacion", filtroSeguro.getJustificacion())
				.conFechaHora("fechaInicio", filtroSeguro.getFechaInicio())
				.conFechaHora("fechaCierre", filtroSeguro.getFechaCierre());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_REVISIONES_KUDO);
	}

	@Override
	public List<RevisionKudoEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_REVISIONES_KUDO);
	}

	@Override
	public void actualizar(UUID id, RevisionKudoEntidad entidad) {
		ejecutarActualizacion(SQL_ACTUALIZAR, CatalogoMensajes.Datos.USUARIO_ERROR_ACTUALIZANDO_REVISION_KUDO,
				entidad.getKudo().getId().toString(), entidad.getModerador().getId().toString(),
				entidad.getEstadoRevision().getId().toString(), entidad.getJustificacion(), entidad.getFechaInicio(),
				escribirFechaHoraOpcional(entidad.getFechaCierre()), id.toString());
	}

	private RevisionKudoEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new RevisionKudoEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.getKudo().setId(leerUUID(resultados, "kudo"));
		entidad.getModerador().setId(leerUUID(resultados, "moderador"));
		entidad.getEstadoRevision().setId(leerUUID(resultados, "estadoRevision"));
		entidad.setJustificacion(resultados.getString("justificacion"));
		entidad.setFechaInicio(resultados.getObject("fechaInicio", LocalDateTime.class));
		entidad.setFechaCierre(resultados.getObject("fechaCierre", LocalDateTime.class));
		return entidad;
	}
}
