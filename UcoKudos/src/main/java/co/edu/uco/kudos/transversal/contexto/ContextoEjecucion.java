package co.edu.uco.kudos.transversal.contexto;

import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ContextoEjecucion {

	private static final ThreadLocal<UUID> ID_EJECUTOR = new ThreadLocal<>();

	private ContextoEjecucion() {

	}

	public static void establecer(UUID idEjecutor) {
		ID_EJECUTOR.set(UtilUUID.obtenerValorDefecto(idEjecutor));
	}

	public static UUID obtenerIdEjecutor() {
		return UtilUUID.obtenerValorDefecto(ID_EJECUTOR.get());
	}

	public static void limpiar() {
		ID_EJECUTOR.remove();
	}
}
