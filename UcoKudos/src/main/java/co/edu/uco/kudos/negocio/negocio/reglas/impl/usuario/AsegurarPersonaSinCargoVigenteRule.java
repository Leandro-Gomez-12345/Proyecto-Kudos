package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarPersonaSinCargoVigenteRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarPersonaSinCargoVigenteRule();

	private AsegurarPersonaSinCargoVigenteRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var idPersona = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		ValidadorNegocio.asegurarCondicion(!ConsultorVigencias.tieneCargoVigente(daoFactory, idPersona),
				CatalogoMensajes.Reglas.PERSONA_CON_CARGO_VIGENTE);
	}
}
