package co.edu.uco.kudos.entidad;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class RevisionKudoEntidad {

	private UUID id;
	private KudoEntidad kudo;
	private UsuarioEntidad moderador;
	private EstadoRevisionEntidad estadoRevision;
	private String justificacion;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaCierre;

	public RevisionKudoEntidad() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setKudo(new KudoEntidad());
		setModerador(new UsuarioEntidad());
		setEstadoRevision(new EstadoRevisionEntidad());
		setJustificacion(UtilTexto.VACIA);
		setFechaInicio(UtilFechaHora.FECHA_HORA_DEFECTO);
		setFechaCierre(UtilFechaHora.FECHA_HORA_DEFECTO);
	}

	public RevisionKudoEntidad(UUID id, KudoEntidad kudo, UsuarioEntidad moderador,
			EstadoRevisionEntidad estadoRevision, String justificacion, LocalDateTime fechaInicio,
			LocalDateTime fechaCierre) {
		setId(id);
		setKudo(kudo);
		setModerador(moderador);
		setEstadoRevision(estadoRevision);
		setJustificacion(justificacion);
		setFechaInicio(fechaInicio);
		setFechaCierre(fechaCierre);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public KudoEntidad getKudo() {
		return kudo;
	}

	public void setKudo(KudoEntidad kudo) {
		this.kudo = UtilObjeto.esNulo(kudo) ? new KudoEntidad() : kudo;
	}

	public UsuarioEntidad getModerador() {
		return moderador;
	}

	public void setModerador(UsuarioEntidad moderador) {
		this.moderador = UtilObjeto.esNulo(moderador) ? new UsuarioEntidad() : moderador;
	}

	public EstadoRevisionEntidad getEstadoRevision() {
		return estadoRevision;
	}

	public void setEstadoRevision(EstadoRevisionEntidad estadoRevision) {
		this.estadoRevision = UtilObjeto.esNulo(estadoRevision) ? new EstadoRevisionEntidad() : estadoRevision;
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
