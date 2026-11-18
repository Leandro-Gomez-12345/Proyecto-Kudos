package co.edu.uco.kudos.negocio.negocio.reglas.impl.comun;

import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class EstadosReporte {

	private EstadosReporte() {

	}

	public static String deducir(boolean sinRevision, String nombreEstadoRevision) {
		if (sinRevision) {
			return NombresCatalogo.EstadoReporte.PENDIENTE;
		}
		if (ConsultorCatalogos.coincide(nombreEstadoRevision, NombresCatalogo.EstadoRevision.CONTENIDO_VETADO)) {
			return NombresCatalogo.EstadoReporte.ACEPTADO;
		}
		if (ConsultorCatalogos.coincide(nombreEstadoRevision, NombresCatalogo.EstadoRevision.CONTENIDO_LIBERADO)) {
			return NombresCatalogo.EstadoReporte.RECHAZADO;
		}
		return NombresCatalogo.EstadoReporte.EN_REVISION;
	}

	public static boolean coincide(String estadoDeducido, String estadoSolicitado) {
		return UtilTexto.normalizarParaComparar(estadoDeducido).equals(
				UtilTexto.normalizarParaComparar(estadoSolicitado));
	}
}
