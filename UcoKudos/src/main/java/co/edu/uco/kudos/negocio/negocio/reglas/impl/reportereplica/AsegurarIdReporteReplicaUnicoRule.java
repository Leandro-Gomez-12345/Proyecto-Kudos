package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReporteReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdReporteReplicaUnicoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarIdReporteReplicaUnicoRule();

	private AsegurarIdReporteReplicaUnicoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var reporteReplica = (ReporteReplicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		do {
			reporteReplica.setId(UtilUUID.generar());
		} while (existeConId(reporteReplica.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerReporteReplicaDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
