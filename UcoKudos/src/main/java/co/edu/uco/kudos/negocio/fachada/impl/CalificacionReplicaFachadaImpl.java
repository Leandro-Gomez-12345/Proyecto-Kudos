package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.CalificacionReplicaDTO;
import co.edu.uco.kudos.negocio.fachada.CalificacionReplicaFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.CalificacionReplicaDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.CalificacionReplicaNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class CalificacionReplicaFachadaImpl implements CalificacionReplicaFachada {

	@Override
	public CalificacionReplicaDTO calificarReplica(CalificacionReplicaDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return calificarReplicaConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private CalificacionReplicaDTO calificarReplicaConfirmandoTransaccion(DAOFactory daoFactory,
			CalificacionReplicaDTO datos) {
		try {
			var calificacionReplicaNegocio = new CalificacionReplicaNegocioImpl(daoFactory);
			var datosDominio = CalificacionReplicaDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = calificacionReplicaNegocio.calificarReplica(datosDominio);
			daoFactory.confirmarTransaccion();
			return CalificacionReplicaDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.CalificacionReplicaFachada.USUARIO_ERROR_CALIFICANDO_REPLICA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public CalificacionReplicaDTO cambiarCalificacionReplica(UUID id, CalificacionReplicaDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return cambiarCalificacionReplicaConfirmandoTransaccion(daoFactory, id, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private CalificacionReplicaDTO cambiarCalificacionReplicaConfirmandoTransaccion(DAOFactory daoFactory, UUID id,
			CalificacionReplicaDTO datos) {
		try {
			var calificacionReplicaNegocio = new CalificacionReplicaNegocioImpl(daoFactory);
			var datosDominio = CalificacionReplicaDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = calificacionReplicaNegocio.cambiarCalificacionReplica(id, datosDominio);
			daoFactory.confirmarTransaccion();
			return CalificacionReplicaDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario =
					CatalogoMensajes.CalificacionReplicaFachada.USUARIO_ERROR_CAMBIANDO_CALIFICACION_REPLICA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public List<CalificacionReplicaDTO> consultarCalificacionesReplica(CalificacionReplicaDTO filtro) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var calificacionReplicaNegocio = new CalificacionReplicaNegocioImpl(daoFactory);
			var filtroDominio = CalificacionReplicaDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = calificacionReplicaNegocio.consultarCalificacionesReplica(filtroDominio);
			return CalificacionReplicaDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario =
					CatalogoMensajes.CalificacionReplicaFachada.USUARIO_ERROR_CONSULTANDO_CALIFICACIONES_REPLICA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
