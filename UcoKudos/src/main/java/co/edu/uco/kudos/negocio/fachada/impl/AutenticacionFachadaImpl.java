package co.edu.uco.kudos.negocio.fachada.impl;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.UsuarioDTO;
import co.edu.uco.kudos.negocio.fachada.AutenticacionFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.UsuarioDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.AutenticacionNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class AutenticacionFachadaImpl implements AutenticacionFachada {

	@Override
	public UsuarioDTO iniciarSesion(String correo) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var autenticacionNegocio = new AutenticacionNegocioImpl(daoFactory);
			var resultado = autenticacionNegocio.iniciarSesion(correo);
			return UsuarioDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.AutenticacionFachada.USUARIO_ERROR_INICIANDO_SESION;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	@Override
	public UsuarioDTO consultarUsuarioPorCorreo(String correo) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var autenticacionNegocio = new AutenticacionNegocioImpl(daoFactory);
			var resultado = autenticacionNegocio.consultarUsuarioPorCorreo(correo);
			return UsuarioDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.AutenticacionFachada.USUARIO_ERROR_CONSULTANDO_USUARIO_POR_CORREO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
