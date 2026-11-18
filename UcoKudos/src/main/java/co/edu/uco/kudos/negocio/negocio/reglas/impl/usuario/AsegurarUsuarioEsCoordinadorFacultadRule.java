package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorUsuarios;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarUsuarioEsCoordinadorFacultadRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarUsuarioEsCoordinadorFacultadRule();

	private AsegurarUsuarioEsCoordinadorFacultadRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID idEjecutor, DAOFactory daoFactory) {
		ValidadorNegocio.asegurarCondicion(ConsultorUsuarios.tieneRol(daoFactory, idEjecutor,
				NombresCatalogo.Rol.COORDINADOR_FACULTAD),
				CatalogoMensajes.Reglas.USUARIO_NO_ES_COORDINADOR_FACULTAD);
	}
}
