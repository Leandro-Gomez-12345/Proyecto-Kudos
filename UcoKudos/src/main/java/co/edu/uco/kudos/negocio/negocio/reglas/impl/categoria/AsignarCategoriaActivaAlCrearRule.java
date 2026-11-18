package co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CategoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilBooleano;

public final class AsignarCategoriaActivaAlCrearRule implements Rule<CategoriaEntidad> {

	private static final Rule<CategoriaEntidad> INSTANCIA = new AsignarCategoriaActivaAlCrearRule();

	private AsignarCategoriaActivaAlCrearRule() {

	}

	public static Rule<CategoriaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CategoriaEntidad categoria, DAOFactory daoFactory) {
		categoria.setActiva(UtilBooleano.SI);
	}
}
