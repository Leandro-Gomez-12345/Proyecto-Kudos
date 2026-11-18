package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.RevisionReplicaDominio;
import co.edu.uco.kudos.entidad.RevisionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class RevisionReplicaEntidadAssembler
		implements EntidadAssembler<RevisionReplicaDominio, RevisionReplicaEntidad> {

	private static final EntidadAssembler<RevisionReplicaDominio, RevisionReplicaEntidad> INSTANCIA =
			new RevisionReplicaEntidadAssembler();

	private RevisionReplicaEntidadAssembler() {

	}

	public static EntidadAssembler<RevisionReplicaDominio, RevisionReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public RevisionReplicaEntidad convertirAEntidad(RevisionReplicaDominio dominio) {
		var dominioTmp = RevisionReplicaDominio.obtenerValorDefecto(dominio);
		var entidad = new RevisionReplicaEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setReplica(ReplicaEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getReplica()));
		entidad.setModerador(UsuarioEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getModerador()));
		entidad.setEstadoRevision(EstadoRevisionEntidadAssembler.obtenerInstancia()
				.convertirAEntidad(dominioTmp.getEstadoRevision()));
		entidad.setJustificacion(dominioTmp.getJustificacion());
		entidad.setFechaInicio(dominioTmp.getFechaInicio());
		entidad.setFechaCierre(dominioTmp.getFechaCierre());
		return entidad;
	}

	@Override
	public RevisionReplicaDominio convertirADominio(RevisionReplicaEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new RevisionReplicaEntidad());
		return new RevisionReplicaDominio.Builder()
				.id(entidadTmp.getId())
				.replica(ReplicaEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getReplica()))
				.moderador(UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getModerador()))
				.estadoRevision(EstadoRevisionEntidadAssembler.obtenerInstancia()
						.convertirADominio(entidadTmp.getEstadoRevision()))
				.justificacion(entidadTmp.getJustificacion())
				.fechaInicio(entidadTmp.getFechaInicio())
				.fechaCierre(entidadTmp.getFechaCierre())
				.build();
	}
}
