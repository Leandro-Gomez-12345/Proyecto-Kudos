package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.CalificacionKudoDominio;
import co.edu.uco.kudos.entidad.CalificacionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class CalificacionKudoEntidadAssembler
		implements EntidadAssembler<CalificacionKudoDominio, CalificacionKudoEntidad> {

	private static final EntidadAssembler<CalificacionKudoDominio, CalificacionKudoEntidad> INSTANCIA =
			new CalificacionKudoEntidadAssembler();

	private CalificacionKudoEntidadAssembler() {

	}

	public static EntidadAssembler<CalificacionKudoDominio, CalificacionKudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public CalificacionKudoEntidad convertirAEntidad(CalificacionKudoDominio dominio) {
		var dominioTmp = CalificacionKudoDominio.obtenerValorDefecto(dominio);
		var entidad = new CalificacionKudoEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setKudo(KudoEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getKudo()));
		entidad.setUsuario(UsuarioEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getUsuario()));
		entidad.setEstrellas(dominioTmp.getEstrellas());
		entidad.setFechaCalificacion(dominioTmp.getFechaCalificacion());
		return entidad;
	}

	@Override
	public CalificacionKudoDominio convertirADominio(CalificacionKudoEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new CalificacionKudoEntidad());
		return new CalificacionKudoDominio.Builder()
				.id(entidadTmp.getId())
				.kudo(KudoEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getKudo()))
				.usuario(UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getUsuario()))
				.estrellas(entidadTmp.getEstrellas())
				.fechaCalificacion(entidadTmp.getFechaCalificacion())
				.build();
	}
}
