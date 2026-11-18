package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.KudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class PublicarKudoAlConfirmarRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new PublicarKudoAlConfirmarRule();

	private PublicarKudoAlConfirmarRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var kudo = (KudoEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		kudo.setEstadoContenido(ConsultorCatalogos.obtenerEstadoContenido(daoFactory,
				NombresCatalogo.EstadoContenido.VISIBLE));
	}
}
