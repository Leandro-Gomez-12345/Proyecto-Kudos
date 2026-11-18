package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.ReporteReplicaDominio;
import co.edu.uco.kudos.entidad.ReporteReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class ReporteReplicaEntidadAssembler
		implements EntidadAssembler<ReporteReplicaDominio, ReporteReplicaEntidad> {

	private static final EntidadAssembler<ReporteReplicaDominio, ReporteReplicaEntidad> INSTANCIA =
			new ReporteReplicaEntidadAssembler();

	private ReporteReplicaEntidadAssembler() {

	}

	public static EntidadAssembler<ReporteReplicaDominio, ReporteReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public ReporteReplicaEntidad convertirAEntidad(ReporteReplicaDominio dominio) {
		var dominioTmp = ReporteReplicaDominio.obtenerValorDefecto(dominio);
		var entidad = new ReporteReplicaEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setReplica(ReplicaEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getReplica()));
		entidad.setUsuario(UsuarioEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getUsuario()));
		entidad.setMotivo(MotivoEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getMotivo()));
		entidad.setDescripcion(dominioTmp.getDescripcion());
		entidad.setRevisionReplica(RevisionReplicaEntidadAssembler.obtenerInstancia()
				.convertirAEntidad(dominioTmp.getRevisionReplica()));
		entidad.setFechaReporte(dominioTmp.getFechaReporte());
		return entidad;
	}

	@Override
	public ReporteReplicaDominio convertirADominio(ReporteReplicaEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new ReporteReplicaEntidad());
		return new ReporteReplicaDominio.Builder()
				.id(entidadTmp.getId())
				.replica(ReplicaEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getReplica()))
				.usuario(UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getUsuario()))
				.motivo(MotivoEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getMotivo()))
				.descripcion(entidadTmp.getDescripcion())
				.revisionReplica(RevisionReplicaEntidadAssembler.obtenerInstancia()
						.convertirADominio(entidadTmp.getRevisionReplica()))
				.fechaReporte(entidadTmp.getFechaReporte())
				.build();
	}
}
