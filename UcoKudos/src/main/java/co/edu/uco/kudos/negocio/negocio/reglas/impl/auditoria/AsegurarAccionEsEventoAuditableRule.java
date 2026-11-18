package co.edu.uco.kudos.negocio.negocio.reglas.impl.auditoria;

import co.edu.uco.kudos.entidad.AuditoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;

public final class AsegurarAccionEsEventoAuditableRule implements Rule<AuditoriaEntidad> {

	private static final Rule<AuditoriaEntidad> INSTANCIA = new AsegurarAccionEsEventoAuditableRule();

	private AsegurarAccionEsEventoAuditableRule() {

	}

	public static Rule<AuditoriaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(AuditoriaEntidad... datos) {
		var auditoria = datos[0];

		var esEventoAuditable = EventoAuditable.existe(auditoria.getAccion(), auditoria.getObjetoAfectado());
		ValidadorNegocio.asegurarCondicion(esEventoAuditable, CatalogoMensajes.Reglas.AUDITORIA_NO_REGISTRADA);
	}
}
