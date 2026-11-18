package co.edu.uco.kudos.negocio.negocio.reglas.impl.programaacademico;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ProgramaAcademicoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdProgramaAcademicoUnicoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarIdProgramaAcademicoUnicoRule();

	private AsegurarIdProgramaAcademicoUnicoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var programaAcademico = (ProgramaAcademicoEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		do {
			programaAcademico.setId(UtilUUID.generar());
		} while (existeConId(programaAcademico.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerProgramaAcademicoDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
