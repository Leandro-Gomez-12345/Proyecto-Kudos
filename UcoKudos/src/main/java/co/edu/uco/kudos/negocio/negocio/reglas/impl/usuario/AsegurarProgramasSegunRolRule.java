package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.UsuarioConProgramas;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarProgramasSegunRolRule implements Rule<UsuarioConProgramas> {

	private static final Rule<UsuarioConProgramas> INSTANCIA = new AsegurarProgramasSegunRolRule();

	private AsegurarProgramasSegunRolRule() {

	}

	public static Rule<UsuarioConProgramas> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UsuarioConProgramas registro, DAOFactory daoFactory) {
		var nombreRol = ConsultorCatalogos.nombreRol(daoFactory, registro.usuario().getRol().getId());
		var requierePrograma = ConsultorCatalogos.coincide(nombreRol, NombresCatalogo.Rol.ESTUDIANTE,
				NombresCatalogo.Rol.DOCENTE);
		ValidadorNegocio.asegurarCondicion(!requierePrograma || !registro.programas().isEmpty(),
				CatalogoMensajes.Reglas.PROGRAMA_OBLIGATORIO_SEGUN_ROL);
	}
}
