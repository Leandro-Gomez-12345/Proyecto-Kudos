package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.KudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;

public final class CongelarNombreCategoriaEnKudoRule implements Rule<KudoEntidad> {

	private static final Rule<KudoEntidad> INSTANCIA = new CongelarNombreCategoriaEnKudoRule();

	private CongelarNombreCategoriaEnKudoRule() {

	}

	public static Rule<KudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(KudoEntidad kudo, DAOFactory daoFactory) {
		var categoria = daoFactory.obtenerCategoriaDAO().consultarPorId(kudo.getCategoria().getId());
		kudo.setNombreCategoria(categoria.getNombre());
	}
}
