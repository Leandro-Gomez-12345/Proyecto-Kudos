package co.edu.uco.kudos.controlador.peticion;

import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class IngresoPeticion {

	private String correoInstitucional;

	public IngresoPeticion() {
		setCorreoInstitucional(UtilTexto.VACIA);
	}

	public String getCorreoInstitucional() {
		return correoInstitucional;
	}

	public void setCorreoInstitucional(String correoInstitucional) {
		this.correoInstitucional = UtilTexto.quitarEspaciosEnBlanco(correoInstitucional);
	}
}
