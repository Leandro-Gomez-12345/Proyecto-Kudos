package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.MotivoDTO;
import co.edu.uco.kudos.negocio.fachada.MotivoFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.MotivoDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.MotivoNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class MotivoFachadaImpl implements MotivoFachada {

	@Override
	public List<MotivoDTO> consultarMotivos() {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var motivoNegocio = new MotivoNegocioImpl(daoFactory);
			var resultados = motivoNegocio.consultarMotivos();
			return MotivoDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.MotivoFachada.USUARIO_ERROR_CONSULTANDO_MOTIVOS;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
