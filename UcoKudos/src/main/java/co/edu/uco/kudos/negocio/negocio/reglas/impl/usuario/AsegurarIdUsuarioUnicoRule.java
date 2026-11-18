package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarIdUsuarioUnicoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarIdUsuarioUnicoRule();

	private AsegurarIdUsuarioUnicoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var usuario = (UsuarioEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		do {
			usuario.setId(UtilUUID.generar());
		} while (existeConId(usuario.getId(), daoFactory));
	}

	private static boolean existeConId(UUID id, DAOFactory daoFactory) {
		var existente = daoFactory.obtenerUsuarioDAO().consultarPorId(id);
		return !UtilUUID.esValorDefecto(existente.getId());
	}
}
