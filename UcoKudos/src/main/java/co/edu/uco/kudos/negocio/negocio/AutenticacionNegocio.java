package co.edu.uco.kudos.negocio.negocio;

import co.edu.uco.kudos.dominio.UsuarioDominio;

public interface AutenticacionNegocio {

	UsuarioDominio iniciarSesion(String correo);

	UsuarioDominio consultarUsuarioPorCorreo(String correo);
}
