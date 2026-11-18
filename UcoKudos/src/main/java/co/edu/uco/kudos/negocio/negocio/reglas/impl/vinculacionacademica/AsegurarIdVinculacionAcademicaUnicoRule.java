package co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.VinculacionAcademicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdVinculacionAcademicaUnicoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarIdVinculacionAcademicaUnicoRule();

	private AsegurarIdVinculacionAcademicaUnicoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var vinculacionAcademica = (VinculacionAcademicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		do {
			vinculacionAcademica.setId(UtilUUID.generar());
		} while (existeConId(vinculacionAcademica.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerVinculacionAcademicaDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
