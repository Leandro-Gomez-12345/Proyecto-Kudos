package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsignarDatosInicialesReplicaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsignarDatosInicialesReplicaRule();

	private AsignarDatosInicialesReplicaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var replica = (ReplicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		replica.getAutor().setId(ContextoEjecucion.obtenerIdEjecutor());
		replica.setFechaCreacion(UtilFechaHora.obtenerFechaHoraActual());
		replica.setEstadoContenido(ConsultorCatalogos.obtenerEstadoContenido(daoFactory,
				NombresCatalogo.EstadoContenido.CREADO));
	}
}
