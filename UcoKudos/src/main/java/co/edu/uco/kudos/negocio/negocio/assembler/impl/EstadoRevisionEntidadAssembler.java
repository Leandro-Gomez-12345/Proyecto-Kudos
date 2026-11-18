package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.EstadoRevisionDominio;
import co.edu.uco.kudos.entidad.EstadoRevisionEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class EstadoRevisionEntidadAssembler
		implements EntidadAssembler<EstadoRevisionDominio, EstadoRevisionEntidad> {

	private static final EntidadAssembler<EstadoRevisionDominio, EstadoRevisionEntidad> INSTANCIA =
			new EstadoRevisionEntidadAssembler();

	private EstadoRevisionEntidadAssembler() {

	}

	public static EntidadAssembler<EstadoRevisionDominio, EstadoRevisionEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public EstadoRevisionEntidad convertirAEntidad(EstadoRevisionDominio dominio) {
		var dominioTmp = EstadoRevisionDominio.obtenerValorDefecto(dominio);
		var entidad = new EstadoRevisionEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setNombre(dominioTmp.getNombre());
		return entidad;
	}

	@Override
	public EstadoRevisionDominio convertirADominio(EstadoRevisionEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new EstadoRevisionEntidad());
		return new EstadoRevisionDominio.Builder()
				.id(entidadTmp.getId())
				.nombre(entidadTmp.getNombre())
				.build();
	}
}
