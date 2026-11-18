package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosContenido;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarKudoPuedeVetarseDirectamenteRule implements Rule<UUID> {

	private static final Rule<UUID> INSTANCIA = new AsegurarKudoPuedeVetarseDirectamenteRule();

	private AsegurarKudoPuedeVetarseDirectamenteRule() {

	}

	public static Rule<UUID> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UUID id, DAOFactory daoFactory) {
		ValidadorNegocio.asegurarCondicion(EstadosContenido.kudoEstaEn(daoFactory, id,
				NombresCatalogo.EstadoContenido.VISIBLE,
				NombresCatalogo.EstadoContenido.REPORTADO),
				CatalogoMensajes.Reglas.KUDO_NO_PUEDE_VETARSE_DIRECTAMENTE);
	}
}
