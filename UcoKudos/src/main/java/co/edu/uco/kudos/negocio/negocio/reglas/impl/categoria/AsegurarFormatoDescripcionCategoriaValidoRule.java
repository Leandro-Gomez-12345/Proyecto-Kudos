package co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria;

import co.edu.uco.kudos.entidad.CategoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.FormatosAtributos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoDescripcionCategoriaValidoRule implements Rule<CategoriaEntidad> {

	private static final Rule<CategoriaEntidad> INSTANCIA = new AsegurarFormatoDescripcionCategoriaValidoRule();

	private static final int LONGITUD_MINIMA_DESCRIPCION = 10;
	private static final int LONGITUD_MAXIMA_DESCRIPCION = 300;

	private AsegurarFormatoDescripcionCategoriaValidoRule() {

	}

	public static Rule<CategoriaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CategoriaEntidad... datos) {
		var categoria = datos[0];

		ValidadorAtributos.asegurarTexto(categoria.getDescripcion(), LONGITUD_MINIMA_DESCRIPCION,
				LONGITUD_MAXIMA_DESCRIPCION, CatalogoMensajes.Reglas.CATEGORIA_DESCRIPCION_NO_VALIDA);
		ValidadorAtributos.asegurarFormato(categoria.getDescripcion(), FormatosAtributos.DESCRIPCION_CATEGORIA,
				CatalogoMensajes.Reglas.CATEGORIA_DESCRIPCION_NO_VALIDA);
	}
}
