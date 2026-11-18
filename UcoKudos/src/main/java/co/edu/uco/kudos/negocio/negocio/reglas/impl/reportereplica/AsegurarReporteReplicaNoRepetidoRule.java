package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReporteReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarReporteReplicaNoRepetidoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarReporteReplicaNoRepetidoRule();

	private AsegurarReporteReplicaNoRepetidoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var reporte = (ReporteReplicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var filtro = new ReporteReplicaEntidad();
		filtro.setReplica(reporte.getReplica());
		filtro.getUsuario().setId(ContextoEjecucion.obtenerIdEjecutor());
		var coincidencias = daoFactory.obtenerReporteReplicaDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(coincidencias.isEmpty(),
				CatalogoMensajes.Reglas.REPORTE_YA_REGISTRADO);
	}
}
