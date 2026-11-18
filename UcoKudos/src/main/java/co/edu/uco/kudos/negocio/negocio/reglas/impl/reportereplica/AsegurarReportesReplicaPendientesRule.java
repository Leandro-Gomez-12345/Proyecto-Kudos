package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReporteReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarReportesReplicaPendientesRule implements Rule<List<ReporteReplicaEntidad>> {

	private static final Rule<List<ReporteReplicaEntidad>> INSTANCIA = new AsegurarReportesReplicaPendientesRule();

	private AsegurarReportesReplicaPendientesRule() {

	}

	public static Rule<List<ReporteReplicaEntidad>> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(List<ReporteReplicaEntidad> reportes, DAOFactory daoFactory) {
		reportes.removeIf(reporte -> !UtilUUID.esValorDefecto(reporte.getRevisionReplica().getId()));
	}
}
