package co.edu.uco.kudos.transversal.logger;

import java.util.EnumSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.enums.Capa;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class UcoKudosLogger {

	private static final Set<Capa> CAPAS_CON_FALLAS_ESPERADAS = EnumSet.of(Capa.NEGOCIO, Capa.DOMINIO, Capa.ENTIDAD,
			Capa.DTO);
	private static final Pattern DATO_ENTRE_COMILLAS = Pattern.compile("'[^']*'");
	private static final String DATO_ENMASCARADO = "'***'";

	private final Logger logger;

	private UcoKudosLogger(Class<?> clase) {
		this.logger = LoggerFactory.getLogger(clase);
	}

	public static UcoKudosLogger obtener(Class<?> clase) {
		return new UcoKudosLogger(clase);
	}

	public void debug(String mensaje, Object... argumentos) {
		logger.debug(mensaje, argumentos);
	}

	public void info(String mensaje, Object... argumentos) {
		logger.info(mensaje, argumentos);
	}

	public void advertencia(String mensaje, Object... argumentos) {
		logger.warn(mensaje, argumentos);
	}

	public void error(String mensaje, Object... argumentos) {
		logger.error(mensaje, argumentos);
	}

	public void registrarExcepcion(Exception excepcion) {
		if (excepcion instanceof UcoKudosExcepcion excepcionPropia) {
			registrarExcepcionPropia(excepcionPropia);
		} else {
			logger.error("Falla no controlada: {}", enmascarar(excepcion.getMessage()), sanear(excepcion));
		}
	}

	private void registrarExcepcionPropia(UcoKudosExcepcion excepcion) {
		var mensajeTecnico = enmascarar(excepcion.getMensajeTecnico());

		if (CAPAS_CON_FALLAS_ESPERADAS.contains(excepcion.getCapa())) {
			logger.warn("Regla incumplida en la capa {}: {}", excepcion.getCapa(), mensajeTecnico);
		} else {
			logger.error("Falla en la capa {}: {}", excepcion.getCapa(), mensajeTecnico, sanear(excepcion));
		}
	}

	private static String enmascarar(String mensaje) {
		var texto = UtilTexto.obtenerValorDefecto(mensaje);
		return DATO_ENTRE_COMILLAS.matcher(texto).replaceAll(DATO_ENMASCARADO);
	}

	private static Throwable sanear(Throwable excepcion) {
		var nombreClase = excepcion.getClass().getName();
		var mensaje = UtilTexto.concatenar(nombreClase, ": ", enmascarar(excepcion.getMessage()));
		var saneada = new TrazaSaneada(mensaje);
		saneada.setStackTrace(excepcion.getStackTrace());

		var causa = excepcion.getCause();
		if (!UtilObjeto.esNulo(causa) && causa != excepcion) {
			saneada.initCause(sanear(causa));
		}
		return saneada;
	}

	private static final class TrazaSaneada extends Throwable {

		private static final long serialVersionUID = 6283104957162038471L;

		private TrazaSaneada(String mensaje) {
			super(mensaje);
		}

		@Override
		public String toString() {
			return getMessage();
		}
	}
}
