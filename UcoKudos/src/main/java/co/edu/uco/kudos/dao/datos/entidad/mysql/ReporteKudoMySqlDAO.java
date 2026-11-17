package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.ReporteKudoDAO;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.ReporteKudoEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ReporteKudoMySqlDAO extends SqlDAO implements ReporteKudoDAO {

	private static final String SQL_CREAR =
			"INSERT INTO reporteKudo (id, kudo, usuario, motivo, descripcion, revisionKudo, "
			+ "fechaReporte) VALUES (?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_CONSULTAR =
			"SELECT id, kudo, usuario, motivo, descripcion, revisionKudo, fechaReporte FROM reporteKudo";

	private static final String SQL_ACTUALIZAR =
			"UPDATE reporteKudo SET kudo = ?, usuario = ?, motivo = ?, descripcion = ?, revisionKudo = ?, "
			+ "fechaReporte = ? WHERE id = ?";

	public ReporteKudoMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(ReporteKudoEntidad entidad) {
		ejecutarActualizacion(SQL_CREAR, CatalogoMensajes.Datos.USUARIO_ERROR_CREANDO_REPORTE_KUDO,
				entidad.getId().toString(), entidad.getKudo().getId().toString(),
				entidad.getUsuario().getId().toString(), entidad.getMotivo().getId().toString(),
				entidad.getDescripcion(), escribirUUIDOpcional(entidad.getRevisionKudo().getId()),
				entidad.getFechaReporte());
	}

	@Override
	public ReporteKudoEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new ReporteKudoEntidad();
		}

		var filtro = new ReporteKudoEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new ReporteKudoEntidad() : resultados.get(0);
	}

	@Override
	public List<ReporteKudoEntidad> consultarPorFiltro(ReporteKudoEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new ReporteKudoEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conId("kudo", filtroSeguro.getKudo().getId())
				.conId("usuario", filtroSeguro.getUsuario().getId())
				.conId("motivo", filtroSeguro.getMotivo().getId())
				.conTexto("descripcion", filtroSeguro.getDescripcion())
				.conId("revisionKudo", filtroSeguro.getRevisionKudo().getId())
				.conFechaHora("fechaReporte", filtroSeguro.getFechaReporte());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_REPORTES_KUDO);
	}

	@Override
	public List<ReporteKudoEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_REPORTES_KUDO);
	}

	@Override
	public void actualizar(UUID id, ReporteKudoEntidad entidad) {
		ejecutarActualizacion(SQL_ACTUALIZAR, CatalogoMensajes.Datos.USUARIO_ERROR_ACTUALIZANDO_REPORTE_KUDO,
				entidad.getKudo().getId().toString(), entidad.getUsuario().getId().toString(),
				entidad.getMotivo().getId().toString(), entidad.getDescripcion(),
				escribirUUIDOpcional(entidad.getRevisionKudo().getId()), entidad.getFechaReporte(), id.toString());
	}

	private ReporteKudoEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new ReporteKudoEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.getKudo().setId(leerUUID(resultados, "kudo"));
		entidad.getUsuario().setId(leerUUID(resultados, "usuario"));
		entidad.getMotivo().setId(leerUUID(resultados, "motivo"));
		entidad.setDescripcion(resultados.getString("descripcion"));
		entidad.getRevisionKudo().setId(leerUUID(resultados, "revisionKudo"));
		entidad.setFechaReporte(resultados.getObject("fechaReporte", LocalDateTime.class));
		return entidad;
	}
}
