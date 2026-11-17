package co.edu.uco.kudos.entidad;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class KudoEntidad {

	private UUID id;
	private UsuarioEntidad autor;
	private UsuarioEntidad destinatario;
	private CategoriaEntidad categoria;
	private String nombreCategoria;
	private String contenido;
	private EstadoContenidoEntidad estadoContenido;
	private LocalDateTime fechaCreacion;

	public KudoEntidad() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setAutor(new UsuarioEntidad());
		setDestinatario(new UsuarioEntidad());
		setCategoria(new CategoriaEntidad());
		setNombreCategoria(UtilTexto.VACIA);
		setContenido(UtilTexto.VACIA);
		setEstadoContenido(new EstadoContenidoEntidad());
		setFechaCreacion(UtilFechaHora.FECHA_HORA_DEFECTO);
	}

	public KudoEntidad(UUID id, UsuarioEntidad autor, UsuarioEntidad destinatario, CategoriaEntidad categoria,
			String nombreCategoria, String contenido, EstadoContenidoEntidad estadoContenido,
			LocalDateTime fechaCreacion) {
		setId(id);
		setAutor(autor);
		setDestinatario(destinatario);
		setCategoria(categoria);
		setNombreCategoria(nombreCategoria);
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

	public UsuarioEntidad getAutor() {
		return autor;
	}

	public void setAutor(UsuarioEntidad autor) {
		this.autor = UtilObjeto.esNulo(autor) ? new UsuarioEntidad() : autor;
	}

	public UsuarioEntidad getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(UsuarioEntidad destinatario) {
		this.destinatario = UtilObjeto.esNulo(destinatario) ? new UsuarioEntidad() : destinatario;
	}

	public CategoriaEntidad getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEntidad categoria) {
		this.categoria = UtilObjeto.esNulo(categoria) ? new CategoriaEntidad() : categoria;
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
