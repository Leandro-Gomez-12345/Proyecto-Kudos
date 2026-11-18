package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.CoordinacionProgramaDominio;
import co.edu.uco.kudos.entidad.CoordinacionProgramaEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class CoordinacionProgramaEntidadAssembler
		implements EntidadAssembler<CoordinacionProgramaDominio, CoordinacionProgramaEntidad> {

	private static final EntidadAssembler<CoordinacionProgramaDominio, CoordinacionProgramaEntidad> INSTANCIA =
			new CoordinacionProgramaEntidadAssembler();

	private CoordinacionProgramaEntidadAssembler() {

	}

	public static EntidadAssembler<CoordinacionProgramaDominio, CoordinacionProgramaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public CoordinacionProgramaEntidad convertirAEntidad(CoordinacionProgramaDominio dominio) {
		var dominioTmp = CoordinacionProgramaDominio.obtenerValorDefecto(dominio);
		var entidad = new CoordinacionProgramaEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setUsuario(UsuarioEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getUsuario()));
		entidad.setProgramaAcademico(ProgramaAcademicoEntidadAssembler.obtenerInstancia()
				.convertirAEntidad(dominioTmp.getProgramaAcademico()));
		entidad.setFechaInicio(dominioTmp.getFechaInicio());
		entidad.setFechaFin(dominioTmp.getFechaFin());
		return entidad;
	}

	@Override
	public CoordinacionProgramaDominio convertirADominio(CoordinacionProgramaEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
				entidad, new CoordinacionProgramaEntidad());
		return new CoordinacionProgramaDominio.Builder()
				.id(entidadTmp.getId())
				.usuario(UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getUsuario()))
				.programaAcademico(ProgramaAcademicoEntidadAssembler.obtenerInstancia()
						.convertirADominio(entidadTmp.getProgramaAcademico()))
				.fechaInicio(entidadTmp.getFechaInicio())
				.fechaFin(entidadTmp.getFechaFin())
				.build();
	}
}
