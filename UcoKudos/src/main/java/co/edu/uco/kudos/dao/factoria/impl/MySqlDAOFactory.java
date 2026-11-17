package co.edu.uco.kudos.dao.factoria.impl;

import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.kudos.dao.datos.entidad.AuditoriaDAO;
import co.edu.uco.kudos.dao.datos.entidad.CalificacionKudoDAO;
import co.edu.uco.kudos.dao.datos.entidad.CalificacionReplicaDAO;
import co.edu.uco.kudos.dao.datos.entidad.CategoriaDAO;
import co.edu.uco.kudos.dao.datos.entidad.CoordinacionFacultadDAO;
import co.edu.uco.kudos.dao.datos.entidad.CoordinacionProgramaDAO;
import co.edu.uco.kudos.dao.datos.entidad.EstadoContenidoDAO;
import co.edu.uco.kudos.dao.datos.entidad.EstadoCuentaDAO;
import co.edu.uco.kudos.dao.datos.entidad.EstadoRevisionDAO;
import co.edu.uco.kudos.dao.datos.entidad.FacultadDAO;
import co.edu.uco.kudos.dao.datos.entidad.KudoDAO;
import co.edu.uco.kudos.dao.datos.entidad.MotivoDAO;
import co.edu.uco.kudos.dao.datos.entidad.ProgramaAcademicoDAO;
import co.edu.uco.kudos.dao.datos.entidad.ReplicaDAO;
import co.edu.uco.kudos.dao.datos.entidad.ReporteKudoDAO;
import co.edu.uco.kudos.dao.datos.entidad.ReporteReplicaDAO;
import co.edu.uco.kudos.dao.datos.entidad.RevisionKudoDAO;
import co.edu.uco.kudos.dao.datos.entidad.RevisionReplicaDAO;
import co.edu.uco.kudos.dao.datos.entidad.RolDAO;
import co.edu.uco.kudos.dao.datos.entidad.UsuarioDAO;
import co.edu.uco.kudos.dao.datos.entidad.VinculacionAcademicaDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.AuditoriaMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.CalificacionKudoMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.CalificacionReplicaMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.CategoriaMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.CoordinacionFacultadMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.CoordinacionProgramaMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.EstadoContenidoMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.EstadoCuentaMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.EstadoRevisionMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.FacultadMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.KudoMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.MotivoMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.ProgramaAcademicoMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.ReplicaMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.ReporteKudoMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.ReporteReplicaMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.RevisionKudoMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.RevisionReplicaMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.RolMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.UsuarioMySqlDAO;
import co.edu.uco.kudos.dao.datos.entidad.mysql.VinculacionAcademicaMySqlDAO;
import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosDatosExcepcion;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class MySqlDAOFactory extends DAOFactory {

	private static final String VARIABLE_ENTORNO_URL = "UCOKUDOS_DB_URL";
	private static final String VARIABLE_ENTORNO_USUARIO = "UCOKUDOS_DB_USUARIO";
	private static final String VARIABLE_ENTORNO_CLAVE = "UCOKUDOS_DB_CLAVE";

	@Override
	protected void abrirConexion() {
		var url = obtenerVariableEntornoObligatoria(VARIABLE_ENTORNO_URL);
		var usuario = obtenerVariableEntornoObligatoria(VARIABLE_ENTORNO_USUARIO);
		var clave = UtilTexto.obtenerValorDefecto(System.getenv(VARIABLE_ENTORNO_CLAVE));

		try {
			setConexion(DriverManager.getConnection(url, usuario, clave));
		} catch (SQLException excepcion) {
			var mensajeUsuario = CatalogoMensajes.Datos.USUARIO_ERROR_ABRIENDO_CONEXION;
			throw UcoKudosDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public RolDAO obtenerRolDAO() {
		return new RolMySqlDAO(getConexion());
	}

	@Override
	public EstadoCuentaDAO obtenerEstadoCuentaDAO() {
		return new EstadoCuentaMySqlDAO(getConexion());
	}

	@Override
	public EstadoContenidoDAO obtenerEstadoContenidoDAO() {
		return new EstadoContenidoMySqlDAO(getConexion());
	}

	@Override
	public EstadoRevisionDAO obtenerEstadoRevisionDAO() {
		return new EstadoRevisionMySqlDAO(getConexion());
	}

	@Override
	public MotivoDAO obtenerMotivoDAO() {
		return new MotivoMySqlDAO(getConexion());
	}

	@Override
	public CategoriaDAO obtenerCategoriaDAO() {
		return new CategoriaMySqlDAO(getConexion());
	}

	@Override
	public FacultadDAO obtenerFacultadDAO() {
		return new FacultadMySqlDAO(getConexion());
	}

	@Override
	public UsuarioDAO obtenerUsuarioDAO() {
		return new UsuarioMySqlDAO(getConexion());
	}

	@Override
	public ProgramaAcademicoDAO obtenerProgramaAcademicoDAO() {
		return new ProgramaAcademicoMySqlDAO(getConexion());
	}

	@Override
	public AuditoriaDAO obtenerAuditoriaDAO() {
		return new AuditoriaMySqlDAO(getConexion());
	}

	@Override
	public CoordinacionFacultadDAO obtenerCoordinacionFacultadDAO() {
		return new CoordinacionFacultadMySqlDAO(getConexion());
	}

	@Override
	public CoordinacionProgramaDAO obtenerCoordinacionProgramaDAO() {
		return new CoordinacionProgramaMySqlDAO(getConexion());
	}

	@Override
	public VinculacionAcademicaDAO obtenerVinculacionAcademicaDAO() {
		return new VinculacionAcademicaMySqlDAO(getConexion());
	}

	@Override
	public KudoDAO obtenerKudoDAO() {
		return new KudoMySqlDAO(getConexion());
	}

	@Override
	public ReplicaDAO obtenerReplicaDAO() {
		return new ReplicaMySqlDAO(getConexion());
	}

	@Override
	public CalificacionKudoDAO obtenerCalificacionKudoDAO() {
		return new CalificacionKudoMySqlDAO(getConexion());
	}

	@Override
	public RevisionKudoDAO obtenerRevisionKudoDAO() {
		return new RevisionKudoMySqlDAO(getConexion());
	}

	@Override
	public CalificacionReplicaDAO obtenerCalificacionReplicaDAO() {
		return new CalificacionReplicaMySqlDAO(getConexion());
	}

	@Override
	public RevisionReplicaDAO obtenerRevisionReplicaDAO() {
		return new RevisionReplicaMySqlDAO(getConexion());
	}

	@Override
	public ReporteKudoDAO obtenerReporteKudoDAO() {
		return new ReporteKudoMySqlDAO(getConexion());
	}

	@Override
	public ReporteReplicaDAO obtenerReporteReplicaDAO() {
		return new ReporteReplicaMySqlDAO(getConexion());
	}

	private static String obtenerVariableEntornoObligatoria(String nombreVariable) {
		if (UtilTexto.esVacia(nombreVariable)) {
			var mensajeUsuario = CatalogoMensajes.Datos.USUARIO_ERROR_VARIABLE_ENTORNO_NO_DEFINIDA;
			throw UcoKudosDatosExcepcion.crear(mensajeUsuario);
		}

		return nombreVariable;
	}
}
