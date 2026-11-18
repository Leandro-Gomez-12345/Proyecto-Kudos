package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.RevisionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarReplicaSinRevisionEnCursoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarReplicaSinRevisionEnCursoRule();

	private AsegurarReplicaSinRevisionEnCursoRule() {

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
		filtro.setEstadoRevision(ConsultorCatalogos.obtenerEstadoRevision(daoFactory,
				NombresCatalogo.EstadoRevision.EN_CURSO));
		var enCurso = daoFactory.obtenerRevisionReplicaDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(enCurso.isEmpty(), CatalogoMensajes.Reglas.REPLICA_CON_REVISION_EN_CURSO);
	}
}
