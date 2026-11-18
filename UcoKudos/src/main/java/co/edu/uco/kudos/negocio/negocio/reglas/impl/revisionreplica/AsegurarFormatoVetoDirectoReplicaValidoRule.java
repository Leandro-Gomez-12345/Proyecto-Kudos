package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.RevisionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoVetoDirectoReplicaValidoRule implements Rule<RevisionReplicaEntidad> {

	private static final Rule<RevisionReplicaEntidad> INSTANCIA = new AsegurarFormatoVetoDirectoReplicaValidoRule();

	private AsegurarFormatoVetoDirectoReplicaValidoRule() {

	}

	public static Rule<RevisionReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(RevisionReplicaEntidad revision, DAOFactory daoFactory) {
		ValidadorAtributos.asegurarReferencia(revision.getReplica().getId(),
				CatalogoMensajes.Reglas.REPLICA_VETO_OBLIGATORIO);
		AsegurarFormatoJustificacionRevisionReplicaValidoRule.obtenerInstancia().ejecutar(revision, daoFactory);
	}
}
