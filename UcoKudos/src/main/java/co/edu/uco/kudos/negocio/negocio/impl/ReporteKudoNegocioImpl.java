package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.ReporteKudoDominio;
import co.edu.uco.kudos.negocio.negocio.ReporteKudoNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.ReporteKudoEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.Auditor;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ReportesConEstado;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarKudoExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarKudoPuedeReportarseRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.MarcarKudoReportadoAlRecibirReporteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.motivo.AsegurarMotivoExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo.AsegurarConsultaReportesKudoPorModeradoresRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo.AsegurarFormatoReporteKudoValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo.AsegurarIdReporteKudoUnicoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo.AsegurarReporteKudoNoRepetidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo.AsignarDatosInicialesReporteKudoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo.DeducirEstadoReporteKudoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo.EnlazarReportesKudoPendientesConRevisionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class ReporteKudoNegocioImpl implements ReporteKudoNegocio {

	private final DAOFactory daoFactory;

	public ReporteKudoNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ReporteKudoDominio reportarKudo(ReporteKudoDominio datos) {
		var reporte = ReporteKudoEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idKudo = reporte.getKudo().getId();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarFormatoReporteKudoValidoRule.obtenerInstancia().ejecutar(reporte, daoFactory);
		AsegurarKudoExisteRule.obtenerInstancia().ejecutar(idKudo, daoFactory);
		AsegurarKudoPuedeReportarseRule.obtenerInstancia().ejecutar(idKudo, daoFactory);
		AsegurarMotivoExisteRule.obtenerInstancia().ejecutar(reporte.getMotivo().getId(), daoFactory);
		AsegurarReporteKudoNoRepetidoRule.obtenerInstancia().ejecutar(reporte, daoFactory);
		AsegurarIdReporteKudoUnicoRule.obtenerInstancia().ejecutar(reporte, daoFactory);
		AsignarDatosInicialesReporteKudoRule.obtenerInstancia().ejecutar(reporte, daoFactory);

		daoFactory.obtenerReporteKudoDAO().crear(reporte);
		MarcarKudoReportadoAlRecibirReporteRule.obtenerInstancia().ejecutar(idKudo, daoFactory);
		Auditor.registrar(daoFactory, EventoAuditable.REPORTAR_KUDO, reporte.getId(),
				UtilTexto.concatenar("Reporte por el motivo ",
						ConsultorCatalogos.nombreMotivo(daoFactory, reporte.getMotivo().getId())));
		return ReporteKudoEntidadAssembler.obtenerInstancia().convertirADominio(reporte);
	}

	@Override
	public List<ReporteKudoDominio> consultarReportesKudo(ReporteKudoDominio filtro, String estado) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarConsultaReportesKudoPorModeradoresRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);

		var filtroEntidad = ReporteKudoEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		var reportes = new ArrayList<>(daoFactory.obtenerReporteKudoDAO().consultarPorFiltro(filtroEntidad));
		DeducirEstadoReporteKudoRule.obtenerInstancia().ejecutar(new ReportesConEstado<>(reportes, estado),
				daoFactory);
		return ReporteKudoEntidadAssembler.obtenerInstancia().convertirADominio(reportes);
	}

	@Override
	public void enlazarReportesKudoConRevision(ReporteKudoDominio filtro, ReporteKudoDominio datos) {
		var enlace = ReporteKudoEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		var datosEntidad = ReporteKudoEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		enlace.setRevisionKudo(datosEntidad.getRevisionKudo());
		EnlazarReportesKudoPendientesConRevisionRule.obtenerInstancia().ejecutar(enlace, daoFactory);
	}
}
