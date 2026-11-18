package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsegurarReplicaNoRepetidaRule implements Rule<ReplicaEntidad> {

	private static final Rule<ReplicaEntidad> INSTANCIA = new AsegurarReplicaNoRepetidaRule();

	private AsegurarReplicaNoRepetidaRule() {

	}

	public static Rule<ReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReplicaEntidad replica, DAOFactory daoFactory) {
		var filtro = new ReplicaEntidad();
		filtro.getAutor().setId(ContextoEjecucion.obtenerIdEjecutor());
		filtro.setFechaCreacion(UtilFechaHora.obtenerFechaHoraActual());
		var coincidencias = daoFactory.obtenerReplicaDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(coincidencias.isEmpty(), CatalogoMensajes.Reglas.REPLICA_REPETIDA);
	}
}
