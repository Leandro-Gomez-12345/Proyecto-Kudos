package co.edu.uco.kudos.negocio.negocio.reglas.impl.comun;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.EstadoContenidoEntidad;
import co.edu.uco.kudos.entidad.EstadoCuentaEntidad;
import co.edu.uco.kudos.entidad.EstadoRevisionEntidad;
import co.edu.uco.kudos.entidad.RolEntidad;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class ConsultorCatalogos {

	private ConsultorCatalogos() {

	}

	public static RolEntidad obtenerRol(DAOFactory daoFactory, String nombre) {
		var filtro = new RolEntidad();
		filtro.setNombre(nombre);
		return primeroOVacio(daoFactory.obtenerRolDAO().consultarPorFiltro(filtro), new RolEntidad());
	}

	public static EstadoCuentaEntidad obtenerEstadoCuenta(DAOFactory daoFactory, String nombre) {
		var filtro = new EstadoCuentaEntidad();
		filtro.setNombre(nombre);
		return primeroOVacio(daoFactory.obtenerEstadoCuentaDAO().consultarPorFiltro(filtro),
				new EstadoCuentaEntidad());
	}

	public static EstadoContenidoEntidad obtenerEstadoContenido(DAOFactory daoFactory, String nombre) {
		var filtro = new EstadoContenidoEntidad();
		filtro.setNombre(nombre);
		return primeroOVacio(daoFactory.obtenerEstadoContenidoDAO().consultarPorFiltro(filtro),
				new EstadoContenidoEntidad());
	}

	public static EstadoRevisionEntidad obtenerEstadoRevision(DAOFactory daoFactory, String nombre) {
		var filtro = new EstadoRevisionEntidad();
		filtro.setNombre(nombre);
		return primeroOVacio(daoFactory.obtenerEstadoRevisionDAO().consultarPorFiltro(filtro),
				new EstadoRevisionEntidad());
	}

	public static String nombreRol(DAOFactory daoFactory, UUID id) {
		return daoFactory.obtenerRolDAO().consultarPorId(id).getNombre();
	}

	public static String nombreEstadoCuenta(DAOFactory daoFactory, UUID id) {
		return daoFactory.obtenerEstadoCuentaDAO().consultarPorId(id).getNombre();
	}

	public static String nombreEstadoContenido(DAOFactory daoFactory, UUID id) {
		return daoFactory.obtenerEstadoContenidoDAO().consultarPorId(id).getNombre();
	}

	public static String nombreEstadoRevision(DAOFactory daoFactory, UUID id) {
		return daoFactory.obtenerEstadoRevisionDAO().consultarPorId(id).getNombre();
	}

	public static String nombreMotivo(DAOFactory daoFactory, UUID id) {
		return daoFactory.obtenerMotivoDAO().consultarPorId(id).getNombre();
	}

	public static boolean coincide(String nombre, String... esperados) {
		var nombreNormalizado = UtilTexto.normalizarParaComparar(nombre);
		for (var esperado : esperados) {
			if (nombreNormalizado.equals(UtilTexto.normalizarParaComparar(esperado))) {
				return true;
			}
		}
		return false;
	}

	private static <E> E primeroOVacio(List<E> coincidencias, E vacio) {
		return coincidencias.isEmpty() ? vacio : coincidencias.get(0);
	}
}
