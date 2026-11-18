package co.edu.uco.kudos.controlador;

import java.util.EnumSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import co.edu.uco.kudos.controlador.respuesta.RespuestaError;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.enums.Capa;
import co.edu.uco.kudos.transversal.logger.UcoKudosLogger;

@RestControllerAdvice
public abstract class AbstractRestController {

	private static final UcoKudosLogger LOGGER = UcoKudosLogger.obtener(AbstractRestController.class);
	private static final Set<Capa> CAPAS_CON_REGLAS_INCUMPLIDAS = EnumSet.of(Capa.NEGOCIO, Capa.DOMINIO,
			Capa.ENTIDAD, Capa.DTO);

	@ExceptionHandler(UcoKudosExcepcion.class)
	public ResponseEntity<RespuestaError<String>> controlarUcoKudosExcepcion(UcoKudosExcepcion excepcion) {
		LOGGER.registrarExcepcion(excepcion);
		var estado = CAPAS_CON_REGLAS_INCUMPLIDAS.contains(excepcion.getCapa()) ? HttpStatus.BAD_REQUEST
				: HttpStatus.INTERNAL_SERVER_ERROR;
		return responder(estado, excepcion.getMensajeUsuario());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<RespuestaError<String>> controlarCuerpoNoLegible(HttpMessageNotReadableException excepcion) {
		LOGGER.advertencia("Cuerpo de la petición no legible: {}", excepcion.getClass().getSimpleName());
		return responder(HttpStatus.BAD_REQUEST, CatalogoMensajes.AbstractRestController.CUERPO_NO_VALIDO);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<RespuestaError<String>> controlarParametroNoValido(
			MethodArgumentTypeMismatchException excepcion) {
		LOGGER.advertencia("Parámetro {} con un valor que no tiene el tipo esperado", excepcion.getName());
		var mensajeUsuario = CatalogoMensajes.AbstractRestController.PARAMETRO_NO_VALIDO
				.formatted(excepcion.getName());
		return responder(HttpStatus.BAD_REQUEST, mensajeUsuario);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<RespuestaError<String>> controlarRecursoNoExiste(NoResourceFoundException excepcion) {
		LOGGER.advertencia("Recurso {} no existe (método {})", excepcion.getResourcePath(),
				excepcion.getHttpMethod());
		var mensajeUsuario = CatalogoMensajes.AbstractRestController.RECURSO_NO_EXISTE
				.formatted(excepcion.getResourcePath(), excepcion.getHttpMethod());
		return responder(HttpStatus.NOT_FOUND, mensajeUsuario);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<RespuestaError<String>> controlarMetodoNoPermitido(
			HttpRequestMethodNotSupportedException excepcion) {
		LOGGER.advertencia("Método {} no permitido para el recurso solicitado", excepcion.getMethod());
		var mensajeUsuario = CatalogoMensajes.AbstractRestController.METODO_NO_PERMITIDO
				.formatted(excepcion.getMethod());
		return responder(HttpStatus.METHOD_NOT_ALLOWED, mensajeUsuario);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<RespuestaError<String>> controlarContenidoNoSoportado(
			HttpMediaTypeNotSupportedException excepcion) {
		LOGGER.advertencia("Tipo de contenido no soportado: {}", excepcion.getContentType());
		return responder(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
				CatalogoMensajes.AbstractRestController.CONTENIDO_NO_SOPORTADO);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<RespuestaError<String>> controlarExcepcionNoControlada(Exception excepcion) {
		LOGGER.registrarExcepcion(excepcion);
		return responder(HttpStatus.INTERNAL_SERVER_ERROR,
				CatalogoMensajes.AbstractRestController.ERROR_NO_CONTROLADO);
	}

	private static ResponseEntity<RespuestaError<String>> responder(HttpStatus estado, String mensajeUsuario) {
		return ResponseEntity.status(estado).body(RespuestaError.crear(mensajeUsuario));
	}
}
