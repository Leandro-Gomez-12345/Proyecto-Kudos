package co.edu.uco.kudos.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class KudoDTO {

	private UUID id;
	private UsuarioDTO autor;
	private UsuarioDTO destinatario;
	private CategoriaDTO categoria;
	private String nombreCategoria;
	private String contenido;
	private EstadoContenidoDTO estadoContenido;
	private LocalDateTime fechaCreacion;

	public KudoDTO() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setAutor(new UsuarioDTO());
		setDestinatario(new UsuarioDTO());
		setCategoria(new CategoriaDTO());
		setNombreCategoria(UtilTexto.VACIA);
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

	public UsuarioDTO getAutor() {
		return autor;
	}

	public void setAutor(UsuarioDTO autor) {
		this.autor = UtilObjeto.esNulo(autor) ? new UsuarioDTO() : autor;
	}

	public UsuarioDTO getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(UsuarioDTO destinatario) {
		this.destinatario = UtilObjeto.esNulo(destinatario) ? new UsuarioDTO() : destinatario;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = UtilObjeto.esNulo(categoria) ? new CategoriaDTO() : categoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = UtilTexto.obtenerValorDefecto(nombreCategoria);
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
