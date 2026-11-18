package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosContenido;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarReplicaReportadaRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarReplicaReportadaRule();

	private AsegurarReplicaReportadaRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		ValidadorNegocio.asegurarCondicion(EstadosContenido.replicaEstaEn(daoFactory, id,
				NombresCatalogo.EstadoContenido.REPORTADO),
				CatalogoMensajes.Reglas.REPLICA_SIN_REPORTES_PENDIENTES);
	}
}
