package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.VinculacionAcademicaDominio;
import co.edu.uco.kudos.dto.VinculacionAcademicaDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class VinculacionAcademicaDTOAssembler
		implements DTOAssembler<VinculacionAcademicaDominio, VinculacionAcademicaDTO> {

	private static final DTOAssembler<VinculacionAcademicaDominio, VinculacionAcademicaDTO> INSTANCIA =
			new VinculacionAcademicaDTOAssembler();

	private VinculacionAcademicaDTOAssembler() {

	}

	public static DTOAssembler<VinculacionAcademicaDominio, VinculacionAcademicaDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public VinculacionAcademicaDTO convertirADTO(VinculacionAcademicaDominio dominio) {
		var dominioTmp = VinculacionAcademicaDominio.obtenerValorDefecto(dominio);
		var dto = new VinculacionAcademicaDTO();
		dto.setId(dominioTmp.getId());
		dto.setUsuario(UsuarioDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getUsuario()));
		dto.setProgramaAcademico(ProgramaAcademicoDTOAssembler.obtenerInstancia()
				.convertirADTO(dominioTmp.getProgramaAcademico()));
		dto.setFechaVinculacion(dominioTmp.getFechaVinculacion());
		dto.setFechaDesvinculacion(dominioTmp.getFechaDesvinculacion());
		return dto;
	}

	@Override
	public VinculacionAcademicaDominio convertirADominio(VinculacionAcademicaDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new VinculacionAcademicaDTO());
		return new VinculacionAcademicaDominio.Builder()
				.id(dtoTmp.getId())
				.usuario(UsuarioDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getUsuario()))
				.programaAcademico(ProgramaAcademicoDTOAssembler.obtenerInstancia()
						.convertirADominio(dtoTmp.getProgramaAcademico()))
				.fechaVinculacion(dtoTmp.getFechaVinculacion())
				.fechaDesvinculacion(dtoTmp.getFechaDesvinculacion())
				.build();
	}
}
