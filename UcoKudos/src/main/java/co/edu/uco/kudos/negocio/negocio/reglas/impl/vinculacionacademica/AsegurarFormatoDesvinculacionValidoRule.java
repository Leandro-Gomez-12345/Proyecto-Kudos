package co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica;

import co.edu.uco.kudos.entidad.VinculacionAcademicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;

public final class AsegurarFormatoDesvinculacionValidoRule implements Rule<VinculacionAcademicaEntidad> {

	private static final Rule<VinculacionAcademicaEntidad> INSTANCIA = new AsegurarFormatoDesvinculacionValidoRule();

	private AsegurarFormatoDesvinculacionValidoRule() {

	}

	public static Rule<VinculacionAcademicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(VinculacionAcademicaEntidad... datos) {
		var vinculacion = datos[0];

		var fechaValida = !UtilFecha.esValorDefecto(vinculacion.getFechaDesvinculacion())
				&& !UtilFecha.esAnterior(vinculacion.getFechaDesvinculacion(), vinculacion.getFechaVinculacion());
		ValidadorNegocio.asegurarCondicion(fechaValida, CatalogoMensajes.Reglas.DESVINCULACION_FECHA_NO_VALIDA);
	}
}
