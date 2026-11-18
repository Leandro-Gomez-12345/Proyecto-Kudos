package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.CalificacionReplicaDominio;
import co.edu.uco.kudos.negocio.negocio.CalificacionReplicaNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.CalificacionReplicaEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica.ActualizarFechaCalificacionReplicaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica.AsegurarCalificacionReplicaExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica.AsegurarCalificadorNoEsAutorDeReplicaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica.AsegurarEstrellasDistintasEnCalificacionReplicaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica.AsegurarFormatoCalificacionReplicaValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica.AsegurarIdCalificacionReplicaUnicoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica.AsegurarUnicaCalificacionReplicaPorPersonaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica.AsegurarUsuarioEsAutorDeCalificacionReplicaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.calificacionreplica.AsignarAutorYFechaCalificacionReplicaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.Auditor;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo.AsegurarKudoAdmiteInteraccionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsegurarReplicaAdmiteInteraccionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.replica.AsegurarReplicaExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class CalificacionReplicaNegocioImpl implements CalificacionReplicaNegocio {

	private final DAOFactory daoFactory;

	public CalificacionReplicaNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public CalificacionReplicaDominio calificarReplica(CalificacionReplicaDominio datos) {
		var calificacion = CalificacionReplicaEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idReplica = calificacion.getReplica().getId();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarFormatoCalificacionReplicaValidoRule.obtenerInstancia().ejecutar(calificacion, daoFactory);
		AsegurarReplicaExisteRule.obtenerInstancia().ejecutar(idReplica, daoFactory);
		AsegurarReplicaAdmiteInteraccionRule.obtenerInstancia().ejecutar(idReplica, daoFactory);
		AsegurarKudoAdmiteInteraccionRule.obtenerInstancia().ejecutar(idKudoDe(idReplica), daoFactory);
		AsegurarCalificadorNoEsAutorDeReplicaRule.obtenerInstancia().ejecutar(calificacion, daoFactory);
		AsegurarUnicaCalificacionReplicaPorPersonaRule.obtenerInstancia().ejecutar(calificacion, daoFactory);
		AsegurarIdCalificacionReplicaUnicoRule.obtenerInstancia().ejecutar(calificacion, daoFactory);
		AsignarAutorYFechaCalificacionReplicaRule.obtenerInstancia().ejecutar(calificacion, daoFactory);

		daoFactory.obtenerCalificacionReplicaDAO().crear(calificacion);
		Auditor.registrar(daoFactory, EventoAuditable.CALIFICAR_REPLICA, calificacion.getId(),
				UtilTexto.concatenar("Calificación de ", String.valueOf(calificacion.getEstrellas()),
						" estrellas"));
		return CalificacionReplicaEntidadAssembler.obtenerInstancia().convertirADominio(calificacion);
	}

	@Override
	public CalificacionReplicaDominio cambiarCalificacionReplica(UUID id, CalificacionReplicaDominio datos) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarCalificacionReplicaExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarUsuarioEsAutorDeCalificacionReplicaRule.obtenerInstancia().ejecutar(id, daoFactory);

		var calificacion = daoFactory.obtenerCalificacionReplicaDAO().consultarPorId(id);
		var idReplica = calificacion.getReplica().getId();
		AsegurarReplicaAdmiteInteraccionRule.obtenerInstancia().ejecutar(idReplica, daoFactory);
		AsegurarKudoAdmiteInteraccionRule.obtenerInstancia().ejecutar(idKudoDe(idReplica), daoFactory);
		var estrellasAnteriores = calificacion.getEstrellas();
		calificacion.setEstrellas(datos.getEstrellas());
		AsegurarFormatoCalificacionReplicaValidoRule.obtenerInstancia().ejecutar(calificacion, daoFactory);
		AsegurarEstrellasDistintasEnCalificacionReplicaRule.obtenerInstancia().ejecutar(calificacion, daoFactory);
		ActualizarFechaCalificacionReplicaRule.obtenerInstancia().ejecutar(calificacion, daoFactory);

		daoFactory.obtenerCalificacionReplicaDAO().actualizar(id, calificacion);
		Auditor.registrar(daoFactory, EventoAuditable.CAMBIAR_CALIFICACION_REPLICA, id,
				Auditor.detalleCambio(String.valueOf(estrellasAnteriores),
						String.valueOf(calificacion.getEstrellas())));
		return CalificacionReplicaEntidadAssembler.obtenerInstancia().convertirADominio(calificacion);
	}

	@Override
	public List<CalificacionReplicaDominio> consultarCalificacionesReplica(CalificacionReplicaDominio filtro) {
		var filtroEntidad = CalificacionReplicaEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarReplicaExisteRule.obtenerInstancia().ejecutar(filtroEntidad.getReplica().getId(), daoFactory);

		var calificaciones = daoFactory.obtenerCalificacionReplicaDAO().consultarPorFiltro(filtroEntidad);
		return CalificacionReplicaEntidadAssembler.obtenerInstancia().convertirADominio(calificaciones);
	}

	private UUID idKudoDe(UUID idReplica) {
		return daoFactory.obtenerReplicaDAO().consultarPorId(idReplica).getKudo().getId();
	}
}
