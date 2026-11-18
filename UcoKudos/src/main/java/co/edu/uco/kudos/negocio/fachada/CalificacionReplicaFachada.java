package co.edu.uco.kudos.negocio.fachada;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dto.CalificacionReplicaDTO;

public interface CalificacionReplicaFachada {

	CalificacionReplicaDTO calificarReplica(CalificacionReplicaDTO datos);

	CalificacionReplicaDTO cambiarCalificacionReplica(UUID id, CalificacionReplicaDTO datos);

	List<CalificacionReplicaDTO> consultarCalificacionesReplica(CalificacionReplicaDTO filtro);
}
