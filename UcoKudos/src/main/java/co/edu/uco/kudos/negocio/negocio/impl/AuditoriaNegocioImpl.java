package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.AuditoriaDominio;
import co.edu.uco.kudos.negocio.negocio.AuditoriaNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.AuditoriaEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.auditoria.AsegurarAccionEsEventoAuditableRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.auditoria.AsegurarConsultaSoloActividadPropiaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.auditoria.AsegurarDetalleSinDatosSensiblesRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.auditoria.AsegurarFormatoAuditoriaValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.auditoria.AsegurarIdAuditoriaUnicoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.auditoria.AsegurarUsuarioPuedeConsultarAuditoriaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.auditoria.AsignarFechaHoraAuditoriaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AuditoriaNegocioImpl implements AuditoriaNegocio {

	private final DAOFactory daoFactory;

	public AuditoriaNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void registrarAuditoria(AuditoriaDominio datos) {
		var auditoria = AuditoriaEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);

		AsegurarFormatoAuditoriaValidoRule.obtenerInstancia().ejecutar(auditoria);
		AsegurarAccionEsEventoAuditableRule.obtenerInstancia().ejecutar(auditoria);
		AsegurarDetalleSinDatosSensiblesRule.obtenerInstancia().ejecutar(auditoria);
		AsegurarIdAuditoriaUnicoRule.obtenerInstancia().ejecutar(auditoria, daoFactory);
		AsignarFechaHoraAuditoriaRule.obtenerInstancia().ejecutar(auditoria);

		daoFactory.obtenerAuditoriaDAO().crear(auditoria);
	}

	@Override
	public List<AuditoriaDominio> consultarAuditorias(AuditoriaDominio filtro) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioPuedeConsultarAuditoriaRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);

		var filtroEntidad = AuditoriaEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		var auditorias = daoFactory.obtenerAuditoriaDAO().consultarPorFiltro(filtroEntidad);
		return AuditoriaEntidadAssembler.obtenerInstancia().convertirADominio(auditorias);
	}

	@Override
	public List<AuditoriaDominio> consultarAuditoriasPropias(AuditoriaDominio filtro) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);

		var filtroEntidad = AuditoriaEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		AsegurarConsultaSoloActividadPropiaRule.obtenerInstancia().ejecutar(filtroEntidad);

		var auditorias = daoFactory.obtenerAuditoriaDAO().consultarPorFiltro(filtroEntidad);
		return AuditoriaEntidadAssembler.obtenerInstancia().convertirADominio(auditorias);
	}
}
