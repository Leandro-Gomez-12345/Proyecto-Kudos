package co.edu.uco.kudos.transversal.utilitarios;

public final class UtilNumero {

	public static final int CERO = 0;

	private UtilNumero() {

	}

	public static int obtenerValorDefecto(Integer valor, int valorDefecto) {
		return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, valorDefecto);
	}

	public static int obtenerValorDefecto(Integer valor) {
		return obtenerValorDefecto(valor, CERO);
	}

	public static boolean estaEnIntervaloCerrado(int valor, int limiteInferior, int limiteSuperior) {
		return valor >= limiteInferior && valor <= limiteSuperior;
	}
}
