package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CalificacionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdCalificacionReplicaUnicoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarIdCalificacionReplicaUnicoRule();

	private AsegurarIdCalificacionReplicaUnicoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var calificacionReplica = (CalificacionReplicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		do {
			calificacionReplica.setId(UtilUUID.generar());
		} while (existeConId(calificacionReplica.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerCalificacionReplicaDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
