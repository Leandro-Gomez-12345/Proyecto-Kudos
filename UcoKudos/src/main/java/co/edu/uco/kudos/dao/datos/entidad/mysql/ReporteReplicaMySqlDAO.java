package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.ReporteReplicaDAO;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.ReporteReplicaEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ReporteReplicaMySqlDAO extends SqlDAO implements ReporteReplicaDAO {

	private static final String SQL_CREAR =
			"INSERT INTO reporteReplica (id, replica, usuario, motivo, descripcion, revisionReplica, "
			+ "fechaReporte) VALUES (?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_CONSULTAR =
			"SELECT id, replica, usuario, motivo, descripcion, revisionReplica, "
			+ "fechaReporte FROM reporteReplica";

	private static final String SQL_ACTUALIZAR =
			"UPDATE reporteReplica SET replica = ?, usuario = ?, motivo = ?, descripcion = ?, "
			+ "revisionReplica = ?, fechaReporte = ? WHERE id = ?";

	public ReporteReplicaMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(ReporteReplicaEntidad entidad) {
		ejecutarActualizacion(SQL_CREAR, CatalogoMensajes.Datos.USUARIO_ERROR_CREANDO_REPORTE_REPLICA,
				entidad.getId().toString(), entidad.getReplica().getId().toString(),
				entidad.getUsuario().getId().toString(), entidad.getMotivo().getId().toString(),
				entidad.getDescripcion(), escribirUUIDOpcional(entidad.getRevisionReplica().getId()),
				entidad.getFechaReporte());
	}

	@Override
	public ReporteReplicaEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new ReporteReplicaEntidad();
		}

		var filtro = new ReporteReplicaEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new ReporteReplicaEntidad() : resultados.get(0);
	}

	@Override
	public List<ReporteReplicaEntidad> consultarPorFiltro(ReporteReplicaEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new ReporteReplicaEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conId("replica", filtroSeguro.getReplica().getId())
				.conId("usuario", filtroSeguro.getUsuario().getId())
				.conId("motivo", filtroSeguro.getMotivo().getId())
				.conTexto("descripcion", filtroSeguro.getDescripcion())
				.conId("revisionReplica", filtroSeguro.getRevisionReplica().getId())
				.conFechaHora("fechaReporte", filtroSeguro.getFechaReporte());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_REPORTES_REPLICA);
	}

	@Override
	public List<ReporteReplicaEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_REPORTES_REPLICA);
	}

	@Override
	public void actualizar(UUID id, ReporteReplicaEntidad entidad) {
		ejecutarActualizacion(SQL_ACTUALIZAR, CatalogoMensajes.Datos.USUARIO_ERROR_ACTUALIZANDO_REPORTE_REPLICA,
				entidad.getReplica().getId().toString(), entidad.getUsuario().getId().toString(),
				entidad.getMotivo().getId().toString(), entidad.getDescripcion(),
				escribirUUIDOpcional(entidad.getRevisionReplica().getId()), entidad.getFechaReporte(), id.toString());
	}

	private ReporteReplicaEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new ReporteReplicaEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.getReplica().setId(leerUUID(resultados, "replica"));
		entidad.getUsuario().setId(leerUUID(resultados, "usuario"));
		entidad.getMotivo().setId(leerUUID(resultados, "motivo"));
		entidad.setDescripcion(resultados.getString("descripcion"));
		entidad.getRevisionReplica().setId(leerUUID(resultados, "revisionReplica"));
		entidad.setFechaReporte(resultados.getObject("fechaReporte", LocalDateTime.class));
		return entidad;
	}
}
