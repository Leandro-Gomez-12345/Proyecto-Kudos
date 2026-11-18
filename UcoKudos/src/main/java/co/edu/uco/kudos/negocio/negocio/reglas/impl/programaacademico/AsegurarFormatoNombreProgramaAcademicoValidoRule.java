package co.edu.uco.kudos.negocio.negocio.reglas.impl.programaacademico;

import co.edu.uco.kudos.entidad.ProgramaAcademicoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.FormatosAtributos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoNombreProgramaAcademicoValidoRule implements Rule<ProgramaAcademicoEntidad> {

	private static final Rule<ProgramaAcademicoEntidad> INSTANCIA =
			new AsegurarFormatoNombreProgramaAcademicoValidoRule();

	private static final int LONGITUD_MINIMA_NOMBRE = 5;
	private static final int LONGITUD_MAXIMA_NOMBRE = 100;

	private AsegurarFormatoNombreProgramaAcademicoValidoRule() {

	}

	public static Rule<ProgramaAcademicoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ProgramaAcademicoEntidad... datos) {
		var programa = datos[0];

		ValidadorAtributos.asegurarTexto(programa.getNombre(), LONGITUD_MINIMA_NOMBRE, LONGITUD_MAXIMA_NOMBRE,
				CatalogoMensajes.Reglas.PROGRAMA_NOMBRE_NO_VALIDO);
		ValidadorAtributos.asegurarFormato(programa.getNombre(), FormatosAtributos.SOLO_LETRAS_Y_ESPACIOS,
				CatalogoMensajes.Reglas.PROGRAMA_NOMBRE_NO_VALIDO);
	}
}
