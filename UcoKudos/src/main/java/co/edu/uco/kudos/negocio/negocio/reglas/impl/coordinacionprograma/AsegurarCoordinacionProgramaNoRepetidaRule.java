package co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CoordinacionProgramaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarCoordinacionProgramaNoRepetidaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarCoordinacionProgramaNoRepetidaRule();

	private AsegurarCoordinacionProgramaNoRepetidaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var coordinacion = (CoordinacionProgramaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var filtro = new CoordinacionProgramaEntidad();
		filtro.setUsuario(coordinacion.getUsuario());
		filtro.setProgramaAcademico(coordinacion.getProgramaAcademico());
		filtro.setFechaInicio(coordinacion.getFechaInicio());
		var coincidencias = daoFactory.obtenerCoordinacionProgramaDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(coincidencias.isEmpty(),
				CatalogoMensajes.Reglas.COORDINACION_REPETIDA);
	}
}
