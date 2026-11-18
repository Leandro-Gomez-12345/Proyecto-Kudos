package co.edu.uco.kudos.negocio.negocio.reglas.impl.reportekudo;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorUsuarios;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarConsultaReportesKudoPorModeradoresRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarConsultaReportesKudoPorModeradoresRule();

	private AsegurarConsultaReportesKudoPorModeradoresRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var idEjecutor = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		ValidadorNegocio.asegurarCondicion(ConsultorUsuarios.tieneRol(daoFactory, idEjecutor,
				NombresCatalogo.Rol.RECTOR, NombresCatalogo.Rol.COORDINADOR_ACADEMICO),
				CatalogoMensajes.Reglas.USUARIO_NO_PUEDE_CONSULTAR_REPORTES);
	}
}
