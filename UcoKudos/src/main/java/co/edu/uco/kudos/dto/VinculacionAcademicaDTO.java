package co.edu.uco.kudos.dto;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class VinculacionAcademicaDTO {

	private UUID id;
	private UsuarioDTO usuario;
	private ProgramaAcademicoDTO programaAcademico;
	private LocalDate fechaVinculacion;
	private LocalDate fechaDesvinculacion;

	public VinculacionAcademicaDTO() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setUsuario(new UsuarioDTO());
		setProgramaAcademico(new ProgramaAcademicoDTO());
		setFechaVinculacion(UtilFecha.FECHA_DEFECTO);
		setFechaDesvinculacion(UtilFecha.FECHA_DEFECTO);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = UtilObjeto.esNulo(usuario) ? new UsuarioDTO() : usuario;
	}

	public ProgramaAcademicoDTO getProgramaAcademico() {
		return programaAcademico;
	}

	public void setProgramaAcademico(ProgramaAcademicoDTO programaAcademico) {
		this.programaAcademico = UtilObjeto.esNulo(programaAcademico) ? new ProgramaAcademicoDTO() : programaAcademico;
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
