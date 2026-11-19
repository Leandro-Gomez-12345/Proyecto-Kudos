package co.edu.uco.kudos.controlador.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.kudos.controlador.AbstractRestController;
import co.edu.uco.kudos.controlador.respuesta.RespuestaOk;
import co.edu.uco.kudos.dto.ReporteReplicaDTO;
import co.edu.uco.kudos.negocio.fachada.ReporteReplicaFachada;
import co.edu.uco.kudos.negocio.fachada.impl.ReporteReplicaFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/reportes-replica")
public class ReporteReplicaControlador extends AbstractRestController {

	private final ReporteReplicaFachada reporteReplicaFachada = new ReporteReplicaFachadaImpl();

	@PostMapping
	public ResponseEntity<RespuestaOk<ReporteReplicaDTO>> reportarReplica(@RequestBody ReporteReplicaDTO datos) {
		var reporte = reporteReplicaFachada.reportarReplica(datos);
		return responderCreado(CatalogoMensajes.ReporteReplicaControlador.REPLICA_REPORTADA, reporte);
	}

	@GetMapping
	public ResponseEntity<RespuestaOk<ReporteReplicaDTO>> consultarReportesReplica(@ModelAttribute ReporteReplicaDTO filtro,
			@RequestParam(name = "estado", defaultValue = "") String estado) {
		var reportes = reporteReplicaFachada.consultarReportesReplica(filtro, estado);
		return ResponseEntity.ok(RespuestaOk.crear(reportes));
	}
}
