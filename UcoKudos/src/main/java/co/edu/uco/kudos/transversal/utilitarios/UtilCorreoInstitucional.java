package co.edu.uco.kudos.transversal.utilitarios;

import java.util.Locale;

public final class UtilCorreoInstitucional {

	public static final String DOMINIO = "uco.edu.co";
	public static final String SUFIJO = "@" + DOMINIO;

	private static final char ARROBA = '@';

	private UtilCorreoInstitucional() {

	}

	public static String normalizar(String correo) {
		return UtilTexto.quitarEspaciosEnBlanco(correo).toLowerCase(Locale.ROOT);
	}

	public static boolean perteneceAlDominio(String correo) {
		var normalizado = normalizar(correo);
		var posicionArroba = normalizado.lastIndexOf(ARROBA);
		return posicionArroba > 0 && DOMINIO.equals(normalizado.substring(posicionArroba + 1));
	}
}
