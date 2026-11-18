package co.edu.uco.kudos.negocio.negocio.reglas.impl.facultad;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.FacultadEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarNombreFacultadNoExistaRule implements Rule<FacultadEntidad> {

	private static final Rule<FacultadEntidad> INSTANCIA = new AsegurarNombreFacultadNoExistaRule();

	private AsegurarNombreFacultadNoExistaRule() {

	}

	public static Rule<FacultadEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(FacultadEntidad facultad, DAOFactory daoFactory) {
		var filtro = new FacultadEntidad();
		filtro.setNombre(facultad.getNombre());
		var coincidencias = daoFactory.obtenerFacultadDAO().consultarPorFiltro(filtro);
		ValidadorNegocio.asegurarQueNoExisteOtro(coincidencias.stream().map(FacultadEntidad::getId).toList(),
				facultad.getId(), CatalogoMensajes.Reglas.FACULTAD_NOMBRE_REPETIDO);
	}
}
