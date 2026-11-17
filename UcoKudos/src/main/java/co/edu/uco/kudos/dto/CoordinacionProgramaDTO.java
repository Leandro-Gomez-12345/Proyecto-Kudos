package co.edu.uco.kudos.dto;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class CoordinacionProgramaDTO {

	private UUID id;
	private UsuarioDTO usuario;
	private ProgramaAcademicoDTO programaAcademico;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	public CoordinacionProgramaDTO() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setUsuario(new UsuarioDTO());
		setProgramaAcademico(new ProgramaAcademicoDTO());
		setFechaInicio(UtilFecha.FECHA_DEFECTO);
		setFechaFin(UtilFecha.FECHA_DEFECTO);
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
