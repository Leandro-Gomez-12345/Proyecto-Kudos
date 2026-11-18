package co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica;

import java.time.LocalDate;
import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ProgramaAcademicoEntidad;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.entidad.VinculacionAcademicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;

public final class VincularProgramasAlRegistrarseRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new VincularProgramasAlRegistrarseRule();

	private VincularProgramasAlRegistrarseRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void ejecutar(Object... datos) {
		var usuario = (UsuarioEntidad) datos[0];
		var programas = (List<ProgramaAcademicoEntidad>) datos[1];
		var fechaIndicada = (LocalDate) datos[2];
		var daoFactory = (DAOFactory) datos[3];

		var nombreRol = ConsultorCatalogos.nombreRol(daoFactory, usuario.getRol().getId());
		if (!ConsultorCatalogos.coincide(nombreRol, NombresCatalogo.Rol.ESTUDIANTE, NombresCatalogo.Rol.DOCENTE)) {
			return;
		}

		var fechaVinculacion = UtilFecha.esValorDefecto(fechaIndicada) ? UtilFecha.obtenerFechaActual() : fechaIndicada;
		for (var programa : programas) {
			vincular(usuario, programa, fechaVinculacion, daoFactory);
		}
	}

	private static void vincular(UsuarioEntidad usuario, ProgramaAcademicoEntidad programa,
			LocalDate fechaVinculacion, DAOFactory daoFactory) {
		var vinculacion = new VinculacionAcademicaEntidad();
		vinculacion.setUsuario(usuario);
		vinculacion.setProgramaAcademico(programa);
		vinculacion.setFechaVinculacion(fechaVinculacion);
		AsegurarPersonaSinVinculoVigenteEnProgramaRule.obtenerInstancia().ejecutar(vinculacion, daoFactory);
		AsegurarIdVinculacionAcademicaUnicoRule.obtenerInstancia().ejecutar(vinculacion, daoFactory);
		daoFactory.obtenerVinculacionAcademicaDAO().crear(vinculacion);
	}
}
