package co.edu.uco.kudos.dto;

import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilBooleano;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class CategoriaDTO {

	private UUID id;
	private String nombre;
	private String descripcion;
	private boolean activa;

	public CategoriaDTO() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setNombre(UtilTexto.VACIA);
		setDescripcion(UtilTexto.VACIA);
		setActiva(UtilBooleano.SI);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = UtilTexto.quitarEspaciosEnBlanco(nombre);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = UtilTexto.quitarEspaciosEnBlanco(descripcion);
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = UtilBooleano.obtenerValorDefecto(activa, UtilBooleano.SI);
	}
}
