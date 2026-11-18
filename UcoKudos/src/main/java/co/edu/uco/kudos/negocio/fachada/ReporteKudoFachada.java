package co.edu.uco.kudos.negocio.fachada;

import java.util.List;

import co.edu.uco.kudos.dto.ReporteKudoDTO;

public interface ReporteKudoFachada {

	ReporteKudoDTO reportarKudo(ReporteKudoDTO datos);

	List<ReporteKudoDTO> consultarReportesKudo(ReporteKudoDTO filtro, String estado);
}
