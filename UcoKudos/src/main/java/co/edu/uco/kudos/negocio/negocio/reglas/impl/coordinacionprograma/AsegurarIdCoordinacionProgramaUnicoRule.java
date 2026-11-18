package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CoordinacionProgramaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdCoordinacionProgramaUnicoRule implements Rule<CoordinacionProgramaEntidad> {

	private static final Rule<CoordinacionProgramaEntidad> INSTANCIA = new AsegurarIdCoordinacionProgramaUnicoRule();

	private AsegurarIdCoordinacionProgramaUnicoRule() {

	}

	public static Rule<CoordinacionProgramaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CoordinacionProgramaEntidad coordinacionPrograma, DAOFactory daoFactory) {
		do {
			coordinacionPrograma.setId(UtilUUID.generar());
		} while (existeConId(coordinacionPrograma.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerCoordinacionProgramaDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
