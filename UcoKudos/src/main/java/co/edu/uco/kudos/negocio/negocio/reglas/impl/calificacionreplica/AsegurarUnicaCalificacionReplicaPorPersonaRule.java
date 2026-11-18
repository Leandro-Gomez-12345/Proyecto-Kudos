package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CalificacionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarUnicaCalificacionReplicaPorPersonaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarUnicaCalificacionReplicaPorPersonaRule();

	private AsegurarUnicaCalificacionReplicaPorPersonaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var calificacion = (CalificacionReplicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var filtro = new CalificacionReplicaEntidad();
		filtro.setReplica(calificacion.getReplica());
		filtro.getUsuario().setId(ContextoEjecucion.obtenerIdEjecutor());
		var coincidencias = daoFactory.obtenerCalificacionReplicaDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(coincidencias.isEmpty(),
				CatalogoMensajes.Reglas.CALIFICACION_YA_REGISTRADA);
	}
}
