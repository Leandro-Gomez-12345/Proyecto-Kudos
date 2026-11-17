package co.edu.uco.kudos.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ReplicaDTO {

	private UUID id;
	private KudoDTO kudo;
	private ReplicaDTO replicaPadre;
	private UsuarioDTO autor;
	private String contenido;
	private EstadoContenidoDTO estadoContenido;
	private LocalDateTime fechaCreacion;

	public ReplicaDTO() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setKudo(new KudoDTO());
		setAutor(new UsuarioDTO());
		setContenido(UtilTexto.VACIA);
		setEstadoContenido(new EstadoContenidoDTO());
		setFechaCreacion(UtilFechaHora.FECHA_HORA_DEFECTO);
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

	public ReplicaDTO getReplicaPadre() {
		return replicaPadre;
	}

	public void setReplicaPadre(ReplicaDTO replicaPadre) {
		this.replicaPadre = replicaPadre;
	}

	public UsuarioDTO getAutor() {
		return autor;
	}

	public void setAutor(UsuarioDTO autor) {
		this.autor = UtilObjeto.esNulo(autor) ? new UsuarioDTO() : autor;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = UtilTexto.quitarEspaciosEnBlanco(contenido);
	}

	public EstadoContenidoDTO getEstadoContenido() {
		return estadoContenido;
	}

	public void setEstadoContenido(EstadoContenidoDTO estadoContenido) {
		this.estadoContenido = UtilObjeto.esNulo(estadoContenido) ? new EstadoContenidoDTO() : estadoContenido;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = UtilFechaHora.obtenerValorDefecto(fechaCreacion);
	}
}
