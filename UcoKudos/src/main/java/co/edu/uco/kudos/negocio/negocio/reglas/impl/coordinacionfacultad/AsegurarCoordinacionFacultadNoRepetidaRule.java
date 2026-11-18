package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CoordinacionFacultadEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarCoordinacionFacultadNoRepetidaRule implements Rule<CoordinacionFacultadEntidad> {

	private static final Rule<CoordinacionFacultadEntidad> INSTANCIA = new AsegurarCoordinacionFacultadNoRepetidaRule();

	private AsegurarCoordinacionFacultadNoRepetidaRule() {

	}

	public static Rule<CoordinacionFacultadEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(CoordinacionFacultadEntidad coordinacion, DAOFactory daoFactory) {
		var filtro = new CoordinacionFacultadEntidad();
		filtro.setUsuario(coordinacion.getUsuario());
		filtro.setFacultad(coordinacion.getFacultad());
		filtro.setFechaInicio(coordinacion.getFechaInicio());
		var coincidencias = daoFactory.obtenerCoordinacionFacultadDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(coincidencias.isEmpty(),
				CatalogoMensajes.Reglas.COORDINACION_REPETIDA);
	}
}
