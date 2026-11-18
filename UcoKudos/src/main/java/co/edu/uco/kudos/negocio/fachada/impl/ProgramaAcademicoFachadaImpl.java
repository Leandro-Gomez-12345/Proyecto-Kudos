package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.ProgramaAcademicoDTO;
import co.edu.uco.kudos.negocio.fachada.ProgramaAcademicoFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.ProgramaAcademicoDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.ProgramaAcademicoNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class ProgramaAcademicoFachadaImpl implements ProgramaAcademicoFachada {

	@Override
	public ProgramaAcademicoDTO registrarProgramaAcademico(ProgramaAcademicoDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return registrarProgramaAcademicoConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private ProgramaAcademicoDTO registrarProgramaAcademicoConfirmandoTransaccion(DAOFactory daoFactory,
			ProgramaAcademicoDTO datos) {
		try {
			var programaAcademicoNegocio = new ProgramaAcademicoNegocioImpl(daoFactory);
			var datosDominio = ProgramaAcademicoDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = programaAcademicoNegocio.registrarProgramaAcademico(datosDominio);
			daoFactory.confirmarTransaccion();
			return ProgramaAcademicoDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.ProgramaAcademicoFachada.USUARIO_ERROR_REGISTRANDO_PROGRAMA_ACADEMICO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public List<ProgramaAcademicoDTO> consultarProgramasAcademicos(ProgramaAcademicoDTO filtro) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var programaAcademicoNegocio = new ProgramaAcademicoNegocioImpl(daoFactory);
			var filtroDominio = ProgramaAcademicoDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = programaAcademicoNegocio.consultarProgramasAcademicos(filtroDominio);
			return ProgramaAcademicoDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario =
					CatalogoMensajes.ProgramaAcademicoFachada.USUARIO_ERROR_CONSULTANDO_PROGRAMAS_ACADEMICOS;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	@Override
	public ProgramaAcademicoDTO cambiarNombreProgramaAcademico(UUID id, ProgramaAcademicoDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return cambiarNombreProgramaAcademicoConfirmandoTransaccion(daoFactory, id, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private ProgramaAcademicoDTO cambiarNombreProgramaAcademicoConfirmandoTransaccion(DAOFactory daoFactory, UUID id,
			ProgramaAcademicoDTO datos) {
		try {
			var programaAcademicoNegocio = new ProgramaAcademicoNegocioImpl(daoFactory);
			var datosDominio = ProgramaAcademicoDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = programaAcademicoNegocio.cambiarNombreProgramaAcademico(id, datosDominio);
			daoFactory.confirmarTransaccion();
			return ProgramaAcademicoDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario =
					CatalogoMensajes.ProgramaAcademicoFachada.USUARIO_ERROR_CAMBIANDO_NOMBRE_PROGRAMA_ACADEMICO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}
}
