package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo;

import co.edu.uco.kudos.entidad.RevisionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoVetoDirectoKudoValidoRule implements Rule<RevisionKudoEntidad> {

	private static final Rule<RevisionKudoEntidad> INSTANCIA = new AsegurarFormatoVetoDirectoKudoValidoRule();

	private AsegurarFormatoVetoDirectoKudoValidoRule() {

	}

	public static Rule<RevisionKudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(RevisionKudoEntidad... datos) {
		var revision = datos[0];

		ValidadorAtributos.asegurarReferencia(revision.getKudo().getId(),
				CatalogoMensajes.Reglas.KUDO_VETO_OBLIGATORIO);
		AsegurarFormatoJustificacionRevisionKudoValidoRule.obtenerInstancia().ejecutar(revision);
	}
}
