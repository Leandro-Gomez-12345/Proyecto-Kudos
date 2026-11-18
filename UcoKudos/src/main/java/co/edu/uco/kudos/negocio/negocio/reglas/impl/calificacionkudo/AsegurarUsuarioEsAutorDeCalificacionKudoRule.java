package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorUsuarios;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarUsuarioEsAutorDeCalificacionKudoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarUsuarioEsAutorDeCalificacionKudoRule();

	private AsegurarUsuarioEsAutorDeCalificacionKudoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var id = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var calificacion = daoFactory.obtenerCalificacionKudoDAO().consultarPorId(id);
		var esAutor = ConsultorUsuarios.esEjecutor(calificacion.getUsuario().getId());
		ValidadorNegocio.asegurarCondicion(esAutor, CatalogoMensajes.Reglas.CALIFICACION_NO_ES_PROPIA);
	}
}
