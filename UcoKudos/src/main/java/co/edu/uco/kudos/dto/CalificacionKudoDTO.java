package co.edu.uco.kudos.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilNumero;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class CalificacionKudoDTO {

	private UUID id;
	private KudoDTO kudo;
	private UsuarioDTO usuario;
	private int estrellas;
	private LocalDateTime fechaCalificacion;

	public CalificacionKudoDTO() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setKudo(new KudoDTO());
		setUsuario(new UsuarioDTO());
		setEstrellas(UtilNumero.CERO);
		setFechaCalificacion(UtilFechaHora.FECHA_HORA_DEFECTO);
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

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = UtilObjeto.esNulo(usuario) ? new UsuarioDTO() : usuario;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(Integer estrellas) {
		this.estrellas = UtilNumero.obtenerValorDefecto(estrellas, UtilNumero.CERO);
	}

	public LocalDateTime getFechaCalificacion() {
		return fechaCalificacion;
	}

	public void setFechaCalificacion(LocalDateTime fechaCalificacion) {
		this.fechaCalificacion = UtilFechaHora.obtenerValorDefecto(fechaCalificacion);
	}
}
