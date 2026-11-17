package co.edu.uco.kudos.dominio;

import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public class ProgramaAcademicoDominio {

	private UUID id;
	private FacultadDominio facultad;
	private String nombre;

	private ProgramaAcademicoDominio(Builder builder) {
		this.id = builder.id;
		this.facultad = builder.facultad;
		this.nombre = builder.nombre;
	}

	public static ProgramaAcademicoDominio obtenerValorDefecto(ProgramaAcademicoDominio programaAcademico) {
		return UtilObjeto.esNulo(programaAcademico) ? new Builder().build() : programaAcademico;
	}

	public UUID getId() {
		return id;
	}

	public FacultadDominio getFacultad() {
		return facultad;
	}

	public String getNombre() {
		return nombre;
	}

	public static class Builder {

		private UUID id;
		private FacultadDominio facultad;
		private String nombre;

		public Builder() {
			id = UtilUUID.obtenerUUIDDefecto();
			facultad = FacultadDominio.obtenerValorDefecto(null);
			nombre = UtilTexto.VACIA;
		}

		public Builder id(UUID id) {
			this.id = UtilUUID.obtenerValorDefecto(id);
			return this;
		}

		public Builder facultad(FacultadDominio facultad) {
			this.facultad = FacultadDominio.obtenerValorDefecto(facultad);
			return this;
		}

		public Builder nombre(String nombre) {
			this.nombre = UtilTexto.quitarEspaciosEnBlanco(nombre);
			return this;
		}

		public ProgramaAcademicoDominio build() {
			return new ProgramaAcademicoDominio(this);
		}
	}
}
