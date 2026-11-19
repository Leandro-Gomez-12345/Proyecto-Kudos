package co.edu.uco.kudos.controlador.peticion;

import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class JustificacionPeticion {

	private String justificacion;

	public JustificacionPeticion() {
		setJustificacion(UtilTexto.VACIA);
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = UtilTexto.quitarEspaciosEnBlanco(justificacion);
	}
}
