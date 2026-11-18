package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.RevisionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsegurarRevisionReplicaNoRepetidaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarRevisionReplicaNoRepetidaRule();

	private AsegurarRevisionReplicaNoRepetidaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var revision = (RevisionReplicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var filtro = new RevisionReplicaEntidad();
		filtro.setReplica(revision.getReplica());
		filtro.setFechaInicio(UtilFechaHora.obtenerFechaHoraActual());
		var coincidencias = daoFactory.obtenerRevisionReplicaDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(coincidencias.isEmpty(),
				CatalogoMensajes.Reglas.REVISION_REPETIDA);
	}
}
