package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo;

import co.edu.uco.kudos.entidad.RevisionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.FormatosAtributos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoJustificacionRevisionKudoValidoRule implements Rule<RevisionKudoEntidad> {

	private static final Rule<RevisionKudoEntidad> INSTANCIA = new AsegurarFormatoJustificacionRevisionKudoValidoRule();

	private static final int LONGITUD_MINIMA_JUSTIFICACION = 10;
	private static final int LONGITUD_MAXIMA_JUSTIFICACION = 1000;

	private AsegurarFormatoJustificacionRevisionKudoValidoRule() {

	}

	public static Rule<RevisionKudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(RevisionKudoEntidad... datos) {
		var revision = datos[0];

		ValidadorAtributos.asegurarTexto(revision.getJustificacion(), LONGITUD_MINIMA_JUSTIFICACION,
				LONGITUD_MAXIMA_JUSTIFICACION, CatalogoMensajes.Reglas.REVISION_JUSTIFICACION_NO_VALIDA);
		ValidadorAtributos.asegurarFormato(revision.getJustificacion(), FormatosAtributos.TEXTO_CON_PUNTUACION,
				CatalogoMensajes.Reglas.REVISION_JUSTIFICACION_NO_VALIDA);
	}
}
