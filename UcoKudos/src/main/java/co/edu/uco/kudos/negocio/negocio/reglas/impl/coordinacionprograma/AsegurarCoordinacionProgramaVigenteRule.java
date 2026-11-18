package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma;

import co.edu.uco.kudos.entidad.CoordinacionProgramaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarCoordinacionProgramaVigenteRule implements Rule<CoordinacionProgramaEntidad> {

	private static final Rule<CoordinacionProgramaEntidad> INSTANCIA = new AsegurarCoordinacionProgramaVigenteRule();

	private AsegurarCoordinacionProgramaVigenteRule() {

	}

	public static Rule<CoordinacionProgramaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CoordinacionProgramaEntidad... datos) {
		var coordinacion = datos[0];

		var estaVigente = !UtilUUID.esValorDefecto(coordinacion.getId())
				&& UtilFecha.esValorDefecto(coordinacion.getFechaFin());
		ValidadorNegocio.asegurarCondicion(estaVigente, CatalogoMensajes.Reglas.COORDINACION_YA_TERMINADA);
	}
}
