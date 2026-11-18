package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.UUID;

import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorUsuarios;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarUsuarioEsTitularRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarUsuarioEsTitularRule();

	private AsegurarUsuarioEsTitularRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID... datos) {
		var idUsuario = datos[0];

		ValidadorNegocio.asegurarCondicion(ConsultorUsuarios.esEjecutor(idUsuario),
				CatalogoMensajes.Reglas.USUARIO_NO_ES_TITULAR);
	}
}
