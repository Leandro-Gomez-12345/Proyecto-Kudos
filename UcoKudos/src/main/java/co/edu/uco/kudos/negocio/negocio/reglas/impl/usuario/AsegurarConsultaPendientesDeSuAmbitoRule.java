package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.AmbitoAutoridad;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarConsultaPendientesDeSuAmbitoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarConsultaPendientesDeSuAmbitoRule();

	private AsegurarConsultaPendientesDeSuAmbitoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void ejecutar(Object... datos) {
		var pendientes = (List<UsuarioEntidad>) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();
		ValidadorNegocio.asegurarCondicion(AmbitoAutoridad.tieneAmbito(daoFactory, idEjecutor),
				CatalogoMensajes.Reglas.SIN_REGISTROS_PENDIENTES_A_CARGO);
		pendientes.removeIf(persona -> !AmbitoAutoridad.decideRegistro(daoFactory, idEjecutor, persona.getId()));
	}
}
