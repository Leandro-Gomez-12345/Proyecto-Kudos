package co.edu.uco.kudos.negocio.negocio.reglas.impl.facultad;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.FacultadEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdFacultadUnicoRule implements Rule<FacultadEntidad> {

	private static final Rule<FacultadEntidad> INSTANCIA = new AsegurarIdFacultadUnicoRule();

	private AsegurarIdFacultadUnicoRule() {

	}

	public static Rule<FacultadEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(FacultadEntidad facultad, DAOFactory daoFactory) {
		do {
			facultad.setId(UtilUUID.generar());
		} while (existeConId(facultad.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerFacultadDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
