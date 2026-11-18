package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CalificacionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdCalificacionKudoUnicoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarIdCalificacionKudoUnicoRule();

	private AsegurarIdCalificacionKudoUnicoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var calificacionKudo = (CalificacionKudoEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		do {
			calificacionKudo.setId(UtilUUID.generar());
		} while (existeConId(calificacionKudo.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerCalificacionKudoDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
