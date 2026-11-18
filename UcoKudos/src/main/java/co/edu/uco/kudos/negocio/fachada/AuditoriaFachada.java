package co.edu.uco.kudos.negocio.fachada;

import java.util.List;

import co.edu.uco.kudos.dto.AuditoriaDTO;

public interface AuditoriaFachada {

	List<AuditoriaDTO> consultarAuditorias(AuditoriaDTO filtro);

	List<AuditoriaDTO> consultarAuditoriasPropias(AuditoriaDTO filtro);
}
