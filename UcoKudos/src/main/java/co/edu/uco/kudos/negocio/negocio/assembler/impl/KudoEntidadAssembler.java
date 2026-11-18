package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.KudoDominio;
import co.edu.uco.kudos.entidad.KudoEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class KudoEntidadAssembler implements EntidadAssembler<KudoDominio, KudoEntidad> {

	private static final EntidadAssembler<KudoDominio, KudoEntidad> INSTANCIA = new KudoEntidadAssembler();

	private KudoEntidadAssembler() {

	}

	public static EntidadAssembler<KudoDominio, KudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public KudoEntidad convertirAEntidad(KudoDominio dominio) {
		var dominioTmp = KudoDominio.obtenerValorDefecto(dominio);
		var entidad = new KudoEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setAutor(UsuarioEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getAutor()));
		entidad.setDestinatario(UsuarioEntidadAssembler.obtenerInstancia()
				.convertirAEntidad(dominioTmp.getDestinatario()));
		entidad.setCategoria(CategoriaEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getCategoria()));
		entidad.setNombreCategoria(dominioTmp.getNombreCategoria());
		entidad.setContenido(dominioTmp.getContenido());
		entidad.setEstadoContenido(EstadoContenidoEntidadAssembler.obtenerInstancia()
				.convertirAEntidad(dominioTmp.getEstadoContenido()));
		entidad.setFechaCreacion(dominioTmp.getFechaCreacion());
		return entidad;
	}

	@Override
	public KudoDominio convertirADominio(KudoEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new KudoEntidad());
		return new KudoDominio.Builder()
				.id(entidadTmp.getId())
				.autor(UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getAutor()))
				.destinatario(UsuarioEntidadAssembler.obtenerInstancia()
						.convertirADominio(entidadTmp.getDestinatario()))
				.categoria(CategoriaEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getCategoria()))
				.nombreCategoria(entidadTmp.getNombreCategoria())
				.contenido(entidadTmp.getContenido())
				.estadoContenido(EstadoContenidoEntidadAssembler.obtenerInstancia()
						.convertirADominio(entidadTmp.getEstadoContenido()))
				.fechaCreacion(entidadTmp.getFechaCreacion())
				.build();
	}
}
