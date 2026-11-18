package co.edu.uco.kudos.negocio.negocio.reglas.impl.auditoria;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.AuditoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdAuditoriaUnicoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarIdAuditoriaUnicoRule();

	private AsegurarIdAuditoriaUnicoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var auditoria = (AuditoriaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		do {
			auditoria.setId(UtilUUID.generar());
		} while (existeConId(auditoria.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerAuditoriaDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
