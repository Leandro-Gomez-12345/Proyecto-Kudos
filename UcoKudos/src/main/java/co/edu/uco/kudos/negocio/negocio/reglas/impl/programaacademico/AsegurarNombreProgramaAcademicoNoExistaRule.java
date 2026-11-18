package co.edu.uco.kudos.negocio.negocio.reglas.impl.programaacademico;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ProgramaAcademicoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarNombreProgramaAcademicoNoExistaRule implements Rule<ProgramaAcademicoEntidad> {

	private static final Rule<ProgramaAcademicoEntidad> INSTANCIA = new AsegurarNombreProgramaAcademicoNoExistaRule();

	private AsegurarNombreProgramaAcademicoNoExistaRule() {

	}

	public static Rule<ProgramaAcademicoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ProgramaAcademicoEntidad programa, DAOFactory daoFactory) {
		var filtro = new ProgramaAcademicoEntidad();
		filtro.setNombre(programa.getNombre());
		var coincidencias = daoFactory.obtenerProgramaAcademicoDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarQueNoExisteOtro(
				coincidencias.stream().map(ProgramaAcademicoEntidad::getId).toList(), programa.getId(),
				CatalogoMensajes.Reglas.PROGRAMA_NOMBRE_REPETIDO);
	}
}
