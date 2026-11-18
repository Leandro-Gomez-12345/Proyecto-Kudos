package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.CoordinacionFacultadDominio;
import co.edu.uco.kudos.entidad.CoordinacionFacultadEntidad;
import co.edu.uco.kudos.negocio.negocio.CoordinacionFacultadNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.CoordinacionFacultadEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.Auditor;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad.AsegurarCoordinacionFacultadNoRepetidaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad.AsegurarCoordinacionFacultadVigenteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad.AsegurarFacultadSinCoordinacionVigenteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad.AsegurarFormatoCoordinacionFacultadValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad.AsegurarIdCoordinacionFacultadUnicoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad.AsegurarPersonaSinOtraCoordinacionFacultadRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad.FinalizarCoordinacionFacultadAlSuspenderODesactivarRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.facultad.AsegurarFacultadExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarPersonaIndicadaActivaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarPersonaTieneRolCoordinadorFacultadRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioEsCoordinadorAcademicoRule;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class CoordinacionFacultadNegocioImpl implements CoordinacionFacultadNegocio {

	private final DAOFactory daoFactory;

	public CoordinacionFacultadNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public CoordinacionFacultadDominio asignarCoordinadorFacultad(CoordinacionFacultadDominio datos) {
		var coordinacion = CoordinacionFacultadEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();
		var idPersona = coordinacion.getUsuario().getId();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioEsCoordinadorAcademicoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarFormatoCoordinacionFacultadValidoRule.obtenerInstancia().ejecutar(coordinacion);
		AsegurarFacultadExisteRule.obtenerInstancia().ejecutar(coordinacion.getFacultad().getId(), daoFactory);
		AsegurarPersonaIndicadaActivaRule.obtenerInstancia().ejecutar(idPersona, daoFactory);
		AsegurarPersonaTieneRolCoordinadorFacultadRule.obtenerInstancia().ejecutar(idPersona, daoFactory);
		AsegurarFacultadSinCoordinacionVigenteRule.obtenerInstancia().ejecutar(coordinacion, daoFactory);
		AsegurarPersonaSinOtraCoordinacionFacultadRule.obtenerInstancia().ejecutar(coordinacion, daoFactory);
		AsegurarCoordinacionFacultadNoRepetidaRule.obtenerInstancia().ejecutar(coordinacion, daoFactory);
		AsegurarIdCoordinacionFacultadUnicoRule.obtenerInstancia().ejecutar(coordinacion, daoFactory);

		daoFactory.obtenerCoordinacionFacultadDAO().crear(coordinacion);
		var detalle = UtilTexto.concatenar("Coordinación de facultad asignada desde ",
				coordinacion.getFechaInicio().toString());
		Auditor.registrar(daoFactory, EventoAuditable.ASIGNAR_COORDINADOR_FACULTAD, coordinacion.getId(), detalle);
		return CoordinacionFacultadEntidadAssembler.obtenerInstancia().convertirADominio(coordinacion);
	}

	@Override
	public void finalizarCoordinacionFacultad(CoordinacionFacultadDominio filtro, CoordinacionFacultadDominio datos) {
		var coordinacion = CoordinacionFacultadEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		coordinacion.setFechaFin(CoordinacionFacultadEntidadAssembler.obtenerInstancia().convertirAEntidad(datos)
				.getFechaFin());

		var vigentes = ConsultorVigencias.coordinacionesFacultad(daoFactory, coordinacion.getUsuario().getId(),
				UtilUUID.obtenerUUIDDefecto());
		var vigente = vigentes.isEmpty() ? new CoordinacionFacultadEntidad() : vigentes.get(0);
		AsegurarCoordinacionFacultadVigenteRule.obtenerInstancia().ejecutar(vigente);
		FinalizarCoordinacionFacultadAlSuspenderODesactivarRule.obtenerInstancia().ejecutar(coordinacion, daoFactory);

		Auditor.registrar(daoFactory, EventoAuditable.FINALIZAR_COORDINACION_FACULTAD, vigente.getId(),
				"Coordinación de facultad finalizada");
	}

	@Override
	public List<CoordinacionFacultadDominio> consultarCoordinacionesFacultad(CoordinacionFacultadDominio filtro) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);

		var filtroEntidad = CoordinacionFacultadEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		var coordinaciones = daoFactory.obtenerCoordinacionFacultadDAO().consultarPorFiltro(filtroEntidad);
		return CoordinacionFacultadEntidadAssembler.obtenerInstancia().convertirADominio(coordinaciones);
	}
}
