package co.edu.uco.kudos.controlador.filtro;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.logger.UcoKudosLogger;
import co.edu.uco.kudos.transversal.logger.contexto.ContextoLog;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public final class ContextoPeticionFiltro extends OncePerRequestFilter {

	private static final UcoKudosLogger LOGGER = UcoKudosLogger.obtener(ContextoPeticionFiltro.class);
	private static final String CABECERA_ID_EJECUTOR = "X-Id-Ejecutor";

	@Override
	protected void doFilterInternal(HttpServletRequest peticion, HttpServletResponse respuesta, FilterChain cadena)
			throws ServletException, IOException {
		try {
			ContextoLog.iniciarSolicitud();
			var idEjecutor = obtenerIdEjecutor(peticion);
			ContextoEjecucion.establecer(idEjecutor);
			ContextoLog.establecerIdEjecutor(idEjecutor);
			cadena.doFilter(peticion, respuesta);
		} finally {
			ContextoEjecucion.limpiar();
			ContextoLog.limpiar();
		}
	}

	private static UUID obtenerIdEjecutor(HttpServletRequest peticion) {
		var valor = UtilTexto.quitarEspaciosEnBlanco(peticion.getHeader(CABECERA_ID_EJECUTOR));

		if (UtilTexto.esVacia(valor)) {
			return UtilUUID.obtenerUUIDDefecto();
		}

		try {
			return UtilUUID.convertirAUUID(valor);
		} catch (IllegalArgumentException excepcion) {
			LOGGER.advertencia("La cabecera {} no contiene un identificador válido", CABECERA_ID_EJECUTOR);
			return UtilUUID.obtenerUUIDDefecto();
		}
	}
}
