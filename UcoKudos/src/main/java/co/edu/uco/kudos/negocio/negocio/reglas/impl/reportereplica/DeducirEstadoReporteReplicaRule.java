package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReporteReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosReporte;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ReportesConEstado;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class DeducirEstadoReporteReplicaRule implements Rule<ReportesConEstado<ReporteReplicaEntidad>> {

	private static final Rule<ReportesConEstado<ReporteReplicaEntidad>> INSTANCIA =
			new DeducirEstadoReporteReplicaRule();

	private DeducirEstadoReporteReplicaRule() {

	}

	public static Rule<ReportesConEstado<ReporteReplicaEntidad>> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReportesConEstado<ReporteReplicaEntidad> consulta, DAOFactory daoFactory) {
		if (UtilTexto.esVacia(consulta.estado())) {
			return;
		}
		consulta.reportes().removeIf(reporte -> !EstadosReporte.coincide(
				deducirEstado(reporte, daoFactory), consulta.estado()));
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
