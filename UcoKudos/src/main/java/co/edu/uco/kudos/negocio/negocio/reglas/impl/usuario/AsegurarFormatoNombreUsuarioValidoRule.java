package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.FormatosAtributos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoNombreUsuarioValidoRule implements Rule<UsuarioEntidad> {

	private static final Rule<UsuarioEntidad> INSTANCIA = new AsegurarFormatoNombreUsuarioValidoRule();

	private static final int LONGITUD_MINIMA_NOMBRE = 2;
	private static final int LONGITUD_MAXIMA_NOMBRE = 60;

	private AsegurarFormatoNombreUsuarioValidoRule() {

	}

	public static Rule<UsuarioEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UsuarioEntidad usuario, DAOFactory daoFactory) {
		ValidadorAtributos.asegurarTexto(usuario.getNombre(), LONGITUD_MINIMA_NOMBRE, LONGITUD_MAXIMA_NOMBRE,
				CatalogoMensajes.Reglas.USUARIO_NOMBRE_NO_VALIDO);
		ValidadorAtributos.asegurarFormato(usuario.getNombre(), FormatosAtributos.SOLO_LETRAS_Y_ESPACIOS,
				CatalogoMensajes.Reglas.USUARIO_NOMBRE_NO_VALIDO);
		ValidadorAtributos.asegurarTexto(usuario.getApellido(), LONGITUD_MINIMA_NOMBRE, LONGITUD_MAXIMA_NOMBRE,
				CatalogoMensajes.Reglas.USUARIO_APELLIDO_NO_VALIDO);
		ValidadorAtributos.asegurarFormato(usuario.getApellido(), FormatosAtributos.SOLO_LETRAS_Y_ESPACIOS,
				CatalogoMensajes.Reglas.USUARIO_APELLIDO_NO_VALIDO);
	}
}
