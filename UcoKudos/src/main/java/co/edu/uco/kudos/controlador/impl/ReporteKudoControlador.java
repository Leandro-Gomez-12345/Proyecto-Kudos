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
import co.edu.uco.kudos.dto.ReporteKudoDTO;
import co.edu.uco.kudos.negocio.fachada.ReporteKudoFachada;
import co.edu.uco.kudos.negocio.fachada.impl.ReporteKudoFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/reportes-kudo")
public class ReporteKudoControlador extends AbstractRestController {

	private final ReporteKudoFachada reporteKudoFachada = new ReporteKudoFachadaImpl();

	@PostMapping
	public ResponseEntity<RespuestaOk<ReporteKudoDTO>> reportarKudo(@RequestBody ReporteKudoDTO datos) {
		var reporte = reporteKudoFachada.reportarKudo(datos);
		return responderCreado(CatalogoMensajes.ReporteKudoControlador.KUDO_REPORTADO, reporte);
	}

	@GetMapping
	public ResponseEntity<RespuestaOk<ReporteKudoDTO>> consultarReportesKudo(@ModelAttribute ReporteKudoDTO filtro,
			@RequestParam(name = "estado", defaultValue = "") String estado) {
		var reportes = reporteKudoFachada.consultarReportesKudo(filtro, estado);
		return ResponseEntity.ok(RespuestaOk.crear(reportes));
	}
}
