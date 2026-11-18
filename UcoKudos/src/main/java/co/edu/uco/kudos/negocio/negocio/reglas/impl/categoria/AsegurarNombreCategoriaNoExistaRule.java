package co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CategoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarNombreCategoriaNoExistaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarNombreCategoriaNoExistaRule();

	private AsegurarNombreCategoriaNoExistaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var categoria = (CategoriaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var filtro = new CategoriaEntidad();
		filtro.setNombre(categoria.getNombre());
		var coincidencias = daoFactory.obtenerCategoriaDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarQueNoExisteOtro(coincidencias.stream().map(CategoriaEntidad::getId).toList(),
				categoria.getId(), CatalogoMensajes.Reglas.CATEGORIA_NOMBRE_REPETIDO);
	}
}
