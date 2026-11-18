package co.edu.uco.kudos.negocio.negocio.reglas.impl.programaacademico;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ProgramaAcademicoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdProgramaAcademicoUnicoRule implements Rule<ProgramaAcademicoEntidad> {

	private static final Rule<ProgramaAcademicoEntidad> INSTANCIA = new AsegurarIdProgramaAcademicoUnicoRule();

	private AsegurarIdProgramaAcademicoUnicoRule() {

	}

	public static Rule<ProgramaAcademicoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ProgramaAcademicoEntidad programaAcademico, DAOFactory daoFactory) {
		do {
			programaAcademico.setId(UtilUUID.generar());
		} while (existeConId(programaAcademico.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerProgramaAcademicoDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
