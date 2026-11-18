package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.FormatosAtributos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoCelularUsuarioValidoRule implements Rule<UsuarioEntidad> {

	private static final Rule<UsuarioEntidad> INSTANCIA = new AsegurarFormatoCelularUsuarioValidoRule();

	private AsegurarFormatoCelularUsuarioValidoRule() {

	}

	public static Rule<UsuarioEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UsuarioEntidad... datos) {
		var usuario = datos[0];

		ValidadorAtributos.asegurarFormato(usuario.getCelular(), FormatosAtributos.CELULAR_COLOMBIANO,
				CatalogoMensajes.Reglas.USUARIO_CELULAR_NO_VALIDO);
	}
}
