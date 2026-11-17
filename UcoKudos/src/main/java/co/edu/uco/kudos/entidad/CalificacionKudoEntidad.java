package co.edu.uco.kudos.entidad;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilNumero;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class CalificacionKudoEntidad {

	private UUID id;
	private KudoEntidad kudo;
	private UsuarioEntidad usuario;
	private int estrellas;
	private LocalDateTime fechaCalificacion;

	public CalificacionKudoEntidad() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setKudo(new KudoEntidad());
		setUsuario(new UsuarioEntidad());
		setEstrellas(UtilNumero.CERO);
		setFechaCalificacion(UtilFechaHora.FECHA_HORA_DEFECTO);
	}

	public CalificacionKudoEntidad(UUID id, KudoEntidad kudo, UsuarioEntidad usuario, Integer estrellas,
			LocalDateTime fechaCalificacion) {
		setId(id);
		setKudo(kudo);
		setUsuario(usuario);
		setEstrellas(estrellas);
		setFechaCalificacion(fechaCalificacion);
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

	public UsuarioEntidad getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntidad usuario) {
		this.usuario = UtilObjeto.esNulo(usuario) ? new UsuarioEntidad() : usuario;
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
