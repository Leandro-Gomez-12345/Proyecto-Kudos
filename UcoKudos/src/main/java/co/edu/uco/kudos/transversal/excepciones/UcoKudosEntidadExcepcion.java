package co.edu.uco.kudos.transversal.excepciones;

import co.edu.uco.kudos.transversal.excepciones.enums.Capa;

public class UcoKudosEntidadExcepcion extends UcoKudosExcepcion {

	private static final long serialVersionUID = -1938475610293847562L;

	private UcoKudosEntidadExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
		super(Capa.ENTIDAD, mensajeUsuario, mensajeTecnico, excepcionRaiz);
	}

	public static UcoKudosExcepcion crear(String mensajeUsuario) {
		return new UcoKudosEntidadExcepcion(mensajeUsuario, mensajeUsuario, new Exception(mensajeUsuario));
	}

	public static UcoKudosExcepcion crear(String mensajeUsuario, String mensajeTecnico) {
		return new UcoKudosEntidadExcepcion(mensajeUsuario, mensajeTecnico, new Exception(mensajeTecnico));
	}

	public static UcoKudosExcepcion crear(String mensajeUsuario, String mensajeTecnico,
			Exception excepcionRaiz) {
		return new UcoKudosEntidadExcepcion(mensajeUsuario, mensajeTecnico, excepcionRaiz);
	}
}
