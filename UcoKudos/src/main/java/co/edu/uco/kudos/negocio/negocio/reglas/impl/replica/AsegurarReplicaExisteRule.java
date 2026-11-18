package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarReplicaExisteRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarReplicaExisteRule();

	private AsegurarReplicaExisteRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var id = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var entidad = daoFactory.obtenerReplicaDAO().consultarPorId(id);
		ValidadorNegocio.asegurarQueExiste(entidad.getId(), CatalogoMensajes.Reglas.REPLICA_NO_EXISTE);
	}
}
