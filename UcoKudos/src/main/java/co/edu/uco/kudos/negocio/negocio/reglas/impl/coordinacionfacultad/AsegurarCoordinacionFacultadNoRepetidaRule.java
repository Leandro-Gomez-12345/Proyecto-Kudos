package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CoordinacionFacultadEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarCoordinacionFacultadNoRepetidaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarCoordinacionFacultadNoRepetidaRule();

	private AsegurarCoordinacionFacultadNoRepetidaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var coordinacion = (CoordinacionFacultadEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var filtro = new CoordinacionFacultadEntidad();
		filtro.setUsuario(coordinacion.getUsuario());
		filtro.setFacultad(coordinacion.getFacultad());
		filtro.setFechaInicio(coordinacion.getFechaInicio());
		var coincidencias = daoFactory.obtenerCoordinacionFacultadDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(coincidencias.isEmpty(),
				CatalogoMensajes.Reglas.COORDINACION_REPETIDA);
	}
}
