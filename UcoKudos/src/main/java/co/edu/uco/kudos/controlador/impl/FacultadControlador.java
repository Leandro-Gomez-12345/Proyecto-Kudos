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
import co.edu.uco.kudos.dto.FacultadDTO;
import co.edu.uco.kudos.negocio.fachada.FacultadFachada;
import co.edu.uco.kudos.negocio.fachada.impl.FacultadFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/facultades")
public class FacultadControlador extends AbstractRestController {

	private final FacultadFachada facultadFachada = new FacultadFachadaImpl();

	@PostMapping
	public ResponseEntity<RespuestaOk<FacultadDTO>> registrarFacultad(@RequestBody FacultadDTO datos) {
		var facultad = facultadFachada.registrarFacultad(datos);
		return responderCreado(CatalogoMensajes.FacultadControlador.FACULTAD_REGISTRADA, facultad);
	}

	@GetMapping
	public ResponseEntity<RespuestaOk<FacultadDTO>> consultarFacultades(@ModelAttribute FacultadDTO filtro) {
		var facultades = facultadFachada.consultarFacultades(filtro);
		return ResponseEntity.ok(RespuestaOk.crear(facultades));
	}

	@PatchMapping("/{id}/nombre")
	public ResponseEntity<RespuestaOk<FacultadDTO>> cambiarNombreFacultad(@PathVariable("id") UUID id,
			@RequestBody FacultadDTO datos) {
		var facultad = facultadFachada.cambiarNombreFacultad(id, datos);
		return responderOk(CatalogoMensajes.FacultadControlador.NOMBRE_CAMBIADO, facultad);
	}
}
