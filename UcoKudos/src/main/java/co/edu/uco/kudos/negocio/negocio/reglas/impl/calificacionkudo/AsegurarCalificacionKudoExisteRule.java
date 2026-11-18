package co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarCalificacionKudoExisteRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarCalificacionKudoExisteRule();

	private AsegurarCalificacionKudoExisteRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var id = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var entidad = daoFactory.obtenerCalificacionKudoDAO().consultarPorId(id);
		ValidadorNegocio.asegurarQueExiste(entidad.getId(), CatalogoMensajes.Reglas.CALIFICACION_NO_EXISTE);
	}
}
