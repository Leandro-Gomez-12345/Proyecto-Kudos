package co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.RevisionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.FormatosAtributos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoJustificacionRevisionReplicaValidoRule implements Rule<RevisionReplicaEntidad> {

	private static final Rule<RevisionReplicaEntidad> INSTANCIA =
			new AsegurarFormatoJustificacionRevisionReplicaValidoRule();

	private static final int LONGITUD_MINIMA_JUSTIFICACION = 10;
	private static final int LONGITUD_MAXIMA_JUSTIFICACION = 1000;

	private AsegurarFormatoJustificacionRevisionReplicaValidoRule() {

	}

	public static Rule<RevisionReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(RevisionReplicaEntidad revision, DAOFactory daoFactory) {
		ValidadorAtributos.asegurarTexto(revision.getJustificacion(), LONGITUD_MINIMA_JUSTIFICACION,
				LONGITUD_MAXIMA_JUSTIFICACION, CatalogoMensajes.Reglas.REVISION_JUSTIFICACION_NO_VALIDA);
		ValidadorAtributos.asegurarFormato(revision.getJustificacion(), FormatosAtributos.TEXTO_CON_PUNTUACION,
				CatalogoMensajes.Reglas.REVISION_JUSTIFICACION_NO_VALIDA);
	}
}
