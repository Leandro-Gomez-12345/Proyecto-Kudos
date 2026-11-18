package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CoordinacionFacultadEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarPersonaSinOtraCoordinacionFacultadRule implements Rule<CoordinacionFacultadEntidad> {

	private static final Rule<CoordinacionFacultadEntidad> INSTANCIA =
			new AsegurarPersonaSinOtraCoordinacionFacultadRule();

	private AsegurarPersonaSinOtraCoordinacionFacultadRule() {

	}

	public static Rule<CoordinacionFacultadEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CoordinacionFacultadEntidad coordinacion, DAOFactory daoFactory) {
		var sinUnidad = UtilUUID.obtenerUUIDDefecto();
		var idPersona = coordinacion.getUsuario().getId();
		var coordinaOtraUnidad = !ConsultorVigencias.coordinacionesFacultad(daoFactory, idPersona,
				sinUnidad).isEmpty()
				|| !ConsultorVigencias.coordinacionesPrograma(daoFactory, idPersona, sinUnidad).isEmpty();
		ValidadorNegocio.asegurarCondicion(!coordinaOtraUnidad,
				CatalogoMensajes.Reglas.PERSONA_YA_COORDINA_OTRA_UNIDAD);
	}
}
