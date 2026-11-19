package co.edu.uco.kudos.controlador.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.kudos.controlador.AbstractRestController;
import co.edu.uco.kudos.controlador.respuesta.RespuestaOk;
import co.edu.uco.kudos.dto.MotivoDTO;
import co.edu.uco.kudos.negocio.fachada.MotivoFachada;
import co.edu.uco.kudos.negocio.fachada.impl.MotivoFachadaImpl;

@RestController
@RequestMapping("/api/v1/motivos")
public class MotivoControlador extends AbstractRestController {

	private final MotivoFachada motivoFachada = new MotivoFachadaImpl();

	@GetMapping
	public ResponseEntity<RespuestaOk<MotivoDTO>> consultarMotivos() {
		var motivos = motivoFachada.consultarMotivos();
		return ResponseEntity.ok(RespuestaOk.crear(motivos));
	}
}
