package co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarCategoriaActivaRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarCategoriaActivaRule();

	private AsegurarCategoriaActivaRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		var categoria = daoFactory.obtenerCategoriaDAO().consultarPorId(id);
		ValidadorNegocio.asegurarCondicion(!UtilUUID.esValorDefecto(categoria.getId()) && categoria.isActiva(),
				CatalogoMensajes.Reglas.CATEGORIA_INACTIVA);
	}
}
