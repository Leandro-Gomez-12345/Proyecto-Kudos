package co.edu.uco.kudos.negocio.negocio;

import co.edu.uco.kudos.dominio.UsuarioDominio;

public interface AutenticacionNegocio {

	UsuarioDominio consultarUsuarioPorCorreo(String correo);
}
