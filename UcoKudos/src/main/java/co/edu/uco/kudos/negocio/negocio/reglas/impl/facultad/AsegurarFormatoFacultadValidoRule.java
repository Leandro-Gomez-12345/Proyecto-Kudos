package co.edu.uco.kudos.negocio.negocio.reglas.impl.facultad;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.FacultadEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.FormatosAtributos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoFacultadValidoRule implements Rule<FacultadEntidad> {

	private static final Rule<FacultadEntidad> INSTANCIA = new AsegurarFormatoFacultadValidoRule();

	private static final int LONGITUD_MINIMA_NOMBRE = 13;
	private static final int LONGITUD_MAXIMA_NOMBRE = 70;

	private AsegurarFormatoFacultadValidoRule() {

	}

	public static Rule<FacultadEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(FacultadEntidad facultad, DAOFactory daoFactory) {
		ValidadorAtributos.asegurarTexto(facultad.getNombre(), LONGITUD_MINIMA_NOMBRE, LONGITUD_MAXIMA_NOMBRE,
				CatalogoMensajes.Reglas.FACULTAD_NOMBRE_NO_VALIDO);
		ValidadorAtributos.asegurarFormato(facultad.getNombre(), FormatosAtributos.NOMBRE_FACULTAD,
				CatalogoMensajes.Reglas.FACULTAD_NOMBRE_NO_VALIDO);
	}
}
