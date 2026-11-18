package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarRolPermiteRegistroPropioRule implements Rule<UsuarioEntidad> {

	private static final Rule<UsuarioEntidad> INSTANCIA = new AsegurarRolPermiteRegistroPropioRule();

	private AsegurarRolPermiteRegistroPropioRule() {

	}

	public static Rule<UsuarioEntidad> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(UsuarioEntidad usuario, DAOFactory daoFactory) {
		var nombreRol = ConsultorCatalogos.nombreRol(daoFactory, usuario.getRol().getId());
		ValidadorNegocio.asegurarCondicion(ConsultorCatalogos.coincide(nombreRol,
				NombresCatalogo.Rol.ESTUDIANTE, NombresCatalogo.Rol.DOCENTE,
				NombresCatalogo.Rol.COORDINADOR_PROGRAMA, NombresCatalogo.Rol.COORDINADOR_FACULTAD,
				NombresCatalogo.Rol.COORDINADOR_ACADEMICO), CatalogoMensajes.Reglas.ROL_NO_PERMITE_REGISTRO_PROPIO);
	}
}
