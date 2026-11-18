package co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria;

import co.edu.uco.kudos.entidad.CategoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;

public final class AsegurarFormatoCategoriaValidoRule implements Rule<CategoriaEntidad> {

	private static final Rule<CategoriaEntidad> INSTANCIA = new AsegurarFormatoCategoriaValidoRule();

	private AsegurarFormatoCategoriaValidoRule() {

	}

	public static Rule<CategoriaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CategoriaEntidad... datos) {
		var categoria = datos[0];

		AsegurarFormatoNombreCategoriaValidoRule.obtenerInstancia().ejecutar(categoria);
		AsegurarFormatoDescripcionCategoriaValidoRule.obtenerInstancia().ejecutar(categoria);
	}
}
