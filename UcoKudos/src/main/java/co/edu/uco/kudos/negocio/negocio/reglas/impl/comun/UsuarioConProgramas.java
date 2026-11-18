package co.edu.uco.kudos.negocio.negocio.reglas.impl.comun;

import java.time.LocalDate;
import java.util.List;

import co.edu.uco.kudos.entidad.ProgramaAcademicoEntidad;
import co.edu.uco.kudos.entidad.UsuarioEntidad;

public record UsuarioConProgramas(UsuarioEntidad usuario, List<ProgramaAcademicoEntidad> programas,
		LocalDate fechaVinculacion) {

}
