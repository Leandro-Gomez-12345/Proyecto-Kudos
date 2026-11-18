package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarPersonaSinCargoVigenteRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarPersonaSinCargoVigenteRule();

	private AsegurarPersonaSinCargoVigenteRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID idPersona, DAOFactory daoFactory) {
		ValidadorNegocio.asegurarCondicion(!ConsultorVigencias.tieneCargoVigente(daoFactory, idPersona),
				CatalogoMensajes.Reglas.PERSONA_CON_CARGO_VIGENTE);
	}
}
