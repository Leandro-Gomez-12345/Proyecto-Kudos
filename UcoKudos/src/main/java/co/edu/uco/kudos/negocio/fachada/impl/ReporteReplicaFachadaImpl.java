package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.ReporteReplicaDTO;
import co.edu.uco.kudos.negocio.fachada.ReporteReplicaFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.ReporteReplicaDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.ReporteReplicaNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class ReporteReplicaFachadaImpl implements ReporteReplicaFachada {

	@Override
	public ReporteReplicaDTO reportarReplica(ReporteReplicaDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return reportarReplicaConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private ReporteReplicaDTO reportarReplicaConfirmandoTransaccion(DAOFactory daoFactory, ReporteReplicaDTO datos) {
		try {
			var reporteReplicaNegocio = new ReporteReplicaNegocioImpl(daoFactory);
			var datosDominio = ReporteReplicaDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = reporteReplicaNegocio.reportarReplica(datosDominio);
			daoFactory.confirmarTransaccion();
			return ReporteReplicaDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.ReporteReplicaFachada.USUARIO_ERROR_REPORTANDO_REPLICA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public List<ReporteReplicaDTO> consultarReportesReplica(ReporteReplicaDTO filtro, String estado) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var reporteReplicaNegocio = new ReporteReplicaNegocioImpl(daoFactory);
			var filtroDominio = ReporteReplicaDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = reporteReplicaNegocio.consultarReportesReplica(filtroDominio, estado);
			return ReporteReplicaDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.ReporteReplicaFachada.USUARIO_ERROR_CONSULTANDO_REPORTES_REPLICA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
