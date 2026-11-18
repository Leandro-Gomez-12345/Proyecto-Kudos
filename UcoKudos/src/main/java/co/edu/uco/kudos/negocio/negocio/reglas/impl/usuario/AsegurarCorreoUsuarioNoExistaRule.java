package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarCorreoUsuarioNoExistaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarCorreoUsuarioNoExistaRule();

	private AsegurarCorreoUsuarioNoExistaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var usuario = (UsuarioEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var filtro = new UsuarioEntidad();
		filtro.setCorreoInstitucional(usuario.getCorreoInstitucional());
		var coincidencias = daoFactory.obtenerUsuarioDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarQueNoExisteOtro(coincidencias.stream().map(UsuarioEntidad::getId).toList(),
				usuario.getId(), CatalogoMensajes.Reglas.USUARIO_CORREO_REPETIDO);
	}
}
