package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.AuditoriaDTO;
import co.edu.uco.kudos.negocio.fachada.AuditoriaFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.AuditoriaDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.AuditoriaNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class AuditoriaFachadaImpl implements AuditoriaFachada {

	@Override
	public List<AuditoriaDTO> consultarAuditorias(AuditoriaDTO filtro) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var auditoriaNegocio = new AuditoriaNegocioImpl(daoFactory);
			var filtroDominio = AuditoriaDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = auditoriaNegocio.consultarAuditorias(filtroDominio);
			return AuditoriaDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.AuditoriaFachada.USUARIO_ERROR_CONSULTANDO_AUDITORIAS;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	@Override
	public List<AuditoriaDTO> consultarAuditoriasPropias(AuditoriaDTO filtro) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var auditoriaNegocio = new AuditoriaNegocioImpl(daoFactory);
			var filtroDominio = AuditoriaDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = auditoriaNegocio.consultarAuditoriasPropias(filtroDominio);
			return AuditoriaDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.AuditoriaFachada.USUARIO_ERROR_CONSULTANDO_AUDITORIAS_PROPIAS;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
