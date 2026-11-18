package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarCorreoSinVerificarRule implements Rule<UsuarioEntidad> {

	private static final Rule<UsuarioEntidad> INSTANCIA = new AsegurarCorreoSinVerificarRule();

	private AsegurarCorreoSinVerificarRule() {

	}

	public static Rule<UsuarioEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UsuarioEntidad... datos) {
		var usuario = datos[0];

		ValidadorNegocio.asegurarCondicion(!usuario.isCorreoVerificado(),
				CatalogoMensajes.Reglas.CORREO_YA_VERIFICADO);
	}
}
