package co.edu.uco.kudos.negocio.negocio;

import java.util.List;

import co.edu.uco.kudos.dominio.ReporteKudoDominio;

public interface ReporteKudoNegocio {

	ReporteKudoDominio reportarKudo(ReporteKudoDominio datos);

	List<ReporteKudoDominio> consultarReportesKudo(ReporteKudoDominio filtro, String estado);

	void enlazarReportesKudoConRevision(ReporteKudoDominio filtro, ReporteKudoDominio datos);
}
