package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoRegistroUsuarioValidoRule implements Rule<UsuarioEntidad> {

	private static final Rule<UsuarioEntidad> INSTANCIA = new AsegurarFormatoRegistroUsuarioValidoRule();

	private AsegurarFormatoRegistroUsuarioValidoRule() {

	}

	public static Rule<UsuarioEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UsuarioEntidad usuario, DAOFactory daoFactory) {
		AsegurarFormatoRegistroPorAdministradorValidoRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		ValidadorAtributos.asegurarReferencia(usuario.getRol().getId(),
				CatalogoMensajes.Reglas.USUARIO_ROL_OBLIGATORIO);
	}
}
