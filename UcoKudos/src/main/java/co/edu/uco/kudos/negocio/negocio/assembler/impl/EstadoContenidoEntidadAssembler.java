package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.EstadoContenidoDominio;
import co.edu.uco.kudos.entidad.EstadoContenidoEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class EstadoContenidoEntidadAssembler
		implements EntidadAssembler<EstadoContenidoDominio, EstadoContenidoEntidad> {

	private static final EntidadAssembler<EstadoContenidoDominio, EstadoContenidoEntidad> INSTANCIA =
			new EstadoContenidoEntidadAssembler();

	private EstadoContenidoEntidadAssembler() {

	}

	public static EntidadAssembler<EstadoContenidoDominio, EstadoContenidoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public EstadoContenidoEntidad convertirAEntidad(EstadoContenidoDominio dominio) {
		var dominioTmp = EstadoContenidoDominio.obtenerValorDefecto(dominio);
		var entidad = new EstadoContenidoEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setNombre(dominioTmp.getNombre());
		return entidad;
	}

	@Override
	public EstadoContenidoDominio convertirADominio(EstadoContenidoEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new EstadoContenidoEntidad());
		return new EstadoContenidoDominio.Builder()
				.id(entidadTmp.getId())
				.nombre(entidadTmp.getNombre())
				.build();
	}
}
