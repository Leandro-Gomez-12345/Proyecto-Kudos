package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.AmbitoAutoridad;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarConsultaPendientesDeSuAmbitoRule implements Rule<List<UsuarioEntidad>> {

	private static final Rule<List<UsuarioEntidad>> INSTANCIA = new AsegurarConsultaPendientesDeSuAmbitoRule();

	private AsegurarConsultaPendientesDeSuAmbitoRule() {

	}

	public static Rule<List<UsuarioEntidad>> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(List<UsuarioEntidad> pendientes, DAOFactory daoFactory) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();
		ValidadorNegocio.asegurarCondicion(AmbitoAutoridad.tieneAmbito(daoFactory, idEjecutor),
				CatalogoMensajes.Reglas.SIN_REGISTROS_PENDIENTES_A_CARGO);
		pendientes.removeIf(persona -> !AmbitoAutoridad.decideRegistro(daoFactory, idEjecutor, persona.getId()));
	}
}
