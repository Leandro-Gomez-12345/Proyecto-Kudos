package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.AmbitoAutoridad;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarAutoridadGestionaPersonaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarAutoridadGestionaPersonaRule();

	private AsegurarAutoridadGestionaPersonaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var idPersona = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();
		ValidadorNegocio.asegurarCondicion(AmbitoAutoridad.gestiona(daoFactory, idEjecutor, idPersona),
				CatalogoMensajes.Reglas.AUTORIDAD_NO_GESTIONA_PERSONA);
	}
}
