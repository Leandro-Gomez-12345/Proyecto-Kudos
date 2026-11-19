package co.edu.uco.kudos.dto;

import co.edu.uco.kudos.transversal.utilitarios.UtilBooleano;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class SesionDTO {

	private boolean registrado;
	private UsuarioDTO usuario;

	public SesionDTO() {
		setRegistrado(UtilBooleano.NO);
		setUsuario(new UsuarioDTO());
	}

	public boolean isRegistrado() {
		return registrado;
	}

	public void setRegistrado(boolean registrado) {
		this.registrado = registrado;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = UtilObjeto.esNulo(usuario) ? new UsuarioDTO() : usuario;
	}
}
