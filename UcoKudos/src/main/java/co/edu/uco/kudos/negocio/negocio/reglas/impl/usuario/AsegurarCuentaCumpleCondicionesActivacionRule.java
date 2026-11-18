package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosCuenta;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarCuentaCumpleCondicionesActivacionRule implements Rule<UsuarioEntidad> {

	private static final Rule<UsuarioEntidad> INSTANCIA = new AsegurarCuentaCumpleCondicionesActivacionRule();

	private AsegurarCuentaCumpleCondicionesActivacionRule() {

	}

	public static Rule<UsuarioEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UsuarioEntidad usuario, DAOFactory daoFactory) {
		ValidadorNegocio.asegurarCondicion(EstadosCuenta.cumpleCondicionesActivacion(daoFactory, usuario),
				CatalogoMensajes.Reglas.CUENTA_NO_CUMPLE_CONDICIONES_ACTIVACION);
	}
}
