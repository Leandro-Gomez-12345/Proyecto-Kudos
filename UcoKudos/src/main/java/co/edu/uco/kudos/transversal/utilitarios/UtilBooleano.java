package co.edu.uco.kudos.transversal.utilitarios;

public final class UtilBooleano {

	public static final boolean SI = true;
	public static final boolean NO = false;

	private UtilBooleano() {

	}

	public static boolean obtenerValorDefecto(Boolean valor, boolean valorDefecto) {
		return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, valorDefecto);
	}

	public static boolean esVerdadero(Boolean valor) {
		return esVerdadero(valor, NO);
	}

	public static boolean esVerdadero(Boolean valor, boolean valorDefecto) {
		return obtenerValorDefecto(valor, valorDefecto);
	}

	public static boolean esFalso(Boolean valor) {
		return !esVerdadero(valor);
	}

	public static boolean esFalso(Boolean valor, boolean valorDefecto) {
		return !esVerdadero(valor, valorDefecto);
	}
}
