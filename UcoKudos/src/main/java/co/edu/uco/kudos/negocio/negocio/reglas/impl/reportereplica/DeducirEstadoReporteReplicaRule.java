package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReporteReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosReporte;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class DeducirEstadoReporteReplicaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new DeducirEstadoReporteReplicaRule();

	private DeducirEstadoReporteReplicaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void ejecutar(Object... datos) {
		var reportes = (List<ReporteReplicaEntidad>) datos[0];
		var estado = (String) datos[1];
		var daoFactory = (DAOFactory) datos[2];

		if (UtilTexto.esVacia(estado)) {
			return;
		}
		reportes.removeIf(reporte -> !EstadosReporte.coincide(deducirEstado(reporte, daoFactory), estado));
	}

	private static String deducirEstado(ReporteReplicaEntidad reporte, DAOFactory daoFactory) {
		var idRevision = reporte.getRevisionReplica().getId();
		var sinRevision = UtilUUID.esValorDefecto(idRevision);
		if (sinRevision) {
			return EstadosReporte.deducir(sinRevision, UtilTexto.VACIA);
		}
		var revision = daoFactory.obtenerRevisionReplicaDAO().consultarPorId(idRevision);
		var nombreEstado = ConsultorCatalogos.nombreEstadoRevision(daoFactory,
				revision.getEstadoRevision().getId());
		return EstadosReporte.deducir(sinRevision, nombreEstado);
	}
}
