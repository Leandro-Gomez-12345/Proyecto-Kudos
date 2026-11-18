package co.edu.uco.kudos.controlador.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.kudos.controlador.AbstractRestController;
import co.edu.uco.kudos.controlador.respuesta.RespuestaOk;
import co.edu.uco.kudos.dto.RolDTO;
import co.edu.uco.kudos.negocio.fachada.RolFachada;
import co.edu.uco.kudos.negocio.fachada.impl.RolFachadaImpl;

@RestController
@RequestMapping("/api/v1/roles")
public class RolControlador extends AbstractRestController {

	private final RolFachada rolFachada = new RolFachadaImpl();

	@GetMapping
	public ResponseEntity<RespuestaOk<RolDTO>> consultarRoles() {
		var roles = rolFachada.consultarRoles();
		return ResponseEntity.ok(RespuestaOk.crear(roles));
	}
}
