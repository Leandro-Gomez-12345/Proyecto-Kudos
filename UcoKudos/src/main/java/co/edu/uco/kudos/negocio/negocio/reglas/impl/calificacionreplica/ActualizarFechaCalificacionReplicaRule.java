package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CalificacionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class ActualizarFechaCalificacionReplicaRule implements Rule<CalificacionReplicaEntidad> {

	private static final Rule<CalificacionReplicaEntidad> INSTANCIA = new ActualizarFechaCalificacionReplicaRule();

	private ActualizarFechaCalificacionReplicaRule() {

	}

	public static Rule<CalificacionReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CalificacionReplicaEntidad calificacion, DAOFactory daoFactory) {
		calificacion.setFechaCalificacion(UtilFechaHora.obtenerFechaHoraActual());
	}
}
