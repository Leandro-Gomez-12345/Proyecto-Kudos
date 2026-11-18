package co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CategoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.FormatosAtributos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoNombreCategoriaValidoRule implements Rule<CategoriaEntidad> {

	private static final Rule<CategoriaEntidad> INSTANCIA = new AsegurarFormatoNombreCategoriaValidoRule();

	private static final int LONGITUD_MINIMA_NOMBRE = 3;
	private static final int LONGITUD_MAXIMA_NOMBRE = 50;

	private AsegurarFormatoNombreCategoriaValidoRule() {

	}

	public static Rule<CategoriaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CategoriaEntidad categoria, DAOFactory daoFactory) {
		ValidadorAtributos.asegurarTexto(categoria.getNombre(), LONGITUD_MINIMA_NOMBRE, LONGITUD_MAXIMA_NOMBRE,
				CatalogoMensajes.Reglas.CATEGORIA_NOMBRE_NO_VALIDO);
		ValidadorAtributos.asegurarFormato(categoria.getNombre(), FormatosAtributos.SOLO_LETRAS_Y_ESPACIOS,
				CatalogoMensajes.Reglas.CATEGORIA_NOMBRE_NO_VALIDO);
	}
}
