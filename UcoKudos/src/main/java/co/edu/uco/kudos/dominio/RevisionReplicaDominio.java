package co.edu.uco.kudos.dominio;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public class RevisionReplicaDominio {

	private UUID id;
	private ReplicaDominio replica;
	private UsuarioDominio moderador;
	private EstadoRevisionDominio estadoRevision;
	private String justificacion;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaCierre;

	private RevisionReplicaDominio(Builder builder) {
		this.id = builder.id;
		this.replica = builder.replica;
		this.moderador = builder.moderador;
		this.estadoRevision = builder.estadoRevision;
		this.justificacion = builder.justificacion;
		this.fechaInicio = builder.fechaInicio;
		this.fechaCierre = builder.fechaCierre;
	}

	public static RevisionReplicaDominio obtenerValorDefecto(RevisionReplicaDominio revisionReplica) {
		return UtilObjeto.esNulo(revisionReplica) ? new Builder().build() : revisionReplica;
	}

	public UUID getId() {
		return id;
	}

	public ReplicaDominio getReplica() {
		return replica;
	}

	public UsuarioDominio getModerador() {
		return moderador;
	}

	public EstadoRevisionDominio getEstadoRevision() {
		return estadoRevision;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public LocalDateTime getFechaCierre() {
		return fechaCierre;
	}

	public static class Builder {

		private UUID id;
		private ReplicaDominio replica;
		private UsuarioDominio moderador;
		private EstadoRevisionDominio estadoRevision;
		private String justificacion;
		private LocalDateTime fechaInicio;
		private LocalDateTime fechaCierre;

		public Builder() {
			id = UtilUUID.obtenerUUIDDefecto();
			replica = ReplicaDominio.obtenerValorDefecto(null);
			moderador = UsuarioDominio.obtenerValorDefecto(null);
			estadoRevision = EstadoRevisionDominio.obtenerValorDefecto(null);
			justificacion = UtilTexto.VACIA;
			fechaInicio = UtilFechaHora.FECHA_HORA_DEFECTO;
			fechaCierre = UtilFechaHora.FECHA_HORA_DEFECTO;
		}

		public Builder id(UUID id) {
			this.id = UtilUUID.obtenerValorDefecto(id);
			return this;
		}

		public Builder replica(ReplicaDominio replica) {
			this.replica = ReplicaDominio.obtenerValorDefecto(replica);
			return this;
		}

		public Builder moderador(UsuarioDominio moderador) {
			this.moderador = UsuarioDominio.obtenerValorDefecto(moderador);
			return this;
		}

		public Builder estadoRevision(EstadoRevisionDominio estadoRevision) {
			this.estadoRevision = EstadoRevisionDominio.obtenerValorDefecto(estadoRevision);
			return this;
		}

		public Builder justificacion(String justificacion) {
			this.justificacion = UtilTexto.quitarEspaciosEnBlanco(justificacion);
			return this;
		}

		public Builder fechaInicio(LocalDateTime fechaInicio) {
			this.fechaInicio = UtilFechaHora.obtenerValorDefecto(fechaInicio);
			return this;
		}

		public Builder fechaCierre(LocalDateTime fechaCierre) {
			this.fechaCierre = UtilFechaHora.obtenerValorDefecto(fechaCierre);
			return this;
		}

		public RevisionReplicaDominio build() {
			return new RevisionReplicaDominio(this);
		}
	}
}
