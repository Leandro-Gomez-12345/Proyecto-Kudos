package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.MotivoDominio;
import co.edu.uco.kudos.dto.MotivoDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class MotivoDTOAssembler implements DTOAssembler<MotivoDominio, MotivoDTO> {

	private static final DTOAssembler<MotivoDominio, MotivoDTO> INSTANCIA = new MotivoDTOAssembler();

	private MotivoDTOAssembler() {

	}

	public static DTOAssembler<MotivoDominio, MotivoDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public MotivoDTO convertirADTO(MotivoDominio dominio) {
		var dominioTmp = MotivoDominio.obtenerValorDefecto(dominio);
		var dto = new MotivoDTO();
		dto.setId(dominioTmp.getId());
		dto.setNombre(dominioTmp.getNombre());
		return dto;
	}

	@Override
	public MotivoDominio convertirADominio(MotivoDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new MotivoDTO());
		return new MotivoDominio.Builder()
				.id(dtoTmp.getId())
				.nombre(dtoTmp.getNombre())
				.build();
	}
}
