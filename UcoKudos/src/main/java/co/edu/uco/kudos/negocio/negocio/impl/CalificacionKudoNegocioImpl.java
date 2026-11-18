package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.CalificacionKudoDominio;
import co.edu.uco.kudos.negocio.negocio.CalificacionKudoNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.CalificacionKudoEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo.ActualizarFechaCalificacionKudoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo.AsegurarCalificacionKudoExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo.AsegurarCalificadorNoEsAutorDeKudoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo.AsegurarEstrellasDistintasEnCalificacionKudoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo.AsegurarFormatoCalificacionKudoValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo.AsegurarIdCalificacionKudoUnicoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo.AsegurarUnicaCalificacionKudoPorPersonaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo.AsegurarUsuarioEsAutorDeCalificacionKudoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionkudo.AsignarAutorYFechaCalificacionKudoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.Auditor;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarKudoAdmiteInteraccionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarKudoExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class CalificacionKudoNegocioImpl implements CalificacionKudoNegocio {

	private final DAOFactory daoFactory;

	public CalificacionKudoNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public CalificacionKudoDominio calificarKudo(CalificacionKudoDominio datos) {
		var calificacion = CalificacionKudoEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idKudo = calificacion.getKudo().getId();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarFormatoCalificacionKudoValidoRule.obtenerInstancia().ejecutar(calificacion);
		AsegurarKudoExisteRule.obtenerInstancia().ejecutar(idKudo, daoFactory);
		AsegurarKudoAdmiteInteraccionRule.obtenerInstancia().ejecutar(idKudo, daoFactory);
		AsegurarCalificadorNoEsAutorDeKudoRule.obtenerInstancia().ejecutar(calificacion, daoFactory);
		AsegurarUnicaCalificacionKudoPorPersonaRule.obtenerInstancia().ejecutar(calificacion, daoFactory);
		AsegurarIdCalificacionKudoUnicoRule.obtenerInstancia().ejecutar(calificacion, daoFactory);
		AsignarAutorYFechaCalificacionKudoRule.obtenerInstancia().ejecutar(calificacion);

		daoFactory.obtenerCalificacionKudoDAO().crear(calificacion);
		Auditor.registrar(daoFactory, EventoAuditable.CALIFICAR_KUDO, calificacion.getId(),
				UtilTexto.concatenar("Calificación de ", String.valueOf(calificacion.getEstrellas()),
						" estrellas"));
		return CalificacionKudoEntidadAssembler.obtenerInstancia().convertirADominio(calificacion);
	}

	@Override
	public CalificacionKudoDominio cambiarCalificacionKudo(UUID id, CalificacionKudoDominio datos) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarCalificacionKudoExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarUsuarioEsAutorDeCalificacionKudoRule.obtenerInstancia().ejecutar(id, daoFactory);

		var calificacion = daoFactory.obtenerCalificacionKudoDAO().consultarPorId(id);
		var idKudo = calificacion.getKudo().getId();
		AsegurarKudoAdmiteInteraccionRule.obtenerInstancia().ejecutar(idKudo, daoFactory);
		var estrellasAnteriores = calificacion.getEstrellas();
		calificacion.setEstrellas(datos.getEstrellas());
		AsegurarFormatoCalificacionKudoValidoRule.obtenerInstancia().ejecutar(calificacion);
		AsegurarEstrellasDistintasEnCalificacionKudoRule.obtenerInstancia().ejecutar(calificacion, daoFactory);
		ActualizarFechaCalificacionKudoRule.obtenerInstancia().ejecutar(calificacion);

		daoFactory.obtenerCalificacionKudoDAO().actualizar(id, calificacion);
		Auditor.registrar(daoFactory, EventoAuditable.CAMBIAR_CALIFICACION_KUDO, id,
				Auditor.detalleCambio(String.valueOf(estrellasAnteriores),
						String.valueOf(calificacion.getEstrellas())));
		return CalificacionKudoEntidadAssembler.obtenerInstancia().convertirADominio(calificacion);
	}

	@Override
	public List<CalificacionKudoDominio> consultarCalificacionesKudo(CalificacionKudoDominio filtro) {
		var filtroEntidad = CalificacionKudoEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarKudoExisteRule.obtenerInstancia().ejecutar(filtroEntidad.getKudo().getId(), daoFactory);

		var calificaciones = daoFactory.obtenerCalificacionKudoDAO().consultarPorFiltro(filtroEntidad);
		return CalificacionKudoEntidadAssembler.obtenerInstancia().convertirADominio(calificaciones);
	}
}
