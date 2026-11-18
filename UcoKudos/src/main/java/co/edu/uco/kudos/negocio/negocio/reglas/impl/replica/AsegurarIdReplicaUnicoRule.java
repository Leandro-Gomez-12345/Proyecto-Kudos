package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdReplicaUnicoRule implements Rule<ReplicaEntidad> {

	private static final Rule<ReplicaEntidad> INSTANCIA = new AsegurarIdReplicaUnicoRule();

	private AsegurarIdReplicaUnicoRule() {

	}

	public static Rule<ReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReplicaEntidad replica, DAOFactory daoFactory) {
		do {
			replica.setId(UtilUUID.generar());
		} while (existeConId(replica.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerReplicaDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
