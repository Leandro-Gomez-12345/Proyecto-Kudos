package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReporteKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdReporteKudoUnicoRule implements Rule<ReporteKudoEntidad> {

	private static final Rule<ReporteKudoEntidad> INSTANCIA = new AsegurarIdReporteKudoUnicoRule();

	private AsegurarIdReporteKudoUnicoRule() {

	}

	public static Rule<ReporteKudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReporteKudoEntidad reporteKudo, DAOFactory daoFactory) {
		do {
			reporteKudo.setId(UtilUUID.generar());
		} while (existeConId(reporteKudo.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerReporteKudoDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
