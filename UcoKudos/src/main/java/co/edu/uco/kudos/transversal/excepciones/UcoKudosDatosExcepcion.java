package co.edu.uco.kudos.transversal.excepciones;

import co.edu.uco.kudos.transversal.excepciones.enums.Capa;

public class UcoKudosDatosExcepcion extends UcoKudosExcepcion {

	private static final long serialVersionUID = -6760162966793838401L;

	private UcoKudosDatosExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
		super(Capa.DATOS, mensajeUsuario, mensajeTecnico, excepcionRaiz);
	}

	public static UcoKudosExcepcion crear(String mensajeUsuario) {
		return new UcoKudosDatosExcepcion(mensajeUsuario, mensajeUsuario, new Exception(mensajeUsuario));
	}

	public static UcoKudosExcepcion crear(String mensajeUsuario, String mensajeTecnico) {
		return new UcoKudosDatosExcepcion(mensajeUsuario, mensajeTecnico, new Exception(mensajeTecnico));
	}

	public static UcoKudosExcepcion crear(String mensajeUsuario, String mensajeTecnico,
			Exception excepcionRaiz) {
		return new UcoKudosDatosExcepcion(mensajeUsuario, mensajeTecnico, excepcionRaiz);
	}
}
