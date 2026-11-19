package co.edu.uco.kudos.negocio.negocio.reglas.impl.autenticacion;

import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.utilitarios.UtilCorreoInstitucional;

public final class AsegurarCorreoDeIngresoInstitucionalRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarCorreoDeIngresoInstitucionalRule();

	private AsegurarCorreoDeIngresoInstitucionalRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var correo = (String) datos[0];

		ValidadorNegocio.asegurarCondicion(UtilCorreoInstitucional.perteneceAlDominio(correo),
				CatalogoMensajes.Reglas.USUARIO_CORREO_NO_VALIDO);
	}
}
