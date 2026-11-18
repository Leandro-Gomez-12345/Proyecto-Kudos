package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CalificacionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarEstrellasDistintasEnCalificacionReplicaRule implements Rule<CalificacionReplicaEntidad> {

	private static final Rule<CalificacionReplicaEntidad> INSTANCIA =
			new AsegurarEstrellasDistintasEnCalificacionReplicaRule();

	private AsegurarEstrellasDistintasEnCalificacionReplicaRule() {

	}

	public static Rule<CalificacionReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CalificacionReplicaEntidad calificacion, DAOFactory daoFactory) {
		var actual = daoFactory.obtenerCalificacionReplicaDAO().consultarPorId(calificacion.getId());
		ValidadorNegocio.asegurarCondicion(actual.getEstrellas() != calificacion.getEstrellas(),
				CatalogoMensajes.Reglas.CALIFICACION_IGUAL_A_LA_ACTUAL);
	}
}
