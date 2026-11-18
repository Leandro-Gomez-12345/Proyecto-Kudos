package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorUsuarios;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarUsuarioEsAutorDeCalificacionKudoRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarUsuarioEsAutorDeCalificacionKudoRule();

	private AsegurarUsuarioEsAutorDeCalificacionKudoRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		var calificacion = daoFactory.obtenerCalificacionKudoDAO().consultarPorId(id);
		var esAutor = ConsultorUsuarios.esEjecutor(calificacion.getUsuario().getId());
		ValidadorNegocio.asegurarCondicion(esAutor, CatalogoMensajes.Reglas.CALIFICACION_NO_ES_PROPIA);
	}
}
