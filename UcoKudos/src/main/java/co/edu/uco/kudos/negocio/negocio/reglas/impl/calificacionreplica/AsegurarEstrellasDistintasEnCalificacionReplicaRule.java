package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CalificacionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarEstrellasDistintasEnCalificacionReplicaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarEstrellasDistintasEnCalificacionReplicaRule();

	private AsegurarEstrellasDistintasEnCalificacionReplicaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var calificacion = (CalificacionReplicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var actual = daoFactory.obtenerCalificacionReplicaDAO().consultarPorId(calificacion.getId());
		ValidadorNegocio.asegurarCondicion(actual.getEstrellas() != calificacion.getEstrellas(),
				CatalogoMensajes.Reglas.CALIFICACION_IGUAL_A_LA_ACTUAL);
	}
}
