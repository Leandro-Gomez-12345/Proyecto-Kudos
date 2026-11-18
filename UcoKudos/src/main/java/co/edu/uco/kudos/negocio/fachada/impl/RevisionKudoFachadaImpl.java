package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.RevisionKudoDTO;
import co.edu.uco.kudos.negocio.fachada.RevisionKudoFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.RevisionKudoDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.RevisionKudoNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class RevisionKudoFachadaImpl implements RevisionKudoFachada {

	@Override
	public RevisionKudoDTO iniciarRevisionKudo(RevisionKudoDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return iniciarRevisionKudoConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private RevisionKudoDTO iniciarRevisionKudoConfirmandoTransaccion(DAOFactory daoFactory, RevisionKudoDTO datos) {
		try {
			var revisionKudoNegocio = new RevisionKudoNegocioImpl(daoFactory);
			var datosDominio = RevisionKudoDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = revisionKudoNegocio.iniciarRevisionKudo(datosDominio);
			daoFactory.confirmarTransaccion();
			return RevisionKudoDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.RevisionKudoFachada.USUARIO_ERROR_INICIANDO_REVISION_KUDO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void cerrarRevisionKudoConVeto(UUID id, RevisionKudoDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			cerrarRevisionKudoConVetoConfirmandoTransaccion(daoFactory, id, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void cerrarRevisionKudoConVetoConfirmandoTransaccion(DAOFactory daoFactory, UUID id,
			RevisionKudoDTO datos) {
		try {
			var revisionKudoNegocio = new RevisionKudoNegocioImpl(daoFactory);
			var datosDominio = RevisionKudoDTOAssembler.obtenerInstancia().convertirADominio(datos);
			revisionKudoNegocio.cerrarRevisionKudoConVeto(id, datosDominio);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.RevisionKudoFachada.USUARIO_ERROR_CERRANDO_REVISION_KUDO_CON_VETO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void cerrarRevisionKudoConLiberacion(UUID id, RevisionKudoDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			cerrarRevisionKudoConLiberacionConfirmandoTransaccion(daoFactory, id, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void cerrarRevisionKudoConLiberacionConfirmandoTransaccion(DAOFactory daoFactory, UUID id,
			RevisionKudoDTO datos) {
		try {
			var revisionKudoNegocio = new RevisionKudoNegocioImpl(daoFactory);
			var datosDominio = RevisionKudoDTOAssembler.obtenerInstancia().convertirADominio(datos);
			revisionKudoNegocio.cerrarRevisionKudoConLiberacion(id, datosDominio);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario =
					CatalogoMensajes.RevisionKudoFachada.USUARIO_ERROR_CERRANDO_REVISION_KUDO_CON_LIBERACION;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public RevisionKudoDTO vetarKudoDirectamente(RevisionKudoDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return vetarKudoDirectamenteConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private RevisionKudoDTO vetarKudoDirectamenteConfirmandoTransaccion(DAOFactory daoFactory, RevisionKudoDTO datos) {
		try {
			var revisionKudoNegocio = new RevisionKudoNegocioImpl(daoFactory);
			var datosDominio = RevisionKudoDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = revisionKudoNegocio.vetarKudoDirectamente(datosDominio);
			daoFactory.confirmarTransaccion();
			return RevisionKudoDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.RevisionKudoFachada.USUARIO_ERROR_VETANDO_KUDO_DIRECTAMENTE;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public List<RevisionKudoDTO> consultarRevisionesKudo(RevisionKudoDTO filtro) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var revisionKudoNegocio = new RevisionKudoNegocioImpl(daoFactory);
			var filtroDominio = RevisionKudoDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = revisionKudoNegocio.consultarRevisionesKudo(filtroDominio);
			return RevisionKudoDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.RevisionKudoFachada.USUARIO_ERROR_CONSULTANDO_REVISIONES_KUDO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
