package co.edu.uco.kudos.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ReporteReplicaDTO {

	private UUID id;
	private ReplicaDTO replica;
	private UsuarioDTO usuario;
	private MotivoDTO motivo;
	private String descripcion;
	private RevisionReplicaDTO revisionReplica;
	private LocalDateTime fechaReporte;

	public ReporteReplicaDTO() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setReplica(new ReplicaDTO());
		setUsuario(new UsuarioDTO());
		setMotivo(new MotivoDTO());
		setDescripcion(UtilTexto.VACIA);
		setRevisionReplica(new RevisionReplicaDTO());
		setFechaReporte(UtilFechaHora.FECHA_HORA_DEFECTO);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public ReplicaDTO getReplica() {
		return replica;
	}

	public void setReplica(ReplicaDTO replica) {
		this.replica = UtilObjeto.esNulo(replica) ? new ReplicaDTO() : replica;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = UtilObjeto.esNulo(usuario) ? new UsuarioDTO() : usuario;
	}

	public MotivoDTO getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoDTO motivo) {
		this.motivo = UtilObjeto.esNulo(motivo) ? new MotivoDTO() : motivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = UtilTexto.quitarEspaciosEnBlanco(descripcion);
	}

	public RevisionReplicaDTO getRevisionReplica() {
		return revisionReplica;
	}

	public void setRevisionReplica(RevisionReplicaDTO revisionReplica) {
		this.revisionReplica = UtilObjeto.esNulo(revisionReplica) ? new RevisionReplicaDTO() : revisionReplica;
	}

	public LocalDateTime getFechaReporte() {
		return fechaReporte;
	}

	public void setFechaReporte(LocalDateTime fechaReporte) {
		this.fechaReporte = UtilFechaHora.obtenerValorDefecto(fechaReporte);
	}
}
