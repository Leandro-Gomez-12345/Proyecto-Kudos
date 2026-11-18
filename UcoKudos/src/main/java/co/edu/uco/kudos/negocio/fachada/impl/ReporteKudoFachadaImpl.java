package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.ReporteKudoDTO;
import co.edu.uco.kudos.negocio.fachada.ReporteKudoFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.ReporteKudoDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.ReporteKudoNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class ReporteKudoFachadaImpl implements ReporteKudoFachada {

	@Override
	public ReporteKudoDTO reportarKudo(ReporteKudoDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return reportarKudoConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private ReporteKudoDTO reportarKudoConfirmandoTransaccion(DAOFactory daoFactory, ReporteKudoDTO datos) {
		try {
			var reporteKudoNegocio = new ReporteKudoNegocioImpl(daoFactory);
			var datosDominio = ReporteKudoDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = reporteKudoNegocio.reportarKudo(datosDominio);
			daoFactory.confirmarTransaccion();
			return ReporteKudoDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.ReporteKudoFachada.USUARIO_ERROR_REPORTANDO_KUDO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public List<ReporteKudoDTO> consultarReportesKudo(ReporteKudoDTO filtro, String estado) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var reporteKudoNegocio = new ReporteKudoNegocioImpl(daoFactory);
			var filtroDominio = ReporteKudoDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = reporteKudoNegocio.consultarReportesKudo(filtroDominio, estado);
			return ReporteKudoDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.ReporteKudoFachada.USUARIO_ERROR_CONSULTANDO_REPORTES_KUDO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
