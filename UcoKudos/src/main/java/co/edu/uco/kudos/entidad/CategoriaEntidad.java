package co.edu.uco.kudos.entidad;

import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilBooleano;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class CategoriaEntidad {

	private UUID id;
	private String nombre;
	private String descripcion;
	private boolean activa;

	public CategoriaEntidad() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setNombre(UtilTexto.VACIA);
		setDescripcion(UtilTexto.VACIA);
		setActiva(UtilBooleano.SI);
	}

	public CategoriaEntidad(UUID id, String nombre, String descripcion, Boolean activa) {
		setId(id);
		setNombre(nombre);
		setDescripcion(descripcion);
		setActiva(activa);
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
