package co.edu.uco.kudos.controlador.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.kudos.controlador.AbstractRestController;
import co.edu.uco.kudos.controlador.respuesta.RespuestaOk;
import co.edu.uco.kudos.dto.EstadoCuentaDTO;
import co.edu.uco.kudos.negocio.fachada.EstadoCuentaFachada;
import co.edu.uco.kudos.negocio.fachada.impl.EstadoCuentaFachadaImpl;

@RestController
@RequestMapping("/api/v1/estados-cuenta")
public class EstadoCuentaControlador extends AbstractRestController {

	private final EstadoCuentaFachada estadoCuentaFachada = new EstadoCuentaFachadaImpl();

	@GetMapping
	public ResponseEntity<RespuestaOk<EstadoCuentaDTO>> consultarEstadosCuenta() {
		var estados = estadoCuentaFachada.consultarEstadosCuenta();
		return ResponseEntity.ok(RespuestaOk.crear(estados));
	}
}
