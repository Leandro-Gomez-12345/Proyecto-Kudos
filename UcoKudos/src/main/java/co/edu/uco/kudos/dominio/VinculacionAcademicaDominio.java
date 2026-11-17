package co.edu.uco.kudos.dominio;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public class VinculacionAcademicaDominio {

	private UUID id;
	private UsuarioDominio usuario;
	private ProgramaAcademicoDominio programaAcademico;
	private LocalDate fechaVinculacion;
	private LocalDate fechaDesvinculacion;

	private VinculacionAcademicaDominio(Builder builder) {
		this.id = builder.id;
		this.usuario = builder.usuario;
		this.programaAcademico = builder.programaAcademico;
		this.fechaVinculacion = builder.fechaVinculacion;
		this.fechaDesvinculacion = builder.fechaDesvinculacion;
	}

	public static VinculacionAcademicaDominio obtenerValorDefecto(VinculacionAcademicaDominio vinculacionAcademica) {
		return UtilObjeto.esNulo(vinculacionAcademica) ? new Builder().build() : vinculacionAcademica;
	}

	public UUID getId() {
		return id;
	}

	public UsuarioDominio getUsuario() {
		return usuario;
	}

	public ProgramaAcademicoDominio getProgramaAcademico() {
		return programaAcademico;
	}

	public LocalDate getFechaVinculacion() {
		return fechaVinculacion;
	}

	public LocalDate getFechaDesvinculacion() {
		return fechaDesvinculacion;
	}

	public static class Builder {

		private UUID id;
		private UsuarioDominio usuario;
		private ProgramaAcademicoDominio programaAcademico;
		private LocalDate fechaVinculacion;
		private LocalDate fechaDesvinculacion;

		public Builder() {
			id = UtilUUID.obtenerUUIDDefecto();
			usuario = UsuarioDominio.obtenerValorDefecto(null);
			programaAcademico = ProgramaAcademicoDominio.obtenerValorDefecto(null);
			fechaVinculacion = UtilFecha.FECHA_DEFECTO;
			fechaDesvinculacion = UtilFecha.FECHA_DEFECTO;
		}

		public Builder id(UUID id) {
			this.id = UtilUUID.obtenerValorDefecto(id);
			return this;
		}

		public Builder usuario(UsuarioDominio usuario) {
			this.usuario = UsuarioDominio.obtenerValorDefecto(usuario);
			return this;
		}

		public Builder programaAcademico(ProgramaAcademicoDominio programaAcademico) {
			this.programaAcademico = ProgramaAcademicoDominio.obtenerValorDefecto(programaAcademico);
			return this;
		}

		public Builder fechaVinculacion(LocalDate fechaVinculacion) {
			this.fechaVinculacion = UtilFecha.obtenerValorDefecto(fechaVinculacion);
			return this;
		}

		public Builder fechaDesvinculacion(LocalDate fechaDesvinculacion) {
			this.fechaDesvinculacion = UtilFecha.obtenerValorDefecto(fechaDesvinculacion);
			return this;
		}

		public VinculacionAcademicaDominio build() {
			return new VinculacionAcademicaDominio(this);
		}
	}
}
