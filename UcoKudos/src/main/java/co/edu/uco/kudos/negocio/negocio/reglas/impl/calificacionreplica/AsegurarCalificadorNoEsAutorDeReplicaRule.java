package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CalificacionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarCalificadorNoEsAutorDeReplicaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarCalificadorNoEsAutorDeReplicaRule();

	private AsegurarCalificadorNoEsAutorDeReplicaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var calificacion = (CalificacionReplicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var replica = daoFactory.obtenerReplicaDAO().consultarPorId(calificacion.getReplica().getId());
		var esElAutor = replica.getAutor().getId().equals(ContextoEjecucion.obtenerIdEjecutor());
		ValidadorNegocio.asegurarCondicion(!esElAutor, CatalogoMensajes.Reglas.CALIFICAR_CONTENIDO_PROPIO);
	}
}
