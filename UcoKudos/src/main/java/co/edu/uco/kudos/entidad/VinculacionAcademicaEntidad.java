package co.edu.uco.kudos.entidad;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class VinculacionAcademicaEntidad {

	private UUID id;
	private UsuarioEntidad usuario;
	private ProgramaAcademicoEntidad programaAcademico;
	private LocalDate fechaVinculacion;
	private LocalDate fechaDesvinculacion;

	public VinculacionAcademicaEntidad() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setUsuario(new UsuarioEntidad());
		setProgramaAcademico(new ProgramaAcademicoEntidad());
		setFechaVinculacion(UtilFecha.FECHA_DEFECTO);
		setFechaDesvinculacion(UtilFecha.FECHA_DEFECTO);
	}

	public VinculacionAcademicaEntidad(UUID id, UsuarioEntidad usuario, ProgramaAcademicoEntidad programaAcademico,
			LocalDate fechaVinculacion, LocalDate fechaDesvinculacion) {
		setId(id);
		setUsuario(usuario);
		setProgramaAcademico(programaAcademico);
		setFechaVinculacion(fechaVinculacion);
		setFechaDesvinculacion(fechaDesvinculacion);
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

	public ProgramaAcademicoEntidad getProgramaAcademico() {
		return programaAcademico;
	}

	public void setProgramaAcademico(ProgramaAcademicoEntidad programaAcademico) {
		this.programaAcademico = UtilObjeto.esNulo(programaAcademico) ? new ProgramaAcademicoEntidad()
				: programaAcademico;
	}

	public LocalDate getFechaVinculacion() {
		return fechaVinculacion;
	}

	public void setFechaVinculacion(LocalDate fechaVinculacion) {
		this.fechaVinculacion = UtilFecha.obtenerValorDefecto(fechaVinculacion);
	}

	public LocalDate getFechaDesvinculacion() {
		return fechaDesvinculacion;
	}

	public void setFechaDesvinculacion(LocalDate fechaDesvinculacion) {
		this.fechaDesvinculacion = UtilFecha.obtenerValorDefecto(fechaDesvinculacion);
	}
}
