package co.edu.uco.kudos.negocio.negocio.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dao.factoria.DAOFactory;
import co.edu.uco.kudos.dominio.FacultadDominio;
import co.edu.uco.kudos.negocio.negocio.FacultadNegocio;
import co.edu.uco.kudos.negocio.negocio.assembler.impl.FacultadEntidadAssembler;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.comun.Auditor;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.facultad.AsegurarFacultadExisteRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.facultad.AsegurarFormatoFacultadValidoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.facultad.AsegurarIdFacultadUnicoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.facultad.AsegurarNombreFacultadNoExistaRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioActivoRule;
import co.edu.uco.kudos.negocio.negocio.reglas.impl.usuario.AsegurarUsuarioEsAdministradorRule;
import co.edu.uco.kudos.transversal.catalogo.EventoAuditable;
import co.edu.uco.kudos.transversal.contexto.ContextoEjecucion;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;

public final class FacultadNegocioImpl implements FacultadNegocio {

	private final DAOFactory daoFactory;

	public FacultadNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public FacultadDominio registrarFacultad(FacultadDominio datos) {
		var facultad = FacultadEntidadAssembler.obtenerInstancia().convertirAEntidad(datos);
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioEsAdministradorRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarFormatoFacultadValidoRule.obtenerInstancia().ejecutar(facultad);
		AsegurarNombreFacultadNoExistaRule.obtenerInstancia().ejecutar(facultad, daoFactory);
		AsegurarIdFacultadUnicoRule.obtenerInstancia().ejecutar(facultad, daoFactory);

		daoFactory.obtenerFacultadDAO().crear(facultad);
		Auditor.registrar(daoFactory, EventoAuditable.REGISTRAR_FACULTAD, facultad.getId(),
				UtilTexto.concatenar("Facultad creada: ", facultad.getNombre()));
		return FacultadEntidadAssembler.obtenerInstancia().convertirADominio(facultad);
	}

	@Override
	public List<FacultadDominio> consultarFacultades(FacultadDominio filtro) {
		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(ContextoEjecucion.obtenerIdEjecutor(), daoFactory);

		var filtroEntidad = FacultadEntidadAssembler.obtenerInstancia().convertirAEntidad(filtro);
		var facultades = daoFactory.obtenerFacultadDAO().consultarPorFiltro(filtroEntidad);
		return FacultadEntidadAssembler.obtenerInstancia().convertirADominio(facultades);
	}

	@Override
	public FacultadDominio cambiarNombreFacultad(UUID id, FacultadDominio datos) {
		var idEjecutor = ContextoEjecucion.obtenerIdEjecutor();

		AsegurarUsuarioActivoRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarUsuarioEsAdministradorRule.obtenerInstancia().ejecutar(idEjecutor, daoFactory);
		AsegurarFacultadExisteRule.obtenerInstancia().ejecutar(id, daoFactory);

		var facultad = daoFactory.obtenerFacultadDAO().consultarPorId(id);
		var nombreAnterior = facultad.getNombre();
		facultad.setNombre(datos.getNombre());
		AsegurarFormatoFacultadValidoRule.obtenerInstancia().ejecutar(facultad);
		AsegurarNombreFacultadNoExistaRule.obtenerInstancia().ejecutar(facultad, daoFactory);

		daoFactory.obtenerFacultadDAO().actualizar(id, facultad);
		Auditor.registrar(daoFactory, EventoAuditable.CAMBIAR_NOMBRE_FACULTAD, id,
				Auditor.detalleCambio(nombreAnterior, facultad.getNombre()));
		return FacultadEntidadAssembler.obtenerInstancia().convertirADominio(facultad);
	}
}
