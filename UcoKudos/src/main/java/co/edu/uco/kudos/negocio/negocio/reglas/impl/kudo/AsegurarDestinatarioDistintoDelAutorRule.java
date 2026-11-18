package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import co.edu.uco.kudos.entidad.KudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarDestinatarioDistintoDelAutorRule implements Rule<KudoEntidad> {

	private static final Rule<KudoEntidad> INSTANCIA = new AsegurarDestinatarioDistintoDelAutorRule();

	private AsegurarDestinatarioDistintoDelAutorRule() {

	}

	public static Rule<KudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(KudoEntidad... datos) {
		var kudo = datos[0];

		var esElMismoAutor = kudo.getDestinatario().getId().equals(ContextoEjecucion.obtenerIdEjecutor());
		ValidadorNegocio.asegurarCondicion(!esElMismoAutor, CatalogoMensajes.Reglas.KUDO_PARA_SI_MISMO);
	}
}
