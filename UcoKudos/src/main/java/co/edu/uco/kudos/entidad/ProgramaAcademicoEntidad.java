package co.edu.uco.kudos.entidad;

import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ProgramaAcademicoEntidad {

	private UUID id;
	private FacultadEntidad facultad;
	private String nombre;

	public ProgramaAcademicoEntidad() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setFacultad(new FacultadEntidad());
		setNombre(UtilTexto.VACIA);
	}

	public ProgramaAcademicoEntidad(UUID id, FacultadEntidad facultad, String nombre) {
		setId(id);
		setFacultad(facultad);
		setNombre(nombre);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public FacultadEntidad getFacultad() {
		return facultad;
	}

	public void setFacultad(FacultadEntidad facultad) {
		this.facultad = UtilObjeto.esNulo(facultad) ? new FacultadEntidad() : facultad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = UtilTexto.quitarEspaciosEnBlanco(nombre);
	}
}
