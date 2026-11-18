package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad;

import co.edu.uco.kudos.entidad.CoordinacionFacultadEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.FormatosAtributos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;

public final class AsegurarFormatoCoordinacionFacultadValidoRule implements Rule<CoordinacionFacultadEntidad> {

	private static final Rule<CoordinacionFacultadEntidad> INSTANCIA =
			new AsegurarFormatoCoordinacionFacultadValidoRule();

	private AsegurarFormatoCoordinacionFacultadValidoRule() {

	}

	public static Rule<CoordinacionFacultadEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CoordinacionFacultadEntidad... datos) {
		var coordinacion = datos[0];

		ValidadorAtributos.asegurarReferencia(coordinacion.getUsuario().getId(),
				CatalogoMensajes.Reglas.COORDINACION_PERSONA_OBLIGATORIA);
		ValidadorAtributos.asegurarReferencia(coordinacion.getFacultad().getId(),
				CatalogoMensajes.Reglas.FACULTAD_OBLIGATORIA);
		ValidadorAtributos.asegurarFechaEnRango(coordinacion.getFechaInicio(),
				FormatosAtributos.FECHA_FUNDACION_UCO, UtilFecha.obtenerFechaActual(),
				CatalogoMensajes.Reglas.COORDINACION_FECHA_INICIO_NO_VALIDA);
	}
}
