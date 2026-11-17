package co.edu.uco.kudos.entidad;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class CoordinacionFacultadEntidad {

	private UUID id;
	private UsuarioEntidad usuario;
	private FacultadEntidad facultad;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	public CoordinacionFacultadEntidad() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setUsuario(new UsuarioEntidad());
		setFacultad(new FacultadEntidad());
		setFechaInicio(UtilFecha.FECHA_DEFECTO);
		setFechaFin(UtilFecha.FECHA_DEFECTO);
	}

	public CoordinacionFacultadEntidad(UUID id, UsuarioEntidad usuario, FacultadEntidad facultad, LocalDate fechaInicio,
			LocalDate fechaFin) {
		setId(id);
		setUsuario(usuario);
		setFacultad(facultad);
		setFechaInicio(fechaInicio);
		setFechaFin(fechaFin);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public UsuarioEntidad getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntidad usuario) {
		this.usuario = UtilObjeto.esNulo(usuario) ? new UsuarioEntidad() : usuario;
	}

	public FacultadEntidad getFacultad() {
		return facultad;
	}

	public void setFacultad(FacultadEntidad facultad) {
		this.facultad = UtilObjeto.esNulo(facultad) ? new FacultadEntidad() : facultad;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = UtilFecha.obtenerValorDefecto(fechaInicio);
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = UtilFecha.obtenerValorDefecto(fechaFin);
	}
}
