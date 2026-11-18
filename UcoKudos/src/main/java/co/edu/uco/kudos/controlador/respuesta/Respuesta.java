package co.edu.uco.kudos.controlador.respuesta;

import java.time.LocalDateTime;
import java.util.List;

import co.edu.uco.kudos.transversal.logger.contexto.ContextoLog;
import co.edu.uco.kudos.transversal.utilitarios.UtilObjeto;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public abstract class Respuesta<D> {

	private LocalDateTime timestamp;
	private String correlationId;
	private List<String> mensajes;
	private List<D> datos;
	private EstadoRespuesta estado;

	protected Respuesta(List<String> mensajes, List<D> datos, EstadoRespuesta estado) {
		setTimestamp(LocalDateTime.now());
		setCorrelationId(ContextoLog.obtenerCorrelationId());
		setMensajes(mensajes);
		setDatos(datos);
		setEstado(estado);
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public List<String> getMensajes() {
		return mensajes;
	}

	public List<D> getDatos() {
		return datos;
	}

	public EstadoRespuesta getEstado() {
		return estado;
	}

	private void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	private void setCorrelationId(String correlationId) {
		this.correlationId = UtilTexto.esVacia(correlationId) ? UtilUUID.generarComoTexto() : correlationId;
	}

	private void setMensajes(List<String> mensajes) {
		this.mensajes = List.copyOf(UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(mensajes, List.of()));
	}

	private void setDatos(List<D> datos) {
		this.datos = List.copyOf(UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(datos, List.of()));
	}

	private void setEstado(EstadoRespuesta estado) {
		this.estado = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(estado, EstadoRespuesta.ERROR);
	}
}
