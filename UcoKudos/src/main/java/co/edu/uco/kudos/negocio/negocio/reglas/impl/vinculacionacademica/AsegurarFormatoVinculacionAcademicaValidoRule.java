package co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.VinculacionAcademicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.FormatosAtributos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;

public final class AsegurarFormatoVinculacionAcademicaValidoRule implements Rule<VinculacionAcademicaEntidad> {

	private static final Rule<VinculacionAcademicaEntidad> INSTANCIA =
			new AsegurarFormatoVinculacionAcademicaValidoRule();

	private AsegurarFormatoVinculacionAcademicaValidoRule() {

	}

	public static Rule<VinculacionAcademicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(VinculacionAcademicaEntidad vinculacion, DAOFactory daoFactory) {
		ValidadorAtributos.asegurarReferencia(vinculacion.getUsuario().getId(),
				CatalogoMensajes.Reglas.VINCULACION_PERSONA_OBLIGATORIA);
		ValidadorAtributos.asegurarReferencia(vinculacion.getProgramaAcademico().getId(),
				CatalogoMensajes.Reglas.VINCULACION_PROGRAMA_OBLIGATORIO);
		ValidadorAtributos.asegurarFechaEnRango(vinculacion.getFechaVinculacion(),
				FormatosAtributos.FECHA_FUNDACION_UCO, UtilFecha.obtenerFechaActual(),
				CatalogoMensajes.Reglas.VINCULACION_FECHA_NO_VALIDA);
	}
}
