package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.RevisionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsignarCierreConLiberacionRevisionReplicaRule implements Rule<RevisionReplicaEntidad> {

	private static final Rule<RevisionReplicaEntidad> INSTANCIA = new AsignarCierreConLiberacionRevisionReplicaRule();

	private AsignarCierreConLiberacionRevisionReplicaRule() {

	}

	public static Rule<RevisionReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(RevisionReplicaEntidad revision, DAOFactory daoFactory) {
		revision.setEstadoRevision(ConsultorCatalogos.obtenerEstadoRevision(daoFactory,
				NombresCatalogo.EstadoRevision.CONTENIDO_LIBERADO));
		revision.setFechaCierre(UtilFechaHora.obtenerFechaHoraActual());
	}
}
