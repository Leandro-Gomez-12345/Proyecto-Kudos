package co.edu.uco.kudos.negocio.negocio.reglas.impl.comun;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ConsultorUsuarios {

	private ConsultorUsuarios() {

	}

	public static boolean tieneRol(DAOFactory daoFactory, UUID idUsuario, String... roles) {
		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(idUsuario);
		var nombreRol = ConsultorCatalogos.nombreRol(daoFactory, usuario.getRol().getId());
		return !UtilUUID.esValorDefecto(usuario.getId()) && ConsultorCatalogos.coincide(nombreRol, roles);
	}

	public static boolean tieneEstadoCuenta(DAOFactory daoFactory, UUID idUsuario, String... estados) {
		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(idUsuario);
		var nombreEstado = ConsultorCatalogos.nombreEstadoCuenta(daoFactory, usuario.getEstadoCuenta().getId());
		return !UtilUUID.esValorDefecto(usuario.getId()) && ConsultorCatalogos.coincide(nombreEstado, estados);
	}

	public static boolean esEjecutor(UUID idUsuario) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();
		return !UtilUUID.esValorDefecto(idEjecutor) && idEjecutor.equals(idUsuario);
	}
}
