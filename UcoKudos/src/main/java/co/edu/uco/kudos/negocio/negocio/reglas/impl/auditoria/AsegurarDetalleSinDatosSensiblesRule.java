package co.edu.uco.kudos.negocio.negocio.reglas.impl.auditoria;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.AuditoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.FormatosAtributos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarDetalleSinDatosSensiblesRule implements Rule<AuditoriaEntidad> {

	private static final Rule<AuditoriaEntidad> INSTANCIA = new AsegurarDetalleSinDatosSensiblesRule();

	private AsegurarDetalleSinDatosSensiblesRule() {

	}

	public static Rule<AuditoriaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(AuditoriaEntidad auditoria, DAOFactory daoFactory) {
		var detalle = auditoria.getDetalle();
		var contieneDatosSensibles = FormatosAtributos.CORREO_COMPLETO.matcher(detalle).find()
				|| FormatosAtributos.CELULAR_COMPLETO.matcher(detalle).find();
		ValidadorNegocio.asegurarCondicion(!contieneDatosSensibles, CatalogoMensajes.Reglas.AUDITORIA_NO_REGISTRADA);
	}
}
