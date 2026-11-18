package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorUsuarios;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarPersonaTieneRolCoordinadorFacultadRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarPersonaTieneRolCoordinadorFacultadRule();

	private AsegurarPersonaTieneRolCoordinadorFacultadRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var id = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		ValidadorNegocio.asegurarCondicion(ConsultorUsuarios.tieneRol(daoFactory, id,
				NombresCatalogo.Rol.COORDINADOR_FACULTAD), CatalogoMensajes.Reglas.PERSONA_NO_ES_COORDINADOR_FACULTAD);
	}
}
