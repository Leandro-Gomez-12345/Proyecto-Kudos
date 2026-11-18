package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.ProgramaAcademicoDominio;
import co.edu.uco.kudos.entidad.ProgramaAcademicoEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class ProgramaAcademicoEntidadAssembler
		implements EntidadAssembler<ProgramaAcademicoDominio, ProgramaAcademicoEntidad> {

	private static final EntidadAssembler<ProgramaAcademicoDominio, ProgramaAcademicoEntidad> INSTANCIA =
			new ProgramaAcademicoEntidadAssembler();

	private ProgramaAcademicoEntidadAssembler() {

	}

	public static EntidadAssembler<ProgramaAcademicoDominio, ProgramaAcademicoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public ProgramaAcademicoEntidad convertirAEntidad(ProgramaAcademicoDominio dominio) {
		var dominioTmp = ProgramaAcademicoDominio.obtenerValorDefecto(dominio);
		var entidad = new ProgramaAcademicoEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setFacultad(FacultadEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getFacultad()));
		entidad.setNombre(dominioTmp.getNombre());
		return entidad;
	}

	@Override
	public ProgramaAcademicoDominio convertirADominio(ProgramaAcademicoEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new ProgramaAcademicoEntidad());
		return new ProgramaAcademicoDominio.Builder()
				.id(entidadTmp.getId())
				.facultad(FacultadEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getFacultad()))
				.nombre(entidadTmp.getNombre())
				.build();
	}
}
