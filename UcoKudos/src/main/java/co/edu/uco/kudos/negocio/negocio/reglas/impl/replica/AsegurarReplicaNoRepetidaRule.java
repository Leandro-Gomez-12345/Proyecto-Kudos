package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsegurarReplicaNoRepetidaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarReplicaNoRepetidaRule();

	private AsegurarReplicaNoRepetidaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var daoFactory = (DAOFactory) datos[1];

		var filtro = new ReplicaEntidad();
		filtro.getAutor().setId(ContextoEjecucion.obtenerIdEjecutor());
		filtro.setFechaCreacion(UtilFechaHora.obtenerFechaHoraActual());
		var coincidencias = daoFactory.obtenerReplicaDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(coincidencias.isEmpty(), CatalogoMensajes.Reglas.REPLICA_REPETIDA);
	}
}
