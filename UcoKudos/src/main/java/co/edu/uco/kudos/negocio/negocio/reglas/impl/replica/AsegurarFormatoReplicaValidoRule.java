package co.edu.uco.kudos.negocio.negocio.reglas.impl.replica;

import co.edu.uco.kudos.entidad.ReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoReplicaValidoRule implements Rule<ReplicaEntidad> {

	private static final Rule<ReplicaEntidad> INSTANCIA = new AsegurarFormatoReplicaValidoRule();

	private static final int LONGITUD_MINIMA_CONTENIDO = 1;

	private AsegurarFormatoReplicaValidoRule() {

	}

	public static Rule<ReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(ReplicaEntidad... datos) {
		var replica = datos[0];

		ValidadorAtributos.asegurarReferencia(replica.getKudo().getId(),
				CatalogoMensajes.Reglas.REPLICA_KUDO_OBLIGATORIO);
		ValidadorAtributos.asegurarTextoSinLimite(replica.getContenido(), LONGITUD_MINIMA_CONTENIDO,
				CatalogoMensajes.Reglas.REPLICA_CONTENIDO_OBLIGATORIO);
	}
}
