package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CoordinacionFacultadEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class FinalizarCoordinacionFacultadAlSuspenderODesactivarRule
		implements Rule<CoordinacionFacultadEntidad> {

	private static final Rule<CoordinacionFacultadEntidad> INSTANCIA =
			new FinalizarCoordinacionFacultadAlSuspenderODesactivarRule();

	private FinalizarCoordinacionFacultadAlSuspenderODesactivarRule() {

	}

	public static Rule<CoordinacionFacultadEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CoordinacionFacultadEntidad coordinacion, DAOFactory daoFactory) {
		var fechaFin = UtilFecha.esValorDefecto(coordinacion.getFechaFin())
				? UtilFecha.obtenerFechaActual() : coordinacion.getFechaFin();
		var sinUnidad = UtilUUID.obtenerUUIDDefecto();
		for (var vigente : ConsultorVigencias.coordinacionesFacultad(daoFactory,
				coordinacion.getUsuario().getId(), sinUnidad)) {
			vigente.setFechaFin(fechaFin);
			daoFactory.obtenerCoordinacionFacultadDAO().actualizar(vigente.getId(), vigente);
		}
	}
}
