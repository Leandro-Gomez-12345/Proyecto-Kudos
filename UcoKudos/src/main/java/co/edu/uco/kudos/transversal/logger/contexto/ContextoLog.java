package co.edu.uco.kudos.transversal.logger.contexto;

import java.util.UUID;

import org.slf4j.MDC;

import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ContextoLog {

	public static final String CLAVE_CORRELATION_ID = "correlationId";
	public static final String CLAVE_ID_EJECUTOR = "idEjecutor";

	private ContextoLog() {

	}

	public static String iniciarSolicitud() {
		var correlationId = UtilUUID.generarComoTexto();
		establecerCorrelationId(correlationId);
		return correlationId;
	}

	public static void establecerCorrelationId(String correlationId) {
		if (UtilTexto.esVacia(correlationId)) {
			MDC.remove(CLAVE_CORRELATION_ID);
		} else {
			MDC.put(CLAVE_CORRELATION_ID, correlationId);
		}
	}

	public static String obtenerCorrelationId() {
		return UtilTexto.obtenerValorDefecto(MDC.get(CLAVE_CORRELATION_ID));
	}

	public static void establecerIdEjecutor(UUID idEjecutor) {
		if (UtilUUID.esValorDefecto(UtilUUID.obtenerValorDefecto(idEjecutor))) {
			MDC.remove(CLAVE_ID_EJECUTOR);
		} else {
			MDC.put(CLAVE_ID_EJECUTOR, idEjecutor.toString());
		}
	}

	public static void limpiar() {
		MDC.remove(CLAVE_CORRELATION_ID);
		MDC.remove(CLAVE_ID_EJECUTOR);
	}
}
