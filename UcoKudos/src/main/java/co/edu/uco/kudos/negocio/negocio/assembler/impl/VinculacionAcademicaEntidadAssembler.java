package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.VinculacionAcademicaDominio;
import co.edu.uco.kudos.entidad.VinculacionAcademicaEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class VinculacionAcademicaEntidadAssembler
		implements EntidadAssembler<VinculacionAcademicaDominio, VinculacionAcademicaEntidad> {

	private static final EntidadAssembler<VinculacionAcademicaDominio, VinculacionAcademicaEntidad> INSTANCIA =
			new VinculacionAcademicaEntidadAssembler();

	private VinculacionAcademicaEntidadAssembler() {

	}

	public static EntidadAssembler<VinculacionAcademicaDominio, VinculacionAcademicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public VinculacionAcademicaEntidad convertirAEntidad(VinculacionAcademicaDominio dominio) {
		var dominioTmp = VinculacionAcademicaDominio.obtenerValorDefecto(dominio);
		var entidad = new VinculacionAcademicaEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setUsuario(UsuarioEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getUsuario()));
		entidad.setProgramaAcademico(ProgramaAcademicoEntidadAssembler.obtenerInstancia()
				.convertirAEntidad(dominioTmp.getProgramaAcademico()));
		entidad.setFechaVinculacion(dominioTmp.getFechaVinculacion());
		entidad.setFechaDesvinculacion(dominioTmp.getFechaDesvinculacion());
		return entidad;
	}

	@Override
	public VinculacionAcademicaDominio convertirADominio(VinculacionAcademicaEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
				entidad, new VinculacionAcademicaEntidad());
		return new VinculacionAcademicaDominio.Builder()
				.id(entidadTmp.getId())
				.usuario(UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getUsuario()))
				.programaAcademico(ProgramaAcademicoEntidadAssembler.obtenerInstancia()
						.convertirADominio(entidadTmp.getProgramaAcademico()))
				.fechaVinculacion(entidadTmp.getFechaVinculacion())
				.fechaDesvinculacion(entidadTmp.getFechaDesvinculacion())
				.build();
	}
}
