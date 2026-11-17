package co.edu.uco.kudos.transversal.utilitarios;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public final class UtilTexto {

	public static final String VACIA = "";
	public static final int SIN_LIMITE = Integer.MAX_VALUE;

	private static final String ESPACIO = " ";
	private static final String ARROBA = "@";
	private static final String MASCARA = "***";
	private static final String CARACTER_ENMASCARADO = "*";
	private static final int DIGITOS_VISIBLES_CELULAR = 4;

	private static final Pattern ESPACIOS_REPETIDOS = Pattern.compile("\\s+");
	private static final Pattern MARCAS_DIACRITICAS_SALVO_ENIE = Pattern.compile("(?<![nN])\\p{M}+");

	private UtilTexto() {

	}

	public static String obtenerValorDefecto(String valor, String valorDefecto) {
		return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, valorDefecto);
	}

	public static String obtenerValorDefecto(String valor) {
		return obtenerValorDefecto(valor, VACIA);
	}

	public static String quitarEspaciosEnBlanco(String valor) {
		return obtenerValorDefecto(valor).trim();
	}

	public static String concatenar(String... textos) {
		var resultado = new StringBuilder();

		if (!UtilObjeto.esNulo(textos)) {
			for (var texto : textos) {
				resultado.append(obtenerValorDefecto(texto));
			}
		}
		return resultado.toString();
	}

	public static boolean esVacia(String valor) {
		return quitarEspaciosEnBlanco(valor).isEmpty();
	}

	public static int obtenerLongitud(String valor) {
		return quitarEspaciosEnBlanco(valor).length();
	}

	public static int obtenerLongitudConservandoEspacios(String valor) {
		return obtenerValorDefecto(valor).length();
	}

	public static boolean longitudEsValida(String valor, int longitudMinima, int longitudMaxima) {
		return UtilNumero.estaEnIntervaloCerrado(obtenerLongitud(valor), longitudMinima, longitudMaxima);
	}

	public static boolean longitudEsValidaConservandoEspacios(String valor, int longitudMinima,
			int longitudMaxima) {
		return UtilNumero.estaEnIntervaloCerrado(obtenerLongitudConservandoEspacios(valor), longitudMinima,
				longitudMaxima);
	}

	public static boolean cumpleFormato(String valor, Pattern formato) {
		return formato.matcher(quitarEspaciosEnBlanco(valor)).matches();
	}

	public static String normalizarParaComparar(String valor) {
		var enMinuscula = obtenerValorDefecto(valor).toLowerCase(Locale.ROOT);
		var descompuesto = Normalizer.normalize(enMinuscula, Normalizer.Form.NFD);
		var sinTildes = MARCAS_DIACRITICAS_SALVO_ENIE.matcher(descompuesto).replaceAll(VACIA);
		var recompuesto = Normalizer.normalize(sinTildes, Normalizer.Form.NFC);
		return ESPACIOS_REPETIDOS.matcher(recompuesto).replaceAll(ESPACIO).trim();
	}

	public static String enmascararCorreo(String correo) {
		var correoSinEspacios = quitarEspaciosEnBlanco(correo);
		var posicionArroba = correoSinEspacios.indexOf(ARROBA);

		if (posicionArroba < 1) {
			return MASCARA;
		}
		return correoSinEspacios.charAt(0) + MASCARA + correoSinEspacios.substring(posicionArroba);
	}

	public static String enmascararCelular(String celular) {
		var celularSinEspacios = quitarEspaciosEnBlanco(celular);
		var cantidadOcultos = Math.max(celularSinEspacios.length() - DIGITOS_VISIBLES_CELULAR, 0);

		if (cantidadOcultos == 0) {
			return CARACTER_ENMASCARADO.repeat(celularSinEspacios.length());
		}
		return CARACTER_ENMASCARADO.repeat(cantidadOcultos) + celularSinEspacios.substring(cantidadOcultos);
	}
}
