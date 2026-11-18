package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosCuenta;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AprobarCuentaAlAsignarCoordinacionProgramaRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AprobarCuentaAlAsignarCoordinacionProgramaRule();

	private AprobarCuentaAlAsignarCoordinacionProgramaRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var idUsuario = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(idUsuario);
		var nombreEstado = ConsultorCatalogos.nombreEstadoCuenta(daoFactory, usuario.getEstadoCuenta().getId());
		if (ConsultorCatalogos.coincide(nombreEstado, NombresCatalogo.EstadoCuenta.PENDIENTE_APROBACION)) {
			EstadosCuenta.asignar(daoFactory, usuario, NombresCatalogo.EstadoCuenta.APROBADA);
			daoFactory.obtenerUsuarioDAO().actualizar(usuario.getId(), usuario);
		}
	}
}
