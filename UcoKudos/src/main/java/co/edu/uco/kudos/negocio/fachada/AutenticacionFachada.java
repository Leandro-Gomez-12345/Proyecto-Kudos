package co.edu.uco.kudos.negocio.fachada;

import co.edu.uco.kudos.dto.UsuarioDTO;

public interface AutenticacionFachada {

	UsuarioDTO consultarUsuarioPorCorreo(String correo);
}
