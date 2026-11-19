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
import co.edu.uco.kudos.dto.CalificacionReplicaDTO;
import co.edu.uco.kudos.negocio.fachada.CalificacionReplicaFachada;
import co.edu.uco.kudos.negocio.fachada.impl.CalificacionReplicaFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/calificaciones-replica")
public class CalificacionReplicaControlador extends AbstractRestController {

	private final CalificacionReplicaFachada calificacionReplicaFachada = new CalificacionReplicaFachadaImpl();

	@PostMapping
	public ResponseEntity<RespuestaOk<CalificacionReplicaDTO>> calificarReplica(@RequestBody CalificacionReplicaDTO datos) {
		var calificacion = calificacionReplicaFachada.calificarReplica(datos);
		return responderCreado(CatalogoMensajes.CalificacionReplicaControlador.REPLICA_CALIFICADA, calificacion);
	}

	@PatchMapping("/{id}/estrellas")
	public ResponseEntity<RespuestaOk<CalificacionReplicaDTO>> cambiarCalificacionReplica(@PathVariable("id") UUID id,
			@RequestBody CalificacionReplicaDTO datos) {
		var calificacion = calificacionReplicaFachada.cambiarCalificacionReplica(id, datos);
		return responderOk(CatalogoMensajes.CalificacionReplicaControlador.CALIFICACION_CAMBIADA, calificacion);
	}

	@GetMapping
	public ResponseEntity<RespuestaOk<CalificacionReplicaDTO>> consultarCalificacionesReplica(@ModelAttribute CalificacionReplicaDTO filtro) {
		var calificaciones = calificacionReplicaFachada.consultarCalificacionesReplica(filtro);
		return ResponseEntity.ok(RespuestaOk.crear(calificaciones));
	}
}
