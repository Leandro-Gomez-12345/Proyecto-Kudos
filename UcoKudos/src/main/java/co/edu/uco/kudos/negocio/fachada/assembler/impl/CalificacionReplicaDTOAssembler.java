package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.CalificacionReplicaDominio;
import co.edu.uco.kudos.dto.CalificacionReplicaDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class CalificacionReplicaDTOAssembler
		implements DTOAssembler<CalificacionReplicaDominio, CalificacionReplicaDTO> {

	private static final DTOAssembler<CalificacionReplicaDominio, CalificacionReplicaDTO> INSTANCIA =
			new CalificacionReplicaDTOAssembler();

	private CalificacionReplicaDTOAssembler() {

	}

	public static DTOAssembler<CalificacionReplicaDominio, CalificacionReplicaDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public CalificacionReplicaDTO convertirADTO(CalificacionReplicaDominio dominio) {
		var dominioTmp = CalificacionReplicaDominio.obtenerValorDefecto(dominio);
		var dto = new CalificacionReplicaDTO();
		dto.setId(dominioTmp.getId());
		dto.setReplica(ReplicaDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getReplica()));
		dto.setUsuario(UsuarioDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getUsuario()));
		dto.setEstrellas(dominioTmp.getEstrellas());
		dto.setFechaCalificacion(dominioTmp.getFechaCalificacion());
		return dto;
	}

	@Override
	public CalificacionReplicaDominio convertirADominio(CalificacionReplicaDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new CalificacionReplicaDTO());
		return new CalificacionReplicaDominio.Builder()
				.id(dtoTmp.getId())
				.replica(ReplicaDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getReplica()))
				.usuario(UsuarioDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getUsuario()))
				.estrellas(dtoTmp.getEstrellas())
				.fechaCalificacion(dtoTmp.getFechaCalificacion())
				.build();
	}
}
