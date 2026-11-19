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
import co.edu.uco.kudos.dto.ReplicaDTO;
import co.edu.uco.kudos.negocio.fachada.ReplicaFachada;
import co.edu.uco.kudos.negocio.fachada.impl.ReplicaFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/replicas")
public class ReplicaControlador extends AbstractRestController {

	private final ReplicaFachada replicaFachada = new ReplicaFachadaImpl();

	@PostMapping
	public ResponseEntity<RespuestaOk<ReplicaDTO>> replicarKudo(@RequestBody ReplicaDTO datos) {
		var replica = replicaFachada.replicarKudo(datos);
		return responderCreado(CatalogoMensajes.ReplicaControlador.KUDO_REPLICADO, replica);
	}

	@PostMapping("/respuestas")
	public ResponseEntity<RespuestaOk<ReplicaDTO>> responderReplica(@RequestBody ReplicaDTO datos) {
		var replica = replicaFachada.responderReplica(datos);
		return responderCreado(CatalogoMensajes.ReplicaControlador.REPLICA_RESPONDIDA, replica);
	}

	@GetMapping
	public ResponseEntity<RespuestaOk<ReplicaDTO>> consultarConversacionKudo(@ModelAttribute ReplicaDTO filtro) {
		var conversacion = replicaFachada.consultarConversacionKudo(filtro);
		return ResponseEntity.ok(RespuestaOk.crear(conversacion));
	}
}
