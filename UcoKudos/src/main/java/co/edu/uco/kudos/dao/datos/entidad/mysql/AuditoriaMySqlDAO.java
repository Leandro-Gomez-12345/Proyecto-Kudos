package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.AuditoriaDAO;
import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.entidad.AuditoriaEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AuditoriaMySqlDAO extends SqlDAO implements AuditoriaDAO {

	private static final String SQL_CREAR =
			"INSERT INTO auditoria (id, usuario, accion, objetoAfectado, registroAfectado, detalle, "
			+ "fechaHora) VALUES (?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_CONSULTAR =
			"SELECT id, usuario, accion, objetoAfectado, registroAfectado, detalle, "
			+ "fechaHora FROM auditoria";

	public AuditoriaMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(AuditoriaEntidad entidad) {
		ejecutarActualizacion(SQL_CREAR, CatalogoMensajes.Datos.USUARIO_ERROR_CREANDO_AUDITORIA,
				entidad.getId().toString(), entidad.getUsuario().getId().toString(), entidad.getAccion(),
				entidad.getObjetoAfectado(), entidad.getRegistroAfectado().toString(), entidad.getDetalle(),
				entidad.getFechaHora());
	}

	@Override
	public AuditoriaEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new AuditoriaEntidad();
		}

		var filtro = new AuditoriaEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new AuditoriaEntidad() : resultados.get(0);
	}

	@Override
	public List<AuditoriaEntidad> consultarPorFiltro(AuditoriaEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new AuditoriaEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conId("usuario", filtroSeguro.getUsuario().getId())
				.conTexto("accion", filtroSeguro.getAccion())
				.conTexto("objetoAfectado", filtroSeguro.getObjetoAfectado())
				.conId("registroAfectado", filtroSeguro.getRegistroAfectado())
				.conTexto("detalle", filtroSeguro.getDetalle())
				.conFechaHora("fechaHora", filtroSeguro.getFechaHora());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_AUDITORIAS);
	}

	@Override
	public List<AuditoriaEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_AUDITORIAS);
	}

	private AuditoriaEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new AuditoriaEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.getUsuario().setId(leerUUID(resultados, "usuario"));
		entidad.setAccion(resultados.getString("accion"));
		entidad.setObjetoAfectado(resultados.getString("objetoAfectado"));
		entidad.setRegistroAfectado(leerUUID(resultados, "registroAfectado"));
		entidad.setDetalle(resultados.getString("detalle"));
		entidad.setFechaHora(resultados.getObject("fechaHora", LocalDateTime.class));
		return entidad;
	}
}
