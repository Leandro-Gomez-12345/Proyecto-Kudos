package co.edu.uco.kudos.negocio.negocio;

import java.util.List;

import co.edu.uco.kudos.dominio.EstadoRevisionDominio;

public interface EstadoRevisionNegocio {

	List<EstadoRevisionDominio> consultarEstadosRevision();
}
