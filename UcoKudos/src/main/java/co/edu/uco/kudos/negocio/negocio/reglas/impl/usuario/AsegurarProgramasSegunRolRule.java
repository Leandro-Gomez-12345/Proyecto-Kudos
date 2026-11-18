package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import java.util.List;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.ProgramaAcademicoEntidad;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarProgramasSegunRolRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarProgramasSegunRolRule();

	private AsegurarProgramasSegunRolRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void ejecutar(Object... datos) {
		var usuario = (UsuarioEntidad) datos[0];
		var programas = (List<ProgramaAcademicoEntidad>) datos[1];
		var daoFactory = (DAOFactory) datos[2];

		var nombreRol = ConsultorCatalogos.nombreRol(daoFactory, usuario.getRol().getId());
		var requierePrograma = ConsultorCatalogos.coincide(nombreRol, NombresCatalogo.Rol.ESTUDIANTE,
				NombresCatalogo.Rol.DOCENTE);
		ValidadorNegocio.asegurarCondicion(!requierePrograma || !programas.isEmpty(),
				CatalogoMensajes.Reglas.PROGRAMA_OBLIGATORIO_SEGUN_ROL);
	}
}
