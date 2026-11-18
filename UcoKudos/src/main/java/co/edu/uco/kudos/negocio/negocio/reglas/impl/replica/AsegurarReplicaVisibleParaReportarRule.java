package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarReplicaVisibleParaReportarRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarReplicaVisibleParaReportarRule();

	private AsegurarReplicaVisibleParaReportarRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var replica = (ReplicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var nombreEstado = ConsultorCatalogos.nombreEstadoContenido(daoFactory, replica.getEstadoContenido().getId());
		if (ConsultorCatalogos.coincide(nombreEstado, NombresCatalogo.EstadoContenido.VISIBLE)) {
			replica.setEstadoContenido(ConsultorCatalogos.obtenerEstadoContenido(daoFactory,
					NombresCatalogo.EstadoContenido.REPORTADO));
		}
	}
}
