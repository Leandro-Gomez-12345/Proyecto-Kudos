package co.edu.uco.kudos.negocio.negocio.reglas.impl.comun;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AmbitoAutoridad {

	private AmbitoAutoridad() {

	}

	public static boolean gestiona(DAOFactory daoFactory, UUID idAutoridad, UUID idPersona) {
		var rolAutoridad = nombreRolDe(daoFactory, idAutoridad);
		var rolPersona = nombreRolDe(daoFactory, idPersona);

		if (ConsultorCatalogos.coincide(rolPersona, NombresCatalogo.Rol.ESTUDIANTE, NombresCatalogo.Rol.DOCENTE)) {
			return ConsultorCatalogos.coincide(rolAutoridad, NombresCatalogo.Rol.COORDINADOR_PROGRAMA)
					&& coordinaAlgunProgramaDe(daoFactory, idAutoridad, idPersona);
		}
		if (ConsultorCatalogos.coincide(rolPersona, NombresCatalogo.Rol.COORDINADOR_PROGRAMA)) {
			return ConsultorCatalogos.coincide(rolAutoridad, NombresCatalogo.Rol.COORDINADOR_FACULTAD)
					&& coordinaFacultadDe(daoFactory, idAutoridad, idPersona);
		}
		if (ConsultorCatalogos.coincide(rolPersona, NombresCatalogo.Rol.COORDINADOR_FACULTAD)) {
			return ConsultorCatalogos.coincide(rolAutoridad, NombresCatalogo.Rol.COORDINADOR_ACADEMICO);
		}
		if (ConsultorCatalogos.coincide(rolPersona, NombresCatalogo.Rol.COORDINADOR_ACADEMICO)) {
			return ConsultorCatalogos.coincide(rolAutoridad, NombresCatalogo.Rol.RECTOR);
		}
		return ConsultorCatalogos.coincide(rolAutoridad, NombresCatalogo.Rol.ADMINISTRADOR);
	}

	public static boolean decideRegistro(DAOFactory daoFactory, UUID idAutoridad, UUID idPersona) {
		var rolPersona = nombreRolDe(daoFactory, idPersona);
		var pasaPorAprobacion = !ConsultorCatalogos.coincide(rolPersona, NombresCatalogo.Rol.RECTOR,
				NombresCatalogo.Rol.ADMINISTRADOR);
		return pasaPorAprobacion && gestiona(daoFactory, idAutoridad, idPersona);
	}

	public static boolean tieneAmbito(DAOFactory daoFactory, UUID idAutoridad) {
		var rolAutoridad = nombreRolDe(daoFactory, idAutoridad);
		var sinUnidad = UtilUUID.obtenerUUIDDefecto();

		if (ConsultorCatalogos.coincide(rolAutoridad, NombresCatalogo.Rol.COORDINADOR_PROGRAMA)) {
			return !ConsultorVigencias.coordinacionesPrograma(daoFactory, idAutoridad, sinUnidad).isEmpty();
		}
		if (ConsultorCatalogos.coincide(rolAutoridad, NombresCatalogo.Rol.COORDINADOR_FACULTAD)) {
			return !ConsultorVigencias.coordinacionesFacultad(daoFactory, idAutoridad, sinUnidad).isEmpty();
		}
		return true;
	}

	private static String nombreRolDe(DAOFactory daoFactory, UUID idUsuario) {
		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(idUsuario);
		return ConsultorCatalogos.nombreRol(daoFactory, usuario.getRol().getId());
	}

	private static boolean coordinaAlgunProgramaDe(DAOFactory daoFactory, UUID idAutoridad, UUID idPersona) {
		var sinPrograma = UtilUUID.obtenerUUIDDefecto();
		for (var vinculacion : ConsultorVigencias.vinculaciones(daoFactory, idPersona, sinPrograma)) {
			var idPrograma = vinculacion.getProgramaAcademico().getId();
			if (!ConsultorVigencias.coordinacionesPrograma(daoFactory, idAutoridad, idPrograma).isEmpty()) {
				return true;
			}
		}
		return false;
	}

	private static boolean coordinaFacultadDe(DAOFactory daoFactory, UUID idAutoridad, UUID idPersona) {
		var sinUnidad = UtilUUID.obtenerUUIDDefecto();
		var facultadesDeLaAutoridad = ConsultorVigencias.coordinacionesFacultad(daoFactory, idAutoridad, sinUnidad);
		if (facultadesDeLaAutoridad.isEmpty()) {
			return false;
		}

		var programasDeLaPersona = ConsultorVigencias.coordinacionesPrograma(daoFactory, idPersona, sinUnidad);
		if (programasDeLaPersona.isEmpty()) {
			return true;
		}
		for (var coordinacion : programasDeLaPersona) {
			var programa = daoFactory.obtenerProgramaAcademicoDAO()
					.consultarPorId(coordinacion.getProgramaAcademico().getId());
			for (var facultad : facultadesDeLaAutoridad) {
				if (programa.getFacultad().getId().equals(facultad.getFacultad().getId())) {
					return true;
				}
			}
		}
		return false;
	}
}
