package co.edu.uco.kudos.entidad;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class AuditoriaEntidad {

	private UUID id;
	private UsuarioEntidad usuario;
	private String accion;
	private String objetoAfectado;
	private UUID registroAfectado;
	private String detalle;
	private LocalDateTime fechaHora;

	public AuditoriaEntidad() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setUsuario(new UsuarioEntidad());
		setAccion(UtilTexto.VACIA);
		setObjetoAfectado(UtilTexto.VACIA);
		setRegistroAfectado(UtilUUID.obtenerUUIDDefecto());
		setDetalle(UtilTexto.VACIA);
		setFechaHora(UtilFechaHora.FECHA_HORA_DEFECTO);
	}

	public AuditoriaEntidad(UUID id, UsuarioEntidad usuario, String accion, String objetoAfectado,
			UUID registroAfectado, String detalle, LocalDateTime fechaHora) {
		setId(id);
		setUsuario(usuario);
		setAccion(accion);
		setObjetoAfectado(objetoAfectado);
		setRegistroAfectado(registroAfectado);
		setDetalle(detalle);
		setFechaHora(fechaHora);
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
