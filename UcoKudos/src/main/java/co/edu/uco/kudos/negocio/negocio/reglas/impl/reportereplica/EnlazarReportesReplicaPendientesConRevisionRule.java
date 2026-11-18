package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica;

import java.util.ArrayList;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReporteReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;

public final class EnlazarReportesReplicaPendientesConRevisionRule implements Rule<ReporteReplicaEntidad> {

	private static final Rule<ReporteReplicaEntidad> INSTANCIA = new EnlazarReportesReplicaPendientesConRevisionRule();

	private EnlazarReportesReplicaPendientesConRevisionRule() {

	}

	public static Rule<ReporteReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReporteReplicaEntidad enlace, DAOFactory daoFactory) {
		var filtro = new ReporteReplicaEntidad();
		filtro.setReplica(enlace.getReplica());
		var reportes = new ArrayList<>(daoFactory.obtenerReporteReplicaDAO().consultarPorFiltro(filtro));
		AsegurarReportesReplicaPendientesRule.obtenerInstancia().ejecutar(reportes, daoFactory);
		for (var reporte : reportes) {
			reporte.setRevisionReplica(enlace.getRevisionReplica());
			daoFactory.obtenerReporteReplicaDAO().actualizar(reporte.getId(), reporte);
		}
	}
}
