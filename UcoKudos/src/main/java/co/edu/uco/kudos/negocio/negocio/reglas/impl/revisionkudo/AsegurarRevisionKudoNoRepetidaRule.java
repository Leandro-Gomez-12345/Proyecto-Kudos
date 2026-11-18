package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.RevisionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsegurarRevisionKudoNoRepetidaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarRevisionKudoNoRepetidaRule();

	private AsegurarRevisionKudoNoRepetidaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var revision = (RevisionKudoEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var filtro = new RevisionKudoEntidad();
		filtro.setKudo(revision.getKudo());
		filtro.setFechaInicio(UtilFechaHora.obtenerFechaHoraActual());
		var coincidencias = daoFactory.obtenerRevisionKudoDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(coincidencias.isEmpty(),
				CatalogoMensajes.Reglas.REVISION_REPETIDA);
	}
}
