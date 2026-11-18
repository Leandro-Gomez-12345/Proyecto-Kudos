package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CoordinacionFacultadEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarFacultadSinCoordinacionVigenteRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarFacultadSinCoordinacionVigenteRule();

	private AsegurarFacultadSinCoordinacionVigenteRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var coordinacion = (CoordinacionFacultadEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var sinPersona = UtilUUID.obtenerUUIDDefecto();
		var vigentes = ConsultorVigencias.coordinacionesFacultad(daoFactory, sinPersona,
				coordinacion.getFacultad().getId());
		ValidadorNegocio.asegurarCondicion(vigentes.isEmpty(),
				CatalogoMensajes.Reglas.FACULTAD_CON_COORDINADOR_VIGENTE);
	}
}
