package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CalificacionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class ActualizarFechaCalificacionKudoRule implements Rule<CalificacionKudoEntidad> {

	private static final Rule<CalificacionKudoEntidad> INSTANCIA = new ActualizarFechaCalificacionKudoRule();

	private ActualizarFechaCalificacionKudoRule() {

	}

	public static Rule<CalificacionKudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CalificacionKudoEntidad calificacion, DAOFactory daoFactory) {
		calificacion.setFechaCalificacion(UtilFechaHora.obtenerFechaHoraActual());
	}
}
