package co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.VinculacionAcademicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarPersonaSinVinculoVigenteEnProgramaRule implements Rule<VinculacionAcademicaEntidad> {

	private static final Rule<VinculacionAcademicaEntidad> INSTANCIA =
			new AsegurarPersonaSinVinculoVigenteEnProgramaRule();

	private AsegurarPersonaSinVinculoVigenteEnProgramaRule() {

	}

	public static Rule<VinculacionAcademicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(VinculacionAcademicaEntidad vinculacion, DAOFactory daoFactory) {
		var vigentes = ConsultorVigencias.vinculaciones(daoFactory, vinculacion.getUsuario().getId(),
				vinculacion.getProgramaAcademico().getId());
		ValidadorNegocio.asegurarCondicion(vigentes.isEmpty(), CatalogoMensajes.Reglas.PERSONA_YA_VINCULADA);
	}
}
