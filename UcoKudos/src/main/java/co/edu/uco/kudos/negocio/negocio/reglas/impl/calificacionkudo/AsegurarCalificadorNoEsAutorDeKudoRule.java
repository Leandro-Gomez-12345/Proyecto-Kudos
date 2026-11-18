package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CalificacionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarCalificadorNoEsAutorDeKudoRule implements Rule<CalificacionKudoEntidad> {

	private static final Rule<CalificacionKudoEntidad> INSTANCIA = new AsegurarCalificadorNoEsAutorDeKudoRule();

	private AsegurarCalificadorNoEsAutorDeKudoRule() {

	}

	public static Rule<CalificacionKudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CalificacionKudoEntidad calificacion, DAOFactory daoFactory) {
		var kudo = daoFactory.obtenerKudoDAO().consultarPorId(calificacion.getKudo().getId());
		var esElAutor = kudo.getAutor().getId().equals(ContextoEjecucion.obtenerIdEjecutor());
		ValidadorNegocio.asegurarCondicion(!esElAutor, CatalogoMensajes.Reglas.CALIFICAR_CONTENIDO_PROPIO);
	}
}
