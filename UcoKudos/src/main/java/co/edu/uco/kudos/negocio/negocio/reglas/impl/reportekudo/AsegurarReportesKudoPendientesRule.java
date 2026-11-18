package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo;

import java.util.List;

import co.edu.uco.kudos.entidad.ReporteKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarReportesKudoPendientesRule implements Rule<List<ReporteKudoEntidad>> {

	private static final Rule<List<ReporteKudoEntidad>> INSTANCIA = new AsegurarReportesKudoPendientesRule();

	private AsegurarReportesKudoPendientesRule() {

	}

	public static Rule<List<ReporteKudoEntidad>> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void ejecutar(List<ReporteKudoEntidad>... datos) {
		var reportes = datos[0];

		reportes.removeIf(reporte -> !UtilUUID.esValorDefecto(reporte.getRevisionKudo().getId()));
	}
}
