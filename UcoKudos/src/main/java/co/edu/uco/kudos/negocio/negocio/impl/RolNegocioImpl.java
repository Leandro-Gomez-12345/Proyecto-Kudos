package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.RolDominio;
import co.edu.uco.kudos.negocio.negocio.RolNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.RolEntidadAssembler;

public final class RolNegocioImpl implements RolNegocio {

	private final DAOFactory daoFactory;

	public RolNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<RolDominio> consultarRoles() {
		var registros = daoFactory.obtenerRolDAO().consultarTodos();
		return RolEntidadAssembler.obtenerInstancia().convertirADominio(registros);
	}
}
