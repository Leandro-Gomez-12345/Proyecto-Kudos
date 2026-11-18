package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.CoordinacionFacultadDominio;
import co.edu.uco.kudos.entidad.CoordinacionFacultadEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class CoordinacionFacultadEntidadAssembler
		implements EntidadAssembler<CoordinacionFacultadDominio, CoordinacionFacultadEntidad> {

	private static final EntidadAssembler<CoordinacionFacultadDominio, CoordinacionFacultadEntidad> INSTANCIA =
			new CoordinacionFacultadEntidadAssembler();

	private CoordinacionFacultadEntidadAssembler() {

	}

	public static EntidadAssembler<CoordinacionFacultadDominio, CoordinacionFacultadEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public CoordinacionFacultadEntidad convertirAEntidad(CoordinacionFacultadDominio dominio) {
		var dominioTmp = CoordinacionFacultadDominio.obtenerValorDefecto(dominio);
		var entidad = new CoordinacionFacultadEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setUsuario(UsuarioEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getUsuario()));
		entidad.setFacultad(FacultadEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getFacultad()));
		entidad.setFechaInicio(dominioTmp.getFechaInicio());
		entidad.setFechaFin(dominioTmp.getFechaFin());
		return entidad;
	}

	@Override
	public CoordinacionFacultadDominio convertirADominio(CoordinacionFacultadEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
				entidad, new CoordinacionFacultadEntidad());
		return new CoordinacionFacultadDominio.Builder()
				.id(entidadTmp.getId())
				.usuario(UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getUsuario()))
				.facultad(FacultadEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getFacultad()))
				.fechaInicio(entidadTmp.getFechaInicio())
				.fechaFin(entidadTmp.getFechaFin())
				.build();
	}
}
