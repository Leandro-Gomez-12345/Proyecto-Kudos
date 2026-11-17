package co.edu.uco.kudos.entidad;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilNumero;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class CalificacionReplicaEntidad {

	private UUID id;
	private ReplicaEntidad replica;
	private UsuarioEntidad usuario;
	private int estrellas;
	private LocalDateTime fechaCalificacion;

	public CalificacionReplicaEntidad() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setReplica(new ReplicaEntidad());
		setUsuario(new UsuarioEntidad());
		setEstrellas(UtilNumero.CERO);
		setFechaCalificacion(UtilFechaHora.FECHA_HORA_DEFECTO);
	}

	public CalificacionReplicaEntidad(UUID id, ReplicaEntidad replica, UsuarioEntidad usuario, Integer estrellas,
			LocalDateTime fechaCalificacion) {
		setId(id);
		setReplica(replica);
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

	public ReplicaEntidad getReplica() {
		return replica;
	}

	public void setReplica(ReplicaEntidad replica) {
		this.replica = UtilObjeto.esNulo(replica) ? new ReplicaEntidad() : replica;
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
