package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.FormatosAtributos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoCorreoUsuarioValidoRule implements Rule<UsuarioEntidad> {

	private static final Rule<UsuarioEntidad> INSTANCIA = new AsegurarFormatoCorreoUsuarioValidoRule();

	private static final int LONGITUD_MINIMA_CORREO = 12;
	private static final int LONGITUD_MAXIMA_CORREO = 100;

	private AsegurarFormatoCorreoUsuarioValidoRule() {

	}

	public static Rule<UsuarioEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UsuarioEntidad... datos) {
		var usuario = datos[0];

		ValidadorAtributos.asegurarTexto(usuario.getCorreoInstitucional(), LONGITUD_MINIMA_CORREO,
				LONGITUD_MAXIMA_CORREO, CatalogoMensajes.Reglas.USUARIO_CORREO_NO_VALIDO);
		ValidadorAtributos.asegurarFormato(usuario.getCorreoInstitucional(), FormatosAtributos.CORREO_INSTITUCIONAL,
				CatalogoMensajes.Reglas.USUARIO_CORREO_NO_VALIDO);
	}
}
