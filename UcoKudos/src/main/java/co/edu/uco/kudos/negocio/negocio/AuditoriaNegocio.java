package co.edu.uco.kudos.negocio.negocio;

import java.util.List;

import co.edu.uco.kudos.dominio.AuditoriaDominio;

public interface AuditoriaNegocio {

	void registrarAuditoria(AuditoriaDominio datos);

	List<AuditoriaDominio> consultarAuditorias(AuditoriaDominio filtro);

	List<AuditoriaDominio> consultarAuditoriasPropias(AuditoriaDominio filtro);
}
