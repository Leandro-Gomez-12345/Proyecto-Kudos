package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosCuenta;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.utilitarios.UtilBooleano;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsignarEstadoInicialUsuarioRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsignarEstadoInicialUsuarioRule();

	private AsignarEstadoInicialUsuarioRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var usuario = (UsuarioEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		EstadosCuenta.asignar(daoFactory, usuario, NombresCatalogo.EstadoCuenta.PENDIENTE_APROBACION);
		usuario.setCorreoVerificado(UtilBooleano.NO);
		usuario.setCelularVerificado(UtilBooleano.NO);
		usuario.setFechaRegistro(UtilFechaHora.obtenerFechaHoraActual());
	}
}
