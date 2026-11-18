package co.edu.uco.kudos.negocio.negocio.reglas.impl.auditoria;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.AuditoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsignarFechaHoraAuditoriaRule implements Rule<AuditoriaEntidad> {

	private static final Rule<AuditoriaEntidad> INSTANCIA = new AsignarFechaHoraAuditoriaRule();

	private AsignarFechaHoraAuditoriaRule() {

	}

	public static Rule<AuditoriaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(AuditoriaEntidad auditoria, DAOFactory daoFactory) {
		auditoria.setFechaHora(UtilFechaHora.obtenerFechaHoraActual());
	}
}
