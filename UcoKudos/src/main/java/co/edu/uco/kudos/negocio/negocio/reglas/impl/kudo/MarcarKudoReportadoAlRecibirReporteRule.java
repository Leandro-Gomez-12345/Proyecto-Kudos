package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;

public final class MarcarKudoReportadoAlRecibirReporteRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new MarcarKudoReportadoAlRecibirReporteRule();

	private MarcarKudoReportadoAlRecibirReporteRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		var kudo = daoFactory.obtenerKudoDAO().consultarPorId(id);
		AsegurarKudoVisibleParaReportarRule.obtenerInstancia().ejecutar(kudo, daoFactory);
		daoFactory.obtenerKudoDAO().actualizar(id, kudo);
	}
}
