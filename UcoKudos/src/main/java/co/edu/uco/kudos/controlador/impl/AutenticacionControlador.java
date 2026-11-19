package co.edu.uco.kudos.controlador.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.kudos.configuracion.SesionAutenticada;
import co.edu.uco.kudos.controlador.AbstractRestController;
import co.edu.uco.kudos.controlador.peticion.IngresoPeticion;
import co.edu.uco.kudos.controlador.respuesta.RespuestaOk;
import co.edu.uco.kudos.dto.SesionDTO;
import co.edu.uco.kudos.dto.UsuarioDTO;
import co.edu.uco.kudos.negocio.fachada.AutenticacionFachada;
import co.edu.uco.kudos.negocio.fachada.impl.AutenticacionFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilCorreoInstitucional;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/autenticacion")
public class AutenticacionControlador extends AbstractRestController {

	private final AutenticacionFachada autenticacionFachada = new AutenticacionFachadaImpl();

	@PostMapping("/ingreso")
	public ResponseEntity<RespuestaOk<SesionDTO>> iniciarSesion(@RequestBody IngresoPeticion peticion,
			HttpServletRequest solicitud) {
		var correo = UtilCorreoInstitucional.normalizar(peticion.getCorreoInstitucional());
		var usuario = autenticacionFachada.iniciarSesion(correo);
		SesionAutenticada.abrir(solicitud, correo);
		return ResponseEntity.ok(RespuestaOk.crear(armarSesion(usuario, correo)));
	}

	@GetMapping
	public ResponseEntity<RespuestaOk<SesionDTO>> consultarSesion() {
		var correo = SesionAutenticada.obtenerCorreo();
		var usuario = autenticacionFachada.consultarUsuarioPorCorreo(correo);
		return ResponseEntity.ok(RespuestaOk.crear(armarSesion(usuario, correo)));
	}

	@PostMapping("/salir")
	public ResponseEntity<RespuestaOk<Void>> cerrarSesion(HttpServletRequest solicitud) {
		SesionAutenticada.cerrar(solicitud);
		return responderOk(CatalogoMensajes.AutenticacionControlador.SESION_CERRADA);
	}

	private static SesionDTO armarSesion(UsuarioDTO usuario, String correo) {
		var sesion = new SesionDTO();
		sesion.setUsuario(usuario);
		sesion.setRegistrado(!UtilUUID.esValorDefecto(usuario.getId()));

		if (!sesion.isRegistrado()) {
			usuario.setCorreoInstitucional(correo);
		}
		return sesion;
	}
}
