package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.ReplicaDominio;
import co.edu.uco.kudos.negocio.negocio.ReplicaNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.ReplicaEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.Auditor;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarKudoAdmiteInteraccionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarKudoExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsegurarConversacionSinCiclosRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsegurarFormatoReplicaValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsegurarIdReplicaUnicoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsegurarReplicaAdmiteInteraccionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsegurarReplicaEnRevisionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsegurarReplicaExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsegurarReplicaNoRepetidaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsegurarReplicaPadreDelMismoKudoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsegurarReplicaReportadaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsignarDatosInicialesReplicaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.LiberarReplicaAlCerrarRevisionSinVetoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.MarcarReplicaReportadaAlRecibirReporteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.MostrarAvisoEnReplicaVetadaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.PonerReplicaEnRevisionAlIniciarRevisionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.PublicarReplicaAlConfirmarRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.VetarReplicaAlCerrarRevisionConVetoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ReplicaNegocioImpl implements ReplicaNegocio {

	private final DAOFactory daoFactory;

	public ReplicaNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ReplicaDominio replicarKudo(ReplicaDominio datos) {
		var replica = ReplicaEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		replica.setReplicaPadre(null);
		var idKudo = replica.getKudo().getId();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarFormatoReplicaValidoRule.obtenerInstancia().ejecutar(replica, daoFactory);
		AsegurarKudoExisteRule.obtenerInstancia().ejecutar(idKudo, daoFactory);
		AsegurarKudoAdmiteInteraccionRule.obtenerInstancia().ejecutar(idKudo, daoFactory);
		AsegurarReplicaNoRepetidaRule.obtenerInstancia().ejecutar(replica, daoFactory);
		AsegurarIdReplicaUnicoRule.obtenerInstancia().ejecutar(replica, daoFactory);
		AsignarDatosInicialesReplicaRule.obtenerInstancia().ejecutar(replica, daoFactory);
		PublicarReplicaAlConfirmarRule.obtenerInstancia().ejecutar(replica, daoFactory);

		daoFactory.obtenerReplicaDAO().crear(replica);
		Auditor.registrar(daoFactory, EventoAuditable.REPLICAR_KUDO, replica.getId(), "Réplica creada sobre un Kudo");
		return ReplicaEntidadAssembler.obtenerInstancia().convertirADominio(replica);
	}

	@Override
	public ReplicaDominio responderReplica(ReplicaDominio datos) {
		var replica = ReplicaEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idPadre = UtilObjeto.esNulo(replica.getReplicaPadre()) ? UtilUUID.obtenerUUIDDefecto()
				: replica.getReplicaPadre().getId();
		var padre = daoFactory.obtenerReplicaDAO().consultarPorId(idPadre);
		if (UtilUUID.esValorDefecto(replica.getKudo().getId())) {
			replica.setKudo(padre.getKudo());
		}

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarFormatoReplicaValidoRule.obtenerInstancia().ejecutar(replica, daoFactory);
		AsegurarReplicaExisteRule.obtenerInstancia().ejecutar(idPadre, daoFactory);
		AsegurarReplicaAdmiteInteraccionRule.obtenerInstancia().ejecutar(idPadre, daoFactory);
		AsegurarReplicaPadreDelMismoKudoRule.obtenerInstancia().ejecutar(replica, daoFactory);
		AsegurarConversacionSinCiclosRule.obtenerInstancia().ejecutar(replica, daoFactory);
		AsegurarKudoAdmiteInteraccionRule.obtenerInstancia().ejecutar(replica.getKudo().getId(), daoFactory);
		AsegurarReplicaNoRepetidaRule.obtenerInstancia().ejecutar(replica, daoFactory);
		AsegurarIdReplicaUnicoRule.obtenerInstancia().ejecutar(replica, daoFactory);
		AsignarDatosInicialesReplicaRule.obtenerInstancia().ejecutar(replica, daoFactory);
		PublicarReplicaAlConfirmarRule.obtenerInstancia().ejecutar(replica, daoFactory);

		daoFactory.obtenerReplicaDAO().crear(replica);
		Auditor.registrar(daoFactory, EventoAuditable.RESPONDER_REPLICA, replica.getId(),
				"Réplica creada en respuesta a otra réplica");
		return ReplicaEntidadAssembler.obtenerInstancia().convertirADominio(replica);
	}

	@Override
	public List<ReplicaDominio> consultarConversacionKudo(ReplicaDominio filtro) {
		var filtroEntidad = ReplicaEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarKudoExisteRule.obtenerInstancia().ejecutar(filtroEntidad.getKudo().getId(), daoFactory);

		var replicas = daoFactory.obtenerReplicaDAO().consultarPorFiltro(filtroEntidad);
		for (var replica : replicas) {
			MostrarAvisoEnReplicaVetadaRule.obtenerInstancia().ejecutar(replica, daoFactory);
		}
		return ReplicaEntidadAssembler.obtenerInstancia().convertirADominio(replicas);
	}

	@Override
	public void marcarReplicaReportada(UUID id) {
		AsegurarReplicaExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		MarcarReplicaReportadaAlRecibirReporteRule.obtenerInstancia().ejecutar(id, daoFactory);
	}

	@Override
	public void ponerReplicaEnRevision(UUID id) {
		AsegurarReplicaExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarReplicaReportadaRule.obtenerInstancia().ejecutar(id, daoFactory);
		PonerReplicaEnRevisionAlIniciarRevisionRule.obtenerInstancia().ejecutar(id, daoFactory);
	}

	@Override
	public void vetarReplica(UUID id) {
		AsegurarReplicaExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		VetarReplicaAlCerrarRevisionConVetoRule.obtenerInstancia().ejecutar(id, daoFactory);
		Auditor.registrar(daoFactory, EventoAuditable.VETAR_REPLICA, id, "Réplica vetada");
	}

	@Override
	public void liberarReplica(UUID id) {
		AsegurarReplicaExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarReplicaEnRevisionRule.obtenerInstancia().ejecutar(id, daoFactory);
		LiberarReplicaAlCerrarRevisionSinVetoRule.obtenerInstancia().ejecutar(id, daoFactory);
		Auditor.registrar(daoFactory, EventoAuditable.LIBERAR_REPLICA, id, "Réplica liberada tras la revisión");
	}
}
