package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosContenido;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class PonerKudoEnRevisionAlIniciarRevisionRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new PonerKudoEnRevisionAlIniciarRevisionRule();

	private PonerKudoEnRevisionAlIniciarRevisionRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var id = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		EstadosContenido.cambiarEstadoKudo(daoFactory, id, NombresCatalogo.EstadoContenido.EN_REVISION);
	}
}
