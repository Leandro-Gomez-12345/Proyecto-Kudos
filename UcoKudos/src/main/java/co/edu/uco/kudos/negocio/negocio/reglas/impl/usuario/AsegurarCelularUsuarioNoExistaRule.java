package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarCelularUsuarioNoExistaRule implements Rule<UsuarioEntidad> {

	private static final Rule<UsuarioEntidad> INSTANCIA = new AsegurarCelularUsuarioNoExistaRule();

	private AsegurarCelularUsuarioNoExistaRule() {

	}

	public static Rule<UsuarioEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UsuarioEntidad usuario, DAOFactory daoFactory) {
		var filtro = new UsuarioEntidad();
		filtro.setCelular(usuario.getCelular());
		var coincidencias = daoFactory.obtenerUsuarioDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarQueNoExisteOtro(coincidencias.stream().map(UsuarioEntidad::getId).toList(),
				usuario.getId(), CatalogoMensajes.Reglas.USUARIO_CELULAR_REPETIDO);
	}
}
