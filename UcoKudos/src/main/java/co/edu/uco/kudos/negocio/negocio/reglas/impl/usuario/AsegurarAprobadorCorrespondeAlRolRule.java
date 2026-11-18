package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.AmbitoAutoridad;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarAprobadorCorrespondeAlRolRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarAprobadorCorrespondeAlRolRule();

	private AsegurarAprobadorCorrespondeAlRolRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID idPersona, DAOFactory daoFactory) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();
		ValidadorNegocio.asegurarCondicion(AmbitoAutoridad.decideRegistro(daoFactory, idEjecutor, idPersona),
				CatalogoMensajes.Reglas.APROBADOR_NO_CORRESPONDE_AL_ROL);
	}
}
