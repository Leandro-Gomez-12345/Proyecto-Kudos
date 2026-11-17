package co.edu.uco.kudos.dominio;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public class AuditoriaDominio {

	private UUID id;
	private UsuarioDominio usuario;
	private String accion;
	private String objetoAfectado;
	private UUID registroAfectado;
	private String detalle;
	private LocalDateTime fechaHora;

	private AuditoriaDominio(Builder builder) {
		this.id = builder.id;
		this.usuario = builder.usuario;
		this.accion = builder.accion;
		this.objetoAfectado = builder.objetoAfectado;
		this.registroAfectado = builder.registroAfectado;
		this.detalle = builder.detalle;
		this.fechaHora = builder.fechaHora;
	}

	public static AuditoriaDominio obtenerValorDefecto(AuditoriaDominio auditoria) {
		return UtilObjeto.esNulo(auditoria) ? new Builder().build() : auditoria;
	}

	public UUID getId() {
		return id;
	}

	public UsuarioDominio getUsuario() {
		return usuario;
	}

	public String getAccion() {
		return accion;
	}

	public String getObjetoAfectado() {
		return objetoAfectado;
	}

	public UUID getRegistroAfectado() {
		return registroAfectado;
	}

	public String getDetalle() {
		return detalle;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public static class Builder {

		private UUID id;
		private UsuarioDominio usuario;
		private String accion;
		private String objetoAfectado;
		private UUID registroAfectado;
		private String detalle;
		private LocalDateTime fechaHora;

		public Builder() {
			id = UtilUUID.obtenerUUIDDefecto();
			usuario = UsuarioDominio.obtenerValorDefecto(null);
			accion = UtilTexto.VACIA;
			objetoAfectado = UtilTexto.VACIA;
			registroAfectado = UtilUUID.obtenerUUIDDefecto();
			detalle = UtilTexto.VACIA;
			fechaHora = UtilFechaHora.FECHA_HORA_DEFECTO;
		}

		public Builder id(UUID id) {
			this.id = UtilUUID.obtenerValorDefecto(id);
			return this;
		}

		public Builder usuario(UsuarioDominio usuario) {
			this.usuario = UsuarioDominio.obtenerValorDefecto(usuario);
			return this;
		}

		public Builder accion(String accion) {
			this.accion = UtilTexto.quitarEspaciosEnBlanco(accion);
			return this;
		}

		public Builder objetoAfectado(String objetoAfectado) {
			this.objetoAfectado = UtilTexto.quitarEspaciosEnBlanco(objetoAfectado);
			return this;
		}

		public Builder registroAfectado(UUID registroAfectado) {
			this.registroAfectado = UtilUUID.obtenerValorDefecto(registroAfectado);
			return this;
		}

		public Builder detalle(String detalle) {
			this.detalle = UtilTexto.quitarEspaciosEnBlanco(detalle);
			return this;
		}

		public Builder fechaHora(LocalDateTime fechaHora) {
			this.fechaHora = UtilFechaHora.obtenerValorDefecto(fechaHora);
			return this;
		}

		public AuditoriaDominio build() {
			return new AuditoriaDominio(this);
		}
	}
}
