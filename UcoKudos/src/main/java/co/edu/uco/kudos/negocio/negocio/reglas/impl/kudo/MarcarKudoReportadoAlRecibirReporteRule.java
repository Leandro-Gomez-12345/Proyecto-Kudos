package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;

public final class MarcarKudoReportadoAlRecibirReporteRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new MarcarKudoReportadoAlRecibirReporteRule();

	private MarcarKudoReportadoAlRecibirReporteRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var id = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var kudo = daoFactory.obtenerKudoDAO().consultarPorId(id);
		AsegurarKudoVisibleParaReportarRule.obtenerInstancia().ejecutar(kudo, daoFactory);
		daoFactory.obtenerKudoDAO().actualizar(id, kudo);
	}
}
