package co.edu.uco.kudos.negocio.negocio.reglas.impl.auditoria;

import co.edu.uco.kudos.entidad.AuditoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarConsultaSoloActividadPropiaRule implements Rule<AuditoriaEntidad> {

	private static final Rule<AuditoriaEntidad> INSTANCIA = new AsegurarConsultaSoloActividadPropiaRule();

	private AsegurarConsultaSoloActividadPropiaRule() {

	}

	public static Rule<AuditoriaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(AuditoriaEntidad... datos) {
		var filtro = datos[0];

		filtro.getUsuario().setId(ContextoEjecucion.obtenerIdEjecutor());
	}
}
