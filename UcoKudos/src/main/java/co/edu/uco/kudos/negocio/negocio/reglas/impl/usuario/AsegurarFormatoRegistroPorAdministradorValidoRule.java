package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;

public final class AsegurarFormatoRegistroPorAdministradorValidoRule implements Rule<UsuarioEntidad> {

	private static final Rule<UsuarioEntidad> INSTANCIA = new AsegurarFormatoRegistroPorAdministradorValidoRule();

	private AsegurarFormatoRegistroPorAdministradorValidoRule() {

	}

	public static Rule<UsuarioEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UsuarioEntidad usuario, DAOFactory daoFactory) {
		AsegurarFormatoNombreUsuarioValidoRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		AsegurarFormatoCorreoUsuarioValidoRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		AsegurarFormatoCelularUsuarioValidoRule.obtenerInstancia().ejecutar(usuario, daoFactory);
	}
}
