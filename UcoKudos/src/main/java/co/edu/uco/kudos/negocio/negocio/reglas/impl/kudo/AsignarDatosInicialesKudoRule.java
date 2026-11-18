package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.KudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsignarDatosInicialesKudoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsignarDatosInicialesKudoRule();

	private AsignarDatosInicialesKudoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var kudo = (KudoEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		kudo.getAutor().setId(ContextoEjecucion.obtenerIdEjecutor());
		kudo.setFechaCreacion(UtilFechaHora.obtenerFechaHoraActual());
		kudo.setEstadoContenido(ConsultorCatalogos.obtenerEstadoContenido(daoFactory,
				NombresCatalogo.EstadoContenido.CREADO));
	}
}
