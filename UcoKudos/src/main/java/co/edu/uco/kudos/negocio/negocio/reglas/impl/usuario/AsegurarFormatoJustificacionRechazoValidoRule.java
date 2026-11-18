package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoJustificacionRechazoValidoRule implements Rule<String> {

	private static final Rule<String> INSTANCIA = new AsegurarFormatoJustificacionRechazoValidoRule();

	private static final int LONGITUD_MINIMA_JUSTIFICACION = 10;
	private static final int LONGITUD_MAXIMA_JUSTIFICACION = 200;

	private AsegurarFormatoJustificacionRechazoValidoRule() {

	}

	public static Rule<String> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(String... datos) {
		var justificacion = datos[0];

		ValidadorAtributos.asegurarTexto(justificacion, LONGITUD_MINIMA_JUSTIFICACION,
				LONGITUD_MAXIMA_JUSTIFICACION, CatalogoMensajes.Reglas.JUSTIFICACION_RECHAZO_NO_VALIDA);
	}
}
