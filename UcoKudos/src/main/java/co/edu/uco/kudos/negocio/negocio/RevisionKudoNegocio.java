package co.edu.uco.kudos.negocio.negocio;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dominio.RevisionKudoDominio;

public interface RevisionKudoNegocio {

	RevisionKudoDominio iniciarRevisionKudo(RevisionKudoDominio datos);

	void cerrarRevisionKudoConVeto(UUID id, RevisionKudoDominio datos);

	void cerrarRevisionKudoConLiberacion(UUID id, RevisionKudoDominio datos);

	RevisionKudoDominio vetarKudoDirectamente(RevisionKudoDominio datos);

	List<RevisionKudoDominio> consultarRevisionesKudo(RevisionKudoDominio filtro);
}
