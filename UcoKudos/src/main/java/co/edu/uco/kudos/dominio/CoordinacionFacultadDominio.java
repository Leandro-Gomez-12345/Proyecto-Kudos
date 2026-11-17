package co.edu.uco.kudos.dominio;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public class CoordinacionFacultadDominio {

	private UUID id;
	private UsuarioDominio usuario;
	private FacultadDominio facultad;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	private CoordinacionFacultadDominio(Builder builder) {
		this.id = builder.id;
		this.usuario = builder.usuario;
		this.facultad = builder.facultad;
		this.fechaInicio = builder.fechaInicio;
		this.fechaFin = builder.fechaFin;
	}

	public static CoordinacionFacultadDominio obtenerValorDefecto(CoordinacionFacultadDominio coordinacionFacultad) {
		return UtilObjeto.esNulo(coordinacionFacultad) ? new Builder().build() : coordinacionFacultad;
	}

	public UUID getId() {
		return id;
	}

	public UsuarioDominio getUsuario() {
		return usuario;
	}

	public FacultadDominio getFacultad() {
		return facultad;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public static class Builder {

		private UUID id;
		private UsuarioDominio usuario;
		private FacultadDominio facultad;
		private LocalDate fechaInicio;
		private LocalDate fechaFin;

		public Builder() {
			id = UtilUUID.obtenerUUIDDefecto();
			usuario = UsuarioDominio.obtenerValorDefecto(null);
			facultad = FacultadDominio.obtenerValorDefecto(null);
			fechaInicio = UtilFecha.FECHA_DEFECTO;
			fechaFin = UtilFecha.FECHA_DEFECTO;
		}

		public Builder id(UUID id) {
			this.id = UtilUUID.obtenerValorDefecto(id);
			return this;
		}

		public Builder usuario(UsuarioDominio usuario) {
			this.usuario = UsuarioDominio.obtenerValorDefecto(usuario);
			return this;
		}

		public Builder facultad(FacultadDominio facultad) {
			this.facultad = FacultadDominio.obtenerValorDefecto(facultad);
			return this;
		}

		public Builder fechaInicio(LocalDate fechaInicio) {
			this.fechaInicio = UtilFecha.obtenerValorDefecto(fechaInicio);
			return this;
		}

		public Builder fechaFin(LocalDate fechaFin) {
			this.fechaFin = UtilFecha.obtenerValorDefecto(fechaFin);
			return this;
		}

		public CoordinacionFacultadDominio build() {
			return new CoordinacionFacultadDominio(this);
		}
	}
}
