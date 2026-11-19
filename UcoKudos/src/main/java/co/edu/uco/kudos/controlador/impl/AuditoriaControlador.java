package co.edu.uco.kudos.controlador.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.kudos.controlador.AbstractRestController;
import co.edu.uco.kudos.controlador.respuesta.RespuestaOk;
import co.edu.uco.kudos.dto.AuditoriaDTO;
import co.edu.uco.kudos.negocio.fachada.AuditoriaFachada;
import co.edu.uco.kudos.negocio.fachada.impl.AuditoriaFachadaImpl;

@RestController
@RequestMapping("/api/v1/auditorias")
public class AuditoriaControlador extends AbstractRestController {

	private final AuditoriaFachada auditoriaFachada = new AuditoriaFachadaImpl();

	@GetMapping
	public ResponseEntity<RespuestaOk<AuditoriaDTO>> consultarAuditorias(@ModelAttribute AuditoriaDTO filtro) {
		var auditorias = auditoriaFachada.consultarAuditorias(filtro);
		return ResponseEntity.ok(RespuestaOk.crear(auditorias));
	}

	@GetMapping("/propias")
	public ResponseEntity<RespuestaOk<AuditoriaDTO>> consultarAuditoriasPropias(@ModelAttribute AuditoriaDTO filtro) {
		var auditorias = auditoriaFachada.consultarAuditoriasPropias(filtro);
		return ResponseEntity.ok(RespuestaOk.crear(auditorias));
	}
}
