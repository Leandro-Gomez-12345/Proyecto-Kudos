package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import java.util.HashSet;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarConversacionSinCiclosRule implements Rule<ReplicaEntidad> {

	private static final Rule<ReplicaEntidad> INSTANCIA = new AsegurarConversacionSinCiclosRule();

	private AsegurarConversacionSinCiclosRule() {

	}

	public static Rule<ReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReplicaEntidad replica, DAOFactory daoFactory) {
		var ancestro = replica.getReplicaPadre();
		var visitados = new HashSet<UUID>();
		visitados.add(replica.getId());

		while (!UtilObjeto.esNulo(ancestro)) {
			var existente = daoFactory.obtenerReplicaDAO().consultarPorId(ancestro.getId());
			var esValido = !UtilUUID.esValorDefecto(existente.getId()) && visitados.add(existente.getId());
			ValidadorNegocio.asegurarCondicion(esValido, CatalogoMensajes.Reglas.REPLICA_SIN_RESPUESTA_POSIBLE);
			ancestro = existente.getReplicaPadre();
		}
	}
}
