package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CoordinacionProgramaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorVigencias;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class FinalizarCoordinacionProgramaAlSuspenderODesactivarRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new FinalizarCoordinacionProgramaAlSuspenderODesactivarRule();

	private FinalizarCoordinacionProgramaAlSuspenderODesactivarRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var coordinacion = (CoordinacionProgramaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var fechaFin = UtilFecha.esValorDefecto(coordinacion.getFechaFin())
				? UtilFecha.obtenerFechaActual() : coordinacion.getFechaFin();
		var sinUnidad = UtilUUID.obtenerUUIDDefecto();
		for (var vigente : ConsultorVigencias.coordinacionesPrograma(daoFactory,
				coordinacion.getUsuario().getId(), sinUnidad)) {
			vigente.setFechaFin(fechaFin);
			daoFactory.obtenerCoordinacionProgramaDAO().actualizar(vigente.getId(), vigente);
		}
	}
}
