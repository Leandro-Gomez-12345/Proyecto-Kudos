package co.edu.uco.kudos.negocio.negocio;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dominio.RevisionReplicaDominio;

public interface RevisionReplicaNegocio {

	RevisionReplicaDominio iniciarRevisionReplica(RevisionReplicaDominio datos);

	void cerrarRevisionReplicaConVeto(UUID id, RevisionReplicaDominio datos);

	void cerrarRevisionReplicaConLiberacion(UUID id, RevisionReplicaDominio datos);

	RevisionReplicaDominio vetarReplicaDirectamente(RevisionReplicaDominio datos);

	List<RevisionReplicaDominio> consultarRevisionesReplica(RevisionReplicaDominio filtro);
}
