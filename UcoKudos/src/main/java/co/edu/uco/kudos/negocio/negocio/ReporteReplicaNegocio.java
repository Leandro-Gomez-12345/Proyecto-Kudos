package co.edu.uco.kudos.negocio.negocio;

import java.util.List;

import co.edu.uco.kudos.dominio.ReporteReplicaDominio;

public interface ReporteReplicaNegocio {

	ReporteReplicaDominio reportarReplica(ReporteReplicaDominio datos);

	List<ReporteReplicaDominio> consultarReportesReplica(ReporteReplicaDominio filtro, String estado);

	void enlazarReportesReplicaConRevision(ReporteReplicaDominio filtro, ReporteReplicaDominio datos);
}
