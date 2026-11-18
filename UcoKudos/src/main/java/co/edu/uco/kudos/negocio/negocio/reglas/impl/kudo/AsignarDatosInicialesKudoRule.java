package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.KudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsignarDatosInicialesKudoRule implements Rule<KudoEntidad> {

	private static final Rule<KudoEntidad> INSTANCIA = new AsignarDatosInicialesKudoRule();

	private AsignarDatosInicialesKudoRule() {

	}

	public static Rule<KudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(KudoEntidad kudo, DAOFactory daoFactory) {
		kudo.getAutor().setId(ContextoEjecucion.obtenerIdEjecutor());
		kudo.setFechaCreacion(UtilFechaHora.obtenerFechaHoraActual());
		kudo.setEstadoContenido(ConsultorCatalogos.obtenerEstadoContenido(daoFactory,
				NombresCatalogo.EstadoContenido.CREADO));
	}
}
