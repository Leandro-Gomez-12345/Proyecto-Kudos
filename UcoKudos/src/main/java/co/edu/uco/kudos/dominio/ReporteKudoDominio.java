package co.edu.uco.kudos.dominio;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public class ReporteKudoDominio {

	private UUID id;
	private KudoDominio kudo;
	private UsuarioDominio usuario;
	private MotivoDominio motivo;
	private String descripcion;
	private RevisionKudoDominio revisionKudo;
	private LocalDateTime fechaReporte;

	private ReporteKudoDominio(Builder builder) {
		this.id = builder.id;
		this.kudo = builder.kudo;
		this.usuario = builder.usuario;
		this.motivo = builder.motivo;
		this.descripcion = builder.descripcion;
		this.revisionKudo = builder.revisionKudo;
		this.fechaReporte = builder.fechaReporte;
	}

	public static ReporteKudoDominio obtenerValorDefecto(ReporteKudoDominio reporteKudo) {
		return UtilObjeto.esNulo(reporteKudo) ? new Builder().build() : reporteKudo;
	}

	public UUID getId() {
		return id;
	}

	public KudoDominio getKudo() {
		return kudo;
	}

	public UsuarioDominio getUsuario() {
		return usuario;
	}

	public MotivoDominio getMotivo() {
		return motivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public RevisionKudoDominio getRevisionKudo() {
		return revisionKudo;
	}

	public LocalDateTime getFechaReporte() {
		return fechaReporte;
	}

	public static class Builder {

		private UUID id;
		private KudoDominio kudo;
		private UsuarioDominio usuario;
		private MotivoDominio motivo;
		private String descripcion;
		private RevisionKudoDominio revisionKudo;
		private LocalDateTime fechaReporte;

		public Builder() {
			id = UtilUUID.obtenerUUIDDefecto();
			kudo = KudoDominio.obtenerValorDefecto(null);
			usuario = UsuarioDominio.obtenerValorDefecto(null);
			motivo = MotivoDominio.obtenerValorDefecto(null);
			descripcion = UtilTexto.VACIA;
			revisionKudo = RevisionKudoDominio.obtenerValorDefecto(null);
			fechaReporte = UtilFechaHora.FECHA_HORA_DEFECTO;
		}

		public Builder id(UUID id) {
			this.id = UtilUUID.obtenerValorDefecto(id);
			return this;
		}

		public Builder kudo(KudoDominio kudo) {
			this.kudo = KudoDominio.obtenerValorDefecto(kudo);
			return this;
		}

		public Builder usuario(UsuarioDominio usuario) {
			this.usuario = UsuarioDominio.obtenerValorDefecto(usuario);
			return this;
		}

		public Builder motivo(MotivoDominio motivo) {
			this.motivo = MotivoDominio.obtenerValorDefecto(motivo);
			return this;
		}

		public Builder descripcion(String descripcion) {
			this.descripcion = UtilTexto.quitarEspaciosEnBlanco(descripcion);
			return this;
		}

		public Builder revisionKudo(RevisionKudoDominio revisionKudo) {
			this.revisionKudo = RevisionKudoDominio.obtenerValorDefecto(revisionKudo);
			return this;
		}

		public Builder fechaReporte(LocalDateTime fechaReporte) {
			this.fechaReporte = UtilFechaHora.obtenerValorDefecto(fechaReporte);
			return this;
		}

		public ReporteKudoDominio build() {
			return new ReporteKudoDominio(this);
		}
	}
}
