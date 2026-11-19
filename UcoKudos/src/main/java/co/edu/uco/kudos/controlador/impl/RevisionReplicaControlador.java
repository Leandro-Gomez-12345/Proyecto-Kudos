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
import co.edu.uco.kudos.dto.RevisionReplicaDTO;
import co.edu.uco.kudos.negocio.fachada.RevisionReplicaFachada;
import co.edu.uco.kudos.negocio.fachada.impl.RevisionReplicaFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/revisiones-replica")
public class RevisionReplicaControlador extends AbstractRestController {

	private final RevisionReplicaFachada revisionReplicaFachada = new RevisionReplicaFachadaImpl();

	@PostMapping
	public ResponseEntity<RespuestaOk<RevisionReplicaDTO>> iniciarRevisionReplica(@RequestBody RevisionReplicaDTO datos) {
		var revision = revisionReplicaFachada.iniciarRevisionReplica(datos);
		return responderCreado(CatalogoMensajes.RevisionReplicaControlador.REVISION_INICIADA, revision);
	}

	@PostMapping("/veto-directo")
	public ResponseEntity<RespuestaOk<RevisionReplicaDTO>> vetarReplicaDirectamente(@RequestBody RevisionReplicaDTO datos) {
		var revision = revisionReplicaFachada.vetarReplicaDirectamente(datos);
		return responderCreado(CatalogoMensajes.RevisionReplicaControlador.REPLICA_VETADA, revision);
	}

	@PatchMapping("/{id}/veto")
	public ResponseEntity<RespuestaOk<Void>> cerrarRevisionReplicaConVeto(@PathVariable("id") UUID id,
			@RequestBody RevisionReplicaDTO datos) {
		revisionReplicaFachada.cerrarRevisionReplicaConVeto(id, datos);
		return responderOk(CatalogoMensajes.RevisionReplicaControlador.REVISION_CERRADA_CON_VETO);
	}

	@PatchMapping("/{id}/liberacion")
	public ResponseEntity<RespuestaOk<Void>> cerrarRevisionReplicaConLiberacion(@PathVariable("id") UUID id,
			@RequestBody RevisionReplicaDTO datos) {
		revisionReplicaFachada.cerrarRevisionReplicaConLiberacion(id, datos);
		return responderOk(CatalogoMensajes.RevisionReplicaControlador.REVISION_CERRADA_CON_LIBERACION);
	}

	@GetMapping
	public ResponseEntity<RespuestaOk<RevisionReplicaDTO>> consultarRevisionesReplica(@ModelAttribute RevisionReplicaDTO filtro) {
		var revisiones = revisionReplicaFachada.consultarRevisionesReplica(filtro);
		return ResponseEntity.ok(RespuestaOk.crear(revisiones));
	}
}
