package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.ProgramaAcademicoDominio;
import co.edu.uco.kudos.dto.ProgramaAcademicoDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class ProgramaAcademicoDTOAssembler
		implements DTOAssembler<ProgramaAcademicoDominio, ProgramaAcademicoDTO> {

	private static final DTOAssembler<ProgramaAcademicoDominio, ProgramaAcademicoDTO> INSTANCIA =
			new ProgramaAcademicoDTOAssembler();

	private ProgramaAcademicoDTOAssembler() {

	}

	public static DTOAssembler<ProgramaAcademicoDominio, ProgramaAcademicoDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public ProgramaAcademicoDTO convertirADTO(ProgramaAcademicoDominio dominio) {
		var dominioTmp = ProgramaAcademicoDominio.obtenerValorDefecto(dominio);
		var dto = new ProgramaAcademicoDTO();
		dto.setId(dominioTmp.getId());
		dto.setFacultad(FacultadDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getFacultad()));
		dto.setNombre(dominioTmp.getNombre());
		return dto;
	}

	@Override
	public ProgramaAcademicoDominio convertirADominio(ProgramaAcademicoDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new ProgramaAcademicoDTO());
		return new ProgramaAcademicoDominio.Builder()
				.id(dtoTmp.getId())
				.facultad(FacultadDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getFacultad()))
				.nombre(dtoTmp.getNombre())
				.build();
	}
}
