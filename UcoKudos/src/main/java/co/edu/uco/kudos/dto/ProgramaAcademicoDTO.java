package co.edu.uco.kudos.dto;

import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ProgramaAcademicoDTO {

	private UUID id;
	private FacultadDTO facultad;
	private String nombre;

	public ProgramaAcademicoDTO() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setFacultad(new FacultadDTO());
		setNombre(UtilTexto.VACIA);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public FacultadDTO getFacultad() {
		return facultad;
	}

	public void setFacultad(FacultadDTO facultad) {
		this.facultad = UtilObjeto.esNulo(facultad) ? new FacultadDTO() : facultad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = UtilTexto.quitarEspaciosEnBlanco(nombre);
	}
}
