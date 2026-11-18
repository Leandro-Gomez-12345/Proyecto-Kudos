package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.RevisionReplicaDominio;
import co.edu.uco.kudos.dto.RevisionReplicaDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class RevisionReplicaDTOAssembler implements DTOAssembler<RevisionReplicaDominio, RevisionReplicaDTO> {

	private static final DTOAssembler<RevisionReplicaDominio, RevisionReplicaDTO> INSTANCIA =
			new RevisionReplicaDTOAssembler();

	private RevisionReplicaDTOAssembler() {

	}

	public static DTOAssembler<RevisionReplicaDominio, RevisionReplicaDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public RevisionReplicaDTO convertirADTO(RevisionReplicaDominio dominio) {
		var dominioTmp = RevisionReplicaDominio.obtenerValorDefecto(dominio);
		var dto = new RevisionReplicaDTO();
		dto.setId(dominioTmp.getId());
		dto.setReplica(ReplicaDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getReplica()));
		dto.setModerador(UsuarioDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getModerador()));
		dto.setEstadoRevision(EstadoRevisionDTOAssembler.obtenerInstancia()
				.convertirADTO(dominioTmp.getEstadoRevision()));
		dto.setJustificacion(dominioTmp.getJustificacion());
		dto.setFechaInicio(dominioTmp.getFechaInicio());
		dto.setFechaCierre(dominioTmp.getFechaCierre());
		return dto;
	}

	@Override
	public RevisionReplicaDominio convertirADominio(RevisionReplicaDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new RevisionReplicaDTO());
		return new RevisionReplicaDominio.Builder()
				.id(dtoTmp.getId())
				.replica(ReplicaDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getReplica()))
				.moderador(UsuarioDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getModerador()))
				.estadoRevision(EstadoRevisionDTOAssembler.obtenerInstancia()
						.convertirADominio(dtoTmp.getEstadoRevision()))
				.justificacion(dtoTmp.getJustificacion())
				.fechaInicio(dtoTmp.getFechaInicio())
				.fechaCierre(dtoTmp.getFechaCierre())
				.build();
	}
}
