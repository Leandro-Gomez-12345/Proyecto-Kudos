package co.edu.uco.kudos.negocio.fachada;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dto.KudoDTO;

public interface KudoFachada {

	KudoDTO crearKudo(KudoDTO datos);

	KudoDTO consultarKudo(UUID id);

	List<KudoDTO> consultarKudos(KudoDTO filtro);
}
