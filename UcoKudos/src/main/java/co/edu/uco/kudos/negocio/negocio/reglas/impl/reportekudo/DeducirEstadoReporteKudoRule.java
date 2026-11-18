package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReporteKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosReporte;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ReportesConEstado;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class DeducirEstadoReporteKudoRule implements Rule<ReportesConEstado<ReporteKudoEntidad>> {

	private static final Rule<ReportesConEstado<ReporteKudoEntidad>> INSTANCIA = new DeducirEstadoReporteKudoRule();

	private DeducirEstadoReporteKudoRule() {

	}

	public static Rule<ReportesConEstado<ReporteKudoEntidad>> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReportesConEstado<ReporteKudoEntidad> consulta, DAOFactory daoFactory) {
		if (UtilTexto.esVacia(consulta.estado())) {
			return;
		}
		consulta.reportes().removeIf(reporte -> !EstadosReporte.coincide(
				deducirEstado(reporte, daoFactory), consulta.estado()));
	}

	private static String deducirEstado(ReporteKudoEntidad reporte, DAOFactory daoFactory) {
		var idRevision = reporte.getRevisionKudo().getId();
		var sinRevision = UtilUUID.esValorDefecto(idRevision);
		if (sinRevision) {
			return EstadosReporte.deducir(sinRevision, UtilTexto.VACIA);
		}
		var revision = daoFactory.obtenerRevisionKudoDAO().consultarPorId(idRevision);
		var nombreEstado = ConsultorCatalogos.nombreEstadoRevision(daoFactory,
				revision.getEstadoRevision().getId());
		return EstadosReporte.deducir(sinRevision, nombreEstado);
	}
}
