package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CoordinacionProgramaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarProgramaPerteneceAFacultadDelCoordinadorRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarProgramaPerteneceAFacultadDelCoordinadorRule();

	private AsegurarProgramaPerteneceAFacultadDelCoordinadorRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var coordinacion = (CoordinacionProgramaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var programa = daoFactory.obtenerProgramaAcademicoDAO()
				.consultarPorId(coordinacion.getProgramaAcademico().getId());
		var sinFacultad = UtilUUID.obtenerUUIDDefecto();
		var facultadesDelCoordinador = ConsultorVigencias.coordinacionesFacultad(daoFactory,
				ContextoEjecucion.obtenerIdEjecutor(), sinFacultad);
		var perteneceAFacultad = facultadesDelCoordinador.stream()
				.anyMatch(facultad -> facultad.getFacultad().getId().equals(programa.getFacultad().getId()));
		ValidadorNegocio.asegurarCondicion(perteneceAFacultad,
				CatalogoMensajes.Reglas.PROGRAMA_NO_PERTENECE_A_FACULTAD_DEL_COORDINADOR);
	}
}
