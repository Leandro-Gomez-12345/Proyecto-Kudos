package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.CoordinacionProgramaDTO;
import co.edu.uco.kudos.negocio.fachada.CoordinacionProgramaFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.CoordinacionProgramaDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.CoordinacionProgramaNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class CoordinacionProgramaFachadaImpl implements CoordinacionProgramaFachada {

	@Override
	public CoordinacionProgramaDTO asignarCoordinadorPrograma(CoordinacionProgramaDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return asignarCoordinadorProgramaConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private CoordinacionProgramaDTO asignarCoordinadorProgramaConfirmandoTransaccion(DAOFactory daoFactory,
			CoordinacionProgramaDTO datos) {
		try {
			var coordinacionProgramaNegocio = new CoordinacionProgramaNegocioImpl(daoFactory);
			var datosDominio = CoordinacionProgramaDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = coordinacionProgramaNegocio.asignarCoordinadorPrograma(datosDominio);
			daoFactory.confirmarTransaccion();
			return CoordinacionProgramaDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario =
					CatalogoMensajes.CoordinacionProgramaFachada.USUARIO_ERROR_ASIGNANDO_COORDINADOR_PROGRAMA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public List<CoordinacionProgramaDTO> consultarCoordinacionesPrograma(CoordinacionProgramaDTO filtro) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var coordinacionProgramaNegocio = new CoordinacionProgramaNegocioImpl(daoFactory);
			var filtroDominio = CoordinacionProgramaDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = coordinacionProgramaNegocio.consultarCoordinacionesPrograma(filtroDominio);
			return CoordinacionProgramaDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario =
					CatalogoMensajes.CoordinacionProgramaFachada.USUARIO_ERROR_CONSULTANDO_COORDINACIONES_PROGRAMA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
