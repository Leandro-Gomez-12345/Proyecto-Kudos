package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CoordinacionFacultadEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdCoordinacionFacultadUnicoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarIdCoordinacionFacultadUnicoRule();

	private AsegurarIdCoordinacionFacultadUnicoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var coordinacionFacultad = (CoordinacionFacultadEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		do {
			coordinacionFacultad.setId(UtilUUID.generar());
		} while (existeConId(coordinacionFacultad.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerCoordinacionFacultadDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
