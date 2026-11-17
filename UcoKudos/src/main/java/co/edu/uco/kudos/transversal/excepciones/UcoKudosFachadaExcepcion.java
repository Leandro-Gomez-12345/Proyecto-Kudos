package co.edu.uco.kudos.transversal.excepciones;

import co.edu.uco.kudos.transversal.excepciones.enums.Capa;

public class UcoKudosFachadaExcepcion extends UcoKudosExcepcion {

	private static final long serialVersionUID = 3750183946120573894L;

	private UcoKudosFachadaExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
		super(Capa.FACHADA, mensajeUsuario, mensajeTecnico, excepcionRaiz);
	}

	public static UcoKudosExcepcion crear(String mensajeUsuario) {
		return new UcoKudosFachadaExcepcion(mensajeUsuario, mensajeUsuario, new Exception(mensajeUsuario));
	}

	public static UcoKudosExcepcion crear(String mensajeUsuario, String mensajeTecnico) {
		return new UcoKudosFachadaExcepcion(mensajeUsuario, mensajeTecnico, new Exception(mensajeTecnico));
	}

	public static UcoKudosExcepcion crear(String mensajeUsuario, String mensajeTecnico,
			Exception excepcionRaiz) {
		return new UcoKudosFachadaExcepcion(mensajeUsuario, mensajeTecnico, excepcionRaiz);
	}
}
