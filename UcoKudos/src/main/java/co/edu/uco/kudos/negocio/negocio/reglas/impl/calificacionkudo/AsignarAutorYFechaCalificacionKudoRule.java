package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo;

import co.edu.uco.kudos.entidad.CalificacionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsignarAutorYFechaCalificacionKudoRule implements Rule<CalificacionKudoEntidad> {

	private static final Rule<CalificacionKudoEntidad> INSTANCIA = new AsignarAutorYFechaCalificacionKudoRule();

	private AsignarAutorYFechaCalificacionKudoRule() {

	}

	public static Rule<CalificacionKudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CalificacionKudoEntidad... datos) {
		var calificacion = datos[0];

		calificacion.getUsuario().setId(ContextoEjecucion.obtenerIdEjecutor());
		calificacion.setFechaCalificacion(UtilFechaHora.obtenerFechaHoraActual());
	}
}
