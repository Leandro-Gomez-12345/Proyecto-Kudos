package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.ProgramaAcademicoDominio;
import co.edu.uco.kudos.dominio.UsuarioDominio;
import co.edu.uco.kudos.entidad.CoordinacionFacultadEntidad;
import co.edu.uco.kudos.entidad.CoordinacionProgramaEntidad;
import co.edu.uco.kudos.entidad.ProgramaAcademicoEntidad;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.entidad.VinculacionAcademicaEntidad;
import co.edu.uco.kudos.negocio.negocio.UsuarioNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.ProgramaAcademicoEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.RolEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.UsuarioEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.Auditor;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.EstadosCuenta;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionfacultad.FinalizarCoordinacionFacultadAlSuspenderODesactivarRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.coordinacionprograma.FinalizarCoordinacionProgramaAlSuspenderODesactivarRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.programaacademico.AsegurarProgramaAcademicoExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.rol.AsegurarRolExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.ActivarCuentaCuandoCumplaCondicionesRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarAprobadorCorrespondeAlRolRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarAutoridadGestionaPersonaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarCelularSinVerificarRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarCelularUsuarioNoExistaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarCodigoCelularValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarCodigoCorreoValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarConsultaPendientesDeSuAmbitoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarCorreoSinVerificarRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarCorreoUsuarioNoExistaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarCuentaActivaParaSuspenderODesactivarRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarCuentaCumpleCondicionesActivacionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarCuentaNoDesactivadaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarCuentaPendienteAprobacionRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarCuentaRechazadaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarCuentaSuspendidaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarFormatoCelularUsuarioValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarFormatoCorreoUsuarioValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarFormatoJustificacionRechazoValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarFormatoNombreUsuarioValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarFormatoRegistroPorAdministradorValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarFormatoRegistroUsuarioValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarIdUsuarioUnicoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarNoActuarSobreCuentaPropiaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarPersonaSinCargoVigenteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarProgramasSegunRolRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarRolNuevoDistintoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarRolPermiteRegistroPropioRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUnicoCoordinadorAcademicoVigenteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUnicoRectorVigenteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioEsAdministradorRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioEsTitularOAutoridadRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioEsTitularRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsignarCuentaAprobadaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsignarCuentaPendienteNuevamenteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsignarCuentaRechazadaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsignarEstadoInicialRegistroPorAdministradorRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsignarEstadoInicialUsuarioRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.ExigirNuevaVerificacionCelularRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.ExigirNuevaVerificacionCorreoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica.DesvincularAlRechazarODesactivarRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.vinculacionacademica.VincularProgramasAlRegistrarseRule;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilBooleano;
import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class UsuarioNegocioImpl implements UsuarioNegocio {

	private final DAOFactory daoFactory;

	public UsuarioNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public UsuarioDominio registrarUsuario(UsuarioDominio datos, List<ProgramaAcademicoDominio> programas) {
		var usuario = UsuarioEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var programasElegidos = ProgramaAcademicoEntidadAssembler.obtenerInstancia().convertirAEntidad(programas);

		AsegurarFormatoRegistroUsuarioValidoRule.obtenerInstancia().ejecutar(usuario);
		AsegurarRolExisteRule.obtenerInstancia().ejecutar(usuario.getRol().getId(), daoFactory);
		AsegurarRolPermiteRegistroPropioRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		AsegurarProgramasSegunRolRule.obtenerInstancia().ejecutar(usuario, programasElegidos, daoFactory);
		asegurarQueLosProgramasExisten(programasElegidos);
		AsegurarCorreoUsuarioNoExistaRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		AsegurarCelularUsuarioNoExistaRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		AsegurarIdUsuarioUnicoRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		AsignarEstadoInicialUsuarioRule.obtenerInstancia().ejecutar(usuario, daoFactory);

		daoFactory.obtenerUsuarioDAO().crear(usuario);
		VincularProgramasAlRegistrarseRule.obtenerInstancia().ejecutar(usuario, programasElegidos,
				UtilFecha.obtenerFechaActual(), daoFactory);
		Auditor.registrarComo(daoFactory, usuario.getId(), EventoAuditable.REGISTRAR_USUARIO, usuario.getId(),
				detalleDelRol("Registro presentado con el rol ", usuario));
		return UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(usuario);
	}

	@Override
	public void presentarNuevamenteRegistroUsuario(UUID id, UsuarioDominio datos,
			List<ProgramaAcademicoDominio> programas) {
		var rolElegido = RolEntidadAssembler.obtenerInstancia().convertirAEntidad(datos.getRol());
		var programasElegidos = ProgramaAcademicoEntidadAssembler.obtenerInstancia().convertirAEntidad(programas);

		AsegurarUsuarioExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarCuentaRechazadaRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarRolExisteRule.obtenerInstancia().ejecutar(rolElegido.getId(), daoFactory);

		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(id);
		usuario.setRol(rolElegido);
		AsegurarRolPermiteRegistroPropioRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		AsegurarProgramasSegunRolRule.obtenerInstancia().ejecutar(usuario, programasElegidos, daoFactory);
		asegurarQueLosProgramasExisten(programasElegidos);
		AsignarCuentaPendienteNuevamenteRule.obtenerInstancia().ejecutar(usuario, daoFactory);

		daoFactory.obtenerUsuarioDAO().actualizar(id, usuario);
		VincularProgramasAlRegistrarseRule.obtenerInstancia().ejecutar(usuario, programasElegidos,
				UtilFecha.obtenerFechaActual(), daoFactory);
		Auditor.registrarComo(daoFactory, id, EventoAuditable.PRESENTAR_NUEVAMENTE_REGISTRO_USUARIO, id,
				detalleDelRol("Registro presentado de nuevo con el rol ", usuario));
	}

	@Override
	public UsuarioDominio registrarRector(UsuarioDominio datos) {
		var usuario = prepararRegistroPorAdministrador(datos, NombresCatalogo.Rol.RECTOR);
		AsegurarUnicoRectorVigenteRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		return finalizarRegistroPorAdministrador(usuario, EventoAuditable.REGISTRAR_RECTOR);
	}

	@Override
	public UsuarioDominio registrarAdministrador(UsuarioDominio datos) {
		var usuario = prepararRegistroPorAdministrador(datos, NombresCatalogo.Rol.ADMINISTRADOR);
		return finalizarRegistroPorAdministrador(usuario, EventoAuditable.REGISTRAR_ADMINISTRADOR);
	}

	@Override
	public void verificarCorreoUsuario(UUID id, String codigo) {
		AsegurarUsuarioExisteRule.obtenerInstancia().ejecutar(id, daoFactory);

		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(id);
		AsegurarCorreoSinVerificarRule.obtenerInstancia().ejecutar(usuario);
		AsegurarCodigoCorreoValidoRule.obtenerInstancia().ejecutar(usuario, codigo);
		usuario.setCorreoVerificado(UtilBooleano.SI);
		ActivarCuentaCuandoCumplaCondicionesRule.obtenerInstancia().ejecutar(usuario, daoFactory);

		daoFactory.obtenerUsuarioDAO().actualizar(id, usuario);
		Auditor.registrarComo(daoFactory, id, EventoAuditable.VERIFICAR_CORREO_USUARIO, id,
				Auditor.detalleCambio("Correo sin verificar", "Correo verificado"));
	}

	@Override
	public void verificarCelularUsuario(UUID id, String codigo) {
		AsegurarUsuarioExisteRule.obtenerInstancia().ejecutar(id, daoFactory);

		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(id);
		AsegurarCelularSinVerificarRule.obtenerInstancia().ejecutar(usuario);
		AsegurarCodigoCelularValidoRule.obtenerInstancia().ejecutar(usuario, codigo);
		usuario.setCelularVerificado(UtilBooleano.SI);
		ActivarCuentaCuandoCumplaCondicionesRule.obtenerInstancia().ejecutar(usuario, daoFactory);

		daoFactory.obtenerUsuarioDAO().actualizar(id, usuario);
		Auditor.registrarComo(daoFactory, id, EventoAuditable.VERIFICAR_CELULAR_USUARIO, id,
				Auditor.detalleCambio("Celular sin verificar", "Celular verificado"));
	}

	@Override
	public void activarCuentaUsuario(UUID id) {
		AsegurarUsuarioExisteRule.obtenerInstancia().ejecutar(id, daoFactory);

		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(id);
		AsegurarCuentaCumpleCondicionesActivacionRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		ActivarCuentaCuandoCumplaCondicionesRule.obtenerInstancia().ejecutar(usuario, daoFactory);

		daoFactory.obtenerUsuarioDAO().actualizar(id, usuario);
		Auditor.registrarComo(daoFactory, id, EventoAuditable.ACTIVAR_CUENTA_USUARIO, id,
				Auditor.detalleCambio(NombresCatalogo.EstadoCuenta.APROBADA, NombresCatalogo.EstadoCuenta.ACTIVA));
	}

	@Override
	public UsuarioDominio consultarInformacionPersonalUsuario(UUID id) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarUsuarioExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarUsuarioEsTitularOAutoridadRule.obtenerInstancia().ejecutar(id, daoFactory);

		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(id);
		return UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(usuario);
	}

	@Override
	public List<UsuarioDominio> consultarUsuarios(UsuarioDominio filtro) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);

		var filtroEntidad = UsuarioEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		var usuarios = daoFactory.obtenerUsuarioDAO().consultarPorFiltro(filtroEntidad);
		usuarios.forEach(this::ocultarDatosDeContacto);
		return UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(usuarios);
	}

	@Override
	public UsuarioDominio cambiarNombreUsuario(UUID id, UsuarioDominio datos) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarUsuarioExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarUsuarioEsTitularRule.obtenerInstancia().ejecutar(id);

		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(id);
		var nombreAnterior = nombreCompleto(usuario);
		usuario.setNombre(datos.getNombre());
		usuario.setApellido(datos.getApellido());
		AsegurarFormatoNombreUsuarioValidoRule.obtenerInstancia().ejecutar(usuario);

		daoFactory.obtenerUsuarioDAO().actualizar(id, usuario);
		Auditor.registrar(daoFactory, EventoAuditable.CAMBIAR_NOMBRE_USUARIO, id,
				Auditor.detalleCambio(nombreAnterior, nombreCompleto(usuario)));
		return UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(usuario);
	}

	@Override
	public void cambiarCorreoUsuario(UUID id, UsuarioDominio datos) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarUsuarioExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarUsuarioEsTitularRule.obtenerInstancia().ejecutar(id);

		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(id);
		var correoAnterior = usuario.getCorreoInstitucional();
		usuario.setCorreoInstitucional(datos.getCorreoInstitucional());
		AsegurarFormatoCorreoUsuarioValidoRule.obtenerInstancia().ejecutar(usuario);
		AsegurarCorreoUsuarioNoExistaRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		ExigirNuevaVerificacionCorreoRule.obtenerInstancia().ejecutar(usuario, daoFactory);

		daoFactory.obtenerUsuarioDAO().actualizar(id, usuario);
		Auditor.registrar(daoFactory, EventoAuditable.CAMBIAR_CORREO_USUARIO, id,
				Auditor.detalleCambio(UtilTexto.enmascararCorreo(correoAnterior),
						UtilTexto.enmascararCorreo(usuario.getCorreoInstitucional())));
	}

	@Override
	public void cambiarCelularUsuario(UUID id, UsuarioDominio datos) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarUsuarioExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarUsuarioEsTitularRule.obtenerInstancia().ejecutar(id);

		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(id);
		var celularAnterior = usuario.getCelular();
		usuario.setCelular(datos.getCelular());
		AsegurarFormatoCelularUsuarioValidoRule.obtenerInstancia().ejecutar(usuario);
		AsegurarCelularUsuarioNoExistaRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		ExigirNuevaVerificacionCelularRule.obtenerInstancia().ejecutar(usuario, daoFactory);

		daoFactory.obtenerUsuarioDAO().actualizar(id, usuario);
		Auditor.registrar(daoFactory, EventoAuditable.CAMBIAR_CELULAR_USUARIO, id,
				Auditor.detalleCambio(UtilTexto.enmascararCelular(celularAnterior),
						UtilTexto.enmascararCelular(usuario.getCelular())));
	}

	@Override
	public void suspenderCuentaUsuario(UUID id, String justificacion) {
		asegurarAutoridadSobreLaCuenta(id);
		AsegurarCuentaActivaParaSuspenderODesactivarRule.obtenerInstancia().ejecutar(id, daoFactory);

		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(id);
		EstadosCuenta.asignar(daoFactory, usuario, NombresCatalogo.EstadoCuenta.SUSPENDIDA);
		finalizarCoordinacionesDe(id);

		daoFactory.obtenerUsuarioDAO().actualizar(id, usuario);
		Auditor.registrar(daoFactory, EventoAuditable.SUSPENDER_CUENTA_USUARIO, id,
				detalleDelCambioDeEstado(NombresCatalogo.EstadoCuenta.ACTIVA, NombresCatalogo.EstadoCuenta.SUSPENDIDA,
						justificacion));
	}

	@Override
	public void reactivarCuentaUsuario(UUID id) {
		asegurarAutoridadSobreLaCuenta(id);
		AsegurarCuentaSuspendidaRule.obtenerInstancia().ejecutar(id, daoFactory);

		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(id);
		AsegurarUnicoRectorVigenteRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		AsegurarUnicoCoordinadorAcademicoVigenteRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		EstadosCuenta.asignar(daoFactory, usuario, NombresCatalogo.EstadoCuenta.ACTIVA);

		daoFactory.obtenerUsuarioDAO().actualizar(id, usuario);
		Auditor.registrar(daoFactory, EventoAuditable.REACTIVAR_CUENTA_USUARIO, id,
				Auditor.detalleCambio(NombresCatalogo.EstadoCuenta.SUSPENDIDA, NombresCatalogo.EstadoCuenta.ACTIVA));
	}

	@Override
	public void desactivarCuentaUsuario(UUID id, String justificacion) {
		asegurarAutoridadSobreLaCuenta(id);
		AsegurarCuentaNoDesactivadaRule.obtenerInstancia().ejecutar(id, daoFactory);

		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(id);
		var estadoAnterior = ConsultorCatalogos.nombreEstadoCuenta(daoFactory, usuario.getEstadoCuenta().getId());
		EstadosCuenta.asignar(daoFactory, usuario, NombresCatalogo.EstadoCuenta.DESACTIVADA);
		finalizarCoordinacionesDe(id);
		var vinculaciones = new VinculacionAcademicaEntidad();
		vinculaciones.setUsuario(usuario);
		DesvincularAlRechazarODesactivarRule.obtenerInstancia().ejecutar(vinculaciones, daoFactory);

		daoFactory.obtenerUsuarioDAO().actualizar(id, usuario);
		Auditor.registrar(daoFactory, EventoAuditable.DESACTIVAR_CUENTA_USUARIO, id,
				detalleDelCambioDeEstado(estadoAnterior, NombresCatalogo.EstadoCuenta.DESACTIVADA, justificacion));
	}

	@Override
	public void cambiarRolUsuario(UUID id, UsuarioDominio datos) {
		var rolNuevo = RolEntidadAssembler.obtenerInstancia().convertirAEntidad(datos.getRol());
		asegurarAutoridadSobreLaCuenta(id);
		AsegurarRolExisteRule.obtenerInstancia().ejecutar(rolNuevo.getId(), daoFactory);

		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(id);
		var rolAnterior = ConsultorCatalogos.nombreRol(daoFactory, usuario.getRol().getId());
		usuario.setRol(rolNuevo);
		AsegurarRolNuevoDistintoRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		AsegurarPersonaSinCargoVigenteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarUnicoRectorVigenteRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		AsegurarUnicoCoordinadorAcademicoVigenteRule.obtenerInstancia().ejecutar(usuario, daoFactory);

		daoFactory.obtenerUsuarioDAO().actualizar(id, usuario);
		Auditor.registrar(daoFactory, EventoAuditable.CAMBIAR_ROL_USUARIO, id,
				Auditor.detalleCambio(rolAnterior, ConsultorCatalogos.nombreRol(daoFactory, rolNuevo.getId())));
	}

	@Override
	public List<UsuarioDominio> consultarUsuariosPendientesAprobacion(UsuarioDominio filtro) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);

		var filtroEntidad = UsuarioEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		EstadosCuenta.asignar(daoFactory, filtroEntidad, NombresCatalogo.EstadoCuenta.PENDIENTE_APROBACION);
		var pendientes = new ArrayList<>(daoFactory.obtenerUsuarioDAO().consultarPorFiltro(filtroEntidad));
		AsegurarConsultaPendientesDeSuAmbitoRule.obtenerInstancia().ejecutar(pendientes, daoFactory);
		return UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(pendientes);
	}

	@Override
	public void aprobarUsuario(UUID id) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarUsuarioExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarCuentaPendienteAprobacionRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarAprobadorCorrespondeAlRolRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarNoActuarSobreCuentaPropiaRule.obtenerInstancia().ejecutar(id);

		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(id);
		AsegurarUnicoRectorVigenteRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		AsegurarUnicoCoordinadorAcademicoVigenteRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		AsignarCuentaAprobadaRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		ActivarCuentaCuandoCumplaCondicionesRule.obtenerInstancia().ejecutar(usuario, daoFactory);

		daoFactory.obtenerUsuarioDAO().actualizar(id, usuario);
		Auditor.registrar(daoFactory, EventoAuditable.APROBAR_USUARIO, id,
				Auditor.detalleCambio(NombresCatalogo.EstadoCuenta.PENDIENTE_APROBACION,
						NombresCatalogo.EstadoCuenta.APROBADA));
	}

	@Override
	public void rechazarUsuario(UUID id, String justificacion) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarUsuarioExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarCuentaPendienteAprobacionRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarAprobadorCorrespondeAlRolRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarNoActuarSobreCuentaPropiaRule.obtenerInstancia().ejecutar(id);
		AsegurarFormatoJustificacionRechazoValidoRule.obtenerInstancia().ejecutar(justificacion);

		var usuario = daoFactory.obtenerUsuarioDAO().consultarPorId(id);
		AsignarCuentaRechazadaRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		var vinculaciones = new VinculacionAcademicaEntidad();
		vinculaciones.setUsuario(usuario);
		DesvincularAlRechazarODesactivarRule.obtenerInstancia().ejecutar(vinculaciones, daoFactory);

		daoFactory.obtenerUsuarioDAO().actualizar(id, usuario);
		Auditor.registrar(daoFactory, EventoAuditable.RECHAZAR_USUARIO, id,
				detalleDelCambioDeEstado(NombresCatalogo.EstadoCuenta.PENDIENTE_APROBACION,
						NombresCatalogo.EstadoCuenta.RECHAZADA, justificacion));
	}

	private void asegurarAutoridadSobreLaCuenta(UUID idPersona) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);
		AsegurarUsuarioExisteRule.obtenerInstancia().ejecutar(idPersona, daoFactory);
		AsegurarNoActuarSobreCuentaPropiaRule.obtenerInstancia().ejecutar(idPersona);
		AsegurarAutoridadGestionaPersonaRule.obtenerInstancia().ejecutar(idPersona, daoFactory);
	}

	private void finalizarCoordinacionesDe(UUID idPersona) {
		var coordinacionFacultad = new CoordinacionFacultadEntidad();
		coordinacionFacultad.getUsuario().setId(idPersona);
		FinalizarCoordinacionFacultadAlSuspenderODesactivarRule.obtenerInstancia().ejecutar(coordinacionFacultad,
				daoFactory);

		var coordinacionPrograma = new CoordinacionProgramaEntidad();
		coordinacionPrograma.getUsuario().setId(idPersona);
		FinalizarCoordinacionProgramaAlSuspenderODesactivarRule.obtenerInstancia().ejecutar(coordinacionPrograma,
				daoFactory);
	}

	private void asegurarQueLosProgramasExisten(List<ProgramaAcademicoEntidad> programas) {
		for (var programa : programas) {
			AsegurarProgramaAcademicoExisteRule.obtenerInstancia().ejecutar(programa.getId(), daoFactory);
		}
	}

	private UsuarioEntidad prepararRegistroPorAdministrador(UsuarioDominio datos, String nombreRol) {
		var usuario = UsuarioEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();
		usuario.setRol(ConsultorCatalogos.obtenerRol(daoFactory, nombreRol));

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioEsAdministradorRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarFormatoRegistroPorAdministradorValidoRule.obtenerInstancia().ejecutar(usuario);
		AsegurarCorreoUsuarioNoExistaRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		AsegurarCelularUsuarioNoExistaRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		return usuario;
	}

	private UsuarioDominio finalizarRegistroPorAdministrador(UsuarioEntidad usuario, EventoAuditable evento) {
		AsegurarIdUsuarioUnicoRule.obtenerInstancia().ejecutar(usuario, daoFactory);
		AsignarEstadoInicialRegistroPorAdministradorRule.obtenerInstancia().ejecutar(usuario, daoFactory);

		daoFactory.obtenerUsuarioDAO().crear(usuario);
		Auditor.registrar(daoFactory, evento, usuario.getId(), detalleDelRol("Cuenta creada con el rol ", usuario));
		return UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(usuario);
	}

	private void ocultarDatosDeContacto(UsuarioEntidad usuario) {
		usuario.setCorreoInstitucional(UtilTexto.VACIA);
		usuario.setCelular(UtilTexto.VACIA);
	}

	private String detalleDelRol(String prefijo, UsuarioEntidad usuario) {
		return UtilTexto.concatenar(prefijo, ConsultorCatalogos.nombreRol(daoFactory, usuario.getRol().getId()));
	}

	private static String detalleDelCambioDeEstado(String estadoAnterior, String estadoNuevo, String justificacion) {
		return UtilTexto.concatenar(Auditor.detalleCambio(estadoAnterior, estadoNuevo), ". ", justificacion);
	}

	private static String nombreCompleto(UsuarioEntidad usuario) {
		return UtilTexto.concatenar(usuario.getNombre(), " ", usuario.getApellido());
	}
}
