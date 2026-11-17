package co.edu.uco.kudos.entidad;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilBooleano;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class UsuarioEntidad {

	private UUID id;
	private String nombre;
	private String apellido;
	private String correoInstitucional;
	private String celular;
	private boolean correoVerificado;
	private boolean celularVerificado;
	private RolEntidad rol;
	private EstadoCuentaEntidad estadoCuenta;
	private LocalDateTime fechaRegistro;

	public UsuarioEntidad() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setNombre(UtilTexto.VACIA);
		setApellido(UtilTexto.VACIA);
		setCorreoInstitucional(UtilTexto.VACIA);
		setCelular(UtilTexto.VACIA);
		setCorreoVerificado(UtilBooleano.NO);
		setCelularVerificado(UtilBooleano.NO);
		setRol(new RolEntidad());
		setEstadoCuenta(new EstadoCuentaEntidad());
		setFechaRegistro(UtilFechaHora.FECHA_HORA_DEFECTO);
	}

	public UsuarioEntidad(UUID id, String nombre, String apellido, String correoInstitucional, String celular,
			Boolean correoVerificado, Boolean celularVerificado, RolEntidad rol, EstadoCuentaEntidad estadoCuenta,
			LocalDateTime fechaRegistro) {
		setId(id);
		setNombre(nombre);
		setApellido(apellido);
		setCorreoInstitucional(correoInstitucional);
		setCelular(celular);
		setCorreoVerificado(correoVerificado);
		setCelularVerificado(celularVerificado);
		setRol(rol);
		setEstadoCuenta(estadoCuenta);
		setFechaRegistro(fechaRegistro);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = UtilTexto.quitarEspaciosEnBlanco(nombre);
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = UtilTexto.quitarEspaciosEnBlanco(apellido);
	}

	public String getCorreoInstitucional() {
		return correoInstitucional;
	}

	public void setCorreoInstitucional(String correoInstitucional) {
		this.correoInstitucional = UtilTexto.quitarEspaciosEnBlanco(correoInstitucional);
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = UtilTexto.quitarEspaciosEnBlanco(celular);
	}

	public boolean isCorreoVerificado() {
		return correoVerificado;
	}

	public void setCorreoVerificado(Boolean correoVerificado) {
		this.correoVerificado = UtilBooleano.obtenerValorDefecto(correoVerificado, UtilBooleano.NO);
	}

	public boolean isCelularVerificado() {
		return celularVerificado;
	}

	public void setCelularVerificado(Boolean celularVerificado) {
		this.celularVerificado = UtilBooleano.obtenerValorDefecto(celularVerificado, UtilBooleano.NO);
	}

	public RolEntidad getRol() {
		return rol;
	}

	public void setRol(RolEntidad rol) {
		this.rol = UtilObjeto.esNulo(rol) ? new RolEntidad() : rol;
	}

	public EstadoCuentaEntidad getEstadoCuenta() {
		return estadoCuenta;
	}

	public void setEstadoCuenta(EstadoCuentaEntidad estadoCuenta) {
		this.estadoCuenta = UtilObjeto.esNulo(estadoCuenta) ? new EstadoCuentaEntidad() : estadoCuenta;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = UtilFechaHora.obtenerValorDefecto(fechaRegistro);
	}
}
