package co.edu.uco.kudos.negocio.negocio;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dominio.KudoDominio;

public interface KudoNegocio {

	KudoDominio crearKudo(KudoDominio datos);

	KudoDominio consultarKudo(UUID id);

	List<KudoDominio> consultarKudos(KudoDominio filtro);

	void marcarKudoReportado(UUID id);

	void ponerKudoEnRevision(UUID id);

	void vetarKudo(UUID id);

	void liberarKudo(UUID id);
}
