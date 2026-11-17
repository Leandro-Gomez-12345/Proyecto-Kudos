package co.edu.uco.kudos.dominio;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public class CoordinacionProgramaDominio {

	private UUID id;
	private UsuarioDominio usuario;
	private ProgramaAcademicoDominio programaAcademico;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	private CoordinacionProgramaDominio(Builder builder) {
		this.id = builder.id;
		this.usuario = builder.usuario;
		this.programaAcademico = builder.programaAcademico;
		this.fechaInicio = builder.fechaInicio;
		this.fechaFin = builder.fechaFin;
	}

	public static CoordinacionProgramaDominio obtenerValorDefecto(CoordinacionProgramaDominio coordinacionPrograma) {
		return UtilObjeto.esNulo(coordinacionPrograma) ? new Builder().build() : coordinacionPrograma;
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

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public static class Builder {

		private UUID id;
		private UsuarioDominio usuario;
		private ProgramaAcademicoDominio programaAcademico;
		private LocalDate fechaInicio;
		private LocalDate fechaFin;

		public Builder() {
			id = UtilUUID.obtenerUUIDDefecto();
			usuario = UsuarioDominio.obtenerValorDefecto(null);
			programaAcademico = ProgramaAcademicoDominio.obtenerValorDefecto(null);
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

		public Builder programaAcademico(ProgramaAcademicoDominio programaAcademico) {
			this.programaAcademico = ProgramaAcademicoDominio.obtenerValorDefecto(programaAcademico);
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

		public CoordinacionProgramaDominio build() {
			return new CoordinacionProgramaDominio(this);
		}
	}
}
