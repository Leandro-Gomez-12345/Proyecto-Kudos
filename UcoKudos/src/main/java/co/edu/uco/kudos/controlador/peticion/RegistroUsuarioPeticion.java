package co.edu.uco.kudos.controlador.peticion;

import java.util.List;

import co.edu.uco.kudos.dto.ProgramaAcademicoDTO;
import co.edu.uco.kudos.dto.UsuarioDTO;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;

public final class RegistroUsuarioPeticion {

	private UsuarioDTO usuario;
	private List<ProgramaAcademicoDTO> programas;

	public RegistroUsuarioPeticion() {
		setUsuario(new UsuarioDTO());
		setProgramas(List.of());
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = UtilObjeto.esNulo(usuario) ? new UsuarioDTO() : usuario;
	}

	public List<ProgramaAcademicoDTO> getProgramas() {
		return programas;
	}

	public void setProgramas(List<ProgramaAcademicoDTO> programas) {
		this.programas = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(programas, List.of());
	}
}
