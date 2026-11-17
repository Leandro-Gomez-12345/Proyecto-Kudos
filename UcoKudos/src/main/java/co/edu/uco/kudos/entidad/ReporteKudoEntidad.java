package co.edu.uco.kudos.entidad;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ReporteKudoEntidad {

	private UUID id;
	private KudoEntidad kudo;
	private UsuarioEntidad usuario;
	private MotivoEntidad motivo;
	private String descripcion;
	private RevisionKudoEntidad revisionKudo;
	private LocalDateTime fechaReporte;

	public ReporteKudoEntidad() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setKudo(new KudoEntidad());
		setUsuario(new UsuarioEntidad());
		setMotivo(new MotivoEntidad());
		setDescripcion(UtilTexto.VACIA);
		setRevisionKudo(new RevisionKudoEntidad());
		setFechaReporte(UtilFechaHora.FECHA_HORA_DEFECTO);
	}

	public ReporteKudoEntidad(UUID id, KudoEntidad kudo, UsuarioEntidad usuario, MotivoEntidad motivo,
			String descripcion, RevisionKudoEntidad revisionKudo, LocalDateTime fechaReporte) {
		setId(id);
		setKudo(kudo);
		setUsuario(usuario);
		setMotivo(motivo);
		setDescripcion(descripcion);
		setRevisionKudo(revisionKudo);
		setFechaReporte(fechaReporte);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public KudoEntidad getKudo() {
		return kudo;
	}

	public void setKudo(KudoEntidad kudo) {
		this.kudo = UtilObjeto.esNulo(kudo) ? new KudoEntidad() : kudo;
	}

	public UsuarioEntidad getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntidad usuario) {
		this.usuario = UtilObjeto.esNulo(usuario) ? new UsuarioEntidad() : usuario;
	}

	public MotivoEntidad getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoEntidad motivo) {
		this.motivo = UtilObjeto.esNulo(motivo) ? new MotivoEntidad() : motivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = UtilTexto.quitarEspaciosEnBlanco(descripcion);
	}

	public RevisionKudoEntidad getRevisionKudo() {
		return revisionKudo;
	}

	public void setRevisionKudo(RevisionKudoEntidad revisionKudo) {
		this.revisionKudo = UtilObjeto.esNulo(revisionKudo) ? new RevisionKudoEntidad() : revisionKudo;
	}

	public LocalDateTime getFechaReporte() {
		return fechaReporte;
	}

	public void setFechaReporte(LocalDateTime fechaReporte) {
		this.fechaReporte = UtilFechaHora.obtenerValorDefecto(fechaReporte);
	}
}
