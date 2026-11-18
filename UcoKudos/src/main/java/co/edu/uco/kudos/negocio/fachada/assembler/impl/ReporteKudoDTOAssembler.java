package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.ReporteKudoDominio;
import co.edu.uco.kudos.dto.ReporteKudoDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class ReporteKudoDTOAssembler implements DTOAssembler<ReporteKudoDominio, ReporteKudoDTO> {

	private static final DTOAssembler<ReporteKudoDominio, ReporteKudoDTO> INSTANCIA = new ReporteKudoDTOAssembler();

	private ReporteKudoDTOAssembler() {

	}

	public static DTOAssembler<ReporteKudoDominio, ReporteKudoDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public ReporteKudoDTO convertirADTO(ReporteKudoDominio dominio) {
		var dominioTmp = ReporteKudoDominio.obtenerValorDefecto(dominio);
		var dto = new ReporteKudoDTO();
		dto.setId(dominioTmp.getId());
		dto.setKudo(KudoDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getKudo()));
		dto.setUsuario(UsuarioDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getUsuario()));
		dto.setMotivo(MotivoDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getMotivo()));
		dto.setDescripcion(dominioTmp.getDescripcion());
		dto.setRevisionKudo(RevisionKudoDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getRevisionKudo()));
		dto.setFechaReporte(dominioTmp.getFechaReporte());
		return dto;
	}

	@Override
	public ReporteKudoDominio convertirADominio(ReporteKudoDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new ReporteKudoDTO());
		return new ReporteKudoDominio.Builder()
				.id(dtoTmp.getId())
				.kudo(KudoDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getKudo()))
				.usuario(UsuarioDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getUsuario()))
				.motivo(MotivoDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getMotivo()))
				.descripcion(dtoTmp.getDescripcion())
				.revisionKudo(RevisionKudoDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getRevisionKudo()))
				.fechaReporte(dtoTmp.getFechaReporte())
				.build();
	}
}
