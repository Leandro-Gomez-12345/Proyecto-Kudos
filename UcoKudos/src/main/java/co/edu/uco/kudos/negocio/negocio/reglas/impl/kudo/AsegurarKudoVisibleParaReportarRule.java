package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.KudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarKudoVisibleParaReportarRule implements Rule<KudoEntidad> {

	private static final Rule<KudoEntidad> INSTANCIA = new AsegurarKudoVisibleParaReportarRule();

	private AsegurarKudoVisibleParaReportarRule() {

	}

	public static Rule<KudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(KudoEntidad kudo, DAOFactory daoFactory) {
		var nombreEstado = ConsultorCatalogos.nombreEstadoContenido(daoFactory, kudo.getEstadoContenido().getId());
		if (ConsultorCatalogos.coincide(nombreEstado, NombresCatalogo.EstadoContenido.VISIBLE)) {
			kudo.setEstadoContenido(ConsultorCatalogos.obtenerEstadoContenido(daoFactory,
					NombresCatalogo.EstadoContenido.REPORTADO));
		}
	}
}
