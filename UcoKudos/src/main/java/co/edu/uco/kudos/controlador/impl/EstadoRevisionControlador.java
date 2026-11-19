package co.edu.uco.kudos.controlador.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.kudos.controlador.AbstractRestController;
import co.edu.uco.kudos.controlador.respuesta.RespuestaOk;
import co.edu.uco.kudos.dto.EstadoRevisionDTO;
import co.edu.uco.kudos.negocio.fachada.EstadoRevisionFachada;
import co.edu.uco.kudos.negocio.fachada.impl.EstadoRevisionFachadaImpl;

@RestController
@RequestMapping("/api/v1/estados-revision")
public class EstadoRevisionControlador extends AbstractRestController {

	private final EstadoRevisionFachada estadoRevisionFachada = new EstadoRevisionFachadaImpl();

	@GetMapping
	public ResponseEntity<RespuestaOk<EstadoRevisionDTO>> consultarEstadosRevision() {
		var estados = estadoRevisionFachada.consultarEstadosRevision();
		return ResponseEntity.ok(RespuestaOk.crear(estados));
	}
}
