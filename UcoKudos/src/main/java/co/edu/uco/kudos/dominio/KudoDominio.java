package co.edu.uco.kudos.dominio;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public class KudoDominio {

	private UUID id;
	private UsuarioDominio autor;
	private UsuarioDominio destinatario;
	private CategoriaDominio categoria;
	private String nombreCategoria;
	private String contenido;
	private EstadoContenidoDominio estadoContenido;
	private LocalDateTime fechaCreacion;

	private KudoDominio(Builder builder) {
		this.id = builder.id;
		this.autor = builder.autor;
		this.destinatario = builder.destinatario;
		this.categoria = builder.categoria;
		this.nombreCategoria = builder.nombreCategoria;
		this.contenido = builder.contenido;
		this.estadoContenido = builder.estadoContenido;
		this.fechaCreacion = builder.fechaCreacion;
	}

	public static KudoDominio obtenerValorDefecto(KudoDominio kudo) {
		return UtilObjeto.esNulo(kudo) ? new Builder().build() : kudo;
	}

	public UUID getId() {
		return id;
	}

	public UsuarioDominio getAutor() {
		return autor;
	}

	public UsuarioDominio getDestinatario() {
		return destinatario;
	}

	public CategoriaDominio getCategoria() {
		return categoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public String getContenido() {
		return contenido;
	}

	public EstadoContenidoDominio getEstadoContenido() {
		return estadoContenido;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public static class Builder {

		private UUID id;
		private UsuarioDominio autor;
		private UsuarioDominio destinatario;
		private CategoriaDominio categoria;
		private String nombreCategoria;
		private String contenido;
		private EstadoContenidoDominio estadoContenido;
		private LocalDateTime fechaCreacion;

		public Builder() {
			id = UtilUUID.obtenerUUIDDefecto();
			autor = UsuarioDominio.obtenerValorDefecto(null);
			destinatario = UsuarioDominio.obtenerValorDefecto(null);
			categoria = CategoriaDominio.obtenerValorDefecto(null);
			nombreCategoria = UtilTexto.VACIA;
			contenido = UtilTexto.VACIA;
			estadoContenido = EstadoContenidoDominio.obtenerValorDefecto(null);
			fechaCreacion = UtilFechaHora.FECHA_HORA_DEFECTO;
		}

		public Builder id(UUID id) {
			this.id = UtilUUID.obtenerValorDefecto(id);
			return this;
		}

		public Builder autor(UsuarioDominio autor) {
			this.autor = UsuarioDominio.obtenerValorDefecto(autor);
			return this;
		}

		public Builder destinatario(UsuarioDominio destinatario) {
			this.destinatario = UsuarioDominio.obtenerValorDefecto(destinatario);
			return this;
		}

		public Builder categoria(CategoriaDominio categoria) {
			this.categoria = CategoriaDominio.obtenerValorDefecto(categoria);
			return this;
		}

		public Builder nombreCategoria(String nombreCategoria) {
			this.nombreCategoria = UtilTexto.obtenerValorDefecto(nombreCategoria);
			return this;
		}

		public Builder contenido(String contenido) {
			this.contenido = UtilTexto.quitarEspaciosEnBlanco(contenido);
			return this;
		}

		public Builder estadoContenido(EstadoContenidoDominio estadoContenido) {
			this.estadoContenido = EstadoContenidoDominio.obtenerValorDefecto(estadoContenido);
			return this;
		}

		public Builder fechaCreacion(LocalDateTime fechaCreacion) {
			this.fechaCreacion = UtilFechaHora.obtenerValorDefecto(fechaCreacion);
			return this;
		}

		public KudoDominio build() {
			return new KudoDominio(this);
		}
	}
}
