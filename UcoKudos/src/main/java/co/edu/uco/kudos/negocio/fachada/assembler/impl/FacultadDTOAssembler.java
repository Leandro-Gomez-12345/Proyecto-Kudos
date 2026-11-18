package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.FacultadDominio;
import co.edu.uco.kudos.dto.FacultadDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class FacultadDTOAssembler implements DTOAssembler<FacultadDominio, FacultadDTO> {

	private static final DTOAssembler<FacultadDominio, FacultadDTO> INSTANCIA = new FacultadDTOAssembler();

	private FacultadDTOAssembler() {

	}

	public static DTOAssembler<FacultadDominio, FacultadDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public FacultadDTO convertirADTO(FacultadDominio dominio) {
		var dominioTmp = FacultadDominio.obtenerValorDefecto(dominio);
		var dto = new FacultadDTO();
		dto.setId(dominioTmp.getId());
		dto.setNombre(dominioTmp.getNombre());
		return dto;
	}

	@Override
	public FacultadDominio convertirADominio(FacultadDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new FacultadDTO());
		return new FacultadDominio.Builder()
				.id(dtoTmp.getId())
				.nombre(dtoTmp.getNombre())
				.build();
	}
}
