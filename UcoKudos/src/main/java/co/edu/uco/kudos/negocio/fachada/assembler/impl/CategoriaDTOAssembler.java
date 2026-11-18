package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.CategoriaDominio;
import co.edu.uco.kudos.dto.CategoriaDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class CategoriaDTOAssembler implements DTOAssembler<CategoriaDominio, CategoriaDTO> {

	private static final DTOAssembler<CategoriaDominio, CategoriaDTO> INSTANCIA = new CategoriaDTOAssembler();

	private CategoriaDTOAssembler() {

	}

	public static DTOAssembler<CategoriaDominio, CategoriaDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public CategoriaDTO convertirADTO(CategoriaDominio dominio) {
		var dominioTmp = CategoriaDominio.obtenerValorDefecto(dominio);
		var dto = new CategoriaDTO();
		dto.setId(dominioTmp.getId());
		dto.setNombre(dominioTmp.getNombre());
		dto.setDescripcion(dominioTmp.getDescripcion());
		dto.setActiva(dominioTmp.isActiva());
		return dto;
	}

	@Override
	public CategoriaDominio convertirADominio(CategoriaDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new CategoriaDTO());
		return new CategoriaDominio.Builder()
				.id(dtoTmp.getId())
				.nombre(dtoTmp.getNombre())
				.descripcion(dtoTmp.getDescripcion())
				.activa(dtoTmp.isActiva())
				.build();
	}
}
