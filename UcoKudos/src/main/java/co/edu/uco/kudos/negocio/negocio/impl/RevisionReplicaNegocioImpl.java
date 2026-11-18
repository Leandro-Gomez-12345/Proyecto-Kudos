package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.RevisionReplicaDominio;
import co.edu.uco.kudos.entidad.ReporteReplicaEntidad;
import co.edu.uco.kudos.entidad.RevisionReplicaEntidad;
import co.edu.uco.kudos.negocio.negocio.RevisionReplicaNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.RevisionReplicaEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.Auditor;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsegurarReplicaExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsegurarReplicaPuedeVetarseDirectamenteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsegurarReplicaReportadaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.LiberarReplicaAlCerrarRevisionSinVetoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.PonerReplicaEnRevisionAlIniciarRevisionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.VetarReplicaAlCerrarRevisionConVetoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.reportereplica.EnlazarReportesReplicaPendientesConRevisionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica.AsegurarFormatoJustificacionRevisionReplicaValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica.AsegurarFormatoVetoDirectoReplicaValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica.AsegurarIdRevisionReplicaUnicoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica.AsegurarReplicaSinRevisionEnCursoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica.AsegurarRevisionReplicaEnCursoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica.AsegurarRevisionReplicaExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica.AsegurarRevisionReplicaNoRepetidaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica.AsignarCierreConLiberacionRevisionReplicaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica.AsignarCierreConVetoRevisionReplicaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica.AsignarDatosInicialesRevisionReplicaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionreplica.RegistrarVetoDirectoComoRevisionReplicaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioPuedeModerarRule;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class RevisionReplicaNegocioImpl implements RevisionReplicaNegocio {

	private final DAOFactory daoFactory;

	public RevisionReplicaNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public RevisionReplicaDominio iniciarRevisionReplica(RevisionReplicaDominio datos) {
		var revision = RevisionReplicaEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idReplica = revision.getReplica().getId();

		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioPuedeModerarRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarReplicaExisteRule.obtenerInstancia().ejecutar(idReplica, daoFactory);
		AsegurarReplicaReportadaRule.obtenerInstancia().ejecutar(idReplica, daoFactory);
		AsegurarReplicaSinRevisionEnCursoRule.obtenerInstancia().ejecutar(revision, daoFactory);
		AsegurarRevisionReplicaNoRepetidaRule.obtenerInstancia().ejecutar(revision, daoFactory);
		AsegurarIdRevisionReplicaUnicoRule.obtenerInstancia().ejecutar(revision, daoFactory);
		AsignarDatosInicialesRevisionReplicaRule.obtenerInstancia().ejecutar(revision, daoFactory);

		daoFactory.obtenerRevisionReplicaDAO().crear(revision);
		PonerReplicaEnRevisionAlIniciarRevisionRule.obtenerInstancia().ejecutar(idReplica, daoFactory);
		enlazarReportesPendientes(revision);
		Auditor.registrar(daoFactory, EventoAuditable.INICIAR_REVISION_REPLICA, revision.getId(),
				"Revisión iniciada");
		return RevisionReplicaEntidadAssembler.obtenerInstancia().convertirADominio(revision);
	}

	@Override
	public void cerrarRevisionReplicaConVeto(UUID id, RevisionReplicaDominio datos) {
		var revision = prepararCierre(id, datos);
		AsignarCierreConVetoRevisionReplicaRule.obtenerInstancia().ejecutar(revision, daoFactory);

		daoFactory.obtenerRevisionReplicaDAO().actualizar(id, revision);
		var idContenido = revision.getReplica().getId();
		VetarReplicaAlCerrarRevisionConVetoRule.obtenerInstancia().ejecutar(idContenido, daoFactory);
		Auditor.registrar(daoFactory, EventoAuditable.CERRAR_REVISION_REPLICA_CON_VETO, id,
				UtilTexto.concatenar("Contenido vetado: ", revision.getJustificacion()));
	}

	@Override
	public void cerrarRevisionReplicaConLiberacion(UUID id, RevisionReplicaDominio datos) {
		var revision = prepararCierre(id, datos);
		AsignarCierreConLiberacionRevisionReplicaRule.obtenerInstancia().ejecutar(revision, daoFactory);

		daoFactory.obtenerRevisionReplicaDAO().actualizar(id, revision);
		var idContenido = revision.getReplica().getId();
		LiberarReplicaAlCerrarRevisionSinVetoRule.obtenerInstancia().ejecutar(idContenido, daoFactory);
		Auditor.registrar(daoFactory, EventoAuditable.CERRAR_REVISION_REPLICA_CON_LIBERACION, id,
				UtilTexto.concatenar("Contenido liberado: ", revision.getJustificacion()));
	}

	@Override
	public RevisionReplicaDominio vetarReplicaDirectamente(RevisionReplicaDominio datos) {
		var revision = RevisionReplicaEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idReplica = revision.getReplica().getId();

		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioPuedeModerarRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarFormatoVetoDirectoReplicaValidoRule.obtenerInstancia().ejecutar(revision);
		AsegurarReplicaExisteRule.obtenerInstancia().ejecutar(idReplica, daoFactory);
		AsegurarReplicaPuedeVetarseDirectamenteRule.obtenerInstancia().ejecutar(idReplica, daoFactory);
		AsegurarRevisionReplicaNoRepetidaRule.obtenerInstancia().ejecutar(revision, daoFactory);
		AsegurarIdRevisionReplicaUnicoRule.obtenerInstancia().ejecutar(revision, daoFactory);
		RegistrarVetoDirectoComoRevisionReplicaRule.obtenerInstancia().ejecutar(revision, daoFactory);

		daoFactory.obtenerRevisionReplicaDAO().crear(revision);
		VetarReplicaAlCerrarRevisionConVetoRule.obtenerInstancia().ejecutar(idReplica, daoFactory);
		enlazarReportesPendientes(revision);
		Auditor.registrar(daoFactory, EventoAuditable.VETAR_REPLICA_DIRECTAMENTE, revision.getId(),
				UtilTexto.concatenar("Veto directo: ", revision.getJustificacion()));
		return RevisionReplicaEntidadAssembler.obtenerInstancia().convertirADominio(revision);
	}

	@Override
	public List<RevisionReplicaDominio> consultarRevisionesReplica(RevisionReplicaDominio filtro) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioPuedeModerarRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);

		var filtroEntidad = RevisionReplicaEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		var revisiones = daoFactory.obtenerRevisionReplicaDAO().consultarPorFiltro(filtroEntidad);
		return RevisionReplicaEntidadAssembler.obtenerInstancia().convertirADominio(revisiones);
	}

	private RevisionReplicaEntidad prepararCierre(UUID id, RevisionReplicaDominio datos) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioPuedeModerarRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarRevisionReplicaExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarRevisionReplicaEnCursoRule.obtenerInstancia().ejecutar(id, daoFactory);

		var revision = daoFactory.obtenerRevisionReplicaDAO().consultarPorId(id);
		revision.setJustificacion(datos.getJustificacion());
		AsegurarFormatoJustificacionRevisionReplicaValidoRule.obtenerInstancia().ejecutar(revision);
		return revision;
	}

	private void enlazarReportesPendientes(RevisionReplicaEntidad revision) {
		var enlace = new ReporteReplicaEntidad();
		enlace.setReplica(revision.getReplica());
		enlace.setRevisionReplica(revision);
		EnlazarReportesReplicaPendientesConRevisionRule.obtenerInstancia().ejecutar(enlace, daoFactory);
	}
}
