package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.KudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorUsuarios;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosContenido;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class MostrarAvisoEnKudoVetadoRule implements Rule<KudoEntidad> {

	private static final Rule<KudoEntidad> INSTANCIA = new MostrarAvisoEnKudoVetadoRule();

	private MostrarAvisoEnKudoVetadoRule() {

	}

	public static Rule<KudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(KudoEntidad kudo, DAOFactory daoFactory) {
		var estaVetado = EstadosContenido.estaEn(daoFactory, kudo.getEstadoContenido().getId(),
				NombresCatalogo.EstadoContenido.VETADO);
		var puedeVerContenido = ConsultorUsuarios.tieneRol(daoFactory, ContextoEjecucion.obtenerIdEjecutor(),
				NombresCatalogo.Rol.RECTOR, NombresCatalogo.Rol.COORDINADOR_ACADEMICO);
		if (estaVetado && !puedeVerContenido) {
			kudo.setContenido(CatalogoMensajes.Reglas.AVISO_CONTENIDO_VETADO);
		}
	}
}
