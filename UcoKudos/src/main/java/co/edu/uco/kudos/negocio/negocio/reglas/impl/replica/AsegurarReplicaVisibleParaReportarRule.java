package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarReplicaVisibleParaReportarRule implements Rule<ReplicaEntidad> {

	private static final Rule<ReplicaEntidad> INSTANCIA = new AsegurarReplicaVisibleParaReportarRule();

	private AsegurarReplicaVisibleParaReportarRule() {

	}

	public static Rule<ReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReplicaEntidad replica, DAOFactory daoFactory) {
		var nombreEstado = ConsultorCatalogos.nombreEstadoContenido(daoFactory, replica.getEstadoContenido().getId());
		if (ConsultorCatalogos.coincide(nombreEstado, NombresCatalogo.EstadoContenido.VISIBLE)) {
			replica.setEstadoContenido(ConsultorCatalogos.obtenerEstadoContenido(daoFactory,
					NombresCatalogo.EstadoContenido.REPORTADO));
		}
	}
}
