package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo;

import java.util.ArrayList;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReporteKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;

public final class EnlazarReportesKudoPendientesConRevisionRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new EnlazarReportesKudoPendientesConRevisionRule();

	private EnlazarReportesKudoPendientesConRevisionRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void ejecutar(Object... datos) {
		var enlace = (ReporteKudoEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var filtro = new ReporteKudoEntidad();
		filtro.setKudo(enlace.getKudo());
		var reportes = new ArrayList<>(daoFactory.obtenerReporteKudoDAO().consultarPorFiltro(filtro));
		AsegurarReportesKudoPendientesRule.obtenerInstancia().ejecutar(reportes);
		for (var reporte : reportes) {
			reporte.setRevisionKudo(enlace.getRevisionKudo());
			daoFactory.obtenerReporteKudoDAO().actualizar(reporte.getId(), reporte);
		}
	}
}
