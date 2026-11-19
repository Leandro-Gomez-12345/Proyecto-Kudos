package co.edu.uco.kudos.controlador.impl;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.kudos.controlador.AbstractRestController;
import co.edu.uco.kudos.controlador.respuesta.RespuestaOk;
import co.edu.uco.kudos.dto.KudoDTO;
import co.edu.uco.kudos.negocio.fachada.KudoFachada;
import co.edu.uco.kudos.negocio.fachada.impl.KudoFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/kudos")
public class KudoControlador extends AbstractRestController {

	private final KudoFachada kudoFachada = new KudoFachadaImpl();

	@PostMapping
	public ResponseEntity<RespuestaOk<KudoDTO>> crearKudo(@RequestBody KudoDTO datos) {
		var kudo = kudoFachada.crearKudo(datos);
		return responderCreado(CatalogoMensajes.KudoControlador.KUDO_CREADO, kudo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RespuestaOk<KudoDTO>> consultarKudo(@PathVariable("id") UUID id) {
		var kudo = kudoFachada.consultarKudo(id);
		return ResponseEntity.ok(RespuestaOk.crear(kudo));
	}

	@GetMapping
	public ResponseEntity<RespuestaOk<KudoDTO>> consultarKudos(@ModelAttribute KudoDTO filtro) {
		var kudos = kudoFachada.consultarKudos(filtro);
		return ResponseEntity.ok(RespuestaOk.crear(kudos));
	}
}
