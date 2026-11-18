package co.edu.uco.kudos.negocio.negocio.assembler.impl;

import co.edu.uco.kudos.dominio.CalificacionReplicaDominio;
import co.edu.uco.kudos.entidad.CalificacionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.assembler.EntidadAssembler;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class CalificacionReplicaEntidadAssembler
		implements EntidadAssembler<CalificacionReplicaDominio, CalificacionReplicaEntidad> {

	private static final EntidadAssembler<CalificacionReplicaDominio, CalificacionReplicaEntidad> INSTANCIA =
			new CalificacionReplicaEntidadAssembler();

	private CalificacionReplicaEntidadAssembler() {

	}

	public static EntidadAssembler<CalificacionReplicaDominio, CalificacionReplicaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public CalificacionReplicaEntidad convertirAEntidad(CalificacionReplicaDominio dominio) {
		var dominioTmp = CalificacionReplicaDominio.obtenerValorDefecto(dominio);
		var entidad = new CalificacionReplicaEntidad();
		entidad.setId(dominioTmp.getId());
		entidad.setReplica(ReplicaEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getReplica()));
		entidad.setUsuario(UsuarioEntidadAssembler.obtenerInstancia().convertirAEntidad(dominioTmp.getUsuario()));
		entidad.setEstrellas(dominioTmp.getEstrellas());
		entidad.setFechaCalificacion(dominioTmp.getFechaCalificacion());
		return entidad;
	}

	@Override
	public CalificacionReplicaDominio convertirADominio(CalificacionReplicaEntidad entidad) {
		var entidadTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new CalificacionReplicaEntidad());
		return new CalificacionReplicaDominio.Builder()
				.id(entidadTmp.getId())
				.replica(ReplicaEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getReplica()))
				.usuario(UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(entidadTmp.getUsuario()))
				.estrellas(entidadTmp.getEstrellas())
				.fechaCalificacion(entidadTmp.getFechaCalificacion())
				.build();
	}
}
