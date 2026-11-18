package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;

public final class MarcarReplicaReportadaAlRecibirReporteRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new MarcarReplicaReportadaAlRecibirReporteRule();

	private MarcarReplicaReportadaAlRecibirReporteRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var id = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var replica = daoFactory.obtenerReplicaDAO().consultarPorId(id);
		AsegurarReplicaVisibleParaReportarRule.obtenerInstancia().ejecutar(replica, daoFactory);
		daoFactory.obtenerReplicaDAO().actualizar(id, replica);
	}
}
