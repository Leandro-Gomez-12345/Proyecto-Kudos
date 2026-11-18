package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorUsuarios;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarPersonaEsEstudianteODocenteRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarPersonaEsEstudianteODocenteRule();

	private AsegurarPersonaEsEstudianteODocenteRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		ValidadorNegocio.asegurarCondicion(ConsultorUsuarios.tieneRol(daoFactory, id,
				NombresCatalogo.Rol.ESTUDIANTE, NombresCatalogo.Rol.DOCENTE),
				CatalogoMensajes.Reglas.PERSONA_NO_ES_ESTUDIANTE_NI_DOCENTE);
	}
}
