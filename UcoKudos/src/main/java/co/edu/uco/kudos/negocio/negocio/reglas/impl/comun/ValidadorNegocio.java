package co.edu.uco.kudos.negocio.negocio.reglas.impl.comun;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.transversal.excepciones.UcoKudosNegocioExcepcion;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ValidadorNegocio {

	private static final String SUFIJO_REGLA = "Rule";

	private ValidadorNegocio() {

	}

	public static void asegurarCondicion(boolean cumple, String mensajeUsuario) {
		if (!cumple) {
			throw UcoKudosNegocioExcepcion.crear(mensajeUsuario, obtenerMensajeTecnico(mensajeUsuario));
		}
	}

	public static void asegurarQueExiste(UUID idEncontrado, String mensajeUsuario) {
		asegurarCondicion(!UtilUUID.esValorDefecto(idEncontrado), mensajeUsuario);
	}

	public static void asegurarQueNoExisteOtro(List<UUID> idsCoincidentes, UUID idPropio, String mensajeUsuario) {
		var existeOtro = idsCoincidentes.stream().anyMatch(id -> !id.equals(idPropio));
		asegurarCondicion(!existeOtro, mensajeUsuario);
	}

	private static String obtenerMensajeTecnico(String mensajeUsuario) {
		var nombreRegla = StackWalker.getInstance().walk(marcos -> marcos
				.map(marco -> marco.getClassName().substring(marco.getClassName().lastIndexOf('.') + 1))
				.filter(nombre -> nombre.endsWith(SUFIJO_REGLA))
				.findFirst());
		return nombreRegla.map(nombre -> UtilTexto.concatenar(nombre, ": ", mensajeUsuario)).orElse(mensajeUsuario);
	}
}
