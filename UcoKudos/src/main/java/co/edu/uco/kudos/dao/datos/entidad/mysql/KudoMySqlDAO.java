package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.KudoDAO;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.KudoEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class KudoMySqlDAO extends SqlDAO implements KudoDAO {

	private static final String SQL_CREAR =
			"INSERT INTO kudo (id, autor, destinatario, categoria, nombreCategoria, contenido, "
			+ "estadoContenido, fechaCreacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_CONSULTAR =
			"SELECT id, autor, destinatario, categoria, nombreCategoria, contenido, estadoContenido, "
			+ "fechaCreacion FROM kudo";

	private static final String SQL_ACTUALIZAR =
			"UPDATE kudo SET autor = ?, destinatario = ?, categoria = ?, nombreCategoria = ?, "
			+ "contenido = ?, estadoContenido = ?, fechaCreacion = ? WHERE id = ?";

	public KudoMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(KudoEntidad entidad) {
		ejecutarActualizacion(SQL_CREAR, CatalogoMensajes.Datos.USUARIO_ERROR_CREANDO_KUDO,
				entidad.getId().toString(), entidad.getAutor().getId().toString(),
				entidad.getDestinatario().getId().toString(), entidad.getCategoria().getId().toString(),
				entidad.getNombreCategoria(), entidad.getContenido(), entidad.getEstadoContenido().getId().toString(),
				entidad.getFechaCreacion());
	}

	@Override
	public KudoEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new KudoEntidad();
		}

		var filtro = new KudoEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new KudoEntidad() : resultados.get(0);
	}

	@Override
	public List<KudoEntidad> consultarPorFiltro(KudoEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new KudoEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conId("autor", filtroSeguro.getAutor().getId())
				.conId("destinatario", filtroSeguro.getDestinatario().getId())
				.conId("categoria", filtroSeguro.getCategoria().getId())
				.conTexto("nombreCategoria", filtroSeguro.getNombreCategoria())
				.conTexto("contenido", filtroSeguro.getContenido())
				.conId("estadoContenido", filtroSeguro.getEstadoContenido().getId())
				.conFechaHora("fechaCreacion", filtroSeguro.getFechaCreacion());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_KUDOS);
	}

	@Override
	public List<KudoEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_KUDOS);
	}

	@Override
	public void actualizar(UUID id, KudoEntidad entidad) {
		ejecutarActualizacion(SQL_ACTUALIZAR, CatalogoMensajes.Datos.USUARIO_ERROR_ACTUALIZANDO_KUDO,
				entidad.getAutor().getId().toString(), entidad.getDestinatario().getId().toString(),
				entidad.getCategoria().getId().toString(), entidad.getNombreCategoria(), entidad.getContenido(),
				entidad.getEstadoContenido().getId().toString(), entidad.getFechaCreacion(), id.toString());
	}

	private KudoEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new KudoEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.getAutor().setId(leerUUID(resultados, "autor"));
		entidad.getDestinatario().setId(leerUUID(resultados, "destinatario"));
		entidad.getCategoria().setId(leerUUID(resultados, "categoria"));
		entidad.setNombreCategoria(resultados.getString("nombreCategoria"));
		entidad.setContenido(resultados.getString("contenido"));
		entidad.getEstadoContenido().setId(leerUUID(resultados, "estadoContenido"));
		entidad.setFechaCreacion(resultados.getObject("fechaCreacion", LocalDateTime.class));
		return entidad;
	}
}
