package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.RevisionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsignarCierreConLiberacionRevisionKudoRule implements Rule<RevisionKudoEntidad> {

	private static final Rule<RevisionKudoEntidad> INSTANCIA = new AsignarCierreConLiberacionRevisionKudoRule();

	private AsignarCierreConLiberacionRevisionKudoRule() {

	}

	public static Rule<RevisionKudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(RevisionKudoEntidad revision, DAOFactory daoFactory) {
		revision.setEstadoRevision(ConsultorCatalogos.obtenerEstadoRevision(daoFactory,
				NombresCatalogo.EstadoRevision.CONTENIDO_LIBERADO));
		revision.setFechaCierre(UtilFechaHora.obtenerFechaHoraActual());
	}
}
