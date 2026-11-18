package co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarCategoriaInactivaRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarCategoriaInactivaRule();

	private AsegurarCategoriaInactivaRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		var categoria = daoFactory.obtenerCategoriaDAO().consultarPorId(id);
		ValidadorNegocio.asegurarCondicion(!categoria.isActiva(), CatalogoMensajes.Reglas.CATEGORIA_YA_ACTIVA);
	}
}
