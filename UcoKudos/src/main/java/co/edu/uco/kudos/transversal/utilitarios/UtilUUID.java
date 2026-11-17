package co.edu.uco.kudos.transversal.utilitarios;

import java.time.Instant;
import java.util.UUID;

public final class UtilUUID {

	public static final UUID UUID_DEFECTO = new UUID(0L, 0L);
	public static final String UUID_DEFECTO_TEXTO = UUID_DEFECTO.toString();

	private UtilUUID() {

	}

	public static UUID generar() {
		return UUID.ofEpochMillis(Instant.now().toEpochMilli());
	}

	public static String generarComoTexto() {
		return generar().toString();
	}

	public static UUID obtenerUUIDDefecto() {
		return UUID_DEFECTO;
	}

	public static UUID obtenerValorDefecto(UUID valor) {
		return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, UUID_DEFECTO);
	}

	public static boolean esValorDefecto(UUID valor) {
		return UUID_DEFECTO.equals(obtenerValorDefecto(valor));
	}

	public static String obtenerValorDefectoComoTexto(String valor) {
		return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, UUID_DEFECTO_TEXTO);
	}

	public static UUID convertirAUUID(String uuidTexto) {
		return UUID.fromString(obtenerValorDefectoComoTexto(uuidTexto));
	}
}
