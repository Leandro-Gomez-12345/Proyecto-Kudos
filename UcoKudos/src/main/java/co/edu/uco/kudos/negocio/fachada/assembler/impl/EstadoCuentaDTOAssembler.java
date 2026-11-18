package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.EstadoCuentaDominio;
import co.edu.uco.kudos.dto.EstadoCuentaDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class EstadoCuentaDTOAssembler implements DTOAssembler<EstadoCuentaDominio, EstadoCuentaDTO> {

	private static final DTOAssembler<EstadoCuentaDominio, EstadoCuentaDTO> INSTANCIA = new EstadoCuentaDTOAssembler();

	private EstadoCuentaDTOAssembler() {

	}

	public static DTOAssembler<EstadoCuentaDominio, EstadoCuentaDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public EstadoCuentaDTO convertirADTO(EstadoCuentaDominio dominio) {
		var dominioTmp = EstadoCuentaDominio.obtenerValorDefecto(dominio);
		var dto = new EstadoCuentaDTO();
		dto.setId(dominioTmp.getId());
		dto.setNombre(dominioTmp.getNombre());
		return dto;
	}

	@Override
	public EstadoCuentaDominio convertirADominio(EstadoCuentaDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new EstadoCuentaDTO());
		return new EstadoCuentaDominio.Builder()
				.id(dtoTmp.getId())
				.nombre(dtoTmp.getNombre())
				.build();
	}
}
