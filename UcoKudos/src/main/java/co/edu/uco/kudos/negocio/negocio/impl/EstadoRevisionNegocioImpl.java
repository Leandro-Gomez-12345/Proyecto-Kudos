package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.EstadoRevisionDominio;
import co.edu.uco.kudos.negocio.negocio.EstadoRevisionNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.EstadoRevisionEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class EstadoRevisionNegocioImpl implements EstadoRevisionNegocio {

	private final DAOFactory daoFactory;

	public EstadoRevisionNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<EstadoRevisionDominio> consultarEstadosRevision() {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(),
				daoFactory);
		var registros = daoFactory.obtenerEstadoRevisionDAO().consultarTodos();
		return EstadoRevisionEntidadAssembler.obtenerInstancia().convertirADominio(registros);
	}
}
