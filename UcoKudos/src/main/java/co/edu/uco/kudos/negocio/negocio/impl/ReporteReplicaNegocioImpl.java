package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.ReporteReplicaDominio;
import co.edu.uco.kudos.negocio.negocio.ReporteReplicaNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.ReporteReplicaEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.Auditor;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarKudoAdmiteInteraccionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.motivo.AsegurarMotivoExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsegurarReplicaExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsegurarReplicaPuedeReportarseRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.MarcarReplicaReportadaAlRecibirReporteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica.AsegurarConsultaReportesReplicaPorModeradoresRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica.AsegurarFormatoReporteReplicaValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica.AsegurarIdReporteReplicaUnicoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica.AsegurarReporteReplicaNoRepetidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica.AsignarDatosInicialesReporteReplicaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica.DeducirEstadoReporteReplicaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica.EnlazarReportesReplicaPendientesConRevisionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class ReporteReplicaNegocioImpl implements ReporteReplicaNegocio {

	private final DAOFactory daoFactory;

	public ReporteReplicaNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ReporteReplicaDominio reportarReplica(ReporteReplicaDominio datos) {
		var reporte = ReporteReplicaEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idReplica = reporte.getReplica().getId();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarFormatoReporteReplicaValidoRule.obtenerInstancia().ejecutar(reporte, daoFactory);
		AsegurarReplicaExisteRule.obtenerInstancia().ejecutar(idReplica, daoFactory);
		AsegurarReplicaPuedeReportarseRule.obtenerInstancia().ejecutar(idReplica, daoFactory);
		AsegurarKudoAdmiteInteraccionRule.obtenerInstancia().ejecutar(idKudoDe(idReplica), daoFactory);
		AsegurarMotivoExisteRule.obtenerInstancia().ejecutar(reporte.getMotivo().getId(), daoFactory);
		AsegurarReporteReplicaNoRepetidoRule.obtenerInstancia().ejecutar(reporte, daoFactory);
		AsegurarIdReporteReplicaUnicoRule.obtenerInstancia().ejecutar(reporte, daoFactory);
		AsignarDatosInicialesReporteReplicaRule.obtenerInstancia().ejecutar(reporte);

		daoFactory.obtenerReporteReplicaDAO().crear(reporte);
		MarcarReplicaReportadaAlRecibirReporteRule.obtenerInstancia().ejecutar(idReplica, daoFactory);
		Auditor.registrar(daoFactory, EventoAuditable.REPORTAR_REPLICA, reporte.getId(),
				UtilTexto.concatenar("Reporte por el motivo ",
						ConsultorCatalogos.nombreMotivo(daoFactory, reporte.getMotivo().getId())));
		return ReporteReplicaEntidadAssembler.obtenerInstancia().convertirADominio(reporte);
	}

	@Override
	public List<ReporteReplicaDominio> consultarReportesReplica(ReporteReplicaDominio filtro, String estado) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarConsultaReportesReplicaPorModeradoresRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);

		var filtroEntidad = ReporteReplicaEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		var reportes = new ArrayList<>(daoFactory.obtenerReporteReplicaDAO().consultarPorFiltro(filtroEntidad));
		DeducirEstadoReporteReplicaRule.obtenerInstancia().ejecutar(reportes, estado, daoFactory);
		return ReporteReplicaEntidadAssembler.obtenerInstancia().convertirADominio(reportes);
	}

	@Override
	public void enlazarReportesReplicaConRevision(ReporteReplicaDominio filtro, ReporteReplicaDominio datos) {
		var enlace = ReporteReplicaEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		var datosEntidad = ReporteReplicaEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		enlace.setRevisionReplica(datosEntidad.getRevisionReplica());
		EnlazarReportesReplicaPendientesConRevisionRule.obtenerInstancia().ejecutar(enlace, daoFactory);
	}

	private UUID idKudoDe(UUID idReplica) {
		return daoFactory.obtenerReplicaDAO().consultarPorId(idReplica).getKudo().getId();
	}
}
