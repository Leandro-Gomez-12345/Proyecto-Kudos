package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosContenido;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class LiberarKudoAlCerrarRevisionSinVetoRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new LiberarKudoAlCerrarRevisionSinVetoRule();

	private LiberarKudoAlCerrarRevisionSinVetoRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		EstadosContenido.cambiarEstadoKudo(daoFactory, id, NombresCatalogo.EstadoContenido.VISIBLE);
	}
}
