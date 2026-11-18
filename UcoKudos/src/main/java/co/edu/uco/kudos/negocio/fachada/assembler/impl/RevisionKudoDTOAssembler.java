package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.RevisionKudoDominio;
import co.edu.uco.kudos.dto.RevisionKudoDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class RevisionKudoDTOAssembler implements DTOAssembler<RevisionKudoDominio, RevisionKudoDTO> {

	private static final DTOAssembler<RevisionKudoDominio, RevisionKudoDTO> INSTANCIA = new RevisionKudoDTOAssembler();

	private RevisionKudoDTOAssembler() {

	}

	public static DTOAssembler<RevisionKudoDominio, RevisionKudoDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public RevisionKudoDTO convertirADTO(RevisionKudoDominio dominio) {
		var dominioTmp = RevisionKudoDominio.obtenerValorDefecto(dominio);
		var dto = new RevisionKudoDTO();
		dto.setId(dominioTmp.getId());
		dto.setKudo(KudoDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getKudo()));
		dto.setModerador(UsuarioDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getModerador()));
		dto.setEstadoRevision(EstadoRevisionDTOAssembler.obtenerInstancia()
				.convertirADTO(dominioTmp.getEstadoRevision()));
		dto.setJustificacion(dominioTmp.getJustificacion());
		dto.setFechaInicio(dominioTmp.getFechaInicio());
		dto.setFechaCierre(dominioTmp.getFechaCierre());
		return dto;
	}

	@Override
	public RevisionKudoDominio convertirADominio(RevisionKudoDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new RevisionKudoDTO());
		return new RevisionKudoDominio.Builder()
				.id(dtoTmp.getId())
				.kudo(KudoDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getKudo()))
				.moderador(UsuarioDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getModerador()))
				.estadoRevision(EstadoRevisionDTOAssembler.obtenerInstancia()
						.convertirADominio(dtoTmp.getEstadoRevision()))
				.justificacion(dtoTmp.getJustificacion())
				.fechaInicio(dtoTmp.getFechaInicio())
				.fechaCierre(dtoTmp.getFechaCierre())
				.build();
	}
}
