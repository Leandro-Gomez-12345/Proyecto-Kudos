package co.edu.uco.kudos.negocio.negocio;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dominio.CalificacionReplicaDominio;

public interface CalificacionReplicaNegocio {

	CalificacionReplicaDominio calificarReplica(CalificacionReplicaDominio datos);

	CalificacionReplicaDominio cambiarCalificacionReplica(UUID id, CalificacionReplicaDominio datos);

	List<CalificacionReplicaDominio> consultarCalificacionesReplica(CalificacionReplicaDominio filtro);
}
