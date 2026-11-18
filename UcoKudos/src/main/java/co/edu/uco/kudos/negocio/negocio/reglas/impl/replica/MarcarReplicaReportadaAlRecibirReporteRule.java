package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;

public final class MarcarReplicaReportadaAlRecibirReporteRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new MarcarReplicaReportadaAlRecibirReporteRule();

	private MarcarReplicaReportadaAlRecibirReporteRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		var replica = daoFactory.obtenerReplicaDAO().consultarPorId(id);
		AsegurarReplicaVisibleParaReportarRule.obtenerInstancia().ejecutar(replica, daoFactory);
		daoFactory.obtenerReplicaDAO().actualizar(id, replica);
	}
}
