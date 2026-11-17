package co.edu.uco.kudos.transversal.excepciones;

import co.edu.uco.kudos.transversal.excepciones.enums.Capa;

public class UcoKudosControladorExcepcion extends UcoKudosExcepcion {

	private static final long serialVersionUID = -2874190650128473019L;

	private UcoKudosControladorExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
		super(Capa.CONTROLADOR, mensajeUsuario, mensajeTecnico, excepcionRaiz);
	}

	public static UcoKudosExcepcion crear(String mensajeUsuario) {
		return new UcoKudosControladorExcepcion(mensajeUsuario, mensajeUsuario, new Exception(mensajeUsuario));
	}

	public static UcoKudosExcepcion crear(String mensajeUsuario, String mensajeTecnico) {
		return new UcoKudosControladorExcepcion(mensajeUsuario, mensajeTecnico, new Exception(mensajeTecnico));
	}

	public static UcoKudosExcepcion crear(String mensajeUsuario, String mensajeTecnico,
			Exception excepcionRaiz) {
		return new UcoKudosControladorExcepcion(mensajeUsuario, mensajeTecnico, excepcionRaiz);
	}
}
