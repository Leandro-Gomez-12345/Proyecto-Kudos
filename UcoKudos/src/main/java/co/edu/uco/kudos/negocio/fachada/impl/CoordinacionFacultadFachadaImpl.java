package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.CoordinacionFacultadDTO;
import co.edu.uco.kudos.negocio.fachada.CoordinacionFacultadFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.CoordinacionFacultadDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.CoordinacionFacultadNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class CoordinacionFacultadFachadaImpl implements CoordinacionFacultadFachada {

	@Override
	public CoordinacionFacultadDTO asignarCoordinadorFacultad(CoordinacionFacultadDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return asignarCoordinadorFacultadConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private CoordinacionFacultadDTO asignarCoordinadorFacultadConfirmandoTransaccion(DAOFactory daoFactory,
			CoordinacionFacultadDTO datos) {
		try {
			var coordinacionFacultadNegocio = new CoordinacionFacultadNegocioImpl(daoFactory);
			var datosDominio = CoordinacionFacultadDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = coordinacionFacultadNegocio.asignarCoordinadorFacultad(datosDominio);
			daoFactory.confirmarTransaccion();
			return CoordinacionFacultadDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario =
					CatalogoMensajes.CoordinacionFacultadFachada.USUARIO_ERROR_ASIGNANDO_COORDINADOR_FACULTAD;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public List<CoordinacionFacultadDTO> consultarCoordinacionesFacultad(CoordinacionFacultadDTO filtro) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var coordinacionFacultadNegocio = new CoordinacionFacultadNegocioImpl(daoFactory);
			var filtroDominio = CoordinacionFacultadDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = coordinacionFacultadNegocio.consultarCoordinacionesFacultad(filtroDominio);
			return CoordinacionFacultadDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario =
					CatalogoMensajes.CoordinacionFacultadFachada.USUARIO_ERROR_CONSULTANDO_COORDINACIONES_FACULTAD;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
