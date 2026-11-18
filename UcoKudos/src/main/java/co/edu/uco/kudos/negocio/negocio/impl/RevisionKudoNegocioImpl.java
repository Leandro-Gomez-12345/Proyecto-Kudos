package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.RevisionKudoDominio;
import co.edu.uco.kudos.entidad.ReporteKudoEntidad;
import co.edu.uco.kudos.entidad.RevisionKudoEntidad;
import co.edu.uco.kudos.negocio.negocio.RevisionKudoNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.RevisionKudoEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.Auditor;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarKudoExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarKudoPuedeVetarseDirectamenteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarKudoReportadoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.LiberarKudoAlCerrarRevisionSinVetoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.PonerKudoEnRevisionAlIniciarRevisionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.VetarKudoAlCerrarRevisionConVetoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo.EnlazarReportesKudoPendientesConRevisionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo.AsegurarFormatoJustificacionRevisionKudoValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo.AsegurarFormatoVetoDirectoKudoValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo.AsegurarIdRevisionKudoUnicoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo.AsegurarKudoSinRevisionEnCursoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo.AsegurarRevisionKudoEnCursoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo.AsegurarRevisionKudoExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo.AsegurarRevisionKudoNoRepetidaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo.AsignarCierreConLiberacionRevisionKudoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo.AsignarCierreConVetoRevisionKudoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo.AsignarDatosInicialesRevisionKudoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.revisionkudo.RegistrarVetoDirectoComoRevisionKudoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioPuedeModerarRule;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class RevisionKudoNegocioImpl implements RevisionKudoNegocio {

	private final DAOFactory daoFactory;

	public RevisionKudoNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public RevisionKudoDominio iniciarRevisionKudo(RevisionKudoDominio datos) {
		var revision = RevisionKudoEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idKudo = revision.getKudo().getId();

		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioPuedeModerarRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarKudoExisteRule.obtenerInstancia().ejecutar(idKudo, daoFactory);
		AsegurarKudoReportadoRule.obtenerInstancia().ejecutar(idKudo, daoFactory);
		AsegurarKudoSinRevisionEnCursoRule.obtenerInstancia().ejecutar(revision, daoFactory);
		AsegurarRevisionKudoNoRepetidaRule.obtenerInstancia().ejecutar(revision, daoFactory);
		AsegurarIdRevisionKudoUnicoRule.obtenerInstancia().ejecutar(revision, daoFactory);
		AsignarDatosInicialesRevisionKudoRule.obtenerInstancia().ejecutar(revision, daoFactory);

		daoFactory.obtenerRevisionKudoDAO().crear(revision);
		PonerKudoEnRevisionAlIniciarRevisionRule.obtenerInstancia().ejecutar(idKudo, daoFactory);
		enlazarReportesPendientes(revision);
		Auditor.registrar(daoFactory, EventoAuditable.INICIAR_REVISION_KUDO, revision.getId(),
				"Revisión iniciada");
		return RevisionKudoEntidadAssembler.obtenerInstancia().convertirADominio(revision);
	}

	@Override
	public void cerrarRevisionKudoConVeto(UUID id, RevisionKudoDominio datos) {
		var revision = prepararCierre(id, datos);
		AsignarCierreConVetoRevisionKudoRule.obtenerInstancia().ejecutar(revision, daoFactory);

		daoFactory.obtenerRevisionKudoDAO().actualizar(id, revision);
		var idContenido = revision.getKudo().getId();
		VetarKudoAlCerrarRevisionConVetoRule.obtenerInstancia().ejecutar(idContenido, daoFactory);
		Auditor.registrar(daoFactory, EventoAuditable.CERRAR_REVISION_KUDO_CON_VETO, id,
				UtilTexto.concatenar("Contenido vetado: ", revision.getJustificacion()));
	}

	@Override
	public void cerrarRevisionKudoConLiberacion(UUID id, RevisionKudoDominio datos) {
		var revision = prepararCierre(id, datos);
		AsignarCierreConLiberacionRevisionKudoRule.obtenerInstancia().ejecutar(revision, daoFactory);

		daoFactory.obtenerRevisionKudoDAO().actualizar(id, revision);
		var idContenido = revision.getKudo().getId();
		LiberarKudoAlCerrarRevisionSinVetoRule.obtenerInstancia().ejecutar(idContenido, daoFactory);
		Auditor.registrar(daoFactory, EventoAuditable.CERRAR_REVISION_KUDO_CON_LIBERACION, id,
				UtilTexto.concatenar("Contenido liberado: ", revision.getJustificacion()));
	}

	@Override
	public RevisionKudoDominio vetarKudoDirectamente(RevisionKudoDominio datos) {
		var revision = RevisionKudoEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idKudo = revision.getKudo().getId();

		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioPuedeModerarRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarFormatoVetoDirectoKudoValidoRule.obtenerInstancia().ejecutar(revision);
		AsegurarKudoExisteRule.obtenerInstancia().ejecutar(idKudo, daoFactory);
		AsegurarKudoPuedeVetarseDirectamenteRule.obtenerInstancia().ejecutar(idKudo, daoFactory);
		AsegurarRevisionKudoNoRepetidaRule.obtenerInstancia().ejecutar(revision, daoFactory);
		AsegurarIdRevisionKudoUnicoRule.obtenerInstancia().ejecutar(revision, daoFactory);
		RegistrarVetoDirectoComoRevisionKudoRule.obtenerInstancia().ejecutar(revision, daoFactory);

		daoFactory.obtenerRevisionKudoDAO().crear(revision);
		VetarKudoAlCerrarRevisionConVetoRule.obtenerInstancia().ejecutar(idKudo, daoFactory);
		enlazarReportesPendientes(revision);
		Auditor.registrar(daoFactory, EventoAuditable.VETAR_KUDO_DIRECTAMENTE, revision.getId(),
				UtilTexto.concatenar("Veto directo: ", revision.getJustificacion()));
		return RevisionKudoEntidadAssembler.obtenerInstancia().convertirADominio(revision);
	}

	@Override
	public List<RevisionKudoDominio> consultarRevisionesKudo(RevisionKudoDominio filtro) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioPuedeModerarRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);

		var filtroEntidad = RevisionKudoEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		var revisiones = daoFactory.obtenerRevisionKudoDAO().consultarPorFiltro(filtroEntidad);
		return RevisionKudoEntidadAssembler.obtenerInstancia().convertirADominio(revisiones);
	}

	private RevisionKudoEntidad prepararCierre(UUID id, RevisionKudoDominio datos) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioPuedeModerarRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarRevisionKudoExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarRevisionKudoEnCursoRule.obtenerInstancia().ejecutar(id, daoFactory);

		var revision = daoFactory.obtenerRevisionKudoDAO().consultarPorId(id);
		revision.setJustificacion(datos.getJustificacion());
		AsegurarFormatoJustificacionRevisionKudoValidoRule.obtenerInstancia().ejecutar(revision);
		return revision;
	}

	private void enlazarReportesPendientes(RevisionKudoEntidad revision) {
		var enlace = new ReporteKudoEntidad();
		enlace.setKudo(revision.getKudo());
		enlace.setRevisionKudo(revision);
		EnlazarReportesKudoPendientesConRevisionRule.obtenerInstancia().ejecutar(enlace, daoFactory);
	}
}
