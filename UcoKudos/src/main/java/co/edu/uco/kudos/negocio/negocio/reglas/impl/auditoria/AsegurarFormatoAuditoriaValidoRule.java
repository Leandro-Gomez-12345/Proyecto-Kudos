package co.edu.uco.kudos.negocio.negocio.reglas.impl.auditoria;

import co.edu.uco.kudos.entidad.AuditoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.Auditor;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoAuditoriaValidoRule implements Rule<AuditoriaEntidad> {

	private static final Rule<AuditoriaEntidad> INSTANCIA = new AsegurarFormatoAuditoriaValidoRule();

	private static final int LONGITUD_MINIMA_ACCION = 5;
	private static final int LONGITUD_MAXIMA_ACCION = 100;
	private static final int LONGITUD_MINIMA_OBJETO = 3;
	private static final int LONGITUD_MAXIMA_OBJETO = 30;
	private static final int LONGITUD_MINIMA_DETALLE = 5;

	private AsegurarFormatoAuditoriaValidoRule() {

	}

	public static Rule<AuditoriaEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(AuditoriaEntidad... datos) {
		var auditoria = datos[0];

		var mensajeUsuario = CatalogoMensajes.Reglas.AUDITORIA_NO_REGISTRADA;
		ValidadorAtributos.asegurarReferencia(auditoria.getUsuario().getId(), mensajeUsuario);
		ValidadorAtributos.asegurarTexto(auditoria.getAccion(), LONGITUD_MINIMA_ACCION, LONGITUD_MAXIMA_ACCION,
				mensajeUsuario);
		ValidadorAtributos.asegurarTexto(auditoria.getObjetoAfectado(), LONGITUD_MINIMA_OBJETO,
				LONGITUD_MAXIMA_OBJETO, mensajeUsuario);
		ValidadorAtributos.asegurarReferencia(auditoria.getRegistroAfectado(), mensajeUsuario);
		ValidadorAtributos.asegurarTexto(auditoria.getDetalle(), LONGITUD_MINIMA_DETALLE,
				Auditor.LONGITUD_MAXIMA_DETALLE, mensajeUsuario);
	}
}
