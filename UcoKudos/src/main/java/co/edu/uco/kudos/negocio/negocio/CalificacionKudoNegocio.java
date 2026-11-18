package co.edu.uco.kudos.negocio.negocio;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dominio.CalificacionKudoDominio;

public interface CalificacionKudoNegocio {

	CalificacionKudoDominio calificarKudo(CalificacionKudoDominio datos);

	CalificacionKudoDominio cambiarCalificacionKudo(UUID id, CalificacionKudoDominio datos);

	List<CalificacionKudoDominio> consultarCalificacionesKudo(CalificacionKudoDominio filtro);
}
