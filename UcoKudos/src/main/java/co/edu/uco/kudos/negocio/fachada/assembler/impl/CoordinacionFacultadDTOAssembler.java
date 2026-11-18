package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.CoordinacionFacultadDominio;
import co.edu.uco.kudos.dto.CoordinacionFacultadDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class CoordinacionFacultadDTOAssembler
		implements DTOAssembler<CoordinacionFacultadDominio, CoordinacionFacultadDTO> {

	private static final DTOAssembler<CoordinacionFacultadDominio, CoordinacionFacultadDTO> INSTANCIA =
			new CoordinacionFacultadDTOAssembler();

	private CoordinacionFacultadDTOAssembler() {

	}

	public static DTOAssembler<CoordinacionFacultadDominio, CoordinacionFacultadDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public CoordinacionFacultadDTO convertirADTO(CoordinacionFacultadDominio dominio) {
		var dominioTmp = CoordinacionFacultadDominio.obtenerValorDefecto(dominio);
		var dto = new CoordinacionFacultadDTO();
		dto.setId(dominioTmp.getId());
		dto.setUsuario(UsuarioDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getUsuario()));
		dto.setFacultad(FacultadDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getFacultad()));
		dto.setFechaInicio(dominioTmp.getFechaInicio());
		dto.setFechaFin(dominioTmp.getFechaFin());
		return dto;
	}

	@Override
	public CoordinacionFacultadDominio convertirADominio(CoordinacionFacultadDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new CoordinacionFacultadDTO());
		return new CoordinacionFacultadDominio.Builder()
				.id(dtoTmp.getId())
				.usuario(UsuarioDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getUsuario()))
				.facultad(FacultadDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getFacultad()))
				.fechaInicio(dtoTmp.getFechaInicio())
				.fechaFin(dtoTmp.getFechaFin())
				.build();
	}
}
