package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.KudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;

public final class CongelarNombreCategoriaEnKudoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new CongelarNombreCategoriaEnKudoRule();

	private CongelarNombreCategoriaEnKudoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var kudo = (KudoEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var categoria = daoFactory.obtenerCategoriaDAO().consultarPorId(kudo.getCategoria().getId());
		kudo.setNombreCategoria(categoria.getNombre());
	}
}
