package co.edu.uco.kudos.negocio.fachada;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dto.CalificacionKudoDTO;

public interface CalificacionKudoFachada {

	CalificacionKudoDTO calificarKudo(CalificacionKudoDTO datos);

	CalificacionKudoDTO cambiarCalificacionKudo(UUID id, CalificacionKudoDTO datos);

	List<CalificacionKudoDTO> consultarCalificacionesKudo(CalificacionKudoDTO filtro);
}
