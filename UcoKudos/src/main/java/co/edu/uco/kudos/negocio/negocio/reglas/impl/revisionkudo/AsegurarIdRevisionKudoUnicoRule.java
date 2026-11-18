package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.RevisionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdRevisionKudoUnicoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarIdRevisionKudoUnicoRule();

	private AsegurarIdRevisionKudoUnicoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var revisionKudo = (RevisionKudoEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		do {
			revisionKudo.setId(UtilUUID.generar());
		} while (existeConId(revisionKudo.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerRevisionKudoDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
