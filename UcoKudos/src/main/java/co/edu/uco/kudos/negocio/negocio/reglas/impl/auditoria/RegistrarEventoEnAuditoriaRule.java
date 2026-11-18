package co.edu.uco.kudos.negocio.negocio.reglas.impl.auditoria;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.AuditoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.AuditoriaEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.AuditoriaNegocioImpl;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;

public final class RegistrarEventoEnAuditoriaRule implements Rule<AuditoriaEntidad> {

	private static final Rule<AuditoriaEntidad> INSTANCIA = new RegistrarEventoEnAuditoriaRule();

	private RegistrarEventoEnAuditoriaRule() {

	}

	public static Rule<AuditoriaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(AuditoriaEntidad auditoria, DAOFactory daoFactory) {
		var auditoriaNegocio = new AuditoriaNegocioImpl(daoFactory);
		auditoriaNegocio.registrarAuditoria(AuditoriaEntidadAssembler.obtenerInstancia().convertirADominio(auditoria));
	}
}
