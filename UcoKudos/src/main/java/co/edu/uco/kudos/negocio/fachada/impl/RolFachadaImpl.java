package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.RolDTO;
import co.edu.uco.kudos.negocio.fachada.RolFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.RolDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.RolNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class RolFachadaImpl implements RolFachada {

	@Override
	public List<RolDTO> consultarRoles() {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var rolNegocio = new RolNegocioImpl(daoFactory);
			var resultados = rolNegocio.consultarRoles();
			return RolDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.RolFachada.USUARIO_ERROR_CONSULTANDO_ROLES;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
