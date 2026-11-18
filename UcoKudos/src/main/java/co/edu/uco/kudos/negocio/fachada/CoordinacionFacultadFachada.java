package co.edu.uco.kudos.negocio.fachada;

import java.util.List;

import co.edu.uco.kudos.dto.CoordinacionFacultadDTO;

public interface CoordinacionFacultadFachada {

	CoordinacionFacultadDTO asignarCoordinadorFacultad(CoordinacionFacultadDTO datos);

	List<CoordinacionFacultadDTO> consultarCoordinacionesFacultad(CoordinacionFacultadDTO filtro);
}
