package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class AsegurarReplicaPadreDelMismoKudoRule implements Rule<ReplicaEntidad> {

	private static final Rule<ReplicaEntidad> INSTANCIA = new AsegurarReplicaPadreDelMismoKudoRule();

	private AsegurarReplicaPadreDelMismoKudoRule() {

	}

	public static Rule<ReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReplicaEntidad replica, DAOFactory daoFactory) {
		if (UtilObjeto.esNulo(replica.getReplicaPadre())) {
			return;
		}

		var padre = daoFactory.obtenerReplicaDAO().consultarPorId(replica.getReplicaPadre().getId());
		ValidadorNegocio.asegurarCondicion(padre.getKudo().getId().equals(replica.getKudo().getId()),
				CatalogoMensajes.Reglas.REPLICA_PADRE_DE_OTRO_KUDO);
	}
}
