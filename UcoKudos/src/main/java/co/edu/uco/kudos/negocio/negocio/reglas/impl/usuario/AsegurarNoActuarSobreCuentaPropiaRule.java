package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.UUID;

import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorUsuarios;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarNoActuarSobreCuentaPropiaRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarNoActuarSobreCuentaPropiaRule();

	private AsegurarNoActuarSobreCuentaPropiaRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID... datos) {
		var idPersona = datos[0];

		ValidadorNegocio.asegurarCondicion(!ConsultorUsuarios.esEjecutor(idPersona),
				CatalogoMensajes.Reglas.NO_PUEDE_ACTUAR_SOBRE_CUENTA_PROPIA);
	}
}
