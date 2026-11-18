package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReporteKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarReporteKudoNoRepetidoRule implements Rule<ReporteKudoEntidad> {

	private static final Rule<ReporteKudoEntidad> INSTANCIA = new AsegurarReporteKudoNoRepetidoRule();

	private AsegurarReporteKudoNoRepetidoRule() {

	}

	public static Rule<ReporteKudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReporteKudoEntidad reporte, DAOFactory daoFactory) {
		var filtro = new ReporteKudoEntidad();
		filtro.setKudo(reporte.getKudo());
		filtro.getUsuario().setId(ContextoEjecucion.obtenerIdEjecutor());
		var coincidencias = daoFactory.obtenerReporteKudoDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(coincidencias.isEmpty(),
				CatalogoMensajes.Reglas.REPORTE_YA_REGISTRADO);
	}
}
