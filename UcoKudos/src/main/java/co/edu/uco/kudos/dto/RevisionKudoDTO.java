package co.edu.uco.kudos.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class RevisionKudoDTO {

	private UUID id;
	private KudoDTO kudo;
	private UsuarioDTO moderador;
	private EstadoRevisionDTO estadoRevision;
	private String justificacion;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaCierre;

	public RevisionKudoDTO() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setKudo(new KudoDTO());
		setModerador(new UsuarioDTO());
		setEstadoRevision(new EstadoRevisionDTO());
		setJustificacion(UtilTexto.VACIA);
		setFechaInicio(UtilFechaHora.FECHA_HORA_DEFECTO);
		setFechaCierre(UtilFechaHora.FECHA_HORA_DEFECTO);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public KudoDTO getKudo() {
		return kudo;
	}

	public void setKudo(KudoDTO kudo) {
		this.kudo = UtilObjeto.esNulo(kudo) ? new KudoDTO() : kudo;
	}

	public UsuarioDTO getModerador() {
		return moderador;
	}

	public void setModerador(UsuarioDTO moderador) {
		this.moderador = UtilObjeto.esNulo(moderador) ? new UsuarioDTO() : moderador;
	}

	public EstadoRevisionDTO getEstadoRevision() {
		return estadoRevision;
	}

	public void setEstadoRevision(EstadoRevisionDTO estadoRevision) {
		this.estadoRevision = UtilObjeto.esNulo(estadoRevision) ? new EstadoRevisionDTO() : estadoRevision;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = UtilTexto.quitarEspaciosEnBlanco(justificacion);
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = UtilFechaHora.obtenerValorDefecto(fechaInicio);
	}

	public LocalDateTime getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(LocalDateTime fechaCierre) {
		this.fechaCierre = UtilFechaHora.obtenerValorDefecto(fechaCierre);
	}
}
