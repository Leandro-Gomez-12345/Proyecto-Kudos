package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.AuditoriaDominio;
import co.edu.uco.kudos.entidad.AuditoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class AuditoriaEntidadAssembler implements EntidadAssembler<AuditoriaDominio, AuditoriaEntidad> {

	private static final EntidadAssembler<AuditoriaDominio, AuditoriaEntidad> INSTANCIA =
			new AuditoriaEntidadAssembler();

	private AuditoriaEntidadAssembler() {

	}

	public static EntidadAssembler<AuditoriaDominio, AuditoriaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public AuditoriaEntidad convertirAEntidad(AuditoriaDominio dominio) {
		var dominioTmp = AuditoriaDominio.obtenerValorDefecto(dominio);
		var entidad = new AuditoriaEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setUsuario(UsuarioEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getUsuario()));
		entidad.setAccion(dominioTmp.getAccion());
		entidad.setObjetoAfectado(dominioTmp.getObjetoAfectado());
		entidad.setRegistroAfectado(dominioTmp.getRegistroAfectado());
		entidad.setDetalle(dominioTmp.getDetalle());
		entidad.setFechaHora(dominioTmp.getFechaHora());
		return entidad;
	}

	@Override
	public AuditoriaDominio convertirADominio(AuditoriaEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new AuditoriaEntidad());
		return new AuditoriaDominio.Builder()
				.id(entidadTmp.getId())
				.usuario(UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getUsuario()))
				.accion(entidadTmp.getAccion())
				.objetoAfectado(entidadTmp.getObjetoAfectado())
				.registroAfectado(entidadTmp.getRegistroAfectado())
				.detalle(entidadTmp.getDetalle())
				.fechaHora(entidadTmp.getFechaHora())
				.build();
	}
}
