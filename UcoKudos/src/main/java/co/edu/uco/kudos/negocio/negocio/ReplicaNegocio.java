package co.edu.uco.kudos.negocio.negocio;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dominio.ReplicaDominio;

public interface ReplicaNegocio {

	ReplicaDominio replicarKudo(ReplicaDominio datos);

	ReplicaDominio responderReplica(ReplicaDominio datos);

	List<ReplicaDominio> consultarConversacionKudo(ReplicaDominio filtro);

	void marcarReplicaReportada(UUID id);

	void ponerReplicaEnRevision(UUID id);

	void vetarReplica(UUID id);

	void liberarReplica(UUID id);
}
