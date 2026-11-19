package co.edu.uco.kudos.negocio.negocio.impl;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.UsuarioDominio;
import co.edu.uco.kudos.entidad.UsuarioEntidad;
import co.edu.uco.kudos.negocio.negocio.AutenticacionNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.UsuarioEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.autenticacion.AsegurarCorreoDeIngresoInstitucionalRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.ConsultorCatalogos;
import co.edu.uco.kudos.transversal.utilitarios.UtilCorreoInstitucional;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class AutenticacionNegocioImpl implements AutenticacionNegocio {

	private final DAOFactory daoFactory;

	public AutenticacionNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public UsuarioDominio iniciarSesion(String correo) {
		AsegurarCorreoDeIngresoInstitucionalRule.obtenerInstancia().ejecutar(correo);
		return consultarUsuarioPorCorreo(correo);
	}

	@Override
	public UsuarioDominio consultarUsuarioPorCorreo(String correo) {
		var correoNormalizado = UtilCorreoInstitucional.normalizar(correo);

		if (UtilTexto.esVacia(correoNormalizado)) {
			return UsuarioDominio.obtenerValorDefecto(null);
		}

		var filtro = new UsuarioEntidad();
		filtro.setCorreoInstitucional(correoNormalizado);

		var usuarios = daoFactory.obtenerUsuarioDAO().consultarPorFiltro(filtro);
		if (usuarios.isEmpty()) {
			return UsuarioDominio.obtenerValorDefecto(null);
		}

		var usuario = usuarios.get(0);
		usuario.getRol().setNombre(ConsultorCatalogos.nombreRol(daoFactory, usuario.getRol().getId()));
		usuario.getEstadoCuenta().setNombre(
				ConsultorCatalogos.nombreEstadoCuenta(daoFactory, usuario.getEstadoCuenta().getId()));
		return UsuarioEntidadAssembler.obtenerInstancia().convertirADominio(usuario);
	}
}
