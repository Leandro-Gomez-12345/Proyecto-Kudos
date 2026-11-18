package co.edu.uco.kudos.negocio.negocio.reglas.impl.auditoria;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.AuditoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.AuditoriaEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.AuditoriaNegocioImpl;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;

public final class RegistrarEventoEnAuditoriaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new RegistrarEventoEnAuditoriaRule();

	private RegistrarEventoEnAuditoriaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var auditoria = (AuditoriaEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var auditoriaNegocio = new AuditoriaNegocioImpl(daoFactory);
		auditoriaNegocio.registrarAuditoria(AuditoriaEntidadAssembler.obtenerInstancia().convertirADominio(auditoria));
	}
}
