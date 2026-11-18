package co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.VinculacionAcademicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarVinculacionAcademicaNoRepetidaRule implements Rule<VinculacionAcademicaEntidad> {

	private static final Rule<VinculacionAcademicaEntidad> INSTANCIA = new AsegurarVinculacionAcademicaNoRepetidaRule();

	private AsegurarVinculacionAcademicaNoRepetidaRule() {

	}

	public static Rule<VinculacionAcademicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(VinculacionAcademicaEntidad vinculacion, DAOFactory daoFactory) {
		var filtro = new VinculacionAcademicaEntidad();
		filtro.setUsuario(vinculacion.getUsuario());
		filtro.setProgramaAcademico(vinculacion.getProgramaAcademico());
		filtro.setFechaVinculacion(vinculacion.getFechaVinculacion());
		var coincidencias = daoFactory.obtenerVinculacionAcademicaDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarCondicion(coincidencias.isEmpty(), CatalogoMensajes.Reglas.VINCULACION_REPETIDA);
	}
}
