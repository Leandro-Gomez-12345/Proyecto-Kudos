package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CoordinacionProgramaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarProgramaAcademicoSinCoordinacionVigenteRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarProgramaAcademicoSinCoordinacionVigenteRule();

	private AsegurarProgramaAcademicoSinCoordinacionVigenteRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var coordinacion = (CoordinacionProgramaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var sinPersona = UtilUUID.obtenerUUIDDefecto();
		var vigentes = ConsultorVigencias.coordinacionesPrograma(daoFactory, sinPersona,
				coordinacion.getProgramaAcademico().getId());
		ValidadorNegocio.asegurarCondicion(vigentes.isEmpty(),
				CatalogoMensajes.Reglas.PROGRAMA_CON_COORDINADOR_VIGENTE);
	}
}
