package co.edu.uco.kudos.dao.factoria;

import java.sql.Connection;

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
import co.edu.uco.kudos.dao.factoria.enums.FactoriaEnum;
import co.edu.uco.kudos.dao.factoria.impl.MySqlDAOFactory;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosDatosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.logger.UcoKudosLogger;
import co.edu.uco.kudos.transversal.utilitarios.UtilSQL;

public abstract class DAOFactory {

	private static final UcoKudosLogger LOGGER = UcoKudosLogger.obtener(DAOFactory.class);
	private static final FactoriaEnum FACTORIA = FactoriaEnum.MYSQL;

	private Connection conexion;

	public static DAOFactory obtenerFactoria() {
		switch (FACTORIA) {
		case MYSQL: {
			return new MySqlDAOFactory();
		}
		default:
			throw UcoKudosDatosExcepcion.crear(CatalogoMensajes.Datos.USUARIO_ERROR_FACTORIA_NO_HABILITADA);
		}
	}

	protected DAOFactory() {
		abrirConexion();
	}

	protected Connection getConexion() {
		return conexion;
	}

	protected void setConexion(Connection conexion) {
		UtilSQL.asegurarConexionValida(conexion);
		this.conexion = conexion;
	}

	protected abstract void abrirConexion();

	public void iniciarTransaccion() {
		UtilSQL.iniciarTransaccion(conexion);
		LOGGER.debug("Transacción iniciada");
	}

	public void confirmarTransaccion() {
		UtilSQL.confirmarTransaccion(conexion);
		LOGGER.debug("Transacción confirmada");
	}

	public void cancelarTransaccion() {
		ejecutarRegistrandoFalla(() -> {
			UtilSQL.cancelarTransaccion(conexion);
			LOGGER.debug("Transacción cancelada");
		});
	}

	public void cerrarConexion() {
		ejecutarRegistrandoFalla(() -> {
			var estabaAbierta = UtilSQL.conexionEstaAbierta(conexion);
			UtilSQL.cerrarConexion(conexion);
			if (estabaAbierta) {
				LOGGER.debug("Conexión cerrada");
			}
		});
	}

	private static void ejecutarRegistrandoFalla(Runnable accion) {
		try {
			accion.run();
		} catch (UcoKudosExcepcion excepcion) {
			LOGGER.registrarExcepcion(excepcion);
			throw excepcion;
		}
	}

	public abstract RolDAO obtenerRolDAO();

	public abstract EstadoCuentaDAO obtenerEstadoCuentaDAO();

	public abstract EstadoContenidoDAO obtenerEstadoContenidoDAO();

	public abstract EstadoRevisionDAO obtenerEstadoRevisionDAO();

	public abstract MotivoDAO obtenerMotivoDAO();

	public abstract CategoriaDAO obtenerCategoriaDAO();

	public abstract FacultadDAO obtenerFacultadDAO();

	public abstract UsuarioDAO obtenerUsuarioDAO();

	public abstract ProgramaAcademicoDAO obtenerProgramaAcademicoDAO();

	public abstract AuditoriaDAO obtenerAuditoriaDAO();

	public abstract CoordinacionFacultadDAO obtenerCoordinacionFacultadDAO();

	public abstract CoordinacionProgramaDAO obtenerCoordinacionProgramaDAO();

	public abstract VinculacionAcademicaDAO obtenerVinculacionAcademicaDAO();

	public abstract KudoDAO obtenerKudoDAO();

	public abstract ReplicaDAO obtenerReplicaDAO();

	public abstract CalificacionKudoDAO obtenerCalificacionKudoDAO();

	public abstract RevisionKudoDAO obtenerRevisionKudoDAO();

	public abstract CalificacionReplicaDAO obtenerCalificacionReplicaDAO();

	public abstract RevisionReplicaDAO obtenerRevisionReplicaDAO();

	public abstract ReporteKudoDAO obtenerReporteKudoDAO();

	public abstract ReporteReplicaDAO obtenerReporteReplicaDAO();
}
