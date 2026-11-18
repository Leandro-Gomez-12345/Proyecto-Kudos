package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarRolNuevoDistintoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarRolNuevoDistintoRule();

	private AsegurarRolNuevoDistintoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var usuario = (UsuarioEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var actual = daoFactory.obtenerUsuarioDAO().consultarPorId(usuario.getId());
		ValidadorNegocio.asegurarCondicion(!actual.getRol().getId().equals(usuario.getRol().getId()),
				CatalogoMensajes.Reglas.ROL_NUEVO_IGUAL_AL_ACTUAL);
	}
}
