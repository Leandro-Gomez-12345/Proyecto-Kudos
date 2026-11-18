package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.EstadoContenidoDominio;
import co.edu.uco.kudos.dto.EstadoContenidoDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class EstadoContenidoDTOAssembler implements DTOAssembler<EstadoContenidoDominio, EstadoContenidoDTO> {

	private static final DTOAssembler<EstadoContenidoDominio, EstadoContenidoDTO> INSTANCIA =
			new EstadoContenidoDTOAssembler();

	private EstadoContenidoDTOAssembler() {

	}

	public static DTOAssembler<EstadoContenidoDominio, EstadoContenidoDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public EstadoContenidoDTO convertirADTO(EstadoContenidoDominio dominio) {
		var dominioTmp = EstadoContenidoDominio.obtenerValorDefecto(dominio);
		var dto = new EstadoContenidoDTO();
		dto.setId(dominioTmp.getId());
		dto.setNombre(dominioTmp.getNombre());
		return dto;
	}

	@Override
	public EstadoContenidoDominio convertirADominio(EstadoContenidoDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new EstadoContenidoDTO());
		return new EstadoContenidoDominio.Builder()
				.id(dtoTmp.getId())
				.nombre(dtoTmp.getNombre())
				.build();
	}
}
