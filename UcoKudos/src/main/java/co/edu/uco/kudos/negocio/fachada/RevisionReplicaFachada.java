package co.edu.uco.kudos.negocio.fachada;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dto.RevisionReplicaDTO;

public interface RevisionReplicaFachada {

	RevisionReplicaDTO iniciarRevisionReplica(RevisionReplicaDTO datos);

	void cerrarRevisionReplicaConVeto(UUID id, RevisionReplicaDTO datos);

	void cerrarRevisionReplicaConLiberacion(UUID id, RevisionReplicaDTO datos);

	RevisionReplicaDTO vetarReplicaDirectamente(RevisionReplicaDTO datos);

	List<RevisionReplicaDTO> consultarRevisionesReplica(RevisionReplicaDTO filtro);
}
