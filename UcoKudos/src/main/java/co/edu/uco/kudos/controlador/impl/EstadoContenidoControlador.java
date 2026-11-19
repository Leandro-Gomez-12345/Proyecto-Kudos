package co.edu.uco.kudos.controlador.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.kudos.controlador.AbstractRestController;
import co.edu.uco.kudos.controlador.respuesta.RespuestaOk;
import co.edu.uco.kudos.dto.EstadoContenidoDTO;
import co.edu.uco.kudos.negocio.fachada.EstadoContenidoFachada;
import co.edu.uco.kudos.negocio.fachada.impl.EstadoContenidoFachadaImpl;

@RestController
@RequestMapping("/api/v1/estados-contenido")
public class EstadoContenidoControlador extends AbstractRestController {

	private final EstadoContenidoFachada estadoContenidoFachada = new EstadoContenidoFachadaImpl();

	@GetMapping
	public ResponseEntity<RespuestaOk<EstadoContenidoDTO>> consultarEstadosContenido() {
		var estados = estadoContenidoFachada.consultarEstadosContenido();
		return ResponseEntity.ok(RespuestaOk.crear(estados));
	}
}
