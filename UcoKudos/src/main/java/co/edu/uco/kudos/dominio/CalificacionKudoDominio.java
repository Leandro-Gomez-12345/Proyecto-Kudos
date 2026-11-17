package co.edu.uco.kudos.dominio;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilNumero;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public class CalificacionKudoDominio {

	private UUID id;
	private KudoDominio kudo;
	private UsuarioDominio usuario;
	private int estrellas;
	private LocalDateTime fechaCalificacion;

	private CalificacionKudoDominio(Builder builder) {
		this.id = builder.id;
		this.kudo = builder.kudo;
		this.usuario = builder.usuario;
		this.estrellas = builder.estrellas;
		this.fechaCalificacion = builder.fechaCalificacion;
	}

	public static CalificacionKudoDominio obtenerValorDefecto(CalificacionKudoDominio calificacionKudo) {
		return UtilObjeto.esNulo(calificacionKudo) ? new Builder().build() : calificacionKudo;
	}

	public UUID getId() {
		return id;
	}

	public KudoDominio getKudo() {
		return kudo;
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
		private KudoDominio kudo;
		private UsuarioDominio usuario;
		private int estrellas;
		private LocalDateTime fechaCalificacion;

		public Builder() {
			id = UtilUUID.obtenerUUIDDefecto();
			kudo = KudoDominio.obtenerValorDefecto(null);
			usuario = UsuarioDominio.obtenerValorDefecto(null);
			estrellas = UtilNumero.CERO;
			fechaCalificacion = UtilFechaHora.FECHA_HORA_DEFECTO;
		}

		public Builder id(UUID id) {
			this.id = UtilUUID.obtenerValorDefecto(id);
			return this;
		}

		public Builder kudo(KudoDominio kudo) {
			this.kudo = KudoDominio.obtenerValorDefecto(kudo);
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

		public CalificacionKudoDominio build() {
			return new CalificacionKudoDominio(this);
		}
	}
}
