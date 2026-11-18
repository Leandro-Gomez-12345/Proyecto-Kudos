package co.edu.uco.kudos.negocio.negocio.reglas.impl.comun;

import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.AuditoriaEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.auditoria.RegistrarEventoEnAuditoriaRule;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class Auditor {

	public static final int LONGITUD_MAXIMA_DETALLE = 200;

	private static final String SEPARADOR_CAMBIO = " → ";

	private Auditor() {

	}

	public static void registrar(DAOFactory daoFactory, EventoAuditable evento, UUID idRegistro, String detalle) {
		registrarComo(daoFactory, ContextoEjecucion.obtenerIdEjecutor(), evento, idRegistro, detalle);
	}

	public static void registrarComo(DAOFactory daoFactory, UUID idUsuario, EventoAuditable evento,
			UUID idRegistro, String detalle) {
		var auditoria = new AuditoriaEntidad();
		auditoria.getUsuario().setId(idUsuario);
		auditoria.setAccion(evento.getAccion());
		auditoria.setObjetoAfectado(evento.getObjetoAfectado());
		auditoria.setRegistroAfectado(idRegistro);
		auditoria.setDetalle(limitar(detalle));
		RegistrarEventoEnAuditoriaRule.obtenerInstancia().ejecutar(auditoria, daoFactory);
	}

	public static String detalleCambio(String anterior, String nuevo) {
		return UtilTexto.concatenar(anterior, SEPARADOR_CAMBIO, nuevo);
	}

	private static String limitar(String detalle) {
		var texto = UtilTexto.quitarEspaciosEnBlanco(detalle);
		return texto.length() > LONGITUD_MAXIMA_DETALLE ? texto.substring(0, LONGITUD_MAXIMA_DETALLE) : texto;
	}
}
