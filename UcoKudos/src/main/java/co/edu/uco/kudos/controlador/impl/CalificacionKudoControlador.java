package co.edu.uco.kudos.controlador.impl;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.kudos.controlador.AbstractRestController;
import co.edu.uco.kudos.controlador.respuesta.RespuestaOk;
import co.edu.uco.kudos.dto.CalificacionKudoDTO;
import co.edu.uco.kudos.negocio.fachada.CalificacionKudoFachada;
import co.edu.uco.kudos.negocio.fachada.impl.CalificacionKudoFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/calificaciones-kudo")
public class CalificacionKudoControlador extends AbstractRestController {

	private final CalificacionKudoFachada calificacionKudoFachada = new CalificacionKudoFachadaImpl();

	@PostMapping
	public ResponseEntity<RespuestaOk<CalificacionKudoDTO>> calificarKudo(@RequestBody CalificacionKudoDTO datos) {
		var calificacion = calificacionKudoFachada.calificarKudo(datos);
		return responderCreado(CatalogoMensajes.CalificacionKudoControlador.KUDO_CALIFICADO, calificacion);
	}

	@PatchMapping("/{id}/estrellas")
	public ResponseEntity<RespuestaOk<CalificacionKudoDTO>> cambiarCalificacionKudo(@PathVariable("id") UUID id,
			@RequestBody CalificacionKudoDTO datos) {
		var calificacion = calificacionKudoFachada.cambiarCalificacionKudo(id, datos);
		return responderOk(CatalogoMensajes.CalificacionKudoControlador.CALIFICACION_CAMBIADA, calificacion);
	}

	@GetMapping
	public ResponseEntity<RespuestaOk<CalificacionKudoDTO>> consultarCalificacionesKudo(@ModelAttribute CalificacionKudoDTO filtro) {
		var calificaciones = calificacionKudoFachada.consultarCalificacionesKudo(filtro);
		return ResponseEntity.ok(RespuestaOk.crear(calificaciones));
	}
}
