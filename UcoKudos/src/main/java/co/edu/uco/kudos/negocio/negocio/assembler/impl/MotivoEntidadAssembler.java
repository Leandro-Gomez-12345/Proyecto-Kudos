package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.MotivoDominio;
import co.edu.uco.kudos.entidad.MotivoEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class MotivoEntidadAssembler implements EntidadAssembler<MotivoDominio, MotivoEntidad> {

	private static final EntidadAssembler<MotivoDominio, MotivoEntidad> INSTANCIA = new MotivoEntidadAssembler();

	private MotivoEntidadAssembler() {

	}

	public static EntidadAssembler<MotivoDominio, MotivoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public MotivoEntidad convertirAEntidad(MotivoDominio dominio) {
		var dominioTmp = MotivoDominio.obtenerValorDefecto(dominio);
		var entidad = new MotivoEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setNombre(dominioTmp.getNombre());
		return entidad;
	}

	@Override
	public MotivoDominio convertirADominio(MotivoEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new MotivoEntidad());
		return new MotivoDominio.Builder()
				.id(entidadTmp.getId())
				.nombre(entidadTmp.getNombre())
				.build();
	}
}
