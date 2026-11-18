package co.edu.uco.kudos.negocio.fachada;

import java.util.List;

import co.edu.uco.kudos.dto.ReporteReplicaDTO;

public interface ReporteReplicaFachada {

	ReporteReplicaDTO reportarReplica(ReporteReplicaDTO datos);

	List<ReporteReplicaDTO> consultarReportesReplica(ReporteReplicaDTO filtro, String estado);
}
