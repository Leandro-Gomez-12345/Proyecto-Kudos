package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.EstadoRevisionDTO;
import co.edu.uco.kudos.negocio.fachada.EstadoRevisionFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.EstadoRevisionDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.EstadoRevisionNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class EstadoRevisionFachadaImpl implements EstadoRevisionFachada {

	@Override
	public List<EstadoRevisionDTO> consultarEstadosRevision() {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var estadoRevisionNegocio = new EstadoRevisionNegocioImpl(daoFactory);
			var resultados = estadoRevisionNegocio.consultarEstadosRevision();
			return EstadoRevisionDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.EstadoRevisionFachada.USUARIO_ERROR_CONSULTANDO_ESTADOS_REVISION;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
