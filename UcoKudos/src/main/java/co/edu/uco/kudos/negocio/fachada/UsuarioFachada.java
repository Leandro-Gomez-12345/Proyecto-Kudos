package co.edu.uco.kudos.negocio.fachada;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dto.ProgramaAcademicoDTO;
import co.edu.uco.kudos.dto.UsuarioDTO;

public interface UsuarioFachada {

	UsuarioDTO registrarUsuario(UsuarioDTO datos, List<ProgramaAcademicoDTO> programas);

	void presentarNuevamenteRegistroUsuario(UUID id, UsuarioDTO datos, List<ProgramaAcademicoDTO> programas);

	UsuarioDTO registrarRector(UsuarioDTO datos);

	UsuarioDTO registrarAdministrador(UsuarioDTO datos);

	void verificarCorreoUsuario(UUID id, String codigo);

	void verificarCelularUsuario(UUID id, String codigo);

	UsuarioDTO consultarInformacionPersonalUsuario(UUID id);

	List<UsuarioDTO> consultarUsuarios(UsuarioDTO filtro);

	UsuarioDTO cambiarNombreUsuario(UUID id, UsuarioDTO datos);

	void cambiarCorreoUsuario(UUID id, UsuarioDTO datos);

	void cambiarCelularUsuario(UUID id, UsuarioDTO datos);

	void suspenderCuentaUsuario(UUID id, String justificacion);

	void reactivarCuentaUsuario(UUID id);

	void desactivarCuentaUsuario(UUID id, String justificacion);

	void cambiarRolUsuario(UUID id, UsuarioDTO datos);

	List<UsuarioDTO> consultarUsuariosPendientesAprobacion(UsuarioDTO filtro);

	void aprobarUsuario(UUID id);

	void rechazarUsuario(UUID id, String justificacion);
}
