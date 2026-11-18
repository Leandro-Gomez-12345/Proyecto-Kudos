package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.VinculacionAcademicaDTO;
import co.edu.uco.kudos.negocio.fachada.VinculacionAcademicaFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.VinculacionAcademicaDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.VinculacionAcademicaNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class VinculacionAcademicaFachadaImpl implements VinculacionAcademicaFachada {

	@Override
	public VinculacionAcademicaDTO vincularPersonaAProgramaAcademico(VinculacionAcademicaDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return vincularPersonaAProgramaAcademicoConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private VinculacionAcademicaDTO vincularPersonaAProgramaAcademicoConfirmandoTransaccion(DAOFactory daoFactory,
			VinculacionAcademicaDTO datos) {
		try {
			var vinculacionAcademicaNegocio = new VinculacionAcademicaNegocioImpl(daoFactory);
			var datosDominio = VinculacionAcademicaDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = vinculacionAcademicaNegocio.vincularPersonaAProgramaAcademico(datosDominio);
			daoFactory.confirmarTransaccion();
			return VinculacionAcademicaDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario =
					CatalogoMensajes.VinculacionAcademicaFachada.USUARIO_ERROR_VINCULANDO_PERSONA_A_PROGRAMA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void desvincularPersonaDeProgramaAcademico(UUID id, VinculacionAcademicaDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			desvincularPersonaDeProgramaAcademicoConfirmandoTransaccion(daoFactory, id, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void desvincularPersonaDeProgramaAcademicoConfirmandoTransaccion(DAOFactory daoFactory, UUID id,
			VinculacionAcademicaDTO datos) {
		try {
			var vinculacionAcademicaNegocio = new VinculacionAcademicaNegocioImpl(daoFactory);
			var datosDominio = VinculacionAcademicaDTOAssembler.obtenerInstancia().convertirADominio(datos);
			vinculacionAcademicaNegocio.desvincularPersonaDeProgramaAcademico(id, datosDominio);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario =
					CatalogoMensajes.VinculacionAcademicaFachada.USUARIO_ERROR_DESVINCULANDO_PERSONA_DE_PROGRAMA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public List<VinculacionAcademicaDTO> consultarVinculacionesAcademicas(VinculacionAcademicaDTO filtro) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var vinculacionAcademicaNegocio = new VinculacionAcademicaNegocioImpl(daoFactory);
			var filtroDominio = VinculacionAcademicaDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = vinculacionAcademicaNegocio.consultarVinculacionesAcademicas(filtroDominio);
			return VinculacionAcademicaDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario =
					CatalogoMensajes.VinculacionAcademicaFachada.USUARIO_ERROR_CONSULTANDO_VINCULACIONES_ACADEMICAS;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
