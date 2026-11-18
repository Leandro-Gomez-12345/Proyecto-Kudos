package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.RevisionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarKudoSinRevisionEnCursoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarKudoSinRevisionEnCursoRule();

	private AsegurarKudoSinRevisionEnCursoRule() {

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
		filtro.setEstadoRevision(ConsultorCatalogos.obtenerEstadoRevision(daoFactory,
				NombresCatalogo.EstadoRevision.EN_CURSO));
		var enCurso = daoFactory.obtenerRevisionKudoDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(enCurso.isEmpty(), CatalogoMensajes.Reglas.KUDO_CON_REVISION_EN_CURSO);
	}
}
