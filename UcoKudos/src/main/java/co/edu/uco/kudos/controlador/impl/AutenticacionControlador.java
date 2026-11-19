package co.edu.uco.kudos.controlador.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.kudos.configuracion.SesionAutenticada;
import co.edu.uco.kudos.controlador.AbstractRestController;
import co.edu.uco.kudos.controlador.respuesta.RespuestaOk;
import co.edu.uco.kudos.dto.SesionDTO;
import co.edu.uco.kudos.negocio.fachada.AutenticacionFachada;
import co.edu.uco.kudos.negocio.fachada.impl.AutenticacionFachadaImpl;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

@RestController
@RequestMapping("/api/v1/autenticacion")
public class AutenticacionControlador extends AbstractRestController {

	private final AutenticacionFachada autenticacionFachada = new AutenticacionFachadaImpl();

	@GetMapping
	public ResponseEntity<RespuestaOk<SesionDTO>> consultarSesion() {
		var correo = SesionAutenticada.obtenerCorreo();
		var usuario = autenticacionFachada.consultarUsuarioPorCorreo(correo);
		var sesion = new SesionDTO();
		sesion.setUsuario(usuario);
		sesion.setRegistrado(!UtilUUID.esValorDefecto(usuario.getId()));

		if (!sesion.isRegistrado()) {
			usuario.setCorreoInstitucional(correo);
			usuario.setNombre(SesionAutenticada.obtenerNombre());
			usuario.setApellido(SesionAutenticada.obtenerApellido());
		}
		return ResponseEntity.ok(RespuestaOk.crear(sesion));
	}
}
