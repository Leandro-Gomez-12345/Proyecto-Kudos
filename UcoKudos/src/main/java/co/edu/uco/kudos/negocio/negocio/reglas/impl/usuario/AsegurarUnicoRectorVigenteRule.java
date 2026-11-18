package co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.reglas.Rule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorUsuarios;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ValidadorNegocio;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.catalogo.NombresCatalogo;

public final class AsegurarUnicoRectorVigenteRule implements Rule<Object> {

	private static final Rule<Object> INSTANCIA = new AsegurarUnicoRectorVigenteRule();

	private AsegurarUnicoRectorVigenteRule() {

	}

	public static Rule<Object> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void ejecutar(Object... datos) {
		var usuario = (UsuarioEntidad) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var rol = ConsultorCatalogos.obtenerRol(daoFactory, NombresCatalogo.Rol.RECTOR);
		if (!rol.getId().equals(usuario.getRol().getId())) {
			return;
		}

		var filtro = new UsuarioEntidad();
		filtro.setRol(rol);
		var hayOtroVigente = daoFactory.obtenerUsuarioDAO().consultarPorFiltro(filtro).stream()
				.anyMatch(otro -> !otro.getId().equals(usuario.getId()) && esAprobadoOActivo(otro, daoFactory));
		ValidadorNegocio.asegurarCondicion(!hayOtroVigente, CatalogoMensajes.Reglas.RECTOR_YA_EXISTE);
	}

	private static boolean esAprobadoOActivo(UsuarioEntidad usuario, DAOFactory daoFactory) {
		return ConsultorUsuarios.tieneEstadoCuenta(daoFactory, usuario.getId(),
				NombresCatalogo.EstadoCuenta.APROBADA, NombresCatalogo.EstadoCuenta.ACTIVA);
	}
}
