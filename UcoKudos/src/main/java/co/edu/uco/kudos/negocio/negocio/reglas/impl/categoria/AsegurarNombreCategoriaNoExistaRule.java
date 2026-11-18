package co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CategoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarNombreCategoriaNoExistaRule implements Rule<CategoriaEntidad> {

	private static final Rule<CategoriaEntidad> INSTANCIA = new AsegurarNombreCategoriaNoExistaRule();

	private AsegurarNombreCategoriaNoExistaRule() {

	}

	public static Rule<CategoriaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CategoriaEntidad categoria, DAOFactory daoFactory) {
		var filtro = new CategoriaEntidad();
		filtro.setNombre(categoria.getNombre());
		var coincidencias = daoFactory.obtenerCategoriaDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarQueNoExisteOtro(coincidencias.stream().map(CategoriaEntidad::getId).toList(),
				categoria.getId(), CatalogoMensajes.Reglas.CATEGORIA_NOMBRE_REPETIDO);
	}
}
