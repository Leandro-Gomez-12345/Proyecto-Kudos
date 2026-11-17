package co.edu.uco.kudos.dominio;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public class ReporteReplicaDominio {

	private UUID id;
	private ReplicaDominio replica;
	private UsuarioDominio usuario;
	private MotivoDominio motivo;
	private String descripcion;
	private RevisionReplicaDominio revisionReplica;
	private LocalDateTime fechaReporte;

	private ReporteReplicaDominio(Builder builder) {
		this.id = builder.id;
		this.replica = builder.replica;
		this.usuario = builder.usuario;
		this.motivo = builder.motivo;
		this.descripcion = builder.descripcion;
		this.revisionReplica = builder.revisionReplica;
		this.fechaReporte = builder.fechaReporte;
	}

	public static ReporteReplicaDominio obtenerValorDefecto(ReporteReplicaDominio reporteReplica) {
		return UtilObjeto.esNulo(reporteReplica) ? new Builder().build() : reporteReplica;
	}

	public UUID getId() {
		return id;
	}

	public ReplicaDominio getReplica() {
		return replica;
	}

	public UsuarioDominio getUsuario() {
		return usuario;
	}

	public MotivoDominio getMotivo() {
		return motivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public RevisionReplicaDominio getRevisionReplica() {
		return revisionReplica;
	}

	public LocalDateTime getFechaReporte() {
		return fechaReporte;
	}

	public static class Builder {

		private UUID id;
		private ReplicaDominio replica;
		private UsuarioDominio usuario;
		private MotivoDominio motivo;
		private String descripcion;
		private RevisionReplicaDominio revisionReplica;
		private LocalDateTime fechaReporte;

		public Builder() {
			id = UtilUUID.obtenerUUIDDefecto();
			replica = ReplicaDominio.obtenerValorDefecto(null);
			usuario = UsuarioDominio.obtenerValorDefecto(null);
			motivo = MotivoDominio.obtenerValorDefecto(null);
			descripcion = UtilTexto.VACIA;
			revisionReplica = RevisionReplicaDominio.obtenerValorDefecto(null);
			fechaReporte = UtilFechaHora.FECHA_HORA_DEFECTO;
		}

		public Builder id(UUID id) {
			this.id = UtilUUID.obtenerValorDefecto(id);
			return this;
		}

		public Builder replica(ReplicaDominio replica) {
			this.replica = ReplicaDominio.obtenerValorDefecto(replica);
			return this;
		}

		public Builder usuario(UsuarioDominio usuario) {
			this.usuario = UsuarioDominio.obtenerValorDefecto(usuario);
			return this;
		}

		public Builder motivo(MotivoDominio motivo) {
			this.motivo = MotivoDominio.obtenerValorDefecto(motivo);
			return this;
		}

		public Builder descripcion(String descripcion) {
			this.descripcion = UtilTexto.quitarEspaciosEnBlanco(descripcion);
			return this;
		}

		public Builder revisionReplica(RevisionReplicaDominio revisionReplica) {
			this.revisionReplica = RevisionReplicaDominio.obtenerValorDefecto(revisionReplica);
			return this;
		}

		public Builder fechaReporte(LocalDateTime fechaReporte) {
			this.fechaReporte = UtilFechaHora.obtenerValorDefecto(fechaReporte);
			return this;
		}

		public ReporteReplicaDominio build() {
			return new ReporteReplicaDominio(this);
		}
	}
}
