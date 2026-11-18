package co.edu.uco.kudos.negocio.fachada;

import java.util.List;

import co.edu.uco.kudos.dto.CoordinacionProgramaDTO;

public interface CoordinacionProgramaFachada {

	CoordinacionProgramaDTO asignarCoordinadorPrograma(CoordinacionProgramaDTO datos);

	List<CoordinacionProgramaDTO> consultarCoordinacionesPrograma(CoordinacionProgramaDTO filtro);
}
