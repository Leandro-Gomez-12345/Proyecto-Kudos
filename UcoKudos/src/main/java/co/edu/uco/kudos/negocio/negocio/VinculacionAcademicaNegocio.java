package co.edu.uco.kudos.negocio.negocio;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dominio.ProgramaAcademicoDominio;
import co.edu.uco.kudos.dominio.VinculacionAcademicaDominio;

public interface VinculacionAcademicaNegocio {

	VinculacionAcademicaDominio vincularPersonaAProgramaAcademico(VinculacionAcademicaDominio datos);

	void desvincularPersonaDeProgramaAcademico(UUID id, VinculacionAcademicaDominio datos);

	void vincularPersonaAlRegistrarse(VinculacionAcademicaDominio datos, List<ProgramaAcademicoDominio> programas);

	void desvincularPersonaDeTodosLosProgramas(VinculacionAcademicaDominio filtro, VinculacionAcademicaDominio datos);

	List<VinculacionAcademicaDominio> consultarVinculacionesAcademicas(VinculacionAcademicaDominio filtro);
}
