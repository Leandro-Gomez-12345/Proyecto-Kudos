package co.edu.uco.kudos.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class ReporteKudoDTO {

	private UUID id;
	private KudoDTO kudo;
	private UsuarioDTO usuario;
	private MotivoDTO motivo;
	private String descripcion;
	private RevisionKudoDTO revisionKudo;
	private LocalDateTime fechaReporte;

	public ReporteKudoDTO() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setKudo(new KudoDTO());
		setUsuario(new UsuarioDTO());
		setMotivo(new MotivoDTO());
		setDescripcion(UtilTexto.VACIA);
		setRevisionKudo(new RevisionKudoDTO());
		setFechaReporte(UtilFechaHora.FECHA_HORA_DEFECTO);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public KudoDTO getKudo() {
		return kudo;
	}

	public void setKudo(KudoDTO kudo) {
		this.kudo = UtilObjeto.esNulo(kudo) ? new KudoDTO() : kudo;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = UtilObjeto.esNulo(usuario) ? new UsuarioDTO() : usuario;
	}

	public MotivoDTO getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoDTO motivo) {
		this.motivo = UtilObjeto.esNulo(motivo) ? new MotivoDTO() : motivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = UtilTexto.quitarEspaciosEnBlanco(descripcion);
	}

	public RevisionKudoDTO getRevisionKudo() {
		return revisionKudo;
	}

	public void setRevisionKudo(RevisionKudoDTO revisionKudo) {
		this.revisionKudo = UtilObjeto.esNulo(revisionKudo) ? new RevisionKudoDTO() : revisionKudo;
	}

	public LocalDateTime getFechaReporte() {
		return fechaReporte;
	}

	public void setFechaReporte(LocalDateTime fechaReporte) {
		this.fechaReporte = UtilFechaHora.obtenerValorDefecto(fechaReporte);
	}
}
