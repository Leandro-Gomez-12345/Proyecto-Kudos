package co.edu.uco.kudos.negocio.negocio.reglas.impl.kudo;

import co.edu.uco.kudos.entidad.KudoEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorAtributos;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;

public final class AsegurarFormatoKudoValidoRule implements Rule<KudoEntidad> {

	private static final Rule<KudoEntidad> INSTANCIA = new AsegurarFormatoKudoValidoRule();

	private static final int LONGITUD_MINIMA_CONTENIDO = 1;

	private AsegurarFormatoKudoValidoRule() {

	}

	public static Rule<KudoEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(KudoEntidad... datos) {
		var kudo = datos[0];

		ValidadorAtributos.asegurarReferencia(kudo.getDestinatario().getId(),
				CatalogoMensajes.Reglas.KUDO_DESTINATARIO_OBLIGATORIO);
		ValidadorAtributos.asegurarReferencia(kudo.getCategoria().getId(),
				CatalogoMensajes.Reglas.KUDO_CATEGORIA_OBLIGATORIA);
		ValidadorAtributos.asegurarTextoSinLimite(kudo.getContenido(), LONGITUD_MINIMA_CONTENIDO,
				CatalogoMensajes.Reglas.KUDO_CONTENIDO_OBLIGATORIO);
	}
}
