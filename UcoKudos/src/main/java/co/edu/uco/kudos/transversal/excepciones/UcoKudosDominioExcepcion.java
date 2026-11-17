package co.edu.uco.kudos.transversal.excepciones;

import co.edu.uco.kudos.transversal.excepciones.enums.Capa;

public class UcoKudosDominioExcepcion extends UcoKudosExcepcion {

	private static final long serialVersionUID = 8240173659284017356L;

	private UcoKudosDominioExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
		super(Capa.DOMINIO, mensajeUsuario, mensajeTecnico, excepcionRaiz);
	}

	public static UcoKudosExcepcion crear(String mensajeUsuario) {
		return new UcoKudosDominioExcepcion(mensajeUsuario, mensajeUsuario, new Exception(mensajeUsuario));
	}

	public static UcoKudosExcepcion crear(String mensajeUsuario, String mensajeTecnico) {
		return new UcoKudosDominioExcepcion(mensajeUsuario, mensajeTecnico, new Exception(mensajeTecnico));
	}

	public static UcoKudosExcepcion crear(String mensajeUsuario, String mensajeTecnico,
			Exception excepcionRaiz) {
		return new UcoKudosDominioExcepcion(mensajeUsuario, mensajeTecnico, excepcionRaiz);
	}
}
