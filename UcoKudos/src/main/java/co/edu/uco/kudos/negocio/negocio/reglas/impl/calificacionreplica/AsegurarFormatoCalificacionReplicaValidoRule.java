package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CalificacionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoCalificacionReplicaValidoRule implements Rule<CalificacionReplicaEntidad> {

	private static final Rule<CalificacionReplicaEntidad> INSTANCIA =
			new AsegurarFormatoCalificacionReplicaValidoRule();

	private static final int ESTRELLAS_MINIMAS = 1;
	private static final int ESTRELLAS_MAXIMAS = 5;

	private AsegurarFormatoCalificacionReplicaValidoRule() {

	}

	public static Rule<CalificacionReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CalificacionReplicaEntidad calificacion, DAOFactory daoFactory) {
		ValidadorAtributos.asegurarReferencia(calificacion.getReplica().getId(),
				CatalogoMensajes.Reglas.CALIFICACION_REPLICA_OBLIGATORIO);
		ValidadorAtributos.asegurarEnteroEnRango(calificacion.getEstrellas(), ESTRELLAS_MINIMAS,
				ESTRELLAS_MAXIMAS, CatalogoMensajes.Reglas.CALIFICACION_ESTRELLAS_NO_VALIDAS);
	}
}
