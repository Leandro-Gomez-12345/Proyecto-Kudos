package co.edu.uco.kudos.dominio;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public class ReplicaDominio {

	private UUID id;
	private KudoDominio kudo;
	private ReplicaDominio replicaPadre;
	private UsuarioDominio autor;
	private String contenido;
	private EstadoContenidoDominio estadoContenido;
	private LocalDateTime fechaCreacion;

	private ReplicaDominio(Builder builder) {
		this.id = builder.id;
		this.kudo = builder.kudo;
		this.replicaPadre = builder.replicaPadre;
		this.autor = builder.autor;
		this.contenido = builder.contenido;
		this.estadoContenido = builder.estadoContenido;
		this.fechaCreacion = builder.fechaCreacion;
	}

	public static ReplicaDominio obtenerValorDefecto(ReplicaDominio replica) {
		return UtilObjeto.esNulo(replica) ? new Builder().build() : replica;
	}

	public UUID getId() {
		return id;
	}

	public KudoDominio getKudo() {
		return kudo;
	}

	public ReplicaDominio getReplicaPadre() {
		return replicaPadre;
	}

	public UsuarioDominio getAutor() {
		return autor;
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
		private KudoDominio kudo;
		private ReplicaDominio replicaPadre;
		private UsuarioDominio autor;
		private String contenido;
		private EstadoContenidoDominio estadoContenido;
		private LocalDateTime fechaCreacion;

		public Builder() {
			id = UtilUUID.obtenerUUIDDefecto();
			kudo = KudoDominio.obtenerValorDefecto(null);
			autor = UsuarioDominio.obtenerValorDefecto(null);
			contenido = UtilTexto.VACIA;
			estadoContenido = EstadoContenidoDominio.obtenerValorDefecto(null);
			fechaCreacion = UtilFechaHora.FECHA_HORA_DEFECTO;
		}

		public Builder id(UUID id) {
			this.id = UtilUUID.obtenerValorDefecto(id);
			return this;
		}

		public Builder kudo(KudoDominio kudo) {
			this.kudo = KudoDominio.obtenerValorDefecto(kudo);
			return this;
		}

		public Builder replicaPadre(ReplicaDominio replicaPadre) {
			this.replicaPadre = replicaPadre;
			return this;
		}

		public Builder autor(UsuarioDominio autor) {
			this.autor = UsuarioDominio.obtenerValorDefecto(autor);
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

		public ReplicaDominio build() {
			return new ReplicaDominio(this);
		}
	}
}
