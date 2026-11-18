package co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarCategoriaInactivaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarCategoriaInactivaRule();

	private AsegurarCategoriaInactivaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var id = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var categoria = daoFactory.obtenerCategoriaDAO().consultarPorId(id);
		ValidadorNegocio.asegurarCondicion(!categoria.isActiva(), CatalogoMensajes.Reglas.CATEGORIA_YA_ACTIVA);
	}
}
