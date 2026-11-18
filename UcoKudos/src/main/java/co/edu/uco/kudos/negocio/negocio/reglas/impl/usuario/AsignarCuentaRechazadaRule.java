package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosCuenta;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsignarCuentaRechazadaRule implements Rule<UsuarioEntidad> {

	private static final Rule<UsuarioEntidad> INSTANCIA = new AsignarCuentaRechazadaRule();

	private AsignarCuentaRechazadaRule() {

	}

	public static Rule<UsuarioEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UsuarioEntidad usuario, DAOFactory daoFactory) {
		EstadosCuenta.asignar(daoFactory, usuario, NombresCatalogo.EstadoCuenta.RECHAZADA);
	}
}
