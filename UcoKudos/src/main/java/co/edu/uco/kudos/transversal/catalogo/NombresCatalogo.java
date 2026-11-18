package co.edu.uco.kudos.transversal.catalogo;

public final class NombresCatalogo {

	private NombresCatalogo() {

	}

	public static final class Rol {

		public static final String ADMINISTRADOR = "Administrador";
		public static final String RECTOR = "Rector";
		public static final String COORDINADOR_ACADEMICO = "Coordinador Académico";
		public static final String COORDINADOR_FACULTAD = "Coordinador de Facultad";
		public static final String COORDINADOR_PROGRAMA = "Coordinador de Programa";
		public static final String DOCENTE = "Docente";
		public static final String ESTUDIANTE = "Estudiante";

		private Rol() {

		}
	}

	public static final class EstadoCuenta {

		public static final String PENDIENTE_APROBACION = "Pendiente de aprobación";
		public static final String APROBADA = "Aprobada";
		public static final String ACTIVA = "Activa";
		public static final String SUSPENDIDA = "Suspendida";
		public static final String DESACTIVADA = "Desactivada";
		public static final String RECHAZADA = "Rechazada";

		private EstadoCuenta() {

		}
	}

	public static final class EstadoContenido {

		public static final String CREADO = "Creado";
		public static final String VISIBLE = "Visible";
		public static final String REPORTADO = "Reportado";
		public static final String EN_REVISION = "En revisión";
		public static final String VETADO = "Vetado";

		private EstadoContenido() {

		}
	}

	public static final class EstadoRevision {

		public static final String EN_CURSO = "En curso";
		public static final String CONTENIDO_VETADO = "Contenido vetado";
		public static final String CONTENIDO_LIBERADO = "Contenido liberado";

		private EstadoRevision() {

		}
	}

	public static final class Motivo {

		public static final String OTRO = "Otro";

		private Motivo() {

		}
	}

	public static final class EstadoReporte {

		public static final String PENDIENTE = "Pendiente";
		public static final String EN_REVISION = "En revisión";
		public static final String ACEPTADO = "Aceptado";
		public static final String RECHAZADO = "Rechazado";

		private EstadoReporte() {

		}
	}
}
