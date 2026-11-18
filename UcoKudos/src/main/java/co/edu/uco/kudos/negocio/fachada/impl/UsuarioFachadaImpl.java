package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.ProgramaAcademicoDTO;
import co.edu.uco.kudos.dto.UsuarioDTO;
import co.edu.uco.kudos.negocio.fachada.UsuarioFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.ProgramaAcademicoDTOAssembler;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.UsuarioDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.UsuarioNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class UsuarioFachadaImpl implements UsuarioFachada {

	@Override
	public UsuarioDTO registrarUsuario(UsuarioDTO datos, List<ProgramaAcademicoDTO> programas) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return registrarUsuarioConfirmandoTransaccion(daoFactory, datos, programas);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private UsuarioDTO registrarUsuarioConfirmandoTransaccion(DAOFactory daoFactory, UsuarioDTO datos,
			List<ProgramaAcademicoDTO> programas) {
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			var datosDominio = UsuarioDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var programasDominio = ProgramaAcademicoDTOAssembler.obtenerInstancia().convertirADominio(programas);
			var resultado = usuarioNegocio.registrarUsuario(datosDominio, programasDominio);
			daoFactory.confirmarTransaccion();
			return UsuarioDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_REGISTRANDO_USUARIO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void presentarNuevamenteRegistroUsuario(UUID id, UsuarioDTO datos, List<ProgramaAcademicoDTO> programas) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			presentarNuevamenteRegistroUsuarioConfirmandoTransaccion(daoFactory, id, datos, programas);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void presentarNuevamenteRegistroUsuarioConfirmandoTransaccion(DAOFactory daoFactory, UUID id,
			UsuarioDTO datos, List<ProgramaAcademicoDTO> programas) {
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			var datosDominio = UsuarioDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var programasDominio = ProgramaAcademicoDTOAssembler.obtenerInstancia().convertirADominio(programas);
			usuarioNegocio.presentarNuevamenteRegistroUsuario(id, datosDominio, programasDominio);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_PRESENTANDO_NUEVAMENTE_REGISTRO_USUARIO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public UsuarioDTO registrarRector(UsuarioDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return registrarRectorConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private UsuarioDTO registrarRectorConfirmandoTransaccion(DAOFactory daoFactory, UsuarioDTO datos) {
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			var datosDominio = UsuarioDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = usuarioNegocio.registrarRector(datosDominio);
			daoFactory.confirmarTransaccion();
			return UsuarioDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_REGISTRANDO_RECTOR;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public UsuarioDTO registrarAdministrador(UsuarioDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return registrarAdministradorConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private UsuarioDTO registrarAdministradorConfirmandoTransaccion(DAOFactory daoFactory, UsuarioDTO datos) {
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			var datosDominio = UsuarioDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = usuarioNegocio.registrarAdministrador(datosDominio);
			daoFactory.confirmarTransaccion();
			return UsuarioDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_REGISTRANDO_ADMINISTRADOR;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void verificarCorreoUsuario(UUID id, String codigo) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			verificarCorreoUsuarioConfirmandoTransaccion(daoFactory, id, codigo);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void verificarCorreoUsuarioConfirmandoTransaccion(DAOFactory daoFactory, UUID id, String codigo) {
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			usuarioNegocio.verificarCorreoUsuario(id, codigo);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_VERIFICANDO_CORREO_USUARIO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void verificarCelularUsuario(UUID id, String codigo) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			verificarCelularUsuarioConfirmandoTransaccion(daoFactory, id, codigo);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void verificarCelularUsuarioConfirmandoTransaccion(DAOFactory daoFactory, UUID id, String codigo) {
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			usuarioNegocio.verificarCelularUsuario(id, codigo);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_VERIFICANDO_CELULAR_USUARIO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public UsuarioDTO consultarInformacionPersonalUsuario(UUID id) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			var resultado = usuarioNegocio.consultarInformacionPersonalUsuario(id);
			return UsuarioDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_CONSULTANDO_INFORMACION_PERSONAL_USUARIO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	@Override
	public List<UsuarioDTO> consultarUsuarios(UsuarioDTO filtro) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			var filtroDominio = UsuarioDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = usuarioNegocio.consultarUsuarios(filtroDominio);
			return UsuarioDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_CONSULTANDO_USUARIOS;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	@Override
	public UsuarioDTO cambiarNombreUsuario(UUID id, UsuarioDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return cambiarNombreUsuarioConfirmandoTransaccion(daoFactory, id, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private UsuarioDTO cambiarNombreUsuarioConfirmandoTransaccion(DAOFactory daoFactory, UUID id, UsuarioDTO datos) {
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			var datosDominio = UsuarioDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = usuarioNegocio.cambiarNombreUsuario(id, datosDominio);
			daoFactory.confirmarTransaccion();
			return UsuarioDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_CAMBIANDO_NOMBRE_USUARIO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void cambiarCorreoUsuario(UUID id, UsuarioDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			cambiarCorreoUsuarioConfirmandoTransaccion(daoFactory, id, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void cambiarCorreoUsuarioConfirmandoTransaccion(DAOFactory daoFactory, UUID id, UsuarioDTO datos) {
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			var datosDominio = UsuarioDTOAssembler.obtenerInstancia().convertirADominio(datos);
			usuarioNegocio.cambiarCorreoUsuario(id, datosDominio);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_CAMBIANDO_CORREO_USUARIO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void cambiarCelularUsuario(UUID id, UsuarioDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			cambiarCelularUsuarioConfirmandoTransaccion(daoFactory, id, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void cambiarCelularUsuarioConfirmandoTransaccion(DAOFactory daoFactory, UUID id, UsuarioDTO datos) {
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			var datosDominio = UsuarioDTOAssembler.obtenerInstancia().convertirADominio(datos);
			usuarioNegocio.cambiarCelularUsuario(id, datosDominio);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_CAMBIANDO_CELULAR_USUARIO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void suspenderCuentaUsuario(UUID id, String justificacion) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			suspenderCuentaUsuarioConfirmandoTransaccion(daoFactory, id, justificacion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void suspenderCuentaUsuarioConfirmandoTransaccion(DAOFactory daoFactory, UUID id, String justificacion) {
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			usuarioNegocio.suspenderCuentaUsuario(id, justificacion);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_SUSPENDIENDO_CUENTA_USUARIO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void reactivarCuentaUsuario(UUID id) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			reactivarCuentaUsuarioConfirmandoTransaccion(daoFactory, id);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void reactivarCuentaUsuarioConfirmandoTransaccion(DAOFactory daoFactory, UUID id) {
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			usuarioNegocio.reactivarCuentaUsuario(id);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_REACTIVANDO_CUENTA_USUARIO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void desactivarCuentaUsuario(UUID id, String justificacion) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			desactivarCuentaUsuarioConfirmandoTransaccion(daoFactory, id, justificacion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void desactivarCuentaUsuarioConfirmandoTransaccion(DAOFactory daoFactory, UUID id, String justificacion) {
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			usuarioNegocio.desactivarCuentaUsuario(id, justificacion);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_DESACTIVANDO_CUENTA_USUARIO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void cambiarRolUsuario(UUID id, UsuarioDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			cambiarRolUsuarioConfirmandoTransaccion(daoFactory, id, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void cambiarRolUsuarioConfirmandoTransaccion(DAOFactory daoFactory, UUID id, UsuarioDTO datos) {
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			var datosDominio = UsuarioDTOAssembler.obtenerInstancia().convertirADominio(datos);
			usuarioNegocio.cambiarRolUsuario(id, datosDominio);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_CAMBIANDO_ROL_USUARIO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public List<UsuarioDTO> consultarUsuariosPendientesAprobacion(UsuarioDTO filtro) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			var filtroDominio = UsuarioDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = usuarioNegocio.consultarUsuariosPendientesAprobacion(filtroDominio);
			return UsuarioDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario =
					CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_CONSULTANDO_USUARIOS_PENDIENTES_APROBACION;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	@Override
	public void aprobarUsuario(UUID id) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			aprobarUsuarioConfirmandoTransaccion(daoFactory, id);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void aprobarUsuarioConfirmandoTransaccion(DAOFactory daoFactory, UUID id) {
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			usuarioNegocio.aprobarUsuario(id);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_APROBANDO_USUARIO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void rechazarUsuario(UUID id, String justificacion) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			rechazarUsuarioConfirmandoTransaccion(daoFactory, id, justificacion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void rechazarUsuarioConfirmandoTransaccion(DAOFactory daoFactory, UUID id, String justificacion) {
		try {
			var usuarioNegocio = new UsuarioNegocioImpl(daoFactory);
			usuarioNegocio.rechazarUsuario(id, justificacion);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.UsuarioFachada.USUARIO_ERROR_RECHAZANDO_USUARIO;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}
}
