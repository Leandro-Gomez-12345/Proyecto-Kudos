package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.AuditoriaDominio;
import co.edu.uco.kudos.dto.AuditoriaDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class AuditoriaDTOAssembler implements DTOAssembler<AuditoriaDominio, AuditoriaDTO> {

	private static final DTOAssembler<AuditoriaDominio, AuditoriaDTO> INSTANCIA = new AuditoriaDTOAssembler();

	private AuditoriaDTOAssembler() {

	}

	public static DTOAssembler<AuditoriaDominio, AuditoriaDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public AuditoriaDTO convertirADTO(AuditoriaDominio dominio) {
		var dominioTmp = AuditoriaDominio.obtenerValorDefecto(dominio);
		var dto = new AuditoriaDTO();
		dto.setId(dominioTmp.getId());
		dto.setUsuario(UsuarioDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getUsuario()));
		dto.setAccion(dominioTmp.getAccion());
		dto.setObjetoAfectado(dominioTmp.getObjetoAfectado());
		dto.setRegistroAfectado(dominioTmp.getRegistroAfectado());
		dto.setDetalle(dominioTmp.getDetalle());
		dto.setFechaHora(dominioTmp.getFechaHora());
		return dto;
	}

	@Override
	public AuditoriaDominio convertirADominio(AuditoriaDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new AuditoriaDTO());
		return new AuditoriaDominio.Builder()
				.id(dtoTmp.getId())
				.usuario(UsuarioDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getUsuario()))
				.accion(dtoTmp.getAccion())
				.objetoAfectado(dtoTmp.getObjetoAfectado())
				.registroAfectado(dtoTmp.getRegistroAfectado())
				.detalle(dtoTmp.getDetalle())
				.fechaHora(dtoTmp.getFechaHora())
				.build();
	}
}
