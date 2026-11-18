package co.edu.uco.kudos.negocio.negocio.reglas.impl.comun;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.CoordinacionFacultadEntidad;
import co.edu.uco.kudos.entidad.CoordinacionProgramaEntidad;
import co.edu.uco.kudos.entidad.VinculacionAcademicaEntidad;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ConsultorVigencias {

	private ConsultorVigencias() {

	}

	public static List<CoordinacionFacultadEntidad> coordinacionesFacultad(DAOFactory daoFactory, UUID idUsuario,
			UUID idFacultad) {
		var filtro = new CoordinacionFacultadEntidad();
		filtro.getUsuario().setId(idUsuario);
		filtro.getFacultad().setId(idFacultad);
		return daoFactory.obtenerCoordinacionFacultadDAO().consultarPorFiltro(filtro).stream()
				.filter(coordinacion -> UtilFecha.esValorDefecto(coordinacion.getFechaFin())).toList();
	}

	public static List<CoordinacionProgramaEntidad> coordinacionesPrograma(DAOFactory daoFactory, UUID idUsuario,
			UUID idPrograma) {
		var filtro = new CoordinacionProgramaEntidad();
		filtro.getUsuario().setId(idUsuario);
		filtro.getProgramaAcademico().setId(idPrograma);
		return daoFactory.obtenerCoordinacionProgramaDAO().consultarPorFiltro(filtro).stream()
				.filter(coordinacion -> UtilFecha.esValorDefecto(coordinacion.getFechaFin())).toList();
	}

	public static List<VinculacionAcademicaEntidad> vinculaciones(DAOFactory daoFactory, UUID idUsuario,
			UUID idPrograma) {
		var filtro = new VinculacionAcademicaEntidad();
		filtro.getUsuario().setId(idUsuario);
		filtro.getProgramaAcademico().setId(idPrograma);
		return daoFactory.obtenerVinculacionAcademicaDAO().consultarPorFiltro(filtro).stream()
				.filter(vinculacion -> UtilFecha.esValorDefecto(vinculacion.getFechaDesvinculacion())).toList();
	}

	public static boolean tieneCargoVigente(DAOFactory daoFactory, UUID idUsuario) {
		var sinFacultad = UtilUUID.obtenerUUIDDefecto();
		return !coordinacionesFacultad(daoFactory, idUsuario, sinFacultad).isEmpty()
				|| !coordinacionesPrograma(daoFactory, idUsuario, sinFacultad).isEmpty()
				|| !vinculaciones(daoFactory, idUsuario, sinFacultad).isEmpty();
	}
}
