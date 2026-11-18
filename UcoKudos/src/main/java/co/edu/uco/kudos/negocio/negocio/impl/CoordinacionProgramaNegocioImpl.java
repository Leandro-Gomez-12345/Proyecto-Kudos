package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.CoordinacionProgramaDominio;
import co.edu.uco.kudos.entidad.CoordinacionProgramaEntidad;
import co.edu.uco.kudos.negocio.negocio.CoordinacionProgramaNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.CoordinacionProgramaEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.Auditor;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma.AsegurarCoordinacionProgramaNoRepetidaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma.AsegurarCoordinacionProgramaVigenteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma.AsegurarFormatoCoordinacionProgramaValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma.AsegurarIdCoordinacionProgramaUnicoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma.AsegurarPersonaSinOtraCoordinacionProgramaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma.AsegurarProgramaAcademicoSinCoordinacionVigenteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma.AsegurarProgramaPerteneceAFacultadDelCoordinadorRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma.FinalizarCoordinacionProgramaAlSuspenderODesactivarRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.programaacademico.AsegurarProgramaAcademicoExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.ActivarCuentaCuandoCumplaCondicionesRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AprobarCuentaAlAsignarCoordinacionProgramaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarPersonaAsignableComoCoordinadorProgramaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioEsCoordinadorFacultadRule;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class CoordinacionProgramaNegocioImpl implements CoordinacionProgramaNegocio {

	private final DAOFactory daoFactory;

	public CoordinacionProgramaNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public CoordinacionProgramaDominio asignarCoordinadorPrograma(CoordinacionProgramaDominio datos) {
		var coordinacion = CoordinacionProgramaEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();
		var idPersona = coordinacion.getUsuario().getId();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioEsCoordinadorFacultadRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarFormatoCoordinacionProgramaValidoRule.obtenerInstancia().ejecutar(coordinacion, daoFactory);
		AsegurarProgramaAcademicoExisteRule.obtenerInstancia().ejecutar(coordinacion.getProgramaAcademico().getId(),
				daoFactory);
		AsegurarProgramaPerteneceAFacultadDelCoordinadorRule.obtenerInstancia().ejecutar(coordinacion, daoFactory);
		AsegurarPersonaAsignableComoCoordinadorProgramaRule.obtenerInstancia().ejecutar(idPersona, daoFactory);
		AsegurarProgramaAcademicoSinCoordinacionVigenteRule.obtenerInstancia().ejecutar(coordinacion, daoFactory);
		AsegurarPersonaSinOtraCoordinacionProgramaRule.obtenerInstancia().ejecutar(coordinacion, daoFactory);
		AsegurarCoordinacionProgramaNoRepetidaRule.obtenerInstancia().ejecutar(coordinacion, daoFactory);
		AsegurarIdCoordinacionProgramaUnicoRule.obtenerInstancia().ejecutar(coordinacion, daoFactory);

		daoFactory.obtenerCoordinacionProgramaDAO().crear(coordinacion);
		AprobarCuentaAlAsignarCoordinacionProgramaRule.obtenerInstancia().ejecutar(idPersona, daoFactory);
		activarCuentaSiCumpleCondiciones(idPersona);
		var detalle = UtilTexto.concatenar("Coordinación de programa asignada desde ",
				coordinacion.getFechaInicio().toString());
		Auditor.registrar(daoFactory, EventoAuditable.ASIGNAR_COORDINADOR_PROGRAMA, coordinacion.getId(), detalle);
		return CoordinacionProgramaEntidadAssembler.obtenerInstancia().convertirADominio(coordinacion);
	}

	@Override
	public void finalizarCoordinacionPrograma(CoordinacionProgramaDominio filtro, CoordinacionProgramaDominio datos) {
		var coordinacion = CoordinacionProgramaEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		coordinacion.setFechaFin(CoordinacionProgramaEntidadAssembler.obtenerInstancia().convertirAEntidad(datos)
				.getFechaFin());

		var vigentes = ConsultorVigencias.coordinacionesPrograma(daoFactory, coordinacion.getUsuario().getId(),
				UtilUUID.obtenerUUIDDefecto());
		var vigente = vigentes.isEmpty() ? new CoordinacionProgramaEntidad() : vigentes.get(0);
		AsegurarCoordinacionProgramaVigenteRule.obtenerInstancia().ejecutar(vigente, daoFactory);
		FinalizarCoordinacionProgramaAlSuspenderODesactivarRule.obtenerInstancia().ejecutar(coordinacion, daoFactory);

		Auditor.registrar(daoFactory, EventoAuditable.FINALIZAR_COORDINACION_PROGRAMA, vigente.getId(),
				"Coordinación de programa finalizada");
	}

	@Override
	public List<CoordinacionProgramaDominio> consultarCoordinacionesPrograma(CoordinacionProgramaDominio filtro) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);

		var filtroEntidad = CoordinacionProgramaEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		var coordinaciones = daoFactory.obtenerCoordinacionProgramaDAO().consultarPorFiltro(filtroEntidad);
		return CoordinacionProgramaEntidadAssembler.obtenerInstancia().convertirADominio(coordinaciones);
	}

	private void activarCuentaSiCumpleCondiciones(UUID idPersona) {
		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(idPersona);
		ActivarCuentaCuandoCumplaCondicionesRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		daoFactory.obtenerUsuarioDAO().actualizar(idPersona, usuario);
	}
}
