package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.EstadoRevisionDominio;
import co.edu.uco.kudos.dto.EstadoRevisionDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class EstadoRevisionDTOAssembler implements DTOAssembler<EstadoRevisionDominio, EstadoRevisionDTO> {

	private static final DTOAssembler<EstadoRevisionDominio, EstadoRevisionDTO> INSTANCIA =
			new EstadoRevisionDTOAssembler();

	private EstadoRevisionDTOAssembler() {

	}

	public static DTOAssembler<EstadoRevisionDominio, EstadoRevisionDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public EstadoRevisionDTO convertirADTO(EstadoRevisionDominio dominio) {
		var dominioTmp = EstadoRevisionDominio.obtenerValorDefecto(dominio);
		var dto = new EstadoRevisionDTO();
		dto.setId(dominioTmp.getId());
		dto.setNombre(dominioTmp.getNombre());
		return dto;
	}

	@Override
	public EstadoRevisionDominio convertirADominio(EstadoRevisionDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new EstadoRevisionDTO());
		return new EstadoRevisionDominio.Builder()
				.id(dtoTmp.getId())
				.nombre(dtoTmp.getNombre())
				.build();
	}
}
