package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReporteReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.FormatosAtributos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class AsegurarFormatoReporteReplicaValidoRule implements Rule<ReporteReplicaEntidad> {

	private static final Rule<ReporteReplicaEntidad> INSTANCIA = new AsegurarFormatoReporteReplicaValidoRule();

	private static final int LONGITUD_MINIMA_OBLIGATORIA = 1;
	private static final int LONGITUD_MINIMA_OPCIONAL = 0;
	private static final int LONGITUD_MAXIMA = 500;

	private AsegurarFormatoReporteReplicaValidoRule() {

	}

	public static Rule<ReporteReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReporteReplicaEntidad reporte, DAOFactory daoFactory) {
		ValidadorAtributos.asegurarReferencia(reporte.getReplica().getId(),
				CatalogoMensajes.Reglas.REPORTE_REPLICA_OBLIGATORIO);
		ValidadorAtributos.asegurarReferencia(reporte.getMotivo().getId(),
				CatalogoMensajes.Reglas.REPORTE_MOTIVO_OBLIGATORIO);
		var nombreMotivo = ConsultorCatalogos.nombreMotivo(daoFactory, reporte.getMotivo().getId());
		var esOtro = ConsultorCatalogos.coincide(nombreMotivo, NombresCatalogo.Motivo.OTRO);
		var longitudMinima = esOtro ? LONGITUD_MINIMA_OBLIGATORIA : LONGITUD_MINIMA_OPCIONAL;
		ValidadorAtributos.asegurarTexto(reporte.getDescripcion(), longitudMinima, LONGITUD_MAXIMA,
				CatalogoMensajes.Reglas.REPORTE_DESCRIPCION_NO_VALIDA);
		if (!UtilTexto.esVacia(reporte.getDescripcion())) {
			ValidadorAtributos.asegurarFormato(reporte.getDescripcion(), FormatosAtributos.TEXTO_CON_PUNTUACION,
					CatalogoMensajes.Reglas.REPORTE_DESCRIPCION_NO_VALIDA);
		}
	}
}
