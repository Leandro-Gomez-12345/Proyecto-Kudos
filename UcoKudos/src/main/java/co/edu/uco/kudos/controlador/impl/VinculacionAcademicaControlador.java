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
import co.edu.uco.kudos.dto.VinculacionAcademicaDTO;
import co.edu.uco.kudos.negocio.fachada.VinculacionAcademicaFachada;
import co.edu.uco.kudos.negocio.fachada.impl.VinculacionAcademicaFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/vinculaciones-academicas")
public class VinculacionAcademicaControlador extends AbstractRestController {

	private final VinculacionAcademicaFachada vinculacionAcademicaFachada = new VinculacionAcademicaFachadaImpl();

	@PostMapping
	public ResponseEntity<RespuestaOk<VinculacionAcademicaDTO>> vincularPersonaAProgramaAcademico(@RequestBody VinculacionAcademicaDTO datos) {
		var vinculacion = vinculacionAcademicaFachada.vincularPersonaAProgramaAcademico(datos);
		return responderCreado(CatalogoMensajes.VinculacionAcademicaControlador.PERSONA_VINCULADA, vinculacion);
	}

	@PatchMapping("/{id}/desvinculacion")
	public ResponseEntity<RespuestaOk<Void>> desvincularPersonaDeProgramaAcademico(@PathVariable("id") UUID id,
			@RequestBody VinculacionAcademicaDTO datos) {
		vinculacionAcademicaFachada.desvincularPersonaDeProgramaAcademico(id, datos);
		return responderOk(CatalogoMensajes.VinculacionAcademicaControlador.PERSONA_DESVINCULADA);
	}

	@GetMapping
	public ResponseEntity<RespuestaOk<VinculacionAcademicaDTO>> consultarVinculacionesAcademicas(@ModelAttribute VinculacionAcademicaDTO filtro) {
		var vinculaciones = vinculacionAcademicaFachada.consultarVinculacionesAcademicas(filtro);
		return ResponseEntity.ok(RespuestaOk.crear(vinculaciones));
	}
}
