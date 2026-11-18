package co.edu.uco.kudos.negocio.negocio.reglas.impl.comun;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class EstadosContenido {

	private EstadosContenido() {

	}

	public static boolean kudoEstaEn(DAOFactory daoFactory, UUID idKudo, String... estados) {
		var kudo = daoFactory.obtenerKudoDAO().consultarPorId(idKudo);
		return !UtilUUID.esValorDefecto(kudo.getId()) && estaEn(daoFactory, kudo.getEstadoContenido().getId(), estados);
	}

	public static boolean replicaEstaEn(DAOFactory daoFactory, UUID idReplica, String... estados) {
		var replica = daoFactory.obtenerReplicaDAO().consultarPorId(idReplica);
		return !UtilUUID.esValorDefecto(replica.getId())
				&& estaEn(daoFactory, replica.getEstadoContenido().getId(), estados);
	}

	public static boolean estaEn(DAOFactory daoFactory, UUID idEstado, String... estados) {
		return ConsultorCatalogos.coincide(ConsultorCatalogos.nombreEstadoContenido(daoFactory, idEstado), estados);
	}

	public static void cambiarEstadoKudo(DAOFactory daoFactory, UUID idKudo, String nombreEstado) {
		var kudo = daoFactory.obtenerKudoDAO().consultarPorId(idKudo);
		kudo.setEstadoContenido(ConsultorCatalogos.obtenerEstadoContenido(daoFactory, nombreEstado));
		daoFactory.obtenerKudoDAO().actualizar(idKudo, kudo);
	}

	public static void cambiarEstadoReplica(DAOFactory daoFactory, UUID idReplica, String nombreEstado) {
		var replica = daoFactory.obtenerReplicaDAO().consultarPorId(idReplica);
		replica.setEstadoContenido(ConsultorCatalogos.obtenerEstadoContenido(daoFactory, nombreEstado));
		daoFactory.obtenerReplicaDAO().actualizar(idReplica, replica);
	}
}
