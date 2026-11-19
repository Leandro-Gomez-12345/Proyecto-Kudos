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
import co.edu.uco.kudos.dto.CoordinacionFacultadDTO;
import co.edu.uco.kudos.negocio.fachada.CoordinacionFacultadFachada;
import co.edu.uco.kudos.negocio.fachada.impl.CoordinacionFacultadFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/coordinaciones-facultad")
public class CoordinacionFacultadControlador extends AbstractRestController {

	private final CoordinacionFacultadFachada coordinacionFacultadFachada = new CoordinacionFacultadFachadaImpl();

	@PostMapping
	public ResponseEntity<RespuestaOk<CoordinacionFacultadDTO>> asignarCoordinadorFacultad(@RequestBody CoordinacionFacultadDTO datos) {
		var coordinacion = coordinacionFacultadFachada.asignarCoordinadorFacultad(datos);
		return responderCreado(CatalogoMensajes.CoordinacionFacultadControlador.COORDINADOR_ASIGNADO, coordinacion);
	}

	@GetMapping
	public ResponseEntity<RespuestaOk<CoordinacionFacultadDTO>> consultarCoordinacionesFacultad(@ModelAttribute CoordinacionFacultadDTO filtro) {
		var coordinaciones = coordinacionFacultadFachada.consultarCoordinacionesFacultad(filtro);
		return ResponseEntity.ok(RespuestaOk.crear(coordinaciones));
	}
}
