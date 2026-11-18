package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CoordinacionFacultadEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarFacultadSinCoordinacionVigenteRule implements Rule<CoordinacionFacultadEntidad> {

	private static final Rule<CoordinacionFacultadEntidad> INSTANCIA = new AsegurarFacultadSinCoordinacionVigenteRule();

	private AsegurarFacultadSinCoordinacionVigenteRule() {

	}

	public static Rule<CoordinacionFacultadEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CoordinacionFacultadEntidad coordinacion, DAOFactory daoFactory) {
		var sinPersona = UtilUUID.obtenerUUIDDefecto();
		var vigentes = ConsultorVigencias.coordinacionesFacultad(daoFactory, sinPersona,
				coordinacion.getFacultad().getId());
		ValidadorNegocio.asegurarCondicion(vigentes.isEmpty(),
				CatalogoMensajes.Reglas.FACULTAD_CON_COORDINADOR_VIGENTE);
	}
}
