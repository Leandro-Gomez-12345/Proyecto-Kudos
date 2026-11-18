package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad;

import co.edu.uco.kudos.entidad.CoordinacionFacultadEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarCoordinacionFacultadVigenteRule implements Rule<CoordinacionFacultadEntidad> {

	private static final Rule<CoordinacionFacultadEntidad> INSTANCIA = new AsegurarCoordinacionFacultadVigenteRule();

	private AsegurarCoordinacionFacultadVigenteRule() {

	}

	public static Rule<CoordinacionFacultadEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CoordinacionFacultadEntidad... datos) {
		var coordinacion = datos[0];

		var estaVigente = !UtilUUID.esValorDefecto(coordinacion.getId())
				&& UtilFecha.esValorDefecto(coordinacion.getFechaFin());
		ValidadorNegocio.asegurarCondicion(estaVigente, CatalogoMensajes.Reglas.COORDINACION_YA_TERMINADA);
	}
}
