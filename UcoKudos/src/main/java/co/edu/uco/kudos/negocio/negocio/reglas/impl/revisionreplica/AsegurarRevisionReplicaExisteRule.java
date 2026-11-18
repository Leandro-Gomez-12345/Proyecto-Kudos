package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarRevisionReplicaExisteRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarRevisionReplicaExisteRule();

	private AsegurarRevisionReplicaExisteRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		var entidad = daoFactory.obtenerRevisionReplicaDAO().consultarPorId(id);
		ValidadorNegocio.asegurarQueExiste(entidad.getId(), CatalogoMensajes.Reglas.REVISION_NO_EXISTE);
	}
}
