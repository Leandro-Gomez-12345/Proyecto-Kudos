package co.edu.uco.kudos.negocio.fachada;

import java.util.List;

import co.edu.uco.kudos.dto.ReplicaDTO;

public interface ReplicaFachada {

	ReplicaDTO replicarKudo(ReplicaDTO datos);

	ReplicaDTO responderReplica(ReplicaDTO datos);

	List<ReplicaDTO> consultarConversacionKudo(ReplicaDTO filtro);
}
