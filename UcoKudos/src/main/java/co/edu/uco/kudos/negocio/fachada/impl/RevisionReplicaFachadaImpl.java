package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.RevisionReplicaDTO;
import co.edu.uco.kudos.negocio.fachada.RevisionReplicaFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.RevisionReplicaDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.RevisionReplicaNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class RevisionReplicaFachadaImpl implements RevisionReplicaFachada {

	@Override
	public RevisionReplicaDTO iniciarRevisionReplica(RevisionReplicaDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return iniciarRevisionReplicaConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private RevisionReplicaDTO iniciarRevisionReplicaConfirmandoTransaccion(DAOFactory daoFactory,
			RevisionReplicaDTO datos) {
		try {
			var revisionReplicaNegocio = new RevisionReplicaNegocioImpl(daoFactory);
			var datosDominio = RevisionReplicaDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = revisionReplicaNegocio.iniciarRevisionReplica(datosDominio);
			daoFactory.confirmarTransaccion();
			return RevisionReplicaDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.RevisionReplicaFachada.USUARIO_ERROR_INICIANDO_REVISION_REPLICA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void cerrarRevisionReplicaConVeto(UUID id, RevisionReplicaDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			cerrarRevisionReplicaConVetoConfirmandoTransaccion(daoFactory, id, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void cerrarRevisionReplicaConVetoConfirmandoTransaccion(DAOFactory daoFactory, UUID id,
			RevisionReplicaDTO datos) {
		try {
			var revisionReplicaNegocio = new RevisionReplicaNegocioImpl(daoFactory);
			var datosDominio = RevisionReplicaDTOAssembler.obtenerInstancia().convertirADominio(datos);
			revisionReplicaNegocio.cerrarRevisionReplicaConVeto(id, datosDominio);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario =
					CatalogoMensajes.RevisionReplicaFachada.USUARIO_ERROR_CERRANDO_REVISION_REPLICA_CON_VETO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void cerrarRevisionReplicaConLiberacion(UUID id, RevisionReplicaDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			cerrarRevisionReplicaConLiberacionConfirmandoTransaccion(daoFactory, id, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void cerrarRevisionReplicaConLiberacionConfirmandoTransaccion(DAOFactory daoFactory, UUID id,
			RevisionReplicaDTO datos) {
		try {
			var revisionReplicaNegocio = new RevisionReplicaNegocioImpl(daoFactory);
			var datosDominio = RevisionReplicaDTOAssembler.obtenerInstancia().convertirADominio(datos);
			revisionReplicaNegocio.cerrarRevisionReplicaConLiberacion(id, datosDominio);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario =
					CatalogoMensajes.RevisionReplicaFachada.USUARIO_ERROR_CERRANDO_REVISION_REPLICA_CON_LIBERACION;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public RevisionReplicaDTO vetarReplicaDirectamente(RevisionReplicaDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return vetarReplicaDirectamenteConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private RevisionReplicaDTO vetarReplicaDirectamenteConfirmandoTransaccion(DAOFactory daoFactory,
			RevisionReplicaDTO datos) {
		try {
			var revisionReplicaNegocio = new RevisionReplicaNegocioImpl(daoFactory);
			var datosDominio = RevisionReplicaDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = revisionReplicaNegocio.vetarReplicaDirectamente(datosDominio);
			daoFactory.confirmarTransaccion();
			return RevisionReplicaDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.RevisionReplicaFachada.USUARIO_ERROR_VETANDO_REPLICA_DIRECTAMENTE;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public List<RevisionReplicaDTO> consultarRevisionesReplica(RevisionReplicaDTO filtro) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var revisionReplicaNegocio = new RevisionReplicaNegocioImpl(daoFactory);
			var filtroDominio = RevisionReplicaDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = revisionReplicaNegocio.consultarRevisionesReplica(filtroDominio);
			return RevisionReplicaDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.RevisionReplicaFachada.USUARIO_ERROR_CONSULTANDO_REVISIONES_REPLICA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
