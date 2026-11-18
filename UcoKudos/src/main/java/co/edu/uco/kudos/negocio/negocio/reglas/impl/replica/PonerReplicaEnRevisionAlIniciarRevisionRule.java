package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosContenido;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class PonerReplicaEnRevisionAlIniciarRevisionRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new PonerReplicaEnRevisionAlIniciarRevisionRule();

	private PonerReplicaEnRevisionAlIniciarRevisionRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		EstadosContenido.cambiarEstadoReplica(daoFactory, id, NombresCatalogo.EstadoContenido.EN_REVISION);
	}
}
