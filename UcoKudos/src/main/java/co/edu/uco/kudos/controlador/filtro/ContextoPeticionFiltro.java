package co.edu.uco.kudos.controlador.filtro;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import co.edu.uco.kudos.configuracion.SesionAutenticada;
import co.edu.uco.kudos.negocio.fachada.AutenticacionFachada;
import co.edu.uco.kudos.negocio.fachada.impl.AutenticacionFachadaImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
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
	private static final String PREFIJO_API = "/api/";
	private static final String RUTA_INGRESO = "/api/v1/autenticacion/ingreso";
	private static final String RUTA_SALIDA = "/api/v1/autenticacion/salir";
	private static final String CUERPO_SESION_NO_INICIADA = "{\"mensajes\":[\"%s\"],\"datos\":[],\"estado\":\"ERROR\"}"
			.formatted(CatalogoMensajes.Seguridad.SESION_NO_INICIADA);

	private final AutenticacionFachada autenticacionFachada = new AutenticacionFachadaImpl();

	@Override
	protected void doFilterInternal(HttpServletRequest peticion, HttpServletResponse respuesta, FilterChain cadena)
			throws ServletException, IOException {
		var correo = SesionAutenticada.obtenerCorreo(peticion);

		if (requiereSesion(peticion) && UtilTexto.esVacia(correo)) {
			responderSesionNoIniciada(respuesta);
			return;
		}

		try {
			ContextoLog.iniciarSolicitud();
			var idEjecutor = obtenerIdEjecutor(peticion, correo);
			ContextoEjecucion.establecer(idEjecutor);
			ContextoLog.establecerIdEjecutor(idEjecutor);
			cadena.doFilter(peticion, respuesta);
		} finally {
			ContextoEjecucion.limpiar();
			ContextoLog.limpiar();
		}
	}

	private static boolean requiereSesion(HttpServletRequest peticion) {
		var ruta = peticion.getRequestURI();
		return ruta.startsWith(PREFIJO_API) && !RUTA_INGRESO.equals(ruta) && !RUTA_SALIDA.equals(ruta);
	}

	private static void responderSesionNoIniciada(HttpServletResponse respuesta) throws IOException {
		respuesta.setStatus(HttpStatus.UNAUTHORIZED.value());
		respuesta.setContentType(MediaType.APPLICATION_JSON_VALUE);
		respuesta.setCharacterEncoding(StandardCharsets.UTF_8.name());
		respuesta.getWriter().write(CUERPO_SESION_NO_INICIADA);
	}

	private UUID obtenerIdEjecutor(HttpServletRequest peticion, String correo) {
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
