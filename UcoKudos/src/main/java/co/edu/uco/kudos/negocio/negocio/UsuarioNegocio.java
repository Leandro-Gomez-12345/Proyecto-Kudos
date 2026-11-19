package co.edu.uco.kudos.negocio.negocio;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dominio.ProgramaAcademicoDominio;
import co.edu.uco.kudos.dominio.UsuarioDominio;

public interface UsuarioNegocio {

	UsuarioDominio registrarUsuario(UsuarioDominio datos, List<ProgramaAcademicoDominio> programas);

	void presentarNuevamenteRegistroUsuario(UUID id, UsuarioDominio datos, List<ProgramaAcademicoDominio> programas);

	UsuarioDominio registrarRector(UsuarioDominio datos);

	UsuarioDominio registrarAdministrador(UsuarioDominio datos);

	void confirmarCorreoUsuario(UUID id);

	void confirmarCelularUsuario(UUID id);

	void activarCuentaUsuario(UUID id);

	UsuarioDominio consultarInformacionPersonalUsuario(UUID id);

	List<UsuarioDominio> consultarUsuarios(UsuarioDominio filtro);

	UsuarioDominio cambiarNombreUsuario(UUID id, UsuarioDominio datos);

	void cambiarCorreoUsuario(UUID id, UsuarioDominio datos);

	void cambiarCelularUsuario(UUID id, UsuarioDominio datos);

	void suspenderCuentaUsuario(UUID id, String justificacion);

	void reactivarCuentaUsuario(UUID id);

	void desactivarCuentaUsuario(UUID id, String justificacion);

	void cambiarRolUsuario(UUID id, UsuarioDominio datos);

	List<UsuarioDominio> consultarUsuariosPendientesAprobacion(UsuarioDominio filtro);

	void aprobarUsuario(UUID id);

	void rechazarUsuario(UUID id, String justificacion);
}
