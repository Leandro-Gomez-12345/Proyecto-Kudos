package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarUsuarioCoordinaElProgramaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarUsuarioCoordinaElProgramaRule();

	private AsegurarUsuarioCoordinaElProgramaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var idPrograma = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var coordinaciones = ConsultorVigencias.coordinacionesPrograma(daoFactory,
				ContextoEjecucion.obtenerIdEjecutor(), idPrograma);
		ValidadorNegocio.asegurarCondicion(!coordinaciones.isEmpty(),
				CatalogoMensajes.Reglas.USUARIO_NO_COORDINA_EL_PROGRAMA);
	}
}
