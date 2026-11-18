package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class PublicarReplicaAlConfirmarRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new PublicarReplicaAlConfirmarRule();

	private PublicarReplicaAlConfirmarRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var replica = (ReplicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		replica.setEstadoContenido(ConsultorCatalogos.obtenerEstadoContenido(daoFactory,
				NombresCatalogo.EstadoContenido.VISIBLE));
	}
}
