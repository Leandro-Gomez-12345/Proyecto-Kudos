package co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica;

import java.time.LocalDate;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ProgramaAcademicoEntidad;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.entidad.VinculacionAcademicaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.UsuarioConProgramas;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;

public final class VincularProgramasAlRegistrarseRule implements Rule<UsuarioConProgramas> {

	private static final Rule<UsuarioConProgramas> INSTANCIA = new VincularProgramasAlRegistrarseRule();

	private VincularProgramasAlRegistrarseRule() {

	}

	public static Rule<UsuarioConProgramas> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UsuarioConProgramas registro, DAOFactory daoFactory) {
		var usuario = registro.usuario();
		var nombreRol = ConsultorCatalogos.nombreRol(daoFactory, usuario.getRol().getId());
		if (!ConsultorCatalogos.coincide(nombreRol, NombresCatalogo.Rol.ESTUDIANTE, NombresCatalogo.Rol.DOCENTE)) {
			return;
		}

		var fechaVinculacion = UtilFecha.esValorDefecto(registro.fechaVinculacion())
				? UtilFecha.obtenerFechaActual() : registro.fechaVinculacion();
		for (var programa : registro.programas()) {
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
