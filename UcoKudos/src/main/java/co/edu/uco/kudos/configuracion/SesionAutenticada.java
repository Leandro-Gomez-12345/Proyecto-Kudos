package co.edu.uco.kudos.configuracion;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import jakarta.servlet.http.HttpServletRequest;

public final class SesionAutenticada {

	private static final String ATRIBUTO_CORREO = SesionAutenticada.class.getName() + ".correo";

	private SesionAutenticada() {

	}

	public static void abrir(HttpServletRequest peticion, String correo) {
		cerrar(peticion);
		peticion.getSession(true).setAttribute(ATRIBUTO_CORREO, correo);
	}

	public static void cerrar(HttpServletRequest peticion) {
		var sesion = peticion.getSession(false);
		if (sesion != null) {
			sesion.invalidate();
		}
	}

	public static String obtenerCorreo(HttpServletRequest peticion) {
		var sesion = peticion.getSession(false);
		if (sesion != null && sesion.getAttribute(ATRIBUTO_CORREO) instanceof String correo) {
			return correo;
		}
		return UtilTexto.VACIA;
	}

	public static String obtenerCorreo() {
		if (RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes atributos) {
			return obtenerCorreo(atributos.getRequest());
		}
		return UtilTexto.VACIA;
	}
}
