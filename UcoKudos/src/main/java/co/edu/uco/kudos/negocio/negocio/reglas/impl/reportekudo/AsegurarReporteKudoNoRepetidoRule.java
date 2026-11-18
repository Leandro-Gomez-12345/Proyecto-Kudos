package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReporteKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarReporteKudoNoRepetidoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarReporteKudoNoRepetidoRule();

	private AsegurarReporteKudoNoRepetidoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var reporte = (ReporteKudoEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var filtro = new ReporteKudoEntidad();
		filtro.setKudo(reporte.getKudo());
		filtro.getUsuario().setId(ContextoEjecucion.obtenerIdEjecutor());
		var coincidencias = daoFactory.obtenerReporteKudoDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(coincidencias.isEmpty(),
				CatalogoMensajes.Reglas.REPORTE_YA_REGISTRADO);
	}
}
