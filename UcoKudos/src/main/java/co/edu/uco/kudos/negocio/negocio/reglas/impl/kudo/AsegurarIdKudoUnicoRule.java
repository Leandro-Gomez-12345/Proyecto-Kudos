package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.KudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdKudoUnicoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarIdKudoUnicoRule();

	private AsegurarIdKudoUnicoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var kudo = (KudoEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		do {
			kudo.setId(UtilUUID.generar());
		} while (existeConId(kudo.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerKudoDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
