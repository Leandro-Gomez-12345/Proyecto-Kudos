package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.RevisionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsignarDatosInicialesRevisionKudoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsignarDatosInicialesRevisionKudoRule();

	private AsignarDatosInicialesRevisionKudoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var revision = (RevisionKudoEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		revision.getModerador().setId(ContextoEjecucion.obtenerIdEjecutor());
		revision.setFechaInicio(UtilFechaHora.obtenerFechaHoraActual());
		revision.setEstadoRevision(ConsultorCatalogos.obtenerEstadoRevision(daoFactory,
				NombresCatalogo.EstadoRevision.EN_CURSO));
	}
}
