package co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.VinculacionAcademicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class DesvincularAlRechazarODesactivarRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new DesvincularAlRechazarODesactivarRule();

	private DesvincularAlRechazarODesactivarRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var vinculacion = (VinculacionAcademicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

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
