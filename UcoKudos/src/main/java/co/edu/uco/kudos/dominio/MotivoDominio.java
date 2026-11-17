package co.edu.uco.kudos.dominio;

import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public class MotivoDominio {

	private UUID id;
	private String nombre;

	private MotivoDominio(Builder builder) {
		this.id = builder.id;
		this.nombre = builder.nombre;
	}

	public static MotivoDominio obtenerValorDefecto(MotivoDominio motivo) {
		return UtilObjeto.esNulo(motivo) ? new Builder().build() : motivo;
	}

	public UUID getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public static class Builder {

		private UUID id;
		private String nombre;

		public Builder() {
			id = UtilUUID.obtenerUUIDDefecto();
			nombre = UtilTexto.VACIA;
		}

		public Builder id(UUID id) {
			this.id = UtilUUID.obtenerValorDefecto(id);
			return this;
		}

		public Builder nombre(String nombre) {
			this.nombre = UtilTexto.quitarEspaciosEnBlanco(nombre);
			return this;
		}

		public MotivoDominio build() {
			return new MotivoDominio(this);
		}
	}
}
