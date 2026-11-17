package co.edu.uco.kudos.dominio;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilBooleano;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public class UsuarioDominio {

	private UUID id;
	private String nombre;
	private String apellido;
	private String correoInstitucional;
	private String celular;
	private boolean correoVerificado;
	private boolean celularVerificado;
	private RolDominio rol;
	private EstadoCuentaDominio estadoCuenta;
	private LocalDateTime fechaRegistro;

	private UsuarioDominio(Builder builder) {
		this.id = builder.id;
		this.nombre = builder.nombre;
		this.apellido = builder.apellido;
		this.correoInstitucional = builder.correoInstitucional;
		this.celular = builder.celular;
		this.correoVerificado = builder.correoVerificado;
		this.celularVerificado = builder.celularVerificado;
		this.rol = builder.rol;
		this.estadoCuenta = builder.estadoCuenta;
		this.fechaRegistro = builder.fechaRegistro;
	}

	public static UsuarioDominio obtenerValorDefecto(UsuarioDominio usuario) {
		return UtilObjeto.esNulo(usuario) ? new Builder().build() : usuario;
	}

	public UUID getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getCorreoInstitucional() {
		return correoInstitucional;
	}

	public String getCelular() {
		return celular;
	}

	public boolean isCorreoVerificado() {
		return correoVerificado;
	}

	public boolean isCelularVerificado() {
		return celularVerificado;
	}

	public RolDominio getRol() {
		return rol;
	}

	public EstadoCuentaDominio getEstadoCuenta() {
		return estadoCuenta;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public static class Builder {

		private UUID id;
		private String nombre;
		private String apellido;
		private String correoInstitucional;
		private String celular;
		private boolean correoVerificado;
		private boolean celularVerificado;
		private RolDominio rol;
		private EstadoCuentaDominio estadoCuenta;
		private LocalDateTime fechaRegistro;

		public Builder() {
			id = UtilUUID.obtenerUUIDDefecto();
			nombre = UtilTexto.VACIA;
			apellido = UtilTexto.VACIA;
			correoInstitucional = UtilTexto.VACIA;
			celular = UtilTexto.VACIA;
			correoVerificado = UtilBooleano.NO;
			celularVerificado = UtilBooleano.NO;
			rol = RolDominio.obtenerValorDefecto(null);
			estadoCuenta = EstadoCuentaDominio.obtenerValorDefecto(null);
			fechaRegistro = UtilFechaHora.FECHA_HORA_DEFECTO;
		}

		public Builder id(UUID id) {
			this.id = UtilUUID.obtenerValorDefecto(id);
			return this;
		}

		public Builder nombre(String nombre) {
			this.nombre = UtilTexto.quitarEspaciosEnBlanco(nombre);
			return this;
		}

		public Builder apellido(String apellido) {
			this.apellido = UtilTexto.quitarEspaciosEnBlanco(apellido);
			return this;
		}

		public Builder correoInstitucional(String correoInstitucional) {
			this.correoInstitucional = UtilTexto.quitarEspaciosEnBlanco(correoInstitucional);
			return this;
		}

		public Builder celular(String celular) {
			this.celular = UtilTexto.quitarEspaciosEnBlanco(celular);
			return this;
		}

		public Builder correoVerificado(Boolean correoVerificado) {
			this.correoVerificado = UtilBooleano.obtenerValorDefecto(correoVerificado, UtilBooleano.NO);
			return this;
		}

		public Builder celularVerificado(Boolean celularVerificado) {
			this.celularVerificado = UtilBooleano.obtenerValorDefecto(celularVerificado, UtilBooleano.NO);
			return this;
		}

		public Builder rol(RolDominio rol) {
			this.rol = RolDominio.obtenerValorDefecto(rol);
			return this;
		}

		public Builder estadoCuenta(EstadoCuentaDominio estadoCuenta) {
			this.estadoCuenta = EstadoCuentaDominio.obtenerValorDefecto(estadoCuenta);
			return this;
		}

		public Builder fechaRegistro(LocalDateTime fechaRegistro) {
			this.fechaRegistro = UtilFechaHora.obtenerValorDefecto(fechaRegistro);
			return this;
		}

		public UsuarioDominio build() {
			return new UsuarioDominio(this);
		}
	}
}
