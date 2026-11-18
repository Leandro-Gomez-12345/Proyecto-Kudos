package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.KudoDominio;
import co.edu.uco.kudos.dto.KudoDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class KudoDTOAssembler implements DTOAssembler<KudoDominio, KudoDTO> {

	private static final DTOAssembler<KudoDominio, KudoDTO> INSTANCIA = new KudoDTOAssembler();

	private KudoDTOAssembler() {

	}

	public static DTOAssembler<KudoDominio, KudoDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public KudoDTO convertirADTO(KudoDominio dominio) {
		var dominioTmp = KudoDominio.obtenerValorDefecto(dominio);
		var dto = new KudoDTO();
		dto.setId(dominioTmp.getId());
		dto.setAutor(UsuarioDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getAutor()));
		dto.setDestinatario(UsuarioDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getDestinatario()));
		dto.setCategoria(CategoriaDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getCategoria()));
		dto.setNombreCategoria(dominioTmp.getNombreCategoria());
		dto.setContenido(dominioTmp.getContenido());
		dto.setEstadoContenido(EstadoContenidoDTOAssembler.obtenerInstancia()
				.convertirADTO(dominioTmp.getEstadoContenido()));
		dto.setFechaCreacion(dominioTmp.getFechaCreacion());
		return dto;
	}

	@Override
	public KudoDominio convertirADominio(KudoDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new KudoDTO());
		return new KudoDominio.Builder()
				.id(dtoTmp.getId())
				.autor(UsuarioDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getAutor()))
				.destinatario(UsuarioDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getDestinatario()))
				.categoria(CategoriaDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getCategoria()))
				.nombreCategoria(dtoTmp.getNombreCategoria())
				.contenido(dtoTmp.getContenido())
				.estadoContenido(EstadoContenidoDTOAssembler.obtenerInstancia()
						.convertirADominio(dtoTmp.getEstadoContenido()))
				.fechaCreacion(dtoTmp.getFechaCreacion())
				.build();
	}
}
