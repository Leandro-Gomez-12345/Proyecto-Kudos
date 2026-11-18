package co.edu.uco.kudos.negocio.negocio.reglas.impl.facultad;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.FacultadEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdFacultadUnicoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarIdFacultadUnicoRule();

	private AsegurarIdFacultadUnicoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var facultad = (FacultadEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		do {
			facultad.setId(UtilUUID.generar());
		} while (existeConId(facultad.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerFacultadDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
