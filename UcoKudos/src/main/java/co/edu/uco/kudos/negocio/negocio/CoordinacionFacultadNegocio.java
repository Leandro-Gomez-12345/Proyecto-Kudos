package co.edu.uco.kudos.negocio.negocio;

import java.util.List;

import co.edu.uco.kudos.dominio.CoordinacionFacultadDominio;

public interface CoordinacionFacultadNegocio {

	CoordinacionFacultadDominio asignarCoordinadorFacultad(CoordinacionFacultadDominio datos);

	void finalizarCoordinacionFacultad(CoordinacionFacultadDominio filtro, CoordinacionFacultadDominio datos);

	List<CoordinacionFacultadDominio> consultarCoordinacionesFacultad(CoordinacionFacultadDominio filtro);
}
