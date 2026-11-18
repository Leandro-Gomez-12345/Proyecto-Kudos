package co.edu.uco.kudos.negocio.fachada;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dto.VinculacionAcademicaDTO;

public interface VinculacionAcademicaFachada {

	VinculacionAcademicaDTO vincularPersonaAProgramaAcademico(VinculacionAcademicaDTO datos);

	void desvincularPersonaDeProgramaAcademico(UUID id, VinculacionAcademicaDTO datos);

	List<VinculacionAcademicaDTO> consultarVinculacionesAcademicas(VinculacionAcademicaDTO filtro);
}
