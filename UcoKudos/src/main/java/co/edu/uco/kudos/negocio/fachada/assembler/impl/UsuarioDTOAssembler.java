package co.edu.uco.kudos.negocio.fachada.assembler.impl;

import co.edu.uco.kudos.dominio.UsuarioDominio;
import co.edu.uco.kudos.dto.UsuarioDTO;
import co.edu.uco.kudos.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class UsuarioDTOAssembler implements DTOAssembler<UsuarioDominio, UsuarioDTO> {

	private static final DTOAssembler<UsuarioDominio, UsuarioDTO> INSTANCIA = new UsuarioDTOAssembler();

	private UsuarioDTOAssembler() {

	}

	public static DTOAssembler<UsuarioDominio, UsuarioDTO> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public UsuarioDTO convertirADTO(UsuarioDominio dominio) {
		var dominioTmp = UsuarioDominio.obtenerValorDefecto(dominio);
		var dto = new UsuarioDTO();
		dto.setId(dominioTmp.getId());
		dto.setNombre(dominioTmp.getNombre());
		dto.setApellido(dominioTmp.getApellido());
		dto.setCorreoInstitucional(dominioTmp.getCorreoInstitucional());
		dto.setCelular(dominioTmp.getCelular());
		dto.setCorreoVerificado(dominioTmp.isCorreoVerificado());
		dto.setCelularVerificado(dominioTmp.isCelularVerificado());
		dto.setRol(RolDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getRol()));
		dto.setEstadoCuenta(EstadoCuentaDTOAssembler.obtenerInstancia().convertirADTO(dominioTmp.getEstadoCuenta()));
		dto.setFechaRegistro(dominioTmp.getFechaRegistro());
		return dto;
	}

	@Override
	public UsuarioDominio convertirADominio(UsuarioDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new UsuarioDTO());
		return new UsuarioDominio.Builder()
				.id(dtoTmp.getId())
				.nombre(dtoTmp.getNombre())
				.apellido(dtoTmp.getApellido())
				.correoInstitucional(dtoTmp.getCorreoInstitucional())
				.celular(dtoTmp.getCelular())
				.correoVerificado(dtoTmp.isCorreoVerificado())
				.celularVerificado(dtoTmp.isCelularVerificado())
				.rol(RolDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getRol()))
				.estadoCuenta(EstadoCuentaDTOAssembler.obtenerInstancia().convertirADominio(dtoTmp.getEstadoCuenta()))
				.fechaRegistro(dtoTmp.getFechaRegistro())
				.build();
	}
}
