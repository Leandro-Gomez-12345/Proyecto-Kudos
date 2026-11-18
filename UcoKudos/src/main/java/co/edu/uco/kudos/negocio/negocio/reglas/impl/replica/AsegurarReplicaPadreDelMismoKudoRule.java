package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class AsegurarReplicaPadreDelMismoKudoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarReplicaPadreDelMismoKudoRule();

	private AsegurarReplicaPadreDelMismoKudoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var replica = (ReplicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		if (UtilObjeto.esNulo(replica.getReplicaPadre())) {
			return;
		}

		var padre = daoFactory.obtenerReplicaDAO().consultarPorId(replica.getReplicaPadre().getId());
		ValidadorNegocio.asegurarCondicion(padre.getKudo().getId().equals(replica.getKudo().getId()),
				CatalogoMensajes.Reglas.REPLICA_PADRE_DE_OTRO_KUDO);
	}
}
