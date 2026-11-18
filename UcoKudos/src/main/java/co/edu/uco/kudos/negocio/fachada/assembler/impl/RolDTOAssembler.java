package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.RolDominio;
import co.edu.uco.kudos.dto.RolDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class RolDTOAssembler implements DTOAssembler<RolDominio, RolDTO> {

	private static final DTOAssembler<RolDominio, RolDTO> INSTANCIA = new RolDTOAssembler();

	private RolDTOAssembler() {

	}

	public static DTOAssembler<RolDominio, RolDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public RolDTO convertirADTO(RolDominio dominio) {
		var dominioTmp = RolDominio.obtenerValorDefecto(dominio);
		var dto = new RolDTO();
		dto.setId(dominioTmp.getId());
		dto.setNombre(dominioTmp.getNombre());
		return dto;
	}

	@Override
	public RolDominio convertirADominio(RolDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new RolDTO());
		return new RolDominio.Builder()
				.id(dtoTmp.getId())
				.nombre(dtoTmp.getNombre())
				.build();
	}
}
