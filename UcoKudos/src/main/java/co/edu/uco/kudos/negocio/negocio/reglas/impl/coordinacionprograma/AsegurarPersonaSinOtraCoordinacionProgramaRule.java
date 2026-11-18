package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CoordinacionProgramaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarPersonaSinOtraCoordinacionProgramaRule implements Rule<CoordinacionProgramaEntidad> {

	private static final Rule<CoordinacionProgramaEntidad> INSTANCIA =
			new AsegurarPersonaSinOtraCoordinacionProgramaRule();

	private AsegurarPersonaSinOtraCoordinacionProgramaRule() {

	}

	public static Rule<CoordinacionProgramaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CoordinacionProgramaEntidad coordinacion, DAOFactory daoFactory) {
		var sinUnidad = UtilUUID.obtenerUUIDDefecto();
		var idPersona = coordinacion.getUsuario().getId();
		var coordinaOtraUnidad = !ConsultorVigencias.coordinacionesFacultad(daoFactory, idPersona,
				sinUnidad).isEmpty()
				|| !ConsultorVigencias.coordinacionesPrograma(daoFactory, idPersona, sinUnidad).isEmpty();
		ValidadorNegocio.asegurarCondicion(!coordinaOtraUnidad,
				CatalogoMensajes.Reglas.PERSONA_YA_COORDINA_OTRA_UNIDAD);
	}
}
