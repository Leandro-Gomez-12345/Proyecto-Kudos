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
import co.edu.uco.kudos.dto.ProgramaAcademicoDTO;
import co.edu.uco.kudos.negocio.fachada.ProgramaAcademicoFachada;
import co.edu.uco.kudos.negocio.fachada.impl.ProgramaAcademicoFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/programas-academicos")
public class ProgramaAcademicoControlador extends AbstractRestController {

	private final ProgramaAcademicoFachada programaAcademicoFachada = new ProgramaAcademicoFachadaImpl();

	@PostMapping
	public ResponseEntity<RespuestaOk<ProgramaAcademicoDTO>> registrarProgramaAcademico(@RequestBody ProgramaAcademicoDTO datos) {
		var programa = programaAcademicoFachada.registrarProgramaAcademico(datos);
		return responderCreado(CatalogoMensajes.ProgramaAcademicoControlador.PROGRAMA_REGISTRADO, programa);
	}

	@GetMapping
	public ResponseEntity<RespuestaOk<ProgramaAcademicoDTO>> consultarProgramasAcademicos(@ModelAttribute ProgramaAcademicoDTO filtro) {
		var programas = programaAcademicoFachada.consultarProgramasAcademicos(filtro);
		return ResponseEntity.ok(RespuestaOk.crear(programas));
	}

	@PatchMapping("/{id}/nombre")
	public ResponseEntity<RespuestaOk<ProgramaAcademicoDTO>> cambiarNombreProgramaAcademico(@PathVariable("id") UUID id,
			@RequestBody ProgramaAcademicoDTO datos) {
		var programa = programaAcademicoFachada.cambiarNombreProgramaAcademico(id, datos);
		return responderOk(CatalogoMensajes.ProgramaAcademicoControlador.NOMBRE_CAMBIADO, programa);
	}
}
