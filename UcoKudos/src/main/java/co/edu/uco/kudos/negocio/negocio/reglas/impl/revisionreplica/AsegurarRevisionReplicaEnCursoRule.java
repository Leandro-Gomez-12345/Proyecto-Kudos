package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarRevisionReplicaEnCursoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarRevisionReplicaEnCursoRule();

	private AsegurarRevisionReplicaEnCursoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var id = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var revision = daoFactory.obtenerRevisionReplicaDAO().consultarPorId(id);
		var estaEnCurso = ConsultorCatalogos.coincide(ConsultorCatalogos.nombreEstadoRevision(daoFactory,
				revision.getEstadoRevision().getId()), NombresCatalogo.EstadoRevision.EN_CURSO);
		ValidadorNegocio.asegurarCondicion(estaEnCurso, CatalogoMensajes.Reglas.REVISION_YA_CERRADA);
	}
}
