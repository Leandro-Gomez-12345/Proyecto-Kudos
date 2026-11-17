package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.CalificacionKudoDAO;
import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.CalificacionKudoEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class CalificacionKudoMySqlDAO extends SqlDAO implements CalificacionKudoDAO {

	private static final String SQL_CREAR =
			"INSERT INTO calificacionKudo (id, kudo, usuario, estrellas, fechaCalificacion) VALUES (?, ?, "
			+ "?, ?, ?)";

	private static final String SQL_CONSULTAR =
			"SELECT id, kudo, usuario, estrellas, fechaCalificacion FROM calificacionKudo";

	private static final String SQL_ACTUALIZAR =
			"UPDATE calificacionKudo SET kudo = ?, usuario = ?, estrellas = ?, "
			+ "fechaCalificacion = ? WHERE id = ?";

	public CalificacionKudoMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(CalificacionKudoEntidad entidad) {
		ejecutarActualizacion(SQL_CREAR, CatalogoMensajes.Datos.USUARIO_ERROR_CREANDO_CALIFICACION_KUDO,
				entidad.getId().toString(), entidad.getKudo().getId().toString(),
				entidad.getUsuario().getId().toString(), entidad.getEstrellas(), entidad.getFechaCalificacion());
	}

	@Override
	public CalificacionKudoEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new CalificacionKudoEntidad();
		}

		var filtro = new CalificacionKudoEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new CalificacionKudoEntidad() : resultados.get(0);
	}

	@Override
	public List<CalificacionKudoEntidad> consultarPorFiltro(CalificacionKudoEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new CalificacionKudoEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conId("kudo", filtroSeguro.getKudo().getId())
				.conId("usuario", filtroSeguro.getUsuario().getId())
				.conEntero("estrellas", filtroSeguro.getEstrellas())
				.conFechaHora("fechaCalificacion", filtroSeguro.getFechaCalificacion());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_CALIFICACIONES_KUDO);
	}

	@Override
	public List<CalificacionKudoEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_CALIFICACIONES_KUDO);
	}

	@Override
	public void actualizar(UUID id, CalificacionKudoEntidad entidad) {
		ejecutarActualizacion(SQL_ACTUALIZAR, CatalogoMensajes.Datos.USUARIO_ERROR_ACTUALIZANDO_CALIFICACION_KUDO,
				entidad.getKudo().getId().toString(), entidad.getUsuario().getId().toString(), entidad.getEstrellas(),
				entidad.getFechaCalificacion(), id.toString());
	}

	private CalificacionKudoEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new CalificacionKudoEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.getKudo().setId(leerUUID(resultados, "kudo"));
		entidad.getUsuario().setId(leerUUID(resultados, "usuario"));
		entidad.setEstrellas(resultados.getInt("estrellas"));
		entidad.setFechaCalificacion(resultados.getObject("fechaCalificacion", LocalDateTime.class));
		return entidad;
	}
}
