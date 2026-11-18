package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.ProgramaAcademicoDominio;
import co.edu.uco.kudos.dominio.VinculacionAcademicaDominio;
import co.edu.uco.kudos.negocio.negocio.VinculacionAcademicaNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.ProgramaAcademicoEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.VinculacionAcademicaEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.Auditor;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma.AsegurarUsuarioCoordinaElProgramaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.programaacademico.AsegurarProgramaAcademicoExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarPersonaEsEstudianteODocenteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarPersonaIndicadaActivaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioEsCoordinadorProgramaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica.AsegurarFormatoDesvinculacionValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica.AsegurarFormatoVinculacionAcademicaValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica.AsegurarIdVinculacionAcademicaUnicoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica.AsegurarPersonaSinVinculoVigenteEnProgramaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica.AsegurarVinculacionAcademicaExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica.AsegurarVinculacionAcademicaNoRepetidaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica.AsegurarVinculacionAcademicaVigenteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica.DesvincularAlRechazarODesactivarRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica.VincularProgramasAlRegistrarseRule;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class VinculacionAcademicaNegocioImpl implements VinculacionAcademicaNegocio {

	private final DAOFactory daoFactory;

	public VinculacionAcademicaNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public VinculacionAcademicaDominio vincularPersonaAProgramaAcademico(VinculacionAcademicaDominio datos) {
		var vinculacion = VinculacionAcademicaEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();
		var idPrograma = vinculacion.getProgramaAcademico().getId();
		var idPersona = vinculacion.getUsuario().getId();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioEsCoordinadorProgramaRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarFormatoVinculacionAcademicaValidoRule.obtenerInstancia().ejecutar(vinculacion);
		AsegurarProgramaAcademicoExisteRule.obtenerInstancia().ejecutar(idPrograma, daoFactory);
		AsegurarUsuarioCoordinaElProgramaRule.obtenerInstancia().ejecutar(idPrograma, daoFactory);
		AsegurarPersonaIndicadaActivaRule.obtenerInstancia().ejecutar(idPersona, daoFactory);
		AsegurarPersonaEsEstudianteODocenteRule.obtenerInstancia().ejecutar(idPersona, daoFactory);
		AsegurarPersonaSinVinculoVigenteEnProgramaRule.obtenerInstancia().ejecutar(vinculacion, daoFactory);
		AsegurarVinculacionAcademicaNoRepetidaRule.obtenerInstancia().ejecutar(vinculacion, daoFactory);
		AsegurarIdVinculacionAcademicaUnicoRule.obtenerInstancia().ejecutar(vinculacion, daoFactory);

		daoFactory.obtenerVinculacionAcademicaDAO().crear(vinculacion);
		Auditor.registrar(daoFactory, EventoAuditable.VINCULAR_PERSONA_A_PROGRAMA_ACADEMICO, vinculacion.getId(),
				UtilTexto.concatenar("Vinculación desde ", vinculacion.getFechaVinculacion().toString()));
		return VinculacionAcademicaEntidadAssembler.obtenerInstancia().convertirADominio(vinculacion);
	}

	@Override
	public void desvincularPersonaDeProgramaAcademico(UUID id, VinculacionAcademicaDominio datos) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioEsCoordinadorProgramaRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarVinculacionAcademicaExisteRule.obtenerInstancia().ejecutar(id, daoFactory);

		var vinculacion = daoFactory.obtenerVinculacionAcademicaDAO().consultarPorId(id);
		AsegurarUsuarioCoordinaElProgramaRule.obtenerInstancia().ejecutar(vinculacion.getProgramaAcademico().getId(),
				daoFactory);
		AsegurarVinculacionAcademicaVigenteRule.obtenerInstancia().ejecutar(vinculacion);
		vinculacion.setFechaDesvinculacion(UtilFecha.esValorDefecto(datos.getFechaDesvinculacion())
				? UtilFecha.obtenerFechaActual() : datos.getFechaDesvinculacion());
		AsegurarFormatoDesvinculacionValidoRule.obtenerInstancia().ejecutar(vinculacion);

		daoFactory.obtenerVinculacionAcademicaDAO().actualizar(id, vinculacion);
		Auditor.registrar(daoFactory, EventoAuditable.DESVINCULAR_PERSONA_DE_PROGRAMA_ACADEMICO, id,
				UtilTexto.concatenar("Desvinculación el ", vinculacion.getFechaDesvinculacion().toString()));
	}

	@Override
	public void vincularPersonaAlRegistrarse(VinculacionAcademicaDominio datos,
			List<ProgramaAcademicoDominio> programas) {
		var vinculacion = VinculacionAcademicaEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var programasElegidos = ProgramaAcademicoEntidadAssembler.obtenerInstancia().convertirAEntidad(programas);
		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(vinculacion.getUsuario().getId());

		for (var programa : programasElegidos) {
			AsegurarProgramaAcademicoExisteRule.obtenerInstancia().ejecutar(programa.getId(), daoFactory);
		}
		VincularProgramasAlRegistrarseRule.obtenerInstancia().ejecutar(usuario, programasElegidos,
				vinculacion.getFechaVinculacion(), daoFactory);
	}

	@Override
	public void desvincularPersonaDeTodosLosProgramas(VinculacionAcademicaDominio filtro,
			VinculacionAcademicaDominio datos) {
		var vinculacion = VinculacionAcademicaEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		vinculacion.setFechaDesvinculacion(VinculacionAcademicaEntidadAssembler.obtenerInstancia()
				.convertirAEntidad(datos).getFechaDesvinculacion());

		var vigentes = ConsultorVigencias.vinculaciones(daoFactory, vinculacion.getUsuario().getId(),
				UtilUUID.obtenerUUIDDefecto());
		for (var vigente : vigentes) {
			AsegurarVinculacionAcademicaVigenteRule.obtenerInstancia().ejecutar(vigente);
		}
		DesvincularAlRechazarODesactivarRule.obtenerInstancia().ejecutar(vinculacion, daoFactory);
	}

	@Override
	public List<VinculacionAcademicaDominio> consultarVinculacionesAcademicas(VinculacionAcademicaDominio filtro) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);

		var filtroEntidad = VinculacionAcademicaEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		var vinculaciones = daoFactory.obtenerVinculacionAcademicaDAO().consultarPorFiltro(filtroEntidad);
		return VinculacionAcademicaEntidadAssembler.obtenerInstancia().convertirADominio(vinculaciones);
	}
}
