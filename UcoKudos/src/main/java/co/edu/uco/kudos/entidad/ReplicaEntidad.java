package co.edu.uco.kudos.entidad;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ReplicaEntidad {

	private UUID id;
	private KudoEntidad kudo;
	private ReplicaEntidad replicaPadre;
	private UsuarioEntidad autor;
	private String contenido;
	private EstadoContenidoEntidad estadoContenido;
	private LocalDateTime fechaCreacion;

	public ReplicaEntidad() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setKudo(new KudoEntidad());
		setAutor(new UsuarioEntidad());
		setContenido(UtilTexto.VACIA);
		setEstadoContenido(new EstadoContenidoEntidad());
		setFechaCreacion(UtilFechaHora.FECHA_HORA_DEFECTO);
	}

	public ReplicaEntidad(UUID id, KudoEntidad kudo, ReplicaEntidad replicaPadre, UsuarioEntidad autor,
			String contenido, EstadoContenidoEntidad estadoContenido, LocalDateTime fechaCreacion) {
		setId(id);
		setKudo(kudo);
		setReplicaPadre(replicaPadre);
		setAutor(autor);
		setContenido(contenido);
		setEstadoContenido(estadoContenido);
		setFechaCreacion(fechaCreacion);
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

	public ReplicaEntidad getReplicaPadre() {
		return replicaPadre;
	}

	public void setReplicaPadre(ReplicaEntidad replicaPadre) {
		this.replicaPadre = replicaPadre;
	}

	public UsuarioEntidad getAutor() {
		return autor;
	}

	public void setAutor(UsuarioEntidad autor) {
		this.autor = UtilObjeto.esNulo(autor) ? new UsuarioEntidad() : autor;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = UtilTexto.quitarEspaciosEnBlanco(contenido);
	}

	public EstadoContenidoEntidad getEstadoContenido() {
		return estadoContenido;
	}

	public void setEstadoContenido(EstadoContenidoEntidad estadoContenido) {
		this.estadoContenido = UtilObjeto.esNulo(estadoContenido) ? new EstadoContenidoEntidad() : estadoContenido;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = UtilFechaHora.obtenerValorDefecto(fechaCreacion);
	}
}
