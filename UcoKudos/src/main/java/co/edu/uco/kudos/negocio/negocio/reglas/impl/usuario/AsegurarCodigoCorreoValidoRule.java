package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.negocio.negocio.reglas.Rule;

public final class AsegurarCodigoCorreoValidoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarCodigoCorreoValidoRule();

	private static final String OPERACION_PENDIENTE =
			"Operación pendiente de implementar: no existe un objeto del modelo donde guardar "
			+ "el código de verificación del correo institucional";

	private AsegurarCodigoCorreoValidoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		throw new UnsupportedOperationException(OPERACION_PENDIENTE);
	}
}
