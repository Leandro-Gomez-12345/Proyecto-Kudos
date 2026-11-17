package co.edu.uco.kudos.transversal.excepciones;

import co.edu.uco.kudos.transversal.excepciones.enums.Capa;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public class UcoKudosExcepcion extends RuntimeException {

	private static final long serialVersionUID = 4120893471520963847L;

	private Capa capa;
	private String mensajeUsuario;
	private String mensajeTecnico;
	private Exception excepcionRaiz;

	protected UcoKudosExcepcion(Capa capa, String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
		super(UtilTexto.obtenerValorDefecto(mensajeTecnico, UtilTexto.obtenerValorDefecto(mensajeUsuario)),
				excepcionRaiz);
		setCapa(capa);
		setMensajeUsuario(mensajeUsuario);
		setMensajeTecnico(mensajeTecnico);
		setExcepcionRaiz(excepcionRaiz);
	}

	public Capa getCapa() {
		return capa;
	}

	public String getMensajeUsuario() {
		return mensajeUsuario;
	}

	public String getMensajeTecnico() {
		return mensajeTecnico;
	}

	public Exception getExcepcionRaiz() {
		return excepcionRaiz;
	}

	private void setCapa(Capa capa) {
		this.capa = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(capa, Capa.GENERAL);
	}

	private void setMensajeUsuario(String mensajeUsuario) {
		this.mensajeUsuario = UtilTexto.obtenerValorDefecto(mensajeUsuario);
	}

	private void setMensajeTecnico(String mensajeTecnico) {
		this.mensajeTecnico = UtilTexto.obtenerValorDefecto(mensajeTecnico, this.mensajeUsuario);
	}

	private void setExcepcionRaiz(Exception excepcionRaiz) {
		this.excepcionRaiz = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(excepcionRaiz,
				new Exception(this.mensajeTecnico));
	}
}
