package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.EstadoContenidoDominio;
import co.edu.uco.kudos.negocio.negocio.EstadoContenidoNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.EstadoContenidoEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class EstadoContenidoNegocioImpl implements EstadoContenidoNegocio {

	private final DAOFactory daoFactory;

	public EstadoContenidoNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<EstadoContenidoDominio> consultarEstadosContenido() {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(),
				daoFactory);
		var registros = daoFactory.obtenerEstadoContenidoDAO().consultarTodos();
		return EstadoContenidoEntidadAssembler.obtenerInstancia().convertirADominio(registros);
	}
}
