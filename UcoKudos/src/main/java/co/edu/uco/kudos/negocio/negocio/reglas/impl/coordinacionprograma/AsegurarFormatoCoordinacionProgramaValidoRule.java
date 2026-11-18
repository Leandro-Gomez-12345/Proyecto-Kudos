package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma;

import co.edu.uco.kudos.entidad.CoordinacionProgramaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.FormatosAtributos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;

public final class AsegurarFormatoCoordinacionProgramaValidoRule implements Rule<CoordinacionProgramaEntidad> {

	private static final Rule<CoordinacionProgramaEntidad> INSTANCIA =
			new AsegurarFormatoCoordinacionProgramaValidoRule();

	private AsegurarFormatoCoordinacionProgramaValidoRule() {

	}

	public static Rule<CoordinacionProgramaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CoordinacionProgramaEntidad... datos) {
		var coordinacion = datos[0];

		ValidadorAtributos.asegurarReferencia(coordinacion.getUsuario().getId(),
				CatalogoMensajes.Reglas.COORDINACION_PERSONA_OBLIGATORIA);
		ValidadorAtributos.asegurarReferencia(coordinacion.getProgramaAcademico().getId(),
				CatalogoMensajes.Reglas.PROGRAMA_OBLIGATORIA);
		ValidadorAtributos.asegurarFechaEnRango(coordinacion.getFechaInicio(),
				FormatosAtributos.FECHA_FUNDACION_UCO, UtilFecha.obtenerFechaActual(),
				CatalogoMensajes.Reglas.COORDINACION_FECHA_INICIO_NO_VALIDA);
	}
}
