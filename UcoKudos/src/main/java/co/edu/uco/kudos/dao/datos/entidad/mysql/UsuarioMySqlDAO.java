package co.edu.uco.kudos.dao.datos.entidad.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.datos.entidad.FiltroSql;
import co.edu.uco.kudos.dao.datos.entidad.SqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.UsuarioDAO;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilBooleano;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class UsuarioMySqlDAO extends SqlDAO implements UsuarioDAO {

	private static final String SQL_CREAR =
			"INSERT INTO usuario (id, nombre, apellido, correoInstitucional, celular, correoVerificado, "
			+ "celularVerificado, rol, estadoCuenta, fechaRegistro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_CONSULTAR =
			"SELECT id, nombre, apellido, correoInstitucional, celular, correoVerificado, "
			+ "celularVerificado, rol, estadoCuenta, fechaRegistro FROM usuario";

	private static final String SQL_ACTUALIZAR =
			"UPDATE usuario SET nombre = ?, apellido = ?, correoInstitucional = ?, celular = ?, "
			+ "correoVerificado = ?, celularVerificado = ?, rol = ?, estadoCuenta = ?, "
			+ "fechaRegistro = ? WHERE id = ?";

	public UsuarioMySqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(UsuarioEntidad entidad) {
		ejecutarActualizacion(SQL_CREAR, CatalogoMensajes.Datos.USUARIO_ERROR_CREANDO_USUARIO,
				entidad.getId().toString(), entidad.getNombre(), entidad.getApellido(),
				entidad.getCorreoInstitucional(), entidad.getCelular(), entidad.isCorreoVerificado(),
				entidad.isCelularVerificado(), entidad.getRol().getId().toString(),
				entidad.getEstadoCuenta().getId().toString(), entidad.getFechaRegistro());
	}

	@Override
	public UsuarioEntidad consultarPorId(UUID id) {
		if (UtilUUID.esValorDefecto(id)) {
			return new UsuarioEntidad();
		}

		var filtro = new UsuarioEntidad();
		filtro.setId(id);

		var resultados = consultarPorFiltro(filtro);
		return resultados.isEmpty() ? new UsuarioEntidad() : resultados.get(0);
	}

	@Override
	public List<UsuarioEntidad> consultarPorFiltro(UsuarioEntidad filtro) {
		var filtroSeguro = UtilObjeto.esNulo(filtro) ? new UsuarioEntidad() : filtro;
		var consulta = new FiltroSql(SQL_CONSULTAR)
				.conId("id", filtroSeguro.getId())
				.conTexto("nombre", filtroSeguro.getNombre())
				.conTexto("apellido", filtroSeguro.getApellido())
				.conTexto("correoInstitucional", filtroSeguro.getCorreoInstitucional())
				.conTexto("celular", filtroSeguro.getCelular())
				.conBooleano("correoVerificado", filtroSeguro.isCorreoVerificado(), UtilBooleano.NO)
				.conBooleano("celularVerificado", filtroSeguro.isCelularVerificado(), UtilBooleano.NO)
				.conId("rol", filtroSeguro.getRol().getId())
				.conId("estadoCuenta", filtroSeguro.getEstadoCuenta().getId())
				.conFechaHora("fechaRegistro", filtroSeguro.getFechaRegistro());
		return ejecutarConsulta(consulta.getSentencia(), consulta.getParametros(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_USUARIOS);
	}

	@Override
	public List<UsuarioEntidad> consultarTodos() {
		return ejecutarConsulta(SQL_CONSULTAR, List.of(), this::mapear,
				CatalogoMensajes.Datos.USUARIO_ERROR_CONSULTANDO_USUARIOS);
	}

	@Override
	public void actualizar(UUID id, UsuarioEntidad entidad) {
		ejecutarActualizacion(SQL_ACTUALIZAR, CatalogoMensajes.Datos.USUARIO_ERROR_ACTUALIZANDO_USUARIO,
				entidad.getNombre(), entidad.getApellido(), entidad.getCorreoInstitucional(), entidad.getCelular(),
				entidad.isCorreoVerificado(), entidad.isCelularVerificado(), entidad.getRol().getId().toString(),
				entidad.getEstadoCuenta().getId().toString(), entidad.getFechaRegistro(), id.toString());
	}

	private UsuarioEntidad mapear(ResultSet resultados) throws SQLException {
		var entidad = new UsuarioEntidad();
		entidad.setId(leerUUID(resultados, "id"));
		entidad.setNombre(resultados.getString("nombre"));
		entidad.setApellido(resultados.getString("apellido"));
		entidad.setCorreoInstitucional(resultados.getString("correoInstitucional"));
		entidad.setCelular(resultados.getString("celular"));
		entidad.setCorreoVerificado(resultados.getBoolean("correoVerificado"));
		entidad.setCelularVerificado(resultados.getBoolean("celularVerificado"));
		entidad.getRol().setId(leerUUID(resultados, "rol"));
		entidad.getEstadoCuenta().setId(leerUUID(resultados, "estadoCuenta"));
		entidad.setFechaRegistro(resultados.getObject("fechaRegistro", LocalDateTime.class));
		return entidad;
	}
}
