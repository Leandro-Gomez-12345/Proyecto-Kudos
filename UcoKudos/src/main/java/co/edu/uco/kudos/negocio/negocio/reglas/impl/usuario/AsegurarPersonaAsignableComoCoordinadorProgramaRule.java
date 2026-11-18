package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorUsuarios;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarPersonaAsignableComoCoordinadorProgramaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarPersonaAsignableComoCoordinadorProgramaRule();

	private AsegurarPersonaAsignableComoCoordinadorProgramaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var id = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var esCoordinadorDePrograma = ConsultorUsuarios.tieneRol(daoFactory, id,
				NombresCatalogo.Rol.COORDINADOR_PROGRAMA);
		var esAsignable = ConsultorUsuarios.tieneEstadoCuenta(daoFactory, id,
				NombresCatalogo.EstadoCuenta.PENDIENTE_APROBACION, NombresCatalogo.EstadoCuenta.ACTIVA);
		ValidadorNegocio.asegurarCondicion(esCoordinadorDePrograma && esAsignable,
				CatalogoMensajes.Reglas.PERSONA_NO_ASIGNABLE_COMO_COORDINADOR_PROGRAMA);
	}
}
