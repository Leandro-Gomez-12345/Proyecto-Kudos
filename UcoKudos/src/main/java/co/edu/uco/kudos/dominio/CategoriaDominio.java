package co.edu.uco.kudos.dominio;

import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilBooleano;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public class CategoriaDominio {

	private UUID id;
	private String nombre;
	private String descripcion;
	private boolean activa;

	private CategoriaDominio(Builder builder) {
		this.id = builder.id;
		this.nombre = builder.nombre;
		this.descripcion = builder.descripcion;
		this.activa = builder.activa;
	}

	public static CategoriaDominio obtenerValorDefecto(CategoriaDominio categoria) {
		return UtilObjeto.esNulo(categoria) ? new Builder().build() : categoria;
	}

	public UUID getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean isActiva() {
		return activa;
	}

	public static class Builder {

		private UUID id;
		private String nombre;
		private String descripcion;
		private boolean activa;

		public Builder() {
			id = UtilUUID.obtenerUUIDDefecto();
			nombre = UtilTexto.VACIA;
			descripcion = UtilTexto.VACIA;
			activa = UtilBooleano.SI;
		}

		public Builder id(UUID id) {
			this.id = UtilUUID.obtenerValorDefecto(id);
			return this;
		}

		public Builder nombre(String nombre) {
			this.nombre = UtilTexto.quitarEspaciosEnBlanco(nombre);
			return this;
		}

		public Builder descripcion(String descripcion) {
			this.descripcion = UtilTexto.quitarEspaciosEnBlanco(descripcion);
			return this;
		}

		public Builder activa(Boolean activa) {
			this.activa = UtilBooleano.obtenerValorDefecto(activa, UtilBooleano.SI);
			return this;
		}

		public CategoriaDominio build() {
			return new CategoriaDominio(this);
		}
	}
}
