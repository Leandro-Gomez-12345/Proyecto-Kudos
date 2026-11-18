package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CalificacionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoCalificacionKudoValidoRule implements Rule<CalificacionKudoEntidad> {

	private static final Rule<CalificacionKudoEntidad> INSTANCIA = new AsegurarFormatoCalificacionKudoValidoRule();

	private static final int ESTRELLAS_MINIMAS = 1;
	private static final int ESTRELLAS_MAXIMAS = 5;

	private AsegurarFormatoCalificacionKudoValidoRule() {

	}

	public static Rule<CalificacionKudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CalificacionKudoEntidad calificacion, DAOFactory daoFactory) {
		ValidadorAtributos.asegurarReferencia(calificacion.getKudo().getId(),
				CatalogoMensajes.Reglas.CALIFICACION_KUDO_OBLIGATORIO);
		ValidadorAtributos.asegurarEnteroEnRango(calificacion.getEstrellas(), ESTRELLAS_MINIMAS,
				ESTRELLAS_MAXIMAS, CatalogoMensajes.Reglas.CALIFICACION_ESTRELLAS_NO_VALIDAS);
	}
}
