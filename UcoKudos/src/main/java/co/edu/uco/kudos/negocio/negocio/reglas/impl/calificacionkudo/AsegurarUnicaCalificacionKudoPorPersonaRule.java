package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CalificacionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarUnicaCalificacionKudoPorPersonaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarUnicaCalificacionKudoPorPersonaRule();

	private AsegurarUnicaCalificacionKudoPorPersonaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var calificacion = (CalificacionKudoEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var filtro = new CalificacionKudoEntidad();
		filtro.setKudo(calificacion.getKudo());
		filtro.getUsuario().setId(ContextoEjecucion.obtenerIdEjecutor());
		var coincidencias = daoFactory.obtenerCalificacionKudoDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(coincidencias.isEmpty(),
				CatalogoMensajes.Reglas.CALIFICACION_YA_REGISTRADA);
	}
}
