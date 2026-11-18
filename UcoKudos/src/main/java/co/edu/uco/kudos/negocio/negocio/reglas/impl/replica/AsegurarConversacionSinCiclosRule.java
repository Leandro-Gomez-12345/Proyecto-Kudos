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

public final class AsegurarConversacionSinCiclosRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarConversacionSinCiclosRule();

	private AsegurarConversacionSinCiclosRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var replica = (ReplicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

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
