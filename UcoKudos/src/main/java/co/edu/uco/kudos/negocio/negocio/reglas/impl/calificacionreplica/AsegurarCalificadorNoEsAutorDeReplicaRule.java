package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CalificacionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarCalificadorNoEsAutorDeReplicaRule implements Rule<CalificacionReplicaEntidad> {

	private static final Rule<CalificacionReplicaEntidad> INSTANCIA = new AsegurarCalificadorNoEsAutorDeReplicaRule();

	private AsegurarCalificadorNoEsAutorDeReplicaRule() {

	}

	public static Rule<CalificacionReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CalificacionReplicaEntidad calificacion, DAOFactory daoFactory) {
		var replica = daoFactory.obtenerReplicaDAO().consultarPorId(calificacion.getReplica().getId());
		var esElAutor = replica.getAutor().getId().equals(ContextoEjecucion.obtenerIdEjecutor());
		ValidadorNegocio.asegurarCondicion(!esElAutor, CatalogoMensajes.Reglas.CALIFICAR_CONTENIDO_PROPIO);
	}
}
