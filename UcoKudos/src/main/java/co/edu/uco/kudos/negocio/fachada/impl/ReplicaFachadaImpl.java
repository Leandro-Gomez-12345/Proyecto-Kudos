package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.ReplicaDTO;
import co.edu.uco.kudos.negocio.fachada.ReplicaFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.ReplicaDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.ReplicaNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class ReplicaFachadaImpl implements ReplicaFachada {

	@Override
	public ReplicaDTO replicarKudo(ReplicaDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return replicarKudoConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private ReplicaDTO replicarKudoConfirmandoTransaccion(DAOFactory daoFactory, ReplicaDTO datos) {
		try {
			var replicaNegocio = new ReplicaNegocioImpl(daoFactory);
			var datosDominio = ReplicaDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = replicaNegocio.replicarKudo(datosDominio);
			daoFactory.confirmarTransaccion();
			return ReplicaDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.ReplicaFachada.USUARIO_ERROR_REPLICANDO_KUDO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public ReplicaDTO responderReplica(ReplicaDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return responderReplicaConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private ReplicaDTO responderReplicaConfirmandoTransaccion(DAOFactory daoFactory, ReplicaDTO datos) {
		try {
			var replicaNegocio = new ReplicaNegocioImpl(daoFactory);
			var datosDominio = ReplicaDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = replicaNegocio.responderReplica(datosDominio);
			daoFactory.confirmarTransaccion();
			return ReplicaDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.ReplicaFachada.USUARIO_ERROR_RESPONDIENDO_REPLICA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public List<ReplicaDTO> consultarConversacionKudo(ReplicaDTO filtro) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var replicaNegocio = new ReplicaNegocioImpl(daoFactory);
			var filtroDominio = ReplicaDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = replicaNegocio.consultarConversacionKudo(filtroDominio);
			return ReplicaDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.ReplicaFachada.USUARIO_ERROR_CONSULTANDO_CONVERSACION_KUDO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
