package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.FacultadDominio;
import co.edu.uco.kudos.entidad.FacultadEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class FacultadEntidadAssembler implements EntidadAssembler<FacultadDominio, FacultadEntidad> {

	private static final EntidadAssembler<FacultadDominio, FacultadEntidad> INSTANCIA = new FacultadEntidadAssembler();

	private FacultadEntidadAssembler() {

	}

	public static EntidadAssembler<FacultadDominio, FacultadEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public FacultadEntidad convertirAEntidad(FacultadDominio dominio) {
		var dominioTmp = FacultadDominio.obtenerValorDefecto(dominio);
		var entidad = new FacultadEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setNombre(dominioTmp.getNombre());
		return entidad;
	}

	@Override
	public FacultadDominio convertirADominio(FacultadEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new FacultadEntidad());
		return new FacultadDominio.Builder()
				.id(entidadTmp.getId())
				.nombre(entidadTmp.getNombre())
				.build();
	}
}
