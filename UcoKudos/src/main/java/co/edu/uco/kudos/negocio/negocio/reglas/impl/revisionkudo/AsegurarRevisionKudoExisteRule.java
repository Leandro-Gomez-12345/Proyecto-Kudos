package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarRevisionKudoExisteRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarRevisionKudoExisteRule();

	private AsegurarRevisionKudoExisteRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		var entidad = daoFactory.obtenerRevisionKudoDAO().consultarPorId(id);
		ValidadorNegocio.asegurarQueExiste(entidad.getId(), CatalogoMensajes.Reglas.REVISION_NO_EXISTE);
	}
}
