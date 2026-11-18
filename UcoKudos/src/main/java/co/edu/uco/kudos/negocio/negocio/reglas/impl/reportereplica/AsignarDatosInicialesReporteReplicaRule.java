package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReporteReplicaEntidad;
import co.edu.uco.kudos.entidad.RevisionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsignarDatosInicialesReporteReplicaRule implements Rule<ReporteReplicaEntidad> {

	private static final Rule<ReporteReplicaEntidad> INSTANCIA = new AsignarDatosInicialesReporteReplicaRule();

	private AsignarDatosInicialesReporteReplicaRule() {

	}

	public static Rule<ReporteReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReporteReplicaEntidad reporte, DAOFactory daoFactory) {
		reporte.getUsuario().setId(ContextoEjecucion.obtenerIdEjecutor());
		reporte.setFechaReporte(UtilFechaHora.obtenerFechaHoraActual());
		reporte.setRevisionReplica(new RevisionReplicaEntidad());
	}
}
