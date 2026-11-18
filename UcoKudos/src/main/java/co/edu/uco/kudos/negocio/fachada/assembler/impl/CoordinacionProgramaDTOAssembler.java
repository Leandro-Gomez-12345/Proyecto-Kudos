package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.CoordinacionProgramaDominio;
import co.edu.uco.kudos.dto.CoordinacionProgramaDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class CoordinacionProgramaDTOAssembler
		implements DTOAssembler<CoordinacionProgramaDominio, CoordinacionProgramaDTO> {

	private static final DTOAssembler<CoordinacionProgramaDominio, CoordinacionProgramaDTO> INSTANCIA =
			new CoordinacionProgramaDTOAssembler();

	private CoordinacionProgramaDTOAssembler() {

	}

	public static DTOAssembler<CoordinacionProgramaDominio, CoordinacionProgramaDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public CoordinacionProgramaDTO convertirADTO(CoordinacionProgramaDominio dominio) {
		var dominioTmp = CoordinacionProgramaDominio.obtenerValorDefecto(dominio);
		var dto = new CoordinacionProgramaDTO();
		dto.setId(dominioTmp.getId());
		dto.setUsuario(UsuarioDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getUsuario()));
		dto.setProgramaAcademico(ProgramaAcademicoDTOAssembler.obtenerInstancia()
				.convertirADTO(dominioTmp.getProgramaAcademico()));
		dto.setFechaInicio(dominioTmp.getFechaInicio());
		dto.setFechaFin(dominioTmp.getFechaFin());
		return dto;
	}

	@Override
	public CoordinacionProgramaDominio convertirADominio(CoordinacionProgramaDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new CoordinacionProgramaDTO());
		return new CoordinacionProgramaDominio.Builder()
				.id(dtoTmp.getId())
				.usuario(UsuarioDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getUsuario()))
				.programaAcademico(ProgramaAcademicoDTOAssembler.obtenerInstancia()
						.convertirADominio(dtoTmp.getProgramaAcademico()))
				.fechaInicio(dtoTmp.getFechaInicio())
				.fechaFin(dtoTmp.getFechaFin())
				.build();
	}
}
