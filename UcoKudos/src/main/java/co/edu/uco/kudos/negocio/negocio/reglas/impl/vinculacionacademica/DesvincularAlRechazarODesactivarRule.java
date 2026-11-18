package co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.VinculacionAcademicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class DesvincularAlRechazarODesactivarRule implements Rule<VinculacionAcademicaEntidad> {

	private static final Rule<VinculacionAcademicaEntidad> INSTANCIA = new DesvincularAlRechazarODesactivarRule();

	private DesvincularAlRechazarODesactivarRule() {

	}

	public static Rule<VinculacionAcademicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(VinculacionAcademicaEntidad vinculacion, DAOFactory daoFactory) {
		var fechaDesvinculacion = UtilFecha.esValorDefecto(vinculacion.getFechaDesvinculacion())
				? UtilFecha.obtenerFechaActual() : vinculacion.getFechaDesvinculacion();
		var sinPrograma = UtilUUID.obtenerUUIDDefecto();
		for (var vigente : ConsultorVigencias.vinculaciones(daoFactory, vinculacion.getUsuario().getId(),
				sinPrograma)) {
			vigente.setFechaDesvinculacion(fechaDesvinculacion);
			daoFactory.obtenerVinculacionAcademicaDAO().actualizar(vigente.getId(), vigente);
		}
	}
}
