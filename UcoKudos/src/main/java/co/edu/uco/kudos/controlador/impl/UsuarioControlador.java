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
import co.edu.uco.kudos.controlador.peticion.JustificacionPeticion;
import co.edu.uco.kudos.controlador.peticion.RegistroUsuarioPeticion;
import co.edu.uco.kudos.controlador.respuesta.RespuestaOk;
import co.edu.uco.kudos.dto.UsuarioDTO;
import co.edu.uco.kudos.negocio.fachada.UsuarioFachada;
import co.edu.uco.kudos.negocio.fachada.impl.UsuarioFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioControlador extends AbstractRestController {

	private final UsuarioFachada usuarioFachada = new UsuarioFachadaImpl();

	@PostMapping
	public ResponseEntity<RespuestaOk<UsuarioDTO>> registrarUsuario(@RequestBody RegistroUsuarioPeticion peticion) {
		var usuario = usuarioFachada.registrarUsuario(peticion.getUsuario(), peticion.getProgramas());
		return responderCreado(CatalogoMensajes.UsuarioControlador.USUARIO_REGISTRADO, usuario);
	}

	@PatchMapping("/{id}/registro")
	public ResponseEntity<RespuestaOk<Void>> presentarNuevamenteRegistroUsuario(@PathVariable("id") UUID id,
			@RequestBody RegistroUsuarioPeticion peticion) {
		usuarioFachada.presentarNuevamenteRegistroUsuario(id, peticion.getUsuario(), peticion.getProgramas());
		return responderOk(CatalogoMensajes.UsuarioControlador.REGISTRO_PRESENTADO_NUEVAMENTE);
	}

	@PostMapping("/rectores")
	public ResponseEntity<RespuestaOk<UsuarioDTO>> registrarRector(@RequestBody UsuarioDTO datos) {
		var rector = usuarioFachada.registrarRector(datos);
		return responderCreado(CatalogoMensajes.UsuarioControlador.RECTOR_REGISTRADO, rector);
	}

	@PostMapping("/administradores")
	public ResponseEntity<RespuestaOk<UsuarioDTO>> registrarAdministrador(@RequestBody UsuarioDTO datos) {
		var administrador = usuarioFachada.registrarAdministrador(datos);
		return responderCreado(CatalogoMensajes.UsuarioControlador.ADMINISTRADOR_REGISTRADO, administrador);
	}

	@PatchMapping("/{id}/correo/confirmacion")
	public ResponseEntity<RespuestaOk<Void>> confirmarCorreoUsuario(@PathVariable("id") UUID id) {
		usuarioFachada.confirmarCorreoUsuario(id);
		return responderOk(CatalogoMensajes.UsuarioControlador.CORREO_CONFIRMADO);
	}

	@PatchMapping("/{id}/celular/confirmacion")
	public ResponseEntity<RespuestaOk<Void>> confirmarCelularUsuario(@PathVariable("id") UUID id) {
		usuarioFachada.confirmarCelularUsuario(id);
		return responderOk(CatalogoMensajes.UsuarioControlador.CELULAR_CONFIRMADO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RespuestaOk<UsuarioDTO>> consultarInformacionPersonalUsuario(
			@PathVariable("id") UUID id) {
		var usuario = usuarioFachada.consultarInformacionPersonalUsuario(id);
		return ResponseEntity.ok(RespuestaOk.crear(usuario));
	}

	@GetMapping
	public ResponseEntity<RespuestaOk<UsuarioDTO>> consultarUsuarios(@ModelAttribute UsuarioDTO filtro) {
		var usuarios = usuarioFachada.consultarUsuarios(filtro);
		return ResponseEntity.ok(RespuestaOk.crear(usuarios));
	}

	@GetMapping("/pendientes-aprobacion")
	public ResponseEntity<RespuestaOk<UsuarioDTO>> consultarUsuariosPendientesAprobacion(
			@ModelAttribute UsuarioDTO filtro) {
		var usuarios = usuarioFachada.consultarUsuariosPendientesAprobacion(filtro);
		return ResponseEntity.ok(RespuestaOk.crear(usuarios));
	}

	@PatchMapping("/{id}/nombre")
	public ResponseEntity<RespuestaOk<UsuarioDTO>> cambiarNombreUsuario(@PathVariable("id") UUID id,
			@RequestBody UsuarioDTO datos) {
		var usuario = usuarioFachada.cambiarNombreUsuario(id, datos);
		return responderOk(CatalogoMensajes.UsuarioControlador.NOMBRE_CAMBIADO, usuario);
	}

	@PatchMapping("/{id}/correo")
	public ResponseEntity<RespuestaOk<Void>> cambiarCorreoUsuario(@PathVariable("id") UUID id,
			@RequestBody UsuarioDTO datos) {
		usuarioFachada.cambiarCorreoUsuario(id, datos);
		return responderOk(CatalogoMensajes.UsuarioControlador.CORREO_CAMBIADO);
	}

	@PatchMapping("/{id}/celular")
	public ResponseEntity<RespuestaOk<Void>> cambiarCelularUsuario(@PathVariable("id") UUID id,
			@RequestBody UsuarioDTO datos) {
		usuarioFachada.cambiarCelularUsuario(id, datos);
		return responderOk(CatalogoMensajes.UsuarioControlador.CELULAR_CAMBIADO);
	}

	@PatchMapping("/{id}/suspension")
	public ResponseEntity<RespuestaOk<Void>> suspenderCuentaUsuario(@PathVariable("id") UUID id,
			@RequestBody JustificacionPeticion peticion) {
		usuarioFachada.suspenderCuentaUsuario(id, peticion.getJustificacion());
		return responderOk(CatalogoMensajes.UsuarioControlador.CUENTA_SUSPENDIDA);
	}

	@PatchMapping("/{id}/reactivacion")
	public ResponseEntity<RespuestaOk<Void>> reactivarCuentaUsuario(@PathVariable("id") UUID id) {
		usuarioFachada.reactivarCuentaUsuario(id);
		return responderOk(CatalogoMensajes.UsuarioControlador.CUENTA_REACTIVADA);
	}

	@PatchMapping("/{id}/desactivacion")
	public ResponseEntity<RespuestaOk<Void>> desactivarCuentaUsuario(@PathVariable("id") UUID id,
			@RequestBody JustificacionPeticion peticion) {
		usuarioFachada.desactivarCuentaUsuario(id, peticion.getJustificacion());
		return responderOk(CatalogoMensajes.UsuarioControlador.CUENTA_DESACTIVADA);
	}

	@PatchMapping("/{id}/rol")
	public ResponseEntity<RespuestaOk<Void>> cambiarRolUsuario(@PathVariable("id") UUID id,
			@RequestBody UsuarioDTO datos) {
		usuarioFachada.cambiarRolUsuario(id, datos);
		return responderOk(CatalogoMensajes.UsuarioControlador.ROL_CAMBIADO);
	}

	@PatchMapping("/{id}/aprobacion")
	public ResponseEntity<RespuestaOk<Void>> aprobarUsuario(@PathVariable("id") UUID id) {
		usuarioFachada.aprobarUsuario(id);
		return responderOk(CatalogoMensajes.UsuarioControlador.USUARIO_APROBADO);
	}

	@PatchMapping("/{id}/rechazo")
	public ResponseEntity<RespuestaOk<Void>> rechazarUsuario(@PathVariable("id") UUID id,
			@RequestBody JustificacionPeticion peticion) {
		usuarioFachada.rechazarUsuario(id, peticion.getJustificacion());
		return responderOk(CatalogoMensajes.UsuarioControlador.USUARIO_RECHAZADO);
	}
}
