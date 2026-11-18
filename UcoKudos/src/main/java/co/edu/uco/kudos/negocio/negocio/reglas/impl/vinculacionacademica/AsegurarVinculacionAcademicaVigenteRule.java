package co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica;

import co.edu.uco.kudos.entidad.VinculacionAcademicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AsegurarVinculacionAcademicaVigenteRule implements Rule<VinculacionAcademicaEntidad> {

	private static final Rule<VinculacionAcademicaEntidad> INSTANCIA = new AsegurarVinculacionAcademicaVigenteRule();

	private AsegurarVinculacionAcademicaVigenteRule() {

	}

	public static Rule<VinculacionAcademicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(VinculacionAcademicaEntidad... datos) {
		var vinculacion = datos[0];

		var estaVigente = !UtilUUID.esValorDefecto(vinculacion.getId())
				&& UtilFecha.esValorDefecto(vinculacion.getFechaDesvinculacion());
		ValidadorNegocio.asegurarCondicion(estaVigente, CatalogoMensajes.Reglas.VINCULACION_YA_TERMINADA);
	}
}
