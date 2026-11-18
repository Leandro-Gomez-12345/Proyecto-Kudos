package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.ReporteKudoDominio;
import co.edu.uco.kudos.entidad.ReporteKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class ReporteKudoEntidadAssembler implements EntidadAssembler<ReporteKudoDominio, ReporteKudoEntidad> {

	private static final EntidadAssembler<ReporteKudoDominio, ReporteKudoEntidad> INSTANCIA =
			new ReporteKudoEntidadAssembler();

	private ReporteKudoEntidadAssembler() {

	}

	public static EntidadAssembler<ReporteKudoDominio, ReporteKudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public ReporteKudoEntidad convertirAEntidad(ReporteKudoDominio dominio) {
		var dominioTmp = ReporteKudoDominio.obtenerValorDefecto(dominio);
		var entidad = new ReporteKudoEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setKudo(KudoEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getKudo()));
		entidad.setUsuario(UsuarioEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getUsuario()));
		entidad.setMotivo(MotivoEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getMotivo()));
		entidad.setDescripcion(dominioTmp.getDescripcion());
		entidad.setRevisionKudo(RevisionKudoEntidadAssembler.obtenerInstancia()
				.convertirAEntidad(dominioTmp.getRevisionKudo()));
		entidad.setFechaReporte(dominioTmp.getFechaReporte());
		return entidad;
	}

	@Override
	public ReporteKudoDominio convertirADominio(ReporteKudoEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new ReporteKudoEntidad());
		return new ReporteKudoDominio.Builder()
				.id(entidadTmp.getId())
				.kudo(KudoEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getKudo()))
				.usuario(UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getUsuario()))
				.motivo(MotivoEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getMotivo()))
				.descripcion(entidadTmp.getDescripcion())
				.revisionKudo(RevisionKudoEntidadAssembler.obtenerInstancia()
						.convertirADominio(entidadTmp.getRevisionKudo()))
				.fechaReporte(entidadTmp.getFechaReporte())
				.build();
	}
}
