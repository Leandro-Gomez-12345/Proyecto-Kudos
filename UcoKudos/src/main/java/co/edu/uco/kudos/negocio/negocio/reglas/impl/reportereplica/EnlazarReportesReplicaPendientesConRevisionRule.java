package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica;

import java.util.ArrayList;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReporteReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;

public final class EnlazarReportesReplicaPendientesConRevisionRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new EnlazarReportesReplicaPendientesConRevisionRule();

	private EnlazarReportesReplicaPendientesConRevisionRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void ejecutar(Object... datos) {
		var enlace = (ReporteReplicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var filtro = new ReporteReplicaEntidad();
		filtro.setReplica(enlace.getReplica());
		var reportes = new ArrayList<>(daoFactory.obtenerReporteReplicaDAO().consultarPorFiltro(filtro));
		AsegurarReportesReplicaPendientesRule.obtenerInstancia().ejecutar(reportes);
		for (var reporte : reportes) {
			reporte.setRevisionReplica(enlace.getRevisionReplica());
			daoFactory.obtenerReporteReplicaDAO().actualizar(reporte.getId(), reporte);
		}
	}
}
