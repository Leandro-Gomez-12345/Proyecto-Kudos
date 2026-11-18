package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarReplicaExisteRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarReplicaExisteRule();

	private AsegurarReplicaExisteRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		var entidad = daoFactory.obtenerReplicaDAO().consultarPorId(id);
		ValidadorNegocio.asegurarQueExiste(entidad.getId(), CatalogoMensajes.Reglas.REPLICA_NO_EXISTE);
	}
}
