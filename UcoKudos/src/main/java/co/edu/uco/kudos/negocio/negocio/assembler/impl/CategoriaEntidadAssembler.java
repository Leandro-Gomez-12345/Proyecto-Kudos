package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.CategoriaDominio;
import co.edu.uco.kudos.entidad.CategoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class CategoriaEntidadAssembler implements EntidadAssembler<CategoriaDominio, CategoriaEntidad> {

	private static final EntidadAssembler<CategoriaDominio, CategoriaEntidad> INSTANCIA =
			new CategoriaEntidadAssembler();

	private CategoriaEntidadAssembler() {

	}

	public static EntidadAssembler<CategoriaDominio, CategoriaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public CategoriaEntidad convertirAEntidad(CategoriaDominio dominio) {
		var dominioTmp = CategoriaDominio.obtenerValorDefecto(dominio);
		var entidad = new CategoriaEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setNombre(dominioTmp.getNombre());
		entidad.setDescripcion(dominioTmp.getDescripcion());
		entidad.setActiva(dominioTmp.isActiva());
		return entidad;
	}

	@Override
	public CategoriaDominio convertirADominio(CategoriaEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new CategoriaEntidad());
		return new CategoriaDominio.Builder()
				.id(entidadTmp.getId())
				.nombre(entidadTmp.getNombre())
				.descripcion(entidadTmp.getDescripcion())
				.activa(entidadTmp.isActiva())
				.build();
	}
}
