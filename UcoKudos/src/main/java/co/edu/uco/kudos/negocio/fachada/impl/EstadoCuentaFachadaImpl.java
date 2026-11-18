package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.EstadoCuentaDTO;
import co.edu.uco.kudos.negocio.fachada.EstadoCuentaFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.EstadoCuentaDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.EstadoCuentaNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class EstadoCuentaFachadaImpl implements EstadoCuentaFachada {

	@Override
	public List<EstadoCuentaDTO> consultarEstadosCuenta() {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var estadoCuentaNegocio = new EstadoCuentaNegocioImpl(daoFactory);
			var resultados = estadoCuentaNegocio.consultarEstadosCuenta();
			return EstadoCuentaDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.EstadoCuentaFachada.USUARIO_ERROR_CONSULTANDO_ESTADOS_CUENTA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
