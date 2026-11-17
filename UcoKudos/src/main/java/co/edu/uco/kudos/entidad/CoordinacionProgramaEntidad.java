package co.edu.uco.kudos.entidad;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class CoordinacionProgramaEntidad {

	private UUID id;
	private UsuarioEntidad usuario;
	private ProgramaAcademicoEntidad programaAcademico;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	public CoordinacionProgramaEntidad() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setUsuario(new UsuarioEntidad());
		setProgramaAcademico(new ProgramaAcademicoEntidad());
		setFechaInicio(UtilFecha.FECHA_DEFECTO);
		setFechaFin(UtilFecha.FECHA_DEFECTO);
	}

	public CoordinacionProgramaEntidad(UUID id, UsuarioEntidad usuario, ProgramaAcademicoEntidad programaAcademico,
			LocalDate fechaInicio, LocalDate fechaFin) {
		setId(id);
		setUsuario(usuario);
		setProgramaAcademico(programaAcademico);
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

	public ProgramaAcademicoEntidad getProgramaAcademico() {
		return programaAcademico;
	}

	public void setProgramaAcademico(ProgramaAcademicoEntidad programaAcademico) {
		this.programaAcademico = UtilObjeto.esNulo(programaAcademico) ? new ProgramaAcademicoEntidad()
				: programaAcademico;
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
