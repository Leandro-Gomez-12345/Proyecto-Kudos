package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarCelularSinVerificarRule implements Rule<UsuarioEntidad> {

	private static final Rule<UsuarioEntidad> INSTANCIA = new AsegurarCelularSinVerificarRule();

	private AsegurarCelularSinVerificarRule() {

	}

	public static Rule<UsuarioEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UsuarioEntidad... datos) {
		var usuario = datos[0];

		ValidadorNegocio.asegurarCondicion(!usuario.isCelularVerificado(),
				CatalogoMensajes.Reglas.CELULAR_YA_VERIFICADO);
	}
}
