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
import co.edu.uco.kudos.dto.RevisionKudoDTO;
import co.edu.uco.kudos.negocio.fachada.RevisionKudoFachada;
import co.edu.uco.kudos.negocio.fachada.impl.RevisionKudoFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/revisiones-kudo")
public class RevisionKudoControlador extends AbstractRestController {

	private final RevisionKudoFachada revisionKudoFachada = new RevisionKudoFachadaImpl();

	@PostMapping
	public ResponseEntity<RespuestaOk<RevisionKudoDTO>> iniciarRevisionKudo(@RequestBody RevisionKudoDTO datos) {
		var revision = revisionKudoFachada.iniciarRevisionKudo(datos);
		return responderCreado(CatalogoMensajes.RevisionKudoControlador.REVISION_INICIADA, revision);
	}

	@PostMapping("/veto-directo")
	public ResponseEntity<RespuestaOk<RevisionKudoDTO>> vetarKudoDirectamente(@RequestBody RevisionKudoDTO datos) {
		var revision = revisionKudoFachada.vetarKudoDirectamente(datos);
		return responderCreado(CatalogoMensajes.RevisionKudoControlador.KUDO_VETADO, revision);
	}

	@PatchMapping("/{id}/veto")
	public ResponseEntity<RespuestaOk<Void>> cerrarRevisionKudoConVeto(@PathVariable("id") UUID id,
			@RequestBody RevisionKudoDTO datos) {
		revisionKudoFachada.cerrarRevisionKudoConVeto(id, datos);
		return responderOk(CatalogoMensajes.RevisionKudoControlador.REVISION_CERRADA_CON_VETO);
	}

	@PatchMapping("/{id}/liberacion")
	public ResponseEntity<RespuestaOk<Void>> cerrarRevisionKudoConLiberacion(@PathVariable("id") UUID id,
			@RequestBody RevisionKudoDTO datos) {
		revisionKudoFachada.cerrarRevisionKudoConLiberacion(id, datos);
		return responderOk(CatalogoMensajes.RevisionKudoControlador.REVISION_CERRADA_CON_LIBERACION);
	}

	@GetMapping
	public ResponseEntity<RespuestaOk<RevisionKudoDTO>> consultarRevisionesKudo(@ModelAttribute RevisionKudoDTO filtro) {
		var revisiones = revisionKudoFachada.consultarRevisionesKudo(filtro);
		return ResponseEntity.ok(RespuestaOk.crear(revisiones));
	}
}
