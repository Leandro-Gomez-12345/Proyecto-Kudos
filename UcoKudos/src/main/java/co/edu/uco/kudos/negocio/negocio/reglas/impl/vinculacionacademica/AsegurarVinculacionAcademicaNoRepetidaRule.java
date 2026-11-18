package co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.VinculacionAcademicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarVinculacionAcademicaNoRepetidaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarVinculacionAcademicaNoRepetidaRule();

	private AsegurarVinculacionAcademicaNoRepetidaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var vinculacion = (VinculacionAcademicaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var filtro = new VinculacionAcademicaEntidad();
		filtro.setUsuario(vinculacion.getUsuario());
		filtro.setProgramaAcademico(vinculacion.getProgramaAcademico());
		filtro.setFechaVinculacion(vinculacion.getFechaVinculacion());
		var coincidencias = daoFactory.obtenerVinculacionAcademicaDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(coincidencias.isEmpty(), CatalogoMensajes.Reglas.VINCULACION_REPETIDA);
	}
}
