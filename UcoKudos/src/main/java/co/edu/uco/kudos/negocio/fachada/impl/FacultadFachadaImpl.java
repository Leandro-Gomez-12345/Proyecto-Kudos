package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.FacultadDTO;
import co.edu.uco.kudos.negocio.fachada.FacultadFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.FacultadDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.FacultadNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class FacultadFachadaImpl implements FacultadFachada {

	@Override
	public FacultadDTO registrarFacultad(FacultadDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return registrarFacultadConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private FacultadDTO registrarFacultadConfirmandoTransaccion(DAOFactory daoFactory, FacultadDTO datos) {
		try {
			var facultadNegocio = new FacultadNegocioImpl(daoFactory);
			var datosDominio = FacultadDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = facultadNegocio.registrarFacultad(datosDominio);
			daoFactory.confirmarTransaccion();
			return FacultadDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.FacultadFachada.USUARIO_ERROR_REGISTRANDO_FACULTAD;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public List<FacultadDTO> consultarFacultades(FacultadDTO filtro) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var facultadNegocio = new FacultadNegocioImpl(daoFactory);
			var filtroDominio = FacultadDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = facultadNegocio.consultarFacultades(filtroDominio);
			return FacultadDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.FacultadFachada.USUARIO_ERROR_CONSULTANDO_FACULTADES;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	@Override
	public FacultadDTO cambiarNombreFacultad(UUID id, FacultadDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return cambiarNombreFacultadConfirmandoTransaccion(daoFactory, id, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private FacultadDTO cambiarNombreFacultadConfirmandoTransaccion(DAOFactory daoFactory, UUID id, FacultadDTO datos) {
		try {
			var facultadNegocio = new FacultadNegocioImpl(daoFactory);
			var datosDominio = FacultadDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = facultadNegocio.cambiarNombreFacultad(id, datosDominio);
			daoFactory.confirmarTransaccion();
			return FacultadDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.FacultadFachada.USUARIO_ERROR_CAMBIANDO_NOMBRE_FACULTAD;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}
}
