package co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.VinculacionAcademicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdVinculacionAcademicaUnicoRule implements Rule<VinculacionAcademicaEntidad> {

	private static final Rule<VinculacionAcademicaEntidad> INSTANCIA = new AsegurarIdVinculacionAcademicaUnicoRule();

	private AsegurarIdVinculacionAcademicaUnicoRule() {

	}

	public static Rule<VinculacionAcademicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(VinculacionAcademicaEntidad vinculacionAcademica, DAOFactory daoFactory) {
		do {
			vinculacionAcademica.setId(UtilUUID.generar());
		} while (existeConId(vinculacionAcademica.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerVinculacionAcademicaDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
