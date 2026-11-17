package co.edu.uco.kudos.entidad;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ReporteReplicaEntidad {

	private UUID id;
	private ReplicaEntidad replica;
	private UsuarioEntidad usuario;
	private MotivoEntidad motivo;
	private String descripcion;
	private RevisionReplicaEntidad revisionReplica;
	private LocalDateTime fechaReporte;

	public ReporteReplicaEntidad() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setReplica(new ReplicaEntidad());
		setUsuario(new UsuarioEntidad());
		setMotivo(new MotivoEntidad());
		setDescripcion(UtilTexto.VACIA);
		setRevisionReplica(new RevisionReplicaEntidad());
		setFechaReporte(UtilFechaHora.FECHA_HORA_DEFECTO);
	}

	public ReporteReplicaEntidad(UUID id, ReplicaEntidad replica, UsuarioEntidad usuario, MotivoEntidad motivo,
			String descripcion, RevisionReplicaEntidad revisionReplica, LocalDateTime fechaReporte) {
		setId(id);
		setReplica(replica);
		setUsuario(usuario);
		setMotivo(motivo);
		setDescripcion(descripcion);
		setRevisionReplica(revisionReplica);
		setFechaReporte(fechaReporte);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public ReplicaEntidad getReplica() {
		return replica;
	}

	public void setReplica(ReplicaEntidad replica) {
		this.replica = UtilObjeto.esNulo(replica) ? new ReplicaEntidad() : replica;
	}

	public UsuarioEntidad getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntidad usuario) {
		this.usuario = UtilObjeto.esNulo(usuario) ? new UsuarioEntidad() : usuario;
	}

	public MotivoEntidad getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoEntidad motivo) {
		this.motivo = UtilObjeto.esNulo(motivo) ? new MotivoEntidad() : motivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = UtilTexto.quitarEspaciosEnBlanco(descripcion);
	}

	public RevisionReplicaEntidad getRevisionReplica() {
		return revisionReplica;
	}

	public void setRevisionReplica(RevisionReplicaEntidad revisionReplica) {
		this.revisionReplica = UtilObjeto.esNulo(revisionReplica) ? new RevisionReplicaEntidad() : revisionReplica;
	}

	public LocalDateTime getFechaReporte() {
		return fechaReporte;
	}

	public void setFechaReporte(LocalDateTime fechaReporte) {
		this.fechaReporte = UtilFechaHora.obtenerValorDefecto(fechaReporte);
	}
}
