package co.edu.uco.kudos.negocio.negocio.reglas.impl.comun;

import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Pattern;

import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilNumero;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ValidadorAtributos {

	private ValidadorAtributos() {

	}

	public static void asegurarTexto(String valor, int longitudMinima, int longitudMaxima, String mensajeUsuario) {
		var longitudValida = UtilTexto.longitudEsValida(valor, longitudMinima, longitudMaxima);
		ValidadorNegocio.asegurarCondicion(longitudValida, mensajeUsuario);
	}

	public static void asegurarTextoSinLimite(String valor, int longitudMinima, String mensajeUsuario) {
		asegurarTexto(valor, longitudMinima, UtilTexto.SIN_LIMITE, mensajeUsuario);
	}

	public static void asegurarFormato(String valor, Pattern formato, String mensajeUsuario) {
		ValidadorNegocio.asegurarCondicion(UtilTexto.cumpleFormato(valor, formato), mensajeUsuario);
	}

	public static void asegurarReferencia(UUID id, String mensajeUsuario) {
		ValidadorNegocio.asegurarCondicion(!UtilUUID.esValorDefecto(id), mensajeUsuario);
	}

	public static void asegurarEnteroEnRango(int valor, int minimo, int maximo, String mensajeUsuario) {
		ValidadorNegocio.asegurarCondicion(UtilNumero.estaEnIntervaloCerrado(valor, minimo, maximo), mensajeUsuario);
	}

	public static void asegurarFechaEnRango(LocalDate fecha, LocalDate minima, LocalDate maxima,
			String mensajeUsuario) {
		ValidadorNegocio.asegurarCondicion(UtilFecha.estaEnRango(fecha, minima, maxima), mensajeUsuario);
	}
}
