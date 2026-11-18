package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.UsuarioConCodigo;

public final class AsegurarCodigoCelularValidoRule implements Rule<UsuarioConCodigo> {

	private static final Rule<UsuarioConCodigo> INSTANCIA = new AsegurarCodigoCelularValidoRule();

	private static final String OPERACION_PENDIENTE =
			"Operación pendiente de implementar: no existe un objeto del modelo donde guardar "
			+ "el código de verificación del celular";

	private AsegurarCodigoCelularValidoRule() {

	}

	public static Rule<UsuarioConCodigo> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UsuarioConCodigo verificacion, DAOFactory daoFactory) {
		throw new UnsupportedOperationException(OPERACION_PENDIENTE);
	}
}
