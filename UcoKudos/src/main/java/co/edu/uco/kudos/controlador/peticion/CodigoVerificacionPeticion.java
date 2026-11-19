package co.edu.uco.kudos.controlador.peticion;

import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class CodigoVerificacionPeticion {

	private String codigo;

	public CodigoVerificacionPeticion() {
		setCodigo(UtilTexto.VACIA);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = UtilTexto.quitarEspaciosEnBlanco(codigo);
	}
}
