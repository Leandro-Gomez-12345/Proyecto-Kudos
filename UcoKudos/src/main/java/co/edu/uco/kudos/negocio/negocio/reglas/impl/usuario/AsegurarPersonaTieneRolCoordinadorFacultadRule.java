package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorUsuarios;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarPersonaTieneRolCoordinadorFacultadRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarPersonaTieneRolCoordinadorFacultadRule();

	private AsegurarPersonaTieneRolCoordinadorFacultadRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		ValidadorNegocio.asegurarCondicion(ConsultorUsuarios.tieneRol(daoFactory, id,
				NombresCatalogo.Rol.COORDINADOR_FACULTAD), CatalogoMensajes.Reglas.PERSONA_NO_ES_COORDINADOR_FACULTAD);
	}
}
