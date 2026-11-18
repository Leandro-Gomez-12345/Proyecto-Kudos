package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.CalificacionKudoDominio;
import co.edu.uco.kudos.dto.CalificacionKudoDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class CalificacionKudoDTOAssembler implements DTOAssembler<CalificacionKudoDominio, CalificacionKudoDTO> {

	private static final DTOAssembler<CalificacionKudoDominio, CalificacionKudoDTO> INSTANCIA =
			new CalificacionKudoDTOAssembler();

	private CalificacionKudoDTOAssembler() {

	}

	public static DTOAssembler<CalificacionKudoDominio, CalificacionKudoDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public CalificacionKudoDTO convertirADTO(CalificacionKudoDominio dominio) {
		var dominioTmp = CalificacionKudoDominio.obtenerValorDefecto(dominio);
		var dto = new CalificacionKudoDTO();
		dto.setId(dominioTmp.getId());
		dto.setKudo(KudoDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getKudo()));
		dto.setUsuario(UsuarioDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getUsuario()));
		dto.setEstrellas(dominioTmp.getEstrellas());
		dto.setFechaCalificacion(dominioTmp.getFechaCalificacion());
		return dto;
	}

	@Override
	public CalificacionKudoDominio convertirADominio(CalificacionKudoDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new CalificacionKudoDTO());
		return new CalificacionKudoDominio.Builder()
				.id(dtoTmp.getId())
				.kudo(KudoDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getKudo()))
				.usuario(UsuarioDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getUsuario()))
				.estrellas(dtoTmp.getEstrellas())
				.fechaCalificacion(dtoTmp.getFechaCalificacion())
				.build();
	}
}
