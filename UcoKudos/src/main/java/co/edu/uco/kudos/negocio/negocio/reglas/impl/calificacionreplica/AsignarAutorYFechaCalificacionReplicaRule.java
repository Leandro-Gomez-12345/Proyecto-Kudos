package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica;

import co.edu.uco.kudos.entidad.CalificacionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsignarAutorYFechaCalificacionReplicaRule implements Rule<CalificacionReplicaEntidad> {

	private static final Rule<CalificacionReplicaEntidad> INSTANCIA = new AsignarAutorYFechaCalificacionReplicaRule();

	private AsignarAutorYFechaCalificacionReplicaRule() {

	}

	public static Rule<CalificacionReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CalificacionReplicaEntidad... datos) {
		var calificacion = datos[0];

		calificacion.getUsuario().setId(ContextoEjecucion.obtenerIdEjecutor());
		calificacion.setFechaCalificacion(UtilFechaHora.obtenerFechaHoraActual());
	}
}
