package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorUsuarios;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosContenido;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class MostrarAvisoEnReplicaVetadaRule implements Rule<ReplicaEntidad> {

	private static final Rule<ReplicaEntidad> INSTANCIA = new MostrarAvisoEnReplicaVetadaRule();

	private MostrarAvisoEnReplicaVetadaRule() {

	}

	public static Rule<ReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReplicaEntidad replica, DAOFactory daoFactory) {
		var estaVetado = EstadosContenido.estaEn(daoFactory, replica.getEstadoContenido().getId(),
				NombresCatalogo.EstadoContenido.VETADO);
		var puedeVerContenido = ConsultorUsuarios.tieneRol(daoFactory, ContextoEjecucion.obtenerIdEjecutor(),
				NombresCatalogo.Rol.RECTOR, NombresCatalogo.Rol.COORDINADOR_ACADEMICO);
		if (estaVetado && !puedeVerContenido) {
			replica.setContenido(CatalogoMensajes.Reglas.AVISO_CONTENIDO_VETADO);
		}
	}
}
