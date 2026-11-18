package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.ReplicaDominio;
import co.edu.uco.kudos.entidad.ReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class ReplicaEntidadAssembler implements EntidadAssembler<ReplicaDominio, ReplicaEntidad> {

	private static final EntidadAssembler<ReplicaDominio, ReplicaEntidad> INSTANCIA = new ReplicaEntidadAssembler();

	private ReplicaEntidadAssembler() {

	}

	public static EntidadAssembler<ReplicaDominio, ReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public ReplicaEntidad convertirAEntidad(ReplicaDominio dominio) {
		var dominioTmp = ReplicaDominio.obtenerValorDefecto(dominio);
		var entidad = new ReplicaEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setKudo(KudoEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getKudo()));
		entidad.setReplicaPadre(convertirReplicaPadreAEntidad(dominioTmp.getReplicaPadre()));
		entidad.setAutor(UsuarioEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getAutor()));
		entidad.setContenido(dominioTmp.getContenido());
		entidad.setEstadoContenido(EstadoContenidoEntidadAssembler.obtenerInstancia()
				.convertirAEntidad(dominioTmp.getEstadoContenido()));
		entidad.setFechaCreacion(dominioTmp.getFechaCreacion());
		return entidad;
	}

	@Override
	public ReplicaDominio convertirADominio(ReplicaEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new ReplicaEntidad());
		return new ReplicaDominio.Builder()
				.id(entidadTmp.getId())
				.kudo(KudoEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getKudo()))
				.replicaPadre(convertirReplicaPadreADominio(entidadTmp.getReplicaPadre()))
				.autor(UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getAutor()))
				.contenido(entidadTmp.getContenido())
				.estadoContenido(EstadoContenidoEntidadAssembler.obtenerInstancia()
						.convertirADominio(entidadTmp.getEstadoContenido()))
				.fechaCreacion(entidadTmp.getFechaCreacion())
				.build();
	}

	private static ReplicaEntidad convertirReplicaPadreAEntidad(ReplicaDominio replicaPadre) {
		return UtilObjeto.esNulo(replicaPadre) ? null : INSTANCIA.convertirAEntidad(replicaPadre);
	}

	private static ReplicaDominio convertirReplicaPadreADominio(ReplicaEntidad replicaPadre) {
		return UtilObjeto.esNulo(replicaPadre) ? null : INSTANCIA.convertirADominio(replicaPadre);
	}
}
