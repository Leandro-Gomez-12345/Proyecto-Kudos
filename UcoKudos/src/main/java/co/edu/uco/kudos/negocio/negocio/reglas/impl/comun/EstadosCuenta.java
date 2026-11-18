package co.edu.uco.kudos.negocio.negocio.reglas.impl.comun;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class EstadosCuenta {

	private EstadosCuenta() {

	}

	public static void asignar(DAOFactory daoFactory, UsuarioEntidad usuario, String nombreEstado) {
		usuario.setEstadoCuenta(ConsultorCatalogos.obtenerEstadoCuenta(daoFactory, nombreEstado));
	}

	public static boolean cumpleCondicionesActivacion(DAOFactory daoFactory, UsuarioEntidad usuario) {
		var nombreEstado = ConsultorCatalogos.nombreEstadoCuenta(daoFactory, usuario.getEstadoCuenta().getId());
		return ConsultorCatalogos.coincide(nombreEstado, NombresCatalogo.EstadoCuenta.APROBADA)
				&& usuario.isCorreoVerificado() && usuario.isCelularVerificado();
	}

	public static void devolverAAprobadaSiEstabaActiva(DAOFactory daoFactory, UsuarioEntidad usuario) {
		var nombreEstado = ConsultorCatalogos.nombreEstadoCuenta(daoFactory, usuario.getEstadoCuenta().getId());
		if (ConsultorCatalogos.coincide(nombreEstado, NombresCatalogo.EstadoCuenta.ACTIVA)) {
			asignar(daoFactory, usuario, NombresCatalogo.EstadoCuenta.APROBADA);
		}
	}
}
