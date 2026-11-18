package co.edu.uco.kudos.negocio.fachada;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dto.RevisionKudoDTO;

public interface RevisionKudoFachada {

	RevisionKudoDTO iniciarRevisionKudo(RevisionKudoDTO datos);

	void cerrarRevisionKudoConVeto(UUID id, RevisionKudoDTO datos);

	void cerrarRevisionKudoConLiberacion(UUID id, RevisionKudoDTO datos);

	RevisionKudoDTO vetarKudoDirectamente(RevisionKudoDTO datos);

	List<RevisionKudoDTO> consultarRevisionesKudo(RevisionKudoDTO filtro);
}
