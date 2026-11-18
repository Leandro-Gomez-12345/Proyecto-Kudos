package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.RevisionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsignarDatosInicialesRevisionReplicaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsignarDatosInicialesRevisionReplicaRule();

	private AsignarDatosInicialesRevisionReplicaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var revision = (RevisionReplicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		revision.getModerador().setId(ContextoEjecucion.obtenerIdEjecutor());
		revision.setFechaInicio(UtilFechaHora.obtenerFechaHoraActual());
		revision.setEstadoRevision(ConsultorCatalogos.obtenerEstadoRevision(daoFactory,
				NombresCatalogo.EstadoRevision.EN_CURSO));
	}
}
