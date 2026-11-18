package co.edu.uco.kudos.negocio.negocio.reglas.impl.programaacademico;

import co.edu.uco.kudos.entidad.ProgramaAcademicoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoProgramaAcademicoValidoRule implements Rule<ProgramaAcademicoEntidad> {

	private static final Rule<ProgramaAcademicoEntidad> INSTANCIA = new AsegurarFormatoProgramaAcademicoValidoRule();

	private AsegurarFormatoProgramaAcademicoValidoRule() {

	}

	public static Rule<ProgramaAcademicoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ProgramaAcademicoEntidad... datos) {
		var programa = datos[0];

		ValidadorAtributos.asegurarReferencia(programa.getFacultad().getId(),
				CatalogoMensajes.Reglas.PROGRAMA_FACULTAD_OBLIGATORIA);
		AsegurarFormatoNombreProgramaAcademicoValidoRule.obtenerInstancia().ejecutar(programa);
	}
}
