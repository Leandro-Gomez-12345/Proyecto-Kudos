package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.KudoDominio;
import co.edu.uco.kudos.negocio.negocio.KudoNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.KudoEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria.AsegurarCategoriaActivaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria.AsegurarCategoriaExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.Auditor;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarDestinatarioDistintoDelAutorRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarFormatoKudoValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarIdKudoUnicoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarKudoEnRevisionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarKudoExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarKudoNoRepetidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarKudoReportadoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsignarDatosInicialesKudoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.CongelarNombreCategoriaEnKudoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.LiberarKudoAlCerrarRevisionSinVetoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.MarcarKudoReportadoAlRecibirReporteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.MostrarAvisoEnKudoVetadoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.PonerKudoEnRevisionAlIniciarRevisionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.PublicarKudoAlConfirmarRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.VetarKudoAlCerrarRevisionConVetoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarPersonaIndicadaActivaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class KudoNegocioImpl implements KudoNegocio {

	private final DAOFactory daoFactory;

	public KudoNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public KudoDominio crearKudo(KudoDominio datos) {
		var kudo = KudoEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idCategoria = kudo.getCategoria().getId();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarFormatoKudoValidoRule.obtenerInstancia().ejecutar(kudo, daoFactory);
		AsegurarPersonaIndicadaActivaRule.obtenerInstancia().ejecutar(kudo.getDestinatario().getId(), daoFactory);
		AsegurarDestinatarioDistintoDelAutorRule.obtenerInstancia().ejecutar(kudo, daoFactory);
		AsegurarCategoriaExisteRule.obtenerInstancia().ejecutar(idCategoria, daoFactory);
		AsegurarCategoriaActivaRule.obtenerInstancia().ejecutar(idCategoria, daoFactory);
		AsegurarKudoNoRepetidoRule.obtenerInstancia().ejecutar(kudo, daoFactory);
		AsegurarIdKudoUnicoRule.obtenerInstancia().ejecutar(kudo, daoFactory);
		AsignarDatosInicialesKudoRule.obtenerInstancia().ejecutar(kudo, daoFactory);
		CongelarNombreCategoriaEnKudoRule.obtenerInstancia().ejecutar(kudo, daoFactory);
		PublicarKudoAlConfirmarRule.obtenerInstancia().ejecutar(kudo, daoFactory);

		daoFactory.obtenerKudoDAO().crear(kudo);
		Auditor.registrar(daoFactory, EventoAuditable.CREAR_KUDO, kudo.getId(),
				UtilTexto.concatenar("Kudo creado en la categoría ", kudo.getNombreCategoria()));
		return KudoEntidadAssembler.obtenerInstancia().convertirADominio(kudo);
	}

	@Override
	public KudoDominio consultarKudo(UUID id) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarKudoExisteRule.obtenerInstancia().ejecutar(id, daoFactory);

		var kudo = daoFactory.obtenerKudoDAO().consultarPorId(id);
		MostrarAvisoEnKudoVetadoRule.obtenerInstancia().ejecutar(kudo, daoFactory);
		return KudoEntidadAssembler.obtenerInstancia().convertirADominio(kudo);
	}

	@Override
	public List<KudoDominio> consultarKudos(KudoDominio filtro) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);

		var filtroEntidad = KudoEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		var kudos = daoFactory.obtenerKudoDAO().consultarPorFiltro(filtroEntidad);
		for (var kudo : kudos) {
			MostrarAvisoEnKudoVetadoRule.obtenerInstancia().ejecutar(kudo, daoFactory);
		}
		return KudoEntidadAssembler.obtenerInstancia().convertirADominio(kudos);
	}

	@Override
	public void marcarKudoReportado(UUID id) {
		AsegurarKudoExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		MarcarKudoReportadoAlRecibirReporteRule.obtenerInstancia().ejecutar(id, daoFactory);
	}

	@Override
	public void ponerKudoEnRevision(UUID id) {
		AsegurarKudoExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarKudoReportadoRule.obtenerInstancia().ejecutar(id, daoFactory);
		PonerKudoEnRevisionAlIniciarRevisionRule.obtenerInstancia().ejecutar(id, daoFactory);
	}

	@Override
	public void vetarKudo(UUID id) {
		AsegurarKudoExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		VetarKudoAlCerrarRevisionConVetoRule.obtenerInstancia().ejecutar(id, daoFactory);
		Auditor.registrar(daoFactory, EventoAuditable.VETAR_KUDO, id, "Kudo vetado");
	}

	@Override
	public void liberarKudo(UUID id) {
		AsegurarKudoExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarKudoEnRevisionRule.obtenerInstancia().ejecutar(id, daoFactory);
		LiberarKudoAlCerrarRevisionSinVetoRule.obtenerInstancia().ejecutar(id, daoFactory);
		Auditor.registrar(daoFactory, EventoAuditable.LIBERAR_KUDO, id, "Kudo liberado tras la revisión");
	}
}
