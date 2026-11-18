package co.edu.uco.kudos.transversal.catalogo;

public enum EventoAuditable {

	REGISTRAR_CATEGORIA("Registrar la categoría", "Categoria"),
	CAMBIAR_NOMBRE_CATEGORIA("Cambiar el nombre de la categoría", "Categoria"),
	CAMBIAR_DESCRIPCION_CATEGORIA("Cambiar la descripción de la categoría", "Categoria"),
	ACTIVAR_CATEGORIA("Activar la categoría", "Categoria"),
	DESACTIVAR_CATEGORIA("Desactivar la categoría", "Categoria"),
	REGISTRAR_FACULTAD("Registrar la facultad", "Facultad"),
	CAMBIAR_NOMBRE_FACULTAD("Cambiar el nombre de la facultad", "Facultad"),
	REGISTRAR_USUARIO("Registrarse en la plataforma", "Usuario"),
	PRESENTAR_NUEVAMENTE_REGISTRO_USUARIO("Presentar de nuevo el registro", "Usuario"),
	REGISTRAR_RECTOR("Registrar al Rector", "Usuario"),
	REGISTRAR_ADMINISTRADOR("Registrar a un Administrador", "Usuario"),
	VERIFICAR_CORREO_USUARIO("Validar el correo institucional", "Usuario"),
	VERIFICAR_CELULAR_USUARIO("Validar el número celular", "Usuario"),
	ACTIVAR_CUENTA_USUARIO("Activar la cuenta", "Usuario"),
	CAMBIAR_NOMBRE_USUARIO("Actualizar su nombre y apellido", "Usuario"),
	CAMBIAR_CORREO_USUARIO("Actualizar su correo institucional", "Usuario"),
	CAMBIAR_CELULAR_USUARIO("Actualizar su número celular", "Usuario"),
	SUSPENDER_CUENTA_USUARIO("Suspender la cuenta", "Usuario"),
	REACTIVAR_CUENTA_USUARIO("Reactivar la cuenta suspendida", "Usuario"),
	DESACTIVAR_CUENTA_USUARIO("Desactivar la cuenta", "Usuario"),
	CAMBIAR_ROL_USUARIO("Cambiar el rol de la persona", "Usuario"),
	APROBAR_USUARIO("Aprobar el registro de la persona", "Usuario"),
	RECHAZAR_USUARIO("Rechazar el registro de la persona", "Usuario"),
	REGISTRAR_PROGRAMA_ACADEMICO("Registrar el programa académico", "ProgramaAcademico"),
	CAMBIAR_NOMBRE_PROGRAMA_ACADEMICO("Cambiar el nombre del programa académico", "ProgramaAcademico"),
	ASIGNAR_COORDINADOR_FACULTAD("Asignar al coordinador de la facultad", "CoordinacionFacultad"),
	FINALIZAR_COORDINACION_FACULTAD("Finalizar la coordinación de la facultad", "CoordinacionFacultad"),
	ASIGNAR_COORDINADOR_PROGRAMA("Asignar al coordinador del programa académico", "CoordinacionPrograma"),
	FINALIZAR_COORDINACION_PROGRAMA("Finalizar la coordinación del programa académico", "CoordinacionPrograma"),
	VINCULAR_PERSONA_A_PROGRAMA_ACADEMICO("Vincular al estudiante o docente con el programa", "VinculacionAcademica"),
	DESVINCULAR_PERSONA_DE_PROGRAMA_ACADEMICO(
			"Desvincular al estudiante o docente del programa", "VinculacionAcademica"),
	CREAR_KUDO("Crear el Kudo", "Kudo"),
	VETAR_KUDO("Vetar el Kudo", "Kudo"),
	LIBERAR_KUDO("Liberar el Kudo tras la revisión", "Kudo"),
	REPLICAR_KUDO("Replicar el Kudo", "Replica"),
	RESPONDER_REPLICA("Responder la réplica", "Replica"),
	VETAR_REPLICA("Vetar la réplica", "Replica"),
	LIBERAR_REPLICA("Liberar la réplica tras la revisión", "Replica"),
	CALIFICAR_KUDO("Calificar el Kudo", "CalificacionKudo"),
	CAMBIAR_CALIFICACION_KUDO("Cambiar su calificación del Kudo", "CalificacionKudo"),
	CALIFICAR_REPLICA("Calificar la réplica", "CalificacionReplica"),
	CAMBIAR_CALIFICACION_REPLICA("Cambiar su calificación de la réplica", "CalificacionReplica"),
	INICIAR_REVISION_KUDO("Iniciar la revisión del Kudo", "RevisionKudo"),
	CERRAR_REVISION_KUDO_CON_VETO("Vetar el Kudo revisado", "RevisionKudo"),
	CERRAR_REVISION_KUDO_CON_LIBERACION("Liberar el Kudo revisado", "RevisionKudo"),
	VETAR_KUDO_DIRECTAMENTE("Vetar el Kudo directamente", "RevisionKudo"),
	INICIAR_REVISION_REPLICA("Iniciar la revisión de la réplica", "RevisionReplica"),
	CERRAR_REVISION_REPLICA_CON_VETO("Vetar la réplica revisada", "RevisionReplica"),
	CERRAR_REVISION_REPLICA_CON_LIBERACION("Liberar la réplica revisada", "RevisionReplica"),
	VETAR_REPLICA_DIRECTAMENTE("Vetar la réplica directamente", "RevisionReplica"),
	REPORTAR_KUDO("Reportar el Kudo", "ReporteKudo"),
	REPORTAR_REPLICA("Reportar la réplica", "ReporteReplica");

	private final String accion;
	private final String objetoAfectado;

	EventoAuditable(String accion, String objetoAfectado) {
		this.accion = accion;
		this.objetoAfectado = objetoAfectado;
	}

	public String getAccion() {
		return accion;
	}

	public String getObjetoAfectado() {
		return objetoAfectado;
	}

	public static boolean existe(String accion, String objetoAfectado) {
		for (var evento : values()) {
			if (evento.accion.equals(accion) && evento.objetoAfectado.equals(objetoAfectado)) {
				return true;
			}
		}
		return false;
	}
}
