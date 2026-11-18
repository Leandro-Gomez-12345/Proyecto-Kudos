package co.edu.uco.kudos.negocio.negocio.reglas.impl.motivo;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarMotivoExisteRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarMotivoExisteRule();

	private AsegurarMotivoExisteRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		var entidad = daoFactory.obtenerMotivoDAO().consultarPorId(id);
		ValidadorNegocio.asegurarQueExiste(entidad.getId(), CatalogoMensajes.Reglas.MOTIVO_NO_EXISTE);
	}
}
