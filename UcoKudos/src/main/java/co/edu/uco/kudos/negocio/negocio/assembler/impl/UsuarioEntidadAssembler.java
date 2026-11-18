package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.UsuarioDominio;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class UsuarioEntidadAssembler implements EntidadAssembler<UsuarioDominio, UsuarioEntidad> {

	private static final EntidadAssembler<UsuarioDominio, UsuarioEntidad> INSTANCIA = new UsuarioEntidadAssembler();

	private UsuarioEntidadAssembler() {

	}

	public static EntidadAssembler<UsuarioDominio, UsuarioEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public UsuarioEntidad convertirAEntidad(UsuarioDominio dominio) {
		var dominioTmp = UsuarioDominio.obtenerValorDefecto(dominio);
		var entidad = new UsuarioEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setNombre(dominioTmp.getNombre());
		entidad.setApellido(dominioTmp.getApellido());
		entidad.setCorreoInstitucional(dominioTmp.getCorreoInstitucional());
		entidad.setCelular(dominioTmp.getCelular());
		entidad.setCorreoVerificado(dominioTmp.isCorreoVerificado());
		entidad.setCelularVerificado(dominioTmp.isCelularVerificado());
		entidad.setRol(RolEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getRol()));
		entidad.setEstadoCuenta(EstadoCuentaEntidadAssembler.obtenerInstancia()
				.convertirAEntidad(dominioTmp.getEstadoCuenta()));
		entidad.setFechaRegistro(dominioTmp.getFechaRegistro());
		return entidad;
	}

	@Override
	public UsuarioDominio convertirADominio(UsuarioEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new UsuarioEntidad());
		return new UsuarioDominio.Builder()
				.id(entidadTmp.getId())
				.nombre(entidadTmp.getNombre())
				.apellido(entidadTmp.getApellido())
				.correoInstitucional(entidadTmp.getCorreoInstitucional())
				.celular(entidadTmp.getCelular())
				.correoVerificado(entidadTmp.isCorreoVerificado())
				.celularVerificado(entidadTmp.isCelularVerificado())
				.rol(RolEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getRol()))
				.estadoCuenta(EstadoCuentaEntidadAssembler.obtenerInstancia()
						.convertirADominio(entidadTmp.getEstadoCuenta()))
				.fechaRegistro(entidadTmp.getFechaRegistro())
				.build();
	}
}
