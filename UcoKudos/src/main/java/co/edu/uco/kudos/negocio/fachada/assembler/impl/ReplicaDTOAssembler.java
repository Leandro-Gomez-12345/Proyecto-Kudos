package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.ReplicaDominio;
import co.edu.uco.kudos.dto.ReplicaDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class ReplicaDTOAssembler implements DTOAssembler<ReplicaDominio, ReplicaDTO> {

	private static final DTOAssembler<ReplicaDominio, ReplicaDTO> INSTANCIA = new ReplicaDTOAssembler();

	private ReplicaDTOAssembler() {

	}

	public static DTOAssembler<ReplicaDominio, ReplicaDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public ReplicaDTO convertirADTO(ReplicaDominio dominio) {
		var dominioTmp = ReplicaDominio.obtenerValorDefecto(dominio);
		var dto = new ReplicaDTO();
		dto.setId(dominioTmp.getId());
		dto.setKudo(KudoDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getKudo()));
		dto.setReplicaPadre(convertirReplicaPadreADTO(dominioTmp.getReplicaPadre()));
		dto.setAutor(UsuarioDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getAutor()));
		dto.setContenido(dominioTmp.getContenido());
		dto.setEstadoContenido(EstadoContenidoDTOAssembler.obtenerInstancia()
				.convertirADTO(dominioTmp.getEstadoContenido()));
		dto.setFechaCreacion(dominioTmp.getFechaCreacion());
		return dto;
	}

	@Override
	public ReplicaDominio convertirADominio(ReplicaDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new ReplicaDTO());
		return new ReplicaDominio.Builder()
				.id(dtoTmp.getId())
				.kudo(KudoDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getKudo()))
				.replicaPadre(convertirReplicaPadreADominio(dtoTmp.getReplicaPadre()))
				.autor(UsuarioDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getAutor()))
				.contenido(dtoTmp.getContenido())
				.estadoContenido(EstadoContenidoDTOAssembler.obtenerInstancia()
						.convertirADominio(dtoTmp.getEstadoContenido()))
				.fechaCreacion(dtoTmp.getFechaCreacion())
				.build();
	}

	private static ReplicaDTO convertirReplicaPadreADTO(ReplicaDominio replicaPadre) {
		return UtilObjeto.esNulo(replicaPadre) ? null : INSTANCIA.convertirADTO(replicaPadre);
	}

	private static ReplicaDominio convertirReplicaPadreADominio(ReplicaDTO replicaPadre) {
		return UtilObjeto.esNulo(replicaPadre) ? null : INSTANCIA.convertirADominio(replicaPadre);
	}
}
