package co.edu.uco.kudos.negocio.fachada;

import co.edu.uco.kudos.dto.UsuarioDTO;

public interface AutenticacionFachada {

	UsuarioDTO iniciarSesion(String correo);

	UsuarioDTO consultarUsuarioPorCorreo(String correo);
}
