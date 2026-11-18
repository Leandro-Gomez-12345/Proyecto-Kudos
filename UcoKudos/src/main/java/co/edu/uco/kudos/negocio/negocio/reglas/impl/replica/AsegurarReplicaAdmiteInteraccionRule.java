package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosContenido;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarReplicaAdmiteInteraccionRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarReplicaAdmiteInteraccionRule();

	private AsegurarReplicaAdmiteInteraccionRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		ValidadorNegocio.asegurarCondicion(EstadosContenido.replicaEstaEn(daoFactory, id,
				NombresCatalogo.EstadoContenido.VISIBLE,
				NombresCatalogo.EstadoContenido.REPORTADO,
				NombresCatalogo.EstadoContenido.EN_REVISION),
				CatalogoMensajes.Reglas.REPLICA_NO_ADMITE_INTERACCION);
	}
}
