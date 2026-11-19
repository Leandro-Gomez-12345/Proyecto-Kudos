package co.edu.uco.kudos.negocio.negocio.reglas.impl.comun;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.transversal.excepciones.UcoKudosNegocioExcepcion;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ValidadorNegocio {

	private ValidadorNegocio() {

	}

	public static void asegurarCondicion(boolean cumple, String mensajeUsuario) {
		if (!cumple) {
			throw UcoKudosNegocioExcepcion.crear(mensajeUsuario);
		}
	}

	public static void asegurarQueExiste(UUID idEncontrado, String mensajeUsuario) {
		asegurarCondicion(!UtilUUID.esValorDefecto(idEncontrado), mensajeUsuario);
	}

	public static void asegurarQueNoExisteOtro(List<UUID> idsCoincidentes, UUID idPropio, String mensajeUsuario) {
		var existeOtro = idsCoincidentes.stream().anyMatch(id -> !id.equals(idPropio));
		asegurarCondicion(!existeOtro, mensajeUsuario);
	}
}
