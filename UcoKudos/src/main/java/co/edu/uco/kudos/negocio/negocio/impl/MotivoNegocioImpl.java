package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.MotivoDominio;
import co.edu.uco.kudos.negocio.negocio.MotivoNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.MotivoEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class MotivoNegocioImpl implements MotivoNegocio {

	private final DAOFactory daoFactory;

	public MotivoNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<MotivoDominio> consultarMotivos() {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(),
				daoFactory);
		var registros = daoFactory.obtenerMotivoDAO().consultarTodos();
		return MotivoEntidadAssembler.obtenerInstancia().convertirADominio(registros);
	}
}
