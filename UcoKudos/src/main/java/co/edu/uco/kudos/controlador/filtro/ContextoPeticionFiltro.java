package co.edu.uco.kudos.controlador.filtro;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import co.edu.uco.kudos.configuracion.SesionAutenticada;
import co.edu.uco.kudos.negocio.fachada.AutenticacionFachada;
import co.edu.uco.kudos.negocio.fachada.impl.AutenticacionFachadaImpl;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.logger.contexto.ContextoLog;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public final class ContextoPeticionFiltro extends OncePerRequestFilter {

	private static final String ATRIBUTO_ID_EJECUTOR = ContextoPeticionFiltro.class.getName() + ".idEjecutor";

	private final AutenticacionFachada autenticacionFachada = new AutenticacionFachadaImpl();

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

	private UUID obtenerIdEjecutor(HttpServletRequest peticion) {
		var correo = SesionAutenticada.obtenerCorreo();

		if (UtilTexto.esVacia(correo)) {
			return UtilUUID.obtenerUUIDDefecto();
		}

		var sesion = peticion.getSession(false);
		if (sesion != null && sesion.getAttribute(ATRIBUTO_ID_EJECUTOR) instanceof UUID idGuardado) {
			return idGuardado;
		}

		var idEjecutor = autenticacionFachada.consultarUsuarioPorCorreo(correo).getId();
		if (sesion != null && !UtilUUID.esValorDefecto(idEjecutor)) {
			sesion.setAttribute(ATRIBUTO_ID_EJECUTOR, idEjecutor);
		}
		return idEjecutor;
	}
}
