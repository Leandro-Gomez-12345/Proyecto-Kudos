package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosContenido;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarKudoAdmiteInteraccionRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarKudoAdmiteInteraccionRule();

	private AsegurarKudoAdmiteInteraccionRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var id = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		ValidadorNegocio.asegurarCondicion(EstadosContenido.kudoEstaEn(daoFactory, id,
				NombresCatalogo.EstadoContenido.VISIBLE,
				NombresCatalogo.EstadoContenido.REPORTADO,
				NombresCatalogo.EstadoContenido.EN_REVISION),
				CatalogoMensajes.Reglas.KUDO_NO_ADMITE_INTERACCION);
	}
}
