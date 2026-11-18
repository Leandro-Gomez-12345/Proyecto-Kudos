package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.RevisionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdRevisionReplicaUnicoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarIdRevisionReplicaUnicoRule();

	private AsegurarIdRevisionReplicaUnicoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var revisionReplica = (RevisionReplicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		do {
			revisionReplica.setId(UtilUUID.generar());
		} while (existeConId(revisionReplica.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerRevisionReplicaDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
