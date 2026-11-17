package co.edu.uco.kudos.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AuditoriaDTO {

	private UUID id;
	private UsuarioDTO usuario;
	private String accion;
	private String objetoAfectado;
	private UUID registroAfectado;
	private String detalle;
	private LocalDateTime fechaHora;

	public AuditoriaDTO() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setUsuario(new UsuarioDTO());
		setAccion(UtilTexto.VACIA);
		setObjetoAfectado(UtilTexto.VACIA);
		setRegistroAfectado(UtilUUID.obtenerUUIDDefecto());
		setDetalle(UtilTexto.VACIA);
		setFechaHora(UtilFechaHora.FECHA_HORA_DEFECTO);
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

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = UtilTexto.quitarEspaciosEnBlanco(accion);
	}

	public String getObjetoAfectado() {
		return objetoAfectado;
	}

	public void setObjetoAfectado(String objetoAfectado) {
		this.objetoAfectado = UtilTexto.quitarEspaciosEnBlanco(objetoAfectado);
	}

	public UUID getRegistroAfectado() {
		return registroAfectado;
	}

	public void setRegistroAfectado(UUID registroAfectado) {
		this.registroAfectado = UtilUUID.obtenerValorDefecto(registroAfectado);
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = UtilTexto.quitarEspaciosEnBlanco(detalle);
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = UtilFechaHora.obtenerValorDefecto(fechaHora);
	}
}
