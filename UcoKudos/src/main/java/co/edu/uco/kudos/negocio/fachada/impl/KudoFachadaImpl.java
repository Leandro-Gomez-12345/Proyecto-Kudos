package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.KudoDTO;
import co.edu.uco.kudos.negocio.fachada.KudoFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.KudoDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.KudoNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class KudoFachadaImpl implements KudoFachada {

	@Override
	public KudoDTO crearKudo(KudoDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return crearKudoConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private KudoDTO crearKudoConfirmandoTransaccion(DAOFactory daoFactory, KudoDTO datos) {
		try {
			var kudoNegocio = new KudoNegocioImpl(daoFactory);
			var datosDominio = KudoDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = kudoNegocio.crearKudo(datosDominio);
			daoFactory.confirmarTransaccion();
			return KudoDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.KudoFachada.USUARIO_ERROR_CREANDO_KUDO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public KudoDTO consultarKudo(UUID id) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var kudoNegocio = new KudoNegocioImpl(daoFactory);
			var resultado = kudoNegocio.consultarKudo(id);
			return KudoDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.KudoFachada.USUARIO_ERROR_CONSULTANDO_KUDO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	@Override
	public List<KudoDTO> consultarKudos(KudoDTO filtro) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var kudoNegocio = new KudoNegocioImpl(daoFactory);
			var filtroDominio = KudoDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = kudoNegocio.consultarKudos(filtroDominio);
			return KudoDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.KudoFachada.USUARIO_ERROR_CONSULTANDO_KUDOS;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
