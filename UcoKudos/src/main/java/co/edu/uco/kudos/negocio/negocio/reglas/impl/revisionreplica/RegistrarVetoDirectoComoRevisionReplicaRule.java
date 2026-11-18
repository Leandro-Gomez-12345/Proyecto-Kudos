package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.RevisionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class RegistrarVetoDirectoComoRevisionReplicaRule implements Rule<RevisionReplicaEntidad> {

	private static final Rule<RevisionReplicaEntidad> INSTANCIA = new RegistrarVetoDirectoComoRevisionReplicaRule();

	private RegistrarVetoDirectoComoRevisionReplicaRule() {

	}

	public static Rule<RevisionReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(RevisionReplicaEntidad revision, DAOFactory daoFactory) {
		var ahora = UtilFechaHora.obtenerFechaHoraActual();
		revision.getModerador().setId(ContextoEjecucion.obtenerIdEjecutor());
		revision.setFechaInicio(ahora);
		revision.setFechaCierre(ahora);
		revision.setEstadoRevision(ConsultorCatalogos.obtenerEstadoRevision(daoFactory,
				NombresCatalogo.EstadoRevision.CONTENIDO_VETADO));
	}
}
