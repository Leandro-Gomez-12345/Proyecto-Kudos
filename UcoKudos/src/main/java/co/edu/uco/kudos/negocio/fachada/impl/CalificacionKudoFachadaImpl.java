package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.CalificacionKudoDTO;
import co.edu.uco.kudos.negocio.fachada.CalificacionKudoFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.CalificacionKudoDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.CalificacionKudoNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class CalificacionKudoFachadaImpl implements CalificacionKudoFachada {

	@Override
	public CalificacionKudoDTO calificarKudo(CalificacionKudoDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return calificarKudoConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private CalificacionKudoDTO calificarKudoConfirmandoTransaccion(DAOFactory daoFactory, CalificacionKudoDTO datos) {
		try {
			var calificacionKudoNegocio = new CalificacionKudoNegocioImpl(daoFactory);
			var datosDominio = CalificacionKudoDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = calificacionKudoNegocio.calificarKudo(datosDominio);
			daoFactory.confirmarTransaccion();
			return CalificacionKudoDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.CalificacionKudoFachada.USUARIO_ERROR_CALIFICANDO_KUDO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public CalificacionKudoDTO cambiarCalificacionKudo(UUID id, CalificacionKudoDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return cambiarCalificacionKudoConfirmandoTransaccion(daoFactory, id, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private CalificacionKudoDTO cambiarCalificacionKudoConfirmandoTransaccion(DAOFactory daoFactory, UUID id,
			CalificacionKudoDTO datos) {
		try {
			var calificacionKudoNegocio = new CalificacionKudoNegocioImpl(daoFactory);
			var datosDominio = CalificacionKudoDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = calificacionKudoNegocio.cambiarCalificacionKudo(id, datosDominio);
			daoFactory.confirmarTransaccion();
			return CalificacionKudoDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.CalificacionKudoFachada.USUARIO_ERROR_CAMBIANDO_CALIFICACION_KUDO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public List<CalificacionKudoDTO> consultarCalificacionesKudo(CalificacionKudoDTO filtro) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var calificacionKudoNegocio = new CalificacionKudoNegocioImpl(daoFactory);
			var filtroDominio = CalificacionKudoDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = calificacionKudoNegocio.consultarCalificacionesKudo(filtroDominio);
			return CalificacionKudoDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.CalificacionKudoFachada.USUARIO_ERROR_CONSULTANDO_CALIFICACIONES_KUDO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
