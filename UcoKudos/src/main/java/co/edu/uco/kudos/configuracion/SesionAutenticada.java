package co.edu.uco.kudos.configuracion;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import co.edu.uco.kudos.transversal.utilitarios.UtilCorreoInstitucional;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class SesionAutenticada {

	private SesionAutenticada() {

	}

	public static String obtenerCorreo() {
		var autenticacion = SecurityContextHolder.getContext().getAuthentication();
		return esUsuarioDeGoogle(autenticacion) ? UtilCorreoInstitucional.normalizar(extraerCorreo(autenticacion))
				: UtilTexto.VACIA;
	}

	public static String obtenerNombre() {
		return extraerTexto(true);
	}

	public static String obtenerApellido() {
		return extraerTexto(false);
	}

	private static String extraerTexto(boolean nombre) {
		var autenticacion = SecurityContextHolder.getContext().getAuthentication();
		if (!esUsuarioDeGoogle(autenticacion)) {
			return UtilTexto.VACIA;
		}
		var usuario = (OidcUser) autenticacion.getPrincipal();
		return UtilTexto.quitarEspaciosEnBlanco(nombre ? usuario.getGivenName() : usuario.getFamilyName());
	}

	private static String extraerCorreo(Authentication autenticacion) {
		return ((OidcUser) autenticacion.getPrincipal()).getEmail();
	}

	private static boolean esUsuarioDeGoogle(Authentication autenticacion) {
		return autenticacion != null && autenticacion.isAuthenticated()
				&& autenticacion.getPrincipal() instanceof OidcUser;
	}
}
