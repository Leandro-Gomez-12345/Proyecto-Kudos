package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CalificacionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarEstrellasDistintasEnCalificacionKudoRule implements Rule<CalificacionKudoEntidad> {

	private static final Rule<CalificacionKudoEntidad> INSTANCIA =
			new AsegurarEstrellasDistintasEnCalificacionKudoRule();

	private AsegurarEstrellasDistintasEnCalificacionKudoRule() {

	}

	public static Rule<CalificacionKudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CalificacionKudoEntidad calificacion, DAOFactory daoFactory) {
		var actual = daoFactory.obtenerCalificacionKudoDAO().consultarPorId(calificacion.getId());
		ValidadorNegocio.asegurarCondicion(actual.getEstrellas() != calificacion.getEstrellas(),
				CatalogoMensajes.Reglas.CALIFICACION_IGUAL_A_LA_ACTUAL);
	}
}
