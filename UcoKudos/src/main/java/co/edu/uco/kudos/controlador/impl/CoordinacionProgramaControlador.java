package co.edu.uco.kudos.controlador.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.kudos.controlador.AbstractRestController;
import co.edu.uco.kudos.controlador.respuesta.RespuestaOk;
import co.edu.uco.kudos.dto.CoordinacionProgramaDTO;
import co.edu.uco.kudos.negocio.fachada.CoordinacionProgramaFachada;
import co.edu.uco.kudos.negocio.fachada.impl.CoordinacionProgramaFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/coordinaciones-programa")
public class CoordinacionProgramaControlador extends AbstractRestController {

	private final CoordinacionProgramaFachada coordinacionProgramaFachada = new CoordinacionProgramaFachadaImpl();

	@PostMapping
	public ResponseEntity<RespuestaOk<CoordinacionProgramaDTO>> asignarCoordinadorPrograma(@RequestBody CoordinacionProgramaDTO datos) {
		var coordinacion = coordinacionProgramaFachada.asignarCoordinadorPrograma(datos);
		return responderCreado(CatalogoMensajes.CoordinacionProgramaControlador.COORDINADOR_ASIGNADO, coordinacion);
	}

	@GetMapping
	public ResponseEntity<RespuestaOk<CoordinacionProgramaDTO>> consultarCoordinacionesPrograma(@ModelAttribute CoordinacionProgramaDTO filtro) {
		var coordinaciones = coordinacionProgramaFachada.consultarCoordinacionesPrograma(filtro);
		return ResponseEntity.ok(RespuestaOk.crear(coordinaciones));
	}
}
