package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.RolDominio;
import co.edu.uco.kudos.entidad.RolEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class RolEntidadAssembler implements EntidadAssembler<RolDominio, RolEntidad> {

	private static final EntidadAssembler<RolDominio, RolEntidad> INSTANCIA = new RolEntidadAssembler();

	private RolEntidadAssembler() {

	}

	public static EntidadAssembler<RolDominio, RolEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public RolEntidad convertirAEntidad(RolDominio dominio) {
		var dominioTmp = RolDominio.obtenerValorDefecto(dominio);
		var entidad = new RolEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setNombre(dominioTmp.getNombre());
		return entidad;
	}

	@Override
	public RolDominio convertirADominio(RolEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new RolEntidad());
		return new RolDominio.Builder()
				.id(entidadTmp.getId())
				.nombre(entidadTmp.getNombre())
				.build();
	}
}
