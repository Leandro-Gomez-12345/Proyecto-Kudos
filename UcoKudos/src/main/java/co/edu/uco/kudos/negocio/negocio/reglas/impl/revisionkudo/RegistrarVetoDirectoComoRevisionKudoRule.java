package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.RevisionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class RegistrarVetoDirectoComoRevisionKudoRule implements Rule<RevisionKudoEntidad> {

	private static final Rule<RevisionKudoEntidad> INSTANCIA = new RegistrarVetoDirectoComoRevisionKudoRule();

	private RegistrarVetoDirectoComoRevisionKudoRule() {

	}

	public static Rule<RevisionKudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(RevisionKudoEntidad revision, DAOFactory daoFactory) {
		var ahora = UtilFechaHora.obtenerFechaHoraActual();
		revision.getModerador().setId(ContextoEjecucion.obtenerIdEjecutor());
		revision.setFechaInicio(ahora);
		revision.setFechaCierre(ahora);
		revision.setEstadoRevision(ConsultorCatalogos.obtenerEstadoRevision(daoFactory,
				NombresCatalogo.EstadoRevision.CONTENIDO_VETADO));
	}
}
