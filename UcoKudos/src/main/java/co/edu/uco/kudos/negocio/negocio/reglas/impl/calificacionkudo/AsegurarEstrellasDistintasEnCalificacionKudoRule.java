package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CalificacionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarEstrellasDistintasEnCalificacionKudoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarEstrellasDistintasEnCalificacionKudoRule();

	private AsegurarEstrellasDistintasEnCalificacionKudoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var calificacion = (CalificacionKudoEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var actual = daoFactory.obtenerCalificacionKudoDAO().consultarPorId(calificacion.getId());
		ValidadorNegocio.asegurarCondicion(actual.getEstrellas() != calificacion.getEstrellas(),
				CatalogoMensajes.Reglas.CALIFICACION_IGUAL_A_LA_ACTUAL);
	}
}
