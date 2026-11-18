package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo;

import java.util.ArrayList;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReporteKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;

public final class EnlazarReportesKudoPendientesConRevisionRule implements Rule<ReporteKudoEntidad> {

	private static final Rule<ReporteKudoEntidad> INSTANCIA = new EnlazarReportesKudoPendientesConRevisionRule();

	private EnlazarReportesKudoPendientesConRevisionRule() {

	}

	public static Rule<ReporteKudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReporteKudoEntidad enlace, DAOFactory daoFactory) {
		var filtro = new ReporteKudoEntidad();
		filtro.setKudo(enlace.getKudo());
		var reportes = new ArrayList<>(daoFactory.obtenerReporteKudoDAO().consultarPorFiltro(filtro));
		AsegurarReportesKudoPendientesRule.obtenerInstancia().ejecutar(reportes, daoFactory);
		for (var reporte : reportes) {
			reporte.setRevisionKudo(enlace.getRevisionKudo());
			daoFactory.obtenerReporteKudoDAO().actualizar(reporte.getId(), reporte);
		}
	}
}
