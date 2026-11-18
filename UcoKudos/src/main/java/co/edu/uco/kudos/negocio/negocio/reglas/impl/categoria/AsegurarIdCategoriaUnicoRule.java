package co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CategoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdCategoriaUnicoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarIdCategoriaUnicoRule();

	private AsegurarIdCategoriaUnicoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var categoria = (CategoriaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		do {
			categoria.setId(UtilUUID.generar());
		} while (existeConId(categoria.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerCategoriaDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
