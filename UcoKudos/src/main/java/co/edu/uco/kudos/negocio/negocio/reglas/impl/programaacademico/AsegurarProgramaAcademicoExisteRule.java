package co.edu.uco.kudos.negocio.negocio.reglas.impl.programaacademico;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarProgramaAcademicoExisteRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarProgramaAcademicoExisteRule();

	private AsegurarProgramaAcademicoExisteRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		var entidad = daoFactory.obtenerProgramaAcademicoDAO().consultarPorId(id);
		ValidadorNegocio.asegurarQueExiste(entidad.getId(), CatalogoMensajes.Reglas.PROGRAMA_NO_EXISTE);
	}
}
