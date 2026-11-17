package co.edu.uco.kudos.dominio;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilNumero;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public class CalificacionReplicaDominio {

	private UUID id;
	private ReplicaDominio replica;
	private UsuarioDominio usuario;
	private int estrellas;
	private LocalDateTime fechaCalificacion;

	private CalificacionReplicaDominio(Builder builder) {
		this.id = builder.id;
		this.replica = builder.replica;
		this.usuario = builder.usuario;
		this.estrellas = builder.estrellas;
		this.fechaCalificacion = builder.fechaCalificacion;
	}

	public static CalificacionReplicaDominio obtenerValorDefecto(CalificacionReplicaDominio calificacionReplica) {
		return UtilObjeto.esNulo(calificacionReplica) ? new Builder().build() : calificacionReplica;
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

	public int getEstrellas() {
		return estrellas;
	}

	public LocalDateTime getFechaCalificacion() {
		return fechaCalificacion;
	}

	public static class Builder {

		private UUID id;
		private ReplicaDominio replica;
		private UsuarioDominio usuario;
		private int estrellas;
		private LocalDateTime fechaCalificacion;

		public Builder() {
			id = UtilUUID.obtenerUUIDDefecto();
			replica = ReplicaDominio.obtenerValorDefecto(null);
			usuario = UsuarioDominio.obtenerValorDefecto(null);
			estrellas = UtilNumero.CERO;
			fechaCalificacion = UtilFechaHora.FECHA_HORA_DEFECTO;
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

		public Builder estrellas(Integer estrellas) {
			this.estrellas = UtilNumero.obtenerValorDefecto(estrellas, UtilNumero.CERO);
			return this;
		}

		public Builder fechaCalificacion(LocalDateTime fechaCalificacion) {
			this.fechaCalificacion = UtilFechaHora.obtenerValorDefecto(fechaCalificacion);
			return this;
		}

		public CalificacionReplicaDominio build() {
			return new CalificacionReplicaDominio(this);
		}
	}
}
