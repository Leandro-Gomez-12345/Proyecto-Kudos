package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.RevisionKudoDominio;
import co.edu.uco.kudos.entidad.RevisionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class RevisionKudoEntidadAssembler implements EntidadAssembler<RevisionKudoDominio, RevisionKudoEntidad> {

	private static final EntidadAssembler<RevisionKudoDominio, RevisionKudoEntidad> INSTANCIA =
			new RevisionKudoEntidadAssembler();

	private RevisionKudoEntidadAssembler() {

	}

	public static EntidadAssembler<RevisionKudoDominio, RevisionKudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public RevisionKudoEntidad convertirAEntidad(RevisionKudoDominio dominio) {
		var dominioTmp = RevisionKudoDominio.obtenerValorDefecto(dominio);
		var entidad = new RevisionKudoEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setKudo(KudoEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getKudo()));
		entidad.setModerador(UsuarioEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getModerador()));
		entidad.setEstadoRevision(EstadoRevisionEntidadAssembler.obtenerInstancia()
				.convertirAEntidad(dominioTmp.getEstadoRevision()));
		entidad.setJustificacion(dominioTmp.getJustificacion());
		entidad.setFechaInicio(dominioTmp.getFechaInicio());
		entidad.setFechaCierre(dominioTmp.getFechaCierre());
		return entidad;
	}

	@Override
	public RevisionKudoDominio convertirADominio(RevisionKudoEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new RevisionKudoEntidad());
		return new RevisionKudoDominio.Builder()
				.id(entidadTmp.getId())
				.kudo(KudoEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getKudo()))
				.moderador(UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getModerador()))
				.estadoRevision(EstadoRevisionEntidadAssembler.obtenerInstancia()
						.convertirADominio(entidadTmp.getEstadoRevision()))
				.justificacion(entidadTmp.getJustificacion())
				.fechaInicio(entidadTmp.getFechaInicio())
				.fechaCierre(entidadTmp.getFechaCierre())
				.build();
	}
}
