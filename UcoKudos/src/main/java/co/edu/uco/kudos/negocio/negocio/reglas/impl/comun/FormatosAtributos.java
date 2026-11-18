package co.edu.uco.kudos.negocio.negocio.reglas.impl.comun;

import java.time.LocalDate;
import java.util.regex.Pattern;

public final class FormatosAtributos {

	public static final Pattern SOLO_LETRAS_Y_ESPACIOS = Pattern.compile("^[\\p{L} ]+$");
	public static final Pattern NOMBRE_FACULTAD = Pattern.compile("^Facultad de [\\p{L} ]+$");
	public static final Pattern TEXTO_CON_PUNTUACION = Pattern.compile("^[\\p{L}0-9\\s\\p{P}]+$");
	public static final Pattern DESCRIPCION_CATEGORIA = Pattern.compile("^[\\p{L}0-9 .,;:¿?¡!()\\-]+$");
	public static final Pattern CORREO_INSTITUCIONAL = Pattern.compile("^[a-z0-9._-]+@uco\\.edu\\.co$");
	public static final Pattern CELULAR_COLOMBIANO = Pattern.compile("^3\\d{9}$");
	public static final Pattern CORREO_COMPLETO = Pattern.compile("[\\w.+-]{1,64}@[\\w.-]{1,255}\\.[\\w-]{2,63}");
	public static final Pattern CELULAR_COMPLETO = Pattern.compile("(?<!\\d)3\\d{9}(?!\\d)");
	public static final LocalDate FECHA_FUNDACION_UCO = LocalDate.of(1982, 1, 1);

	private FormatosAtributos() {

	}
}
