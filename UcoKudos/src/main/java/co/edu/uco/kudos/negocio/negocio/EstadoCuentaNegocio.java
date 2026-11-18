package co.edu.uco.kudos.negocio.negocio;

import java.util.List;

import co.edu.uco.kudos.dominio.EstadoCuentaDominio;

public interface EstadoCuentaNegocio {

	List<EstadoCuentaDominio> consultarEstadosCuenta();
}
