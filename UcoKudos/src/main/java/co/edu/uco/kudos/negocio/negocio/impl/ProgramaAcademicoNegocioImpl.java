package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.ProgramaAcademicoDominio;
import co.edu.uco.kudos.negocio.negocio.ProgramaAcademicoNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.ProgramaAcademicoEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.Auditor;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.facultad.AsegurarFacultadExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.programaacademico.AsegurarFormatoNombreProgramaAcademicoValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.programaacademico.AsegurarFormatoProgramaAcademicoValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.programaacademico.AsegurarIdProgramaAcademicoUnicoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.programaacademico.AsegurarNombreProgramaAcademicoNoExistaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.programaacademico.AsegurarProgramaAcademicoExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioEsAdministradorRule;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class ProgramaAcademicoNegocioImpl implements ProgramaAcademicoNegocio {

	private final DAOFactory daoFactory;

	public ProgramaAcademicoNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ProgramaAcademicoDominio registrarProgramaAcademico(ProgramaAcademicoDominio datos) {
		var programa = ProgramaAcademicoEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioEsAdministradorRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarFormatoProgramaAcademicoValidoRule.obtenerInstancia().ejecutar(programa, daoFactory);
		AsegurarFacultadExisteRule.obtenerInstancia().ejecutar(programa.getFacultad().getId(), daoFactory);
		AsegurarNombreProgramaAcademicoNoExistaRule.obtenerInstancia().ejecutar(programa, daoFactory);
		AsegurarIdProgramaAcademicoUnicoRule.obtenerInstancia().ejecutar(programa, daoFactory);

		daoFactory.obtenerProgramaAcademicoDAO().crear(programa);
		Auditor.registrar(daoFactory, EventoAuditable.REGISTRAR_PROGRAMA_ACADEMICO, programa.getId(),
				UtilTexto.concatenar("Programa creado: ", programa.getNombre()));
		return ProgramaAcademicoEntidadAssembler.obtenerInstancia().convertirADominio(programa);
	}

	@Override
	public List<ProgramaAcademicoDominio> consultarProgramasAcademicos(ProgramaAcademicoDominio filtro) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);

		var filtroEntidad = ProgramaAcademicoEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		var programas = daoFactory.obtenerProgramaAcademicoDAO().consultarPorFiltro(filtroEntidad);
		return ProgramaAcademicoEntidadAssembler.obtenerInstancia().convertirADominio(programas);
	}

	@Override
	public ProgramaAcademicoDominio cambiarNombreProgramaAcademico(UUID id, ProgramaAcademicoDominio datos) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioEsAdministradorRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarProgramaAcademicoExisteRule.obtenerInstancia().ejecutar(id, daoFactory);

		var programa = daoFactory.obtenerProgramaAcademicoDAO().consultarPorId(id);
		var nombreAnterior = programa.getNombre();
		programa.setNombre(datos.getNombre());
		AsegurarFormatoNombreProgramaAcademicoValidoRule.obtenerInstancia().ejecutar(programa, daoFactory);
		AsegurarNombreProgramaAcademicoNoExistaRule.obtenerInstancia().ejecutar(programa, daoFactory);

		daoFactory.obtenerProgramaAcademicoDAO().actualizar(id, programa);
		Auditor.registrar(daoFactory, EventoAuditable.CAMBIAR_NOMBRE_PROGRAMA_ACADEMICO, id,
				Auditor.detalleCambio(nombreAnterior, programa.getNombre()));
		return ProgramaAcademicoEntidadAssembler.obtenerInstancia().convertirADominio(programa);
	}
}
