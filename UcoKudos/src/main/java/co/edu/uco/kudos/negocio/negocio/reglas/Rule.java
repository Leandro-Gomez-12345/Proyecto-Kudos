package co.edu.uco.kudos.negocio.negocio.reglas;

import co.edu.uco.kudos.dao.factoria.DAOFactory;

public interface Rule<O> {

	void ejecutar(O dato, DAOFactory daoFactory);
}
