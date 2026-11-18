package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo;

import co.edu.uco.kudos.entidad.ReporteKudoEntidad;
import co.edu.uco.kudos.entidad.RevisionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsignarDatosInicialesReporteKudoRule implements Rule<ReporteKudoEntidad> {

	private static final Rule<ReporteKudoEntidad> INSTANCIA = new AsignarDatosInicialesReporteKudoRule();

	private AsignarDatosInicialesReporteKudoRule() {

	}

	public static Rule<ReporteKudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReporteKudoEntidad... datos) {
		var reporte = datos[0];

		reporte.getUsuario().setId(ContextoEjecucion.obtenerIdEjecutor());
		reporte.setFechaReporte(UtilFechaHora.obtenerFechaHoraActual());
		reporte.setRevisionKudo(new RevisionKudoEntidad());
	}
}
