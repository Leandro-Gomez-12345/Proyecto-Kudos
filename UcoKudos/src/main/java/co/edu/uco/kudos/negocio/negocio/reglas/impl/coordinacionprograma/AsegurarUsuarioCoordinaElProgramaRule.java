package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarUsuarioCoordinaElProgramaRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarUsuarioCoordinaElProgramaRule();

	private AsegurarUsuarioCoordinaElProgramaRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID idPrograma, DAOFactory daoFactory) {
		var coordinaciones = ConsultorVigencias.coordinacionesPrograma(daoFactory,
				ContextoEjecucion.obtenerIdEjecutor(), idPrograma);
		ValidadorNegocio.asegurarCondicion(!coordinaciones.isEmpty(),
				CatalogoMensajes.Reglas.USUARIO_NO_COORDINA_EL_PROGRAMA);
	}
}
