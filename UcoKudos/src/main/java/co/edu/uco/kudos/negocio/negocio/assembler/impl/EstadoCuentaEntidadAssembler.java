package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.EstadoCuentaDominio;
import co.edu.uco.kudos.entidad.EstadoCuentaEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class EstadoCuentaEntidadAssembler implements EntidadAssembler<EstadoCuentaDominio, EstadoCuentaEntidad> {

	private static final EntidadAssembler<EstadoCuentaDominio, EstadoCuentaEntidad> INSTANCIA =
			new EstadoCuentaEntidadAssembler();

	private EstadoCuentaEntidadAssembler() {

	}

	public static EntidadAssembler<EstadoCuentaDominio, EstadoCuentaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public EstadoCuentaEntidad convertirAEntidad(EstadoCuentaDominio dominio) {
		var dominioTmp = EstadoCuentaDominio.obtenerValorDefecto(dominio);
		var entidad = new EstadoCuentaEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setNombre(dominioTmp.getNombre());
		return entidad;
	}

	@Override
	public EstadoCuentaDominio convertirADominio(EstadoCuentaEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new EstadoCuentaEntidad());
		return new EstadoCuentaDominio.Builder()
				.id(entidadTmp.getId())
				.nombre(entidadTmp.getNombre())
				.build();
	}
}
