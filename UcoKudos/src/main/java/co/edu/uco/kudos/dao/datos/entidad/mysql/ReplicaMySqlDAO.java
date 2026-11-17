package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.ReplicaDAO;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.ReplicaEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ReplicaMySqlDAO extends SqlDAO implements ReplicaDAO {

	private static final String SQL_CREAR =
			"INSERT INTO replica (id, kudo, replicaPadre, autor, contenido, estadoContenido, "
			+ "fechaCreacion) VALUES (?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_CONSULTAR =
			"SELECT id, kudo, replicaPadre, autor, contenido, estadoContenido, fechaCreacion FROM replica";

	private static final String SQL_ACTUALIZAR =
			"UPDATE replica SET kudo = ?, replicaPadre = ?, autor = ?, contenido = ?, "
			+ "estadoContenido = ?, fechaCreacion = ? WHERE id = ?";

	public ReplicaMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(ReplicaEntidad entidad) {
		ejecutarActualizacion(SQL_CREAR, CatalogoMensajes.Datos.USUARIO_ERROR_CREANDO_REPLICA,
				entidad.getId().toString(), entidad.getKudo().getId().toString(),
				escribirUUIDOpcional(obtenerIdReplicaPadre(entidad)), entidad.getAutor().getId().toString(),
				entidad.getContenido(), entidad.getEstadoContenido().getId().toString(), entidad.getFechaCreacion());
	}

	@Override
	public ReplicaEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new ReplicaEntidad();
		}

		var filtro = new ReplicaEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new ReplicaEntidad() : resultados.get(0);
	}

	@Override
	public List<ReplicaEntidad> consultarPorFiltro(ReplicaEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new ReplicaEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conId("kudo", filtroSeguro.getKudo().getId())
				.conId("replicaPadre", obtenerIdReplicaPadre(filtroSeguro))
				.conId("autor", filtroSeguro.getAutor().getId())
				.conTexto("contenido", filtroSeguro.getContenido())
				.conId("estadoContenido", filtroSeguro.getEstadoContenido().getId())
				.conFechaHora("fechaCreacion", filtroSeguro.getFechaCreacion());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_REPLICAS);
	}

	@Override
	public List<ReplicaEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_REPLICAS);
	}

	@Override
	public void actualizar(UUID id, ReplicaEntidad entidad) {
		ejecutarActualizacion(SQL_ACTUALIZAR, CatalogoMensajes.Datos.USUARIO_ERROR_ACTUALIZANDO_REPLICA,
				entidad.getKudo().getId().toString(), escribirUUIDOpcional(obtenerIdReplicaPadre(entidad)),
				entidad.getAutor().getId().toString(), entidad.getContenido(),
				entidad.getEstadoContenido().getId().toString(), entidad.getFechaCreacion(), id.toString());
	}

	private static UUID obtenerIdReplicaPadre(ReplicaEntidad replica) {
		if (UtilObjeto.esNulo(replica.getReplicaPadre())) {
			return UtilUUID.obtenerUUIDDefecto();
		}
		return replica.getReplicaPadre().getId();
	}

	private ReplicaEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new ReplicaEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.getKudo().setId(leerUUID(resultados, "kudo"));

		var idReplicaPadre = leerUUID(resultados, "replicaPadre");

		if (!UtilUUID.esValorDefecto(idReplicaPadre)) {
			var replicaPadre = new ReplicaEntidad();
			replicaPadre.setId(idReplicaPadre);
			entidad.setReplicaPadre(replicaPadre);
		}

		entidad.getAutor().setId(leerUUID(resultados, "autor"));
		entidad.setContenido(resultados.getString("contenido"));
		entidad.getEstadoContenido().setId(leerUUID(resultados, "estadoContenido"));
		entidad.setFechaCreacion(resultados.getObject("fechaCreacion", LocalDateTime.class));
		return entidad;
	}
}
