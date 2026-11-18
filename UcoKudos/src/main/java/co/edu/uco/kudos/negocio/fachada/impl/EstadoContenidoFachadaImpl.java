package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.EstadoContenidoDTO;
import co.edu.uco.kudos.negocio.fachada.EstadoContenidoFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.EstadoContenidoDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.EstadoContenidoNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class EstadoContenidoFachadaImpl implements EstadoContenidoFachada {

	@Override
	public List<EstadoContenidoDTO> consultarEstadosContenido() {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var estadoContenidoNegocio = new EstadoContenidoNegocioImpl(daoFactory);
			var resultados = estadoContenidoNegocio.consultarEstadosContenido();
			return EstadoContenidoDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.EstadoContenidoFachada.USUARIO_ERROR_CONSULTANDO_ESTADOS_CONTENIDO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
