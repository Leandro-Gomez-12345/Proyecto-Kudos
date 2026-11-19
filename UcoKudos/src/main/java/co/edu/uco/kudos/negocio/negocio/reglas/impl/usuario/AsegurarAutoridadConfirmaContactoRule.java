package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.AmbitoAutoridad;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorUsuarios;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;

public final class AsegurarAutoridadConfirmaContactoRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarAutoridadConfirmaContactoRule();

	private AsegurarAutoridadConfirmaContactoRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var idPersona = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();
		var cuentaConfirmable = !ConsultorUsuarios.tieneEstadoCuenta(daoFactory, idPersona,
				NombresCatalogo.EstadoCuenta.RECHAZADA,
				NombresCatalogo.EstadoCuenta.DESACTIVADA);
		ValidadorNegocio.asegurarCondicion(
				cuentaConfirmable && AmbitoAutoridad.gestiona(daoFactory, idEjecutor, idPersona),
				CatalogoMensajes.Reglas.CONFIRMADOR_NO_CORRESPONDE_A_LA_PERSONA);
	}
}
