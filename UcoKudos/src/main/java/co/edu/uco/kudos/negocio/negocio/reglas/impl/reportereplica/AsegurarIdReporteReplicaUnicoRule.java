package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReporteReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdReporteReplicaUnicoRule implements Rule<ReporteReplicaEntidad> {

	private static final Rule<ReporteReplicaEntidad> INSTANCIA = new AsegurarIdReporteReplicaUnicoRule();

	private AsegurarIdReporteReplicaUnicoRule() {

	}

	public static Rule<ReporteReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReporteReplicaEntidad reporteReplica, DAOFactory daoFactory) {
		do {
			reporteReplica.setId(UtilUUID.generar());
		} while (existeConId(reporteReplica.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerReporteReplicaDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
