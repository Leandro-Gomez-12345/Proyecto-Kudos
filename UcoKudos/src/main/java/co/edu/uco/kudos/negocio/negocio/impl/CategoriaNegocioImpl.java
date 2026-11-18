package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.CategoriaDominio;
import co.edu.uco.kudos.negocio.negocio.CategoriaNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.CategoriaEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria.AsegurarCategoriaActivaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria.AsegurarCategoriaExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria.AsegurarCategoriaInactivaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria.AsegurarFormatoCategoriaValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria.AsegurarFormatoDescripcionCategoriaValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria.AsegurarFormatoNombreCategoriaValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria.AsegurarIdCategoriaUnicoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria.AsegurarNombreCategoriaNoExistaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria.AsegurarQuedeOtraCategoriaActivaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.categoria.AsignarCategoriaActivaAlCrearRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.Auditor;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioGestionaCategoriasRule;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilBooleano;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class CategoriaNegocioImpl implements CategoriaNegocio {

	private final DAOFactory daoFactory;

	public CategoriaNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public CategoriaDominio registrarCategoria(CategoriaDominio datos) {
		var categoria = CategoriaEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioGestionaCategoriasRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarFormatoCategoriaValidoRule.obtenerInstancia().ejecutar(categoria, daoFactory);
		AsegurarNombreCategoriaNoExistaRule.obtenerInstancia().ejecutar(categoria, daoFactory);
		AsegurarIdCategoriaUnicoRule.obtenerInstancia().ejecutar(categoria, daoFactory);
		AsignarCategoriaActivaAlCrearRule.obtenerInstancia().ejecutar(categoria, daoFactory);

		daoFactory.obtenerCategoriaDAO().crear(categoria);
		Auditor.registrar(daoFactory, EventoAuditable.REGISTRAR_CATEGORIA, categoria.getId(),
				UtilTexto.concatenar("Categoría creada: ", categoria.getNombre()));
		return CategoriaEntidadAssembler.obtenerInstancia().convertirADominio(categoria);
	}

	@Override
	public List<CategoriaDominio> consultarCategorias(CategoriaDominio filtro) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);

		var filtroEntidad = CategoriaEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		var categorias = daoFactory.obtenerCategoriaDAO().consultarPorFiltro(filtroEntidad);
		return CategoriaEntidadAssembler.obtenerInstancia().convertirADominio(categorias);
	}

	@Override
	public CategoriaDominio cambiarNombreCategoria(UUID id, CategoriaDominio datos) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioGestionaCategoriasRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarCategoriaExisteRule.obtenerInstancia().ejecutar(id, daoFactory);

		var categoria = daoFactory.obtenerCategoriaDAO().consultarPorId(id);
		var nombreAnterior = categoria.getNombre();
		categoria.setNombre(datos.getNombre());
		AsegurarFormatoNombreCategoriaValidoRule.obtenerInstancia().ejecutar(categoria, daoFactory);
		AsegurarNombreCategoriaNoExistaRule.obtenerInstancia().ejecutar(categoria, daoFactory);

		daoFactory.obtenerCategoriaDAO().actualizar(id, categoria);
		Auditor.registrar(daoFactory, EventoAuditable.CAMBIAR_NOMBRE_CATEGORIA, id,
				Auditor.detalleCambio(nombreAnterior, categoria.getNombre()));
		return CategoriaEntidadAssembler.obtenerInstancia().convertirADominio(categoria);
	}

	@Override
	public CategoriaDominio cambiarDescripcionCategoria(UUID id, CategoriaDominio datos) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioGestionaCategoriasRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarCategoriaExisteRule.obtenerInstancia().ejecutar(id, daoFactory);

		var categoria = daoFactory.obtenerCategoriaDAO().consultarPorId(id);
		var descripcionAnterior = categoria.getDescripcion();
		categoria.setDescripcion(datos.getDescripcion());
		AsegurarFormatoDescripcionCategoriaValidoRule.obtenerInstancia().ejecutar(categoria, daoFactory);

		daoFactory.obtenerCategoriaDAO().actualizar(id, categoria);
		Auditor.registrar(daoFactory, EventoAuditable.CAMBIAR_DESCRIPCION_CATEGORIA, id,
				Auditor.detalleCambio(descripcionAnterior, categoria.getDescripcion()));
		return CategoriaEntidadAssembler.obtenerInstancia().convertirADominio(categoria);
	}

	@Override
	public void activarCategoria(UUID id) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioGestionaCategoriasRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarCategoriaExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarCategoriaInactivaRule.obtenerInstancia().ejecutar(id, daoFactory);

		var categoria = daoFactory.obtenerCategoriaDAO().consultarPorId(id);
		categoria.setActiva(UtilBooleano.SI);
		daoFactory.obtenerCategoriaDAO().actualizar(id, categoria);
		Auditor.registrar(daoFactory, EventoAuditable.ACTIVAR_CATEGORIA, id,
				Auditor.detalleCambio("Inactiva", "Activa"));
	}

	@Override
	public void desactivarCategoria(UUID id) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioGestionaCategoriasRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarCategoriaExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarCategoriaActivaRule.obtenerInstancia().ejecutar(id, daoFactory);
		AsegurarQuedeOtraCategoriaActivaRule.obtenerInstancia().ejecutar(id, daoFactory);

		var categoria = daoFactory.obtenerCategoriaDAO().consultarPorId(id);
		categoria.setActiva(UtilBooleano.NO);
		daoFactory.obtenerCategoriaDAO().actualizar(id, categoria);
		Auditor.registrar(daoFactory, EventoAuditable.DESACTIVAR_CATEGORIA, id,
				Auditor.detalleCambio("Activa", "Inactiva"));
	}
}
