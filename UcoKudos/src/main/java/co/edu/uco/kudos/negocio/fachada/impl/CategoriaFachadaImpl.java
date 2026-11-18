package co.edu.uco.kudos.negocio.fachada.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dto.CategoriaDTO;
import co.edu.uco.kudos.negocio.fachada.CategoriaFachada;
import co.edu.uco.kudos.negocio.fachada.assembler.impl.CategoriaDTOAssembler;
import co.edu.uco.kudos.negocio.negocio.impl.CategoriaNegocioImpl;
import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosExcepcion;
import co.edu.uco.kudos.transversal.excepciones.UcoKudosFachadaExcepcion;

public final class CategoriaFachadaImpl implements CategoriaFachada {

	@Override
	public CategoriaDTO registrarCategoria(CategoriaDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return registrarCategoriaConfirmandoTransaccion(daoFactory, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private CategoriaDTO registrarCategoriaConfirmandoTransaccion(DAOFactory daoFactory, CategoriaDTO datos) {
		try {
			var categoriaNegocio = new CategoriaNegocioImpl(daoFactory);
			var datosDominio = CategoriaDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = categoriaNegocio.registrarCategoria(datosDominio);
			daoFactory.confirmarTransaccion();
			return CategoriaDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.CategoriaFachada.USUARIO_ERROR_REGISTRANDO_CATEGORIA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public List<CategoriaDTO> consultarCategorias(CategoriaDTO filtro) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			var categoriaNegocio = new CategoriaNegocioImpl(daoFactory);
			var filtroDominio = CategoriaDTOAssembler.obtenerInstancia().convertirADominio(filtro);
			var resultados = categoriaNegocio.consultarCategorias(filtroDominio);
			return CategoriaDTOAssembler.obtenerInstancia().convertirADTO(resultados);
		} catch (UcoKudosExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.CategoriaFachada.USUARIO_ERROR_CONSULTANDO_CATEGORIAS;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	@Override
	public CategoriaDTO cambiarNombreCategoria(UUID id, CategoriaDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return cambiarNombreCategoriaConfirmandoTransaccion(daoFactory, id, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private CategoriaDTO cambiarNombreCategoriaConfirmandoTransaccion(DAOFactory daoFactory, UUID id,
			CategoriaDTO datos) {
		try {
			var categoriaNegocio = new CategoriaNegocioImpl(daoFactory);
			var datosDominio = CategoriaDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = categoriaNegocio.cambiarNombreCategoria(id, datosDominio);
			daoFactory.confirmarTransaccion();
			return CategoriaDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.CategoriaFachada.USUARIO_ERROR_CAMBIANDO_NOMBRE_CATEGORIA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public CategoriaDTO cambiarDescripcionCategoria(UUID id, CategoriaDTO datos) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			return cambiarDescripcionCategoriaConfirmandoTransaccion(daoFactory, id, datos);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private CategoriaDTO cambiarDescripcionCategoriaConfirmandoTransaccion(DAOFactory daoFactory, UUID id,
			CategoriaDTO datos) {
		try {
			var categoriaNegocio = new CategoriaNegocioImpl(daoFactory);
			var datosDominio = CategoriaDTOAssembler.obtenerInstancia().convertirADominio(datos);
			var resultado = categoriaNegocio.cambiarDescripcionCategoria(id, datosDominio);
			daoFactory.confirmarTransaccion();
			return CategoriaDTOAssembler.obtenerInstancia().convertirADTO(resultado);
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.CategoriaFachada.USUARIO_ERROR_CAMBIANDO_DESCRIPCION_CATEGORIA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void activarCategoria(UUID id) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			activarCategoriaConfirmandoTransaccion(daoFactory, id);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void activarCategoriaConfirmandoTransaccion(DAOFactory daoFactory, UUID id) {
		try {
			var categoriaNegocio = new CategoriaNegocioImpl(daoFactory);
			categoriaNegocio.activarCategoria(id);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.CategoriaFachada.USUARIO_ERROR_ACTIVANDO_CATEGORIA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void desactivarCategoria(UUID id) {
		var daoFactory = DAOFactory.obtenerFactoria();
		try {
			daoFactory.iniciarTransaccion();
			desactivarCategoriaConfirmandoTransaccion(daoFactory, id);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	private void desactivarCategoriaConfirmandoTransaccion(DAOFactory daoFactory, UUID id) {
		try {
			var categoriaNegocio = new CategoriaNegocioImpl(daoFactory);
			categoriaNegocio.desactivarCategoria(id);
			daoFactory.confirmarTransaccion();
		} catch (UcoKudosExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = CatalogoMensajes.CategoriaFachada.USUARIO_ERROR_DESACTIVANDO_CATEGORIA;
			throw UcoKudosFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}
}
