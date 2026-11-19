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
import co.edu.uco.kudos.dto.CategoriaDTO;
import co.edu.uco.kudos.negocio.fachada.CategoriaFachada;
import co.edu.uco.kudos.negocio.fachada.impl.CategoriaFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaControlador extends AbstractRestController {

	private final CategoriaFachada categoriaFachada = new CategoriaFachadaImpl();

	@PostMapping
	public ResponseEntity<RespuestaOk<CategoriaDTO>> registrarCategoria(@RequestBody CategoriaDTO datos) {
		var categoria = categoriaFachada.registrarCategoria(datos);
		return responderCreado(CatalogoMensajes.CategoriaControlador.CATEGORIA_REGISTRADA, categoria);
	}

	@GetMapping
	public ResponseEntity<RespuestaOk<CategoriaDTO>> consultarCategorias(@ModelAttribute CategoriaDTO filtro) {
		var categorias = categoriaFachada.consultarCategorias(filtro);
		return ResponseEntity.ok(RespuestaOk.crear(categorias));
	}

	@PatchMapping("/{id}/nombre")
	public ResponseEntity<RespuestaOk<CategoriaDTO>> cambiarNombreCategoria(@PathVariable("id") UUID id,
			@RequestBody CategoriaDTO datos) {
		var categoria = categoriaFachada.cambiarNombreCategoria(id, datos);
		return responderOk(CatalogoMensajes.CategoriaControlador.NOMBRE_CAMBIADO, categoria);
	}

	@PatchMapping("/{id}/descripcion")
	public ResponseEntity<RespuestaOk<CategoriaDTO>> cambiarDescripcionCategoria(@PathVariable("id") UUID id,
			@RequestBody CategoriaDTO datos) {
		var categoria = categoriaFachada.cambiarDescripcionCategoria(id, datos);
		return responderOk(CatalogoMensajes.CategoriaControlador.DESCRIPCION_CAMBIADA, categoria);
	}

	@PatchMapping("/{id}/activacion")
	public ResponseEntity<RespuestaOk<Void>> activarCategoria(@PathVariable("id") UUID id) {
		categoriaFachada.activarCategoria(id);
		return responderOk(CatalogoMensajes.CategoriaControlador.CATEGORIA_ACTIVADA);
	}

	@PatchMapping("/{id}/desactivacion")
	public ResponseEntity<RespuestaOk<Void>> desactivarCategoria(@PathVariable("id") UUID id) {
		categoriaFachada.desactivarCategoria(id);
		return responderOk(CatalogoMensajes.CategoriaControlador.CATEGORIA_DESACTIVADA);
	}
}
