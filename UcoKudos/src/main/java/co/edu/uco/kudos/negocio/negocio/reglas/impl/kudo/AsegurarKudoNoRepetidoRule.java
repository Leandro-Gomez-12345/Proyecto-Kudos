package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.KudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;

public final class AsegurarKudoNoRepetidoRule implements Rule<KudoEntidad> {

	private static final Rule<KudoEntidad> INSTANCIA = new AsegurarKudoNoRepetidoRule();

	private AsegurarKudoNoRepetidoRule() {

	}

	public static Rule<KudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(KudoEntidad kudo, DAOFactory daoFactory) {
		var filtro = new KudoEntidad();
		filtro.getAutor().setId(ContextoEjecucion.obtenerIdEjecutor());
		filtro.setFechaCreacion(UtilFechaHora.obtenerFechaHoraActual());
		var coincidencias = daoFactory.obtenerKudoDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(coincidencias.isEmpty(), CatalogoMensajes.Reglas.KUDO_REPETIDO);
	}
}
