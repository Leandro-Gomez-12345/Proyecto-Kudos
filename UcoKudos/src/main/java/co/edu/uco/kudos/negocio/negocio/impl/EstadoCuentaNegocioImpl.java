package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.EstadoCuentaDominio;
import co.edu.uco.kudos.negocio.negocio.EstadoCuentaNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.EstadoCuentaEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class EstadoCuentaNegocioImpl implements EstadoCuentaNegocio {

	private final DAOFactory daoFactory;

	public EstadoCuentaNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<EstadoCuentaDominio> consultarEstadosCuenta() {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		var registros = daoFactory.obtenerEstadoCuentaDAO().consultarTodos();
		return EstadoCuentaEntidadAssembler.obtenerInstancia().convertirADominio(registros);
	}
}
