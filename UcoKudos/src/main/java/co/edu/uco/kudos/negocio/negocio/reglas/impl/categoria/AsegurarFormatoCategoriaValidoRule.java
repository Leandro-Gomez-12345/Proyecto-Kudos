package co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
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
	public void ejecutar(CategoriaEntidad categoria, DAOFactory daoFactory) {
		AsegurarFormatoNombreCategoriaValidoRule.obtenerInstancia().ejecutar(categoria, daoFactory);
		AsegurarFormatoDescripcionCategoriaValidoRule.obtenerInstancia().ejecutar(categoria, daoFactory);
	}
}
