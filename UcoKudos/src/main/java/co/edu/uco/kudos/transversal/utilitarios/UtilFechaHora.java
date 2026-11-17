package co.edu.uco.kudos.transversal.utilitarios;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public final class UtilFechaHora {

	public static final LocalDateTime FECHA_HORA_DEFECTO = LocalDateTime.of(UtilFecha.FECHA_DEFECTO,
			LocalTime.MIDNIGHT);

	private UtilFechaHora() {

	}

	public static LocalDateTime obtenerValorDefecto(LocalDateTime fechaHora, LocalDateTime valorDefecto) {
		return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(fechaHora, valorDefecto);
	}

	public static LocalDateTime obtenerValorDefecto(LocalDateTime fechaHora) {
		return obtenerValorDefecto(fechaHora, FECHA_HORA_DEFECTO);
	}

	public static boolean esValorDefecto(LocalDateTime fechaHora) {
		return FECHA_HORA_DEFECTO.equals(obtenerValorDefecto(fechaHora));
	}

	public static LocalDateTime obtenerFechaHoraActual() {
		return LocalDateTime.now(UtilFecha.ZONA_HORARIA).truncatedTo(ChronoUnit.SECONDS);
	}

	public static boolean esAnterior(LocalDateTime fechaHora, LocalDateTime fechaHoraReferencia) {
		return obtenerValorDefecto(fechaHora).isBefore(obtenerValorDefecto(fechaHoraReferencia));
	}

	public static boolean esPosterior(LocalDateTime fechaHora, LocalDateTime fechaHoraReferencia) {
		return obtenerValorDefecto(fechaHora).isAfter(obtenerValorDefecto(fechaHoraReferencia));
	}
}
