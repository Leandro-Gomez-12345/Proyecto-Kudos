package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.ReporteReplicaDominio;
import co.edu.uco.kudos.dto.ReporteReplicaDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class ReporteReplicaDTOAssembler implements DTOAssembler<ReporteReplicaDominio, ReporteReplicaDTO> {

	private static final DTOAssembler<ReporteReplicaDominio, ReporteReplicaDTO> INSTANCIA =
			new ReporteReplicaDTOAssembler();

	private ReporteReplicaDTOAssembler() {

	}

	public static DTOAssembler<ReporteReplicaDominio, ReporteReplicaDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public ReporteReplicaDTO convertirADTO(ReporteReplicaDominio dominio) {
		var dominioTmp = ReporteReplicaDominio.obtenerValorDefecto(dominio);
		var dto = new ReporteReplicaDTO();
		dto.setId(dominioTmp.getId());
		dto.setReplica(ReplicaDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getReplica()));
		dto.setUsuario(UsuarioDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getUsuario()));
		dto.setMotivo(MotivoDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getMotivo()));
		dto.setDescripcion(dominioTmp.getDescripcion());
		dto.setRevisionReplica(RevisionReplicaDTOAssembler.obtenerInstancia()
				.convertirADTO(dominioTmp.getRevisionReplica()));
		dto.setFechaReporte(dominioTmp.getFechaReporte());
		return dto;
	}

	@Override
	public ReporteReplicaDominio convertirADominio(ReporteReplicaDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new ReporteReplicaDTO());
		return new ReporteReplicaDominio.Builder()
				.id(dtoTmp.getId())
				.replica(ReplicaDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getReplica()))
				.usuario(UsuarioDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getUsuario()))
				.motivo(MotivoDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getMotivo()))
				.descripcion(dtoTmp.getDescripcion())
				.revisionReplica(RevisionReplicaDTOAssembler.obtenerInstancia()
						.convertirADominio(dtoTmp.getRevisionReplica()))
				.fechaReporte(dtoTmp.getFechaReporte())
				.build();
	}
}
